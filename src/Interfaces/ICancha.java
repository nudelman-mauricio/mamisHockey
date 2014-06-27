package Interfaces;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Cancha;
import logicaNegocios.Club;
import logicaNegocios.TipoCancha;
import main.ControladoraGlobal;

public class ICancha extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Club unClub;
    private Cancha unaCanchaSeleccionada;
    private DefaultTableModel modeloTable;

    public ICancha(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Club unClub) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unClub = unClub;
        this.modeloTable = (DefaultTableModel) jTableCancha.getModel();

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/club.png"))); //Icono Ventana
        this.setTitle("Club: " + unClub.getNombre()); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar

        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getTiposCanchasBD());
        this.jComboBoxTipo.setModel(modelCombo);
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        try {
            int filas = modeloTabla.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTabla.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    //Cargar Tabla con las Canchas del Club
    public void cargarTabla() {
        limpiarTabla(modeloTable);
        for (Cancha unaCancha : unClub.getCanchas()) {
            this.modeloTable.addRow(new Object[]{unaCancha.getUnTipoCancha().getNombre(), unaCancha.getNombre(), unaCancha.isSeOcupa()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCancha = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelTipo = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox();
        jCheckBoxSeOcupa = new javax.swing.JCheckBox();
        jLabelTipo1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Canchas - Nombre Club");
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

        jTableCancha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tipo", "Nombre", "Se ocupa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCancha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableCanchaFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCancha);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/deletered.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setEnabled(false);
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/cancel.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Edit2.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.setEnabled(false);
        jButtonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonCancelar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Cancha"));

        jLabelNombre.setText("Nombre");

        jTextFieldNombre.setEditable(false);

        jLabelTipo.setText("Tipo");

        jComboBoxTipo.setEnabled(false);

        jCheckBoxSeOcupa.setEnabled(false);

        jLabelTipo1.setText("Se Ocupa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelTipo)
                    .addComponent(jLabelTipo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBoxSeOcupa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNombre)
                    .addComponent(jComboBoxTipo, 0, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipo)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTipo1)
                    .addComponent(jCheckBoxSeOcupa))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonCancelar.setEnabled(true);
        jButtonGuardar.setEnabled(true);
        jButtonNuevo.setEnabled(false);
        jTableCancha.setEnabled(false);

        jTextFieldNombre.setEditable(true);
        jComboBoxTipo.setEnabled(true);
        jCheckBoxSeOcupa.setEnabled(true);

        jTextFieldNombre.setText("");
        jCheckBoxSeOcupa.setSelected(false);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed

    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonCancelar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonNuevo.setEnabled(true);
        jTableCancha.setEnabled(true);

        jTextFieldNombre.setEditable(false);
        jComboBoxTipo.setEnabled(false);
        jCheckBoxSeOcupa.setEnabled(false);

        jTextFieldNombre.setText("");
        jCheckBoxSeOcupa.setSelected(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTableCanchaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableCanchaFocusGained
        System.out.println(jTableCancha.getValueAt(jTableCancha.getSelectedRow(), 0));
        if (jTableCancha.getValueAt(jTableCancha.getSelectedRow(), 0) != null) {
            unaCanchaSeleccionada = unaControladoraGlobal.getCanchaBD((Long) jTableCancha.getValueAt(jTableCancha.getSelectedRow(), 0));
            jTextFieldNombre.setText(unaCanchaSeleccionada.getNombre());
            jCheckBoxSeOcupa.setSelected(unaCanchaSeleccionada.isSeOcupa());
            jComboBoxTipo.setSelectedItem(unaCanchaSeleccionada.getUnTipoCancha());
        }
    }//GEN-LAST:event_jTableCanchaFocusGained

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (unaCanchaSeleccionada == null) {
            unaControladoraGlobal.crearCancha(unClub, jTextFieldNombre.getText(), jCheckBoxSeOcupa.isSelected(), (TipoCancha) jComboBoxTipo.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Categoria Guardada");

        } else {
            unaControladoraGlobal.modificarCancha(unaCanchaSeleccionada, jTextFieldNombre.getText(), jCheckBoxSeOcupa.isSelected(), (TipoCancha) jComboBoxTipo.getSelectedItem(), false);
            unaCanchaSeleccionada = null;
        }
        cargarTabla();

        jButtonCancelar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonNuevo.setEnabled(true);
        jTableCancha.setEnabled(true);

        jTextFieldNombre.setEditable(false);
        jComboBoxTipo.setEnabled(false);
        jCheckBoxSeOcupa.setEnabled(false);

        jTextFieldNombre.setText("");
        jCheckBoxSeOcupa.setSelected(false);
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxSeOcupa;
    private javax.swing.JComboBox jComboBoxTipo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JLabel jLabelTipo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCancha;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
