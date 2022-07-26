package co.com.challenge.usecase.model;

public class Jugador {
    private String jugadorId;
    private String alias;
    private Integer puntaje;

    public Jugador() {
    }

    public Jugador(String jugadorId, String alias, Integer puntaje) {
        this.jugadorId = jugadorId;
        this.alias = alias;
        this.puntaje = puntaje;
    }

    public String getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(String jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
}
