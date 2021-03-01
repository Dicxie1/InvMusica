package Control;

import Vista.vistaRegistrarArtista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import modelo.Artista;
import modelo.ArtistaDAO;

public class CtrlFrmRegistrarArtista1 implements ActionListener, WindowListener, ListSelectionListener {

    private vistaRegistrarArtista form;
    private ArtistaDAO modeloArtista;
    private Artista artista;

    public CtrlFrmRegistrarArtista1() {
        form = null;
    }

    public CtrlFrmRegistrarArtista1(vistaRegistrarArtista ventana) {
        form = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        switch (option) {
            case "Nuevo":
                btnNuevoActionPerformed();
                break;
            case "Calcelar":
                btnCalcelarActionPerformed();
                form.tbRegistroArtista.clearSelection();
                break;
            case "RegistrarArtista":
                btnRegistrarArtistaActioPerformed();
                enabledControl(false);
                clearControllers();
                break;
            case "Eliminiar":
                btnEliminiarArtistaActionPerformed();
                clearControllers();
                enabledControl(true);
                form.btnEliminar.setEnabled(false);
                break;
            case "Actualizar":
                btnActualizarActionPerformed();
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        if (form.vistaDisco != null) {
            form.vistaDisco.setVisible(false);
        }
        form.txtIDArtista.setVisible(false);
        form.lblIDArtista.setVisible(false);
        form.tbRegistroArtista.setModel(getRegistroAritsta());
        TableColumnModel tcm = form.tbRegistroArtista.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == form.tbRegistroArtista.getSelectionModel()) {
            if (form.tbRegistroArtista.getSelectedRow() != -1) {
                enabledControl(true);
                form.btnNuevo.setText("Actualizar");
                form.btnNuevo.setActionCommand("Actualizar");
                form.btnEliminar.setEnabled(true);
                // recuperar la fila selecionada 
                int fila = form.tbRegistroArtista.getSelectedRow();
                //recuperar los valores de las celas de la fila seleccionada
                artista = new Artista();
                artista = (Artista) form.tbRegistroArtista.getModel().getValueAt(fila, 0);
                String nombreCompleto = (String) form.tbRegistroArtista.getValueAt(fila, 0);
                String nombreArtistico = (String) form.tbRegistroArtista.getValueAt(fila, 1);
                Character sexo = (Character) form.tbRegistroArtista.getValueAt(fila, 2);
                String nacionalidad = (String) form.tbRegistroArtista.getValueAt(fila, 3);
                //enlazar la filas seleccionadad a los controles
                form.txtIDArtista.setText(String.valueOf(artista.getIdArtista()));
                form.txtNombreCompleto.setText(nombreCompleto);
                form.txtNombreArtistico.setText(nombreArtistico);
                form.txtNacionalidad.setText(nacionalidad);
                if (!form.btnEliminar.isEnabled()) {
                    form.btnEliminar.setEnabled(true);
                }
                switch (sexo) {
                    case '?':
                        form.cbSexo.setSelectedIndex(1);
                        break;
                    case 'F':
                        form.cbSexo.setSelectedIndex(2);
                        break;
                    case 'M':
                        form.cbSexo.setSelectedIndex(3);
                        break;
                    default:
                        form.cbSexo.setSelectedIndex(0);
                        break;
                }
            }
        }
    }

    private void btnNuevoActionPerformed() {
        enabledControl(true);
    }

    private void btnCalcelarActionPerformed() {
        enabledControl(false);
        clearControllers();
        form.txtIDArtista.setText(null);
    }

