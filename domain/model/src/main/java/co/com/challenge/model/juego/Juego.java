package co.com.challenge.model.juego;

import co.com.challenge.model.juego.event.CartaJugada;
import co.com.challenge.model.juego.event.JuegoCreado;
import co.com.challenge.model.juego.event.JugadorCreado;
import co.com.challenge.model.juego.value.*;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Juego extends AggregateEvent<JuegoId> {

    protected Set<Jugador> jugadores;
    protected Tablero tablero;
    protected Ronda ronda;
    protected Jugador ganador;

    public Juego(JuegoId juegoId, String jugadorId, String alias) {
        super(juegoId);
        subscribe(new JuegoChange(this));
        appendChange(new JuegoCreado(jugadorId, alias)).apply();
    }

    private Juego(JuegoId juegoId){
        super(juegoId);
        subscribe(new JuegoChange(this));
    }

    public static Juego from(JuegoId id, List<DomainEvent> events) {
        var canal = new Juego(id);
        events.forEach(canal::applyEvent);
        return canal;
    }

    public Set<Jugador> jugadores() {
        return jugadores;
    }

    public Tablero tablero() {
        return tablero;
    }

    public Ronda ronda() {
        return ronda;
    }

    public Jugador ganador() {
        return ganador;
    }

    public void crearJugador(String jugadorId, String alias){
        appendChange(new JugadorCreado(jugadorId,alias)).apply();
    }

    public void iniciarJuego(Set<Carta> mazo){

    }

    public void crearTablero(){
        appendChange(new TableroCreado()).apply();
    }

    public void crearRonda(){
        appendChange(new RondaCreada()).apply();
    }

    public void repartirCartas(JugadorId jugadorId,Set<Carta> cartas){
        appendChange(new CartasRepartidas(jugadorId,cartas)).apply();
    }

    public void jugarCarta(String jugadorId, String cartaId){
        appendChange(new CartaJugada(jugadorId, cartaId)).apply();
    }

    public void seleccionarCartaAlAzar(){
        appendChange(new CartaAlAzarseleccionar()).apply();
    }

    public void quitarCarta(JugadorId jugadorId){
        appendChange(new CartaQuitada(jugadorId)).apply();
    }

    public void determinarGanador(){
        appendChange(new GanadorDeterminado()).apply();
    }

    public Jugador buscarJugadorPorId(JugadorId id){
        return jugadores()
                .stream()
                .filter(suscriptor -> suscriptor.identity().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el jugador"));
    }
}
