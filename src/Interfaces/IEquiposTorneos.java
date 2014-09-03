package Interfaces;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;

public class IEquiposTorneos extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private ControladoraGlobal unaControladoraGlobal;
    private Torneo unTorneo = null;
    private DefaultTableModel modeloTablaEquipoInscripto;
    private DefaultTableModel modeloTablaEquipoDisponible;
    private Club unClub = null;
    private Equipo unEquipoSeleccionado = null;

    public IEquiposTorneos(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Torneo unTorneo) {
        initComponents();
        IMenuPrincipalInterface.centrar(this);
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneo;
        this.modeloTablaEquipoInscripto = (DefaultTableModel) jTableEquiposInscriptos.getModel();
        this.modeloTablaEquipoDisponible = (DefaultTableModel) jTableEquiposDisponibles.getModel();
        this.setTitle("Torneo: " + unTorneo.getNombre());
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Torneo.png")));
        cargarTabla();
    }

    private void limpiarTabla() {
        int filas = modeloTablaEquipoInscripto.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaEquipoInscripto.removeRow(0);
        }
        filas = modeloTablaEquipoDisponible.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaEquipoDisponible.removeRow(0);
        }
    }

    private void cargarTabla() {
        //cargar inscriptos
        this.modeloTablaEquipoInscripto = (DefaultTableModel) jTableEquiposInscriptos.getModel();
        for (Equipo unEquipo : unTorneo.getEquiposInscriptos()) {
            this.modeloTablaEquipoInscripto.addRow(new Object[]{unEquipo.getIdEquipo(), unEquipo.getNombre(), unaControladoraGlobal.getClubBD(unEquipo)});
        }

        //cargar disponibles
        this.modeloTablaEquipoDisponible = (DefaultTableModel) jTableEquiposDisponibles.getModel();
        boolean bandera = true;
        for (Equipo unEquipo : unaControladoraGlobal.getEquiposDBPorCategoria(unTorneo.getUnaCategoria())) {
            for (Equipo aux : unTorneo.getEquiposInscriptos()) {
                if (aux.equals(unEquipo)) {
                    bandera = false;
                }
            }
            if (bandera) {
                this.modeloTablaEquipoDisponible.addRow(new Object[]{unEquipo.getIdEquipo(), unEquipo.getNombre(), unaControladoraGlobal.getClubBD(unEquipo)});
            }
            bandera = true;
        }
    }

    private void seleccionEquipoDisponible() {
        jButtonAgregar.setEnabled(true);
        jButtonQuitar.setEnabled(false);
        jTableEquiposInscriptos.clearSelection();
        unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquiposDisponibles.getValueAt(jTableEquiposDisponibles.getSelectedRow(), 0));
    }

    private void seleccionEquipoInscripto() {
        jButtonAgregar.setEnabled(false);
        jButtonQuitar.setEnabled(true);
        jTableEquiposDisponibles.clearSelection();
        unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquiposInscriptos.getValueAt(jTableEquiposInscriptos.getSelectedRow(), 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonImprimir = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        jButtonQuitar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEquiposDisponibles = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEquiposInscriptos = new javax.swing.JTable();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(800, 450));
        setMinimumSize(new java.awt.Dimension(800, 450));
        setPreferredSize(new java.awt.Dimension(800, 450));
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

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonImprimir)
                .addGap(3, 3, 3))
        );

        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/next.png"))); // NOI18N
        jButtonAgregar.setEnabled(false);
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/back.png"))); // NOI18N
        jButtonQuitar.setEnabled(false);
        jButtonQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitarActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipos disponibles no Inscrptos"));

        jTableEquiposDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Club", "Categoria"
            }
        ));
        jScrollPane1.setViewportView(jTableEquiposDisponibles);
        if (jTableEquiposDisponibles.getColumnModel().getColumnCount() > 0) {
            jTableEquiposDisponibles.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEquiposDisponibles.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEquiposDisponibles.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableEquiposDisponibles.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                seleccionEquipoDisponible();
            }
        });

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipos Inscriptos"));

        jTableEquiposInscriptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Club", "Categoria"
            }
        ));
        jScrollPane2.setViewportView(jTableEquiposInscriptos);
        if (jTableEquiposInscriptos.getColumnModel().getColumnCount() > 0) {
            jTableEquiposInscriptos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEquiposInscriptos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEquiposInscriptos.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableEquiposInscriptos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                seleccionEquipoInscripto();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jButtonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonQuitar)
                        .addContainerGap(152, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarActionPerformed
        unaControladoraGlobal.quitarEquipoInscripto(unTorneo, unEquipoSeleccionado);
        unEquipoSeleccionado = null;
        limpiarTabla();
        cargarTabla();
    }//GEN-LAST:event_jButtonQuitarActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        unaControladoraGlobal.agregarEquipoInscripto(unTorneo, unEquipoSeleccionado);
        unEquipoSeleccionado = null;
        limpiarTabla();
        cargarTabla();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonQuitar;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEquiposDisponibles;
    private javax.swing.JTable jTableEquiposInscriptos;
    // End of variables declaration//GEN-END:variables
}
