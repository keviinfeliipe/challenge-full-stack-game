package co.com.challenge.model.juego;

import co.com.challenge.model.juego.event.*;
import co.com.challenge.model.juego.value.*;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Juego extends AggregateEvent<JuegoId> {

    protected Set<Jugador> jugadores;
    protected Tablero tablero;
    protected Ronda ronda;
    protected Jugador ganador;
    protected Mazo mazo;
    protected Boolean jugando;
    protected Set<Carta> cartasTemporales;

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

    public Set<Carta> cartasTemporales() {
        return cartasTemporales;
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

    public void crearRonda(Set<JugadorId> jugadorIds){
        appendChange(new RondaCreada(jugadorIds)).apply();
    }

    public void repartirCartas(){
        appendChange(new CartasRepartidas()).apply();
    }

    public void jugarCarta(String jugadorId, String cartaId){
        appendChange(new CartaJugada(jugadorId, cartaId)).apply();
    }

    public void seleccionarCartaAlAzar(){
        appendChange(new CartaAlAzarSeleccionada()).apply();
    }

    public void agregarCartasJugador(JugadorId jugadorId, CartaFactory factory){
        appendChange(new CartasAgregadasAJugador(jugadorId, factory)).apply();
    }

    public void quitarCartaJugador(JugadorId jugadorId, Carta carta){
        appendChange(new CartaQuitada(jugadorId, carta)).apply();
    }

    public void restablecerCronometro(){
        appendChange(new CronometroRestablecido()).apply();
    }

    public void iniciarCronometro(){
        appendChange(new CronometroIniciado()).apply();
    }

    public void descontarTiempo(){
        appendChange(new TiempoDescontado()).apply();
    }

    public void terminoElTimepo(){
        appendChange(new TiempoTerminado()).apply();
    }

    public void determinarGanadorDeLaRonda(JugadorId jugadorId, CartaFactory factory){
        appendChange(new GanadorDeRondaDeterminado(jugadorId,factory)).apply();
    }

    public void determinarGanadorDeJuego(Jugador ganador){
        appendChange(new GanadorDeJuegoDeterminado(ganador)).apply();
    }

    public void habiltarTablero(){
        appendChange(new TableroHabilitado()).apply();
    }

    public void deshabilitarTablero(){
        appendChange(new TableroDeshabilitado()).apply();
    }

    public Optional<Jugador> buscarJugadorPorId(JugadorId id){
        return jugadores()
                .stream()
                .filter(suscriptor -> suscriptor.identity().equals(id))
                .findFirst();
    }

    public void cambiarEstadoDelTablero(Boolean aBoolean){
        this.tablero.habilitarTablero(aBoolean);
    }

    public void agregarCartaAlTablero(JugadorId jugadorId,Carta carta){
        appendChange(new CartaAgregadaAlTablero(jugadorId, carta)).apply();
    }

    public void mostrarCartasApostadas(){
        appendChange(new CartasApostadasMostradas()).apply();
    }

    public void agregarCartasMazoPrincipal(CartaFactory factory){
        factory.cartas().forEach(carta -> mazo.agregarCarta(carta));
    }

    public void mostrarJuego(JuegoId juegoId, List<Jugador> jugadores, Boolean jugando) {
        appendChange(new JuegoMostrado(juegoId, jugadores, jugando)).apply();
    }

    public void deshabilitarCartasDelTablero(){
        appendChange(new CartasDelTableroDeshabilitadas()).apply();
    }

    public void crearRondaDeDesempate(Set<JugadorId> jugadores){
        appendChange(new RondaDeDesempateCreada(jugadores)).apply();
    }

    public void retirarJugador(JugadorId jugadorId){
        appendChange(new JugadorRetirado(jugadorId)).apply();
    }
}
