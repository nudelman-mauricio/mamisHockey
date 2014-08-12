/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.ConceptoEgreso;
import logicaNegocios.Egreso;
import main.ControladoraGlobal;

/**
 *
 * @author Lucas
 */
public class IGestionEgresos extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaGestionEgresos;
    private Egreso unEgresoSeleccionado;
    private DefaultComboBoxModel unModeloComboConceptoEgreso;
    private DateFormat df = DateFormat.getDateInstance();
    private String unaFecha;
    private String[] unaFechaDividida;

    /**
     * Creates new form GestionEgresos
     */
    public IGestionEgresos(JDesktopPane unjDesktopPanel, ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        this.modeloTablaGestionEgresos = (DefaultTableModel) jTableEgresos.getModel();
        this.unaControladoraGlobal = unaControladoraGlobal;

        IMenuPrincipalInterface.centrar(this);
        camposActivo(false);

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png")));
        this.setTitle("Gestión de Egresos");//titulo de la ventana
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonGuardar.setEnabled(false);

        if(unaControladoraGlobal.getIngresosOtrosBD().size()>0){
        cargarFechasFiltrado();
        cargarTabla();}
    }

    private void cargarTabla() {
        limpiarTabla();
        String desde = "01/" + String.valueOf(jComboBoxDesdeMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxDesdeAño.getSelectedIndex() + 2010);
        String hasta = "01/" + String.valueOf(jComboBoxHastaMes.getSelectedIndex() + 2) + "/" + String.valueOf(jComboBoxHastaAño.getSelectedIndex() + 2010);
        Date fechaHasta = null;
        Date fechaDesde = null;
        try {
            fechaDesde = new java.sql.Date(df.parse(String.valueOf(desde)).getTime());
            fechaHasta = new java.sql.Date(df.parse(String.valueOf(hasta)).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(IGestionEgresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Egreso> unaListaResultado = this.unaControladoraGlobal.getEgresosEntreFechas(fechaDesde, fechaHasta);
        for (Egreso unEgreso : unaListaResultado) {
            this.modeloTablaGestionEgresos.addRow(new Object[]{unEgreso.getIdEgreso(), df.format(unEgreso.getFecha()), unEgreso.getUnConceptoEgreso(), unEgreso.getObservacion(), unEgreso.getMonto()});
        }
    }

    private void limpiarTabla() {
        int filas = this.modeloTablaGestionEgresos.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaGestionEgresos.removeRow(0);
        }
    }

    public void camposActivo(boolean Editable) {
        jTextFieldMonto.setEditable(Editable);
        jTextFieldFecha.setEditable(Editable);
        jTextPaneDetalle.setEditable(Editable);
        jComboBoxConceptoEgreso.setEnabled(Editable);
    }

    //blanquea componentes editables
    void camposLimpiar() {
        jTextPaneDetalle.setText("");
        jTextFieldFecha.setText("dd/mm/aaaa");
        jTextFieldMonto.setText("");
        jComboBoxConceptoEgreso.setSelectedIndex(-1);
    }

    public void cargarComboBoxConceptoEgreso() {
        this.unModeloComboConceptoEgreso = new DefaultComboBoxModel((Vector) this.unaControladoraGlobal.getConceptosEgresosBD());
        this.jComboBoxConceptoEgreso.setModel(unModeloComboConceptoEgreso);
        this.jComboBoxConceptoEgreso.setSelectedIndex(-1);
    }

    public void cargarFechasFiltrado() {
        if (unaControladoraGlobal.getPrimerIngresoOtro() != null) {
            String fecha = df.format(unaControladoraGlobal.getPrimerEgreso().getFecha());
            String[] fechaDividida = fecha.split("/");
            jComboBoxDesdeMes.setSelectedIndex(Integer.parseInt(fechaDividida[1]) - 1);
            jComboBoxDesdeAño.setSelectedIndex(Integer.parseInt(fechaDividida[2]) + 1 - Integer.parseInt(jComboBoxDesdeAño.getItemAt(1).toString()));

            fecha = df.format(unaControladoraGlobal.getUltimoEgreso().getFecha());
            fechaDividida = fecha.split("/");
            jComboBoxHastaMes.setSelectedIndex(Integer.parseInt(fechaDividida[1]) - 1);
            jComboBoxHastaAño.setSelectedIndex(Integer.parseInt(fechaDividida[2]) + 1 - Integer.parseInt(jComboBoxDesdeAño.getItemAt(1).toString()));
        }
    }

    //actualizar campos al seleccionar en la tabla
    void camposCargar() {
        if (jTableEgresos.getSelectedRow() > -1) {
            if (jTableEgresos.getValueAt(jTableEgresos.getSelectedRow(), 0) != null) {
                unEgresoSeleccionado = unaControladoraGlobal.getEgresoBD((Long) jTableEgresos.getValueAt(jTableEgresos.getSelectedRow(), 0));

                camposLimpiar();

                jTextFieldFecha.setText(df.format(unEgresoSeleccionado.getFecha()));
                jTextFieldMonto.setText(String.valueOf(unEgresoSeleccionado.getMonto()));
                jTextPaneDetalle.setText(unEgresoSeleccionado.getObservacion());
                jComboBoxConceptoEgreso.setSelectedItem(unEgresoSeleccionado.getUnConceptoEgreso());

                camposActivo(false);
                jButtonEditar.setEnabled(true);
                jButtonEliminar.setEnabled(true);
            }
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
        jTableEgresos = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabelFechaRealizacion = new javax.swing.JLabel();
        jLabelFechaCaducidad = new javax.swing.JLabel();
        jLabelComentario = new javax.swing.JLabel();
        jTextFieldFecha = new javax.swing.JTextField();
        jTextFieldMonto = new javax.swing.JTextField();
        jLabelFechaCaducidad1 = new javax.swing.JLabel();
        jComboBoxConceptoEgreso = new javax.swing.JComboBox();
        jButtonNuevoEgreso = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneDetalle = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxDesdeMes = new javax.swing.JComboBox();
        jComboBoxDesdeAño = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxHastaMes = new javax.swing.JComboBox();
        jComboBoxHastaAño = new javax.swing.JComboBox();

        setClosable(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
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
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTableEgresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Concepto Egreso", "Observación", "Monto"
            }
        ));
        jScrollPane1.setViewportView(jTableEgresos);
        if (jTableEgresos.getColumnModel().getColumnCount() > 0) {
            jTableEgresos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEgresos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEgresos.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableEgresos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel7.setName(""); // NOI18N

        jLabelFechaRealizacion.setText("Fecha");

        jLabelFechaCaducidad.setText("Concepto Egreso");

        jLabelComentario.setText("Observación");

        jTextFieldFecha.setText("dd/mm/aaaa");

        jLabelFechaCaducidad1.setText("Monto");

        jButtonNuevoEgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Nuevo2.png"))); // NOI18N
        jButtonNuevoEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoEgresoActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jTextPaneDetalle);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFechaCaducidad1)
                    .addComponent(jLabelFechaCaducidad)
                    .addComponent(jLabelFechaRealizacion)
                    .addComponent(jLabelComentario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxConceptoEgreso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNuevoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaRealizacion)
                    .addComponent(jTextFieldFecha))
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFechaCaducidad)
                        .addComponent(jComboBoxConceptoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonNuevoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMonto)
                    .addComponent(jLabelFechaCaducidad1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabelComentario)
                        .addGap(0, 57, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Desde"));

        jLabel1.setText("Año");

        jLabel2.setText("Mes");

        jComboBoxDesdeMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Julio", "Junio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxDesdeMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDesdeMesItemStateChanged(evt);
            }
        });

        jComboBoxDesdeAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015" }));
        jComboBoxDesdeAño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDesdeAñoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDesdeMes, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDesdeAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxDesdeAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxDesdeMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hasta"));

        jLabel7.setText("Año");

        jLabel8.setText("Mes");

        jComboBoxHastaMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Julio", "Junio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxHastaMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxHastaMesItemStateChanged(evt);
            }
        });

        jComboBoxHastaAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015" }));
        jComboBoxHastaAño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxHastaAñoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxHastaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxHastaAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxHastaAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxHastaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableEgresos.setEnabled(false);

        camposActivo(true);
        camposLimpiar();
        cargarComboBoxConceptoEgreso();
        unEgresoSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonNuevoEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoEgresoActionPerformed
        IConceptoEgresos unaIConceptoEgresos = new IConceptoEgresos(this, unaControladoraGlobal);
        unaIConceptoEgresos.pack();
        unaIConceptoEgresos.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaIConceptoEgresos);
    }//GEN-LAST:event_jButtonNuevoEgresoActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cargarComboBoxConceptoEgreso();
    }//GEN-LAST:event_formComponentShown

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed

        if (!(jTextFieldFecha.getText().isEmpty() && jTextFieldMonto.getText().isEmpty() && jComboBoxConceptoEgreso.getSelectedIndex() != -1)) {
            if (unEgresoSeleccionado == null) {
                try {
                    unaControladoraGlobal.crearEgreso(
                            new java.sql.Date(df.parse(jTextFieldFecha.getText()).getTime()),
                            Integer.parseInt(jTextFieldMonto.getText()),
                            (ConceptoEgreso) jComboBoxConceptoEgreso.getSelectedItem(),
                            jTextPaneDetalle.getText());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Error en el formato de la fecha. Por favor, ingrese la fecha con el siguiente formato: dd/mm/aaaa");
                }
                JOptionPane.showMessageDialog(this, "Egreso Guardada");
            } else {
                try {
                    unaControladoraGlobal.modificarEgreso(
                            unEgresoSeleccionado,
                            new java.sql.Date(df.parse(jTextFieldFecha.getText()).getTime()),
                            Integer.parseInt(jTextFieldMonto.getText()),
                            (ConceptoEgreso) jComboBoxConceptoEgreso.getSelectedItem(),
                            jTextPaneDetalle.getText(),
                            false);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Error en el formato de la fecha. Por favor, ingrese la fecha con el siguiente formato: dd/mm/aaaa");
                }
                unEgresoSeleccionado = null;
                JOptionPane.showMessageDialog(this, "Egreso Modificada");
            }
            jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);

            camposActivo(false);
            camposLimpiar();
            cargarFechasFiltrado();
            cargarTabla();
            jTableEgresos.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "El campo nombre es obligatorio");
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableEgresos.setEnabled(false);

        camposActivo(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);

        jTableEgresos.setEnabled(true);

        camposActivo(false);
        camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);

        jTableEgresos.setEnabled(false);

        camposActivo(false);
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el egreso: " + unEgresoSeleccionado.getFecha() + " - " + unEgresoSeleccionado.getUnConceptoEgreso() + " - " + unEgresoSeleccionado.getMonto(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarEgreso(unEgresoSeleccionado);
            unEgresoSeleccionado = null;
            cargarTabla();
        }
        jTableEgresos.clearSelection();
        jTableEgresos.setEnabled(true);
        camposLimpiar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jComboBoxDesdeMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDesdeMesItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jComboBoxDesdeMesItemStateChanged

    private void jComboBoxDesdeAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDesdeAñoItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jComboBoxDesdeAñoItemStateChanged

    private void jComboBoxHastaMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHastaMesItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jComboBoxHastaMesItemStateChanged

    private void jComboBoxHastaAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHastaAñoItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jComboBoxHastaAñoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonNuevoEgreso;
    private javax.swing.JComboBox jComboBoxConceptoEgreso;
    private javax.swing.JComboBox jComboBoxDesdeAño;
    private javax.swing.JComboBox jComboBoxDesdeMes;
    private javax.swing.JComboBox jComboBoxHastaAño;
    private javax.swing.JComboBox jComboBoxHastaMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelComentario;
    private javax.swing.JLabel jLabelFechaCaducidad;
    private javax.swing.JLabel jLabelFechaCaducidad1;
    private javax.swing.JLabel jLabelFechaRealizacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableEgresos;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldMonto;
    private javax.swing.JTextPane jTextPaneDetalle;
    // End of variables declaration//GEN-END:variables
}
