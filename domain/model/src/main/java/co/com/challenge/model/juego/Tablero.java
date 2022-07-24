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
        this.tiempo = new Tiempo(60);
        this.habilitado = new EstaHabilitado(true);
    }

    public void restablecerTiempo(){
        this.tiempo = new Tiempo(60);
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
}
