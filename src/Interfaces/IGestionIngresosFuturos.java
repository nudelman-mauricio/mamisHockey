package Interfaces;

import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.Cuota;
import logicaNegocios.Deuda;
import logicaNegocios.Equipo;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IGestionIngresosFuturos extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaIngresosPorConcepto, modeloTablaIngresosPorDeuda;
    private DateFormat df = DateFormat.getDateInstance();
    private SimpleDateFormat dateFormatSoloMes = new SimpleDateFormat("MM");
    private SimpleDateFormat dateFormatSoloAnio = new SimpleDateFormat("YYYY");
    private ConceptoDeportivo unConceptoDeportivoSeleccionado = null;
    private double montoTotalRestante = 0;
    private double montoTotalVencido = 0;
    private double montoRestantePorConcepto = 0;
    private double montoVencidoPorConcepto = 0;
    private Date fechaDesde = null;
    private Date fechaHasta = null;

    public IGestionIngresosFuturos(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        IMenuPrincipalInterface.jDesktopPane.add(this);
        IMenuPrincipalInterface.centrarYalFrente(this);

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaIngresosPorConcepto = (DefaultTableModel) jTableIngresosPorConcepto.getModel();
        this.modeloTablaIngresosPorDeuda = (DefaultTableModel) jTableIngresosPorDeuda.getModel();

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png")));
        this.setTitle("Gestión de Ingresos Futuros");
        this.jTableIngresosPorConcepto.getTableHeader().setReorderingAllowed(false);

        jComboBoxDesdeMes.setSelectedIndex(Integer.parseInt(dateFormatSoloMes.format(unaControladoraGlobal.fechaSistema())) - 1);
        jComboBoxDesdeAño.setSelectedItem(dateFormatSoloAnio.format(unaControladoraGlobal.fechaSistema()));

        jComboBoxHastaMes.setSelectedIndex(Integer.parseInt(dateFormatSoloMes.format(unaControladoraGlobal.fechaSistema())) - 1);
        jComboBoxHastaAño.setSelectedItem(dateFormatSoloAnio.format(unaControladoraGlobal.fechaSistema()));

        cargarTablaIngresosPorConcepto();
    }

    private void cargarTablaIngresosPorConcepto() {
        limpiarTabla(modeloTablaIngresosPorConcepto);
        limpiarTabla(modeloTablaIngresosPorDeuda);

        jTextFieldRestante.setText("");
        jTextFieldVencido.setText("");

        montoTotalRestante = 0;
        montoTotalVencido = 0;

        try {
            fechaDesde = new java.sql.Date(df.parse(String.valueOf("01/" + String.valueOf(jComboBoxDesdeMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxDesdeAño.getSelectedItem()))).getTime());
            fechaHasta = new java.sql.Date(df.parse(String.valueOf("01/" + String.valueOf(jComboBoxHastaMes.getSelectedIndex() + 2) + "/" + String.valueOf(jComboBoxHastaAño.getSelectedItem()))).getTime());
            for (ConceptoDeportivo unConceptoDeportivo : unaControladoraGlobal.getConceptosDeportivosBD()) {
                getListaDeudasPorConceptoConFiltros(unConceptoDeportivo, fechaDesde, fechaHasta, false);
                montoTotalRestante += montoRestantePorConcepto;
                montoTotalVencido += montoVencidoPorConcepto;
                if (montoRestantePorConcepto != 0) {
                    this.modeloTablaIngresosPorConcepto.addRow(new Object[]{unConceptoDeportivo, montoRestantePorConcepto, montoVencidoPorConcepto});
                }
            }
            jTextFieldRestante.setText(Double.toString(montoTotalRestante));
            jTextFieldVencido.setText(Double.toString(montoTotalVencido));
        } catch (ParseException ex) {
            Logger.getLogger(IGestionIngresosFuturos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getListaDeudasPorConceptoConFiltros(ConceptoDeportivo unConceptoDeportivo, Date fechaDesde, Date fechaHasta, boolean mostrarDeudas) {
        montoRestantePorConcepto = 0;
        montoVencidoPorConcepto = 0;
        double montoRestantePorDeuda;
        double montoVencidoPorDeuda;
        boolean bandera;
        for (Deuda unaDeuda : this.unaControladoraGlobal.getDeudasPorConceptoDeportivo(unConceptoDeportivo)) {
            bandera = false;
            montoRestantePorDeuda = 0;
            montoVencidoPorDeuda = 0;
            for (Cuota unaCuota : unaDeuda.getCuotas()) {
                if (!unaCuota.isBorradoLogico()) {
                    if (!unaCuota.isSaldado()) {
                        //Si el vencimiento de la cuota esta dentro del rango especificado como parametro
                        if (((unaCuota.getFechaVencimiento().after(fechaDesde)) || (unaCuota.getFechaVencimiento().equals(fechaDesde))) && ((unaCuota.getFechaVencimiento().before(fechaHasta))) || (unaCuota.getFechaVencimiento().equals(fechaHasta))) {
                            if (jRadioButtonTodos.isSelected()) { //Si hay que mostrar vencidos juntos con a futuro
                                montoRestantePorConcepto += montoASumar(unaCuota);
                                montoRestantePorDeuda += montoASumar(unaCuota);
                                if (unaControladoraGlobal.fechaSistema().after(unaCuota.getFechaVencimiento())) {
                                    montoVencidoPorConcepto += montoRestantePorConcepto;
                                    montoVencidoPorDeuda += montoRestantePorDeuda;
                                }
                                bandera = true;
                            } else {
                                if (jRadioButtonVencidos.isSelected()) { //Si solo hay que mostrar vencidos
                                    if (unaControladoraGlobal.fechaSistema().after(unaCuota.getFechaVencimiento())) {
                                        montoRestantePorConcepto += montoASumar(unaCuota);
                                        montoRestantePorDeuda += montoASumar(unaCuota);
                                        montoVencidoPorConcepto += montoRestantePorConcepto;
                                        montoVencidoPorDeuda += montoRestantePorDeuda;
                                        bandera = true;
                                    }
                                } else { //Si solo hay que mostrar los no vencidos
                                    if (!unaControladoraGlobal.fechaSistema().after(unaCuota.getFechaVencimiento())) {
                                        montoRestantePorConcepto += montoASumar(unaCuota);
                                        montoRestantePorDeuda += montoASumar(unaCuota);
                                        bandera = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (bandera && mostrarDeudas) {
                String responsableDeuda;
                Socia unaSociaResponsableDeuda = unaControladoraGlobal.getSociaResponsableDeuda(unaDeuda);
                if (unaSociaResponsableDeuda != null) {
                    responsableDeuda = unaSociaResponsableDeuda.toString();
                } else {
                    Equipo unEquipoResponsableDeuda = unaControladoraGlobal.getEquipoResponsableDeuda(unaDeuda);
                    if (unEquipoResponsableDeuda != null) {
                        responsableDeuda = unEquipoResponsableDeuda.toString();
                    } else {
                        responsableDeuda = "-";
                    }
                }
                this.modeloTablaIngresosPorDeuda.addRow(new Object[]{responsableDeuda, unConceptoDeportivo.getConcepto(), unaDeuda.getObservacion(), unaDeuda.getMontoTotal(), (unaDeuda.getMontoTotal() - montoRestantePorDeuda), montoRestantePorDeuda, montoVencidoPorDeuda});
            }
        }
    }

    private double montoASumar(Cuota unaCuota) {
        if (unaCuota.getUnPagoCuota() != null) {
            if (!unaCuota.getUnPagoCuota().isBorradoLogico()) {
                return (unaCuota.getMonto() - unaCuota.getUnPagoCuota().getMonto());
            } else {
                return unaCuota.getMonto();
            }
        } else {
            return unaCuota.getMonto();
        }
    }

    private void cargarTablaIngresosPorDeudas() {
        limpiarTabla(modeloTablaIngresosPorDeuda);
        if (jTableIngresosPorConcepto.getSelectedRow() > -1) {
            if (jTableIngresosPorConcepto.getValueAt(jTableIngresosPorConcepto.getSelectedRow(), 0) != null) {
                unConceptoDeportivoSeleccionado = (ConceptoDeportivo) jTableIngresosPorConcepto.getValueAt(jTableIngresosPorConcepto.getSelectedRow(), 0);
                getListaDeudasPorConceptoConFiltros(unConceptoDeportivoSeleccionado, fechaDesde, fechaHasta, true);
            }
        }
    }

    private void limpiarTabla(DefaultTableModel unModeloTabla) {
        int filas = unModeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            unModeloTabla.removeRow(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButtonImprimir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIngresosPorConcepto = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableIngresosPorDeuda = new javax.swing.JTable();
        jLabelRestante = new javax.swing.JLabel();
        jTextFieldRestante = new javax.swing.JTextField();
        jLabelVencido = new javax.swing.JLabel();
        jTextFieldVencido = new javax.swing.JTextField();
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
        jRadioButtonSinVencer = new javax.swing.JRadioButton();
        jRadioButtonVencidos = new javax.swing.JRadioButton();
        jRadioButtonTodos = new javax.swing.JRadioButton();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(900, 700));
        setMinimumSize(new java.awt.Dimension(900, 700));
        setPreferredSize(new java.awt.Dimension(900, 700));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jTableIngresosPorConcepto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Concepto", "Monto a Ingresar", "Monto Vencido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableIngresosPorConcepto);
        jTableIngresosPorConcepto.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                cargarTablaIngresosPorDeudas();
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        jTableIngresosPorDeuda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Responsable", "Concepto", "Observación", "$ Total", "$ Pagado", "$ Restante", "$ Vencido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableIngresosPorDeuda);
        if (jTableIngresosPorDeuda.getColumnModel().getColumnCount() > 0) {
            jTableIngresosPorDeuda.getColumnModel().getColumn(1).setMinWidth(100);
            jTableIngresosPorDeuda.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableIngresosPorDeuda.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableIngresosPorDeuda.getColumnModel().getColumn(3).setMinWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(3).setPreferredWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(3).setMaxWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(4).setMinWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(4).setPreferredWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(4).setMaxWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(5).setMinWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(5).setPreferredWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(5).setMaxWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(6).setMinWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(6).setPreferredWidth(70);
            jTableIngresosPorDeuda.getColumnModel().getColumn(6).setMaxWidth(70);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );

        jLabelRestante.setText("Monto Total a Ingresar");

        jTextFieldRestante.setEditable(false);

        jLabelVencido.setText("Monto Total Vencido");

        jTextFieldVencido.setEditable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(" Desde Vencimiento"));

        jLabel1.setText("Año");

        jLabel2.setText("Mes");

        jComboBoxDesdeMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxDesdeMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDesdeMesItemStateChanged(evt);
            }
        });

        jComboBoxDesdeAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hasta Vencimiento"));

        jLabel7.setText("Año");

        jLabel8.setText("Mes");

        jComboBoxHastaMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxHastaMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxHastaMesItemStateChanged(evt);
            }
        });

        jComboBoxHastaAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        buttonGroup1.add(jRadioButtonSinVencer);
        jRadioButtonSinVencer.setText("Solo Sin Vencer");
        jRadioButtonSinVencer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSinVencerActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonVencidos);
        jRadioButtonVencidos.setText("Solo Vencidos");
        jRadioButtonVencidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVencidosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonTodos);
        jRadioButtonTodos.setSelected(true);
        jRadioButtonTodos.setText("Todos");
        jRadioButtonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelVencido, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelRestante, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldRestante, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(jTextFieldVencido)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonTodos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonVencidos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonSinVencer))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(85, 85, 85)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonTodos)
                            .addComponent(jRadioButtonVencidos)
                            .addComponent(jRadioButtonSinVencer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRestante))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldVencido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVencido))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxDesdeMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDesdeMesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTablaIngresosPorConcepto();
        }
    }//GEN-LAST:event_jComboBoxDesdeMesItemStateChanged

    private void jComboBoxDesdeAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDesdeAñoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTablaIngresosPorConcepto();
        }
    }//GEN-LAST:event_jComboBoxDesdeAñoItemStateChanged

    private void jComboBoxHastaMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHastaMesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTablaIngresosPorConcepto();
        }
    }//GEN-LAST:event_jComboBoxHastaMesItemStateChanged

    private void jComboBoxHastaAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHastaAñoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTablaIngresosPorConcepto();
        }
    }//GEN-LAST:event_jComboBoxHastaAñoItemStateChanged

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
//        if (jTableIngresos.getRowCount() > 0) {
//            String desde = "01/" + String.valueOf(jComboBoxDesdeMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxDesdeAño.getSelectedItem());
//            String hasta = "01/" + String.valueOf(jComboBoxHastaMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxHastaAño.getSelectedItem());
//            Date fechaHasta = null;
//            Date fechaDesde = null;
//            try {
//                fechaDesde = new java.sql.Date(df.parse(String.valueOf(desde)).getTime());
//                fechaHasta = new java.sql.Date(df.parse(String.valueOf(hasta)).getTime());
//
//            } catch (ParseException ex) {
//                Logger.getLogger(IGestionEgresos.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            List<IngresoOtro> unaListaIngreso = new ArrayList();
//            IngresoOtro unIngresoOtro;
//            int filas = this.modeloTablaGestionIngresos.getRowCount();
//            for (int i = 0; i < filas; i++) {
//                unIngresoOtro = unaControladoraGlobal.getIngresoOtroBD((Long) jTableIngresos.getValueAt(i, 0));
//                unaListaIngreso.add(unIngresoOtro);
//            }
//            GestionIngresosDS unaGestionIgresosDS = new GestionIngresosDS(unaControladoraGlobal, unaListaIngreso, fechaDesde, fechaHasta);
//            unaGestionIgresosDS.verReporte();
//        } else {
//            JOptionPane.showMessageDialog(this, "La tabla esta Vacia");
//        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jRadioButtonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodosActionPerformed
        cargarTablaIngresosPorConcepto();
    }//GEN-LAST:event_jRadioButtonTodosActionPerformed

    private void jRadioButtonVencidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVencidosActionPerformed
        cargarTablaIngresosPorConcepto();
    }//GEN-LAST:event_jRadioButtonVencidosActionPerformed

    private void jRadioButtonSinVencerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSinVencerActionPerformed
        cargarTablaIngresosPorConcepto();
    }//GEN-LAST:event_jRadioButtonSinVencerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JComboBox jComboBoxDesdeAño;
    private javax.swing.JComboBox jComboBoxDesdeMes;
    private javax.swing.JComboBox jComboBoxHastaAño;
    private javax.swing.JComboBox jComboBoxHastaMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelRestante;
    private javax.swing.JLabel jLabelVencido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonSinVencer;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JRadioButton jRadioButtonVencidos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableIngresosPorConcepto;
    private javax.swing.JTable jTableIngresosPorDeuda;
    private javax.swing.JTextField jTextFieldRestante;
    private javax.swing.JTextField jTextFieldVencido;
    // End of variables declaration//GEN-END:variables
}
