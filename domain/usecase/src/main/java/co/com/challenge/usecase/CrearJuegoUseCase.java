package co.com.challenge.usecase;

import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.Jugador;
import co.com.challenge.model.juego.command.CrearJuegoCommand;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

import java.util.ArrayList;

public class CrearJuegoUseCase extends UseCase<RequestCommand<CrearJuegoCommand>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CrearJuegoCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var juego = new Juego(JuegoId.of(command.getJuegoId()), command.getJugadorId(), command.getAlias());
        juego.mostrarJuego(juego.identity(), new ArrayList<Jugador>(juego.jugadores()), juego.jugando());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
