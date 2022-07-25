package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.CartaId;
import co.com.challenge.model.juego.value.EstaHabilitada;
import co.com.challenge.model.juego.value.EstaOculta;
import co.com.challenge.model.juego.value.Xp;
import co.com.sofka.domain.generic.Entity;

public class Carta extends Entity<CartaId> {

    private EstaOculta oculta;
    private EstaHabilitada habilitada;
    private Integer xp;

    public Carta(CartaId cartaId, Integer xp) {
        super(cartaId);
        this.xp = xp;
        this.oculta = new EstaOculta(true);
        this.habilitada = new EstaHabilitada(true);
    }

    public Integer xp() {
        return xp;
    }

    public EstaOculta oculta() {
        return oculta;
    }

    public EstaHabilitada habilitada() {
        return habilitada;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Carta{");
        sb.append("oculta=").append(oculta);
        sb.append(", habilitada=").append(habilitada);
        sb.append(", xp=").append(xp);
        sb.append('}');
        return sb.toString();
    }
}
