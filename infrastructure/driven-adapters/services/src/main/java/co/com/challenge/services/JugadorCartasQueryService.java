package co.com.challenge.services;

import co.com.challenge.usecase.model.JugadorCartas;
import co.com.challenge.usecase.service.JugadorCartasService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JugadorCartasQueryService implements JugadorCartasService {

    private final MongoTemplate mongoTemplate;

    public JugadorCartasQueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<JugadorCartas> obtenerCartasDeJugador() {
        return null;
    }
}
