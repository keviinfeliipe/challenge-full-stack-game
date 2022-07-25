package co.com.challenge.usecase.service;

import co.com.challenge.usecase.model.JuegosActivos;
import reactor.core.publisher.Flux;

public interface JuegoActivoService {
    Flux<JuegosActivos> obtenerJuegosActivos();
}
