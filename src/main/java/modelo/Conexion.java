package modelo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javax.swing.JOptionPane;
public class Conexion {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/inventario?serverTimezone=America/Managua";
    private String user = "root";
    private String pwd = "";
    private Connection con = null;
    
    
    public Conexion(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            if(ex.getClass() == CommunicationsException.class){
                JOptionPane.showMessageDialog(null, 
                        "No se establecion conexión con la base de datos"
                + "\n contacese con el Administrador",
                        "Error de Conexion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public Connection getConnection() {
        return con;
    }
}
