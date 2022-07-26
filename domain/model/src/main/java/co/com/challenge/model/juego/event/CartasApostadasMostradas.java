package co.com.challenge.model.juego.event;

import co.com.sofka.domain.generic.DomainEvent;

public class CartasApostadasMostradas extends DomainEvent {

    public CartasApostadasMostradas() {
        super("juego.CartasApostadasMostradas");
    }
}
