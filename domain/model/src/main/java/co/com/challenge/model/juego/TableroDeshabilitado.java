package co.com.challenge.model.juego;

import co.com.sofka.domain.generic.DomainEvent;

public class TableroDeshabilitado extends DomainEvent {
    public TableroDeshabilitado() {
        super("juego.TableroDeshabilitado");
    }
}
