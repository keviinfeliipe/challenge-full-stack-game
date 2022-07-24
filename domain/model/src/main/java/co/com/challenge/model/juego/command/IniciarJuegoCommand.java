package co.com.challenge.model.juego.command;

import co.com.sofka.domain.generic.Command;

public class IniciarJuegoCommand extends Command {

    private final String juegoId;

    public IniciarJuegoCommand(String juegoId) {
        this.juegoId = juegoId;
    }

    public String getJuegoId() {
        return juegoId;
    }


}
