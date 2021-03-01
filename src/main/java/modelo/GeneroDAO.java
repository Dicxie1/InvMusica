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
public class GeneroDAO implements CRUD<Genero> {

    public static enum MODE {
        GET_MUSIC,
        GET_ARTISTA,
        GET_FULL
    };

    @Override
    public boolean insert(Genero obj) {
        String sql = "CALL setGenero(?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();
            CallableStatement call = con.prepareCall(sql)){
                call.setString(1, obj.getNombre());
                call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(List<Genero> obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    Conexion conexion;

    @Override
    public Genero getByID(Genero obj) {
        Genero genero = new Genero();
        try ( Connection con = conexion.getConnection()) {
            String sql = "CALL getArtistaById(?)";
            try ( CallableStatement call = con.prepareCall(sql)) {
                call.setInt(1, obj.getIdGenero());
                try ( ResultSet rs = call.executeQuery()) {
                    rs.next();
                    genero.setIdGenero(rs.getInt("id_genero"));
                    genero.setNombre(rs.getString("titulo"));
                }
            }
            return genero;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Genero> getAll() {
        List<Genero> generos = new ArrayList<>();
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection()) {
            String sql = "CALL getAllGenero()";
            try ( CallableStatement call = con.prepareCall(sql)) {
                try ( ResultSet rs = call.executeQuery()) {
                    while (rs.next()) {
                        Genero genero = new Genero();
                        genero.setIdGenero(rs.getInt("id_genero"));
                        genero.setNombre(rs.getString("titulo"));
                        generos.add(genero);
                    }
                }
            }
            return generos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteByID(Genero obj) {
        String sql = "CALL deleteGenero(?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();  CallableStatement call = con.prepareCall(sql)) {
            call.setInt(1, obj.getIdGenero());
            call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateByID(Genero obj) {
        String sql = "CALL updateGenero(?,?)";
        conexion = new Conexion();
        try ( Connection con = conexion.getConnection();  CallableStatement call = con.prepareCall(sql)) {
            call.setInt(1, obj.getIdGenero());
            call.setString(2, obj.getNombre());
            call.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public List<Genero> getGeneroByMusica(Musica obj){
        List<Genero> generos = new ArrayList<>();
        conexion = new Conexion();
        Genero genero;
        String sql = "CALL getGeneroByMusica(?)";
        try(Connection con = conexion.getConnection();
                CallableStatement call = con.prepareCall(sql)){
            call.setInt(1, obj.getIdMusica());
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                genero = new Genero();
                genero.setIdGenero(rs.getInt("id_genero"));
                genero.setNombre(rs.getString("titulo"));
                generos.add(genero);
            }
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            return generos;
        }finally{
            return generos;
        }
        
    }
}
