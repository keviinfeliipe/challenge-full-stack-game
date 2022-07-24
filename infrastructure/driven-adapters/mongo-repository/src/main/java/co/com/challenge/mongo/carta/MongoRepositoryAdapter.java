package co.com.challenge.mongo.carta;

import co.com.challenge.model.carta.CartaMaestra;
import co.com.challenge.model.carta.gateway.CartaRepository;
import org.reactivecommons.utils.ObjectMapperI;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryAdapter extends OperationAdapter<CartaMaestra, CartaDocument, String, CartaMongoRepository> implements CartaRepository{
    public MongoRepositoryAdapter(CartaMongoRepository repository, ObjectMapperI mapper) {
        super(repository, mapper, d -> mapper.map(d, CartaMaestra.class));
    }
}
