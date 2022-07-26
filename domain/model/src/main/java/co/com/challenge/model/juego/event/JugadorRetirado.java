package co.com.challenge.model.juego.event;

import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.domain.generic.DomainEvent;

public class JugadorRetirado extends DomainEvent {

    private final JugadorId jugadorId;

    public JugadorRetirado(JugadorId jugadorId) {
        super("juego.JugadorRetirado");
        this.jugadorId = jugadorId;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

}
