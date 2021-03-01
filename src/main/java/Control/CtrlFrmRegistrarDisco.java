/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Vista.vistaRegistrarArtista;
import Vista.vistaRegistrarDisco;
import Vista.vistaMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.Artista;
import modelo.ArtistaDAO;
import modelo.Disco;
import modelo.DiscoDAO;

/**
 *
 * @author dicxie
 */
public class CtrlFrmRegistrarDisco implements ActionListener, InternalFrameListener, ListSelectionListener,
        MouseListener {

    vistaRegistrarDisco form;
    DiscoDAO modeloDisco;
    Disco disco;
    public vistaMain formularioPrincipal;

    public CtrlFrmRegistrarDisco() {
        form = null;
    }

    public CtrlFrmRegistrarDisco(vistaRegistrarDisco frmCtrl) {
        form = frmCtrl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        boolean v;
        System.out.println(option);
        int res;
        switch (option) {
            case "Agregar":
                int fila = form.tbArtista.getSelectedRow();
                if (fila != -1) {
                    Artista value = (Artista) form.tbArtista.getModel().getValueAt(fila, 0);
                    int tamanio = form.lstArtista.getModel().getSize();
                    if (tamanio == 0) {
                        res = JOptionPane.showConfirmDialog(form, "Desean agregar al " + value,
                                "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (res == 0) {
                            DefaultListModel<Artista> listaMode = new DefaultListModel();
                            listaMode.addElement(value);
                            form.lstArtista.setModel(listaMode);
                            form.btnEliminarArtista.setEnabled(true);
                            form.tbArtista.clearSelection();
                        }
                    } else if (tamanio == 1) {
                        JOptionPane.showMessageDialog(null, "No se puede agregar más de 1 inteprete",
                                "Error", JOptionPane.WARNING_MESSAGE);
                    }

                }
                break;
            case "EliminarArtista":
                if (form.lstArtista.getModel().getSize() == 1) {
                    Artista art = (Artista) form.lstArtista.getModel().getElementAt(0);
                    res = JOptionPane.showInternalConfirmDialog(form, "Desean elimir al Inteprete "
                            + art.toString(), "Info", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (res == 0) {
                        form.lstArtista.remove(0);
                        form.lstArtista = new JList();
                        form.spAritsta.setViewportView(form.lstArtista);
                        form.btnEliminarArtista.setEnabled(false);
                        form.tbArtista.clearSelection();
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(form,
                            "Registro vacio",
                            "informacion", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "RegistrarDiscos":
                if (validarCampo()) {
                    res = JOptionPane.showInternalConfirmDialog(form,
                            "Desea agregar el registro", "Aviso",
                            JOptionPane.YES_NO_OPTION);
                    if (res == 0) {
                        modeloDisco = new DiscoDAO();
                        disco = new Disco();
                        disco.setTitulo(form.txttitulo.getText());
                        disco.setLanzamiento((int) form.txtLanzamiento.getValue());
                        disco.setArtista((Artista) form.lstArtista.getModel().getElementAt(0));
                        v = modeloDisco.insert(disco);
                        System.out.println(v);
                        if(v){
                        JOptionPane.showInternalMessageDialog(form,
                                "Registro insertado",
                                "Operación exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                        clearContrles();
                        enableControl(false);
                        form.tbDisco.setModel(getRegistro());
                        }
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(form,
                            "Debe llenar campos:\nTitulo\nAño lanzamiento\nArtista");
                }
                break;
            case "NuevoDisco":
                enableControl(true);
                form.tbDisco.clearSelection();
                break;
            case "ActualizarDisco":
                System.out.println(validarCampo() && form.txtIdDisco.getText().length() > 0);
                if (validarCampo() && form.txtIdDisco.getText().length() > 0) {
                    modeloDisco = new DiscoDAO();
                    int idArtista = Integer.parseInt(form.txtIdDisco.getText());
                    String titulo = form.txttitulo.getText();
                    int lanzamiento = Integer.parseInt(form.txtLanzamiento.getValue().toString());
                    Artista artista = (Artista) form.lstArtista.getModel().getElementAt(0);
                    disco = new Disco();
                    disco.setIdDisco(idArtista);
                    disco.setLanzamiento(lanzamiento);
                    disco.setTitulo(titulo);
                    disco.setArtista(artista);
                    if (modeloDisco.updateByID(disco)) {
                        JOptionPane.showInternalMessageDialog(form,
                                "Registro guardado", "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        form.tbDisco.setModel(getRegistro());
                        clearContrles();
                        form.btnEliminar.setEnabled(false);
                        enableControl(false);

                    }
                }
                break;
            case "CancelarActualizacion":
                clearContrles();
                enableControl(false);
                break;
            case "Cancelar":
                clearContrles();
                enableControl(false);
                form.btnEliminar.setEnabled(false);
                form.tbDisco.clearSelection();
                break;
            case "Eliminar":
                if (form.txtIdDisco.getText().length() > 0) {
                    disco = new Disco();
                    disco.setIdDisco(Integer.parseInt(form.txtIdDisco.getText()));
                    modeloDisco = new DiscoDAO();
                    if (modeloDisco.deleteByID(disco)) {
                        JOptionPane.showInternalMessageDialog(form,
                                "Registro eliminado", "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        form.tbDisco.setModel(getRegistro());
                        clearContrles();
                        enableControl(false);

                    }
                }
                break;
            case "NuevoArtista":
                System.out.println("NuevoArtista");
                btnNuevoArtistaActionPerformed();
                break;
        }
    }

    public DefaultTableModel getDiscoModel() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{},
                new String[]{"Disco", "Año de Lazamiento", "Artista"}) {
            Class[] type = {Disco.class, Integer.class, Artista.class
            };
            boolean[] cantEdit = {false, false, false};

            public Class getColumnnClass(int columnindex) {
                return type[columnindex];
            }

            public boolean isCellEditable(int rowIndex, int columnindex) {
                return cantEdit[columnindex];
            }
        };
        return modelo;
    }

    public DefaultTableModel getRegistro() {
        DefaultTableModel registros = getDiscoModel();
        DiscoDAO modeloDisco = new DiscoDAO();
        for (Disco disco : modeloDisco.getAll()) {
            registros.addRow(new Object[]{disco, disco.getLanzamiento(), disco.getArtista()});
        }
        return registros;
    }

    public DefaultTableModel getArtistaModel() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{
            "Object", "Nombre Completo", "Nombre Artistico", "Sexo", "Nacinonalida" 
        }) {
            Class[] type = {Artista.class, String.class, String.class, String.class, String.class};

            public Class getColumnClass(int columnindex) {
                return type[columnindex];
            }
        };
        return modelo;
    }

    public DefaultTableModel getRistroArtista() {
        DefaultTableModel registro = getArtistaModel();
        ArtistaDAO modeloArtista = new ArtistaDAO();
        for (Artista artista : modeloArtista.getAll()) {
            registro.addRow(new Object[]{artista,
                artista.getNombreCompleto(), 
                artista.getNombreArtistico(), artista.getSexo(),
                artista.getNacionalidad()});
        }
        return registro;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        form.tbDisco.setModel(getRegistro());
        form.tbDisco.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        form.tbArtista.setModel(getRistroArtista());
        TableColumnModel tcm = form.tbArtista.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
        form.txtIdDisco.setVisible(false);
        form.lblIdArtista.setVisible(false);
    }

    public boolean validarCampo() {
        return form.txttitulo.getText().length() > 0
                && form.txtLanzamiento.getValue().toString().length() > 0
                && form.lstArtista.getModel().getSize() > 0;
    }

    public void clearContrles() {
        form.txtIdDisco.setText(null);
        form.txttitulo.setText(null);
        form.txtLanzamiento.setValue(0);
        form.lstArtista.setModel(new DefaultListModel());
        form.spAritsta.setViewportView(form.lstArtista);
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

    public void enableControl(boolean opt) {
        if (opt) {
            form.btnNuevoDisco.setText("Registrar");
            form.btnNuevoDisco.setActionCommand("RegistrarDiscos");
            form.btnAgregarArtista.setEnabled(opt);
            form.txttitulo.setEnabled(opt);
            form.btnNuevoArtista.setEnabled(opt);
            form.btnCancelar.setEnabled(opt);
            form.txtLanzamiento.setEnabled(opt);
            form.txttitulo.setEnabled(opt);
            form.lstArtista.setEnabled(opt);
            form.tbArtista.setEnabled(opt);
            form.btnEliminarArtista.setEnabled(opt);
        } else {
            form.btnNuevoDisco.setText("Nuevo");
            form.btnNuevoDisco.setActionCommand("NuevoDisco");
            form.btnAgregarArtista.setEnabled(opt);
            form.txttitulo.setEnabled(opt);
            form.btnNuevoArtista.setEnabled(opt);
            form.btnCancelar.setEnabled(opt);
            form.txtLanzamiento.setEnabled(opt);
            form.txttitulo.setEnabled(opt);
            form.lstArtista.setEnabled(opt);
            form.tbArtista.setEnabled(opt);
            form.btnEliminarArtista.setEnabled(opt);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == form.tbDisco.getSelectionModel()) {
            if (form.tbDisco.getSelectedRow() != -1) {
                enableControl(true);
                form.btnNuevoDisco.setText("Actualizar");
                form.btnNuevoDisco.setActionCommand("ActualizarDisco");
                int filaselecionada = form.tbDisco.getSelectedRow();
                //recuperar las registro de la JTable
                Disco edisco = (Disco) form.tbDisco.getValueAt(filaselecionada, 0);
                Integer lanzamiento = (Integer) form.tbDisco.getValueAt(filaselecionada, 1);
                Artista artista = (Artista) form.tbDisco.getValueAt(filaselecionada, 2);
                //Agregar en los controles del formulario
                form.txtIdDisco.setText(String.valueOf(edisco.getIdDisco()));
                form.txttitulo.setText(edisco.getTitulo());
                form.txtLanzamiento.setValue(lanzamiento);
                DefaultListModel<Artista> registroArtista = new DefaultListModel<>();
                registroArtista.addElement(artista);
                form.lstArtista.setModel(registroArtista);
                form.spAritsta.setViewportView(form.lstArtista);
                form.btnEliminar.setEnabled(true);

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void btnNuevoArtistaActionPerformed() {
        vistaRegistrarArtista ventana = new vistaRegistrarArtista();
        ventana.vistaDisco = form;
        formularioPrincipal.escritorio.add(ventana);
        ventana.toFront();
        ventana.setVisible(true);
    }

}
