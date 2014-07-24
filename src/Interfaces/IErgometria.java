package Interfaces;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Ergometria;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IErgometria extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Socia unaSocia;

    private boolean modificar = false;
    private DefaultTableModel modeloTableErgometrias;

    //LLAMADO MOSTRANDO UNA SOCIA
    public IErgometria(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/corazon.png")));
        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());
        IMenuPrincipalInterface.centrar(this);

        camposActivo(false);

        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
        
        this.modeloTableErgometrias = (DefaultTableModel) jTableErgometrias.getModel();
        cargarCamposTabla();
    }

    public void camposActivo(boolean Editable) {
        jTextFieldFechaRealizacion.setEditable(Editable);
        jTextFieldFechaCaducidad.setEditable(Editable);
        jCheckBoxEgometriaAprobada.setEnabled(Editable);
        jTextAreaErgometriaComentario.setEditable(Editable);
    }

    public void camposLimpiar() {
        jTextFieldFechaRealizacion.setText("");
        jTextFieldFechaCaducidad.setText("");
        jCheckBoxEgometriaAprobada.setSelected(false);
        jTextAreaErgometriaComentario.setText("");
    }

    public void camposCargar(Ergometria unaErgometria) {
        DateFormat df = DateFormat.getDateInstance();
        jTextFieldFechaRealizacion.setText(df.format(unaErgometria.getFechaRealizacion()));
        jTextFieldFechaCaducidad.setText(df.format(unaErgometria.getFechaCaducidad()));
        jCheckBoxEgometriaAprobada.setSelected(unaErgometria.isAprobado());
        jTextAreaErgometriaComentario.setText(unaErgometria.getComentarios());
        
        jButtonEliminar.setEnabled(true);
        jButtonEditar.setEnabled(true);
        jButtonImprimir.setEnabled(true);
    }
    
    public void cargarCamposTabla() {
        limpiarTabla(modeloTableErgometrias);

        for (Ergometria aux : this.unaSocia.getErgometrias()) {
            Ergometria unaErgometria = (Ergometria) aux;
            DateFormat df = DateFormat.getDateInstance();
            if (!unaErgometria.isBorradoLogico()){
                this.modeloTableErgometrias.addRow(new Object[]{unaErgometria.getIdErgometria(), df.format(unaErgometria.getFechaRealizacion()), df.format(unaErgometria.getFechaCaducidad()), unaErgometria.isAprobado()});
            }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableErgometrias = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabelFechaRealizacion = new javax.swing.JLabel();
        jLabelFechaCaducidad = new javax.swing.JLabel();
        jCheckBoxEgometriaAprobada = new javax.swing.JCheckBox();
        jLabelComentario = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaErgometriaComentario = new javax.swing.JTextArea();
        jTextFieldFechaRealizacion = new javax.swing.JTextField();
        jTextFieldFechaCaducidad = new javax.swing.JTextField();

        setClosable(true);
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
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar))
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableErgometrias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Ergometria", "Fecha Realización", "Fecha Caducidad", "Aprobado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableErgometrias.getSelectionModel () .addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                Ergometria unaErgometria = unaControladoraGlobal.getErgometriaBD((Long) jTableErgometrias.getValueAt(jTableErgometrias.getSelectedRow(), 0));

                camposCargar(unaErgometria);
            }
        }

    );
    jTableErgometrias.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            jTableErgometriasFocusGained(evt);
        }
    });
    jScrollPane1.setViewportView(jTableErgometrias);
    if (jTableErgometrias.getColumnModel().getColumnCount() > 0) {
        jTableErgometrias.getColumnModel().getColumn(0).setMinWidth(0);
        jTableErgometrias.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableErgometrias.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addComponent(jScrollPane1)
            .addGap(3, 3, 3))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
    jPanel7.setName(""); // NOI18N

    jLabelFechaRealizacion.setText("Fecha de Realización");

    jLabelFechaCaducidad.setText("Fecha de Caducidad");

    jCheckBoxEgometriaAprobada.setText("Aprobado");
    jCheckBoxEgometriaAprobada.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCheckBoxEgometriaAprobadaActionPerformed(evt);
        }
    });

    jLabelComentario.setText("Comentario");

    jTextAreaErgometriaComentario.setColumns(20);
    jTextAreaErgometriaComentario.setRows(5);
    jScrollPane2.setViewportView(jTextAreaErgometriaComentario);

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabelFechaRealizacion)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldFechaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                            .addComponent(jLabelFechaCaducidad)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jCheckBoxEgometriaAprobada))
                        .addComponent(jScrollPane2))
                    .addGap(24, 24, 24))
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addComponent(jLabelComentario)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelFechaRealizacion)
                .addComponent(jLabelFechaCaducidad)
                .addComponent(jCheckBoxEgometriaAprobada)
                .addComponent(jTextFieldFechaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextFieldFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabelComentario)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
            .addGap(3, 3, 3))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 3, Short.MAX_VALUE))
        .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 542, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jCheckBoxEgometriaAprobadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEgometriaAprobadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEgometriaAprobadaActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        }
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        modificar = false;

        jTableErgometrias.clearSelection();
        jTableErgometrias.setEnabled(false);
        camposActivo(true);
        camposLimpiar();

        //Comportamiento Botones
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        modificar = true;
        jTableErgometrias.setEnabled(false);
        camposActivo(true);

        Ergometria unaErgometria = unaControladoraGlobal.getErgometriaBD((Long) jTableErgometrias.getValueAt(jTableErgometrias.getSelectedRow(), 0));

        DateFormat df = DateFormat.getDateInstance();
        jTextFieldFechaRealizacion.setText(df.format(unaErgometria.getFechaRealizacion()));
        jTextFieldFechaCaducidad.setText(df.format(unaErgometria.getFechaCaducidad()));
        jCheckBoxEgometriaAprobada.setSelected(unaErgometria.isAprobado());
        jTextAreaErgometriaComentario.setText(unaErgometria.getComentarios());

        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jTableErgometriasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableErgometriasFocusGained
            Ergometria unaErgometria = unaControladoraGlobal.getErgometriaBD((Long) jTableErgometrias.getValueAt(jTableErgometrias.getSelectedRow(), 0));  
            
            camposCargar(unaErgometria);

