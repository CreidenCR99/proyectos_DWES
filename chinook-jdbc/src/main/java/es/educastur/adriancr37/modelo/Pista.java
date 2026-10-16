package es.educastur.adriancr37.modelo;

import java.math.BigDecimal;

public class Pista {

    private Integer id;
    private String nombre;
    private String compositor;
    private Integer milisegundos;
    private BigDecimal precioUnitario;
    private Genero genero;

    public Pista() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public Integer getMilisegundos() {
        return milisegundos;
    }

    public void setMilisegundos(Integer milisegundos) {
        this.milisegundos = milisegundos;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        int segundos = milisegundos / 1000;
        return String.format("%d:%02d", segundos / 60, segundos % 60);
    }

    @Override
    public String toString() {
        return nombre + " | " + genero.getNombre() + " | " + getDuracion() + " | " + precioUnitario + " €";
    }
}
