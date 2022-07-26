package co.com.challenge.model.juego;

import co.com.challenge.model.juego.value.MazoId;
import co.com.sofka.domain.generic.Entity;

import java.util.HashSet;
import java.util.Set;

public class Mazo extends Entity<MazoId> {

    private Set<Carta> cartas;
    private Integer cantidad;

    public Mazo() {
        super(new MazoId());
        this.cartas = new HashSet<>();
        this.cantidad = 0;
    }

    public Mazo(Set<Carta> cartas){
        super(new MazoId());
        this.cartas = cartas;
        this.cantidad = cartas.size();
    }

    public void agregarCarta(Carta carta){
        carta.ocultarCarta();

        this.cartas.add(carta);
        this.cantidad+=1;
    }

    public void quitarCarta(Carta carta){
        this.cartas.remove(carta);
        this.cantidad-=1;
    }

    public Set<Carta> cartas() {
        return cartas;
    }

    public Integer cantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Mazo{");
        sb.append("cartas=").append(cartas);
        sb.append(", cantidad=").append(cantidad);
        sb.append('}');
        return sb.toString();
    }
}
