package co.com.challenge.model.juego.event;


import co.com.sofka.domain.generic.DomainEvent;

public class CartaJugada extends DomainEvent {
    private final String jugadorId;
    private final String carta;

    public CartaJugada(String jugadorId, String cartaId) {
        super("juego.CartaJugada");
        this.jugadorId = jugadorId;
        this.carta = cartaId;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public String getCartaId() {
        return carta;
    }
}
