package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class CartaAlAzarSeleccionada extends DomainEvent {
    public CartaAlAzarSeleccionada() {
        super("juego.CartaAlAzarSeleccionada");
    }
}
