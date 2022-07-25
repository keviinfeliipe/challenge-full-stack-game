package co.com.challenge.model.juego.event;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

public class CartaAgregadaAlTablero extends DomainEvent {
    private final JugadorId jugadorId;
    private final Carta carta;

    public CartaAgregadaAlTablero(JugadorId jugadorId, Carta carta) {
        super("juego.CartaAgregadaAlTablero");
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
