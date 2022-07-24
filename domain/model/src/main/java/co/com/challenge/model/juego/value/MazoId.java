package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.Identity;

public class MazoId extends Identity {
    private MazoId(String uuid) {
        super(uuid);
    }

    public MazoId() {
    }

    public static MazoId of(String id) {
        return new MazoId(id);
    }
}
