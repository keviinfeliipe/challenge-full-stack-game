package co.com.challenge.usecase.listeners;

import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.event.TiempoTerminado;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

import java.util.ArrayList;
import java.util.Random;

public class ValidarCartasApostadasUseCase extends UseCase<TriggeredEvent<TiempoTerminado>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<TiempoTerminado> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var events = repository().getEventsBy("juego", juegoId.value());
        var juego = Juego.from(juegoId, events);
        var tablero = juego.tablero().cartaMap();
        System.out.println(juego.ronda().jugadores().toString());
        juego.ronda().jugadores().forEach(jugadorId -> {
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
        var jugagor = juego.buscarJugadorPorId(jugadorId).orElseThrow(() -> {
            throw new IllegalArgumentException("Jugador no encontrado");
        });
        var cantidadDeCArtas = jugagor.mazo().cartas().size();
        var random = new Random();
        var aleatorio = random.nextInt(cantidadDeCArtas);
        return new ArrayList<>(jugagor.mazo()
                .cartas())
                .get(aleatorio);
    }
}
