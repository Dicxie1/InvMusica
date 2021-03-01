/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Vista.VistaInfoMusica;
import Vista.vistaMain;
import java.awt.event.ItemEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.Artista;
import modelo.Musica;
import modelo.Disco;
import modelo.MusicaDAO;

/**
 *
 * @author dicxie
 */
public class CtrlListaMusica implements InternalFrameListener, DocumentListener, ItemListener{

    private VistaInfoMusica vistaInfoMusica;
    private vistaMain vistaPrincipal;

    public static final int SEARCH_BY_ALL = 0;
    public static final int SEARCH_BY_TITULO = 1;
    public static final int SEARCH_BY_DISCO = 2;
    public static final int SEARCH_BY_ARTISTA = 3;
    public static final int SEARCH_BY_LANZAMIENTO = 4;
    public static final int SEARCH_BY_GENERO = 5;
    private int filtrar = 0;

    public CtrlListaMusica(VistaInfoMusica form) {
        vistaInfoMusica = form;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        filtrar = vistaInfoMusica.cbFiltro.getSelectedIndex();
        vistaInfoMusica.tbListaMusica.setModel(getRegistroInfoMusica());
        tamanioColumna();
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

    public DefaultTableModel getModeloInfoMusica() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{},
                new String[]{"Musica", "N° pista", "Disco", "Artista", "Colaboradr", "Generos"}) {
            Class type[] = {Musica.class, Integer.class, Disco.class, Artista.class, String.class, String.class};

            public Class getColumnClass(int columnindex) {
                return type[columnindex];
            }

            public boolean isCellEditable(int row, int columnindex) {
                return false;
            }
        };
        
        return modelo;
    }

    public DefaultTableModel getRegistroInfoMusica() {
        MusicaDAO modeloMusica = new MusicaDAO();
        DefaultTableModel registro = registro = getModeloInfoMusica();
        switch (filtrar) {
            //no realizar busqueda
            case 0:
                for (Musica musica : modeloMusica.infoMusica()) {
                    registro.addRow(new Object[]{musica, musica.getPista(),
                        musica.getDisco(), musica.getDisco().getArtista(),
                        musica.getColaboradoresNombres(), musica.getGenerosNombre()});
                }
                break;
            //Buscar musica por Titulo 
            case 1:
                String titulo = vistaInfoMusica.txtBusqueda.getText();
                for(Musica musica : modeloMusica.searchByMusicaTitulo(titulo)){
                    registro.addRow(new Object[]{musica, musica.getPista(),
                        musica.getDisco(), musica.getDisco().getArtista(),
                        musica.getColaboradoresNombres(), musica.getGenerosNombre()});
                }
                break;
            //Bucar musica por Disco
            case 2:
                String disco = vistaInfoMusica.txtBusqueda.getText();
                for(Musica musica : modeloMusica.searchMusicaByDisco(disco)){
                    registro.addRow(new Object[]{musica, musica.getPista(),
                        musica.getDisco(), musica.getDisco().getArtista(),
                        musica.getColaboradoresNombres(), musica.getGenerosNombre()});
                }
                break;
            //Buscar Musica por Artista
            case 3:
                
                break;
            //Buscar por año de lanzamiento de Disco
            case 4:
                
                break;
            //Buscar por Genero musical
            case 5:
                String genero = vistaInfoMusica.txtBusqueda.getText();
                for(Musica musica : modeloMusica.searchMusicaByGenero(genero)){
                    registro.addRow(new Object[]{musica, musica.getPista(),
                        musica.getDisco(), musica.getDisco().getArtista(),
                        musica.getColaboradoresNombres(), musica.getGenerosNombre()});
                }
                break;

        }
        return registro;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        vistaInfoMusica.tbListaMusica.setModel(getRegistroInfoMusica());
        tamanioColumna();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        vistaInfoMusica.tbListaMusica.setModel(getRegistroInfoMusica());
        tamanioColumna();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        vistaInfoMusica.tbListaMusica.setModel(getRegistroInfoMusica());
        tamanioColumna();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        vistaInfoMusica.txtBusqueda.setText(null);
        filtrar = vistaInfoMusica.cbFiltro.getSelectedIndex();
    }
    public void tamanioColumna(){
        TableColumnModel tcm = vistaInfoMusica.tbListaMusica.getColumnModel();
        //Tamaño de las columnas Titulo
        tcm.getColumn(0).setPreferredWidth(200);
        tcm.getColumn(0).setMinWidth(200);
        //COlumna Pista
        tcm.getColumn(1).setMinWidth(40);
        tcm.getColumn(1).setPreferredWidth(40);
        //COlumna Disco
        tcm.getColumn(2).setMinWidth(150);
        tcm.getColumn(2).setPreferredWidth(150);
        //COlumna Artista
        tcm.getColumn(3).setMinWidth(150);
        tcm.getColumn(3).setPreferredWidth(150);
        //COlumna Colaboradores
        tcm.getColumn(4).setMinWidth(180);
        tcm.getColumn(4).setPreferredWidth(180);
        //COlumna Generos
        tcm.getColumn(5).setMinWidth(110);
        tcm.getColumn(5).setPreferredWidth(110);
    }

  }
