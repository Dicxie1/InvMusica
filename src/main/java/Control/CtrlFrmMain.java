package Control;

import Vista.ExampleJTree;
import Vista.VistaInfoMusica;
import Vista.vistaRegistrarArtista;
import Vista.vistaRegistrarDisco;
import Vista.vistaMain;
import Vista.vistaRegistrarGenero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author dicxie
 */
public class CtrlFrmMain implements ActionListener, WindowListener {
    public vistaMain form;
    public CtrlFrmMain(vistaMain nform) {
        form = nform;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        System.out.println(option);
        switch (option) {
            case "RegistrarDisco":
                mRegistrarMusicaActionPerformend();
                break;
            case "RegistrarArtista":
                mRegristrarArtistaActionPerformed();
                break;
            case "RegistrarGenero":
                mRegistrarGeneroActionPeformed();
                break;
            case "ListaMusica":
                btnListaMusicaActionPerformed();
                break;
            case "Disco":
                btnDiscoActionPerformed();
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    private void mRegistrarMusicaActionPerformend() {
        vistaRegistrarDisco ventana = new vistaRegistrarDisco(form);
        ventana.setVisible(true);
        form.escritorio.add(ventana);
    }

    private void mRegristrarMusicaActionPerformed() {
       
    }

    public void mRegristrarArtistaActionPerformed() {
        vistaRegistrarArtista ventana = new vistaRegistrarArtista();
        ventana.vistaPrincipal = form;
        form.escritorio.add(ventana);
        ventana.setVisible(true);
    }

    private void mRegistrarGeneroActionPeformed() {
        vistaRegistrarGenero ventana = new vistaRegistrarGenero();
        ventana.vistaPrincipal = form;
        form.escritorio.add(ventana);
        ventana.setVisible(true);
    }

    private void btnListaMusicaActionPerformed() {
        VistaInfoMusica ventana = new VistaInfoMusica();
        form.escritorio.add(ventana);
        ventana.setVisible(true);
    }

    private void btnDiscoActionPerformed() {
        ExampleJTree ventan = new ExampleJTree();
        
        ventan.setVisible(true);
    }
}
