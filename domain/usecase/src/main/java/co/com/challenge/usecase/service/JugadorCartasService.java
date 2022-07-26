package co.com.challenge.usecase.service;

import co.com.challenge.usecase.model.JugadorActual;
import reactor.core.publisher.Flux;

public interface JugadorCartasService {
    Flux<JugadorActual> obtenerCartasDeJugador(String juegoId, String jugadorId);
}
