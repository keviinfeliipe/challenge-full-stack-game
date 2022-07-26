package co.com.challenge.usecase.service;

import co.com.challenge.usecase.model.JuegoInformacion;
import reactor.core.publisher.Mono;

public interface JuegoInformacionService {
    Mono<JuegoInformacion> obtenerInformacionDelJuego(String juegoId);
}
