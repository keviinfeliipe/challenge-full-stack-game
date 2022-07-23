package co.com.challenge.model.juego.value;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Alias implements ValueObject<String> {

    private final String value;

    public Alias(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alias alias = (Alias) o;
        return Objects.equals(value, alias.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
