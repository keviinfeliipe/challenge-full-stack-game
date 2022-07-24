package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.event.CronometroIniciado;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import reactor.core.publisher.Flux;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class xUseCase extends UseCase<TriggeredEvent<CronometroIniciado>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<CronometroIniciado> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);
        //emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));

    }
}
