package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.Alias;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.challenge.model.juego.value.Puntaje;
import co.com.challenge.model.juego.value.UsuarioId;
import co.com.sofka.domain.generic.Entity;

import java.util.Objects;

public class Jugador extends Entity<JugadorId> {

    private UsuarioId usuarioId;
    private Alias alias;
    private Mazo mazo;
    private Puntaje puntaje;

    public Jugador(JugadorId entityId, Alias alias) {
        super(entityId);
        this.alias = alias;
        this.puntaje = new Puntaje(0);
        this.mazo = new Mazo();
    }

    public void agregarCartaAJugador(Carta carta){
        Objects.requireNonNull(carta);
        this.mazo.agregarCarta(carta);
    }

    public void quitarCartaAJugador(Carta carta){
        Objects.requireNonNull(carta);
        this.mazo.quitarCarta(carta);
    }

    public void agragarPuntajeAJugador(){
        this.puntaje = new Puntaje(puntaje().value()+1);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Jugador{");
        sb.append("usuarioId=").append(usuarioId);
        sb.append(", alias=").append(alias);
        sb.append(", mazo=").append(mazo);
        sb.append(", puntaje=").append(puntaje);
        sb.append('}');
        return sb.toString();
    }
}
