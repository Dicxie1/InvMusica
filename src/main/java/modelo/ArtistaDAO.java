package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
public class ArtistaDAO implements CRUD<Artista> {
    private Conexion conexion;
    @Override
    public Artista getByID(Artista obj) {
       Artista artista = new Artista();
       conexion = new Conexion();
       try(Connection con = conexion.getConnection()){
           String sql = "CALL getArtistaById(?)";
           try(CallableStatement call = con.prepareCall(sql)){
               call.setInt(1, obj.getIdArtista());
               try(ResultSet rs = call.executeQuery()){
                   rs.next();
                   artista.setIdArtista(rs.getInt(1));
                   artista.setNombreCompleto(rs.getString(2));
                   artista.setNombreArtistico(rs.getString(3));
                   artista.setSexo(rs.getString(4).charAt(0));
                   artista.setNacionalidad(rs.getString(5));
               }
           }
           return artista ;
       }catch(SQLException e){
           e.printStackTrace();
           return null;
       }
    }
    @Override
    public List<Artista> getAll() {
        List<Artista> artistas = new ArrayList<>();
        conexion = new Conexion();
        try(Connection con = conexion.getConnection())
        {
            String sql = "CALL getAllArtista()";
            try(CallableStatement call = con.prepareCall(sql)){
                try(ResultSet rs = call.executeQuery()){
                    while(rs.next()){
                        Artista artist = new Artista();
                        artist.setIdArtista(rs.getInt("id_artista"));
                        artist.setNombreCompleto(rs.getString("nombreCompleto"));
                        artist.setNombreArtistico(rs.getString("nombreArtistico"));
                        artist.setSexo(rs.getString("sexo").charAt(0));
                        artist.setNacionalidad(rs.getString("nacionalidad"));
                        artistas.add(artist);
                    }
                }
            }
            return artistas;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean deleteByID(Artista obj) {
       String sql = "Call deleteArtista(?)";
       conexion = new Conexion();
       try(Connection con = conexion.getConnection();
            CallableStatement call = con.prepareCall(sql)){
           call.setInt(1, obj.getIdArtista());
           call.execute();
           return true;
       }catch(SQLException e){
           e.printStackTrace();
           return false;
       }
    }
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateByID(Artista obj) {
;
        String sql = "CALL updateArtista(?,?,?,?,?)";
        conexion = new Conexion();
        try(Connection con = conexion.getConnection();
            CallableStatement call = con.prepareCall(sql)){
            call.setInt(1, obj.getIdArtista());
            call.setString(2, obj.getNombreCompleto());
            call.setString(3, obj.getNombreArtistico());
            call.setString(4, String.valueOf(obj.getSexo()));
            call.setString(5,obj.getNacionalidad());
            call.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteArtistaGenero(Artista A, Genero G){
        String sql = "CALL deleteGeneroByArtista(?,?)";
        conexion = new Conexion();
        try(Connection con = conexion.getConnection();
                CallableStatement call = con.prepareCall(sql)){
         call.setInt(1, A.getIdArtista());
         call.setInt(2, G.getIdGenero());
         call.execute();
         return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean insert(Artista obj) {
        String sql = "CALL setArtista(?,?,?,?)";
        conexion = new Conexion();
        try(Connection con = conexion.getConnection();
                CallableStatement call = con.prepareCall(sql)){
            call.setString(1, obj.getNombreCompleto());
            call.setString(2, obj.getNombreArtistico());
            call.setString(3, String.valueOf(obj.getSexo()));
            call.setString(4, obj.getNacionalidad());
            call.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean insert(List<Artista> obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
