package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class TableroHabilitado extends DomainEvent {
    public TableroHabilitado() {
        super("juego.TableroHabilitado");
    }
}
