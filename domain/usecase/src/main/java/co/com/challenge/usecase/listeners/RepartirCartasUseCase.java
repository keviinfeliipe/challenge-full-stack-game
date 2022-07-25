package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.CartaFactory;
import co.com.challenge.model.juego.Mazo;
import co.com.challenge.model.juego.event.CartasMazoPrincipalAgregadas;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.mongodb.client.model.Collation;

import java.util.*;
import java.util.stream.Collectors;

public class RepartirCartasUseCase extends UseCase<TriggeredEvent<CartasMazoPrincipalAgregadas>, ResponseEvents> {

    private List<Carta> listaDeCartas = new ArrayList<>();
    @Override
    public void executeUseCase(TriggeredEvent<CartasMazoPrincipalAgregadas> triggeredEvent) {

        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);

        listaDeCartas = new HashSet<>(juego.mazo().cartas()).stream().collect(Collectors.toList());

        juego.jugadores().forEach(jugador -> {
            Collections.shuffle(listaDeCartas);
            var cartaFactory= new CartaFactory();
                listaDeCartas.stream().limit(5)
                        .collect(Collectors.toList())
                        .stream()
                        .forEach(cartaFactory::add);
            juego.agregarCartasJugador(jugador.identity(),cartaFactory);
            eliminarCartas();
        });
        juego.repartirCartas();
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }


    private void eliminarCartas() {
        for (int i = 0; i < 5; i++) {
            listaDeCartas.remove(i);
        }
    }
}
