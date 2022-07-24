package co.com.challenge.model.juego.event;


import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

public class CartaAgregada extends DomainEvent {
    private final JugadorId jugadorId;
    private final Carta cartaId;

    public CartaAgregada(JugadorId jugadorId, Carta carta) {
        super("juego.CartaAgregada");
        this.jugadorId = jugadorId;
        this.cartaId = carta;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Carta getCarta() {
        return cartaId;
    }
}
