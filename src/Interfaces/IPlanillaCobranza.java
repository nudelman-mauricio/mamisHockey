package Interfaces;

import DataSources.PlanilladePagoDS;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Cuota;
import logicaNegocios.Deuda;
import logicaNegocios.Equipo;
import logicaNegocios.PlanillaPago;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IPlanillaCobranza extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Equipo unEquipo;
    private DefaultTableModel modeloPlantel;
    private DefaultTableModel modeloDeudas;
    private DateFormat df = DateFormat.getDateInstance();
    private SimpleDateFormat dateFormatYear = new SimpleDateFormat("YYYY");

    public IPlanillaCobranza(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Equipo unEquipo) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unEquipo = unEquipo;

        this.modeloPlantel = (DefaultTableModel) jTablePlantel.getModel();
        this.modeloDeudas = (DefaultTableModel) jTableDeudas.getModel();

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/PanillaPagos.png")));
        this.setTitle("Planilla de Pagos Mensuales de: " + unEquipo.getNombre());
        IMenuPrincipalInterface.centrar(this);

        cargarCampos();

        jLabelFechaHoy.setText("Fecha: " + df.format(unaControladoraGlobal.fechaSistema()));
        if (unEquipo.getUnaDelegada() != null) {
            jComboBoxDelegadas.addItem(unEquipo.getUnaDelegada());
        }
        if (unEquipo.getUnaDelegadaSuplente() != null) {
            jComboBoxDelegadas.addItem(unEquipo.getUnaDelegadaSuplente());
        }
        jComboBoxDelegadas.setSelectedIndex(-1);

        // <editor-fold defaultstate="collapsed" desc="Mes">
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        String mes = "";
        switch (dateFormat.format(unaControladoraGlobal.fechaSistema())) {
            case "01":
                mes = "Enero";
                break;
            case "02":
                mes = "Febrero";
                break;
            case "03":
                mes = "Marzo";
                break;
            case "04":
                mes = "Abril";
                break;
            case "05":
                mes = "Mayo";
                break;
            case "06":
                mes = "Junio";
                break;
            case "07":
                mes = "Julio";
                break;
            case "08":
                mes = "Agosto";
                break;
            case "09":
                mes = "Septiembre";
                break;
            case "10":
                mes = "Octubre";
                break;
            case "11":
                mes = "Noviembre";
                break;
            case "12":
                mes = "Diciembre";
                break;
        }
        // </editor-fold>
        jLabelTitulo.setText("Pago Mensual: " + unEquipo.getNombre() + " - " + mes + "/" + dateFormatYear.format(unaControladoraGlobal.fechaSistema()));

    }

    public void cargarCampos() {
        cargarTablaPlantel();
        calcularCostos();
    }

    public void cargarTablaPlantel() {
        limpiarTabla(this.modeloPlantel);
        double SubTotalxSocia;
        for (Socia unaSocia : unEquipo.getPlantel()) {
            SubTotalxSocia = 0;

            //Crea la fecha para traer las cuotas que vencieron o estan por vencer en un mes mas, hasta el dia 8
            Date fechaHasta = unaControladoraGlobal.fechaSistema();
            fechaHasta.setMonth(fechaHasta.getMonth() + 1);
            fechaHasta.setDate(15);

            for (Deuda unaDeuda : unaSocia.getDeudas()) {
                if ((!unaDeuda.isBorradoLogico()) && (!unaDeuda.isSaldado())) {
                    for (Cuota unaCuota : unaDeuda.getCuotas()) {
                        if ((unaCuota.getFechaVencimiento().before(fechaHasta)) && (!unaCuota.isSaldado())) {
                            SubTotalxSocia += unaCuota.getMonto();
                        }
                    }
                }
            }
            boolean pagar = true;
            if (SubTotalxSocia == 0.0) {
                pagar = false;
            }
            this.modeloPlantel.addRow(new Object[]{pagar, unaSocia.getDni(), unaSocia, unaSocia.getUltimoEstado().getUnTipoEstado().getNombre(), SubTotalxSocia});
        }
    }

    public void cargarDeudas(Socia unaSocia) {
        limpiarTabla(this.modeloDeudas);

        //Crea la fecha para traer las cuotas que vencieron o estan por vencer en un mes mas, hasta el dia 8
        Date fechaHasta = unaControladoraGlobal.fechaSistema();
        fechaHasta.setMonth(fechaHasta.getMonth() + 1);
        fechaHasta.setDate(15);

        for (Deuda unaDeuda : unaSocia.getDeudas()) {
            if ((!unaDeuda.isBorradoLogico()) && (!unaDeuda.isSaldado())) {
                for (Cuota unaCuota : unaDeuda.getCuotas()) {
                    if ((unaCuota.getFechaVencimiento().before(fechaHasta)) && (!unaCuota.isSaldado())) {
                        this.modeloDeudas.addRow(new Object[]{df.format(unaDeuda.getFechaGeneracion()), unaDeuda.getUnConceptoDeportivo(), unaCuota.getNumero(), df.format(unaCuota.getFechaVencimiento()), unaDeuda.getObservacion(), unaCuota.getMonto()});
                    }
                }
            }
        }
    }

    private void calcularCostos() {
        //SubTotal:
        double subTotalSocia = 0.0;
        for (int i = 0; i < jTablePlantel.getRowCount(); i++) {
            if ((boolean) jTablePlantel.getValueAt(i, 0)) {
                subTotalSocia += (double) jTablePlantel.getValueAt(i, 4);
            }
        }
        jTextFieldSubTotal.setText(String.valueOf(subTotalSocia));

        //Costo cancha
        double costoCancha = 0.0;
        for (Deuda unaDeuda : unEquipo.getDeudas()) {
            if ((!unaDeuda.isSaldado()) && ("Cancha".equalsIgnoreCase(unaDeuda.getUnConceptoDeportivo().getConcepto()))) {
                costoCancha += unaDeuda.getMontoTotal();
            }
        }
        jTextFieldCostoCancha.setText(String.valueOf(costoCancha));

        //Seguro Tecnicos
        double costoSeguro = 0.0;
        for (Deuda unaDeuda : unEquipo.getDeudas()) {
            if ((!unaDeuda.isSaldado()) && ("Seguro Técnicos".equalsIgnoreCase(unaDeuda.getUnConceptoDeportivo().getConcepto()))) {
                costoSeguro += unaDeuda.getMontoTotal();
            }
        }
        jTextFieldCostoSeguro.setText(String.valueOf(costoSeguro));

        //Total
        jTextFieldTotal.setText(String.valueOf(costoCancha + costoSeguro + subTotalSocia));
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

    private boolean camposValidar() {
        boolean bandera = true;
        if (jComboBoxDelegadas.getSelectedIndex() == -1) {
            jLabelDelegadas.setForeground(Color.red);
            bandera = false;
            if (jComboBoxDelegadas.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "El equipo no tiene Delegada ni Delegada Suplente, no se puede pagar por falta de responsable de pago." + System.getProperty("line.separator") + "Asigne una Delegada y vuelva a intentarlo.");
                return false;
            }
        } else {
            jLabelDelegadas.setForeground(Color.black);
        }
        if (jTextFieldIdRecibo.getText().isEmpty()) {
            jLabelIdRecibo.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelIdRecibo.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDeudas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePlantel = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelFechaHoy = new javax.swing.JLabel();
        jButtonImprimir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldTotal = new javax.swing.JTextField();
        jTextFieldSubTotal = new javax.swing.JTextField();
        jTextFieldCostoCancha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonPagar = new javax.swing.JButton();
        jTextFieldIdRecibo = new javax.swing.JTextField();
        jLabelIdRecibo = new javax.swing.JLabel();
        jLabelDelegadas = new javax.swing.JLabel();
        jComboBoxDelegadas = new javax.swing.JComboBox();
        jTextFieldCostoSeguro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(798, 742));
        setMinimumSize(new java.awt.Dimension(798, 742));
        setPreferredSize(new java.awt.Dimension(798, 742));
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

        jTableDeudas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Concepto", "Cuota", "Vencimiento", "Observación", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDeudas);
        if (jTableDeudas.getColumnModel().getColumnCount() > 0) {
            jTableDeudas.getColumnModel().getColumn(0).setMinWidth(80);
            jTableDeudas.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTableDeudas.getColumnModel().getColumn(0).setMaxWidth(80);
            jTableDeudas.getColumnModel().getColumn(1).setMinWidth(110);
            jTableDeudas.getColumnModel().getColumn(1).setPreferredWidth(110);
            jTableDeudas.getColumnModel().getColumn(1).setMaxWidth(110);
            jTableDeudas.getColumnModel().getColumn(2).setMinWidth(50);
            jTableDeudas.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTableDeudas.getColumnModel().getColumn(2).setMaxWidth(50);
            jTableDeudas.getColumnModel().getColumn(3).setMinWidth(80);
            jTableDeudas.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTableDeudas.getColumnModel().getColumn(3).setMaxWidth(80);
            jTableDeudas.getColumnModel().getColumn(4).setPreferredWidth(400);
            jTableDeudas.getColumnModel().getColumn(5).setMinWidth(80);
            jTableDeudas.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTableDeudas.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        jTablePlantel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pagar", "DNI", "Apellido y Nombre", "Estado", "Sub-Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTablePlantel);
        if (jTablePlantel.getColumnModel().getColumnCount() > 0) {
            jTablePlantel.getColumnModel().getColumn(0).setMinWidth(40);
            jTablePlantel.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTablePlantel.getColumnModel().getColumn(0).setMaxWidth(40);
            jTablePlantel.getColumnModel().getColumn(1).setMinWidth(60);
            jTablePlantel.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTablePlantel.getColumnModel().getColumn(1).setMaxWidth(60);
            jTablePlantel.getColumnModel().getColumn(3).setMinWidth(120);
            jTablePlantel.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTablePlantel.getColumnModel().getColumn(3).setMaxWidth(120);
            jTablePlantel.getColumnModel().getColumn(4).setMinWidth(80);
            jTablePlantel.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTablePlantel.getColumnModel().getColumn(4).setMaxWidth(80);
        }
        jTablePlantel.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (jTablePlantel.getSelectedRow() > -1) {
                    if (jTablePlantel.getValueAt(jTablePlantel.getSelectedRow(), 0) != null) {
                        cargarDeudas((Socia) jTablePlantel.getValueAt(jTablePlantel.getSelectedRow(),2));
                    }
                }
                calcularCostos();
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Detalles:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Plantel:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Pago Mensual: \"Equipo\" - \"Mes\"");

        jLabelFechaHoy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFechaHoy.setText("Fecha: dd/MM/aaaa");

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelFechaHoy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelFechaHoy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTextFieldTotal.setEditable(false);

        jTextFieldSubTotal.setEditable(false);

        jTextFieldCostoCancha.setEditable(false);

        jLabel1.setText("Total Plantel:");

        jLabel3.setText("Total General:");

        jLabel2.setText("Total Cancha:");

        jButtonPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Pagar.png"))); // NOI18N
        jButtonPagar.setText("Pagar");
        jButtonPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });

        jLabelIdRecibo.setText("Numero de Recibo:");

        jLabelDelegadas.setText("Pagado por:");

        jTextFieldCostoSeguro.setEditable(false);

        jLabel6.setText("Total Seguro Técnicos:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 453, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIdRecibo)
                            .addComponent(jLabelDelegadas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxDelegadas, 0, 180, Short.MAX_VALUE)
                            .addComponent(jTextFieldIdRecibo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCostoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCostoSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldIdRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIdRecibo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCostoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxDelegadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDelegadas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCostoSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jButtonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        if (camposValidar()) {
            Object[] options = {"OK", "Cancelar"};
            if (0 == JOptionPane.showOptionDialog(
                    this,
                    "Confirme que desea generar el pago. Verifique el dinero y controle todo antes. Si Acepta no podrá deshacer los cambios.",
                    "Confirme",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options)) {
                List<Socia> sociaPagaron = new ArrayList();
                List<Cuota> cuotasPagaron = new ArrayList();

                // <editor-fold defaultstate="collapsed" desc="Pago Cuotas Socia">
                Socia unaSocia;
                //Crea la fecha para traer las cuotas que vencieron o estan por vencer en un mes mas, hasta el dia 8
                Date fechaHasta = unaControladoraGlobal.fechaSistema();
                fechaHasta.setMonth(fechaHasta.getMonth() + 1);
                fechaHasta.setDate(15);

                for (int i = 0; i < jTablePlantel.getRowCount(); i++) {
                    if ((boolean) jTablePlantel.getValueAt(i, 0)) {
                        unaSocia = (Socia) jTablePlantel.getValueAt(i, 2);

                        //Lista para el Reporte
                        sociaPagaron.add(unaSocia);

                        //Recorrido de las deudas para pagar
                        for (Deuda unaDeuda : unaSocia.getDeudas()) {
                            if ((!unaDeuda.isBorradoLogico()) && (!unaDeuda.isSaldado())) {
                                for (Cuota unaCuota : unaDeuda.getCuotas()) {
                                    if ((unaCuota.getFechaVencimiento().before(fechaHasta)) && (!unaCuota.isSaldado())) {
                                        cuotasPagaron.add(unaCuota);
                                        unaControladoraGlobal.crearPagoCuota(unaCuota, unaCuota.getMonto(), unaControladoraGlobal.fechaSistema(), "Pagado en Planilla id: " + "idPlanilla");
                                    }
                                }
                            }
                        }

                    }
                }
            // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Pago Deudas Cancha y Seguro Tecnico">
                for (Deuda unaDeuda : unEquipo.getDeudas()) {
                    if ((!unaDeuda.isSaldado())
                            && ("Cancha".equalsIgnoreCase(unaDeuda.getUnConceptoDeportivo().getConcepto()))
                            || ("Seguro Técnicos".equalsIgnoreCase(unaDeuda.getUnConceptoDeportivo().getConcepto()))) {
                        for (Cuota unaCuota : unaDeuda.getCuotas()) {
                            unaControladoraGlobal.crearPagoCuota(unaCuota, unaCuota.getMonto(), unaControladoraGlobal.fechaSistema(), "Pagado en Planilla id: " + "idPlanilla");
                        }
                    }
                }
            // </editor-fold>

                //Guardar Planilla
                PlanillaPago unaPlanillaPago = unaControladoraGlobal.crearPlanillaPago(unEquipo, unaControladoraGlobal.fechaSistema(), Double.valueOf(jTextFieldTotal.getText()), Long.valueOf(jTextFieldIdRecibo.getText()), (Socia) jComboBoxDelegadas.getSelectedItem());

                //Reporte
                PlanilladePagoDS PlanilladePagoDS = new PlanilladePagoDS(unaControladoraGlobal, jLabelTitulo.getText(), String.valueOf(unaPlanillaPago.getId()), unaPlanillaPago.getResponsablePago().toString(), jTextFieldCostoCancha.getText(), jTextFieldCostoSeguro.getText(), jTextFieldSubTotal.getText(), jTextFieldTotal.getText(), String.valueOf(unaPlanillaPago.getNroRecibo()), sociaPagaron, cuotasPagaron);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                String nombrePDF = dateFormat.format(unaControladoraGlobal.fechaSistema()) + " - " + unaPlanillaPago.getId() + " - " + unEquipo.getNombre();
                PlanilladePagoDS.verReporte(nombrePDF);

                unaControladoraGlobal.modificarPlanillaPago(unaPlanillaPago, "Planillas de Pago/" + nombrePDF + ".pdf");
            }
        }
    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        }
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        List<Socia> sociaPagaron = new ArrayList();
        List<Cuota> cuotasPagaron = new ArrayList();

        // <editor-fold defaultstate="collapsed" desc="Pago Cuotas Socia">
        Socia unaSocia;
        //Crea la fecha para traer las cuotas que vencieron o estan por vencer en un mes mas, hasta el dia 8
        Date fechaHasta = unaControladoraGlobal.fechaSistema();
        fechaHasta.setMonth(fechaHasta.getMonth() + 1);
        fechaHasta.setDate(15);

        for (int i = 0; i < jTablePlantel.getRowCount(); i++) {
            if ((boolean) jTablePlantel.getValueAt(i, 0)) {
                unaSocia = (Socia) jTablePlantel.getValueAt(i, 2);

                //Lista para el Reporte
                sociaPagaron.add(unaSocia);

                //Recorrido de las deudas para pagar
                for (Deuda unaDeuda : unaSocia.getDeudas()) {
                    if ((!unaDeuda.isBorradoLogico()) && (!unaDeuda.isSaldado())) {
                        for (Cuota unaCuota : unaDeuda.getCuotas()) {
                            if ((unaCuota.getFechaVencimiento().before(fechaHasta)) && (!unaCuota.isSaldado())) {
                                cuotasPagaron.add(unaCuota);
                            }
                        }
                    }
                }
            }
        }
        // </editor-fold>

        //Reporte
        PlanilladePagoDS PlanilladePagoDS = new PlanilladePagoDS(unaControladoraGlobal, jLabelTitulo.getText(), "-", "-", jTextFieldCostoCancha.getText(), jTextFieldCostoSeguro.getText(), jTextFieldSubTotal.getText(), jTextFieldTotal.getText(), "-", sociaPagaron, cuotasPagaron);
        PlanilladePagoDS.verReportePDFTemporal(unEquipo.getNombre());
    }//GEN-LAST:event_jButtonImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JComboBox jComboBoxDelegadas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelDelegadas;
    private javax.swing.JLabel jLabelFechaHoy;
    private javax.swing.JLabel jLabelIdRecibo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDeudas;
    private javax.swing.JTable jTablePlantel;
    private javax.swing.JTextField jTextFieldCostoCancha;
    private javax.swing.JTextField jTextFieldCostoSeguro;
    private javax.swing.JTextField jTextFieldIdRecibo;
    private javax.swing.JTextField jTextFieldSubTotal;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables

}
