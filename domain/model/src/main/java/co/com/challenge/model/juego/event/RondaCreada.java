package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class RondaCreada extends DomainEvent {
    public RondaCreada() {
        super("juego.RondaCreada");
    }
}
