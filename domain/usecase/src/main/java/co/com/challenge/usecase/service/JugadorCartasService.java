package co.com.challenge.usecase.service;

import co.com.challenge.usecase.model.JuegadorActual;
import reactor.core.publisher.Flux;

public interface JugadorCartasService {
    Flux<JuegadorActual> obtenerCartasDeJugador(String juegoId, String jugadorId);
}
