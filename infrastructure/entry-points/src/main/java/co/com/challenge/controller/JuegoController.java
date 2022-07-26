package co.com.challenge.controller;

import co.com.challenge.model.juego.command.JuegoJugadorCommand;
import co.com.challenge.model.juego.command.CrearJuegoCommand;
import co.com.challenge.model.juego.command.CrearJugadorCommand;
import co.com.challenge.model.juego.command.IniciarJuegoCommand;
import co.com.challenge.model.juego.command.JugarCartaCommand;
import co.com.challenge.usecase.*;
import co.com.challenge.usecase.RepartirCartasUseCase;
import co.com.challenge.usecase.model.JugadorActual;
import co.com.challenge.usecase.model.JuegoActivo;
import co.com.challenge.usecase.model.JuegoInformacion;
import co.com.challenge.usecase.service.JuegoActivoService;
import co.com.challenge.usecase.service.JuegoInformacionService;
import co.com.challenge.usecase.service.JugadorCartasService;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.infraestructure.asyn.SubscriberEvent;
import co.com.sofka.infraestructure.repository.EventStoreRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@RestController
@Component
@RequestMapping("/api/v1/juego")
public class JuegoController {


    private final CrearJuegoUseCase crearJuegoUseCase;
    private final CrearJugadorUseCase crearJugadorUseCase;
    private final EventStoreRepository eventStoreRepository;
    private final SubscriberEvent subscriberEvent;
    private final JugarCartaUseCase jugarCartaUseCase;
    private final RepartirCartasUseCase repartirCartasUseCase;
    private final JuegoActivoService juegoActivoService;
    private final JugadorCartasService jugadorCartasService;
    private final JuegoInformacionService juegoInformacionService;

    public JuegoController(CrearJuegoUseCase useCase,
                           CrearJugadorUseCase crearJugadorUseCase,
                           EventStoreRepository eventStoreRepository,
                           SubscriberEvent subscriberEvent, JugarCartaUseCase jugarCartaUseCase,
                           RepartirCartasUseCase repartirCartasUseCase, JuegoActivoService juegoActivoService, JugadorCartasService jugadorCartasService, JuegoInformacionService juegoInformacionService) {
        this.crearJuegoUseCase = useCase;
        this.crearJugadorUseCase = crearJugadorUseCase;
        this.eventStoreRepository = eventStoreRepository;
        this.subscriberEvent = subscriberEvent;
        this.jugarCartaUseCase = jugarCartaUseCase;
        this.repartirCartasUseCase = repartirCartasUseCase;
        this.juegoActivoService = juegoActivoService;
        this.jugadorCartasService = jugadorCartasService;
        this.juegoInformacionService = juegoInformacionService;
    }

    @PostMapping("/crearjuego")
    public ResponseEntity<String> crearJuego(@RequestBody CrearJuegoCommand command){
        crearJuegoUseCase.addRepository(domainEventRepository());
        UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getJuegoId())
                .asyncExecutor(crearJuegoUseCase, new RequestCommand<>(command))
                .subscribe(subscriberEvent);
        return ResponseEntity.created(URI.create(""))
                .contentType(MediaType.APPLICATION_JSON)
                .body(command.getJuegoId());
    }


    @PostMapping("/agregarjugador")
    public ResponseEntity<String> agregarJugador(@RequestBody CrearJugadorCommand command){
        crearJugadorUseCase.addRepository(domainEventRepository());
        UseCaseHandler.getInstance()
                .asyncExecutor(crearJugadorUseCase, new RequestCommand<>(command))
                .subscribe(subscriberEvent);
        return ResponseEntity.created(URI.create(""))
                .contentType(MediaType.APPLICATION_JSON)
                .body(command.getJuegoId());
    }


    @PostMapping("/jugarcarta")
    public ResponseEntity<String> jugarCarta(@RequestBody JugarCartaCommand command){
        jugarCartaUseCase.addRepository(domainEventRepository());
        UseCaseHandler.getInstance()
                .asyncExecutor(jugarCartaUseCase, new RequestCommand<>(command))
                .subscribe(subscriberEvent);
        return ResponseEntity.created(URI.create(""))
                .contentType(MediaType.APPLICATION_JSON)
                .body(command.getJuegoId());
    }

    @GetMapping("/iniciarjuego/{id}")
    public ResponseEntity<String> iniciarJuego(@PathVariable String id){
        repartirCartasUseCase.addRepository(domainEventRepository());
        UseCaseHandler.getInstance()
                .asyncExecutor(repartirCartasUseCase, new RequestCommand<>(new IniciarJuegoCommand(id)))
                .subscribe(subscriberEvent);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(null);
    }

    @GetMapping()
    public Mono<ResponseEntity<Flux<JuegoActivo>>> juegosActivos(){
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(juegoActivoService.obtenerJuegosActivos())
        );
    }

    @PostMapping("/jugador")
    public Mono<ResponseEntity<Flux<JugadorActual>>> jugadorCartas(@RequestBody JuegoJugadorCommand juegoJugador){
        var jugador = jugadorCartasService.obtenerCartasDeJugador(juegoJugador.getJuegoId(), juegoJugador.getJugadorId());
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jugador)
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Mono<JuegoInformacion>>> obtenerInformacionDeJuego(@PathVariable String id){
        var jugador = juegoInformacionService.obtenerInformacionDelJuego(id);
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jugador)
        );
    }


    private DomainEventRepository domainEventRepository() {
        return new DomainEventRepository() {
            @Override
            public List<DomainEvent> getEventsBy(String aggregateId) {
                return eventStoreRepository.getEventsBy("juego", aggregateId);
            }

            @Override
            public List<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId) {
                return eventStoreRepository.getEventsBy(aggregateName, aggregateRootId);
            }
        };
    }
}
