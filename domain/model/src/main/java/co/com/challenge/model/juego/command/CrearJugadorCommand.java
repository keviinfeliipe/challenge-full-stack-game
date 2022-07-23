package co.com.challenge.model.juego.command;

import co.com.sofka.domain.generic.Command;

public class CrearJugadorCommand extends Command {

    private String juegoId;
    private String jugadorId;
    private String alias;

    public CrearJugadorCommand() {
    }

    public CrearJugadorCommand(String juegoId, String jugadorId, String alias) {
        this.juegoId = juegoId;
        this.jugadorId = jugadorId;
        this.alias = alias;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public String getAlias() {
        return alias;
    }

}
