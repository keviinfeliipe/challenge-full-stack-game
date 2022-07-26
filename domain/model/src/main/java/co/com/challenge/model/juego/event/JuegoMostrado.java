package co.com.challenge.model.juego.event;

import co.com.challenge.model.juego.Jugador;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;

public class JuegoMostrado extends DomainEvent {
    private final JuegoId juegoId;
    private final List<Jugador> jugadores;
    private final Boolean jugando;

    public JuegoMostrado(JuegoId juegoId, List<Jugador> jugadorId, Boolean jugando) {
        super("juego.JuegoMostrado");
        this.juegoId = juegoId;
        this.jugadores = jugadorId;
        this.jugando = jugando;
    }

    public JuegoId getJuego() {
        return juegoId;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Boolean getJugando() {
        return jugando;
    }
}
