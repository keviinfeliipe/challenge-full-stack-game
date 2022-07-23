package co.com.challenge.usecase;

import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.command.JugarCartaCommand;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class JugarCartaUseCase extends UseCase<RequestCommand<JugarCartaCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<JugarCartaCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var juego = Juego.from(JuegoId.of(command.getJuegoId()), repository().getEventsBy(command.getJuegoId()));
        juego.jugarCarta(command.getJugadorId(), command.getCartaId());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
