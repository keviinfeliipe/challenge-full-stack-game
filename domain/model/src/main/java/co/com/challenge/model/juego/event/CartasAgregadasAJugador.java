package co.com.challenge.model.juego.event;


import co.com.challenge.model.juego.CartaFactory;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

public class CartasAgregadasAJugador extends DomainEvent {
    private final JugadorId jugadorId;
    private final CartaFactory cartaFactory;

    public CartasAgregadasAJugador(JugadorId jugadorId, CartaFactory cartaFactory) {
        super("juego.CartasAgregadasAJugador");
        this.jugadorId = jugadorId;
        this.cartaFactory = cartaFactory;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public CartaFactory getCartaFactory() {
        return cartaFactory;
    }
}
