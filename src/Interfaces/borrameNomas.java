package Interfaces;

import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Cuota;
import logicaNegocios.Deuda;
import main.ControladoraGlobal;

public class borrameNomas extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaGestionIngresos;
    private DateFormat df = DateFormat.getDateInstance();

    public borrameNomas(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        IMenuPrincipalInterface.jDesktopPane.add(this);
        IMenuPrincipalInterface.centrarYalFrente(this);

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaGestionIngresos = (DefaultTableModel) jTableIngresos.getModel();

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png")));
        this.setTitle("Gestión de Ingresos Otro");
        this.jTableIngresos.getTableHeader().setReorderingAllowed(false);

        cargarTabla();
    }

    private void cargarTabla() {
        limpiarTabla();
        jTextFieldRestante.setText("");
        jTextFieldVencido.setText("");

        String desde = "01/" + String.valueOf(jComboBoxDesdeMes.getSelectedIndex() + 1) + "/" + String.valueOf(jComboBoxDesdeAño.getSelectedItem());
        String hasta = "01/" + String.valueOf(jComboBoxHastaMes.getSelectedIndex() + 2) + "/" + String.valueOf(jComboBoxHastaAño.getSelectedItem());
        Date fechaHasta = null;
        Date fechaDesde = null;
        double montoTotalRestante = 0;
        double montoTotalVencido = 0;
        try {
            fechaDesde = new java.sql.Date(df.parse(String.valueOf(desde)).getTime());
            fechaHasta = new java.sql.Date(df.parse(String.valueOf(hasta)).getTime());

            double montoRestante = 0;
            double montoVencido = 0;
            for (Deuda unaDeuda : this.unaControladoraGlobal.getDeudasEntreFechas(fechaDesde, fechaHasta)) {
                montoRestante = 0;
                montoVencido = 0;
                for (Cuota unaCuota : unaDeuda.getCuotas()) {
                    if (!unaCuota.isBorradoLogico()) {
                        if (!unaCuota.isSaldado()) {
                            if (unaCuota.getUnPagoCuota() != null) {
                                if (!unaCuota.getUnPagoCuota().isBorradoLogico()) {
                                    montoRestante += (unaCuota.getMonto() - unaCuota.getUnPagoCuota().getMonto());
                                    montoTotalRestante += montoRestante;
                                    if (unaControladoraGlobal.fechaSistema().after(unaCuota.getFechaVencimiento())) {
                                        montoVencido += (unaCuota.getMonto() - unaCuota.getUnPagoCuota().getMonto());
                                        montoTotalVencido += montoVencido;
                                    }
                                }
                            } else {
                                montoRestante += unaCuota.getMonto();
                                montoTotalRestante += montoRestante;
                                if (unaControladoraGlobal.fechaSistema().after(unaCuota.getFechaVencimiento())) {
                                    montoVencido += unaCuota.getMonto();
                                    montoTotalVencido += montoVencido;
                                }
                            }
                        }
                    }
                }
                if ((montoVencido != 0) || (montoRestante != 0)) {
                    this.modeloTablaGestionIngresos.addRow(new Object[]{unaDeuda.getIdDeuda(), df.format(unaDeuda.getFechaGeneracion()), unaDeuda.getUnConceptoDeportivo().getConcepto(), unaDeuda.getObservacion(), unaDeuda.getMontoTotal(), (unaDeuda.getMontoTotal() - montoRestante), montoRestante, montoVencido});
                }
            }
            jTextFieldRestante.setText(Double.toString(montoTotalRestante));
            jTextFieldVencido.setText(Double.toString(montoTotalVencido));
        } catch (ParseException ex) {
            Logger.getLogger(borrameNomas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpiarTabla() {
        int filas = this.modeloTablaGestionIngresos.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaGestionIngresos.removeRow(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIngresos = new javax.swing.JTable();
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
        jTextFieldVencido = new javax.swing.JTextField();
        jLabelVencido = new javax.swing.JLabel();
        jTextFieldRestante = new javax.swing.JTextField();
        jLabelRestante = new javax.swing.JLabel();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(900, 500));
        setMinimumSize(new java.awt.Dimension(900, 500));
        setPreferredSize(new java.awt.Dimension(900, 500));

        jTableIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha Generación", "Concepto", "Observación", "Monto Total", "Monto Pagado", "Monto Restante", "Monto Vencido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableIngresos);
        if (jTableIngresos.getColumnModel().getColumnCount() > 0) {
            jTableIngresos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableIngresos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableIngresos.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableIngresos.getColumnModel().getColumn(1).setMinWidth(100);
            jTableIngresos.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableIngresos.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableIngresos.getColumnModel().getColumn(2).setMinWidth(100);
            jTableIngresos.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableIngresos.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableIngresos.getColumnModel().getColumn(4).setMinWidth(90);
            jTableIngresos.getColumnModel().getColumn(4).setPreferredWidth(90);
            jTableIngresos.getColumnModel().getColumn(4).setMaxWidth(90);
            jTableIngresos.getColumnModel().getColumn(5).setMinWidth(90);
            jTableIngresos.getColumnModel().getColumn(5).setPreferredWidth(90);
            jTableIngresos.getColumnModel().getColumn(5).setMaxWidth(90);
            jTableIngresos.getColumnModel().getColumn(6).setMinWidth(90);
            jTableIngresos.getColumnModel().getColumn(6).setPreferredWidth(90);
            jTableIngresos.getColumnModel().getColumn(6).setMaxWidth(90);
            jTableIngresos.getColumnModel().getColumn(7).setMinWidth(90);
            jTableIngresos.getColumnModel().getColumn(7).setPreferredWidth(90);
            jTableIngresos.getColumnModel().getColumn(7).setMaxWidth(90);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
                .addGap(140, 140, 140)
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

        jLabelVencido.setText("Monto Total Vencido");

        jLabelRestante.setText("Monto Total a Ingresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelVencido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVencido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelRestante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRestante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldVencido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVencido))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableIngresos;
    private javax.swing.JTextField jTextFieldRestante;
    private javax.swing.JTextField jTextFieldVencido;
    // End of variables declaration//GEN-END:variables
}
