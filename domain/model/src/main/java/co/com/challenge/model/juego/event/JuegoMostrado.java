package co.com.challenge.model.juego.event;

import co.com.challenge.model.juego.Jugador;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;

public class JuegoMostrado extends DomainEvent {
    private final JuegoId juegoId;
    private final List<Jugador> jugadorId;

    public JuegoMostrado(JuegoId juegoId, List<Jugador> jugadorId) {
        super("juego.JuegoMostrado");
        this.juegoId = juegoId;
        this.jugadorId = jugadorId;
    }

    public JuegoId getJuego() {
        return juegoId;
    }

    public List<Jugador> getJugadorId() {
        return jugadorId;
    }
}
