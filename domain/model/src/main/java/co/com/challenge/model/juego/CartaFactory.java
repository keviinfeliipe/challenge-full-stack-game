package co.com.challenge.model.juego;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CartaFactory {

    public static CartaFactory instance;
    private final Set<Carta> cartas;

    public CartaFactory(){
        cartas = new HashSet<>();
    }

    public static CartaFactory getInstance(){
        if (Objects.isNull(instance)){
            instance = new CartaFactory();
            return instance;
        }
        return instance;
    }

    public CartaFactory add(Carta carta){
        cartas.add(carta);
        return this;
    }

    protected Set<Carta> cartas() {
        return cartas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartaFactory{");
        sb.append("cartas=").append(cartas);
        sb.append('}');
        return sb.toString();
    }
}
