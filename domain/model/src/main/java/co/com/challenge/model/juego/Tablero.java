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

    public Tablero(Tiempo tiempo) {
        super(new TableroId());
        this.cartaMap = new HashMap<>();
        this.tiempo = tiempo;
        this.habilitado = new EstaHabilitado(false);
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
