package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class TiempoDescontado extends DomainEvent {
    public TiempoDescontado() {
        super("juego.TiempoDescontado");
    }
}
