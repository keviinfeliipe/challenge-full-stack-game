package co.com.challenge.model.juego.event;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

public class CartaQuitada extends DomainEvent {
    private final JugadorId jugadorId;
    private final Carta carta;

    public CartaQuitada(JugadorId jugadorId, Carta carta) {
        super("juego.CartaQuitada");
        this.jugadorId = jugadorId;
        this.carta = carta;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Carta getCarta() {
        return carta;
    }
}
