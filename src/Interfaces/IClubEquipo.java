package Interfaces;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;

public class IClubEquipo extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private Club unClub;
    private DefaultTableModel unModeloTablaEquipo;
    private JInternalFrame unJInternalFrame;    
    
    public IClubEquipo(ControladoraGlobal unaControladoraGlobal, Club unClub, JInternalFrame unJInternalFrame) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unClub = unClub;     
        this.unJInternalFrame = unJInternalFrame;
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Club.png")));
        this.setTitle("Equipos de Club: " + unClub.getNombre()); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this);
        this.unModeloTablaEquipo = (DefaultTableModel) jTableEquipo.getModel();
        cargarTablaEquipo();
    }

    private void limpiarTabla(DefaultTableModel unModeloTablaEquipo) {
        int filas = unModeloTablaEquipo.getRowCount();
        for (int i = 0; i < filas; i++) {
            unModeloTablaEquipo.removeRow(0);
        }
    }

    private void cargarTablaEquipo() {
        limpiarTabla(unModeloTablaEquipo);
        List<Equipo> unaListaResultado = (List<Equipo>) this.unClub.getEquipos();
        for (Equipo unEquipo : unaListaResultado) {
            if (!unEquipo.isBorradoLogico()) {
                this.unModeloTablaEquipo.addRow(new Object[]{unEquipo.getIdEquipo(), unEquipo.getNombre(), unEquipo.getUnaDelegada(), unEquipo.getUnaDelegadaSuplente(), unEquipo.getUnDT()});
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEquipo = new javax.swing.JTable();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 405, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTableEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdEquipo", "Nombre", "Delegada", "Delegada Sup.", "Director Tecnico"
            }
        ));
        jScrollPane1.setViewportView(jTableEquipo);
        if (jTableEquipo.getColumnModel().getColumnCount() > 0) {
            jTableEquipo.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEquipo.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEquipo.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        IEquipo unIEquipo = new IEquipo(unaControladoraGlobal, this, unClub);
        unIEquipo.pack();
        unIEquipo.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIEquipo);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cargarTablaEquipo();
    }//GEN-LAST:event_formComponentShown

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEquipo;
    // End of variables declaration//GEN-END:variables
}
