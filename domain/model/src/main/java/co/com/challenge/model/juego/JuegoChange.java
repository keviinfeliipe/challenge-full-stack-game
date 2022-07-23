package co.com.challenge.model.juego;

import co.com.challenge.model.juego.event.CartaJugada;
import co.com.challenge.model.juego.event.JugadorCreado;
import co.com.challenge.model.juego.event.JuegoCreado;
import co.com.challenge.model.juego.value.Alias;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.challenge.model.juego.value.RondaNumero;
import co.com.challenge.model.juego.value.Tiempo;
import co.com.sofka.domain.generic.EventChange;

import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class JuegoChange extends EventChange {
    public JuegoChange(Juego juego) {
        apply((JuegoCreado event)->{
            juego.ronda = null;
            juego.ganador = null;
            juego.jugadores = new HashSet<>();
            juego.jugadores.add(new Jugador(JugadorId.of(event.getJugadorId()), new Alias(event.getAlias())));
        });

        apply((JugadorCreado event)->{
            if(juego.jugadores.size()>5){
                throw new IllegalArgumentException("Solo se puede maximo 6 jugadores");
            }
            var id = JugadorId.of(event.getJugadorId());
            var alias = new Alias(event.getAlias());
            juego.jugadores.add(new Jugador(id,alias));
        });

        apply((TableroCreado event)->{
            juego.tablero= new Tablero(new Tiempo(1000));
        });

        apply((RondaCreada event)->{
            var jugadores =juego.jugadores
                    .stream()
                    .filter(jugador -> jugador.mazo().value().cantidad()>0)
                    .map(Jugador::identity)
                    .collect(Collectors.toSet());
            juego.ronda = new Ronda(jugadores,new RondaNumero(jugadores.size()));
        });

        apply((CartaJugada event)->{
            var jugador = juego.buscarJugadorPorId(JugadorId.of(event.getJugadorId()));
            var carta = jugador.mazo().value().cartas().stream()
                    .filter(carta1 -> carta1.cartaId().equals(event.getCartaId()))
                    .findFirst()
                    .orElseThrow();
            jugador.quitarCarta(carta);
            juego.tablero.cartaMap().put(jugador.identity(),carta);
        });

        apply((CartasRepartidas event)->{
            var jugador = juego.buscarJugadorPorId(event.getJugadorId());
            jugador.agregarMazo(event.getCartas());
        });

        apply((CartaAlAzarseleccionar event)->{
            var tablero = juego.tablero.cartaMap();
            juego.ronda.jugadores().stream().forEach(jugadorId -> {
                if(!tablero.containsKey(jugadorId)){

                }
            });
        });

        apply((CartaQuitada event)->{
            var jugador = juego.buscarJugadorPorId(event.getJugadorId());
            var random = new Random();
            var cantidadCartas = jugador.mazo().value().cartas().size();
            jugador.mazo().value().cartas().remove(random.nextInt(cantidadCartas));
        });

        apply((GanadorDeterminado event)->{

        });
    }


}
