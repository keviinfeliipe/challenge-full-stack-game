package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.Identity;

public class CartaId extends Identity {
    private CartaId(String uuid) {
        super(uuid);
    }

    public CartaId() {
    }

    public static CartaId of(String id) {
        return new CartaId(id);
    }
}
