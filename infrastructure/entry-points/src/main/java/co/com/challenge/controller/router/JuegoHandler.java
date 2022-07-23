package co.com.challenge.controller.router;

import co.com.challenge.model.juego.command.CrearJuegoCommand;
import co.com.challenge.usecase.CrearJuegoUseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.infraestructure.asyn.SubscriberEvent;
import co.com.sofka.infraestructure.repository.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import java.net.URI;
import java.util.List;


public class JuegoHandler {

    private CrearJuegoUseCase useCase;
    private EventStoreRepository eventStoreRepository;
    private SubscriberEvent subscriberEvent;

    public Mono<ServerResponse> crearJuego(ServerRequest request){

        /*
        var command = request.bodyToMono(CrearJuegoCommand.class);
        useCase.apply(domainEventRepository());
        return command.flatMap(command1 ->{
            UseCaseHandler
                    .getInstance()//
                    .asyncExecutor(useCase, new RequestCommand<>(command1))
                    .subscribe(subscriberEvent);
            return ServerResponse
                    .created(URI.create("api/v1/juego/".concat(command1.getJuegoId())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fromValue(command1.getJuegoId()));
        });

         */

        return null;
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
