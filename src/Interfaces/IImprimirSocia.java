package Interfaces;

import DataSources.HistorialSociaDS;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IImprimirSocia extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Socia unaSociaSeleccionada = null;
    private DateFormat df = DateFormat.getDateInstance();

    public IImprimirSocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unaSociaSeleccionada = unaSocia;

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/printer.png"))); //Icono Ventana
        this.setTitle("Reportes para Imrimir de: " + unaSocia.toString()); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar
        jLabelNombreJugadora.setText(unaSocia.toString());
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (!jRadioButtonTarjetas.isSelected() && !jRadioButtonPases.isSelected() && !jRadioButtonSanciones.isSelected() && !jRadioButtonErgometrias.isSelected() && !jRadioButtonContabilidad.isSelected() && !jRadioButtonEstados.isSelected()) {
            bandera = false;
        }
        if ((jComboBoxDesdeMes.getSelectedItem()== jComboBoxHastaMes.getSelectedItem() && (jComboBoxDesdeAño.getSelectedItem() == jComboBoxHastaAño.getSelectedItem()))) {
            JOptionPane.showMessageDialog(this, "Las fechas son iguales. La diferencia debe ser como mínimo un mes.");
            return false;
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una opción");
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabelNombreJugadora = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonTarjetas = new javax.swing.JRadioButton();
        jRadioButtonPases = new javax.swing.JRadioButton();
        jRadioButtonSanciones = new javax.swing.JRadioButton();
        jRadioButtonErgometrias = new javax.swing.JRadioButton();
        jRadioButtonContabilidad = new javax.swing.JRadioButton();
        jRadioButtonEstados = new javax.swing.JRadioButton();
        jPanelBotones = new javax.swing.JPanel();
        jButtonImprimir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxDesdeMes = new javax.swing.JComboBox();
        jComboBoxDesdeAño = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxHastaMes = new javax.swing.JComboBox();
        jComboBoxHastaAño = new javax.swing.JComboBox();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(545, 387));
        setMinimumSize(new java.awt.Dimension(545, 387));
        setPreferredSize(new java.awt.Dimension(545, 387));
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

        jLabelNombreJugadora.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNombreJugadora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreJugadora.setText("María Antonieta de las Nieves como la Chilindrina");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle a Imprimir", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel4.setName(""); // NOI18N

        buttonGroup1.add(jRadioButtonTarjetas);
        jRadioButtonTarjetas.setText("Historial Tarjetas");

        buttonGroup1.add(jRadioButtonPases);
        jRadioButtonPases.setText("Historial Pases");

        buttonGroup1.add(jRadioButtonSanciones);
        jRadioButtonSanciones.setText("Historial Sanciones");

        buttonGroup1.add(jRadioButtonErgometrias);
        jRadioButtonErgometrias.setText("Historial Ergometrías");

        buttonGroup1.add(jRadioButtonContabilidad);
        jRadioButtonContabilidad.setText("Historial Contable");

        buttonGroup1.add(jRadioButtonEstados);
        jRadioButtonEstados.setText("Historial Estados");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonTarjetas)
                    .addComponent(jRadioButtonPases)
                    .addComponent(jRadioButtonSanciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonErgometrias)
                    .addComponent(jRadioButtonContabilidad)
                    .addComponent(jRadioButtonEstados))
                .addGap(57, 57, 57))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButtonTarjetas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonPases)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonSanciones))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButtonErgometrias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonContabilidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonEstados)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/cancel.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
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
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonImprimir))
                .addGap(3, 3, 3))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Desde"));

        jLabel1.setText("Año");

        jLabel2.setText("Mes");

        jComboBoxDesdeMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Julio", "Junio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jComboBoxDesdeAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hasta"));

        jLabel7.setText("Año");

        jLabel8.setText("Mes");

        jComboBoxHastaMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Julio", "Junio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jComboBoxHastaAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
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
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabelNombreJugadora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNombreJugadora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.setVisible(false);
        this.dispose();
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        if (camposValidar()) {
            String desde = "01/" + String.valueOf(jComboBoxDesdeMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxDesdeAño.getSelectedIndex() + 2010);
            String hasta = "01/" + String.valueOf(jComboBoxHastaMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxHastaAño.getSelectedIndex() + 2010);
            try {
                Date fechaDesde = new java.sql.Date(df.parse(String.valueOf(desde)).getTime());
                Date fechaHasta = new java.sql.Date(df.parse(String.valueOf(hasta)).getTime());

                if (jRadioButtonTarjetas.isSelected()) {
                    //llamar reporte
                }
                if (jRadioButtonPases.isSelected()) {
                    //llamar reporte
                }
                if (jRadioButtonSanciones.isSelected()) {
                    //llamar reporte
                }
                if (jRadioButtonErgometrias.isSelected()) {
                    //llamar reporte
                }
                if (jRadioButtonContabilidad.isSelected()) {
                    //llamar reporte
                }
                if (jRadioButtonEstados.isSelected()) {
                    //llamar reporte
                }

                HistorialSociaDS unHistorialSociaDS = new HistorialSociaDS(unaControladoraGlobal, fechaDesde, fechaHasta, unaSociaSeleccionada);
                unHistorialSociaDS.verReporte();
                
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Error en las fechas. Verifique e intente nuevamente.");
            }

        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JComboBox jComboBoxDesdeAño;
    private javax.swing.JComboBox jComboBoxDesdeMes;
    private javax.swing.JComboBox jComboBoxHastaAño;
    private javax.swing.JComboBox jComboBoxHastaMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelNombreJugadora;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JRadioButton jRadioButtonContabilidad;
    private javax.swing.JRadioButton jRadioButtonErgometrias;
    private javax.swing.JRadioButton jRadioButtonEstados;
    private javax.swing.JRadioButton jRadioButtonPases;
    private javax.swing.JRadioButton jRadioButtonSanciones;
    private javax.swing.JRadioButton jRadioButtonTarjetas;
    // End of variables declaration//GEN-END:variables
}
