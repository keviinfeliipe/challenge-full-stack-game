package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.TiempoTerminado;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

import java.util.Random;
import java.util.stream.Collectors;

public class ValidarCartasApostadasUseCase extends UseCase<TriggeredEvent<TiempoTerminado>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<TiempoTerminado> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);
        var tablero = juego.tablero().cartaMap();
        juego.ronda().jugadores().stream().forEach(jugadorId -> {
            if (!tablero.containsKey(jugadorId)){
                var carta = apostarCartaAleatoria(juego, jugadorId);
                juego.agregarCartaAlTablero(jugadorId,carta);
                juego.quitarCartaJugador(jugadorId, carta);
            }
        });
        juego.seleccionarCartaAlAzar();
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }

    private Carta apostarCartaAleatoria(Juego juego, JugadorId jugadorId){
        var jugagor = juego.buscarJugadorPorId(jugadorId);
        var cantidadDeCArtas = jugagor.mazo().cartas().size();
        var random = new Random();
        var aleatorio = random.nextInt(cantidadDeCArtas);
        System.out.println("Numero aletorio= "+ aleatorio + jugagor.toString());
        return  jugagor.mazo()
                .cartas()
                .stream()
                .collect(Collectors.toList())
                .get(aleatorio);
    }
}
