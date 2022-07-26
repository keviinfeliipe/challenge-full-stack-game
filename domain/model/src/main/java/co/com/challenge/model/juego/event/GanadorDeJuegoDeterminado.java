package co.com.challenge.model.juego.event;

import co.com.challenge.model.juego.Jugador;
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
