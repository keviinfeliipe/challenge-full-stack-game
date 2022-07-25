package co.com.challenge.model.juego.event;


import co.com.challenge.model.juego.CartaFactory;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

public class GanadorDeRondaDeterminado extends DomainEvent {
    private final JugadorId jugadorId;
    private final CartaFactory factory;

    public GanadorDeRondaDeterminado(JugadorId jugadorId, CartaFactory factory) {
        super("juego.GanadorDeRondaDeterminado");
        this.jugadorId = jugadorId;
        this.factory = factory;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public CartaFactory getFactory() {
        return factory;
    }
}
