/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Vista.vistaMain;
import Vista.vistaRegistrarGenero;
import Vista.vistaRegistrarMusica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.Genero;
import modelo.GeneroDAO;
import modelo.Musica;
import modelo.MusicaDAO;
import modelo.GeneroDAO;

/**
 *
 * @author dicxie
 */
public class CtrlRegistrarGenero implements ActionListener, InternalFrameListener, ListSelectionListener {

    private vistaRegistrarGenero vistaGenero;
    public vistaRegistrarMusica vistaMusica;
    public vistaMain vistaPrincipal;
    private GeneroDAO modeloGenero;
    private Genero genero;
    private Musica musica;

    public void enableControlls(boolean opt) {
        if (opt) {
            vistaGenero.btnCancelar.setEnabled(opt);
            vistaGenero.txtCategoria.setEnabled(opt);
            vistaGenero.txtIDCategoria.setEnabled(opt);
            vistaGenero.tbGenero.clearSelection();
        } else {
            vistaGenero.btnCancelar.setEnabled(opt);
            vistaGenero.btnEliminar.setEnabled(opt);
            vistaGenero.txtCategoria.setEnabled(opt);
            vistaGenero.txtIDCategoria.setEnabled(opt);
            vistaGenero.tbGenero.clearSelection();
        }
    }

    public CtrlRegistrarGenero(vistaRegistrarGenero vista) {
        vistaGenero = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String opcion = e.getActionCommand();
        switch (opcion) {
            case "Nuevo":
                btnNuevoActionPerformed();
                break;
            case "Cancelar":
                btnCancelarActionPeformed();
                break;
            case "Eliminar":
                btnEliminiarActionPerformed();
                break;
            case "Actualizar":
                btnActualizarActionPerformed();
                break;
            case "Registrar":
                btnRegistrarActionPerformed();
                break;

        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        vistaGenero.txtIDCategoria.setVisible(false);
        vistaGenero.lblIDCategoria.setVisible(false);
        vistaGenero.tbGenero.setModel(getRegistro());
        TableColumnModel tcm = vistaGenero.tbGenero.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vistaGenero.tbGenero.getSelectionModel()) {
            int fila = vistaGenero.tbGenero.getSelectedRow();
            if (fila != -1) {
                vistaGenero.btnNuevo.setText("Actualizar");
                vistaGenero.btnNuevo.setActionCommand("Actualizar");
                vistaGenero.btnEliminar.setEnabled(true);
                System.out.println(vistaGenero.btnNuevo.getActionCommand());
                vistaGenero.btnNuevo.setActionCommand("Actualizar");
                //Habilitar y limpiar los controles
                enableControlls(true);
                //recuperar los valores 
                Integer idArtista = (Integer) vistaGenero.tbGenero.getModel().getValueAt(fila, 0);
                String categoria = (String) vistaGenero.tbGenero.getModel().getValueAt(fila, 1);
                //agregar los valores a los controles
                vistaGenero.txtIDCategoria.setText(String.valueOf(idArtista));
                vistaGenero.txtCategoria.setText(categoria);
            }
        }
    }

    public void btnNuevoActionPerformed() {
        enableControlls(true);
        vistaGenero.btnNuevo.setText("Registrar");
        vistaGenero.btnNuevo.setActionCommand("Registrar");
        clearControlls();
    }

    private void btnCancelarActionPeformed() {
        enableControlls(false);
        vistaGenero.btnNuevo.setText("Nuevo");
        vistaGenero.btnNuevo.setActionCommand("Nuevo");
        clearControlls();
    }

    private void clearControlls() {
        vistaGenero.txtCategoria.setText(null);
        vistaGenero.txtIDCategoria.setText(null);
    }

    public DefaultTableModel getModelGenero() {
        DefaultTableModel tablemodel = new DefaultTableModel(new Object[][]{},
                new String[]{"ID Objeto", "Categoria"}) {
            Class type[] = {Integer.class, String.class};

            @Override
            public Class getColumnClass(int columnindex) {
                return type[columnindex];
            }

            @Override
            public boolean isCellEditable(int row, int columnindex) {
                return false;
            }
        };
        return tablemodel;
    }

    public DefaultTableModel getRegistro() {
        DefaultTableModel registro = getModelGenero();
        new GeneroDAO().getAll().forEach(gen -> {
            registro.addRow(new Object[]{gen.getIdGenero(), gen.getNombre()});
        });
        return registro;
    }

    private void btnActualizarActionPerformed() {
        //comprueba que los esten llenos los controles
        if (validarControl()) {
            genero = new Genero();
            modeloGenero = new GeneroDAO();
            //pasar los valores de los controles al Objeto genero
            genero.setIdGenero(Integer.parseInt(vistaGenero.txtIDCategoria.getText()));
            genero.setNombre(vistaGenero.txtCategoria.getText());
            //introducir el objeto genero en la base de datos 
            boolean resultado;
            resultado = modeloGenero.updateByID(genero);
            if (resultado) { //Comprobar que la consulta se ha realizado de manera satisfactoria
                JOptionPane.showMessageDialog(vistaGenero, "Se ha instroducido el registro");
                enableControlls(false);
                clearControlls();
                vistaGenero.btnNuevo.setText("Nuevo");
                vistaGenero.btnNuevo.setActionCommand("Nuevo");
                vistaGenero.tbGenero.setModel(getRegistro());
                TableColumnModel tcm = vistaGenero.tbGenero.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));
            } else {
                JOptionPane.showConfirmDialog(vistaGenero, "No se agrego el registro");
            }

        }
    }

    private boolean validarControl() {
        //valida que todos los campos contiene valores en su propietad Text
        return vistaGenero.txtCategoria.getText().length() > 0
                && vistaGenero.txtIDCategoria.getText().length() > 0;
    }

    private void btnEliminiarActionPerformed() {
        if (validarControl()) {
            genero = new Genero();
            modeloGenero = new GeneroDAO();
            //pasar los valores de los controles al Objeto
            genero.setIdGenero(Integer.parseInt(vistaGenero.txtIDCategoria.getText()));
            genero.setNombre(vistaGenero.txtCategoria.getText());
            //Eliminiar el objeto en la base de datos 
            boolean resultado;
            resultado = modeloGenero.deleteByID(genero);
            if (resultado) {
                JOptionPane.showInternalMessageDialog(vistaGenero, 
                        "El"+ genero+ " se Elimino");
                enableControlls(false);
                clearControlls();
                vistaGenero.btnNuevo.setText("Nuevo");
                vistaGenero.btnNuevo.setActionCommand("Nuevo");
                vistaGenero.tbGenero.setModel(getRegistro());
                vistaGenero.btnEliminar.setFocusable(false);
                TableColumnModel tcm = vistaGenero.tbGenero.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

            } else {
                JOptionPane.showInternalMessageDialog(vistaGenero, "No se agrego el registro");
            }
        }
    }

    private void btnRegistrarActionPerformed() {
        genero = new Genero();
        modeloGenero = new GeneroDAO();
        genero.setNombre(vistaGenero.txtCategoria.getText());
        if(modeloGenero.insert(genero)){
            JOptionPane.showInternalMessageDialog(vistaGenero, 
                    "El"+genero+ " se registro", "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
            vistaGenero.btnNuevo.setText("Nuevo");
            vistaGenero.btnNuevo.setActionCommand("Nuevo");
            vistaGenero.tbGenero.setModel(getRegistro());
            TableColumnModel tcm = vistaGenero.tbGenero.getColumnModel();
            tcm.removeColumn(tcm.getColumn(0));
            enableControlls(false);
            clearControlls();
        }
    }

}
