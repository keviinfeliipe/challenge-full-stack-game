package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Xp implements ValueObject<Integer> {
    private final Integer value;

    public Xp(Integer value) {
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
        Xp xp = (Xp) o;
        return Objects.equals(value, xp.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
