package Interfaces;

import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.ConceptoDeportivo;
import main.ControladoraGlobal;

public class IGeneradorMensual extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private ConceptoDeportivo unConceptoDeportivoSeleccionado;
    private DefaultTableModel modeloTable;
    private DateFormat dateFormatCompleto = DateFormat.getDateInstance();
    private SimpleDateFormat dateFormatSoloMes = new SimpleDateFormat("MM");
    private SimpleDateFormat dateFormatSoloAnio = new SimpleDateFormat("YYYY");
    private String mesSeleccionado, anioSeleccionado, enter = System.lineSeparator();

    public IGeneradorMensual(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        
        IMenuPrincipal.jDesktopPane.add(this);
        IMenuPrincipal.centrarYalFrente(this);
        
        setFrameIcon(new ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png")));//Icono de la ventana
        this.setTitle("Generador Deudas Mensuales");//Titulo Ventana

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTable = (DefaultTableModel) jTableConceptos.getModel();
        cargarTabla();
        camposLimpiar();

        //Setear Combos para Mes actual y Año actual.
        jComboBoxMes.setSelectedIndex(Integer.parseInt(dateFormatSoloMes.format(unaControladoraGlobal.fechaSistema())) - 1);
        jComboBoxAno.setSelectedItem(dateFormatSoloAnio.format(unaControladoraGlobal.fechaSistema()));

        //Setear fecha de vencimiento con la fecha vencimiento estandar de la DB        
        Date fechaVencimientoEstandar = (Date) unaControladoraGlobal.fechaSistema();
        fechaVencimientoEstandar.setDate(Integer.parseInt(unaControladoraGlobal.getConfiguracion("diaVencimientoEstandar")));
        //Si la fecha vencimiento parametro es anterior al dia de la fecha entonces setear vencimiento para el mes siguiente
        if (fechaVencimientoEstandar.getDate() <= unaControladoraGlobal.fechaSistema().getDate()) {
            fechaVencimientoEstandar.setMonth(fechaVencimientoEstandar.getMonth() + 1);
            //Tambien setear el mes al que corresponde ya que se entiende que el mes actual ya se genero
            jComboBoxMes.setSelectedIndex(Integer.parseInt(dateFormatSoloMes.format(unaControladoraGlobal.fechaSistema())));
        }
        this.jDateChooserFechaVencimiento.setDate(fechaVencimientoEstandar);
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int filas = modeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTabla.removeRow(0);
        }
    }

    /**
     * Cargar Tabla con los conceptos deportivos. Pero verificar que el mes
     * seleccionado corresponda con la Frecuencia establecida para cada
     * concepto.
     */
    private void cargarTabla() {
        limpiarTabla(modeloTable);
        String unAfectado = "No Especificado";
        for (ConceptoDeportivo unConceptoDeportivo : unaControladoraGlobal.getConceptosDeportivosBD()) {
            if (unConceptoDeportivo.isMesEnFrecuencia(mesSeleccionado)) {
                if (unConceptoDeportivo.getUnTipoCancha() != null) {
                    unAfectado = "Cancha: " + unConceptoDeportivo.getUnTipoCancha().getNombre();
                }
                if (unConceptoDeportivo.getUnTipoEstado() != null) {
                    unAfectado = "Estado: " + unConceptoDeportivo.getUnTipoEstado().getNombre();
                }
                this.modeloTable.addRow(new Object[]{unConceptoDeportivo, true, unConceptoDeportivo.getConcepto(), unConceptoDeportivo.getMonto(), unAfectado});
                unAfectado = "No Especificado";
            }
        }
    }

    //blanquea componentes editables
    private void camposLimpiar() {
        jTextFieldConcepto.setText("");
        jTextFieldMonto.setText("");
        camposLimpiarFrecuencia();
    }

    private void camposLimpiarFrecuencia() {
        jTextFieldAfectados.setText("");
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        jCheckBox5.setSelected(false);
        jCheckBox6.setSelected(false);
        jCheckBox7.setSelected(false);
        jCheckBox8.setSelected(false);
        jCheckBox9.setSelected(false);
        jCheckBox10.setSelected(false);
        jCheckBox11.setSelected(false);
        jCheckBox12.setSelected(false);
    }

    //cargar Check de Meses Frecuencia
    private void setMeses() {
        jCheckBox1.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Enero"));
        jCheckBox2.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Febrero"));
        jCheckBox3.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Marzo"));
        jCheckBox4.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Abril"));
        jCheckBox5.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Mayo"));
        jCheckBox6.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Junio"));
        jCheckBox7.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Julio"));
        jCheckBox8.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Agosto"));
        jCheckBox9.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Septiembre"));
        jCheckBox10.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Octubre"));
        jCheckBox11.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Nobiembre"));
        jCheckBox12.setSelected(unConceptoDeportivoSeleccionado.isMesEnFrecuencia("Diciembre"));
    }

    //actualizar campos al seleccionar en la tabla
    private void camposCargar() {
        if (jTableConceptos.getSelectedRow() > -1) {
            if (jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0) != null) {
                unConceptoDeportivoSeleccionado = (ConceptoDeportivo) jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0);

                camposLimpiar();

                jTextFieldConcepto.setText(unConceptoDeportivoSeleccionado.getConcepto());
                jTextFieldMonto.setText(String.valueOf(unConceptoDeportivoSeleccionado.getMonto()));

                if (unConceptoDeportivoSeleccionado.getUnTipoCancha() != null) {
                    jTextFieldAfectados.setText(unConceptoDeportivoSeleccionado.getUnTipoCancha().toString());
                }
                if (unConceptoDeportivoSeleccionado.getUnTipoEstado() != null) {
                    jTextFieldAfectados.setText(unConceptoDeportivoSeleccionado.getUnTipoEstado().toString());
                }
                if (!unConceptoDeportivoSeleccionado.getMeses().isEmpty()) {
                    setMeses();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonGenerar = new javax.swing.JButton();
        jDateChooserFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabelFecha = new javax.swing.JLabel();
        jLabelFechaRealizacion4 = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxAno = new javax.swing.JComboBox();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableConceptos = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelConcepto = new javax.swing.JLabel();
        jLabelMonto = new javax.swing.JLabel();
        jTextFieldConcepto = new javax.swing.JTextField();
        jTextFieldMonto = new javax.swing.JTextField();
        jLabelAfectados = new javax.swing.JLabel();
        jTextFieldAfectados = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabelMeses = new javax.swing.JLabel();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(659, 472));
        setMinimumSize(new java.awt.Dimension(659, 472));
        setPreferredSize(new java.awt.Dimension(659, 472));

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelBotones.setMaximumSize(new java.awt.Dimension(627, 69));

        jButtonGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png"))); // NOI18N
        jButtonGenerar.setText("Generar");
        jButtonGenerar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGenerar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarActionPerformed(evt);
            }
        });

        jLabelFecha.setText("Vencimiento: ");

        jLabelFechaRealizacion4.setText("Cuota correspondiente al mes de");

        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMesItemStateChanged(evt);
            }
        });

        jLabel2.setText("del año");

        jComboBoxAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFechaRealizacion4)
                    .addComponent(jLabelFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBotonesLayout.createSequentialGroup()
                        .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(jButtonGenerar)
                .addGap(3, 3, 3))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGenerar)
                    .addGroup(jPanelBotonesLayout.createSequentialGroup()
                        .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaRealizacion4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3))
        );

        jTableConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Objeto", "Generar?", "Concepto Deportivo", "Monto ($)", "Le corresponde a"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableConceptos);
        if (jTableConceptos.getColumnModel().getColumnCount() > 0) {
            jTableConceptos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableConceptos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableConceptos.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableConceptos.getColumnModel().getColumn(1).setMinWidth(60);
            jTableConceptos.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableConceptos.getColumnModel().getColumn(1).setMaxWidth(60);
            jTableConceptos.getColumnModel().getColumn(3).setMinWidth(100);
            jTableConceptos.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableConceptos.getColumnModel().getColumn(3).setMaxWidth(100);
            jTableConceptos.getColumnModel().getColumn(4).setMinWidth(110);
            jTableConceptos.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTableConceptos.getColumnModel().getColumn(4).setMaxWidth(110);
        }
        jTableConceptos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelConcepto.setText("Concepto Deportivo");

        jLabelMonto.setText("Monto ($)");

        jTextFieldConcepto.setEditable(false);

        jTextFieldMonto.setEditable(false);

        jLabelAfectados.setText("Le corresponde pagar a");

        jTextFieldAfectados.setEditable(false);

        jCheckBox1.setText("E");
        jCheckBox1.setEnabled(false);

        jCheckBox2.setText("F");
        jCheckBox2.setEnabled(false);

        jCheckBox3.setText("M");
        jCheckBox3.setEnabled(false);

        jCheckBox4.setText("A");
        jCheckBox4.setEnabled(false);

        jCheckBox5.setText("M");
        jCheckBox5.setEnabled(false);

        jCheckBox6.setText("J");
        jCheckBox6.setEnabled(false);
        jCheckBox6.setMaximumSize(new java.awt.Dimension(33, 23));
        jCheckBox6.setMinimumSize(new java.awt.Dimension(33, 23));

        jCheckBox8.setText("A");
        jCheckBox8.setEnabled(false);

        jCheckBox9.setText("S");
        jCheckBox9.setEnabled(false);

        jCheckBox10.setText("O");
        jCheckBox10.setEnabled(false);

        jCheckBox11.setText("N");
        jCheckBox11.setEnabled(false);

        jCheckBox12.setText("D");
        jCheckBox12.setEnabled(false);

        jCheckBox7.setText("J");
        jCheckBox7.setEnabled(false);

        jLabelMeses.setText("Meses a los que se aplica");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox9)
                        .addGap(4, 4, 4)
                        .addComponent(jCheckBox10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabelMeses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelMeses)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox11)
                    .addComponent(jCheckBox12))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMonto)
                    .addComponent(jLabelConcepto)
                    .addComponent(jLabelAfectados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jTextFieldAfectados))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelConcepto)
                            .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMonto)
                            .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAfectados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAfectados))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed
        JOptionPane.showMessageDialog(this, "El proceso puede demorar.");
        
        String cartelConfirmacion = "Va a generar las deudas correspondientes al mes de " + mesSeleccionado.toUpperCase() + " del año " + anioSeleccionado.toUpperCase() + "." + enter + enter + "Los conceptos a generar serán: " + enter;

        // <editor-fold defaultstate="collapsed" desc="Crear Lista de Conceptos Seleccionados">
        ConceptoDeportivo unConceptoDeportivo;
        List<ConceptoDeportivo> conceptosSeleccionados = new ArrayList();
        for (int i = 0; i < jTableConceptos.getRowCount(); i++) {
            if ((boolean) jTableConceptos.getValueAt(i, 1)) {
                unConceptoDeportivo = (ConceptoDeportivo) jTableConceptos.getValueAt(i, 0);

                //Lista para el Reporte
                conceptosSeleccionados.add(unConceptoDeportivo);

                //Armar la lista para el cartel
                cartelConfirmacion += "     - " + unConceptoDeportivo.getConcepto() + enter;
            }
        }
        // </editor-fold>

        //Agregar vencimiento al cartel
        cartelConfirmacion += enter + "El día de vencimiento será: " + dateFormatCompleto.format(jDateChooserFechaVencimiento.getDate()) + enter + enter + "Si confirma no podrá deshacer.";

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                cartelConfirmacion,
                "Generar Deudas",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options
        )) {
            String observacionParametro = "Cuota correspondiente al mes de " + mesSeleccionado.toUpperCase() + " del año " + anioSeleccionado.toUpperCase();
            unaControladoraGlobal.crearDeudasMensualesAutomaticas(jDateChooserFechaVencimiento.getDate(), conceptosSeleccionados, observacionParametro);
            JOptionPane.showMessageDialog(this, "Se generaron las deudas Exitosamente. Gracias por aguardar.");

            this.jButtonGenerar.setEnabled(false);
            jComboBoxMes.setEnabled(false);
            jComboBoxAno.setEnabled(false);
            jDateChooserFechaVencimiento.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonGenerarActionPerformed

    private void jComboBoxMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            mesSeleccionado = String.valueOf(jComboBoxMes.getSelectedItem());
            anioSeleccionado = String.valueOf(jComboBoxAno.getSelectedItem());
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxMesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBoxAno;
    private javax.swing.JComboBox jComboBoxMes;
    private com.toedter.calendar.JDateChooser jDateChooserFechaVencimiento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAfectados;
    private javax.swing.JLabel jLabelConcepto;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFechaRealizacion4;
    private javax.swing.JLabel jLabelMeses;
    private javax.swing.JLabel jLabelMonto;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableConceptos;
    private javax.swing.JTextField jTextFieldAfectados;
    private javax.swing.JTextField jTextFieldConcepto;
    private javax.swing.JTextField jTextFieldMonto;
    // End of variables declaration//GEN-END:variables
}
