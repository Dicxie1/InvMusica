package Control;

import Vista.vistaRegistrarArtista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import modelo.Artista;
import modelo.ArtistaDAO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CtrlFrmRegistrarArtista implements ActionListener, InternalFrameListener, ListSelectionListener {

    private vistaRegistrarArtista form;
    private ArtistaDAO modeloArtista;
    private Artista artista;

    public CtrlFrmRegistrarArtista() {
        form = null;
    }

    public CtrlFrmRegistrarArtista(vistaRegistrarArtista ventana) {
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

    public void btnNuevoActionPerformed() {
        enabledControl(true);
    }

    public void enabledControl(boolean opt) {
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

    private void btnCalcelarActionPerformed() {
        enabledControl(false);
        clearControllers();
        form.txtIDArtista.setText(null);
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
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
    public void internalFrameClosing(InternalFrameEvent e) {
        if (form.vistaDisco != null) {
            form.vistaDisco.setVisible(true);
        }
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }

    public void clearControllers() {
        form.txtIDArtista.setText("");
        form.txtNombreArtistico.setText("");
        form.txtNombreCompleto.setText("");
        form.txtNacionalidad.setText("");
        form.cbSexo.setSelectedIndex(0);
    }

    public void btnRegistrarArtistaActioPerformed() {
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

    public boolean validarCampos() {
        return form.txtNombreArtistico.getText().length() > 0
                && form.txtNombreCompleto.getText().length() > 0
                && form.txtNacionalidad.getText().length() > 0
                && form.cbSexo.getSelectedIndex() > 0;
    }

    public void btnEliminiarArtistaActionPerformed() {
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

    public DefaultTableModel getModel() {
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

    public DefaultTableModel getRegistroAritsta() {
        DefaultTableModel registro = getModel();
        modeloArtista = new ArtistaDAO();
        modeloArtista.getAll().forEach(artist -> {
            registro.addRow(new Object[]{artist, artist.getNombreCompleto(),
                artist.getNombreArtistico(), artist.getSexo(), artist.getNacionalidad()});
        });
        return registro;
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

    public void btnActualizarActionPerformed() {
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

}
