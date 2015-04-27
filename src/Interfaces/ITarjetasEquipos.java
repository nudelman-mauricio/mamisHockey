package Interfaces;

import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Jugadora;
import logicaNegocios.Partido;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;

public class ITarjetasEquipos extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private Equipo unEquipo;
    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaTarjetas;
    private DateFormat df = DateFormat.getDateInstance();

    public ITarjetasEquipos(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Equipo unEquipo) {
        initComponents();

        IMenuPrincipalInterface.jDesktopPane.add(this);
        IMenuPrincipalInterface.centrarYalFrente(this);

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unEquipo = unEquipo;
        this.modeloTablaTarjetas = (DefaultTableModel) jTableTarjeta.getModel();
        this.jTableTarjeta.getTableHeader().setReorderingAllowed(false);

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/tarjeta-roja-amarilla-verde.png")));
        this.setTitle("Tarjetas del Equipo: " + unEquipo.getNombre());

        //Carga del comboBox con todos los torneos
        List<Torneo> torneos = unaControladoraGlobal.getTorneosBD();
        jComboBoxTorneos.addItem("Todos");
        for (Torneo unTorneo : torneos) {
            if (unTorneo.getEquiposInscriptos().contains(this.unEquipo) && !unTorneo.isBorradoLogico()) {
                jComboBoxTorneos.addItem(unTorneo);
            }
        }
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
        HashSet<Socia> SociasDelEquipo = new HashSet();
        List<Torneo> torneos = new ArrayList();
        if (jComboBoxTorneos.getSelectedItem() != "Todos") {
            torneos.add((Torneo) jComboBoxTorneos.getSelectedItem());
        } else {
            torneos = unaControladoraGlobal.getTorneosBD();
        }
        for (Torneo unTorneo : torneos) {
            for (FechaTorneo unaFecha : unTorneo.getFechasTorneo()) {
                for (Partido unPartido : unaFecha.getPartidos()) {
                    if (unPartido.getUnEquipoLocal() == unEquipo) {
                        for (Jugadora unaJugadora : unPartido.getJugadorasLocales()) {
                            SociasDelEquipo.add(unaJugadora.getUnaSocia());
                        }
                    } else if (unPartido.getUnEquipoVisitante() == unEquipo) {
                        for (Jugadora unaJugadora : unPartido.getJugadorasVisitantes()) {
                            SociasDelEquipo.add(unaJugadora.getUnaSocia());
                        }
                    }
                }

            }

            for (Socia unaSocia : SociasDelEquipo) {
                for (Tarjeta unaTarjeta : unaSocia.getTarjetas()) {
                    if (unaTarjeta.getUnTorneo() == unTorneo) {
                        if (("Roja".equals(unaTarjeta.getTipo())) && (jCheckBoxRojas.isSelected())) {
                            SancionTribunal unaSancion = unaControladoraGlobal.getSancionTarjeta(unaTarjeta);
                            if (unaControladoraGlobal.getFechaTorneoTarjeta(unaTarjeta) != null) {
                                this.modeloTablaTarjetas.addRow(new Object[]{
                                    unaTarjeta.getIdTarjeta(),
                                    df.format(unaTarjeta.getFecha()),
                                    unaSocia,
                                    unaTarjeta.getTipo(),
                                    unaTarjeta.getUnTorneo().getNombre(),
                                    unaControladoraGlobal.getFechaTorneoTarjeta(unaTarjeta).getNumeroFecha(),
                                    unaTarjeta.isComputado(),
                                    unaSancion.getCantFechasCumplidas() + "/" + unaSancion.getCantFechas()});
                            }
                        } else if (("Amarilla".equals(unaTarjeta.getTipo())) && (jCheckBoxAmarillas.isSelected())) {
                            this.modeloTablaTarjetas.addRow(new Object[]{
                                unaTarjeta.getIdTarjeta(),
                                df.format(unaTarjeta.getFecha()),
                                unaSocia,
                                unaTarjeta.getTipo(),
                                unaTarjeta.getUnTorneo().getNombre(),
                                unaControladoraGlobal.getFechaTorneoTarjeta(unaTarjeta).getNumeroFecha(),
                                unaTarjeta.isComputado(),
                                " - "});
                        } else if (("Verde".equals(unaTarjeta.getTipo())) && (jCheckBoxVerdes.isSelected())) {
                            this.modeloTablaTarjetas.addRow(new Object[]{
                                unaTarjeta.getIdTarjeta(),
                                df.format(unaTarjeta.getFecha()),
                                unaSocia,
                                unaTarjeta.getTipo(),
                                unaTarjeta.getUnTorneo().getNombre(),
                                unaControladoraGlobal.getFechaTorneoTarjeta(unaTarjeta).getNumeroFecha(),
                                unaTarjeta.isComputado(),
                                " - "});
                        }

                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTarjeta = new javax.swing.JTable();
        jPanelBotones = new javax.swing.JPanel();
        jPanelFiltro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTorneos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jCheckBoxVerdes = new javax.swing.JCheckBox();
        jCheckBoxAmarillas = new javax.swing.JCheckBox();
        jCheckBoxRojas = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
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

        jTableTarjeta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Socia", "Tipo de Tarjeta", "Torneo", "Fecha n°", "Computado", "Cumplida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            jTableTarjeta.getColumnModel().getColumn(1).setMinWidth(70);
            jTableTarjeta.getColumnModel().getColumn(1).setPreferredWidth(70);
            jTableTarjeta.getColumnModel().getColumn(1).setMaxWidth(70);
            jTableTarjeta.getColumnModel().getColumn(2).setMinWidth(295);
            jTableTarjeta.getColumnModel().getColumn(2).setPreferredWidth(295);
            jTableTarjeta.getColumnModel().getColumn(2).setMaxWidth(295);
            jTableTarjeta.getColumnModel().getColumn(3).setMinWidth(90);
            jTableTarjeta.getColumnModel().getColumn(3).setPreferredWidth(90);
            jTableTarjeta.getColumnModel().getColumn(3).setMaxWidth(90);
            jTableTarjeta.getColumnModel().getColumn(5).setMinWidth(90);
            jTableTarjeta.getColumnModel().getColumn(5).setPreferredWidth(90);
            jTableTarjeta.getColumnModel().getColumn(5).setMaxWidth(90);
            jTableTarjeta.getColumnModel().getColumn(6).setMinWidth(70);
            jTableTarjeta.getColumnModel().getColumn(6).setPreferredWidth(70);
            jTableTarjeta.getColumnModel().getColumn(6).setMaxWidth(70);
            jTableTarjeta.getColumnModel().getColumn(7).setMinWidth(60);
            jTableTarjeta.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTableTarjeta.getColumnModel().getColumn(7).setMaxWidth(60);
        }

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelBotones.setPreferredSize(new java.awt.Dimension(253, 69));

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
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
                        .addGap(0, 0, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTorneosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTorneosItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarTabla();
        }
    }//GEN-LAST:event_jComboBoxTorneosItemStateChanged

    private void jCheckBoxVerdesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxVerdesItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBoxVerdesItemStateChanged

    private void jCheckBoxAmarillasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxAmarillasItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBoxAmarillasItemStateChanged

    private void jCheckBoxRojasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxRojasItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBoxRojasItemStateChanged

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxAmarillas;
    private javax.swing.JCheckBox jCheckBoxRojas;
    private javax.swing.JCheckBox jCheckBoxVerdes;
    private javax.swing.JComboBox jComboBoxTorneos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTarjeta;
    // End of variables declaration//GEN-END:variables
}
