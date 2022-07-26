package co.com.challenge.model.juego;


import co.com.challenge.model.juego.event.*;
import co.com.challenge.model.juego.value.Alias;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.challenge.model.juego.value.RondaNumero;
import co.com.sofka.domain.generic.EventChange;


import java.util.HashSet;
import java.util.stream.Collectors;

public class JuegoChange extends EventChange {
    public JuegoChange(Juego juego) {

        apply((JuegoCreado event)->{
            juego.jugadores = new HashSet<>();
            juego.jugadores.add(new Jugador(JugadorId.of(event.getJugadorId()), new Alias(event.getAlias())));
            juego.mazo = new Mazo();
            juego.jugando=false;
        });

        apply((JugadorCreado event)->{
            if(juego.jugadores.size()>5){
                throw new IllegalArgumentException("Solo se puede maximo 6 jugadores");
            }
            var id = JugadorId.of(event.getJugadorId());
            var alias = new Alias(event.getAlias());
            juego.jugadores.add(new Jugador(id,alias));
        });

        apply((JuegoIniciado event)->{
            juego.jugando=true;
        });

        apply((TableroCreado event)-> juego.tablero= new Tablero());

        apply((RondaCreada event)->{
            var jugadores =juego.jugadores
                    .stream()
                    .filter(jugador -> jugador.mazo().cantidad()>0)
                    .map(Jugador::identity)
                    .collect(Collectors.toSet());
            juego.ronda = new Ronda(jugadores,new RondaNumero(jugadores.size()));
        });

        apply((CronometroRestablecido event)-> juego.tablero().restablecerTiempo());

        apply((TableroHabilitado event)-> juego.cambiarEstadoDelTablero(true));


        apply((TiempoDescontado event)-> juego.tablero.descontarTiempo());

        apply((TableroDeshabilitado event)-> juego.cambiarEstadoDelTablero(false));

        apply((CartaAlAzarSeleccionada event)->{

        });

        apply((GanadorDeRondaDeterminado event)->{
            var jugador = juego.buscarJugadorPorId(event.getJugadorId());
            event.getFactory().cartas().forEach(jugador::agregarCartaAJugador);
            jugador.agragarPuntajeAJugador();
            juego.tablero=new Tablero();
        });

        apply((GanadorDeJuegoDeterminado event)-> {
            juego.ganador=event.getGanador();
            juego.jugando=false;
        });

        apply((CartaJugada event)->{
            var jugador = juego.buscarJugadorPorId(JugadorId.of(event.getJugadorId()));
            var carta = jugador.mazo().cartas().stream()
                    .filter(carta1 -> carta1.identity().value().equals(event.getCartaId()))
                    .findFirst()
                    .orElseThrow();
            if(Boolean.TRUE.equals(juego.tablero.habilitado().value())){
                jugador.quitarCartaAJugador(carta);
                juego.tablero.cartaMap().put(jugador.identity(),carta);
            }
        });

        apply((CartasAgregadasAJugador event)->{
            var jugador = juego.buscarJugadorPorId(event.getJugadorId());
            event.getCartaFactory().cartas().forEach(jugador::agregarCartaAJugador);
        });

        apply((CartaQuitada event)->{
            var jugador = juego.buscarJugadorPorId(event.getJugadorId());
            jugador.quitarCartaAJugador(event.getCarta());
        });

        apply((CartaAgregadaAlTablero event)->{
            var jugador = juego.buscarJugadorPorId(event.getJugadorId());
            juego.tablero.cartaMap().put(jugador.identity(),event.getCarta());
        });

        apply((CartasApostadasMostradas event)->{
            juego.tablero.cartaMap().forEach((jugadorId, carta) -> carta.mostrarCarta());
        });



    }


}
