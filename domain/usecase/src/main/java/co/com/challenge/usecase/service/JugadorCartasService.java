package co.com.challenge.usecase.service;

import co.com.challenge.usecase.model.JugadorCartas;
import reactor.core.publisher.Mono;

public interface JugadorCartasService {
    Mono<JugadorCartas> obtenerCartasDeJugador();
}
