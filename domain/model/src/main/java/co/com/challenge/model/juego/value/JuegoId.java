package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.Identity;

public class JuegoId extends Identity {
    private JuegoId(String uuid) {
        super(uuid);
    }

    public JuegoId() {
    }

    public static JuegoId of(String id) {
        return new JuegoId(id);
    }
}
