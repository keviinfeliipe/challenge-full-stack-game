package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.event.CartaAlAzarSeleccionada;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

public class MostrarCartasApostadasUseCase extends UseCase<TriggeredEvent<CartaAlAzarSeleccionada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<CartaAlAzarSeleccionada> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);
        juego.mostrarCartasApostadas();
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));

    }
}
