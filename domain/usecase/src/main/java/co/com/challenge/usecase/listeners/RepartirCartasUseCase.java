package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.event.CartasMazoPrincipalAgregadas;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

public class RepartirCartasUseCase extends UseCase<TriggeredEvent<CartasMazoPrincipalAgregadas>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<CartasMazoPrincipalAgregadas> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);

        juego.jugadores().forEach(jugador -> {
            juego.mazo().cartas()
                    .stream()
                    .limit(5)
                    .forEach(carta -> {
                        juego.agregarCartaJugador(jugador.identity(),carta);
                    });
            for(int i = 0; i<5; i++){
                var carta = juego.mazo().cartas().stream().limit(1).findFirst().orElseThrow();
                juego.mazo().cartas().remove(carta);
            }
        });
        juego.repartirCartas();
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
