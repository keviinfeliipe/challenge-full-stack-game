package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class JugadorCreado extends DomainEvent {
    private final String jugadorId;
    private final String alias;

    public JugadorCreado(String jugadorId, String alias) {
        super("juego.JugadorCreado");
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
