package Interfaces;

import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Categoria;
import main.ControladoraGlobal;

public class ICategoria extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaCategoria;
    private Categoria unaCategoria;
    private boolean modificar = false;

    public ICategoria(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        this.modeloTablaCategoria = (DefaultTableModel) jTableCategoria.getModel();
        this.unaControladoraGlobal = unaControladoraGlobal;
        cargarTabla();

        //Icono de la ventana HAY QUE AGREGAR UN ICONO PARA LOCALIDAD
        //setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/localidad.png")));        
        IMenuPrincipalInterface.centrar(this);
        camposActivo(false);
    }

    public void camposActivo(boolean Editable) {
        jTextFieldNombre.setEditable(Editable);
        jTextFieldEdadParametro.setEditable(Editable);
        jTextFieldMaximoMenores.setEditable(Editable);
        jTextFieldMinimoMenores.setEditable(Editable);
        jButtonGuardar.setEnabled(Editable);
        jButtonEditar.setEnabled(Editable);
        jButtonCancelar.setEnabled(Editable);
        jButtonEliminar.setEnabled(Editable);
    }

    public boolean validar() {
        boolean bandera = true;
        if (jTextFieldNombre.getText().isEmpty()) {
            jLabelNombre.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNombre.setForeground(Color.black);
        }
        if (jTextFieldMaximoMenores.getText().isEmpty()) {
            jLabelMaximoMenores.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMaximoMenores.setForeground(Color.black);
        }
        if (jTextFieldEdadParametro.getText().isEmpty()) {
            jLabelEdad.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelEdad.setForeground(Color.black);
        }
        if (jTextFieldMinimoMenores.getText().isEmpty()) {
            jLabelMinimoMenores.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMinimoMenores.setForeground(Color.black);
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
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategoria = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelEdad = new javax.swing.JLabel();
        jTextFieldEdadParametro = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelMinimoMenores = new javax.swing.JLabel();
        jTextFieldMinimoMenores = new javax.swing.JTextField();
        jTextFieldMaximoMenores = new javax.swing.JTextField();
        jLabelMaximoMenores = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Categorias");
        setEnabled(false);
        setMaximumSize(new java.awt.Dimension(650, 443));
        setMinimumSize(new java.awt.Dimension(650, 443));
        setPreferredSize(new java.awt.Dimension(650, 443));

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
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Cant. Mínima Menores", "Cant. Máxima Menores"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCategoria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableCategoriaFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCategoria);
        if (jTableCategoria.getColumnModel().getColumnCount() > 0) {
            jTableCategoria.getColumnModel().getColumn(0).setMinWidth(0);
            jTableCategoria.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableCategoria.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableCategoria.getColumnModel().getColumn(2).setMinWidth(150);
            jTableCategoria.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTableCategoria.getColumnModel().getColumn(2).setMaxWidth(150);
            jTableCategoria.getColumnModel().getColumn(3).setMinWidth(150);
            jTableCategoria.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTableCategoria.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelNombre.setText("Nombre");

        jLabelEdad.setText("Edad Parámetro");

        jTextFieldEdadParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEdadParametroActionPerformed(evt);
            }
        });

        jLabelMinimoMenores.setText("Mínimo Permitido de Menores");

        jTextFieldMinimoMenores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMinimoMenoresActionPerformed(evt);
            }
        });

        jTextFieldMaximoMenores.setToolTipText("");
        jTextFieldMaximoMenores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaximoMenoresActionPerformed(evt);
            }
        });

        jLabelMaximoMenores.setText("Máximo Permitido de Menores");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMinimoMenores)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelEdad)
                    .addComponent(jLabelMaximoMenores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldEdadParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaximoMenores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMinimoMenores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEdad)
                    .addComponent(jTextFieldEdadParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMinimoMenores)
                    .addComponent(jTextFieldMinimoMenores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaximoMenores)
                    .addComponent(jTextFieldMaximoMenores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        modificar = true;
        unaCategoria = unaControladoraGlobal.buscarCategoriaBD((Long) jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 0));
        jTextFieldEdadParametro.setText(String.valueOf(unaCategoria.getEdadParametro()));
        jTextFieldMaximoMenores.setText(String.valueOf(unaCategoria.getCantidadMaxima()));
        jTextFieldMinimoMenores.setText(String.valueOf(unaCategoria.getCantidadMinima()));
        jTextFieldNombre.setText(unaCategoria.getNombre());
        camposActivo(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        unaCategoria = unaControladoraGlobal.buscarCategoriaBD((Long) jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 0));

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar la categoria: " + unaCategoria.getNombre() + " (" + unaCategoria.getEdadParametro() + ")?",
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarCategoria(unaCategoria);
            cargarTabla();
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        modificar = false;
        camposActivoNuevo(true);
        this.jTextFieldNombre.setText("");
        this.jTextFieldEdadParametro.setText("");
        this.jTextFieldMinimoMenores.setText("");
        this.jTextFieldMaximoMenores.setText("");
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (validar()) {
            if (!modificar) {
                unaControladoraGlobal.crearCategoria(jTextFieldNombre.getText(), Integer.parseInt(jTextFieldEdadParametro.getText()), Integer.parseInt(jTextFieldMinimoMenores.getText()), Integer.parseInt(jTextFieldMaximoMenores.getText()));
                JOptionPane.showMessageDialog(this, "Categoria Guardada");
            } else {
                unaCategoria = unaControladoraGlobal.buscarCategoriaBD((Long) jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 0));
                unaControladoraGlobal.modificarCategoria(unaCategoria, jTextFieldNombre.getText(), Integer.parseInt(jTextFieldEdadParametro.getText()), Integer.parseInt(jTextFieldMinimoMenores.getText()), Integer.parseInt(jTextFieldMaximoMenores.getText()), false);

            }
            camposActivo(false);
            //camposActivoNuevo(false);        
            this.jTextFieldNombre.setText("");
            this.jTextFieldEdadParametro.setText("");
            this.jTextFieldMinimoMenores.setText("");
            this.jTextFieldMaximoMenores.setText("");
            cargarTabla();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        camposActivoNuevo(false); // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTableCategoriaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableCategoriaFocusGained
        this.SeleccionarObjetoTabla(true);
    }//GEN-LAST:event_jTableCategoriaFocusGained

    private void jTextFieldEdadParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEdadParametroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEdadParametroActionPerformed

    private void jTextFieldMinimoMenoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMinimoMenoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMinimoMenoresActionPerformed

    private void jTextFieldMaximoMenoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaximoMenoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaximoMenoresActionPerformed

    private void cargarTabla() {
        limpiarTabla();
        List<Categoria> unaListaResultado = this.unaControladoraGlobal.getCategoriasBD();
        for (Categoria unaCategoria : unaListaResultado) {
            this.modeloTablaCategoria.addRow(new Object[]{
                unaCategoria.getIdCategoria(),
                unaCategoria.getNombre(),
                unaCategoria.getCantidadMinima(),
                unaCategoria.getCantidadMaxima()
            });
        }
    }

    public void camposActivoNuevo(boolean Editable) {
        jTextFieldNombre.setEditable(Editable);
        jTextFieldEdadParametro.setEditable(Editable);
        jTextFieldMinimoMenores.setEditable(Editable);
        jTextFieldMaximoMenores.setEditable(Editable);
        jButtonCancelar.setEnabled(Editable);
        jButtonGuardar.setEnabled(Editable);
        jButtonNuevo.setEnabled(!Editable);
        jButtonEditar.setEnabled(!Editable);
        jTableCategoria.setEnabled(!Editable);
    }

    private void limpiarTabla() {
        try {
            int filas = this.modeloTablaCategoria.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTablaCategoria.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla Cateogria.");
        }
    }

    private void SeleccionarObjetoTabla(boolean estado) {
        jButtonEditar.setEnabled(estado);
        jButtonEliminar.setEnabled(estado);
        if (!estado) {
            jTableCategoria.clearSelection();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JLabel jLabelEdad;
    private javax.swing.JLabel jLabelMaximoMenores;
    private javax.swing.JLabel jLabelMinimoMenores;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCategoria;
    private javax.swing.JTextField jTextFieldEdadParametro;
    private javax.swing.JTextField jTextFieldMaximoMenores;
    private javax.swing.JTextField jTextFieldMinimoMenores;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
