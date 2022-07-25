package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.CartaFactory;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.Jugador;
import co.com.challenge.model.juego.event.CartaAlAzarSeleccionada;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

import java.util.*;
import java.util.stream.Collectors;

public class DeterminarGanadorDeRondaUseCase extends UseCase<TriggeredEvent<CartaAlAzarSeleccionada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<CartaAlAzarSeleccionada> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);
        var cartasConMayorValor = cartasConMayorValor(juego.tablero().cartaMap());
        if(cartasConMayorValor.size()>1){
            System.out.println("Empate +++++++++++++++++++++++++++");
        }else{
            var idJugadorGanador = cartasConMayorValor.keySet().stream().findFirst().orElseThrow();
            var cartaFactory = new CartaFactory();
            juego.tablero().cartaMap().values().stream().forEach(cartaFactory::add);
            juego.determinarGanador(idJugadorGanador,cartaFactory);
            var jugadoresActivos = juegadoresConCartasEnJuego(juego);
            if(jugadoresActivos.size()>1){
                juego.crearRonda();
            }else{
                var ganador = jugadoresActivos.stream().findFirst().orElseThrow();
                juego.determinarGanadorDeJuego(ganador);
            }

        }
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }

    private Set<Jugador> juegadoresConCartasEnJuego(Juego juego){
        return juego.jugadores().stream().filter(jugador -> jugador.mazo().cantidad()>0).collect(Collectors.toSet());
    }

    private Map<JugadorId, Carta> cartasConMayorValor(Map<JugadorId, Carta> map){
        HashMap<JugadorId, Carta> nuevoMap = new HashMap<>();
        Map<JugadorId, Carta> mapResponse = new HashMap<>();
        map.entrySet().forEach(jugadorIdCartaEntry -> nuevoMap.put(jugadorIdCartaEntry.getKey(),jugadorIdCartaEntry.getValue()));
        var mayor = map.values().stream().max(Comparator.comparing(Carta::xp)).orElseThrow();
        nuevoMap.forEach((jugadorId, carta) -> {
            if (carta.xp()==mayor.xp()){
                mapResponse.put(jugadorId, carta);
            }
        });
        return mapResponse;
    }
}

