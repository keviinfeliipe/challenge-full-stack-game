package co.com.challenge.usecase;

import co.com.challenge.model.carta.gateway.CartaRepository;
import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.CartaFactory;
import co.com.challenge.model.juego.command.IniciarJuegoCommand;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.value.CartaId;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

import java.util.*;

public class RepartirCartasUseCase extends UseCase<RequestCommand<IniciarJuegoCommand>, ResponseEvents> {

    private List<Carta> listaDeCartas = new ArrayList<>();
    private final CartaRepository cartaRepository;

    public RepartirCartasUseCase(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }

    @Override
    public void executeUseCase(RequestCommand<IniciarJuegoCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var juegoId = JuegoId.of(command.getJuegoId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);

        agrecarCartasAMazoPrincipal(juego);

        listaDeCartas = new ArrayList<>(new HashSet<>(juego.mazo().cartas()));
        juego.jugadores().forEach(jugador -> {
            Collections.shuffle(listaDeCartas);
            var cartaFactory = new CartaFactory();
            listaDeCartas.stream().limit(5).forEach(cartaFactory::add);
            juego.agregarCartasJugador(jugador.identity(),cartaFactory);
            eliminarCartas();
        });
        juego.iniciarJuego();
        juego.repartirCartas();
        juego.mostrarJuego(juego.identity(), new ArrayList<>(juego.jugadores()), juego.jugando());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }

    private void agrecarCartasAMazoPrincipal(Juego juego){
        var cartaFactory = new CartaFactory();
        var cartasMaestra = cartaRepository.findAll().collectList().block();
        Collections.shuffle(cartasMaestra);
        cartasMaestra.forEach(cartaMaestra -> {
            cartaFactory.add(new Carta(CartaId.of(cartaMaestra.getId()) , cartaMaestra.getPoder()));
        });
        juego.agregarCartasMazoPrincipal(cartaFactory);
    };


    private void eliminarCartas() {
        for (int i = 0; i < 5; i++) {
            listaDeCartas.remove(i);
        }
    }


}
