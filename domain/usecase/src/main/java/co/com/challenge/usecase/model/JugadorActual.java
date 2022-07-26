package co.com.challenge.usecase.model;

import java.util.Date;
import java.util.List;

public class JugadorActual {

    private String jugadorId;
    private List<Carta> cartas;
    private Integer puntaje;
    private Date when;

    public JugadorActual() {
    }

    public JugadorActual(String jugadorId, List<Carta> cartas, Integer puntaje, Date when) {
        this.jugadorId = jugadorId;
        this.cartas = cartas;
        this.puntaje = puntaje;
        this.when = when;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(String jugadorId) {
        this.jugadorId = jugadorId;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
