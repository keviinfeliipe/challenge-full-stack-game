package co.com.challenge.usecase.service;

import co.com.challenge.usecase.model.JuegoActivo;
import reactor.core.publisher.Flux;

public interface JuegoActivoService {
    Flux<JuegoActivo> obtenerJuegosActivos();
}
