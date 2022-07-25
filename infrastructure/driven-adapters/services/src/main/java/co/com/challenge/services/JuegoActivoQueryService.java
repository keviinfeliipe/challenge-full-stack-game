package co.com.challenge.services;

import co.com.challenge.usecase.model.JuegoActivo;
import co.com.challenge.usecase.service.JuegoActivoService;
import com.google.gson.Gson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Service
public class JuegoActivoQueryService implements JuegoActivoService {

    private final MongoTemplate mongoTemplate;

    public JuegoActivoQueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<JuegoActivo> obtenerJuegosActivos() {

        return Flux.fromIterable(mongoTemplate
                .findAll(String.class, "juego.JuegoCreado")
                .stream()
                .map(s -> new Gson().fromJson(s,JuegoIdRecord.class))
                .map(juegoIdRecord -> {
                    var juegoActivo = new JuegoActivo();
                    juegoActivo.setJuegoId(juegoIdRecord.getAggregateRootId());
                    juegoActivo.setJugadorId(juegoIdRecord.getJugadorId());
                    juegoActivo.setAlias(juegoIdRecord.getAlias());
                    return juegoActivo;
                }).collect(Collectors.toList()));
    }

    class JuegoIdRecord{
        private String jugadorId;
        private String alias;
        private String aggregateRootId;

        public String getJugadorId() {
            return jugadorId;
        }

        public void setJugadorId(String jugadorId) {
            this.jugadorId = jugadorId;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getAggregateRootId() {
            return aggregateRootId;
        }

        public void setAggregateRootId(String aggregateRootId) {
            this.aggregateRootId = aggregateRootId;
        }
    }

}
