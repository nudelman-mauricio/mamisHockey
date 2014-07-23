/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;

/**
 *
 * @author Lucas
 */
public class IEquiposTorneos extends javax.swing.JInternalFrame {

    /**
     * Creates new form EquiposTorneos
     */
    JInternalFrame unJInternalFrame;
    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    DefaultComboBoxModel modelCombo;
    DefaultTableModel modeloTablaEquipoInscripto;
    DefaultTableModel modeloTablaEquipoDisponible;
    Club unClub;

    public IEquiposTorneos(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Torneo unTorneo) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneo;
        this.modeloTablaEquipoInscripto = (DefaultTableModel) jTableEquiposInscriptos.getModel();
        this.modeloTablaEquipoDisponible = (DefaultTableModel) jTableEquiposInscriptos.getModel();
        this.setTitle("Torneo: " + unTorneo.getNombre());
        SeInicio();
        cargarTabla();
    }

    public void SeInicio() {
        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Equipoo.png")));

        camposActivo(false);

    }

    private void limpiarTabla(DefaultTableModel modeloTablaEquipoInscripto, DefaultTableModel modeloTablaEquipoDisponible) {
        try {
            int filas = modeloTablaEquipoInscripto.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTablaEquipoInscripto.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
        
        try {
            int filas = modeloTablaEquipoDisponible.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTablaEquipoDisponible.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
        
        
    }

    public void camposActivo(boolean Editable) {

        jButtonCancelar.setEnabled(Editable);
    }

    public void cargarTabla() {
        this.modeloTablaEquipoInscripto = (DefaultTableModel) jTableEquiposInscriptos.getModel();
        Collection<Equipo> listaInscriptos = unTorneo.getEquiposInscriptos();
        for (Equipo unEquipo : listaInscriptos) {
            this.modeloTablaEquipoInscripto.addRow(new Object[]{unEquipo.getIdEquipo(), unEquipo.getNombre(), unaControladoraGlobal.getClubBD(unEquipo)});
        }

        this.modeloTablaEquipoDisponible = (DefaultTableModel) jTableEquiposDisponibles.getModel();
        Collection<Equipo> unaListaResultado = unaControladoraGlobal.getEquiposDBPorCategoria(unTorneo.getUnaCategoria());
        boolean bandera = true;
        for (Equipo unEquipo : unaListaResultado) {

            for (Equipo aux : listaInscriptos) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonImprimir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonQuitar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEquiposInscriptos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEquiposDisponibles = new javax.swing.JTable();
        jTextFieldBuscar = new javax.swing.JTextField();
        jRadioButtonDni3 = new javax.swing.JRadioButton();
        jRadioButtonApellido3 = new javax.swing.JRadioButton();
        jRadioButtonDni4 = new javax.swing.JRadioButton();

        setClosable(true);

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

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/cancel.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Edit2.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonImprimir)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonEditar))
                .addGap(3, 3, 3))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

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

        jTableEquiposInscriptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Club", "Categoria", "id"
            }
        ));
        jTableEquiposInscriptos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableEquiposInscriptosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableEquiposInscriptosFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jTableEquiposInscriptos);
        if (jTableEquiposInscriptos.getColumnModel().getColumnCount() > 0) {
            jTableEquiposInscriptos.getColumnModel().getColumn(3).setMinWidth(0);
            jTableEquiposInscriptos.getColumnModel().getColumn(3).setPreferredWidth(0);
            jTableEquiposInscriptos.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jTableEquiposDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Club", "Categoria"
            }
        ));
        jTableEquiposDisponibles.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableEquiposDisponiblesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableEquiposDisponiblesFocusLost(evt);
            }
        });
        jScrollPane4.setViewportView(jTableEquiposDisponibles);
        if (jTableEquiposDisponibles.getColumnModel().getColumnCount() > 0) {
            jTableEquiposDisponibles.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEquiposDisponibles.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEquiposDisponibles.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jRadioButtonDni3.setText("Categoria");

        jRadioButtonApellido3.setText("Club");
        jRadioButtonApellido3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonApellido3ActionPerformed(evt);
            }
        });

        jRadioButtonDni4.setText("Nombre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldBuscar)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAgregar)
                            .addComponent(jButtonQuitar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jRadioButtonDni4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonApellido3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonDni3)))
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDni3)
                    .addComponent(jRadioButtonApellido3)
                    .addComponent(jRadioButtonDni4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonQuitar)))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jRadioButtonApellido3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonApellido3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonApellido3ActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        Equipo unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquiposDisponibles.getValueAt(jTableEquiposDisponibles.getSelectedRow(), 0));
        unaControladoraGlobal.agregarEquipoInscripto(unTorneo, unEquipoSeleccionado);
        limpiarTabla(modeloTablaEquipoInscripto, modeloTablaEquipoDisponible);
        cargarTabla();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jTableEquiposDisponiblesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableEquiposDisponiblesFocusLost
        this.jTableEquiposDisponibles.clearSelection();
        jButtonAgregar.setEnabled(false);

    }//GEN-LAST:event_jTableEquiposDisponiblesFocusLost

    private void jTableEquiposInscriptosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableEquiposInscriptosFocusLost
        this.jTableEquiposInscriptos.clearSelection();
        jButtonQuitar.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_jTableEquiposInscriptosFocusLost

    private void jTableEquiposDisponiblesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableEquiposDisponiblesFocusGained
        jButtonAgregar.setEnabled(true);
        jButtonQuitar.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_jTableEquiposDisponiblesFocusGained

    private void jTableEquiposInscriptosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableEquiposInscriptosFocusGained
        jButtonAgregar.setEnabled(false);
        jButtonQuitar.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_jTableEquiposInscriptosFocusGained

    private void jButtonQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarActionPerformed
        Equipo unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquiposDisponibles.getValueAt(jTableEquiposDisponibles.getSelectedRow(), 0));
        unaControladoraGlobal.quitarEquipoInscripto(unTorneo, unEquipoSeleccionado);
        limpiarTabla(modeloTablaEquipoInscripto, modeloTablaEquipoDisponible);
        cargarTabla();
     // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQuitarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonQuitar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JRadioButton jRadioButtonApellido3;
    private javax.swing.JRadioButton jRadioButtonDni3;
    private javax.swing.JRadioButton jRadioButtonDni4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableEquiposDisponibles;
    private javax.swing.JTable jTableEquiposInscriptos;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
