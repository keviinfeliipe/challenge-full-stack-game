package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.Identity;

public class JugadorId extends Identity {
    private JugadorId(String uuid) {
        super(uuid);
    }

    public JugadorId() {
    }
    public static JugadorId of(String id) {
        return new JugadorId(id);
    }
}
