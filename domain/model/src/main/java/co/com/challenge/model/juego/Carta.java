package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.CartaId;
import co.com.challenge.model.juego.value.EstaHabilitada;
import co.com.challenge.model.juego.value.EstaOculta;
import co.com.sofka.domain.generic.Entity;

public class Carta extends Entity<CartaId> {

    private String cartaId;
    private EstaOculta oculta;
    private EstaHabilitada habilitada;

    public Carta(CartaId entityId, String cartaId, EstaOculta oculta, EstaHabilitada habilitada) {
        super(entityId);
        this.cartaId = cartaId;
        this.oculta = oculta;
        this.habilitada = habilitada;
    }

    public String cartaId() {
        return cartaId;
    }

    public EstaOculta oculta() {
        return oculta;
    }

    public EstaHabilitada habilitada() {
        return habilitada;
    }

}
