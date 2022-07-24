package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class JuegoCreado extends DomainEvent {

    private final String jugadorId;
    private final String alias;

    public JuegoCreado(String jugadorId, String alias) {
        super("juego.JuegoCreado");
        this.jugadorId = jugadorId;
        this.alias = alias;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public String getAlias() {
        return alias;
    }
}
