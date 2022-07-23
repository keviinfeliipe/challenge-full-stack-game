package co.com.challenge.model.juego;

import co.com.sofka.domain.generic.DomainEvent;

public class RondaCreada extends DomainEvent {
    public RondaCreada() {
        super("juego.RondaCreada");
    }
}
