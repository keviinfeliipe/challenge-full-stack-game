package co.com.challenge.model.juego;

import co.com.sofka.domain.generic.DomainEvent;

public class GanadorDeJuegoDeterminado extends DomainEvent {
    private final Jugador ganador;

    public GanadorDeJuegoDeterminado(Jugador ganador) {
        super("juego.GanadorDeJuegoDeterminado");
        this.ganador = ganador;
    }

    public Jugador getGanador() {
        return ganador;
    }
}
