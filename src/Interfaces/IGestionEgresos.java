package Interfaces;

import DataSources.GestionEgresosDS;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Cancha;
import logicaNegocios.Club;
import logicaNegocios.ConceptoEgreso;
import logicaNegocios.Egreso;
import main.ControladoraGlobal;

public class IGestionEgresos extends javax.swing.JInternalFrame {
    
    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaGestionEgresos;
    private Egreso unEgresoSeleccionado;
    private DateFormat df = DateFormat.getDateInstance();
    private String enter = System.getProperty("line.separator");
    
    public IGestionEgresos(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        
        IMenuPrincipalInterface.jDesktopPane.add(this);
        IMenuPrincipalInterface.centrarYalFrente(this);
        
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaGestionEgresos = (DefaultTableModel) jTableEgresos.getModel();
        this.jTableEgresos.getTableHeader().setReorderingAllowed(false);
        
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png")));
        this.setTitle("Gestión de Egresos");
        
        if (unaControladoraGlobal.getEgresosBD().size() > 0) {
            cargarFechasFiltrado();
            cargarTabla();
        }
        camposLimpiar();
        jTextPaneDetalle.setBackground(new Color(228, 231, 237));
    }
    
    private void cargarComboBoxConceptoEgreso() {
        this.jComboBoxConceptoEgreso.setModel(new DefaultComboBoxModel((Vector) this.unaControladoraGlobal.getConceptosEgresosBD()));
        this.jComboBoxConceptoEgreso.setSelectedIndex(-1);
    }
    
    private void cargarComboBoxClub() {
        this.jComboBoxClub.setModel(new DefaultComboBoxModel((Vector) this.unaControladoraGlobal.getClubesBD()));
        this.jComboBoxClub.setSelectedIndex(-1);
    }
    
