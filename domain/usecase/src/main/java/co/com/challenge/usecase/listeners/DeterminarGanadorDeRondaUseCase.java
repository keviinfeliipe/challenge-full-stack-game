package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.CartaFactory;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.Jugador;
import co.com.challenge.model.juego.event.CartasApostadasMostradas;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

import java.util.*;
import java.util.stream.Collectors;

public class DeterminarGanadorDeRondaUseCase extends UseCase<TriggeredEvent<CartasApostadasMostradas>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<CartasApostadasMostradas> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);
        var cartasConMayorValor = cartasConMayorValor(juego.tablero().cartaMap());
        if(cartasConMayorValor.size()>1){
            desempatarJuego(juego, cartasConMayorValor);
        }else{
            ganadorDeLaRonda(juego, cartasConMayorValor);
        }
        juego.mostrarJuego(juego.identity(), new ArrayList<>(juego.jugadores()), juego.jugando());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }

    private void ganadorDeLaRonda(Juego juego, Map<JugadorId, Carta> cartasConMayorValor) {
        var idJugadorGanador = cartasConMayorValor.keySet().stream().findFirst().orElseThrow();
        var cartaFactory = new CartaFactory();
        juego.tablero().cartaMap().values().forEach(cartaFactory::add);
        juego.determinarGanadorDeLaRonda(idJugadorGanador,cartaFactory);
        var jugadoresActivos = juegadoresConCartasEnJuego(juego);
        validarNuevaRonda(juego, jugadoresActivos);
    }

    private void validarNuevaRonda(Juego juego, Set<Jugador> jugadoresActivos) {
        if(jugadoresActivos.size()>1){
            juego.crearRonda(juegadoresConCartas(juego));
        }else{
            var ganador = jugadoresActivos.stream().findFirst().orElseThrow();
            juego.determinarGanadorDeJuego(ganador);
        }
    }

    private void desempatarJuego(Juego juego, Map<JugadorId, Carta> cartasConMayorValor) {
        System.out.println("+++++++++++++++++++++++++++ Empate +++++++++++++++++++++++++++");
        juego.deshabilitarCartasDelTablero();
        var jugadoresEmpatados = cartasConMayorValor.keySet();
        juego.crearRondaDeDesempate(jugadoresEmpatados);
        juego.crearRonda(jugadoresEmpatados);
    }

    private Set<Jugador> juegadoresConCartasEnJuego(Juego juego){
        return juego.jugadores().stream().filter(jugador -> jugador.mazo().cantidad()>0).collect(Collectors.toSet());
    }

    private Map<JugadorId, Carta> cartasConMayorValor(Map<JugadorId, Carta> map){
        Map<JugadorId, Carta> mapResponse = new HashMap<>();
        HashMap<JugadorId, Carta> nuevoMap = new HashMap<>(map);
        var mayor = map.values()
                .stream()
                .filter(carta -> carta.habilitada().value())
                .max(Comparator.comparing(Carta::xp))
                .orElseThrow();
        nuevoMap.forEach((jugadorId, carta) -> {
            if (Objects.equals(carta.xp(), mayor.xp())){
                mapResponse.put(jugadorId, carta);
            }
        });
        return mapResponse;
    }

    private Set<JugadorId> juegadoresConCartas(Juego juego){
        return juego.jugadores()
                .stream()
                .filter(jugador -> jugador.mazo().cantidad()>0)
                .map(Jugador::identity)
                .collect(Collectors.toSet());
    }
}

