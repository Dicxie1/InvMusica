package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicxie
 */
public class MusicaDAO implements CRUD<Musica> {

    Conexion conexion;

    @Override
    public Musica getByID(Musica obj) {
        Musica musica = new Musica();
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection()) {
            String sql = "CALL getMusicaByID(?)";
            try ( CallableStatement call = con.prepareCall(sql)) {
                call.setInt(1, obj.getIdMusica());
                try ( ResultSet rs = call.executeQuery()) {
                    rs.next();
                    musica.setIdMusica(rs.getInt("id_musica"));
                    musica.setPista(rs.getInt("num_pist"));
                    musica.setTitulo(rs.getString("titulo_musica"));
                    Disco disco = new Disco();
                    disco.setIdDisco(rs.getInt("id_disco"));
                    musica.setDisco(disco);
                }
            }
            return musica;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Musica> getAll() {
        List<Musica> musicas = new ArrayList<>();
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection()) {
            String sql = "CALL getAllArtista()";
            try ( CallableStatement call = con.prepareCall(sql)) {
                try ( ResultSet rs = call.executeQuery()) {
                    while (rs.next()) {
                        Musica musica = new Musica();
                        musica.setIdMusica(rs.getInt("id_musica"));
                        musica.setPista(rs.getInt("num_pist"));
                        musica.setTitulo(rs.getString("titulo_musica"));
                        Disco disco = new Disco();
                        disco.setIdDisco(rs.getInt("id_disco"));
                        musica.setDisco(disco);
                        musicas.add(musica);
                    }
                }
            }
            return musicas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteByID(Musica obj) {
        String sql = "Call deleteMusica(?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();  CallableStatement call = con.prepareCall(sql)) {
            call.setInt(1, obj.getIdMusica());
            call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateByID(Musica obj) {
        String sql = "CALL updateMusica(?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();  CallableStatement call = con.prepareCall(sql)) {
            call.setInt(1, obj.getIdMusica());
            call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(Musica obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(List<Musica> obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Musica> infoMusica() {
        conexion = new Conexion();
        MusicaDAO modeloMusica = new MusicaDAO();
        DiscoDAO modeloDisco = new DiscoDAO();
        ArtistaDAO modeloArtista = new ArtistaDAO();
        GeneroDAO modeloGenero = new GeneroDAO();
        Genero genero = new Genero();
        List<Artista> colaboradores = new ArrayList<>();
        Artista artist = new Artista();
        Musica musica = new Musica();
        Disco disco = new Disco();
        List<Musica> listMusica = new ArrayList<>();
        String sql = "SELECT * FROM Musica";
        try ( Connection con = conexion.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
            //recuperar la musica y crear una objeto
            musica.setIdMusica(rs.getInt("id_Musica"));
            musica = modeloMusica.getByID(musica);
            //recuperar el disco de musica
            disco = modeloDisco.getByID(musica.getDisco());
            musica.setDisco(disco);
            //recuperar el Artista de la musica
            artist = modeloArtista.getByID(disco.getArtista());
            disco.setArtista(artist);
            //recuperar los colaboradores de la musica
            colaboradores = modeloMusica.getColaboradores(musica);
            musica.setColaboradores(colaboradores);
            //recuperar los los generos de la musicas
            musica.setGeneros(modeloGenero.getGeneroByMusica(musica));
            listMusica.add(musica);
            }
            return listMusica;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Artista> getColaboradores(Musica obj) {
        List<Artista> listaMusica = new ArrayList<>();
        Artista artista = new Artista();
        conexion = new Conexion();
        String sql = "CALL getColaboradorByMusica(?)";
        try ( Connection con = conexion.getConnection();  CallableStatement call = con.prepareCall(sql);) {
            call.setInt(1, obj.getIdMusica());
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                artista = new Artista();
                artista.setIdArtista(rs.getInt("id_artista"));
                artista.setNombreCompleto(rs.getString("nombreCompleto"));
                artista.setNombreArtistico(rs.getString("nombreArtistico"));
                artista.setSexo(rs.getString("sexo").charAt(0));
                artista.setNacionalidad(rs.getString("nacionalidad"));
                listaMusica.add(artista);
            }
            return listaMusica;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Genero> getGeneros(Musica obj){
        List<Genero> generos = new ArrayList<>();
        Genero genero = new Genero();
        conexion = new Conexion();
        String sql = "CALL getGeneroByMusica(?)";
        try(Connection con = conexion.getConnection();
                CallableStatement call = con.prepareCall(sql)){
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                genero = new Genero();
                genero.setIdGenero(rs.getInt("id_genero"));
                genero.setNombre(rs.getString("titulo"));
                generos.add(genero);
            }
            return generos;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<Musica>searchByMusicaTitulo(String titulo){
        conexion = new Conexion();
        MusicaDAO modeloMusica = new MusicaDAO();
        DiscoDAO modeloDisco = new DiscoDAO();
        ArtistaDAO modeloArtista = new ArtistaDAO();
        GeneroDAO modeloGenero = new GeneroDAO();
        Genero genero = new Genero();
        List<Artista> colaboradores = new ArrayList<>();
        Artista artist = new Artista();
        Musica musica = new Musica();
        Disco disco = new Disco();
        List<Musica> listMusica = new ArrayList<>();
        String sql = "CALL searchByMusicaTitulo(?)";
        try ( Connection con = conexion.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, titulo);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
            //recuperar la musica y crear una objeto
            musica.setIdMusica(rs.getInt("id_Musica"));
            musica = modeloMusica.getByID(musica);
            //recuperar el disco de musica
            disco = modeloDisco.getByID(musica.getDisco());
            musica.setDisco(disco);
            //recuperar el Artista de la musica
            artist = modeloArtista.getByID(disco.getArtista());
            disco.setArtista(artist);
            //recuperar los colaboradores de la musica
            colaboradores = modeloMusica.getColaboradores(musica);
            musica.setColaboradores(colaboradores);
            //recuperar los los generos de la musicas
            musica.setGeneros(modeloGenero.getGeneroByMusica(musica));
            listMusica.add(musica);
            }
            return listMusica;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Musica> searchMusicaByGenero(String nombreGenero){
        conexion = new Conexion();
        MusicaDAO modeloMusica = new MusicaDAO();
        DiscoDAO modeloDisco = new DiscoDAO();
        ArtistaDAO modeloArtista = new ArtistaDAO();
        GeneroDAO modeloGenero = new GeneroDAO();
        List<Artista> colaboradores = new ArrayList<>();
        Artista artist = new Artista();
        Musica musica = new Musica();
        Disco disco = new Disco();
        List<Musica> listMusica = new ArrayList<>();
        String sql = "CALL searchMusicaByGenero(?)";
        try ( Connection con = conexion.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, nombreGenero);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
            //recuperar la musica y crear una objeto
            musica.setIdMusica(rs.getInt("id_Musica"));
            musica = modeloMusica.getByID(musica);
            //recuperar el disco de musica
            disco = modeloDisco.getByID(musica.getDisco());
            musica.setDisco(disco);
            //recuperar el Artista de la musica
            artist = modeloArtista.getByID(disco.getArtista());
            disco.setArtista(artist);
            //recuperar los colaboradores de la musica
            colaboradores = modeloMusica.getColaboradores(musica);
            musica.setColaboradores(colaboradores);
            //recuperar los los generos de la musicas
            musica.setGeneros(modeloGenero.getGeneroByMusica(musica));
            listMusica.add(musica);
            musica.setGeneros(modeloGenero.getGeneroByMusica(musica));
            listMusica.add(musica);
            }
            return listMusica;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Musica> searchMusicaByDisco(String nombreDisco){
        List<Musica> musicas = new ArrayList<>();
        conexion = new Conexion();
        MusicaDAO modeloMusica = new MusicaDAO();
        DiscoDAO modeloDisco = new DiscoDAO();
        ArtistaDAO modeloArtista = new ArtistaDAO();
        GeneroDAO modeloGenero = new GeneroDAO();
        List<Artista> colaboradores = new ArrayList<>();
        Artista artist = new Artista();
        Musica musica = new Musica();
        Disco disco = new Disco();
        List<Musica> listMusica = new ArrayList<>();
        String sql = "CALL searchMusicaByDisco(?)";
        try ( Connection con = conexion.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, nombreDisco);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
            //recuperar la musica y crear una objeto
            musica.setIdMusica(rs.getInt("id_Musica"));
            musica = modeloMusica.getByID(musica);
            //recuperar el disco de musica
            disco = modeloDisco.getByID(musica.getDisco());
            musica.setDisco(disco);
            //recuperar el Artista de la musica
            artist = modeloArtista.getByID(disco.getArtista());
            disco.setArtista(artist);
            //recuperar los colaboradores de la musica
            colaboradores = modeloMusica.getColaboradores(musica);
            musica.setColaboradores(colaboradores);
            //recuperar los los generos de la musicas
            musica.setGeneros(modeloGenero.getGeneroByMusica(musica));
            listMusica.add(musica);
            }
            return listMusica;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
