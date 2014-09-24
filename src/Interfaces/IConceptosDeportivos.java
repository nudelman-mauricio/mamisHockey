package Interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.ConceptoDeportivo;
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
        camposActivo(jPanelDetalles, false);
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
        
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png"))); //Icono Ventana
        this.setTitle("Conceptos Deportivos"); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar
        camposLimpiar();
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
            unAfectado = "No Especificado";
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
        jTextFieldConcepto.setEditable(false);
    }

    //blanquea componentes editables
    void camposLimpiar() {
        jTextFieldConcepto.setText("");
        jTextFieldMonto.setText("");
        camposLimpiarFrecuencia();
    }
    
    void camposLimpiarFrecuencia() {
        jCheckBoxAutoGeneracion.setSelected(false);
        jComboBoxAfectados.setSelectedIndex(-1);
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
    void setMeses() {
        for (Mes unMes : unConceptoDeportivoSeleccionado.getMeses()) {
            switch (unMes.getNombre()) {
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
    private ArrayList<Mes> crearFrecuencia() {
        ArrayList<Mes> meses = new ArrayList();
        if (jCheckBox1.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(1));
        }
        if (jCheckBox2.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(2));
        }
        if (jCheckBox3.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(3));
        }
        if (jCheckBox4.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(4));
        }
        if (jCheckBox5.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(5));
        }
        if (jCheckBox6.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(6));
        }
        if (jCheckBox7.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(7));
        }
        if (jCheckBox8.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(8));
        }
        if (jCheckBox9.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(9));
        }
        if (jCheckBox10.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(10));
        }
        if (jCheckBox11.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(11));
        }
        if (jCheckBox12.isSelected()) {
            meses.add(unaControladoraGlobal.getMesDB(12));
        }
        return meses;
    }

    //actualizar campos al seleccionar en la tabla
    void camposCargar() {
        if (jTableConceptos.getSelectedRow() > -1) {
            if (jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0) != null) {
                unConceptoDeportivoSeleccionado = unaControladoraGlobal.getConceptoDeportivoBD((Long) jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0));
                
                camposLimpiar();
                
                jTextFieldConcepto.setText(unConceptoDeportivoSeleccionado.getConcepto());
                jTextFieldMonto.setText(String.valueOf(unConceptoDeportivoSeleccionado.getMonto()));
                
                if (unConceptoDeportivoSeleccionado.getUnTipoCancha() != null) {
                    jComboBoxAfectados.setSelectedItem(unConceptoDeportivoSeleccionado.getUnTipoCancha());
                }
                if (unConceptoDeportivoSeleccionado.getUnTipoEstado() != null) {
                    jComboBoxAfectados.setSelectedItem(unConceptoDeportivoSeleccionado.getUnTipoEstado());
                }
                
                if (!unConceptoDeportivoSeleccionado.getMeses().isEmpty()) {
                    jCheckBoxAutoGeneracion.setSelected(true);
                    setMeses();
                }
                camposActivo(jPanelDetalles, false);
                jButtonEditar.setEnabled(true);
                //jButtonEliminar.setEnabled(true);
            }
        }
    }
    
    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldConcepto.getText().isEmpty()) {
            jLabelConcepto.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelConcepto.setForeground(Color.black);
        }
        if (jTextFieldMonto.getText().isEmpty()) {
            jLabelMonto.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMonto.setForeground(Color.black);
        }
        if (jCheckBoxAutoGeneracion.isSelected()) {
            if (jComboBoxAfectados.getSelectedIndex() == -1) {
                jLabelAfectados.setForeground(Color.red);
                bandera = false;
            } else {
                jLabelAfectados.setForeground(Color.black);
            }
            if (!jCheckBox1.isSelected() && !jCheckBox2.isSelected() && !jCheckBox3.isSelected() && !jCheckBox4.isSelected() && !jCheckBox5.isSelected() && !jCheckBox6.isSelected() && !jCheckBox7.isSelected() && !jCheckBox8.isSelected() && !jCheckBox9.isSelected() && !jCheckBox10.isSelected() && !jCheckBox11.isSelected() && !jCheckBox12.isSelected()) {
                jLabelMeses.setForeground(Color.red);
                bandera = false;
            } else {
                jLabelMeses.setForeground(Color.black);
            }
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
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableConceptos = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelConcepto = new javax.swing.JLabel();
        jLabelMonto = new javax.swing.JLabel();
        jTextFieldConcepto = new javax.swing.JTextField();
        jTextFieldMonto = new javax.swing.JTextField();
        jCheckBoxAutoGeneracion = new javax.swing.JCheckBox();
        jPanelFrecuencia = new javax.swing.JPanel();
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
        jLabelAfectados = new javax.swing.JLabel();
        jComboBoxAfectados = new javax.swing.JComboBox();
        jLabelFechaRealizacion3 = new javax.swing.JLabel();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 588));
        setMinimumSize(new java.awt.Dimension(650, 588));
        setPreferredSize(new java.awt.Dimension(650, 588));

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
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setEnabled(false);
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
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelConcepto.setText("Concepto Deportivo");

        jLabelMonto.setText("Monto ($)");

        jTextFieldConcepto.setEditable(false);

        jCheckBoxAutoGeneracion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxAutoGeneracionStateChanged(evt);
            }
        });

        jPanelFrecuencia.setBorder(javax.swing.BorderFactory.createTitledBorder("Frecuencia de Autogeneración"));

        jCheckBox1.setText("E");

        jCheckBox2.setText("F");

        jCheckBox3.setText("M");

        jCheckBox4.setText("A");

        jCheckBox5.setText("M");

        jCheckBox6.setText("J");
        jCheckBox6.setMaximumSize(new java.awt.Dimension(33, 23));
        jCheckBox6.setMinimumSize(new java.awt.Dimension(33, 23));

        jCheckBox8.setText("A");

        jCheckBox9.setText("S");

        jCheckBox10.setText("O");

        jCheckBox11.setText("N");

        jCheckBox12.setText("D");

        jCheckBox7.setText("J");

        jLabelMeses.setText("Meses a los que se aplica");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
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
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
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

        jLabelAfectados.setText("Le corresponde pagar a");

        javax.swing.GroupLayout jPanelFrecuenciaLayout = new javax.swing.GroupLayout(jPanelFrecuencia);
        jPanelFrecuencia.setLayout(jPanelFrecuenciaLayout);
        jPanelFrecuenciaLayout.setHorizontalGroup(
            jPanelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFrecuenciaLayout.createSequentialGroup()
                .addGroup(jPanelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFrecuenciaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelAfectados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAfectados, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFrecuenciaLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanelFrecuenciaLayout.setVerticalGroup(
            jPanelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFrecuenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAfectados)
                    .addComponent(jComboBoxAfectados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabelFechaRealizacion3.setText("Habilitar Auto Generación");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jPanelFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelMonto)
                            .addComponent(jLabelConcepto)
                            .addComponent(jLabelFechaRealizacion3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBoxAutoGeneracion)
                            .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConcepto)
                    .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMonto)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFechaRealizacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxAutoGeneracion))
                .addGap(25, 25, 25)
                .addComponent(jPanelFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        
        camposActivo(jPanelDetalles, true);
        camposActivo(jPanelFrecuencia, false);
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
        
        camposActivo(jPanelDetalles, true);
        camposActivo(jPanelFrecuencia, jCheckBoxAutoGeneracion.isSelected());
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            ArrayList<Mes> meses = null;
            TipoCancha unTipoCancha = null;
            TipoEstado unTipoEstado = null;
            if (jCheckBoxAutoGeneracion.isSelected()) {
                meses = crearFrecuencia();
                if (jComboBoxAfectados.getSelectedItem() instanceof TipoCancha) {
                    unTipoCancha = (TipoCancha) jComboBoxAfectados.getSelectedItem();
                }
                if (jComboBoxAfectados.getSelectedItem() instanceof TipoEstado) {
                    unTipoEstado = (TipoEstado) jComboBoxAfectados.getSelectedItem();
                }
            }
            if (unConceptoDeportivoSeleccionado == null) {
                unaControladoraGlobal.crearConceptoDeportivo(Double.valueOf(jTextFieldMonto.getText()), jTextFieldConcepto.getText(), meses, unTipoCancha, unTipoEstado);
                JOptionPane.showMessageDialog(this, "Concepto Deportivo Creado");
            } else {
                unaControladoraGlobal.modificarConceptoDeportivo(unConceptoDeportivoSeleccionado, Double.valueOf(jTextFieldMonto.getText()), jTextFieldConcepto.getText(), meses, unTipoCancha, unTipoEstado, unConceptoDeportivoSeleccionado.isBorradoLogico());
                JOptionPane.showMessageDialog(this, "Concepto Deportivo Modificado");
                unConceptoDeportivoSeleccionado = null;
            }
            cargarTabla();

            //jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);
            
            jTableConceptos.setEnabled(true);
            
            camposActivo(jPanelDetalles, false);
            camposLimpiar();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jCheckBoxAutoGeneracionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxAutoGeneracionStateChanged
        camposActivo(jPanelFrecuencia, jCheckBoxAutoGeneracion.isSelected());
        if (!jCheckBoxAutoGeneracion.isSelected()) {
            camposLimpiarFrecuencia();
        }
    }//GEN-LAST:event_jCheckBoxAutoGeneracionStateChanged

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        //jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        
        jTableConceptos.setEnabled(true);
        
        camposActivo(jPanelDetalles, false);
        camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        
        jTableConceptos.setEnabled(false);
        
        camposActivo(jPanelDetalles, false);
        
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el Concepto Deportivo: " + unConceptoDeportivoSeleccionado.getConcepto(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarConceptoDeportivo(unConceptoDeportivoSeleccionado);
            unConceptoDeportivoSeleccionado = null;
            cargarTabla();
        }
        jTableConceptos.clearSelection();
        jTableConceptos.setEnabled(true);
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
    private javax.swing.JCheckBox jCheckBoxAutoGeneracion;
    private javax.swing.JComboBox jComboBoxAfectados;
    private javax.swing.JLabel jLabelAfectados;
    private javax.swing.JLabel jLabelConcepto;
    private javax.swing.JLabel jLabelFechaRealizacion3;
    private javax.swing.JLabel jLabelMeses;
    private javax.swing.JLabel jLabelMonto;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelFrecuencia;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableConceptos;
    private javax.swing.JTextField jTextFieldConcepto;
    private javax.swing.JTextField jTextFieldMonto;
    // End of variables declaration//GEN-END:variables
}
