package Interfaces;

import DataSources.TarjetaDS;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Partido;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import main.ControladoraGlobal;

public class ITarjeta extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private Socia unaSocia;
    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaTarjetas;
    private Tarjeta unaTarjetaSeleccionada = null;
    private DateFormat df = DateFormat.getDateInstance();

    public ITarjeta(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();

        IMenuPrincipal.jDesktopPane.add(this);
        IMenuPrincipal.centrarYalFrente(this);

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;
        this.modeloTablaTarjetas = (DefaultTableModel) jTableTarjeta.getModel();
        this.jTableTarjeta.getTableHeader().setReorderingAllowed(false);

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("/Iconos Nuevos/tarjeta-roja-amarilla-verde.png")));
        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());

        //Carga del comboBox con todos los torneos y el primero con "Todos los torneos"
        Vector VTorneo = new Vector();
        VTorneo.add("Todos los Torneos");
        VTorneo.addAll(unaControladoraGlobal.getTorneoParticipoSocia(unaSocia));
        this.jComboBoxTorneos.setModel(new DefaultComboBoxModel(VTorneo));
        this.jTextPaneMotivo.setBackground(new Color(228, 231, 237));

        cargarTabla();
    }

    private void limpiarTabla() {
        int filas = modeloTablaTarjetas.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaTarjetas.removeRow(0);
        }
    }

    public void cargarTabla() {
        limpiarTabla();
        String partido;
        int numeroFechaTorneo = 0;
        Partido unPartidoDeTarjeta = null;
        for (Tarjeta unaTarjeta : unaSocia.getTarjetas()) {
            if (!unaTarjeta.isBorradoLogico()) {
                if ((unaTarjeta.getTipo().equals("Verde") && jCheckBoxVerdes.isSelected()) || (unaTarjeta.getTipo().equals("Amarilla") && jCheckBoxAmarillas.isSelected()) || (unaTarjeta.getTipo().equals("Roja") && jCheckBoxRojas.isSelected())) {
                    if ((jComboBoxTorneos.getSelectedIndex() == 0) || (unaTarjeta.getUnTorneo().equals(jComboBoxTorneos.getSelectedItem()))) {
                        unPartidoDeTarjeta = unaControladoraGlobal.getPartidoTarjeta(unaTarjeta);
                        if (unPartidoDeTarjeta != null) {
                            partido = unPartidoDeTarjeta.toString();
                            FechaTorneo fechaTorneoDeTarjeta = unaControladoraGlobal.getFechaTorneoDePartido(unPartidoDeTarjeta);
                            if (fechaTorneoDeTarjeta != null) {
                                numeroFechaTorneo = fechaTorneoDeTarjeta.getNumeroFecha();
                            }
                        } else {
                            partido = "ACUMULADA";
                            numeroFechaTorneo = 0;
                        }
                        this.modeloTablaTarjetas.addRow(new Object[]{
                            unaTarjeta.getIdTarjeta(),
                            df.format(unaTarjeta.getFecha()),
                            unaTarjeta.getTipo(),
                            unaTarjeta.getUnTorneo().getNombre(),
                            numeroFechaTorneo,
                            partido,
                            unaTarjeta.isComputado()});
                    }
                }
            }
        }
    }

    public void camposCargar() {
        if (jTableTarjeta.getSelectedRow() > -1) {
            if (jTableTarjeta.getValueAt(jTableTarjeta.getSelectedRow(), 0) != null) {
                camposLimpiar();
                unaTarjetaSeleccionada = unaControladoraGlobal.getTarjetaBD((Long) jTableTarjeta.getValueAt(jTableTarjeta.getSelectedRow(), 0));
                String partido = "ACUMULADA";
                if (unaControladoraGlobal.getPartidoTarjeta(unaTarjetaSeleccionada) != null) {
                    partido = unaControladoraGlobal.getPartidoTarjeta(unaTarjetaSeleccionada).toString();
                }
                jDateChooserFecha.setDate(unaTarjetaSeleccionada.getFecha());
                jTextFieldTipoTarjeta.setText(unaTarjetaSeleccionada.getTipo());
                jTextFieldTorneo.setText(unaTarjetaSeleccionada.getUnTorneo().getNombre());
                jTextFieldPartido.setText(partido);
                jTextFieldFechaTorneo.setText(Integer.toString(unaControladoraGlobal.getFechaTorneoDePartido(unaControladoraGlobal.getPartidoTarjeta(unaTarjetaSeleccionada)).getNumeroFecha()));
                jTextFieldTiempo.setText(unaTarjetaSeleccionada.getTiempo());
                jTextFieldMinuto.setText(unaTarjetaSeleccionada.getMinuto());
                jTextPaneMotivo.setText(unaTarjetaSeleccionada.getMotivo());

                SancionTribunal unaSancionTribunal = unaControladoraGlobal.getSancionTarjeta(unaTarjetaSeleccionada);
                if (unaSancionTribunal != null) {
                    if (unaSancionTribunal.getVencimiento() != null) {
                        this.jTextFieldPenalizacion.setText(df.format(unaSancionTribunal.getVencimiento()));
                        this.jRadioButtonHasta.setSelected(true);
                    }
                    if (unaSancionTribunal.getCantFechas() != 0) {
                        this.jTextFieldPenalizacion.setText(Integer.toString(unaSancionTribunal.getCantFechas()));
                        this.jRadioButtonCantFechas.setSelected(true);
                    }
                    this.jTextFieldFechasCumplidas.setText(Integer.toString(unaSancionTribunal.getCantFechasCumplidas()));
                }
                jCheckBoxComputado.setSelected(unaTarjetaSeleccionada.isComputado());
                jButtonImprimir.setEnabled(true);
            }
        }
    }

    public void camposLimpiar() {
        jDateChooserFecha.setDate(null);
        jTextFieldTipoTarjeta.setText("");
        jTextFieldTorneo.setText("");
        jTextFieldPartido.setText("");
        jTextFieldFechaTorneo.setText("");
        jTextFieldTiempo.setText("");
        jTextFieldMinuto.setText("");
        jTextPaneMotivo.setText("");
        buttonGroup1.clearSelection();
        jRadioButtonCantFechas.setSelected(false);
        jRadioButtonHasta.setSelected(false);
        jTextFieldPenalizacion.setText("");
        jTextFieldFechasCumplidas.setText("");
        jCheckBoxComputado.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelBotones = new javax.swing.JPanel();
        jButtonImprimir = new javax.swing.JButton();
        jPanelFiltro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTorneos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jCheckBoxVerdes = new javax.swing.JCheckBox();
        jCheckBoxAmarillas = new javax.swing.JCheckBox();
        jCheckBoxRojas = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTarjeta = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelMotivo = new javax.swing.JLabel();
        jLabelPartido = new javax.swing.JLabel();
        jTextFieldPartido = new javax.swing.JTextField();
        jTextFieldTorneo = new javax.swing.JTextField();
        jLabelTorneo = new javax.swing.JLabel();
        jLabelTipoTarjeta = new javax.swing.JLabel();
        jTextFieldTipoTarjeta = new javax.swing.JTextField();
        jLabelFecha = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneMotivo = new javax.swing.JTextPane();
        jPanelPenalizacion = new javax.swing.JPanel();
        jRadioButtonCantFechas = new javax.swing.JRadioButton();
        jRadioButtonHasta = new javax.swing.JRadioButton();
        jTextFieldPenalizacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFechasCumplidas = new javax.swing.JTextField();
        jCheckBoxComputado = new javax.swing.JCheckBox();
        jTextFieldTiempo = new javax.swing.JTextField();
        jLabelPartido1 = new javax.swing.JLabel();
        jTextFieldMinuto = new javax.swing.JTextField();
        jLabelPartido2 = new javax.swing.JLabel();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jLabelFechaTorneo = new javax.swing.JLabel();
        jTextFieldFechaTorneo = new javax.swing.JTextField();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(726, 652));
        setMinimumSize(new java.awt.Dimension(726, 652));
        setPreferredSize(new java.awt.Dimension(726, 652));
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
        jPanelBotones.setPreferredSize(new java.awt.Dimension(253, 69));

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonImprimir)
                .addGap(3, 3, 3))
        );

        jPanelFiltro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Filtro2.png"))); // NOI18N
        jLabel1.setText("Filtrar");

        jComboBoxTorneos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTorneosItemStateChanged(evt);
            }
        });

        jLabel3.setText("Torneos:");

        jCheckBoxVerdes.setSelected(true);
        jCheckBoxVerdes.setText("Verdes");
        jCheckBoxVerdes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxVerdesItemStateChanged(evt);
            }
        });

        jCheckBoxAmarillas.setSelected(true);
        jCheckBoxAmarillas.setText("Amarillas");
        jCheckBoxAmarillas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxAmarillasItemStateChanged(evt);
            }
        });

        jCheckBoxRojas.setSelected(true);
        jCheckBoxRojas.setText("Rojas");
        jCheckBoxRojas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxRojasItemStateChanged(evt);
            }
        });

        jLabel2.setText("Tarjetas:");

        javax.swing.GroupLayout jPanelFiltroLayout = new javax.swing.GroupLayout(jPanelFiltro);
        jPanelFiltro.setLayout(jPanelFiltroLayout);
        jPanelFiltroLayout.setHorizontalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFiltroLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxRojas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxAmarillas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxVerdes)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addComponent(jComboBoxTorneos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanelFiltroLayout.createSequentialGroup()
                        .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxTorneos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxVerdes)
                            .addComponent(jCheckBoxAmarillas)
                            .addComponent(jCheckBoxRojas)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableTarjeta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Tipo de Tarjeta", "Torneo", "Fecha", "Partido", "Computado"
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
        jScrollPane1.setViewportView(jTableTarjeta);
        if (jTableTarjeta.getColumnModel().getColumnCount() > 0) {
            jTableTarjeta.getColumnModel().getColumn(0).setMinWidth(0);
            jTableTarjeta.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableTarjeta.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableTarjeta.getColumnModel().getColumn(1).setMinWidth(80);
            jTableTarjeta.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTableTarjeta.getColumnModel().getColumn(1).setMaxWidth(80);
            jTableTarjeta.getColumnModel().getColumn(2).setMinWidth(100);
            jTableTarjeta.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableTarjeta.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableTarjeta.getColumnModel().getColumn(4).setMinWidth(80);
            jTableTarjeta.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableTarjeta.getColumnModel().getColumn(4).setMaxWidth(80);
            jTableTarjeta.getColumnModel().getColumn(6).setMinWidth(80);
            jTableTarjeta.getColumnModel().getColumn(6).setPreferredWidth(80);
            jTableTarjeta.getColumnModel().getColumn(6).setMaxWidth(80);
        }
        jTableTarjeta.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelMotivo.setText("Motivo");

        jLabelPartido.setText("Partido");

        jTextFieldPartido.setEditable(false);

        jTextFieldTorneo.setEditable(false);

        jLabelTorneo.setText("Torneo");

        jLabelTipoTarjeta.setText("Tipo de Tarjeta");

        jTextFieldTipoTarjeta.setEditable(false);

        jLabelFecha.setText("Fecha");

        jTextPaneMotivo.setEditable(false);
        jTextPaneMotivo.setBackground(new java.awt.Color(228, 231, 237));
        jScrollPane3.setViewportView(jTextPaneMotivo);

        jPanelPenalizacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sanción", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(jRadioButtonCantFechas);
        jRadioButtonCantFechas.setText("Cant. Fechas");
        jRadioButtonCantFechas.setEnabled(false);

        buttonGroup1.add(jRadioButtonHasta);
        jRadioButtonHasta.setText("Hasta");
        jRadioButtonHasta.setEnabled(false);

        jTextFieldPenalizacion.setEditable(false);

        jLabel4.setText("Fechas Cumplidas");

        jTextFieldFechasCumplidas.setEditable(false);

        javax.swing.GroupLayout jPanelPenalizacionLayout = new javax.swing.GroupLayout(jPanelPenalizacion);
        jPanelPenalizacion.setLayout(jPanelPenalizacionLayout);
        jPanelPenalizacionLayout.setHorizontalGroup(
            jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPenalizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPenalizacionLayout.createSequentialGroup()
                        .addComponent(jRadioButtonCantFechas)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonHasta))
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldFechasCumplidas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPenalizacionLayout.setVerticalGroup(
            jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPenalizacionLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonCantFechas)
                    .addComponent(jRadioButtonHasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFechasCumplidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jCheckBoxComputado.setText("Computado");
        jCheckBoxComputado.setEnabled(false);

        jTextFieldTiempo.setEditable(false);

        jLabelPartido1.setText("Tiempo");

        jTextFieldMinuto.setEditable(false);

        jLabelPartido2.setText("Minuto");

        jDateChooserFecha.setEnabled(false);

        jLabelFechaTorneo.setText("Fecha");

        jTextFieldFechaTorneo.setEditable(false);

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTorneo)
                    .addComponent(jLabelFecha)
                    .addComponent(jLabelTipoTarjeta)
                    .addComponent(jLabelPartido)
                    .addComponent(jLabelPartido1)
                    .addComponent(jLabelMotivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTipoTarjeta)
                            .addComponent(jTextFieldPartido)
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jTextFieldTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelPartido2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooserFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jTextFieldTorneo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelFechaTorneo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldFechaTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelPenalizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jCheckBoxComputado)
                                .addGap(52, 52, 52))))
                    .addComponent(jScrollPane3))
                .addGap(120, 120, 120))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFecha)
                            .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTipoTarjeta)
                            .addComponent(jTextFieldTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTorneo)
                            .addComponent(jTextFieldTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaTorneo)
                            .addComponent(jTextFieldFechaTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPartido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPartido1)
                            .addComponent(jTextFieldMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPartido2)))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanelPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxComputado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMotivo)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        List<Tarjeta> listaTarjetas = new ArrayList();
        int filas = this.modeloTablaTarjetas.getRowCount();
        for (int i = 0; i < filas; i++) {
            listaTarjetas.add(unaControladoraGlobal.getTarjetaBD((Long) jTableTarjeta.getValueAt(i, 0)));
        }
        String tipo = "";
        if (jCheckBoxAmarillas.isSelected() && jCheckBoxRojas.isSelected() && !jCheckBoxVerdes.isSelected()) {
            tipo = "Amarillas - Rojas";
        } else {
            if (jCheckBoxAmarillas.isSelected() && !jCheckBoxRojas.isSelected() && !jCheckBoxVerdes.isSelected()) {
                tipo = "Amarillas";
            } else {
                if (jCheckBoxAmarillas.isSelected() && !jCheckBoxRojas.isSelected() && jCheckBoxVerdes.isSelected()) {
                    tipo = "Amarillas - Verdes";
                } else {
                    if (!jCheckBoxAmarillas.isSelected() && jCheckBoxRojas.isSelected() && !jCheckBoxVerdes.isSelected()) {
                        tipo = "Rojas";
                    } else {
                        if (!jCheckBoxAmarillas.isSelected() && !jCheckBoxRojas.isSelected() && jCheckBoxVerdes.isSelected()) {
                            tipo = "Verdes";
                        } else {
                            if (!jCheckBoxAmarillas.isSelected() && jCheckBoxRojas.isSelected() && jCheckBoxVerdes.isSelected()) {
                                tipo = "Rojas-Verdes";
                            }
                        }
                    }

                }
            }
        }
        TarjetaDS unaTarjetaDS = new TarjetaDS(unaControladoraGlobal, listaTarjetas, unaSocia, tipo);
        unaTarjetaDS.verReporte();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jComboBoxTorneosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTorneosItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxTorneosItemStateChanged

    private void jCheckBoxRojasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxRojasItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBoxRojasItemStateChanged

    private void jCheckBoxAmarillasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxAmarillasItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBoxAmarillasItemStateChanged

    private void jCheckBoxVerdesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxVerdesItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBoxVerdesItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JCheckBox jCheckBoxAmarillas;
    private javax.swing.JCheckBox jCheckBoxComputado;
    private javax.swing.JCheckBox jCheckBoxRojas;
    private javax.swing.JCheckBox jCheckBoxVerdes;
    private javax.swing.JComboBox jComboBoxTorneos;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFechaTorneo;
    private javax.swing.JLabel jLabelMotivo;
    private javax.swing.JLabel jLabelPartido;
    private javax.swing.JLabel jLabelPartido1;
    private javax.swing.JLabel jLabelPartido2;
    private javax.swing.JLabel jLabelTipoTarjeta;
    private javax.swing.JLabel jLabelTorneo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelPenalizacion;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JRadioButton jRadioButtonCantFechas;
    private javax.swing.JRadioButton jRadioButtonHasta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableTarjeta;
    private javax.swing.JTextField jTextFieldFechaTorneo;
    private javax.swing.JTextField jTextFieldFechasCumplidas;
    private javax.swing.JTextField jTextFieldMinuto;
    private javax.swing.JTextField jTextFieldPartido;
    private javax.swing.JTextField jTextFieldPenalizacion;
    private javax.swing.JTextField jTextFieldTiempo;
    private javax.swing.JTextField jTextFieldTipoTarjeta;
    private javax.swing.JTextField jTextFieldTorneo;
    private javax.swing.JTextPane jTextPaneMotivo;
    // End of variables declaration//GEN-END:variables
}
