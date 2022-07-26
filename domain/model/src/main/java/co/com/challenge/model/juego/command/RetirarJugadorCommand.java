package co.com.challenge.model.juego.command;

import co.com.sofka.domain.generic.Command;

public class RetirarJugadorCommand extends Command {
    private String juegoId;
    private String jugadorId;

    public RetirarJugadorCommand() {
    }

    public RetirarJugadorCommand(String juegoId, String jugadorId) {
        this.juegoId = juegoId;
        this.jugadorId = jugadorId;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(String jugadorId) {
        this.jugadorId = jugadorId;
    }
}
