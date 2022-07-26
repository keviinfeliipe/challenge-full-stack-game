package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class CartasDelTableroDeshabilitadas extends DomainEvent {
    public CartasDelTableroDeshabilitadas() {
        super("juego.CartasDelTableroDeshabilitadas");
    }
}
