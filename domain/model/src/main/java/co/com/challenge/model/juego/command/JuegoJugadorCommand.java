package co.com.challenge.model.juego.command;

import co.com.sofka.domain.generic.Command;

public class JuegoJugadorCommand extends Command {
    private String juegoId;
    private String jugadorId;


    public JuegoJugadorCommand() {
    }

    public JuegoJugadorCommand(String juegoId, String jugadorId) {
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
