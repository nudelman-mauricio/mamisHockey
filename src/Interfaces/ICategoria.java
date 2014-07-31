package Interfaces;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Categoria;
import main.ControladoraGlobal;


public class ICategoria extends javax.swing.JInternalFrame {
 
    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaCategoria;
    private Categoria unaCategoria;    
    private boolean modificar=false;
    
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
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategoria = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabelFechaRealizacion1 = new javax.swing.JLabel();
        jLabelOrigen = new javax.swing.JLabel();
        jTextFieldEdadParametro = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelOrigen1 = new javax.swing.JLabel();
        jTextFieldMinimoMenores = new javax.swing.JTextField();
        jTextFieldMaximoMenores = new javax.swing.JTextField();
        jLabelOrigen2 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel13.setName(""); // NOI18N

        jLabelFechaRealizacion1.setText("Nombre");

        jLabelOrigen.setText("Edad Parámetro");

        jTextFieldEdadParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEdadParametroActionPerformed(evt);
            }
        });

        jLabelOrigen1.setText("Mínimo Permitido de Menores");

        jTextFieldMinimoMenores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMinimoMenoresActionPerformed(evt);
            }
        });

        jTextFieldMaximoMenores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaximoMenoresActionPerformed(evt);
            }
        });

        jLabelOrigen2.setText("Máximo Permitido de Menores");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelOrigen1)
                    .addComponent(jLabelFechaRealizacion1)
                    .addComponent(jLabelOrigen)
                    .addComponent(jLabelOrigen2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldMinimoMenores)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(jTextFieldEdadParametro, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMaximoMenores))
                .addGap(52, 52, 52))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaRealizacion1)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrigen)
                    .addComponent(jTextFieldEdadParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrigen1)
                    .addComponent(jTextFieldMinimoMenores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrigen2)
                    .addComponent(jTextFieldMaximoMenores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            "Desea eliminar la categoria: " + unaCategoria.getNombre() + " (" + unaCategoria.getEdadParametro()+")?",
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
        modificar=false;
        camposActivoNuevo(true);
        this.jTextFieldNombre.setText("");
        this.jTextFieldEdadParametro.setText("");
        this.jTextFieldMinimoMenores.setText("");
        this.jTextFieldMaximoMenores.setText("");
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (!modificar) {
            unaControladoraGlobal.crearCategoria(jTextFieldNombre.getText(), Integer.parseInt(jTextFieldEdadParametro.getText()), Integer.parseInt(jTextFieldMinimoMenores.getText()), Integer.parseInt(jTextFieldMaximoMenores.getText()));
            JOptionPane.showMessageDialog(this, "Categoria Guardada");
        } else {
            unaCategoria = unaControladoraGlobal.buscarCategoriaBD((Long) jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 0));
            unaControladoraGlobal.modificarCategoria(unaCategoria,jTextFieldNombre.getText(), Integer.parseInt(jTextFieldEdadParametro.getText()), Integer.parseInt(jTextFieldMinimoMenores.getText()), Integer.parseInt(jTextFieldMaximoMenores.getText()), false);
   
        }
        camposActivo(false);
        //camposActivoNuevo(false);        
        this.jTextFieldNombre.setText("");
        this.jTextFieldEdadParametro.setText("");
        this.jTextFieldMinimoMenores.setText("");
        this.jTextFieldMaximoMenores.setText("");
        cargarTabla();
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
        jTextFieldNombre.setEnabled(Editable);
        jTextFieldEdadParametro.setEnabled(Editable);
        jTextFieldMinimoMenores.setEnabled(Editable);
        jTextFieldMaximoMenores.setEnabled(Editable);
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
    private javax.swing.JLabel jLabelFechaRealizacion1;
    private javax.swing.JLabel jLabelOrigen;
    private javax.swing.JLabel jLabelOrigen1;
    private javax.swing.JLabel jLabelOrigen2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCategoria;
    private javax.swing.JTextField jTextFieldEdadParametro;
    private javax.swing.JTextField jTextFieldMaximoMenores;
    private javax.swing.JTextField jTextFieldMinimoMenores;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
