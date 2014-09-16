package Interfaces;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Partido;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import logicaNegocios.Torneo;
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
        
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;
        this.modeloTablaTarjetas = (DefaultTableModel) jTableTarjeta.getModel();

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/tarjeta-roja-amarilla-verde.png")));
        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());
        IMenuPrincipalInterface.centrar(this);

        //Carga del comboBox con todos los torneos y el primero con "Todos los torneos"
        Vector VTorneo = new Vector();
        VTorneo.add("Todos los Torneos");        
        VTorneo.addAll(unaControladoraGlobal.getTorneoParticipoSocia(unaSocia));
        this.jComboBoxTorneos.setModel(new DefaultComboBoxModel(VTorneo));
        this.jTextPaneMotivo.setBackground(new Color(228, 231, 237));
    }
    
    private void limpiarTabla() {
        int filas = modeloTablaTarjetas.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaTarjetas.removeRow(0);
        }
    }
    
    public void cargarTabla() {
        limpiarTabla();
        Partido unPartido;
        Torneo unTorneo;
        for (Tarjeta unaTarjeta : unaSocia.getTarjetas()) {
            if ((unaTarjeta.getTipo().equals("Verde") && jCheckBoxVerdes.isSelected()) || (unaTarjeta.getTipo().equals("Amarilla") && jCheckBoxAmarillas.isSelected()) || (unaTarjeta.getTipo().equals("Rojas") && jCheckBoxRojas.isSelected())) {
                unPartido = unaControladoraGlobal.getPartidoTarjeta(unaTarjeta);
                unTorneo = unaControladoraGlobal.getTorneoTarjeta(unaTarjeta);
                if ((jComboBoxTorneos.getSelectedIndex() == 0) || (unTorneo.equals(jComboBoxTorneos.getSelectedItem()))) {
                    this.modeloTablaTarjetas.addRow(new Object[]{
                        df.format(unPartido.getFecha()),
                        unaTarjeta.getTipo(),
                        unTorneo.getNombre(),
                        unPartido.toString()});
                }
            }
        }
    }
    
    public void camposCargar() {
        if (jTableTarjeta.getSelectedRow() > -1) {
            if (jTableTarjeta.getValueAt(jTableTarjeta.getSelectedRow(), 0) != null) {
                unaTarjetaSeleccionada = unaControladoraGlobal.getTarjetaBD((Long) jTableTarjeta.getValueAt(jTableTarjeta.getSelectedRow(), 0));
                Partido unPartido = unaControladoraGlobal.getPartidoTarjeta(unaTarjetaSeleccionada);
                jTextFieldFecha.setText(df.format(unPartido.getFecha()));
                jTextFieldTipoTarjeta.setText(unaTarjetaSeleccionada.getTipo());
                jTextFieldTorneo.setText(unaControladoraGlobal.getTorneoTarjeta(unaTarjetaSeleccionada).getNombre());
                jTextFieldPartido.setText(unPartido.toString());
                jTextPaneMotivo.setText(unaTarjetaSeleccionada.getMotivo());
                SancionTribunal unaSancionTribunal = unaControladoraGlobal.getSancionTarjeta(unaTarjetaSeleccionada);
                if (unaSancionTribunal.getVencimiento() != null) {
                    this.jTextFieldPenalizacion.setText(df.format(unaSancionTribunal.getVencimiento()));
                    this.jRadioButtonHasta.setSelected(true);
                }
                if (unaSancionTribunal.getCantFechas() != 0) {
                    this.jTextFieldPenalizacion.setText(Integer.toString(unaSancionTribunal.getCantFechas()));
                    this.jRadioButtonCantFechas.setSelected(true);
                }
                this.jTextFieldFechasCumplidas.setText(Integer.toString(unaSancionTribunal.getCantFechasCumplidas()));
                jButtonImprimir.setEnabled(true);
            }
        }
    }
    
    public void camposLimpiar() {
        jTextFieldFecha.setText("");
        jTextFieldTipoTarjeta.setText("");
        jTextFieldTorneo.setText("");
        jTextFieldPartido.setText("");
        jTextPaneMotivo.setText("");
        buttonGroup1.clearSelection();
        jTextFieldPenalizacion.setText("");
        jTextFieldFechasCumplidas.setText("");
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
        jTextFieldFecha = new javax.swing.JTextField();
        jLabelFecha = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneMotivo = new javax.swing.JTextPane();
        jPanelPenalizacion = new javax.swing.JPanel();
        jRadioButtonCantFechas = new javax.swing.JRadioButton();
        jRadioButtonHasta = new javax.swing.JRadioButton();
        jTextFieldPenalizacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFechasCumplidas = new javax.swing.JTextField();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(726, 630));
        setMinimumSize(new java.awt.Dimension(726, 630));
        setPreferredSize(new java.awt.Dimension(726, 630));
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
        jButtonImprimir.setEnabled(false);
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

        jCheckBoxVerdes.setText("Verdes");

        jCheckBoxAmarillas.setText("Amarillas");

        jCheckBoxRojas.setText("Rojas");

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
                "Fecha", "Tipo de Tarjeta", "Torneo", "Partido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTarjeta);

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

        jTextFieldFecha.setEditable(false);
        jTextFieldFecha.setText("dd/mm/aaaa");

        jLabelFecha.setText("Fecha");

        jTextPaneMotivo.setBackground(new java.awt.Color(228, 231, 237));
        jScrollPane3.setViewportView(jTextPaneMotivo);

        jPanelPenalizacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sanción", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(jRadioButtonCantFechas);
        jRadioButtonCantFechas.setText("Cant. Fechas");

        buttonGroup1.add(jRadioButtonHasta);
        jRadioButtonHasta.setText("Hasta");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMotivo)
                    .addComponent(jLabelTorneo)
                    .addComponent(jLabelFecha)
                    .addComponent(jLabelPartido)
                    .addComponent(jLabelTipoTarjeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTipoTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jTextFieldPartido, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jTextFieldTorneo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jPanelPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFecha)
                            .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTipoTarjeta)
                            .addComponent(jTextFieldTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTorneo)
                            .addComponent(jTextFieldTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPartido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jLabelMotivo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jComboBoxTorneosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTorneosItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxTorneosItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JCheckBox jCheckBoxAmarillas;
    private javax.swing.JCheckBox jCheckBoxRojas;
    private javax.swing.JCheckBox jCheckBoxVerdes;
    private javax.swing.JComboBox jComboBoxTorneos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelMotivo;
    private javax.swing.JLabel jLabelPartido;
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
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldFechasCumplidas;
    private javax.swing.JTextField jTextFieldPartido;
    private javax.swing.JTextField jTextFieldPenalizacion;
    private javax.swing.JTextField jTextFieldTipoTarjeta;
    private javax.swing.JTextField jTextFieldTorneo;
    private javax.swing.JTextPane jTextPaneMotivo;
    // End of variables declaration//GEN-END:variables
}
