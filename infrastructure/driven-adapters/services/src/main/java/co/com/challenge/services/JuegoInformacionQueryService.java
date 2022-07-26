package co.com.challenge.services;

import co.com.challenge.usecase.model.JuegoInformacion;
import co.com.challenge.usecase.model.Jugador;
import co.com.challenge.usecase.service.JuegoInformacionService;
import com.google.gson.Gson;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class JuegoInformacionQueryService implements JuegoInformacionService {

    private final ReactiveMongoTemplate mongoTemplate;

    public JuegoInformacionQueryService(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<JuegoInformacion> obtenerInformacionDelJuego(String juegoId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        var query = new Query(where("aggregateRootId").is(juegoId));


        var juego = new JuegoInformacion();
        var jugadores = new ArrayList<Jugador>();
        return mongoTemplate.find(query,String.class, "juego.JuegoMostrado")
                .map(s -> new Gson().fromJson(s, JuegoRecord.class))
                .map(juegoRecord -> {

                    try {
                        Date date = sdf.parse(juegoRecord.getWhen().get$date());
                        juego.setWhen(date);
                        juego.setJuegoId(juegoId);
                        juego.setEstado(juegoRecord.getJugando());
                        juego.setJugadores(new ArrayList<>());
                        juegoRecord.getJugadores().forEach(jugador1 -> {
                            var jugador = new Jugador();
                            jugador.setJugadorId(jugador1.getEntityId().uuid);
                            jugador.setPuntaje(jugador1.puntaje.value);
                            jugador.setAlias(jugador1.getAlias().value);

                            juego.getJugadores().add(jugador);
                        });
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return juego;
                }).sort(Comparator.comparing(JuegoInformacion::getWhen)).take(1).next();
    }

    class JuegoRecord {
        private JuegoId juegoId;
        private List<Jugador> jugadores;
        private When when;
        private Boolean jugando;

        public JuegoId getJuegoId() {
            return juegoId;
        }

        public List<Jugador> getJugadores() {
            return jugadores;
        }

        public When getWhen() {
            return when;
        }

        public Boolean getJugando() {
            return jugando;
        }

        class JuegoId{
            private String uuid;

            public String getUuid() {
                return uuid;
            }
        }

        class Jugador {

            private EntityId entityId;
            private Puntaje puntaje;
            private Alias alias;

            public EntityId getEntityId() {
                return entityId;
            }

            public Puntaje getPuntaje() {
                return puntaje;
            }

            public Alias getAlias() {
                return alias;
            }

            class EntityId {
                private String uuid;

                public String getUuid() {
                    return uuid;
                }
            }

            class Puntaje {
                private Integer value;

                public Integer getValue() {
                    return value;
                }
            }

            class Alias {
                private String value;

                public String getValue() {
                    return value;
                }
            }

        }

        class When{
            private String $date;

            public String get$date() {
                return $date;
            }

            public void set$date(String $date) {
                this.$date = $date;
            }
        }


    }
}
