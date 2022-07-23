package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.JugadorId;
import co.com.challenge.model.juego.value.RondaId;
import co.com.challenge.model.juego.value.RondaNumero;
import co.com.sofka.domain.generic.Entity;

import java.util.Set;

public class Ronda extends Entity<RondaId> {

    private Set<JugadorId> jugadores;
    private RondaNumero numero;

    public Ronda(Set<JugadorId> jugadores, RondaNumero numero) {
        super(new RondaId());
        this.jugadores = jugadores;
        this.numero = numero;
    }

    public Set<JugadorId> jugadores() {
        return jugadores;
    }

    public RondaNumero numero() {
        return numero;
    }
}
