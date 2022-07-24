package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class TableroCreado extends DomainEvent {
    public TableroCreado() {
        super("juego.TableroCreado");
    }
}
