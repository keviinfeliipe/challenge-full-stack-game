package co.com.challenge.usecase.model;

public class Carta {
    private String cartaId;
    private Boolean habilitada;
    private Boolean oculta;
    private Integer xp;

    public Carta() {
    }

    public Carta(String cartaId, Boolean habilitada, Boolean oculta, Integer xp) {
        this.cartaId = cartaId;
        this.habilitada = habilitada;
        this.oculta = oculta;
        this.xp = xp;
    }

    public String getCartaId() {
        return cartaId;
    }

    public void setCartaId(String cartaId) {
        this.cartaId = cartaId;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(Boolean habilitada) {
        this.habilitada = habilitada;
    }

    public Boolean getOculta() {
        return oculta;
    }

    public void setOculta(Boolean oculta) {
        this.oculta = oculta;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }
}
