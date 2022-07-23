package co.com.challenge.mongo.carta;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartaMongoRepository extends ReactiveCrudRepository<CartaDocument, String> {
}
