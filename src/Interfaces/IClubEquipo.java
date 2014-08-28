/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;

/**
 *
 * @author Leanwit
 */
public class IClubEquipo extends javax.swing.JInternalFrame {

    ControladoraGlobal unaControladoraGlobal;
    Club unClub;
    DefaultTableModel unModeloTablaEquipo;
    JDesktopPane unJDesktopPane;

    public IClubEquipo(ControladoraGlobal unaControladoraGlobal, Club unClub , JDesktopPane unJDesktopPane) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unClub = unClub;
        this.unJDesktopPane = unJDesktopPane;
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Club.png")));
        IMenuPrincipalInterface.centrar(this);
        this.unModeloTablaEquipo = (DefaultTableModel) jTableEquipo.getModel();
        cargarTablaEquipo();
       
    }

    private void limpiarTabla(DefaultTableModel unModeloTablaEquipo) {
        try {
            int filas = unModeloTablaEquipo.getRowCount();
            for (int i = 0; i < filas; i++) {
                unModeloTablaEquipo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    private void cargarTablaEquipo() {
        limpiarTabla(unModeloTablaEquipo);
        List<Equipo> unaListaResultado = (List<Equipo>) this.unClub.getEquipos();
        for (Equipo unEquipo : unaListaResultado) {
            if (!unEquipo.isBorradoLogico()) {
                this.unModeloTablaEquipo.addRow(new Object[]{unEquipo.getIdEquipo(),unEquipo.getNombre(),unEquipo.getUnaDelegada(), unEquipo.getUnaDelegadaSuplente(), unEquipo.getUnDT()});
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEquipo = new javax.swing.JTable();

        setClosable(true);
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
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 322, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonImprimir, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTableEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdEquipo", "Nombre", "Delegada", "Delegada Sup.", "Director Tecnico"
            }
        ));
        jTableEquipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableEquipoFocusGained(evt);
            }
        });
        jTableEquipo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTableEquipoComponentShown(evt);
            }
        });
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
        this.unJDesktopPane.add(unIEquipo);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jTableEquipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableEquipoFocusGained

    }//GEN-LAST:event_jTableEquipoFocusGained

    private void jTableEquipoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTableEquipoComponentShown

    }//GEN-LAST:event_jTableEquipoComponentShown

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cargarTablaEquipo();
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEquipo;
    // End of variables declaration//GEN-END:variables
}
