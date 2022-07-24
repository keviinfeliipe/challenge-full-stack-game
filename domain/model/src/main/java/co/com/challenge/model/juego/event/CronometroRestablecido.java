package co.com.challenge.model.juego.event;

public class CronometroRestablecido extends co.com.sofka.domain.generic.DomainEvent {
    public CronometroRestablecido() {
        super("juego.TiempoRestablecido");
    }
}
