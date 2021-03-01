package modelo;


import java.util.List;
import javax.swing.UIManager;
/**
 *
 * @author dicxie
 */
public class Test {

    public static void main(String[] arg0) {
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            System.out.println(info.getName() + " "+ info.getClassName());
        }
//        GeneroDAO generoDAO = new GeneroDAO();
//        List<Genero> generos = generoDAO.getAll();
//        System.err.println("Datos de los discos Registros" + generos.size());
//        for (Genero genero : generos) {
//            System.out.println(
//                    "Código unico:  " + genero.getIdGenero()+ "\n"
//                    + "titulo : " + genero.getNombre() + "\n" );
//        }
//        ArtistaDAO artistaDAO = new ArtistaDAO();
//        List<Artista> obj = new ArrayList<>();
//    
//        obj = artistaDAO.getAll();
//        for( Artista artista : obj){
//        System.out.println("Datos Artista\n" +
//                "ID:\t" + artista.getIdArtista()+ "\n" +
//                "Nombre Completo:\t" + artista.getNombreCompleto()+ "\n" +
//                "Nombre Artistico:\t" + artista.getNombreArtistico()+ "\n" +
//                "Sexo:\t" + artista.getSexo()+ "\n" +
//                "Nacionalidad:\t" + artista.getNacionalidad()+ "\n");
//    }

    }

}
