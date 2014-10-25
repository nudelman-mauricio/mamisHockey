package Interfaces;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Cancha;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.Deuda;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;

public class IContabilidadEquipo extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Equipo unEquipo;
    private Deuda unaDeudaSeleccionada;
    private DefaultTableModel modeloTableDeudas;
    private DateFormat df = DateFormat.getDateInstance();

    public IContabilidadEquipo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Equipo unEquipo) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unEquipo = unEquipo;
        this.modeloTableDeudas = (DefaultTableModel) jTableDeudas.getModel();

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png"))); //Icono de la ventana
        this.setTitle("Contabilidad de: " + this.unEquipo.getNombre()); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar

        Vector<ConceptoDeportivo> conceptosEquipo = new Vector<ConceptoDeportivo>();
        conceptosEquipo.add(unaControladoraGlobal.getConceptoDeportivoBD("Cancha"));
        conceptosEquipo.add(unaControladoraGlobal.getConceptoDeportivoBD("Seguro Técnicos"));
        DefaultComboBoxModel modelComboConcepto = new DefaultComboBoxModel(conceptosEquipo);
        this.jComboBoxConcepto.setModel(modelComboConcepto);
        this.jComboBoxConcepto.setSelectedIndex(-1);

        camposActivo(false);

        cargarTabla();
    }

    //Cargar Tabla con las Deudas
    private void cargarTabla() {
        limpiarTabla();
        for (Deuda unaDeuda : unEquipo.getDeudas()) {
            if (!unaDeuda.isBorradoLogico()) {
                this.modeloTableDeudas.addRow(new Object[]{unaDeuda.getIdDeuda(), df.format(unaDeuda.getFechaGeneracion()), unaDeuda.getUnConceptoDeportivo().toString(), df.format(unaDeuda.getPrimerVencimiento()), unaDeuda.getMontoTotal(), unaDeuda.isSaldado()});
            }
        }
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }

    private void limpiarTabla() {
        int filas = modeloTableDeudas.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTableDeudas.removeRow(0);
        }
    }

    //actualizar los campos al seleccionar una deuda en la tabla deudas
    private void camposCargar() {
        if (jTableDeudas.getSelectedRow() > -1) {
            if (jTableDeudas.getValueAt(jTableDeudas.getSelectedRow(), 0) != null) {
                unaDeudaSeleccionada = unaControladoraGlobal.getDeudaBD((Long) jTableDeudas.getValueAt(jTableDeudas.getSelectedRow(), 0));

                jDateChooserFecha.setDate(unaDeudaSeleccionada.getFechaGeneracion());
                jComboBoxConcepto.setSelectedItem(unaDeudaSeleccionada.getUnConceptoDeportivo());
                jDateChooserFechaVencimiento.setDate(unaDeudaSeleccionada.getPrimerVencimiento());
                jTextFieldMontoTotalDeuda.setText(Double.toString(unaDeudaSeleccionada.getMontoTotal()));
                jTextPaneObservacionDeuda.setText(unaDeudaSeleccionada.getObservacion());

                jButtonEliminar.setEnabled(true);
                jButtonImprimir.setEnabled(true);
            }
        }
    }

    private void camposActivo(boolean bandera) {
        jDateChooserFecha.setEnabled(bandera);
        jDateChooserFechaVencimiento.setEnabled(bandera);
        jTextFieldMontoTotalDeuda.setEditable(bandera);
        jTextPaneObservacionDeuda.setEditable(bandera);
        jComboBoxConcepto.setEnabled(bandera);
        if (bandera) {
            jTextPaneObservacionDeuda.setBackground(Color.WHITE);
        } else {
            jTextPaneObservacionDeuda.setBackground(new Color(228, 231, 237));
        }
    }

    //blanqueda componentes editables
    private void camposLimpiar() {
        jDateChooserFecha.setDate(null);
        jComboBoxConcepto.setSelectedIndex(-1);
        jDateChooserFechaVencimiento.setDate(null);
        jTextFieldMontoTotalDeuda.setText("");
        jTextPaneObservacionDeuda.setText("");
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jDateChooserFecha.getDate() == null) {
            jLabelFechaRealizacion.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFechaRealizacion.setForeground(Color.black);
        }
        if (jDateChooserFechaVencimiento.getDate() == null) {
            jLabelFechaVencimiento.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFechaVencimiento.setForeground(Color.black);
        }
        if (jTextFieldMontoTotalDeuda.getText().isEmpty()) {
            jLabelMontoTotalDeuda.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMontoTotalDeuda.setForeground(Color.black);
        }
        if (jComboBoxConcepto.getSelectedIndex() == -1) {
            jLabelConcepto.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelConcepto.setForeground(Color.black);
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
        jButtonImprimir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTablaDeudas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDeudas = new javax.swing.JTable();
        jPanelDetalleDeudas = new javax.swing.JPanel();
        jLabelFechaRealizacion = new javax.swing.JLabel();
        jLabelConcepto = new javax.swing.JLabel();
        jLabelFechaVencimiento = new javax.swing.JLabel();
        jLabelFechaRealizacion3 = new javax.swing.JLabel();
        jLabelMontoTotalDeuda = new javax.swing.JLabel();
        jTextFieldMontoTotalDeuda = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneObservacionDeuda = new javax.swing.JTextPane();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jDateChooserFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jComboBoxConcepto = new javax.swing.JComboBox();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(792, 494));
        setMinimumSize(new java.awt.Dimension(792, 494));
        setPreferredSize(new java.awt.Dimension(792, 494));
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

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setEnabled(false);
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
                    .addComponent(jButtonNuevo)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableDeudas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Concepto", "Vencimiento", "Monto", "Deuda Saldada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDeudas);
        if (jTableDeudas.getColumnModel().getColumnCount() > 0) {
            jTableDeudas.getColumnModel().getColumn(0).setMinWidth(0);
            jTableDeudas.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableDeudas.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableDeudas.getColumnModel().getColumn(5).setMinWidth(90);
            jTableDeudas.getColumnModel().getColumn(5).setPreferredWidth(90);
            jTableDeudas.getColumnModel().getColumn(5).setMaxWidth(90);
        }
        jTableDeudas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaDeudasLayout = new javax.swing.GroupLayout(jPanelTablaDeudas);
        jPanelTablaDeudas.setLayout(jPanelTablaDeudasLayout);
        jPanelTablaDeudasLayout.setHorizontalGroup(
            jPanelTablaDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );
        jPanelTablaDeudasLayout.setVerticalGroup(
            jPanelTablaDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaDeudasLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelDetalleDeudas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabelFechaRealizacion.setText("Fecha");

        jLabelConcepto.setText("Concepto");

        jLabelFechaVencimiento.setText("Vencimiento");

        jLabelFechaRealizacion3.setText("Observación");

        jLabelMontoTotalDeuda.setText("Monto ($)");

        jTextFieldMontoTotalDeuda.setEditable(false);

        jTextPaneObservacionDeuda.setEditable(false);
        jScrollPane3.setViewportView(jTextPaneObservacionDeuda);

        jDateChooserFecha.setEnabled(false);

        jDateChooserFechaVencimiento.setEnabled(false);

        jComboBoxConcepto.setEnabled(false);
        jComboBoxConcepto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxConceptoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelDetalleDeudasLayout = new javax.swing.GroupLayout(jPanelDetalleDeudas);
        jPanelDetalleDeudas.setLayout(jPanelDetalleDeudasLayout);
        jPanelDetalleDeudasLayout.setHorizontalGroup(
            jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelConcepto)
                    .addComponent(jLabelFechaRealizacion)
                    .addComponent(jLabelMontoTotalDeuda)
                    .addComponent(jLabelFechaVencimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMontoTotalDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooserFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jDateChooserFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxConcepto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addComponent(jLabelFechaRealizacion3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        jPanelDetalleDeudasLayout.setVerticalGroup(
            jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDetalleDeudasLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelFechaRealizacion)
                                .addComponent(jLabelFechaRealizacion3))
                            .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelConcepto)
                            .addComponent(jComboBoxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaVencimiento)
                            .addComponent(jDateChooserFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMontoTotalDeuda)
                            .addComponent(jTextFieldMontoTotalDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelDetalleDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTablaDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTablaDeudas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalleDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);

        camposActivo(false);

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar la Deuda del Equipo "
                + unEquipo.toString()
                + ", Por concepto: "
                + unaDeudaSeleccionada.getUnConceptoDeportivo().getConcepto() + " ("
                + unaDeudaSeleccionada.getMontoTotal() + ") ? ",
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarDeuda(unaDeudaSeleccionada);
            cargarTabla();
        }
        unaDeudaSeleccionada = null;
        jTableDeudas.clearSelection();
        camposLimpiar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableDeudas.setEnabled(false);

        camposActivo(true);
        camposLimpiar();
        unaDeudaSeleccionada = null;
        this.jDateChooserFecha.setDate(unaControladoraGlobal.fechaSistema());
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            Date fechaRealizacion = new java.sql.Date((jDateChooserFecha.getDate()).getTime());
            Date fechaVencimiento = new java.sql.Date((jDateChooserFechaVencimiento.getDate()).getTime());
            unaControladoraGlobal.crearDeudaEquipo(unEquipo, fechaRealizacion, (ConceptoDeportivo) jComboBoxConcepto.getSelectedItem(), jTextPaneObservacionDeuda.getText(), Double.parseDouble(jTextFieldMontoTotalDeuda.getText()), 1, fechaVencimiento);
            JOptionPane.showMessageDialog(this, "Deuda Guardada");
            cargarTabla();
            jButtonNuevo.setEnabled(true);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);
            jButtonImprimir.setEnabled(false);
            jTableDeudas.setEnabled(true);
            jTableDeudas.clearSelection();
            unaDeudaSeleccionada = null;
            camposActivo(false);
            camposLimpiar();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);

        jTableDeudas.setEnabled(true);
        jTableDeudas.clearSelection();
        unaDeudaSeleccionada = null;

        camposActivo(false);
        camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jComboBoxConceptoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxConceptoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (unaDeudaSeleccionada == null) {
                ConceptoDeportivo unConceptoDeportivoSeleccionado = (ConceptoDeportivo) jComboBoxConcepto.getSelectedItem();
                jTextFieldMontoTotalDeuda.setText(Double.toString(unConceptoDeportivoSeleccionado.getMonto()));
                if (unConceptoDeportivoSeleccionado.getConcepto().equals("Cancha")) {
                    //cargar campos de ayuda                                        
                    int mes = unaControladoraGlobal.fechaSistema().getMonth(), anio = unaControladoraGlobal.fechaSistema().getYear();
                    String datosCanchas = "Canchas del Equipo:", enter = System.getProperty("line.separator");
                    for (Cancha unaCancha : unaControladoraGlobal.getClubBD(unEquipo).getCanchas()) {
                        datosCanchas += (enter + unaCancha.toString() + " - Usos en el mes actual: " + Integer.toString(unaControladoraGlobal.getCantCanchaOcupadaEnMes(unaCancha, mes, anio)));
                    }
                    jTextPaneObservacionDeuda.setText(datosCanchas);
                }
            }
        }
    }//GEN-LAST:event_jComboBoxConceptoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JComboBox jComboBoxConcepto;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private com.toedter.calendar.JDateChooser jDateChooserFechaVencimiento;
    private javax.swing.JLabel jLabelConcepto;
    private javax.swing.JLabel jLabelFechaRealizacion;
    private javax.swing.JLabel jLabelFechaRealizacion3;
    private javax.swing.JLabel jLabelFechaVencimiento;
    private javax.swing.JLabel jLabelMontoTotalDeuda;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalleDeudas;
    private javax.swing.JPanel jPanelTablaDeudas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableDeudas;
    private javax.swing.JTextField jTextFieldMontoTotalDeuda;
    private javax.swing.JTextPane jTextPaneObservacionDeuda;
    // End of variables declaration//GEN-END:variables
}
