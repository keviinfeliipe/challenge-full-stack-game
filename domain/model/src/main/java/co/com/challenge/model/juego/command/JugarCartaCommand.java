package co.com.challenge.model.juego.command;

import co.com.sofka.domain.generic.Command;

public class JugarCartaCommand extends Command {

    private String juegoId;
    private String jugadorId;
    private String cartaId;

    public JugarCartaCommand() {
    }

    public JugarCartaCommand(String juegoId, String jugadorId, String cartaId) {
        this.juegoId = juegoId;
        this.jugadorId = jugadorId;
        this.cartaId = cartaId;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public String getCartaId() {
        return cartaId;
    }

}
