package co.com.challenge.model.juego;

import co.com.sofka.domain.generic.ValueObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mazo implements ValueObject<Mazo.Values> {

    private final Set<Carta> cartas;
    private final Integer cantidad;

    public Mazo(Set<Carta> cartas) {
        Objects.requireNonNull(cartas);
        this.cartas = new HashSet<>();
        this.cantidad = 5;
    }

    @Override
    public Values value() {
        return new Values() {
            @Override
            public Set<Carta> cartas() {
                return cartas;
            }

            @Override
            public Integer cantidad() {
                return cantidad;
            }
        };
    }


    public interface Values {
        Set<Carta> cartas();
        Integer cantidad();
    }

}
