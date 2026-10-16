package es.educastur.adriancr37.modelo;

public class Album {

    private Integer id;
    private String titulo;
    private Artista artista;

    public Album() {
    }

    public Album(Integer id, String titulo, Artista artista) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " (" + artista.getNombre() + ")";
    }
}
