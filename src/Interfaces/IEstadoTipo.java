package Interfaces;

import java.awt.Color;
import java.text.DateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.TipoEstado;
import main.ControladoraGlobal;

public class IEstadoTipo extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablePases;
    private TipoEstado unTipoEstadoSeleccionado;
    private DateFormat df = DateFormat.getDateInstance();

    public IEstadoTipo(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        
        IMenuPrincipalInterface.jDesktopPane.add(this);
        IMenuPrincipalInterface.centrarYalFrente(this);
        
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablePases = (DefaultTableModel) jTableTipoEstado.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Estados.png")));//Icono de la ventana  
        this.setTitle("Tipos de Estados");//Titulo Ventana
        cargarTabla();
    }

    private void limpiarTabla() {
        int filas = modeloTablePases.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablePases.removeRow(0);
        }
    }

    //Cargar Tabla con los pases de la Socia
    public void cargarTabla() {
        limpiarTabla();
        for (TipoEstado unTipoEstado : unaControladoraGlobal.getTiposEstadosBD()) {
            if (!unTipoEstado.isBorradoLogico()) {
                this.modeloTablePases.addRow(new Object[]{unTipoEstado.getIdTipoEstado(), unTipoEstado.getNombre()});
            }
        }
        jButtonEditar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
    }

    public void camposCargar() {
        if (jTableTipoEstado.getSelectedRow() > -1) {
            if (jTableTipoEstado.getValueAt(jTableTipoEstado.getSelectedRow(), 0) != null) {
                unTipoEstadoSeleccionado = unaControladoraGlobal.getTipoEstadoBD((Long) jTableTipoEstado.getValueAt(jTableTipoEstado.getSelectedRow(), 0));
                jTextFieldNombre.setText(unTipoEstadoSeleccionado.getNombre());
                //jButtonEditar.setEnabled(true);
                //jButtonEliminar.setEnabled(true);
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
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTipoEstado = new javax.swing.JTable();
        jPanelDetalle = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 387));
        setMinimumSize(new java.awt.Dimension(650, 387));
        setPreferredSize(new java.awt.Dimension(650, 387));

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
        jButtonNuevo.setEnabled(false);
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
                .addContainerGap(198, Short.MAX_VALUE))
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

        jTableTipoEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdTipoEstado", "Nombre del Tipo de Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTipoEstado);
        if (jTableTipoEstado.getColumnModel().getColumnCount() > 0) {
            jTableTipoEstado.getColumnModel().getColumn(0).setMinWidth(0);
            jTableTipoEstado.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableTipoEstado.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableTipoEstado.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        jPanelDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalle.setName(""); // NOI18N

        jLabelNombre.setText("Nombre del Estado");

        jTextFieldNombre.setEditable(false);

        javax.swing.GroupLayout jPanelDetalleLayout = new javax.swing.GroupLayout(jPanelDetalle);
        jPanelDetalle.setLayout(jPanelDetalleLayout);
        jPanelDetalleLayout.setHorizontalGroup(
            jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabelNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetalleLayout.setVerticalGroup(
            jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                    .addComponent(jPanelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jTableTipoEstado.setEnabled(false);

        jTextFieldNombre.setEditable(true);
        jTextFieldNombre.setText("");
        unTipoEstadoSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            if (unTipoEstadoSeleccionado == null) {
                unaControladoraGlobal.crearTipoEstado(jTextFieldNombre.getText());
                JOptionPane.showMessageDialog(this, "Tipo de Estado Guardado");
            } else {
                unaControladoraGlobal.modificarTipoEstado(unTipoEstadoSeleccionado, jTextFieldNombre.getText(), unTipoEstadoSeleccionado.isBorradoLogico());
                JOptionPane.showMessageDialog(this, "Tipo de Estado Modificado");
                unTipoEstadoSeleccionado = null;
            }
            cargarTabla();

            jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);

            jTableTipoEstado.setEnabled(true);

            jTextFieldNombre.setEditable(false);
            jTextFieldNombre.setText("");
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);

        jTableTipoEstado.setEnabled(true);

        jTextFieldNombre.setEditable(false);
        jTextFieldNombre.setText("");
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jTableTipoEstado.setEnabled(true);
        
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el Tipo de Estado: " + unTipoEstadoSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarTipoEstado(unTipoEstadoSeleccionado);            
            cargarTabla();
        }
        unTipoEstadoSeleccionado = null;
        jTableTipoEstado.clearSelection();
        jTextFieldNombre.setEditable(false);
        jTextFieldNombre.setText("");
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableTipoEstado.setEnabled(false);

        jTextFieldNombre.setEditable(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalle;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTipoEstado;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
