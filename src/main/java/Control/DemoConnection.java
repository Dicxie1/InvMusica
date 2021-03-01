package Control;

/**
 *
 * @author dicxie
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoConnection {
    public static void main(String[] arg0){
            String user = "root";
            String pwd = null;
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/inventario?serverTimezone=America/Managua";
            
            Connection con = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            try{
                //levantamos al driver
                Class.forName(driver);
                //establecemos conecion con la base de datos
                con = DriverManager.getConnection(url, user,pwd);
               //definos la conslta
               String sql = "SELECT 'Se ha realizado una consulta a la base de datos'";
              pstm = con.prepareStatement(sql);
              rs = pstm.executeQuery();
              //iteacion los resultados y obtener los resultado
              while(rs.next()){
                  System.out.println(rs.getString(1));
              }
                
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
    }
}
