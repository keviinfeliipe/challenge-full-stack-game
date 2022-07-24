package co.com.challenge.model.carta.gateway;

import co.com.challenge.model.carta.CartaMaestra;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartaRepository {
    Mono<CartaMaestra> save(CartaMaestra carta);
    Mono<CartaMaestra> findById(String id);
    Mono<Void> deleteById(String id);
    Flux<CartaMaestra> findAll();
}
