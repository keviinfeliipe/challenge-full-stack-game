package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Incremental;

public class JuegoIniciado extends DomainEvent implements Incremental {

    public JuegoIniciado() {
        super("juego.JuegoIniciado");
    }


}
