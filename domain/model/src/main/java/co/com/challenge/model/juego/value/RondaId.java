package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.Identity;

public class RondaId extends Identity {
    private RondaId(String uuid) {
        super(uuid);
    }

    public RondaId() {
    }

    public static RondaId of(String id) {
        return new RondaId(id);
    }
}
