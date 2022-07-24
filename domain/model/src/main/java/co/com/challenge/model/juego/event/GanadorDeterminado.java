package co.com.challenge.model.juego.event;


import co.com.sofka.domain.generic.DomainEvent;

public class GanadorDeterminado extends DomainEvent {
    public GanadorDeterminado() {
        super("juego.GanadorDeterminado");
    }
}
