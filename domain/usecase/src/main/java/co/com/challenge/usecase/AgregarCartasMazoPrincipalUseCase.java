package co.com.challenge.usecase;

import co.com.challenge.model.carta.gateway.CartaRepository;
import co.com.challenge.model.juego.Carta;
import co.com.challenge.model.juego.CartaFactory;
import co.com.challenge.model.juego.Juego;
import co.com.challenge.model.juego.command.IniciarJuegoCommand;
import co.com.challenge.model.juego.value.CartaId;
import co.com.challenge.model.juego.value.JuegoId;
import co.com.challenge.model.juego.value.Xp;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

import java.util.Objects;

public class AgregarCartasMazoPrincipalUseCase extends UseCase<RequestCommand<IniciarJuegoCommand>, ResponseEvents> {

    private final CartaRepository cartaRepository;

    public AgregarCartasMazoPrincipalUseCase(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }

    @Override
    public void executeUseCase(RequestCommand<IniciarJuegoCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var juego = Juego.from(JuegoId.of(command.getJuegoId()), repository().getEventsBy(command.getJuegoId()));
        var cartaFactory = CartaFactory.getInstance();
        Objects.requireNonNull(cartaRepository.findAll()
                        .collectList()
                        .block())
                .forEach(cartaMaestra -> cartaFactory.add(new Carta(CartaId.of(cartaMaestra.getId()) , new Xp(cartaMaestra.getPoder()))));
        juego.agregarCartasMazoPrincipal(cartaFactory);
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));

    }

}
