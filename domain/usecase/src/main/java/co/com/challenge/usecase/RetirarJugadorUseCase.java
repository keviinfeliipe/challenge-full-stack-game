package co.com.challenge.usecase;

import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.command.RetirarJugadorCommand;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.JugadorId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

import java.util.ArrayList;

public class RetirarJugadorUseCase extends UseCase<RequestCommand<RetirarJugadorCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<RetirarJugadorCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var juego = Juego.from(JuegoId.of(command.getJuegoId()), repository().getEventsBy(command.getJuegoId()));
        var jugador = juego.buscarJugadorPorId(JugadorId.of(command.getJugadorId())).orElseThrow(() -> {
            throw new IllegalArgumentException("Jugador no encontrado");
        });
        juego.retirarJugador(jugador.identity());
        juego.mostrarJuego(juego.identity(), new ArrayList<>(juego.jugadores()), juego.jugando());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
