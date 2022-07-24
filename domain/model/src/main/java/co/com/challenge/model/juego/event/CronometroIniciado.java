package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class CronometroIniciado extends DomainEvent {
    public CronometroIniciado() {
        super("juego.CronometroIniciado");
    }
}
