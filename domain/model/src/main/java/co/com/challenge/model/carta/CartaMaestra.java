package co.com.challenge.model.carta;


public class CartaMaestra {

    private String id;
    private String nombre;
    private String descipcion;
    private Integer poder;
    private String caracteristica;
    private String imagen;

    public CartaMaestra() {
    }

    public CartaMaestra(String id, String nombre, String descipcion, Integer poder, String caracteristica, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descipcion = descipcion;
        this.poder = poder;
        this.caracteristica = caracteristica;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public Integer getPoder() {
        return poder;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
