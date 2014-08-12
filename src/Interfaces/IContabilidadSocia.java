package Interfaces;

import java.awt.Component;
import java.awt.Container;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.Cuota;
import logicaNegocios.Deuda;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IContabilidadSocia extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Socia unaSocia;
    private Deuda unaDeudaSeleccionada;
    private Cuota unaCuotaSeleccionada;
    private DefaultTableModel modeloTableDeudas, modeloTableCuotas;
    private DateFormat df;

    public IContabilidadSocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;
        this.modeloTableDeudas = (DefaultTableModel) jTableDeudas.getModel();
        this.modeloTableCuotas = (DefaultTableModel) jTableCuotas.getModel();

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png"))); //Icono de la ventana
        this.setTitle("Contabilidad de: " + this.unaSocia.getNombre()); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar

        DefaultComboBoxModel modelComboConcepto = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getConceptosDeportivosBD());
        this.jComboBoxConcepto.setModel(modelComboConcepto);

        jTabbedPane1.setEnabledAt(1, false);// deshabilita la pestaña de cuotas
        camposActivo(jPanelDetalleDeudas, false);

        cargarTablaDeudas();
        df = DateFormat.getDateInstance();
    }

    //Cargar Tabla con las Deudas de la socia
    public void cargarTablaDeudas() {
        limpiarTabla(modeloTableDeudas);
        for (Deuda unaDeuda : unaSocia.getDeudas()) {
            if (!unaDeuda.isBorradoLogico()) {
                this.modeloTableDeudas.addRow(new Object[]{unaDeuda.getIdDeuda(), df.format(unaDeuda.getFechaGeneracion()), unaDeuda.getUnConceptoDeportivo().getConcepto(), unaDeuda.getCantidadCuotas(), df.format(unaDeuda.getPrimerVencimiento()), unaDeuda.getMontoTotal(), unaDeuda.isSaldado()});
            }
        }
        jButtonEliminar.setEnabled(false);
    }

    //Cargar Tabla con las Cuotas de la Deuda
    public void cargarTablaCuotas() {
        limpiarTabla(modeloTableCuotas);
        for (Cuota unaCuota : unaDeudaSeleccionada.getCuotas()) {
            if (!unaCuota.isBorradoLogico()) {
                this.modeloTableCuotas.addRow(new Object[]{unaCuota.getIdCuota(), unaCuota.getNumero(), df.format(unaCuota.getFechaVencimiento()), unaCuota.getMonto(), df.format(unaCuota.getFechaPago()), unaCuota.getMontoPago()});
            }
        }
        jButtonEliminar.setEnabled(false);
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int filas = modeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTabla.removeRow(0);
        }
    }

    //actualizar los campos al seleccionar una deuda en la tabla deudas
    void camposCargarDeuda() {
        if (jTableDeudas.getSelectedRow() > -1) {
            if (jTableDeudas.getValueAt(jTableDeudas.getSelectedRow(), 0) != null) {
                unaDeudaSeleccionada = unaControladoraGlobal.getDeudaBD((Long) jTableDeudas.getValueAt(jTableDeudas.getSelectedRow(), 0));

                DateFormat df = DateFormat.getDateInstance();
                jTextFieldFechaRealizacion.setText(df.format(unaDeudaSeleccionada.getFechaGeneracion()));
                jComboBoxConcepto.setSelectedItem(unaDeudaSeleccionada);
                jTextFieldFechaVencimiento.setText(df.format(unaDeudaSeleccionada.getPrimerVencimiento()));
                jTextFieldMontoTotalDeuda.setText(Double.toString(unaDeudaSeleccionada.getMontoTotal()));
                jComboBoxCantidadCuotas.setSelectedIndex(unaDeudaSeleccionada.getCantidadCuotas() - 1);
                jTextFieldMontoCuota.setText(Double.toString(unaDeudaSeleccionada.getPrimerMonto()));
                jTextPaneObservacionDeuda.setText(unaDeudaSeleccionada.getObservacion());

                jButtonEliminar.setEnabled(true);
            }
        }
    }

    //actualizar los campos Cuota al seleccionar una Cuota en la tabla Cuotas
    void camposCargarCuota() {
        if (jTableCuotas.getSelectedRow() > -1) {
            if (jTableCuotas.getValueAt(jTableCuotas.getSelectedRow(), 0) != null) {
                unaCuotaSeleccionada = unaControladoraGlobal.getCuotaBD((Long) jTableCuotas.getValueAt(jTableCuotas.getSelectedRow(), 0));

                DateFormat df = DateFormat.getDateInstance();
                if (unaCuotaSeleccionada.getFechaPago() != null) {
                    jTextFieldFechaPagoCuota.setText(df.format(unaCuotaSeleccionada.getFechaPago()));
                }
                jTextFieldMontoCuotaAbonado.setText(Double.toString(unaCuotaSeleccionada.getMontoPago()));
                jTextPaneObservacionPago.setText(unaCuotaSeleccionada.getUnPagoCuota().getObservacion());

                jButtonPagar.setEnabled(true);
            }
        }
    }

    //deshabilitar todo lo de un contenedor
    void camposActivo(Container c, boolean bandera) {
        Component[] components = c.getComponents();
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(bandera);
            if (components[i] instanceof JTextField) {
                ((JTextField) components[i]).setEditable(bandera);
            }
            if (components[i] instanceof Container) {
                camposActivo((Container) components[i], bandera);
            }
        }
    }

    //blanqueda componentes editables de la solapa Deuda
    void camposLimpiarDeuda() {
        jTextFieldFechaRealizacion.setText("");
        jComboBoxConcepto.setSelectedIndex(-1);
        jTextFieldFechaVencimiento.setText("");
        jTextFieldMontoTotalDeuda.setText("");
        jComboBoxCantidadCuotas.setSelectedIndex(-1);
        jTextFieldMontoCuota.setText("");
        jTextPaneObservacionDeuda.setText("");
    }

    //blanqueda componentes editables de la solapa Cuotas
    void camposLimpiarCuotas() {
        jTextFieldFechaPagoCuota.setText("");
        jTextFieldMontoCuotaAbonado.setText("");
        jTextPaneObservacionPago.setText("");
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
        jButtonPagar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelDeudas = new javax.swing.JPanel();
        jPanelTablaDeudas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDeudas = new javax.swing.JTable();
        jPanelDetalleDeudas = new javax.swing.JPanel();
        jLabelFechaRealizacion = new javax.swing.JLabel();
        jTextFieldFechaRealizacion = new javax.swing.JTextField();
        jLabelFechaRealizacion1 = new javax.swing.JLabel();
        jTextFieldFechaVencimiento = new javax.swing.JTextField();
        jLabelFechaRealizacion2 = new javax.swing.JLabel();
        jComboBoxConcepto = new javax.swing.JComboBox();
        jLabelFechaRealizacion3 = new javax.swing.JLabel();
        jLabelFechaRealizacion4 = new javax.swing.JLabel();
        jTextFieldMontoTotalDeuda = new javax.swing.JTextField();
        jLabelFechaRealizacion5 = new javax.swing.JLabel();
        jTextFieldMontoCuota = new javax.swing.JTextField();
        jComboBoxCantidadCuotas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneObservacionDeuda = new javax.swing.JTextPane();
        jPanelCuotas = new javax.swing.JPanel();
        jPanelTablaCuotas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCuotas = new javax.swing.JTable();
        jPanelDetalleCuotas = new javax.swing.JPanel();
        jLabelFechaRealizacion6 = new javax.swing.JLabel();
        jTextFieldFechaPagoCuota = new javax.swing.JTextField();
        jLabelFechaRealizacion10 = new javax.swing.JLabel();
        jTextFieldMontoCuotaAbonado = new javax.swing.JTextField();
        jLabelFechaRealizacion8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPaneObservacionPago = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 534));
        setMinimumSize(new java.awt.Dimension(650, 534));
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

        jButtonPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Pagar.png"))); // NOI18N
        jButtonPagar.setText("Pagar");
        jButtonPagar.setEnabled(false);
        jButtonPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
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
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jTableDeudas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Concepto", "Cant. Cuotas", "1° Vencimiento", "Monto", "Deuda Saldada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDeudas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDeudasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDeudas);
        if (jTableDeudas.getColumnModel().getColumnCount() > 0) {
            jTableDeudas.getColumnModel().getColumn(0).setMinWidth(0);
            jTableDeudas.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableDeudas.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableDeudas.getColumnModel().getColumn(3).setMinWidth(80);
            jTableDeudas.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTableDeudas.getColumnModel().getColumn(3).setMaxWidth(80);
            jTableDeudas.getColumnModel().getColumn(6).setMinWidth(90);
            jTableDeudas.getColumnModel().getColumn(6).setPreferredWidth(90);
            jTableDeudas.getColumnModel().getColumn(6).setMaxWidth(90);
        }
        jTableDeudas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargarDeuda();
            }
        });

        javax.swing.GroupLayout jPanelTablaDeudasLayout = new javax.swing.GroupLayout(jPanelTablaDeudas);
        jPanelTablaDeudas.setLayout(jPanelTablaDeudasLayout);
        jPanelTablaDeudasLayout.setHorizontalGroup(
            jPanelTablaDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        jPanelTablaDeudasLayout.setVerticalGroup(
            jPanelTablaDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanelDetalleDeudas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabelFechaRealizacion.setText("Fecha");

        jLabelFechaRealizacion1.setText("Concepto");

        jLabelFechaRealizacion2.setText("Primer Vencimiento");

        jLabelFechaRealizacion3.setText("Observación");

        jLabelFechaRealizacion4.setText("Monto ($)");

        jLabelFechaRealizacion5.setText("Cuotas");

        jTextFieldMontoCuota.setEditable(false);

        jComboBoxCantidadCuotas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel1.setText("X ($)");

        jScrollPane3.setViewportView(jTextPaneObservacionDeuda);

        javax.swing.GroupLayout jPanelDetalleDeudasLayout = new javax.swing.GroupLayout(jPanelDetalleDeudas);
        jPanelDetalleDeudas.setLayout(jPanelDetalleDeudasLayout);
        jPanelDetalleDeudasLayout.setHorizontalGroup(
            jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFechaRealizacion1)
                    .addComponent(jLabelFechaRealizacion)
                    .addComponent(jLabelFechaRealizacion4)
                    .addComponent(jLabelFechaRealizacion5)
                    .addComponent(jLabelFechaRealizacion2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDetalleDeudasLayout.createSequentialGroup()
                            .addComponent(jComboBoxCantidadCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldMontoCuota))
                        .addComponent(jTextFieldMontoTotalDeuda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFechaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabelFechaRealizacion3))
                    .addComponent(jTextFieldFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetalleDeudasLayout.setVerticalGroup(
            jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelDetalleDeudasLayout.createSequentialGroup()
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaRealizacion)
                            .addComponent(jTextFieldFechaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaRealizacion3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaRealizacion1)
                            .addComponent(jComboBoxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaRealizacion2)
                            .addComponent(jTextFieldFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaRealizacion4)
                            .addComponent(jTextFieldMontoTotalDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalleDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaRealizacion5)
                            .addComponent(jTextFieldMontoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxCantidadCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(jScrollPane3))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelDeudasLayout = new javax.swing.GroupLayout(jPanelDeudas);
        jPanelDeudas.setLayout(jPanelDeudasLayout);
        jPanelDeudasLayout.setHorizontalGroup(
            jPanelDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDeudasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelDetalleDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTablaDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelDeudasLayout.setVerticalGroup(
            jPanelDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeudasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTablaDeudas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalleDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Deudas", jPanelDeudas);

        jTableCuotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Número", "Vencimiento", "Monto", "Fecha de Pago", "Monto Abonado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        jScrollPane2.setViewportView(jTableCuotas);
        if (jTableCuotas.getColumnModel().getColumnCount() > 0) {
            jTableCuotas.getColumnModel().getColumn(0).setMinWidth(0);
            jTableCuotas.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableCuotas.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableCuotas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargarCuota();
            }
        });

        javax.swing.GroupLayout jPanelTablaCuotasLayout = new javax.swing.GroupLayout(jPanelTablaCuotas);
        jPanelTablaCuotas.setLayout(jPanelTablaCuotasLayout);
        jPanelTablaCuotasLayout.setHorizontalGroup(
            jPanelTablaCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        jPanelTablaCuotasLayout.setVerticalGroup(
            jPanelTablaCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );

        jPanelDetalleCuotas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabelFechaRealizacion6.setText("Fecha de Pago");

        jLabelFechaRealizacion10.setText("Monto Abonado ($)");

        jLabelFechaRealizacion8.setText("Observación");

        jScrollPane4.setViewportView(jTextPaneObservacionPago);

        javax.swing.GroupLayout jPanelDetalleCuotasLayout = new javax.swing.GroupLayout(jPanelDetalleCuotas);
        jPanelDetalleCuotas.setLayout(jPanelDetalleCuotasLayout);
        jPanelDetalleCuotasLayout.setHorizontalGroup(
            jPanelDetalleCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleCuotasLayout.createSequentialGroup()
                .addGroup(jPanelDetalleCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalleCuotasLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabelFechaRealizacion6))
                    .addGroup(jPanelDetalleCuotasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelFechaRealizacion10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetalleCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldFechaPagoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMontoCuotaAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabelFechaRealizacion8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanelDetalleCuotasLayout.setVerticalGroup(
            jPanelDetalleCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleCuotasLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanelDetalleCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaRealizacion6)
                    .addComponent(jTextFieldFechaPagoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDetalleCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMontoCuotaAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaRealizacion10))
                .addGap(0, 46, Short.MAX_VALUE))
            .addGroup(jPanelDetalleCuotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalleCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanelDetalleCuotasLayout.createSequentialGroup()
                        .addComponent(jLabelFechaRealizacion8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Deuda: ConceptoDeuda - Fecha: FechaCreacion - Monto: MontoTotalDeuda");

        javax.swing.GroupLayout jPanelCuotasLayout = new javax.swing.GroupLayout(jPanelCuotas);
        jPanelCuotas.setLayout(jPanelCuotasLayout);
        jPanelCuotasLayout.setHorizontalGroup(
            jPanelCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCuotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTablaCuotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalleCuotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelCuotasLayout.setVerticalGroup(
            jPanelCuotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCuotasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTablaCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDetalleCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cuotas", jPanelCuotas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableDeudas.setEnabled(false);

        camposActivo(jPanelDetalleDeudas, true);
        camposLimpiarDeuda();
        unaDeudaSeleccionada = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 0) {
            try {
                Date fechaRealizacion = new java.sql.Date(df.parse(jTextFieldFechaRealizacion.getText()).getTime());
                Date fechaVencimiento = new java.sql.Date(df.parse(jTextFieldFechaVencimiento.getText()).getTime());

                unaControladoraGlobal.crearDeudaSocia(unaSocia, fechaRealizacion, (ConceptoDeportivo) jComboBoxConcepto.getSelectedItem(), jTextPaneObservacionDeuda.getText(), Double.parseDouble(jTextFieldMontoTotalDeuda.getText()), jComboBoxCantidadCuotas.getSelectedIndex() + 1, fechaVencimiento);
                JOptionPane.showMessageDialog(this, "Deuda Guardada");

                cargarTablaDeudas();

                jButtonNuevo.setEnabled(true);
                jButtonGuardar.setEnabled(false);
                jButtonCancelar.setEnabled(false);
                jButtonEliminar.setEnabled(false);

                jTableDeudas.setEnabled(true);

                camposActivo(jPanelDetalleDeudas, false);
                camposLimpiarDeuda();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
            }
        } else {
            if (jTabbedPane1.getSelectedIndex() == 1) {
                if (unaCuotaSeleccionada.getUnPagoCuota() == null) {
                    try {
                        Date fechaPago = new java.sql.Date(df.parse(jTextFieldFechaPagoCuota.getText()).getTime());
                        unaControladoraGlobal.crearPagoCuota(unaCuotaSeleccionada, Double.valueOf(jTextFieldMontoCuotaAbonado.getText()), fechaPago, title);
                        JOptionPane.showMessageDialog(this, "Pago generado exitosamente");

                        cargarTablaCuotas();

                        jButtonNuevo.setEnabled(false);
                        jButtonGuardar.setEnabled(false);
                        jButtonCancelar.setEnabled(false);
                        jButtonEliminar.setEnabled(false);
                        jButtonPagar.setEnabled(false);
                        jTabbedPane1.setEnabledAt(0, true);

                        jTableCuotas.setEnabled(true);

                        camposActivo(jPanelDetalleCuotas, false);
                        camposLimpiarCuotas();
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La cuota ya posee un pago. No se admite un nuevo pago.");
                }
            }
        }

    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 0) {
            jButtonNuevo.setEnabled(true);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);

            jTableDeudas.setEnabled(true);

            camposActivo(jPanelDetalleDeudas, false);
            camposLimpiarDeuda();
        } else {
            if (jTabbedPane1.getSelectedIndex() == 1) {
                jButtonNuevo.setEnabled(false);
                jButtonGuardar.setEnabled(false);
                jButtonCancelar.setEnabled(false);
                jButtonEliminar.setEnabled(false);

                jTableCuotas.setEnabled(true);

                camposActivo(jPanelDetalleCuotas, false);
                camposLimpiarCuotas();
                jTabbedPane1.setEnabledAt(0, true);
            }
        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 0) {
            jButtonNuevo.setEnabled(true);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);

            jTableDeudas.setEnabled(true);

            camposActivo(jPanelDetalleDeudas, false);

            Object[] options = {"OK", "Cancelar"};
            if (0 == JOptionPane.showOptionDialog(
                    this,
                    "Desea eliminar la Deuda de la socia "
                    + unaSocia.getApellido() + " "
                    + unaSocia.getNombre() + ", Por concepto: "
                    + unaDeudaSeleccionada.getUnConceptoDeportivo().getConcepto() + " ("
                    + unaDeudaSeleccionada.getMontoTotal() + ") ? ",
                    "Eliminar",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options)) {
                unaControladoraGlobal.eliminarDeuda(unaDeudaSeleccionada);
                unaDeudaSeleccionada = null;
                cargarTablaDeudas();
            }
            jTableDeudas.clearSelection();
            camposLimpiarDeuda();
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTableDeudasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDeudasMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            jTabbedPane1.setSelectedIndex(1);
            cargarTablaCuotas();
        }
    }//GEN-LAST:event_jTableDeudasMouseClicked

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonPagar.setEnabled(false);
        jTabbedPane1.setEnabledAt(0, false);

        jTableCuotas.setEnabled(false);

        camposActivo(jPanelDetalleCuotas, true);
        camposLimpiarCuotas();
    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 0 && jTabbedPane1.getComponentCount()==2) {
            jTabbedPane1.setEnabledAt(1, false);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JComboBox jComboBoxCantidadCuotas;
    private javax.swing.JComboBox jComboBoxConcepto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFechaRealizacion;
    private javax.swing.JLabel jLabelFechaRealizacion1;
    private javax.swing.JLabel jLabelFechaRealizacion10;
    private javax.swing.JLabel jLabelFechaRealizacion2;
    private javax.swing.JLabel jLabelFechaRealizacion3;
    private javax.swing.JLabel jLabelFechaRealizacion4;
    private javax.swing.JLabel jLabelFechaRealizacion5;
    private javax.swing.JLabel jLabelFechaRealizacion6;
    private javax.swing.JLabel jLabelFechaRealizacion8;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelCuotas;
    private javax.swing.JPanel jPanelDetalleCuotas;
    private javax.swing.JPanel jPanelDetalleDeudas;
    private javax.swing.JPanel jPanelDeudas;
    private javax.swing.JPanel jPanelTablaCuotas;
    private javax.swing.JPanel jPanelTablaDeudas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCuotas;
    private javax.swing.JTable jTableDeudas;
    private javax.swing.JTextField jTextFieldFechaPagoCuota;
    private javax.swing.JTextField jTextFieldFechaRealizacion;
    private javax.swing.JTextField jTextFieldFechaVencimiento;
    private javax.swing.JTextField jTextFieldMontoCuota;
    private javax.swing.JTextField jTextFieldMontoCuotaAbonado;
    private javax.swing.JTextField jTextFieldMontoTotalDeuda;
    private javax.swing.JTextPane jTextPaneObservacionDeuda;
    private javax.swing.JTextPane jTextPaneObservacionPago;
    // End of variables declaration//GEN-END:variables
}
