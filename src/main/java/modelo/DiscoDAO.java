package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dicxie
 */
public class DiscoDAO implements CRUD<Disco> {
    Conexion conexion;

    @Override
    public Disco getByID(Disco obj) {
        String sql = "CALL getDiscoByID(?)";
        Disco disco;
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();
                CallableStatement call = con.prepareCall(sql)) {
            call.setInt(1, obj.getIdDisco());
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                disco = new Disco();
                disco.setIdDisco(rs.getInt("id_disco"));
                disco.setTitulo(rs.getString("titulo_disco"));
                disco.setLanzamiento(rs.getInt("lanzamiento"));
                Artista art = new Artista();
                art.setIdArtista(rs.getInt("id_artista"));
                disco.setArtista(art);
                return disco;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Disco> getAll() {
        List<Disco> discos = new ArrayList<>();
        String sql = "CALL getAllDiscos()";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection(); 
                CallableStatement call = con.prepareCall(sql)) {
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Disco disco = new Disco();
                disco.setIdDisco(rs.getInt("id_disco"));
                disco.setTitulo(rs.getString("titulo_disco"));
                disco.setLanzamiento(rs.getInt("lanzamiento"));
                ArtistaDAO datoArtista = new ArtistaDAO();
                Artista artista = new Artista();
                artista.setIdArtista(rs.getInt("id_artista"));
                artista = datoArtista.getByID(artista);
                artista.setIdArtista(rs.getInt("id_artista"));
                disco.setArtista(artista);
                discos.add(disco);
            }
            return discos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteByID(Disco obj) {
        String sql = "Call deleteDisco(?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();  
                CallableStatement call = con.prepareCall(sql)) {
            call.setInt(1, obj.getIdDisco());
            call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateByID(Disco obj) {
        String sql = "CALL updateDisco(?,?,?,?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();  
                CallableStatement call = con.prepareCall(sql)) {
            call.setInt(1, obj.getIdDisco());
            call.setString(2, obj.getTitulo());
            call.setInt(3, obj.getLanzamiento());
            call.setInt(4, obj.getArtista().getIdArtista());
            call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(Disco obj) {
        String sql = "CALL setDisco(?,?,?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection(); 
                CallableStatement call = con.prepareCall(sql)) {
            call.setString(1, obj.getTitulo());
            call.setInt(2, obj.getLanzamiento());
            call.setInt(3, obj.getArtista().getIdArtista());
            call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(List<Disco> obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
