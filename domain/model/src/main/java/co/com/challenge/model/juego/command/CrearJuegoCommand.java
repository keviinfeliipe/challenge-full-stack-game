package co.com.challenge.model.juego.command;


import co.com.sofka.domain.generic.Command;

public class CrearJuegoCommand extends Command {

    private String juegoId;
    private String jugadorId;
    private String alias;

    public CrearJuegoCommand() {
    }

    public CrearJuegoCommand(String juegoId, String jugadorId, String alias) {
        this.juegoId = juegoId;
        this.jugadorId = jugadorId;
        this.alias = alias;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
