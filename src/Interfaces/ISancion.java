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
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class ISancion extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private Socia unaSocia;
    private ControladoraGlobal unaControladoraGlobal;

    private boolean modificar = false;
    private DefaultTableModel modeloTableSancion;

    //LLAMADO MOSTRANDO UNA SOCIA
    public ISancion(JInternalFrame unJInternalFrame, Socia unaSocia, ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;
        this.unaControladoraGlobal = unaControladoraGlobal;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/sanciones.png")));
        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());
        IMenuPrincipalInterface.centrar(this);

        activarCampos(false);

        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);

        this.modeloTableSancion = (DefaultTableModel) jTableSancion.getModel();
        cargarCamposTabla();
    }

    private void activarCampos(Boolean editable) {
        this.jTextAreaDetalle.setEditable(editable);
        this.jTextFieldCantFechasACumplir.setEditable(editable);
        this.jTextFieldFecha.setEditable(editable);
        this.jTextFieldHastaUnaFecha.setEditable(editable);
        this.jTextFieldMotivo.setEditable(editable);
        this.jTextFieldNumResolucion.setEditable(editable);
    }

    public void camposLimpiar() {
        this.jTextFieldFecha.setText("");
        this.jTextFieldNumResolucion.setText("");
        this.jTextFieldCantFechasACumplir.setText("");
        this.jTextFieldHastaUnaFecha.setText("");
        this.jTextFieldMotivo.setText("");
        this.jTextAreaDetalle.setText("");
    }

    public void camposCargar(SancionTribunal unaSancion) {
        DateFormat df = DateFormat.getDateInstance();
        this.jTextFieldFecha.setText(df.format(unaSancion.getFecha()));
        this.jTextFieldNumResolucion.setText(unaSancion.getNumeroResolucion());
        this.jTextFieldCantFechasACumplir.setText(String.valueOf(unaSancion.getCantFechas()));
        this.jTextFieldHastaUnaFecha.setText(df.format(unaSancion.getVencimiento()));
        this.jTextFieldMotivo.setText(unaSancion.getMotivo());
        this.jTextAreaDetalle.setText(unaSancion.getDetalles());

        jButtonEliminar.setEnabled(true);
        jButtonEditar.setEnabled(true);
        jButtonImprimir.setEnabled(true);
    }

    public void cargarCamposTabla() {
        limpiarTabla(modeloTableSancion);

        for (SancionTribunal aux : this.unaSocia.getSancionesTribunal()) {
            SancionTribunal unaSancion = (SancionTribunal) aux;
            DateFormat df = DateFormat.getDateInstance();
            if (!unaSancion.isBorradoLogico()) {
                this.modeloTableSancion.addRow(new Object[]{unaSancion.getIdSancionTribunal(), 
                    df.format(unaSancion.getFecha()),
                    unaSancion.getMotivo(),
                    unaSancion.getNumeroResolucion(),
                    unaSancion.getCantFechas(),
                    unaSancion.getCantFechasCumplidas(),
                    unaSancion.getVencimiento(),
                    unaSancion.getUnPartido()});
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
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSancion = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelDetalle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDetalle = new javax.swing.JTextArea();
        jTextFieldFecha = new javax.swing.JTextField();
        jLabel1NumResolucion = new javax.swing.JLabel();
        jTextFieldNumResolucion = new javax.swing.JTextField();
        jLabelCantFechasACumplir = new javax.swing.JLabel();
        jLabelHastaUnaFecha = new javax.swing.JLabel();
        jTextFieldCantFechasACumplir = new javax.swing.JTextField();
        jTextFieldHastaUnaFecha = new javax.swing.JTextField();
        jLabelMotivo = new javax.swing.JLabel();
        jTextFieldMotivo = new javax.swing.JTextField();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 544));
        setMinimumSize(new java.awt.Dimension(650, 544));
        setPreferredSize(new java.awt.Dimension(650, 544));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableSancion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Sancion", "Fecha", "Motivo", "N° de Resolución", "Cantidad de Fecha", "Fechas Cumplidas", "Fecha Vencimiento", "Partido"
            }
        ));
        jTableSancion.getSelectionModel () .addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                SancionTribunal unaSancion = unaControladoraGlobal.getSancionTribunalBD((Long) jTableSancion.getValueAt(jTableSancion.getSelectedRow(), 0));

                camposCargar(unaSancion);
            }
        }

    );
    jTableSancion.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            jTableSancionFocusGained(evt);
        }
    });
    jScrollPane1.setViewportView(jTableSancion);
    if (jTableSancion.getColumnModel().getColumnCount() > 0) {
        jTableSancion.getColumnModel().getColumn(0).setMinWidth(0);
        jTableSancion.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableSancion.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
    jPanelTabla.setLayout(jPanelTablaLayout);
    jPanelTablaLayout.setHorizontalGroup(
        jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1)
    );
    jPanelTablaLayout.setVerticalGroup(
        jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
    );

    jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
    jPanelDetalles.setName(""); // NOI18N

    jLabelFecha.setText("Fecha");

    jLabelDetalle.setText("Detalle");

    jTextAreaDetalle.setColumns(20);
    jTextAreaDetalle.setRows(5);
    jScrollPane2.setViewportView(jTextAreaDetalle);

    jLabel1NumResolucion.setText("N° de Resolución");

    jLabelCantFechasACumplir.setText("Cantidad de Fechas a Cumplir");

    jLabelHastaUnaFecha.setText("Hasta una fecha");

    jLabelMotivo.setText("Motivo");

    javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
    jPanelDetalles.setLayout(jPanelDetallesLayout);
    jPanelDetallesLayout.setHorizontalGroup(
        jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelDetallesLayout.createSequentialGroup()
            .addGap(80, 80, 80)
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabelDetalle)
                .addComponent(jLabelMotivo)
                .addComponent(jLabel1NumResolucion)
                .addComponent(jLabelFecha)
                .addComponent(jLabelCantFechasACumplir)
                .addComponent(jLabelHastaUnaFecha))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCantFechasACumplir)
                    .addComponent(jTextFieldHastaUnaFecha)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jTextFieldNumResolucion)
                    .addComponent(jTextFieldMotivo))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanelDetallesLayout.setVerticalGroup(
        jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelFecha)
                .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1NumResolucion)
                .addComponent(jTextFieldNumResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelCantFechasACumplir)
                .addComponent(jTextFieldCantFechasACumplir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelHastaUnaFecha)
                .addComponent(jTextFieldHastaUnaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelMotivo)
                .addComponent(jTextFieldMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelDetalle)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
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
            .addComponent(jPanelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        }
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        modificar = false;

        jTableSancion.clearSelection();
        jTableSancion.setEnabled(false);
        activarCampos(true);
        camposLimpiar();

        //Comportamiento Botones
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        DateFormat df = DateFormat.getDateInstance();
        try {
            Date fecha = new java.sql.Date(df.parse(jTextFieldFecha.getText()).getTime());
            Date fechaCaducidad = new java.sql.Date(df.parse(jTextFieldHastaUnaFecha.getText()).getTime());
            if (!modificar) {
                unaControladoraGlobal.crearSancionTribunal(null, unaSocia, fecha, jTextFieldMotivo.getText(), jTextAreaDetalle.getText());                
                JOptionPane.showMessageDialog(this, "Sancion Creada");
            } else {
                SancionTribunal unaSancion = unaControladoraGlobal.getSancionTribunalBD((Long) jTableSancion.getValueAt(jTableSancion.getSelectedRow(), 0));
                
                unaControladoraGlobal.modificarSancionTribunal(
                        unaSancion, 
                        fechaCaducidad, 
                        Integer.parseInt(jTextFieldCantFechasACumplir.getText()), 
                        fecha, jTextFieldMotivo.getText(), jTextAreaDetalle.getText(), 
                        unaSancion.getUnaTarjeta(), 
                        unaSancion.getUnPartido(), 
                        Integer.parseInt(jTextFieldCantFechasACumplir.getText()), 
                        jTextFieldNumResolucion.getText(), 
                        false
                );
                JOptionPane.showMessageDialog(this, "Sancion Modificada");
            }
        } catch (ParseException e) {
            System.out.println("Error en el formato de las fechas. dd/mm/aaaa " + e.getMessage());
        }

        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(true);
        jButtonImprimir.setEnabled(false);

        camposLimpiar();
        activarCampos(false);
        jTableSancion.setEnabled(true);
        cargarCamposTabla();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        modificar = true;
        jTableSancion.setEnabled(false);
        activarCampos(true);

        SancionTribunal unaSancion = unaControladoraGlobal.getSancionTribunalBD((Long) jTableSancion.getValueAt(jTableSancion.getSelectedRow(), 0));

        DateFormat df = DateFormat.getDateInstance();
        this.jTextFieldFecha.setText(df.format(unaSancion.getFecha()));
        this.jTextFieldNumResolucion.setText(unaSancion.getNumeroResolucion());
        this.jTextFieldCantFechasACumplir.setText(String.valueOf(unaSancion.getCantFechas()));
        this.jTextFieldHastaUnaFecha.setText(df.format(unaSancion.getVencimiento()));
        this.jTextFieldMotivo.setText(unaSancion.getMotivo());
        this.jTextAreaDetalle.setText(unaSancion.getDetalles());

        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jTableSancionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableSancionFocusGained
            /*SancionTribunal unaSancion = unaControladoraGlobal.getSancionTribunalBD((Long) jTableSancion.getValueAt(jTableSancion.getSelectedRow(), 0));              
            if(unaSancion != null){
            camposCargar(unaSancion);}*/
    }//GEN-LAST:event_jTableSancionFocusGained

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jTableSancion.clearSelection();
        
        modificar = false;

        camposLimpiar();
        activarCampos(false);
        jTableSancion.setEnabled(true);

        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
            SancionTribunal unaSancion = unaControladoraGlobal.getSancionTribunalBD((Long) jTableSancion.getValueAt(jTableSancion.getSelectedRow(), 0));
                
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
            unaControladoraGlobal.eliminarSancionTribunal(unaSancion);
            
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
    private javax.swing.JLabel jLabel1NumResolucion;
    private javax.swing.JLabel jLabelCantFechasACumplir;
    private javax.swing.JLabel jLabelDetalle;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelHastaUnaFecha;
    private javax.swing.JLabel jLabelMotivo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableSancion;
    private javax.swing.JTextArea jTextAreaDetalle;
    private javax.swing.JTextField jTextFieldCantFechasACumplir;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldHastaUnaFecha;
    private javax.swing.JTextField jTextFieldMotivo;
    private javax.swing.JTextField jTextFieldNumResolucion;
    // End of variables declaration//GEN-END:variables
}
