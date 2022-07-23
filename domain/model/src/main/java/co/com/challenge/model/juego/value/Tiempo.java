package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Tiempo implements ValueObject<Integer> {

    private final Integer value;

    public Tiempo(Integer value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tiempo tiempo = (Tiempo) o;
        return Objects.equals(value, tiempo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
