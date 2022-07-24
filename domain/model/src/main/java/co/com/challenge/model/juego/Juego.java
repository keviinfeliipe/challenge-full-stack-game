package co.com.challenge.model.juego;

import co.com.challenge.model.juego.event.*;
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
    protected Mazo mazo;
    protected Boolean jugando;

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
        var Juego = new Juego(id);
        events.forEach(Juego::applyEvent);
        return Juego;
    }

    public Set<Jugador> jugadores() {
        return jugadores;
    }

    public Tablero tablero() {
        return tablero;
    }

    public Boolean jugando() {
        return jugando;
    }

    public Ronda ronda() {
        return ronda;
    }

    public Jugador ganador() {
        return ganador;
    }

    public Mazo mazo() {
        return mazo;
    }

    public void crearJugador(String jugadorId, String alias){
        appendChange(new JugadorCreado(jugadorId,alias)).apply();
    }

    public void iniciarJuego(){
        appendChange(new JuegoIniciado()).apply();
    }

    public void crearTablero(){
        appendChange(new TableroCreado()).apply();
    }

    public void crearRonda(){
        appendChange(new RondaCreada()).apply();
    }

    public void repartirCartas(){
        appendChange(new CartasRepartidas()).apply();
    }

    public void jugarCarta(String jugadorId, String cartaId){
        appendChange(new CartaJugada(jugadorId, cartaId)).apply();
    }

    public void seleccionarCartaAlAzar(){
        appendChange(new CartaAlAzarseleccionar()).apply();
    }

    public void agregarCartaJugador(JugadorId jugadorId, Carta carta){
        appendChange(new CartaAgregada(jugadorId, carta)).apply();
    }

    public void quitarCartaJugador(JugadorId jugadorId, Carta carta){
        appendChange(new CartaQuitada(jugadorId, carta)).apply();
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

    public void agregarCartasMazoPrincipal(CartaFactory factory){
        appendChange(new CartasMazoPrincipalAgregadas(factory)).apply();
    }
}
