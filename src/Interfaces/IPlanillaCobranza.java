package Interfaces;

import java.awt.Color;
import java.text.DateFormat;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Deuda;
import logicaNegocios.Equipo;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IPlanillaCobranza extends javax.swing.JInternalFrame {

    ControladoraGlobal unaControladoraGlobal;
    JInternalFrame unJInternalFrame;
    Equipo unEquipo;

    private DefaultTableModel modeloPlanillaCobranza;
    DateFormat df = DateFormat.getDateInstance();

    public IPlanillaCobranza(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Equipo unEquipo) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unEquipo = unEquipo;

        this.modeloPlanillaCobranza = (DefaultTableModel) jTablePlanillaCobranza.getModel();

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png")));
        this.setTitle(unEquipo.getNombre());
        IMenuPrincipalInterface.centrar(this);

        cargarCampos();

        jLabelFechaHoy.setText("Fecha:" + df.format(unaControladoraGlobal.fechaSistema()));

    }

    public void cargarCampos() {

        cargarPlanilla(modeloPlanillaCobranza);

        //Costo cancha
        //jTextFieldSubTotal.setText(SUMATORIA DE totales Socia);
        //Total
        jTextFieldTotal.setText(String.valueOf(Double.parseDouble((jTextFieldSubTotal.getText()) + Double.parseDouble(jTextFieldCostoCancha.getText()))));

    }

    public void cargarPlanilla(DefaultTableModel modeloTable) {
        limpiarTabla(modeloTable);

        for (Socia unSocia : unEquipo.getPlantel()) {
            Object[] unaLinea = new Object[11];
            for (Deuda unaDeuda : unaControladoraGlobal.getDeudasMesSocias(null, unSocia)) {
                switch (unaDeuda.getUnConceptoDeportivo().getConcepto()) {
                    case "Inscripción":
                        unaLinea[3] = unaDeuda;
                        break;
                    case "Re-Inscripción":
                        unaLinea[4] = unaDeuda;
                        break;
                    case "Fichaje":
                        unaLinea[5] = unaDeuda;
                        break;
                    case "Re-Fichaje":
                        unaLinea[6] = unaDeuda;
                        break;
                    case "Cuota Mensual":
                        unaLinea[7] = unaDeuda;
                        break;
                    case "Pase":
                        unaLinea[8] = unaDeuda;
                        break;
                    case "Otro":
                        //Arrglar el problema que puede haber mas de una deuda en concepto de OTRO
                        unaLinea[9] = unaDeuda;
                        break;
                    default:
                        //Por ahora pasa nada
                        break;
                }
                modeloTable.addRow(unaLinea);
            }
        }
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

    private boolean camposValidar() {
        boolean bandera = true;
        if (jComboBoxDelegadas.getSelectedIndex() == -1) {
            jLabelDelegadas.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelDelegadas.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePlanillaCobranza = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldTotal = new javax.swing.JTextField();
        jTextFieldSubTotal = new javax.swing.JTextField();
        jTextFieldCostoCancha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonPagar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelIdPlanilla = new javax.swing.JLabel();
        jLabelFechaHoy = new javax.swing.JLabel();
        jLabelDelegadas = new javax.swing.JLabel();
        jComboBoxDelegadas = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxPlantel = new javax.swing.JComboBox();

        jTablePlanillaCobranza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Pagar", "Fecha", "Concepto", "Observación", "Monto"
            }
        ));
        jScrollPane1.setViewportView(jTablePlanillaCobranza);
        if (jTablePlanillaCobranza.getColumnModel().getColumnCount() > 0) {
            jTablePlanillaCobranza.getColumnModel().getColumn(0).setMinWidth(40);
            jTablePlanillaCobranza.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTablePlanillaCobranza.getColumnModel().getColumn(0).setMaxWidth(40);
            jTablePlanillaCobranza.getColumnModel().getColumn(1).setMinWidth(80);
            jTablePlanillaCobranza.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTablePlanillaCobranza.getColumnModel().getColumn(1).setMaxWidth(80);
            jTablePlanillaCobranza.getColumnModel().getColumn(2).setMinWidth(200);
            jTablePlanillaCobranza.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTablePlanillaCobranza.getColumnModel().getColumn(2).setMaxWidth(200);
            jTablePlanillaCobranza.getColumnModel().getColumn(3).setMinWidth(400);
            jTablePlanillaCobranza.getColumnModel().getColumn(3).setPreferredWidth(400);
            jTablePlanillaCobranza.getColumnModel().getColumn(3).setMaxWidth(400);
            jTablePlanillaCobranza.getColumnModel().getColumn(4).setMinWidth(80);
            jTablePlanillaCobranza.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTablePlanillaCobranza.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTextFieldTotal.setEditable(false);

        jTextFieldSubTotal.setEditable(false);

        jTextFieldCostoCancha.setEditable(false);

        jLabel1.setText("SubTotal:");

        jLabel3.setText("Total:");

        jLabel2.setText("Cancha:");

        jButtonPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Pagar.png"))); // NOI18N
        jButtonPagar.setText("Pagar");
        jButtonPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCostoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCostoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Planilla Mensual \"Equipo\" - \"Mes\"");

        jLabelIdPlanilla.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelIdPlanilla.setText("ID Planilla de Pago");

        jLabelFechaHoy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFechaHoy.setText("Fecha: dd/MM/aaaa");

        jLabelDelegadas.setText("Pagado por:");

        jComboBoxDelegadas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Delegada", "Sub-Delegada" }));

        jLabel4.setText("Mostrar deudas de:");

        jComboBoxPlantel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPlantelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelFechaHoy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelIdPlanilla))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxPlantel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelDelegadas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxDelegadas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdPlanilla)
                    .addComponent(jLabelFechaHoy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDelegadas)
                    .addComponent(jComboBoxDelegadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxPlantel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed

    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void jComboBoxPlantelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPlantelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPlantelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JComboBox jComboBoxDelegadas;
    private javax.swing.JComboBox jComboBoxPlantel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDelegadas;
    private javax.swing.JLabel jLabelFechaHoy;
    private javax.swing.JLabel jLabelIdPlanilla;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePlanillaCobranza;
    private javax.swing.JTextField jTextFieldCostoCancha;
    private javax.swing.JTextField jTextFieldSubTotal;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables
}
