package co.com.challenge.model.juego;

import co.com.sofka.domain.generic.DomainEvent;

public class TableroCreado extends DomainEvent {
    public TableroCreado() {
        super("juego.TableroCreado");
    }
}
