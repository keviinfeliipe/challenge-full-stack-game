package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.EstaHabilitado;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.challenge.model.juego.value.TableroId;
import co.com.challenge.model.juego.value.Tiempo;
import co.com.sofka.domain.generic.Entity;

import java.util.HashMap;
import java.util.Map;

public class Tablero extends Entity<TableroId> {

    private Map<JugadorId, Carta> cartaMap;
    private Tiempo tiempo;
    private EstaHabilitado habilitado;

    public Tablero() {
        super(new TableroId());
        this.cartaMap = new HashMap<>();
        this.tiempo = new Tiempo(0);
        this.habilitado = new EstaHabilitado(false);
    }

    public void habilitarTablero(Boolean aBoolean){
        this.habilitado = new EstaHabilitado(aBoolean);
    }

    public void restablecerTiempo(){
        this.tiempo = new Tiempo(1);
    }

    public void descontarTiempo(){
        this.tiempo = new Tiempo(this.tiempo.value()-1);
    }

    public Map<JugadorId, Carta> cartaMap() {
        return cartaMap;
    }

    public Tiempo tiempo() {
        return tiempo;
    }

    public EstaHabilitado habilitado() {
        return habilitado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tablero{");
        sb.append("cartaMap=").append(cartaMap);
        sb.append(", tiempo=").append(tiempo);
        sb.append(", habilitado=").append(habilitado);
        sb.append('}');
        return sb.toString();
    }
}