    private void btnRegistrarArtistaActioPerformed() {
        if (validarCampos()) {
            modeloArtista = new ArtistaDAO();
            artista = new Artista();
            artista.setNombreCompleto(form.txtNombreCompleto.getText());
            artista.setNombreArtistico(form.txtNombreArtistico.getText());
            artista.setSexo(form.cbSexo.getSelectedItem().toString().charAt(0));
            artista.setNacionalidad(form.txtNacionalidad.getText());
            if (modeloArtista.insert(artista)) {
                JOptionPane.showInternalMessageDialog(form,
                        "Se ha Insertado el registro " + artista,
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                enabledControl(false);
                clearControllers();
                form.tbRegistroArtista.setModel(getRegistroAritsta());
            }
        } else {
            JOptionPane.showInternalMessageDialog(form,
                    "Debe de rellenar todos los campos", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void enabledControl(boolean opt) {
        if (opt) {
            form.btnNuevo.setText("Registrar");
            form.btnNuevo.setActionCommand("RegistrarArtista");
            form.btnCancelar.setEnabled(opt);
            form.txtNombreCompleto.setEnabled(opt);
            form.txtNacionalidad.setEnabled(opt);
            form.txtNombreArtistico.setEnabled(opt);
            form.cbSexo.setEnabled(opt);
            clearControllers();
        } else {
            form.btnNuevo.setText("Nuevo");
            form.btnNuevo.setActionCommand("Nuevo");
            form.btnCancelar.setEnabled(opt);
            form.txtNombreCompleto.setEnabled(opt);
            form.txtNacionalidad.setEnabled(opt);
            form.txtNombreArtistico.setEnabled(opt);
            form.cbSexo.setEnabled(opt);
            clearControllers();
        }
    }

    private void clearControllers() {
        form.txtIDArtista.setText("");
        form.txtNombreArtistico.setText("");
        form.txtNombreCompleto.setText("");
        form.txtNacionalidad.setText("");
        form.cbSexo.setSelectedIndex(0);
    }

    private void btnEliminiarArtistaActionPerformed() {
        modeloArtista = new ArtistaDAO();
        artista = new Artista();
        int row = form.tbRegistroArtista.getSelectedRow();
        int value = Integer.parseInt(form.txtIDArtista.getText());
        artista.setIdArtista(value);
        modeloArtista.deleteByID(artista);
        form.tbRegistroArtista.setModel(getRegistroAritsta());
        TableColumnModel tcm = form.tbRegistroArtista.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
        form.tbRegistroArtista.clearSelection();
    }

    private void btnActualizarActionPerformed() {
        modeloArtista = new ArtistaDAO();
        artista = new Artista();
        //pasar los valores de los controles al Objeto Artista
        artista.setIdArtista(Integer.parseInt(form.txtIDArtista.getText()));
        artista.setNombreCompleto(form.txtNombreCompleto.getText());
        artista.setNombreArtistico(form.txtNombreArtistico.getText());
        artista.setSexo(form.cbSexo.getSelectedItem().toString().charAt(0));
        artista.setNacionalidad(form.txtNacionalidad.getText());
        //Introducir los valores utilizando el metodo insert
        if (modeloArtista.updateByID(artista)) {
            JOptionPane.showInternalMessageDialog(form, "Se ha Actualizado el registro",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            enabledControl(false);
            form.btnEliminar.setEnabled(false);
            clearControllers();
            //Actualizar los registro en JTable
            form.tbRegistroArtista.setModel(getRegistroAritsta());
            //Oculatar la Primera columna de la tabla
            TableColumnModel tcm = form.tbRegistroArtista.getColumnModel();
            tcm.removeColumn(tcm.getColumn(0));
        }
    }

    private TableModel getRegistroAritsta() {
        DefaultTableModel registro = getModel();
        modeloArtista = new ArtistaDAO();
        modeloArtista.getAll().forEach(artist -> {
            registro.addRow(new Object[]{artist, artist.getNombreCompleto(),
                artist.getNombreArtistico(), artist.getSexo(), artist.getNacionalidad()});
        });
        return registro;
    }

    private boolean validarCampos() {
        return form.txtNombreArtistico.getText().length() > 0
                && form.txtNombreCompleto.getText().length() > 0
                && form.txtNacionalidad.getText().length() > 0
                && form.cbSexo.getSelectedIndex() > 0;
    }

    private DefaultTableModel getModel() {
        return new DefaultTableModel(new Object[][]{}, new String[]{"ID Object",
            "Nombre Completo", "Nombre Artistico", "Sexo", "Nacionalidad"}) {
            Class type[] = {Artista.class, String.class, String.class, String.class, String.class};

            @Override
            public Class getColumnClass(int columnindex) {
                return type[columnindex];
            }

            @Override
            public boolean isCellEditable(int row, int columnindex) {
                return false;
            }
        };
    }
}
