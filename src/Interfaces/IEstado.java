package Interfaces;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Estado;
import logicaNegocios.Socia;
import logicaNegocios.TipoEstado;
import main.ControladoraGlobal;

public class IEstado extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private ControladoraGlobal unaControladoraGlobal;
    private Socia unaSocia;

    private boolean modificar = false;
    private DefaultTableModel modeloTableEstado;

    //LLAMADO A TRAVES DE UNA SOCIA (unico)
    public IEstado(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();

        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaSocia = unaSocia;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Estados.png")));
        //Centrar
        IMenuPrincipalInterface.centrar(this);
        //Titulo Ventana
        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());

        //ComboBox con los tipos de estados
        cargarComboBoxTipoEstado();

        this.modeloTableEstado = (DefaultTableModel) jTableEstado.getModel();
        cargarCamposTabla();

        camposActivo(false);

        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
    }

    public void cargarComboBoxTipoEstado() {
        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getTiposEstadosBD());
        this.jComboBoxEstado.setModel(modelCombo);
    }

    //Cargar Tabla con los Estados de la Socia
    public void cargarCamposTabla() {
        limpiarTabla(modeloTableEstado);

        for (Object aux : unaSocia.getEstadosValidos()) {
            Estado unEstado = (Estado) aux;
            DateFormat df = DateFormat.getDateInstance();
            this.modeloTableEstado.addRow(new Object[]{unEstado.getIdEstado(), df.format(unEstado.getFecha()), unEstado.getUnTipoEstado().getNombre()});
        }
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

    public void camposActivo(boolean Editable) {
        jTextFieldFecha.setEditable(Editable);
        jComboBoxEstado.setEnabled(Editable);
    }

    public void camposLimpiar() {
        jTextFieldFecha.setText("");
        jComboBoxEstado.setSelectedIndex(-1);
    }

    public boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldFecha.getText().isEmpty()) {
            jLabelFecha.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFecha.setForeground(Color.black);
        }
        if (jComboBoxEstado.getSelectedIndex() == -1) {
            jLabelEstado.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelEstado.setForeground(Color.black);
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
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstado = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jTextFieldFecha = new javax.swing.JTextField();
        jComboBoxEstado = new javax.swing.JComboBox();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 391));
        setMinimumSize(new java.awt.Dimension(650, 391));
        setPreferredSize(new java.awt.Dimension(650, 391));
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
        jButtonEditar.setEnabled(false);
        jButtonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEliminar)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar))
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdEstado", "Fecha", "Estados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEstado);
        if (jTableEstado.getColumnModel().getColumnCount() > 0) {
            jTableEstado.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEstado.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEstado.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableEstado.getSelectionModel () .addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                jButtonEliminar.setEnabled(true);
                jButtonEditar.setEnabled(true);
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelFecha.setText("Fecha");

        jLabelEstado.setText("Estado");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFecha)
                    .addComponent(jLabelEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFecha)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEstado)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        modificar = false;

        jTableEstado.clearSelection();
        jTableEstado.setEnabled(false);
        camposActivo(true);
        camposLimpiar();

        //Comportamiento Botones
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            DateFormat df = DateFormat.getDateInstance();
            try {
                Date fechaRealizacion = new java.sql.Date(df.parse(jTextFieldFecha.getText()).getTime());
                if (!modificar) {
                    unaControladoraGlobal.crearEstado(unaSocia, fechaRealizacion, (TipoEstado) jComboBoxEstado.getSelectedItem());
                    JOptionPane.showMessageDialog(this, "Nuevo Estado Guardado");
                } else {
                    Estado unEstado = unaControladoraGlobal.getEstadoBD((Long) jTableEstado.getValueAt(jTableEstado.getSelectedRow(), 0));
                    unaControladoraGlobal.modificarEstado(unEstado, fechaRealizacion, (TipoEstado) jComboBoxEstado.getSelectedItem(), false);
                    JOptionPane.showMessageDialog(this, "Estado Modificado");
                }
            } catch (ParseException e) {
                System.out.println("ERROR EN LAS FECHAS" + e.getMessage());
            }

            jButtonNuevo.setEnabled(true);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);
            jButtonEditar.setEnabled(false);

            camposLimpiar();
            camposActivo(false);
            jTableEstado.setEnabled(true);
            cargarCamposTabla();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        modificar = false;

        jTableEstado.clearSelection();

        camposLimpiar();
        camposActivo(false);
        jTableEstado.setEnabled(true);

        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        Estado unEstado = unaControladoraGlobal.getEstadoBD((Long) jTableEstado.getValueAt(jTableEstado.getSelectedRow(), 0));

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el estado de la Socia",
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarEstado(unEstado);

            cargarCamposTabla();
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        modificar = true;
        jTableEstado.setEnabled(false);
        camposActivo(true);

        Estado unEstado = unaControladoraGlobal.getEstadoBD((Long) jTableEstado.getValueAt(jTableEstado.getSelectedRow(), 0));
        DateFormat df = DateFormat.getDateInstance();
        jTextFieldFecha.setText(df.format(unEstado.getFecha()));
        jComboBoxEstado.setSelectedItem(unEstado.getUnTipoEstado());

        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        }
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEstado;
    private javax.swing.JTextField jTextFieldFecha;
    // End of variables declaration//GEN-END:variables
}
