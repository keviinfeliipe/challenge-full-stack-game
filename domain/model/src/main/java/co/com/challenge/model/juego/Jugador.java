package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.Alias;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.challenge.model.juego.value.Puntaje;
import co.com.challenge.model.juego.value.UsuarioId;
import co.com.sofka.domain.generic.Entity;

import java.util.Set;

public class Jugador extends Entity<JugadorId> {

    private UsuarioId usuarioId;
    private Alias alias;
    private Mazo mazo;
    private Puntaje puntaje;

    public Jugador(JugadorId entityId, Alias alias) {
        super(entityId);
        this.alias = alias;
        this.puntaje = new Puntaje(0);
    }

    public void agregarMazo(Set<Carta> cartas){
        this.mazo = new Mazo(cartas);
    }

    public void quitarCarta(Carta carta){
        this.mazo.value().cartas().remove(carta);
        this.mazo.value().cantidad();
    }

    public Alias alias() {
        return alias;
    }

    public Mazo mazo() {
        return mazo;
    }

    public Puntaje puntaje() {
        return puntaje;
    }
}
