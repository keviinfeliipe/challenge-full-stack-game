package co.com.challenge.usecase.model;

import java.util.Date;
import java.util.List;

public class JuegoInformacion {

    private String juegoId;
    private List<Jugador> jugadores;
    private Boolean estado;
    private Date when;

    public JuegoInformacion() {
    }

    public JuegoInformacion(String juegoId, List<Jugador> jugadores, Boolean estado, Date when) {
        this.juegoId = juegoId;
        this.jugadores = jugadores;
        this.estado = estado;
        this.when = when;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
