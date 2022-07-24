package co.com.challenge.usecase;

import co.com.challenge.model.carta.CartaMaestra;
import co.com.challenge.model.carta.gateway.CartaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CartaUseCase {

    private final CartaRepository repository;

    public CartaUseCase(CartaRepository repository) {
        this.repository = repository;
    }

    public Mono<CartaMaestra> save(CartaMaestra carta) {
        return repository.save(carta);
    }

    public Flux<CartaMaestra> findAll() {
        return repository.findAll();
    }

    public Mono<CartaMaestra> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
