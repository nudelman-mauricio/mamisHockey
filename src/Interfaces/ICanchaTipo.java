package Interfaces;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.TipoCancha;
import main.ControladoraGlobal;

public class ICanchaTipo extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private TipoCancha unTipoCanchaSeleccionado;
    private DefaultTableModel modeloTable;

    public ICanchaTipo(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTable = (DefaultTableModel) jTableTipoCancha.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/club.png")));
        this.setTitle("Tipos de Canchas");
        IMenuPrincipalInterface.centrar(this);
        cargarTabla();
    }

    private void limpiarTabla() {
        int filas = modeloTable.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTable.removeRow(0);
        }
    }

    //Cargar Tabla con los tipos de canchas
    private void cargarTabla() {
        limpiarTabla();
        for (TipoCancha unTipoCancha : unaControladoraGlobal.getTiposCanchasBD()) {
            this.modeloTable.addRow(new Object[]{unTipoCancha.getIdTipoCancha(), unTipoCancha.getNombre()});
        }
        jButtonEditar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
    }

    //actualizar campos al seleccionar un tipo cancha en la tabla
    private void camposCargar() {
        if (jTableTipoCancha.getSelectedRow() > -1) {
            if (jTableTipoCancha.getValueAt(jTableTipoCancha.getSelectedRow(), 0) != null) {
                unTipoCanchaSeleccionado = unaControladoraGlobal.getTipoCanchaBD((Long) jTableTipoCancha.getValueAt(jTableTipoCancha.getSelectedRow(), 0));
                jTextFieldNombre.setText(unTipoCanchaSeleccionado.getNombre());
                jButtonEditar.setEnabled(true);
                jButtonEliminar.setEnabled(true);
            }
        }
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldNombre.getText().isEmpty()) {
            jLabelNombre.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNombre.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableTipoCancha = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 387));
        setMinimumSize(new java.awt.Dimension(650, 387));
        setPreferredSize(new java.awt.Dimension(650, 387));

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/deletered.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
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
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGuardar)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonCancelar)
                        .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jTableTipoCancha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdTipoCancha", "Nombre del Tipo de Cancha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableTipoCancha);
        if (jTableTipoCancha.getColumnModel().getColumnCount() > 0) {
            jTableTipoCancha.getColumnModel().getColumn(0).setMinWidth(0);
            jTableTipoCancha.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableTipoCancha.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableTipoCancha.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelNombre.setText("Nombre");

        jTextFieldNombre.setEditable(false);

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabelNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(false);

        jTextFieldNombre.setEditable(true);
        jTextFieldNombre.setText("");
        unTipoCanchaSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(true);

        jTextFieldNombre.setEditable(false);
        jTextFieldNombre.setText("");
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(false);

        jTextFieldNombre.setEditable(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            if (unTipoCanchaSeleccionado == null) {
                unaControladoraGlobal.crearTipoCancha(jTextFieldNombre.getText());
                JOptionPane.showMessageDialog(this, "Tipo Cancha Guardado");
            } else {
                unaControladoraGlobal.modificarTipoCancha(unTipoCanchaSeleccionado, jTextFieldNombre.getText(), false);
                JOptionPane.showMessageDialog(this, "Tipo Cancha Modificado");
                unTipoCanchaSeleccionado = null;
            }
            cargarTabla();

            jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);

            jTableTipoCancha.setEnabled(true);

            jTextFieldNombre.setEditable(false);
            jTextFieldNombre.setText("");
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        
        jTextFieldNombre.setEditable(false);

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el Tipo de Cancha: " + unTipoCanchaSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarTipoCancha(unTipoCanchaSeleccionado);            
            cargarTabla();
        }
        unTipoCanchaSeleccionado = null;
        jTableTipoCancha.clearSelection();
        jTableTipoCancha.setEnabled(true);
        jTextFieldNombre.setText("");
    }//GEN-LAST:event_jButtonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableTipoCancha;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
