package Interfaces;

import java.awt.Component;
import java.awt.Container;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.Frecuencia;
import logicaNegocios.Mes;
import logicaNegocios.TipoCancha;
import logicaNegocios.TipoEstado;
import main.ControladoraGlobal;

public class IConceptosDeportivos extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private ConceptoDeportivo unConceptoDeportivoSeleccionado;
    private DefaultTableModel modeloTable;

    public IConceptosDeportivos(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTable = (DefaultTableModel) jTableConceptos.getModel();
        camposActivo(jPanel7, false);
        cargarTabla();

        Vector comboBoxItems = new Vector();
        for (TipoCancha aux : unaControladoraGlobal.getTiposCanchasBD()) {
            comboBoxItems.add(aux);
        }
        for (TipoEstado aux : unaControladoraGlobal.getTiposEstadosBD()) {
            comboBoxItems.add(aux);
        }
        DefaultComboBoxModel modelComboAfectados = new DefaultComboBoxModel(comboBoxItems);
        this.jComboBoxAfectados.setModel(modelComboAfectados);

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/club.png"))); //Icono Ventana
        this.setTitle("Conceptos Deportivos"); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar 
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int filas = modeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTabla.removeRow(0);
        }
    }

    //Cargar Tabla con los conceptos deportivos
    public void cargarTabla() {
        limpiarTabla(modeloTable);
        String unAfectado = "No Especificado";
        for (ConceptoDeportivo unConceptoDeportivo : unaControladoraGlobal.getConceptosDeportivosBD()) {
            if (unConceptoDeportivo.getUnTipoCancha() != null) {
                unAfectado = "Cancha: " + unConceptoDeportivo.getUnTipoCancha().getNombre();
            }
            if (unConceptoDeportivo.getUnTipoEstado() != null) {
                unAfectado = "Estado: " + unConceptoDeportivo.getUnTipoEstado().getNombre();
            }

            this.modeloTable.addRow(new Object[]{unConceptoDeportivo.getIdConceptoDeportivo(), unConceptoDeportivo.getConcepto(), unConceptoDeportivo.getMonto(), unAfectado});
        }
        jButtonEditar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
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

    //blanquea componentes editables
    void camposLimpiar() {
        jTextFieldConcepto.setText("");
        jTextFieldMonto.setText("");

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

        jComboBoxAfectados.setSelectedIndex(-1);
        jComboBoxDiaGeneracion.setSelectedIndex(-1);
        jComboBoxDiaVencimiento.setSelectedIndex(-1);
    }

    //cargar Check de Meses Frecuencia
    void setMeses(Frecuencia unaFrecuencia) {
        for (Mes aux : unaFrecuencia.getMeses()) {
            switch (aux.getNombre()) {
                case "Enero":
                    jCheckBox1.setSelected(true);
                    break;
                case "Febrero":
                    jCheckBox2.setSelected(true);
                    break;
                case "Marzo":
                    jCheckBox3.setSelected(true);
                    break;
                case "Abril":
                    jCheckBox4.setSelected(true);
                    break;
                case "Mayo":
                    jCheckBox5.setSelected(true);
                    break;
                case "Junio":
                    jCheckBox6.setSelected(true);
                    break;
                case "Julio":
                    jCheckBox7.setSelected(true);
                    break;
                case "Agosto":
                    jCheckBox8.setSelected(true);
                    break;
                case "Septiembre":
                    jCheckBox9.setSelected(true);
                    break;
                case "Octubre":
                    jCheckBox10.setSelected(true);
                    break;
                case "Nobiembre":
                    jCheckBox11.setSelected(true);
                    break;
                case "Diciembre":
                    jCheckBox12.setSelected(true);
                    break;
            }
        }
    }
    
    //Crear Frecuencia
    private Frecuencia crearFrecuencia() {
        Frecuencia unaFrecuencia = unaControladoraGlobal.crearFrecuencia((String) jComboBoxDiaGeneracion.getSelectedItem(), (String) jComboBoxDiaVencimiento.getSelectedItem());
        if (jCheckBox1.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(1));
        }
        if (jCheckBox2.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(2));
        }
        if (jCheckBox3.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(3));
        }
        if (jCheckBox4.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(4));
        }
        if (jCheckBox5.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(5));
        }
        if (jCheckBox6.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(6));
        }
        if (jCheckBox7.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(7));
        }
        if (jCheckBox8.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(8));
        }
        if (jCheckBox9.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(9));
        }
        if (jCheckBox10.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(10));
        }
        if (jCheckBox11.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(11));
        }
        if (jCheckBox12.isSelected()) {
            unaControladoraGlobal.agregarMesFrecuencia(unaFrecuencia, unaControladoraGlobal.getMesDB(12));
        }
        return unaFrecuencia;
    }

    //actualizar campos al seleccionar en la tabla
    void camposCargar() {
        if (jTableConceptos.getSelectedRow() > -1) {
            if (jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0) != null) {
                unConceptoDeportivoSeleccionado = unaControladoraGlobal.getConceptoDeportivoBD((Long) jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0));
                Frecuencia unaFrecuencia = unConceptoDeportivoSeleccionado.getUnaFrecuencia();

                camposLimpiar();

                jTextFieldConcepto.setText(unConceptoDeportivoSeleccionado.getConcepto());
                jTextFieldMonto.setText(String.valueOf(unConceptoDeportivoSeleccionado.getMonto()));

                if (unConceptoDeportivoSeleccionado.getUnTipoCancha() != null) {
                    jComboBoxAfectados.setSelectedItem(unConceptoDeportivoSeleccionado.getUnTipoCancha());
                }
                if (unConceptoDeportivoSeleccionado.getUnTipoEstado() != null) {
                    jComboBoxAfectados.setSelectedItem(unConceptoDeportivoSeleccionado.getUnTipoEstado());
                }

                setMeses(unaFrecuencia);
                jComboBoxDiaGeneracion.setSelectedIndex(Integer.valueOf(unaFrecuencia.getDiaGeneracion()) - 1);
                jComboBoxDiaVencimiento.setSelectedIndex(Integer.valueOf(unaFrecuencia.getDiaVencimiento()) - 1);

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
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableConceptos = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabelFechaRealizacion = new javax.swing.JLabel();
        jLabelFechaCaducidad = new javax.swing.JLabel();
        jTextFieldConcepto = new javax.swing.JTextField();
        jTextFieldMonto = new javax.swing.JTextField();
        jLabelFechaRealizacion2 = new javax.swing.JLabel();
        jComboBoxAfectados = new javax.swing.JComboBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabelFechaCaducidad2 = new javax.swing.JLabel();
        jLabelFechaCaducidad3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelFechaCaducidad4 = new javax.swing.JLabel();
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
        jComboBoxDiaGeneracion = new javax.swing.JComboBox();
        jComboBoxDiaVencimiento = new javax.swing.JComboBox();
        jLabelFechaRealizacion3 = new javax.swing.JLabel();

        setClosable(true);

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
                .addGap(230, 230, 230))
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
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Concepto Deportivo", "Monto ($)", "Le corresponde a"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        jScrollPane1.setViewportView(jTableConceptos);
        if (jTableConceptos.getColumnModel().getColumnCount() > 0) {
            jTableConceptos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableConceptos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableConceptos.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableConceptos.getColumnModel().getColumn(2).setMinWidth(100);
            jTableConceptos.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableConceptos.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel7.setName(""); // NOI18N

        jLabelFechaRealizacion.setText("Concepto Deportivo");

        jLabelFechaCaducidad.setText("Monto ($)");

        jLabelFechaRealizacion2.setText("Le corresponde pagar a");

        jCheckBox13.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox13StateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Frecuencia"));

        jLabelFechaCaducidad2.setText("Dia Generación");

        jLabelFechaCaducidad3.setText("Dia Vencimiento");

        jLabelFechaCaducidad4.setText("Meses a los que se aplica");

        jCheckBox1.setText("E");

        jCheckBox2.setText("F");

        jCheckBox3.setText("M");

        jCheckBox4.setText("A");

        jCheckBox5.setText("M");

        jCheckBox6.setText("J");

        jCheckBox8.setText("A");

        jCheckBox9.setText("S");

        jCheckBox10.setText("O");

        jCheckBox11.setText("N");

        jCheckBox12.setText("D");

        jCheckBox7.setText("J");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaCaducidad4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox12)))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabelFechaCaducidad4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
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

        jComboBoxDiaGeneracion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBoxDiaVencimiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaCaducidad3)
                            .addComponent(jLabelFechaCaducidad2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxDiaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxDiaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaCaducidad2)
                    .addComponent(jComboBoxDiaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaCaducidad3)
                    .addComponent(jComboBoxDiaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        jLabelFechaRealizacion3.setText("Habilitar Auto Generación");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelFechaCaducidad)
                                    .addComponent(jLabelFechaRealizacion)
                                    .addComponent(jLabelFechaRealizacion2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMonto)
                                    .addComponent(jComboBoxAfectados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabelFechaRealizacion3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaRealizacion)
                    .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaCaducidad)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaRealizacion2)
                    .addComponent(jComboBoxAfectados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaRealizacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox13))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 543, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableConceptos.setEnabled(false);

        camposActivo(jPanel7, true);
        camposLimpiar();
        unConceptoDeportivoSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableConceptos.setEnabled(false);

        camposActivo(jPanel7, true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (unConceptoDeportivoSeleccionado == null) {
            ConceptoDeportivo unConceptoDeportivo = unaControladoraGlobal.crearConceptoDeportivo(Double.valueOf(jTextFieldMonto.getText()), jTextFieldConcepto.getText());
            if (verificarFrecuencia()) {
                unConceptoDeportivo.setUnaFrecuencia(crearFrecuencia());
            }
            JOptionPane.showMessageDialog(this, "Concepto Deportivo Creado");
        } else {
            unaControladoraGlobal.eliminarFrecuencia(unTipoCanchaSeleccionado.getUnaFrecuencia());
            unaControladoraGlobal.modificarTipoCancha(unTipoCanchaSeleccionado, Double.valueOf(jTextFieldMonto.getText()), crearFrecuencia(), jTextFieldNombre.getText(), false);
            JOptionPane.showMessageDialog(this, "Tipo Cancha Modificada");
            unConceptoDeportivoSeleccionado = null;
        }
        cargarTabla();

        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(true);

        camposActivo(jPanel3, false);
        camposLimpiar();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jCheckBox13StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox13StateChanged
        camposActivo(jPanel1, jCheckBox13.isSelected());
    }//GEN-LAST:event_jCheckBox13StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBoxAfectados;
    private javax.swing.JComboBox jComboBoxDiaGeneracion;
    private javax.swing.JComboBox jComboBoxDiaVencimiento;
    private javax.swing.JLabel jLabelFechaCaducidad;
    private javax.swing.JLabel jLabelFechaCaducidad2;
    private javax.swing.JLabel jLabelFechaCaducidad3;
    private javax.swing.JLabel jLabelFechaCaducidad4;
    private javax.swing.JLabel jLabelFechaRealizacion;
    private javax.swing.JLabel jLabelFechaRealizacion2;
    private javax.swing.JLabel jLabelFechaRealizacion3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableConceptos;
    private javax.swing.JTextField jTextFieldConcepto;
    private javax.swing.JTextField jTextFieldMonto;
    // End of variables declaration//GEN-END:variables
}
