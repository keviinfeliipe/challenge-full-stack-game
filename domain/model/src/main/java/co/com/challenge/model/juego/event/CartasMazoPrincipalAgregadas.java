package co.com.challenge.model.juego.event;

import co.com.challenge.model.juego.CartaFactory;
import co.com.sofka.domain.generic.DomainEvent;

public class CartasMazoPrincipalAgregadas extends DomainEvent {
    private final CartaFactory factory;

    public CartasMazoPrincipalAgregadas(CartaFactory factory) {
        super("juego.CartasMazoPrincipalAgregadas");
        this.factory = factory;
    }

    public CartaFactory getFactory() {
        return factory;
    }
}
