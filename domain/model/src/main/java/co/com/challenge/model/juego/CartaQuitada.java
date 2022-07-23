package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

public class CartaQuitada extends DomainEvent {
    private final JugadorId jugadorId;

    public CartaQuitada(JugadorId jugadorId) {
        super("juego.CartaQuitada");
        this.jugadorId = jugadorId;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }
}
