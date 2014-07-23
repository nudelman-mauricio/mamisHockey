package Interfaces;

import java.awt.Component;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Frecuencia;
import logicaNegocios.Mes;
import logicaNegocios.TipoCancha;
import main.ControladoraGlobal;

public class ITipoCancha extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private TipoCancha unTipoCanchaSeleccionado;
    private DefaultTableModel modeloTable;

    public ITipoCancha(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTable = (DefaultTableModel) jTableTipoCancha.getModel();
        camposActivo(jPanel3, false);
        cargarTabla();

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/club.png"))); //Icono Ventana
        this.setTitle("Tipos de Canchas"); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar       
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

    //Cargar Tabla con los tipos de canchas
    public void cargarTabla() {
        limpiarTabla(modeloTable);
        for (TipoCancha unTipoCancha : unaControladoraGlobal.getTiposCanchasBD()) {
            this.modeloTable.addRow(new Object[]{unTipoCancha.getIdTipoCancha(), unTipoCancha.getNombre(), unTipoCancha.getMonto()});
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

    //blanqueda componentes editables
    void camposLimpiar() {
        jTextFieldNombre.setText("");
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

        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
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

    //Crear Frecuencia para el tipo de cancha
    private Frecuencia crearFrecuencia() {
        Frecuencia unaFrecuencia = unaControladoraGlobal.crearFrecuencia((String) jComboBox1.getSelectedItem(), (String) jComboBox2.getSelectedItem());
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
    
    //actualizar campos al seleccionar un tipo cancha en la tabla
    void camposCargar(){
        if (jTableTipoCancha.getSelectedRow() > -1) {
            if (jTableTipoCancha.getValueAt(jTableTipoCancha.getSelectedRow(), 0) != null) {
                unTipoCanchaSeleccionado = unaControladoraGlobal.getTipoCanchaBD((Long) jTableTipoCancha.getValueAt(jTableTipoCancha.getSelectedRow(), 0));
                Frecuencia unaFrecuencia = unTipoCanchaSeleccionado.getUnaFrecuencia();
                
                camposLimpiar();

                jTextFieldNombre.setText(unTipoCanchaSeleccionado.getNombre());
                jTextFieldMonto.setText(String.valueOf(unTipoCanchaSeleccionado.getMonto()));                
                setMeses(unaFrecuencia);
                jComboBox1.setSelectedIndex(Integer.valueOf(unaFrecuencia.getDiaGeneracion())-1);
                jComboBox2.setSelectedIndex(Integer.valueOf(unaFrecuencia.getDiaVencimiento())-1);

                jButtonEditar.setEnabled(true);
                jButtonEliminar.setEnabled(true);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTipoCancha = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldMonto = new javax.swing.JTextField();
        jLabelNombre3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelFechaCaducidad2 = new javax.swing.JLabel();
        jLabelFechaCaducidad3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
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
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();

        setClosable(true);

        jTableTipoCancha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Tipo de Cancha", "Monto Mensual ($)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTipoCancha);
        if (jTableTipoCancha.getColumnModel().getColumnCount() > 0) {
            jTableTipoCancha.getColumnModel().getColumn(0).setMinWidth(0);
            jTableTipoCancha.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableTipoCancha.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableTipoCancha.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGuardar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonCancelar)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo"));

        jLabelNombre.setText("Tipo de Cancha");

        jTextFieldNombre.setEditable(false);
        jTextFieldNombre.setEnabled(false);

        jTextFieldMonto.setEditable(false);
        jTextFieldMonto.setEnabled(false);

        jLabelNombre3.setText("Monto Mensual ($)");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Frecuencia"));

        jLabelFechaCaducidad2.setText("Dia Generación");

        jLabelFechaCaducidad3.setText("Dia Vencimiento");

        jLabelFechaCaducidad4.setText("Meses a los que se aplica");

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaCaducidad4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
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
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox9)
                                .addGap(4, 4, 4)
                                .addComponent(jCheckBox10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox12)))))
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabelFechaCaducidad4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox11)
                    .addComponent(jCheckBox12))
                .addGap(10, 10, 10))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox1.setEnabled(false);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox2.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaCaducidad3)
                            .addComponent(jLabelFechaCaducidad2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaCaducidad2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaCaducidad3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelNombre3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jTextFieldMonto))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre3)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(false);

        camposActivo(jPanel3, true);
        camposLimpiar();
        unTipoCanchaSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(true);

        camposActivo(jPanel3, false);
        camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(false);

        camposActivo(jPanel3, true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (unTipoCanchaSeleccionado == null) {
            unaControladoraGlobal.crearTipoCancha(Double.valueOf(jTextFieldMonto.getText()), crearFrecuencia(), jTextFieldNombre.getText());
            JOptionPane.showMessageDialog(this, "Tipo Cancha Creada");
        } else {
            unaControladoraGlobal.eliminarFrecuencia(unTipoCanchaSeleccionado.getUnaFrecuencia());
            unaControladoraGlobal.modificarTipoCancha(unTipoCanchaSeleccionado, Double.valueOf(jTextFieldMonto.getText()), crearFrecuencia(), jTextFieldNombre.getText(), false);
            JOptionPane.showMessageDialog(this, "Tipo Cancha Modificada");
            unTipoCanchaSeleccionado = null;
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

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);

        jTableTipoCancha.setEnabled(true);

        camposActivo(jPanel3, false);        

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el Tipo de Cancha: " + unTipoCanchaSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarFrecuencia(unTipoCanchaSeleccionado.getUnaFrecuencia());
            unaControladoraGlobal.eliminarTipoCancha(unTipoCanchaSeleccionado);
            unTipoCanchaSeleccionado = null;
            cargarTabla();
        }
        jTableTipoCancha.clearSelection();
        camposLimpiar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed


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
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabelFechaCaducidad2;
    private javax.swing.JLabel jLabelFechaCaducidad3;
    private javax.swing.JLabel jLabelFechaCaducidad4;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombre3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTipoCancha;
    private javax.swing.JTextField jTextFieldMonto;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