    private void cargarFechasFiltrado() {
        if (unaControladoraGlobal.getPrimerEgreso() != null) {
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
    
    private void cargarTabla() {
        limpiarTabla();
        String desde = "01/" + String.valueOf(jComboBoxDesdeMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxDesdeAño.getSelectedItem());
        String hasta = "01/" + String.valueOf(jComboBoxHastaMes.getSelectedIndex() + 2) + "/" + String.valueOf(jComboBoxHastaAño.getSelectedItem());
        Date fechaHasta = null;
        Date fechaDesde = null;
        try {
            fechaDesde = new java.sql.Date(df.parse(String.valueOf(desde)).getTime());
            fechaHasta = new java.sql.Date(df.parse(String.valueOf(hasta)).getTime());
            
            for (Egreso unEgreso : this.unaControladoraGlobal.getEgresosEntreFechas(fechaDesde, fechaHasta)) {
                this.modeloTablaGestionEgresos.addRow(new Object[]{unEgreso.getIdEgreso(), df.format(unEgreso.getFecha()), unEgreso.getUnConceptoEgreso(), unEgreso.getObservacion(), unEgreso.getMonto()});
            }
        } catch (ParseException ex) {
            Logger.getLogger(IGestionEgresos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limpiarTabla() {
        int filas = this.modeloTablaGestionEgresos.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaGestionEgresos.removeRow(0);
        }
        camposLimpiar();
    }
    
    private void camposFiltroActivo(boolean Editable) {
        jComboBoxDesdeMes.setEnabled(Editable);
        jComboBoxDesdeAño.setEnabled(Editable);
        jComboBoxHastaMes.setEnabled(Editable);
        jComboBoxHastaAño.setEnabled(Editable);
    }
    
    private void camposActivo(boolean Editable) {
        jTextFieldMonto.setEditable(Editable);
        jDateChooserFecha.setEnabled(Editable);
        jTextPaneDetalle.setEditable(Editable);
        jComboBoxConceptoEgreso.setEnabled(Editable);
        jButtonNuevoEgreso.setEnabled(Editable);
        if (Editable) {
            jTextPaneDetalle.setBackground(Color.WHITE);
        } else {
            jTextPaneDetalle.setBackground(new Color(228, 231, 237));
            camposCanchaActivo(Editable);
        }
    }
    
    private void camposCanchaActivo(boolean Editable) {
        jPanelPagoCancha.setEnabled(Editable);
        jLabelAno.setEnabled(Editable);
        jLabelMes.setEnabled(Editable);
        jLabelClub.setEnabled(Editable);
        jComboBoxAno.setEnabled(Editable);
        jComboBoxMes.setEnabled(Editable);
        jComboBoxClub.setEnabled(Editable);
        jButtonCargarDetalle.setEnabled(Editable);
    }

    //blanquea componentes editables
    private void camposCanchaLimpiar() {
        jComboBoxAno.setSelectedIndex(-1);
        jComboBoxMes.setSelectedIndex(-1);
        jComboBoxClub.setSelectedIndex(-1);
    }

    //blanquea componentes editables
    private void camposLimpiar() {
        jTextPaneDetalle.setText("");
        jDateChooserFecha.setDate(null);
        jTextFieldMonto.setText("");
        jComboBoxConceptoEgreso.setSelectedIndex(-1);
        camposCanchaLimpiar();
    }

    //actualizar campos al seleccionar en la tabla
    private void camposCargar() {
        if (jTableEgresos.getSelectedRow() > -1) {
            if (jTableEgresos.getValueAt(jTableEgresos.getSelectedRow(), 0) != null) {
                unEgresoSeleccionado = unaControladoraGlobal.getEgresoBD((Long) jTableEgresos.getValueAt(jTableEgresos.getSelectedRow(), 0));
                
                camposLimpiar();
                
                jDateChooserFecha.setDate((unEgresoSeleccionado.getFecha()));
                jTextFieldMonto.setText(String.valueOf(unEgresoSeleccionado.getMonto()));
                jTextPaneDetalle.setText(unEgresoSeleccionado.getObservacion());
                jComboBoxConceptoEgreso.setSelectedItem(unEgresoSeleccionado.getUnConceptoEgreso());
                
                camposActivo(false);
                jButtonEditar.setEnabled(true);
                jButtonEliminar.setEnabled(true);
            }
        }
    }
    
    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldMonto.getText().isEmpty()) {
            jLabelMonto.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMonto.setForeground(Color.black);
        }
        if (jComboBoxConceptoEgreso.getSelectedIndex() == -1) {
            jLabelConceptoEgreso.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelConceptoEgreso.setForeground(Color.black);
        }
        if (jDateChooserFecha.getDate() == null) {
            jLabelFecha.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFecha.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }
    
    private boolean camposCanchaValidar() {
        boolean bandera = true;
        if (jComboBoxAno.getSelectedIndex() == -1) {
            jLabelAno.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelAno.setForeground(Color.black);
        }
        if (jComboBoxMes.getSelectedIndex() == -1) {
            jLabelMes.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMes.setForeground(Color.black);
        }
        if (jComboBoxClub.getSelectedIndex() == -1) {
            jLabelClub.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelClub.setForeground(Color.black);
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
        jButtonImprimir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEgresos = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelConceptoEgreso = new javax.swing.JLabel();
        jLabelComentario = new javax.swing.JLabel();
        jTextFieldMonto = new javax.swing.JTextField();
        jLabelMonto = new javax.swing.JLabel();
        jComboBoxConceptoEgreso = new javax.swing.JComboBox();
        jButtonNuevoEgreso = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneDetalle = new javax.swing.JTextPane();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jPanelPagoCancha = new javax.swing.JPanel();
        jLabelAno = new javax.swing.JLabel();
        jComboBoxAno = new javax.swing.JComboBox();
        jLabelMes = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox();
        jLabelClub = new javax.swing.JLabel();
        jComboBoxClub = new javax.swing.JComboBox();
        jButtonCargarDetalle = new javax.swing.JButton();
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
        setMaximumSize(new java.awt.Dimension(826, 720));
        setMinimumSize(new java.awt.Dimension(826, 720));
        setPreferredSize(new java.awt.Dimension(826, 720));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
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
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableEgresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Concepto Egreso", "Observación", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jPanel7.setPreferredSize(new java.awt.Dimension(607, 235));

        jLabelFecha.setText("Fecha");

        jLabelConceptoEgreso.setText("Concepto Egreso");

        jLabelComentario.setText("Observación");

        jTextFieldMonto.setEditable(false);

        jLabelMonto.setText("Monto");

        jComboBoxConceptoEgreso.setEnabled(false);
        jComboBoxConceptoEgreso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxConceptoEgresoItemStateChanged(evt);
            }
        });

        jButtonNuevoEgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Nuevo2.png"))); // NOI18N
        jButtonNuevoEgreso.setEnabled(false);
        jButtonNuevoEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoEgresoActionPerformed(evt);
            }
        });

        jTextPaneDetalle.setBackground(new java.awt.Color(240, 240, 240));
        jTextPaneDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextPaneDetalle.setEnabled(false);
        jTextPaneDetalle.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextPaneDetalle.setPreferredSize(new java.awt.Dimension(200, 114));
        jScrollPane3.setViewportView(jTextPaneDetalle);

        jDateChooserFecha.setEnabled(false);

        jPanelPagoCancha.setBorder(javax.swing.BorderFactory.createTitledBorder("Exclusivo para Pago de Canchas al Club"));
        jPanelPagoCancha.setEnabled(false);

        jLabelAno.setText("Año:");
        jLabelAno.setEnabled(false);

        jComboBoxAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
        jComboBoxAno.setEnabled(false);

        jLabelMes.setText("Mes:");
        jLabelMes.setEnabled(false);

        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxMes.setEnabled(false);

        jLabelClub.setText("Club:");
        jLabelClub.setEnabled(false);

        jComboBoxClub.setEnabled(false);

        jButtonCargarDetalle.setText("Mostrar Detalle");
        jButtonCargarDetalle.setEnabled(false);
        jButtonCargarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPagoCanchaLayout = new javax.swing.GroupLayout(jPanelPagoCancha);
        jPanelPagoCancha.setLayout(jPanelPagoCanchaLayout);
        jPanelPagoCanchaLayout.setHorizontalGroup(
            jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPagoCanchaLayout.createSequentialGroup()
                .addGroup(jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPagoCanchaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMes)
                            .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxClub, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelClub)))
                    .addGroup(jPanelPagoCanchaLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButtonCargarDetalle)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPagoCanchaLayout.setVerticalGroup(
            jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPagoCanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAno)
                    .addComponent(jLabelMes)
                    .addComponent(jLabelClub))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPagoCanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCargarDetalle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMonto)
                    .addComponent(jLabelConceptoEgreso)
                    .addComponent(jLabelFecha)
                    .addComponent(jLabelComentario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jDateChooserFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxConceptoEgreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jButtonNuevoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelPagoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFecha)
                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelConceptoEgreso)
                        .addComponent(jComboBoxConceptoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonNuevoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMonto)
                    .addComponent(jLabelMonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelComentario)
                    .addComponent(jPanelPagoCancha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Desde"));

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hasta"));

        jLabel7.setText("Año");

        jLabel8.setText("Mes");

        jComboBoxHastaMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxHastaMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxHastaMesItemStateChanged(evt);
            }
        });

        jComboBoxHastaAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "" }));
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
                .addGap(142, 142, 142)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        if (jTableEgresos.getRowCount() > 0) {
            String desde = "01/" + String.valueOf(jComboBoxDesdeMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxDesdeAño.getSelectedItem());
            String hasta = "01/" + String.valueOf(jComboBoxHastaMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxHastaAño.getSelectedItem());
            Date fechaHasta = null;
            Date fechaDesde = null;
            try {
                fechaDesde = new java.sql.Date(df.parse(String.valueOf(desde)).getTime());
                fechaHasta = new java.sql.Date(df.parse(String.valueOf(hasta)).getTime());
                
            } catch (ParseException ex) {
                Logger.getLogger(IGestionEgresos.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Egreso> unaListaEgresos = new ArrayList();
            Egreso unEgreso;
            int filas = this.modeloTablaGestionEgresos.getRowCount();
            for (int i = 0; i < filas; i++) {
                unEgreso = unaControladoraGlobal.getEgresoBD((Long) jTableEgresos.getValueAt(i, 0));
                unaListaEgresos.add(unEgreso);
            }
            GestionEgresosDS unaGestionEgresosDS = new GestionEgresosDS(unaControladoraGlobal, unaListaEgresos, fechaDesde, fechaHasta);
            unaGestionEgresosDS.verReporte();
        } else {
            JOptionPane.showMessageDialog(this, "La tabla esta Vacia");
        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        
        jTableEgresos.setEnabled(false);
        
        camposFiltroActivo(false);
        camposActivo(true);
        camposLimpiar();
        cargarComboBoxConceptoEgreso();
        cargarComboBoxClub();
        unEgresoSeleccionado = null;
        
        jDateChooserFecha.setDate(unaControladoraGlobal.fechaSistema());
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonNuevoEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoEgresoActionPerformed
        IConceptoEgresos unaIConceptoEgresos = new IConceptoEgresos(this, unaControladoraGlobal);
        unaIConceptoEgresos.pack();
        unaIConceptoEgresos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonNuevoEgresoActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cargarComboBoxConceptoEgreso();
        cargarComboBoxClub();
    }//GEN-LAST:event_formComponentShown

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            Date fecha = new java.sql.Date((jDateChooserFecha.getDate()).getTime());
            if (unEgresoSeleccionado == null) {
                unaControladoraGlobal.crearEgreso(fecha, Double.parseDouble(jTextFieldMonto.getText()), (ConceptoEgreso) jComboBoxConceptoEgreso.getSelectedItem(), jTextPaneDetalle.getText());
                JOptionPane.showMessageDialog(this, "Egreso Guardado");
            } else {
                unaControladoraGlobal.modificarEgreso(unEgresoSeleccionado, fecha, Double.parseDouble(jTextFieldMonto.getText()), (ConceptoEgreso) jComboBoxConceptoEgreso.getSelectedItem(), jTextPaneDetalle.getText(), unEgresoSeleccionado.isBorradoLogico());
                JOptionPane.showMessageDialog(this, "Egreso Modificado");
                unEgresoSeleccionado = null;
            }
            cargarFechasFiltrado();
            cargarTabla();
            jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);
            jTableEgresos.setEnabled(true);
            camposActivo(false);
            camposFiltroActivo(true);
            camposLimpiar();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        
        jTableEgresos.setEnabled(false);
        
        camposFiltroActivo(false);
        camposActivo(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        
        jTableEgresos.setEnabled(true);
        
        camposFiltroActivo(true);
        camposActivo(false);
        camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        
        camposActivo(false);
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el Egreso: " + unEgresoSeleccionado.getFecha() + " - " + unEgresoSeleccionado.getUnConceptoEgreso() + " - " + unEgresoSeleccionado.getMonto(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarEgreso(unEgresoSeleccionado);
            cargarFechasFiltrado();
            cargarTabla();
        }
        unEgresoSeleccionado = null;
        jTableEgresos.clearSelection();
        jTableEgresos.setEnabled(true);
        camposLimpiar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jComboBoxDesdeMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDesdeMesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxDesdeMesItemStateChanged

    private void jComboBoxDesdeAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDesdeAñoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxDesdeAñoItemStateChanged

    private void jComboBoxHastaMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHastaMesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxHastaMesItemStateChanged

    private void jComboBoxHastaAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHastaAñoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxHastaAñoItemStateChanged

    private void jComboBoxConceptoEgresoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxConceptoEgresoItemStateChanged
        if ((evt.getStateChange() == ItemEvent.SELECTED) && (jComboBoxConceptoEgreso.isEnabled())) {
            ConceptoEgreso unConceptoEgresoSeleccionado = (ConceptoEgreso) jComboBoxConceptoEgreso.getSelectedItem();
            jTextPaneDetalle.setText(unConceptoEgresoSeleccionado.getDetalle());
            if (unConceptoEgresoSeleccionado.getNombre().equals("Cancha")) {
                //habilitar los campos para seleccionar datos para la ayuda de cancha
                camposCanchaActivo(true);
                jComboBoxMes.setSelectedIndex(unaControladoraGlobal.fechaSistema().getMonth());
                jComboBoxAno.setSelectedIndex(unaControladoraGlobal.fechaSistema().getYear() + 1901 - Integer.parseInt(jComboBoxAno.getItemAt(1).toString()));
            } else {
                camposCanchaActivo(false);
                camposCanchaLimpiar();
            }
        }
    }//GEN-LAST:event_jComboBoxConceptoEgresoItemStateChanged

    private void jButtonCargarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarDetalleActionPerformed
        if (camposCanchaValidar()) {
            String datosCanchas = "Corresponde al pago de la Asociación por canchas al Club: " + jComboBoxClub.getSelectedItem() + enter + "Por el mes " + jComboBoxMes.getSelectedItem() + " del " + jComboBoxAno.getSelectedItem() + enter;
            for (Cancha unaCancha : ((Club) jComboBoxClub.getSelectedItem()).getCanchas()) {
                datosCanchas += (unaCancha.toString() + " - Usos en el mes: " + Integer.toString(unaControladoraGlobal.getCantCanchaOcupadaEnMes(unaCancha, this.jComboBoxAno.getSelectedIndex(), this.jComboBoxMes.getSelectedIndex())) + enter);
            }
            jTextPaneDetalle.setText(datosCanchas);            
        }
    }//GEN-LAST:event_jButtonCargarDetalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCargarDetalle;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonNuevoEgreso;
    private javax.swing.JComboBox jComboBoxAno;
    private javax.swing.JComboBox jComboBoxClub;
    private javax.swing.JComboBox jComboBoxConceptoEgreso;
    private javax.swing.JComboBox jComboBoxDesdeAño;
    private javax.swing.JComboBox jComboBoxDesdeMes;
    private javax.swing.JComboBox jComboBoxHastaAño;
    private javax.swing.JComboBox jComboBoxHastaMes;
    private javax.swing.JComboBox jComboBoxMes;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelClub;
    private javax.swing.JLabel jLabelComentario;
    private javax.swing.JLabel jLabelConceptoEgreso;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelMes;
    private javax.swing.JLabel jLabelMonto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelPagoCancha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableEgresos;
    private javax.swing.JTextField jTextFieldMonto;
    private javax.swing.JTextPane jTextPaneDetalle;
    // End of variables declaration//GEN-END:variables
}