//        jButtonEliminar.setEnabled(true);
//        jButtonEditar.setEnabled(true);
//        jButtonImprimir.setEnabled(true);
    }//GEN-LAST:event_jTableErgometriasFocusGained

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        DateFormat df = DateFormat.getDateInstance();
        try {
            Date fechaRealizacion = new java.sql.Date(df.parse(jTextFieldFechaRealizacion.getText()).getTime());
            Date fechaCaducidad = new java.sql.Date(df.parse(jTextFieldFechaCaducidad.getText()).getTime());
            if (!modificar) {
                unaControladoraGlobal.crearErgometria(unaSocia, fechaCaducidad, fechaRealizacion, jCheckBoxEgometriaAprobada.isSelected(), jTextAreaErgometriaComentario.getText());
                JOptionPane.showMessageDialog(this, "Nuevo Ergometria Guardada");
            } else {
                Ergometria unaErgometria = unaControladoraGlobal.getErgometriaBD((Long) jTableErgometrias.getValueAt(jTableErgometrias.getSelectedRow(), 0));
                unaControladoraGlobal.modificarErgometria(unaErgometria, fechaCaducidad, fechaRealizacion, jCheckBoxEgometriaAprobada.isSelected(), jTextAreaErgometriaComentario.getText(), false);
                JOptionPane.showMessageDialog(this, "Ergometria Modificada");
            }
        } catch (ParseException e) {
            System.out.println("ERROR EN LAS FECHAS" + e.getMessage());
        }

        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(true);
        jButtonImprimir.setEnabled(false);

        camposLimpiar();
        camposActivo(false);
        jTableErgometrias.setEnabled(true);
        cargarCamposTabla();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jTableErgometrias.clearSelection();
        
        modificar = false;

        camposLimpiar();
        camposActivo(false);
        jTableErgometrias.setEnabled(true);

        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        Ergometria unaErgometria = unaControladoraGlobal.getErgometriaBD((Long) jTableErgometrias.getValueAt(jTableErgometrias.getSelectedRow(), 0));
                
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
            unaControladoraGlobal.eliminarErgometria(unaErgometria);
            
            cargarCamposTabla();
        }    
    }//GEN-LAST:event_jButtonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxEgometriaAprobada;
    private javax.swing.JLabel jLabelComentario;
    private javax.swing.JLabel jLabelFechaCaducidad;
    private javax.swing.JLabel jLabelFechaRealizacion;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableErgometrias;
    private javax.swing.JTextArea jTextAreaErgometriaComentario;
    private javax.swing.JTextField jTextFieldFechaCaducidad;
    private javax.swing.JTextField jTextFieldFechaRealizacion;
    // End of variables declaration//GEN-END:variables
}
