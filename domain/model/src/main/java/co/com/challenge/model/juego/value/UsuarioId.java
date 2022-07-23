package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.Identity;

public class UsuarioId extends Identity {
    private UsuarioId(String uuid) {
        super(uuid);
    }

    public UsuarioId() {
    }

    public static UsuarioId of(String id) {
        return new UsuarioId(id);
    }
}
