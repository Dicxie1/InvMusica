/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.CtrlListaMusica;

/**
 *
 * @author dicxie
 */
public class VistaInfoMusica extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaInfoMusica
     */
    public VistaInfoMusica() {
        initComponents();
        addControllers(new CtrlListaMusica(this));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblBuscarPor = new javax.swing.JLabel();
        cbFiltro = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        spListaMusica = new javax.swing.JScrollPane();
        tbListaMusica = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setName("Lista de Musica"); // NOI18N

        lblBuscarPor.setText("Buscar por: ");

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Titulo", "Disco", "Artista", "Lanzamiento", "Genero" }));

        tbListaMusica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Musica", "N° Pista", "Disco", "Artista", "Colaboradores", "Genero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbListaMusica.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbListaMusica.setShowGrid(true);
        spListaMusica.setViewportView(tbListaMusica);
        if (tbListaMusica.getColumnModel().getColumnCount() > 0) {
            tbListaMusica.getColumnModel().getColumn(0).setMinWidth(300);
            tbListaMusica.getColumnModel().getColumn(0).setPreferredWidth(300);
            tbListaMusica.getColumnModel().getColumn(1).setMinWidth(60);
            tbListaMusica.getColumnModel().getColumn(1).setPreferredWidth(60);
            tbListaMusica.getColumnModel().getColumn(1).setMaxWidth(80);
            tbListaMusica.getColumnModel().getColumn(2).setMinWidth(200);
            tbListaMusica.getColumnModel().getColumn(2).setPreferredWidth(200);
            tbListaMusica.getColumnModel().getColumn(3).setMinWidth(200);
            tbListaMusica.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbListaMusica.getColumnModel().getColumn(4).setMinWidth(200);
            tbListaMusica.getColumnModel().getColumn(4).setPreferredWidth(200);
            tbListaMusica.getColumnModel().getColumn(5).setMinWidth(200);
            tbListaMusica.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscarPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(spListaMusica, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscarPor)
                    .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spListaMusica, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void addControllers(CtrlListaMusica C){
        
        txtBusqueda.getDocument().addDocumentListener(C);
        addInternalFrameListener(C);
        cbFiltro.addItemListener(C);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cbFiltro;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblBuscarPor;
    private javax.swing.JScrollPane spListaMusica;
    public javax.swing.JTable tbListaMusica;
    public javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
