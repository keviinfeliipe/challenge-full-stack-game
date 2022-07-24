package co.com.challenge.model.juego;

import co.com.sofka.domain.generic.DomainEvent;

public class TiempoTerminado extends DomainEvent {
    public TiempoTerminado() {
        super("juego.TiempoTerminado");
    }
}
