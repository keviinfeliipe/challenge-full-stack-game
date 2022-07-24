package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Incremental;

public class CartasRepartidas extends DomainEvent implements Incremental {

    public CartasRepartidas() {
        super("juego.CartasRepartidas");

    }

}
