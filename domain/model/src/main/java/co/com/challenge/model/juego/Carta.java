package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.CartaId;
import co.com.challenge.model.juego.value.EstaHabilitada;
import co.com.challenge.model.juego.value.EstaOculta;
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

    public void mostrarCarta(){
        this.oculta = new EstaOculta(false);
    }

    public void ocultarCarta(){
        this.oculta = new EstaOculta(true);
    }

    public void habilitarCarta(){
        this.habilitada = new EstaHabilitada(true);
    }

    public void deshabilitarCarta(){
        this.habilitada = new EstaHabilitada(false);
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

}
