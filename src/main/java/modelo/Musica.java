package modelo;

import java.util.List;

/**
 *
 * @author dicxie
 */
public class Musica {
    //Atributos

    private int idMusica;
    private String titulo;
    private int pista;
    private Disco disco;
    private List<Artista> colaboradores;
    private List<Genero> generos;

    public int getPista() {
        return pista;
    }

    public void setPista(int pista) {
        this.pista = pista;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public List<Artista> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Artista> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Genero> getGenero() {
        return generos;
    }

    public String getGenerosNombre() {
        String nombres = null;
        int i = 1;
        if (getGeneros() != null) {
            for (Genero genero : generos) {
                if (i == 1) {
                    nombres = genero.getNombre();
                    i++;
                } else {
                    nombres += ", " + genero.getNombre();
                }

            }
        }
        return nombres;
    }

    public String getColaboradoresNombres() {
        String nombres = null;
        int i = 1;
        if (getColaboradores() != null) {
            for (Artista artista : getColaboradores()) {
                if (i == 1) {
                    i++;
                    nombres = artista.getNombreCompleto();
                } else {
                    nombres += ", " + artista.getNombreCompleto();
                }
            }
        }
        return nombres;
    }

    @Override
    public String toString() {
        return getTitulo();
    }
}
