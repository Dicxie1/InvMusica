/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author dicxie
 */
import Vista.vistaMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.vistaRegistrarMusica;
import javax.swing.JOptionPane;
import modelo.Musica;
import modelo.MusicaDAO;
public class CtrlFrmMusica implements ActionListener {
    public vistaRegistrarMusica form;
    public vistaMain vistaPrincipal;
    private MusicaDAO modeloMusica;
    private Musica musica;
    
    public CtrlFrmMusica(){
        
    }
    public  CtrlFrmMusica(vistaRegistrarMusica frm){
        this.form = frm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String  s = e.getActionCommand();
        switch(s){
            case "Cerrar":
                form.setVisible(false);
                break;
            case "Insertar":
                JOptionPane.showMessageDialog(null, "Se ha insertado los registro");
                break;
            default:
                JOptionPane.showMessageDialog(null, "No disponible");
        }
    }
    
}
