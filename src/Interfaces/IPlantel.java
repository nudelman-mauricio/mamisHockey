package Interfaces;

import java.awt.Color;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IPlantel extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private Equipo unEquipo;
    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaPlantel;
    private Socia unaSocia;

    public IPlantel(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Equipo unEquipo) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.unEquipo = unEquipo;
        this.unaControladoraGlobal = unaControladoraGlobal;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/plantel.png")));

        IMenuPrincipalInterface.centrar(this);

        camposActivo(false);
        jButtonCancelar.setEnabled(false);
        camposLimpiar();
        this.modeloTablaPlantel = (DefaultTableModel) jTablePlantel.getModel();
        obtenerPlantel();
    }

    private void obtenerPlantel() {
        DateFormat df = DateFormat.getDateInstance();
        Calendar FechaSO = Calendar.getInstance();
        String deudas, puesto;
        limpiarTabla(modeloTablaPlantel);

        for (Socia unaSocia : unEquipo.getPlantel()) {    
            
        
            if (unaSocia.isAlDia(FechaSO.getTime())) {
                deudas = "Si";
            } else {
                deudas = "No";
            }
//            puesto = "Jugadora";
//            if (unaSocia.getDni() == unEquipo.getUnaCapitana().getDni()) {
//                puesto = "Capitana";
//            } else {
//                if (unaSocia.getDni() == unEquipo.getUnaCapitanaSuplente().getDni()) {
//                    puesto = "Capitana Suplente";
//                } else {
//                    if (unaSocia.getDni() == unEquipo.getUnaDelegada().getDni()) {
//                        puesto = "Delegada";
//                    } else {
//                        if (unaSocia.getDni() == unEquipo.getUnaDelegadaSuplente().getDni()) {
//                            puesto = "Delegada Suplente";
//                        }
//                    }
//                }
//            }
            this.modeloTablaPlantel.addRow(new Object[]{
                unaSocia.getDni(),
                unaSocia.getNumeroCamiseta(),
                unaSocia.getApellido(),
                unaSocia.getNombre(),
                unaSocia.getUltimoEstado(),
                "puesto",
                deudas});
        }

    }

    private void limpiarTabla(DefaultTableModel modeloTablaPlantel) {
        try {
            int filas = modeloTablaPlantel.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTablaPlantel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    private void camposLimpiar() {
        jTextFieldNroCamiseta.setText("");
        jTextFieldApellido.setText("");
        jTextFieldNombre.setText("");
    }

    private void camposActivo(boolean Editable) {
        jTextFieldNroCamiseta.setEditable(Editable);

        jButtonGuardar.setEnabled(Editable);
        jButtonCancelar.setEnabled(Editable);
        jButtonEditar.setEnabled(Editable);
        jButtonEliminar.setEnabled(Editable);
        jButtonImprimir.setEnabled(!Editable);
    }

    private void SeleccionarObjetoTabla(boolean estado) {
        jButtonGuardar.setEnabled(estado);
        jButtonCancelar.setEnabled(estado);
        jButtonEditar.setEnabled(estado);
        jButtonEliminar.setEnabled(estado);
        jButtonImprimir.setEnabled(!estado);
        if (!estado) {
            jTablePlantel.clearSelection();
        }
    }

    //actualizar campos al seleccionar en la tabla
    private void camposCargar() {
        if (jTablePlantel.getSelectedRow() > -1) {
            if (jTablePlantel.getValueAt(jTablePlantel.getSelectedRow(), 0) != null) {
                Socia unaSociaSeleccionada = unaControladoraGlobal.getSociaBD((Long) jTablePlantel.getValueAt(jTablePlantel.getSelectedRow(), 0));

                camposLimpiar();

                jTextFieldNombre.setText(unaSociaSeleccionada.getNombre());
                jTextFieldApellido.setText(unaSociaSeleccionada.getApellido());
                jTextFieldNroCamiseta.setText(unaSociaSeleccionada.getNumeroCamiseta());

                camposActivo(false);
                jButtonEditar.setEnabled(true);
                jButtonEliminar.setEnabled(true);
            }
        }
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldNroCamiseta.getText().isEmpty()) {
            jLabelNroCamiseta.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNroCamiseta.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePlantel = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelNroCamiseta = new javax.swing.JLabel();
        jLabelFechaMonto = new javax.swing.JLabel();
        jLabelOrigen = new javax.swing.JLabel();
        jLabelDestino = new javax.swing.JLabel();
        jTextFieldApellido = new javax.swing.JTextField();
        jTextFieldNroCamiseta = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 431));
        setMinimumSize(new java.awt.Dimension(650, 431));
        setPreferredSize(new java.awt.Dimension(650, 431));
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

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Edit2.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/deletered.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

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
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonImprimir)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar)))
                .addGap(3, 3, 3))
        );

        jTablePlantel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "N° Camiseta", "Apellido", "Nombre", "Estado", "Puesto", "¿Posee Deudas?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePlantel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTablePlantelFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePlantel);
        if (jTablePlantel.getColumnModel().getColumnCount() > 0) {
            jTablePlantel.getColumnModel().getColumn(0).setMinWidth(0);
            jTablePlantel.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTablePlantel.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTablePlantel.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelNroCamiseta.setText("N° Camiseta");

        jLabelOrigen.setText("Apellido");

        jLabelDestino.setText("Nombre");

        jTextFieldApellido.setEditable(false);

        jTextFieldNombre.setEditable(false);

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabelFechaMonto))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNroCamiseta)
                                .addComponent(jLabelOrigen, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabelDestino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jTextFieldApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jTextFieldNroCamiseta))))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabelFechaMonto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNroCamiseta)
                    .addComponent(jTextFieldNroCamiseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrigen)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDestino))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        this.unaSocia = unaControladoraGlobal.getSociaBD((Long) jTablePlantel.getValueAt(jTablePlantel.getSelectedRow(), 0));
        camposActivo(true);
        jButtonEditar.setEnabled(false);
        jTextFieldNroCamiseta.setText(unaSocia.getNumeroCamiseta());
        jTextFieldNombre.setText(unaSocia.getNombre());
        jTextFieldApellido.setText(unaSocia.getApellido());
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jTablePlantelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTablePlantelFocusGained

    }//GEN-LAST:event_jTablePlantelFocusGained

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        boolean control = true;
        for (Socia SociasDelPlantel : unEquipo.getPlantel()) {
            if ((SociasDelPlantel.getDni() != unaSocia.getDni()) && (SociasDelPlantel.getNumeroCamiseta() == unaSocia.getNumeroCamiseta())) {
                control = false;
            }
        }
        if (control) {
            if (camposValidar()) {
                unaControladoraGlobal.modificarNumeroCamiseta(unaSocia, jTextFieldNroCamiseta.getText());
                JOptionPane.showMessageDialog(this, "Numero de camiseta modificado exitosamente");
                camposActivo(false);
                camposLimpiar();
                obtenerPlantel();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe una jugadora en el equipo con ese numero de camiseta");
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.jTablePlantel.clearSelection();
        this.camposActivo(false);
        this.camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JLabel jLabelDestino;
    private javax.swing.JLabel jLabelFechaMonto;
    private javax.swing.JLabel jLabelNroCamiseta;
    private javax.swing.JLabel jLabelOrigen;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePlantel;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNroCamiseta;
    // End of variables declaration//GEN-END:variables
}
