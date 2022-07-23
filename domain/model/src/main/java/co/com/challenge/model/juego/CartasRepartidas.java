package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Set;

public class CartasRepartidas extends DomainEvent {
    private final JugadorId jugadorId;
    private final Set<Carta> cartas;

    public CartasRepartidas(JugadorId jugadorId, Set<Carta> cartas) {
        super("juego.CartasRepartidas");
        this.jugadorId = jugadorId;
        this.cartas = cartas;
    }

    public Set<Carta> getCartas() {
        return cartas;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }
}
