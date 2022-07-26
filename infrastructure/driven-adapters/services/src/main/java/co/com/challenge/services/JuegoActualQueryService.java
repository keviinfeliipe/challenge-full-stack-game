package co.com.challenge.services;

import co.com.challenge.usecase.model.Carta;
import co.com.challenge.usecase.model.JuegadorActual;
import co.com.challenge.usecase.service.JugadorCartasService;
import com.google.gson.Gson;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class JuegoActualQueryService implements JugadorCartasService {

    private final ReactiveMongoTemplate mongoTemplate;

    public JuegoActualQueryService(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<JuegadorActual> obtenerCartasDeJugador(String juegoId, String jugadorId) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        var query = new Query(where("aggregateRootId").is(juegoId));

        var jugadores = new ArrayList<JuegadorActual>();
        return mongoTemplate.find(query,String.class, "juego.JuegoMostrado")
                .map(s -> new Gson().fromJson(s, JuegoJugadorRecord.class))
                .map(juegoJugadorRecord -> {

                    try {
                        Date date = sdf.parse(juegoJugadorRecord.getWhen().get$date());

                        juegoJugadorRecord.getJugadorId().forEach(jugador1 -> {
                            var jugador = new JuegadorActual();
                            jugador.setWhen(date);
                            jugador.setJugadorId(jugador1.entityId.uuid);
                            jugador.setPuntaje(jugador1.puntaje.value);
                            jugador.setCartas(new ArrayList<>());
                            jugador1.mazo.getCartas().forEach(carta ->
                                    jugador.getCartas().add(new Carta(carta.getEntityId().uuid, carta.habilitada.value, carta.oculta.value,carta.xp))
                            );
                            jugadores.add(jugador);
                        });
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return jugadores;
                }).flatMap(Flux::fromIterable)
                .filter(juegadorActual -> juegadorActual.getJugadorId().equals(jugadorId))
                .sort(Comparator.comparing(JuegadorActual::getWhen).reversed())
                .take(1);
    }

    class JuegoJugadorRecord{
        private JuegoId juegoId;
        private List<Jugador> jugadorId;
        private When when;

        public JuegoId getJuegId() {
            return juegoId;
        }

        public List<Jugador> getJugadorId() {
            return jugadorId;
        }

        public When getWhen() {
            return when;
        }

        class JuegoId{
            private String uuid;

            public String getUuid() {
                return uuid;
            }
        }

        class Jugador {

            private EntityId entityId;
            private Mazo mazo;
            private Puntaje puntaje;

            public EntityId getUsuarioId() {
                return entityId;
            }

            public Mazo getMazo() {
                return mazo;
            }

            public Puntaje getPuntaje() {
                return puntaje;
            }

            class EntityId {
                private String uuid;

                public String getUuid() {
                    return uuid;
                }
            }

            class Mazo {
                private List<Carta> cartas;

                public List<Carta> getCartas() {
                    return cartas;
                }

                class Carta{
                    private EntityId entityId;
                    private EstaOculta oculta;
                    private EstaHabilitada habilitada;
                    private Integer xp;

                    public EntityId getEntityId() {
                        return entityId;
                    }

                    public EstaOculta getOculta() {
                        return oculta;
                    }

                    public EstaHabilitada getHabilitada() {
                        return habilitada;
                    }

                    public Integer getXp() {
                        return xp;
                    }

                    class EntityId{
                        String uuid;

                        public String getUuid() {
                            return uuid;
                        }
                    }

                    class EstaOculta{
                        Boolean value;

                        public Boolean getValue() {
                            return value;
                        }
                    }

                    class EstaHabilitada{
                        Boolean value;

                        public Boolean getValue() {
                            return value;
                        }
                    }

                }
            }

            class Puntaje {
                private Integer value;

                public Integer getValue() {
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
