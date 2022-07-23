package co.com.challenge.mongo.carta;
import org.reactivecommons.utils.ObjectMapperI;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

public abstract class OperationAdapter<E, D, I, R extends ReactiveCrudRepository<D, I>> {
    protected R repository;
    protected ObjectMapperI mapper;
    private final Class<D> dataClass;
    private final Function<D, E> toEntityFn;

    @SuppressWarnings("unchecked")
    public OperationAdapter(R repository, ObjectMapperI mapper, Function<D, E> toEntityFn) {
        this.repository = repository;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
        this.toEntityFn = toEntityFn;
    }

    public Mono<E> save(E entity) {
        return Mono.just(entity)
                .map(this::toData)
                .flatMap(this::saveData)
                .map(this::toEntity);
    }

    public Flux<E> saveAll(Flux<E> entities) {
        return doQueryMany(repository.saveAll(entities.map(this::toData)));
    }

    public Mono<E> findById(I id) {
        return doQuery(repository.findById(id));
    }


    public Mono<Void> deleteById(I id) {
        return repository.deleteById(id);
    }

    public Flux<E> findAll() {
        return doQueryMany(repository.findAll());
    }

    protected Mono<E> doQuery(Mono<D> query) {
        return query.map(this::toEntity);
    }

    protected Flux<E> doQueryMany(Flux<D> query) {
        return query.map(this::toEntity);
    }

    protected Mono<D> saveData(D data) {
        return repository.save(data);
    }

    protected D toData(E entity) {
        return mapper.map(entity, dataClass);
    }

    protected E toEntity(D data) {
        return toEntityFn.apply(data);
    }

}
