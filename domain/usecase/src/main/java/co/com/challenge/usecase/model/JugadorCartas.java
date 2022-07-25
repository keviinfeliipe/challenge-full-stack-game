package co.com.challenge.usecase.model;

import co.com.challenge.model.juego.Carta;

import java.util.List;

public class JugadorCartas {

    private String juegoId;
    private List<Carta> cartas;

    public JugadorCartas(String juegoId, List<Carta> cartas) {
        this.juegoId = juegoId;
        this.cartas = cartas;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
}
