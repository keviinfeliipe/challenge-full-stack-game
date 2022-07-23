package co.com.challenge.usecase;

import co.com.challenge.model.carta.Carta;
import co.com.challenge.model.carta.gateway.CartaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CartaUseCase {

    private final CartaRepository repository;

    public CartaUseCase(CartaRepository repository) {
        this.repository = repository;
    }

    public Mono<Carta> save(Carta pet) {
        return repository.save(pet);
    }

    public Flux<Carta> findAll() {
        return repository.findAll();
    }

    public Mono<Carta> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
