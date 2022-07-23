package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class EstaOculta implements ValueObject<Boolean> {

    private final Boolean value;

    public EstaOculta(Boolean value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public Boolean value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstaOculta that = (EstaOculta) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
