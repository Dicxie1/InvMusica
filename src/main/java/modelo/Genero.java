package modelo;

import java.util.List;
public class Genero {
    //
    private int idGenero;
    private String nombre;
    private List<Musica> musicas;
    private List<Artista> artistas;

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    } 
    @Override
    public String toString(){
        return " Genero: " + getNombre();
    }
}
