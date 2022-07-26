package co.com.challenge.usecase;

import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.Jugador;
import co.com.challenge.model.juego.command.CrearJugadorCommand;
import co.com.challenge.model.juego.value.JuegoId;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

import java.util.ArrayList;


public class CrearJugadorUseCase extends UseCase<RequestCommand<CrearJugadorCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearJugadorCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var juego = Juego.from(JuegoId.of(command.getJuegoId()), repository().getEventsBy(command.getJuegoId()));
        juego.crearJugador(command.getJugadorId(), command.getAlias());
        juego.mostrarJuego(juego.identity(), new ArrayList<Jugador>(juego.jugadores()), juego.jugando());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}


