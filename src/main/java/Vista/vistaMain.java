package Vista;

import Control.CtrlFrmMain;
import javax.swing.JInternalFrame;
/**
 *
 * @author dicxie
 */
public class vistaMain extends javax.swing.JFrame {

    /**
     * Creates new form jFrmMain
     */
    public vistaMain() {
        initComponents();
        addController(new CtrlFrmMain(this));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        escritorio = new javax.swing.JDesktopPane();
        pnMenuBar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        mRegistrar = new javax.swing.JMenu();
        mRegristrarDisco = new javax.swing.JMenuItem();
        mRegistrarArtista = new javax.swing.JMenuItem();
        mRegistrarGenero = new javax.swing.JMenuItem();
        mVer = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        mImprmir = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(3);

        escritorio.setAutoscrolls(true);
        escritorio.setMaximumSize(null);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Disco.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setActionCommand("Disco");
        jButton1.setMaximumSize(new java.awt.Dimension(36, 36));
        jButton1.setMinimumSize(new java.awt.Dimension(36, 36));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/music.png"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.setActionCommand("ListaMusica");

        javax.swing.GroupLayout pnMenuBarLayout = new javax.swing.GroupLayout(pnMenuBar);
        pnMenuBar.setLayout(pnMenuBarLayout);
        pnMenuBarLayout.setHorizontalGroup(
            pnMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuBarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMenuBarLayout.setVerticalGroup(
            pnMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mRegistrar.setText("Registrar");
        mRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRegistrarActionPerformed(evt);
            }
        });

        mRegristrarDisco.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mRegristrarDisco.setText("Disco");
        mRegristrarDisco.setActionCommand("RegistrarDisco");
        mRegistrar.add(mRegristrarDisco);

        mRegistrarArtista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mRegistrarArtista.setText("Artista");
        mRegistrarArtista.setActionCommand("RegistrarArtista");
        mRegistrar.add(mRegistrarArtista);

        mRegistrarGenero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mRegistrarGenero.setText("Genero Musical");
        mRegistrarGenero.setActionCommand("RegistrarGenero");
        mRegistrar.add(mRegistrarGenero);

        menu.add(mRegistrar);

        mVer.setText("Ver");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Musica");
        mVer.add(jMenuItem4);

        menu.add(mVer);

        mImprmir.setText("Imprimir");

        jMenuItem1.setText("Discografia");
        mImprmir.add(jMenuItem1);

        menu.add(mImprmir);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRegistrarActionPerformed
        // TODO add your handling code here:
        vistaRegistrarMusica v = new vistaRegistrarMusica();
        escritorio.add(v);
        v.show();
        v.setVisible(true);
    }//GEN-LAST:event_mRegistrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
           javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaMain().setVisible(true);
            }
        });
    }

    public void addController(CtrlFrmMain C) {
        mRegristrarDisco.addActionListener(C);
        mRegistrarArtista.addActionListener(C);
        mRegistrarGenero.addActionListener(C);
        jButton2.addActionListener(C);
        jButton1.addActionListener(C);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane escritorio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu3;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu mImprmir;
    private javax.swing.JMenu mRegistrar;
    public javax.swing.JMenuItem mRegistrarArtista;
    public javax.swing.JMenuItem mRegistrarGenero;
    public javax.swing.JMenuItem mRegristrarDisco;
    private javax.swing.JMenu mVer;
    private javax.swing.JMenuBar menu;
    private javax.swing.JPanel pnMenuBar;
    // End of variables declaration//GEN-END:variables
    
}
