package co.com.challenge.model.carta.gateway;

import co.com.challenge.model.carta.Carta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartaRepository {
    Mono<Carta> save(Carta carta);
    Mono<Carta> findById(String id);
    Mono<Void> deleteById(String id);
    Flux<Carta> findAll();
}
