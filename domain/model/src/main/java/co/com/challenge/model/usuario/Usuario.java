package co.com.challenge.model.usuario;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String Rol;
    private Integer puntos;

    public Usuario(String id, String nombre, String email, String rol, Integer puntos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        Rol = rol;
        this.puntos = puntos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
