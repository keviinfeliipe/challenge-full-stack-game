package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.Identity;

public class TableroId extends Identity {
    private TableroId(String uuid) {
        super(uuid);
    }

    public TableroId() {
    }

    public static TableroId of(String id) {
        return new TableroId(id);
    }
}
