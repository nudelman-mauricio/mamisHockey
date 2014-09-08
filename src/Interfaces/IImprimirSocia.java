package Interfaces;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IImprimirSocia extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Socia unaSociaSeleccionada = null;

    public IImprimirSocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unaSociaSeleccionada = unaSocia;

        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/printer.png"))); //Icono Ventana
        this.setTitle("Reportes para Imrimir de: " + unaSocia.getApellido() + ", " + unaSocia.getNombre()); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar
        jLabelNombreJugadora.setText(unaSocia.getApellido() + ", " + unaSocia.getNombre());
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (!jRadioButtonDatosPersonales.isSelected() && !jRadioButtonTarjetas.isSelected() && !jRadioButtonPases.isSelected() && !jRadioButtonSanciones.isSelected() && !jRadioButtonErgometrias.isSelected() && !jRadioButtonContabilidad.isSelected() && !jRadioButtonEstados.isSelected()) {
            bandera = false;
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
        jRadioButtonDatosPersonales = new javax.swing.JRadioButton();
        jRadioButtonTarjetas = new javax.swing.JRadioButton();
        jRadioButtonPases = new javax.swing.JRadioButton();
        jRadioButtonSanciones = new javax.swing.JRadioButton();
        jRadioButtonErgometrias = new javax.swing.JRadioButton();
        jRadioButtonContabilidad = new javax.swing.JRadioButton();
        jRadioButtonEstados = new javax.swing.JRadioButton();
        jPanelBotones = new javax.swing.JPanel();
        jButtonImprimir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setClosable(true);

        jLabelNombreJugadora.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNombreJugadora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreJugadora.setText("María Antonieta de las Nieves como la Chilindrina");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle a Imprimir", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel4.setName(""); // NOI18N

        buttonGroup1.add(jRadioButtonDatosPersonales);
        jRadioButtonDatosPersonales.setText("Datos Personales");

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
                    .addComponent(jRadioButtonDatosPersonales)
                    .addComponent(jRadioButtonTarjetas)
                    .addComponent(jRadioButtonPases)
                    .addComponent(jRadioButtonSanciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDatosPersonales)
                    .addComponent(jRadioButtonErgometrias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonTarjetas)
                    .addComponent(jRadioButtonContabilidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonPases)
                    .addComponent(jRadioButtonEstados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonSanciones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNombreJugadora, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNombreJugadora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            if (jRadioButtonDatosPersonales.isSelected()) {
                //llamar reporte
            }
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
        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JLabel jLabelNombreJugadora;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JRadioButton jRadioButtonContabilidad;
    private javax.swing.JRadioButton jRadioButtonDatosPersonales;
    private javax.swing.JRadioButton jRadioButtonErgometrias;
    private javax.swing.JRadioButton jRadioButtonEstados;
    private javax.swing.JRadioButton jRadioButtonPases;
    private javax.swing.JRadioButton jRadioButtonSanciones;
    private javax.swing.JRadioButton jRadioButtonTarjetas;
    // End of variables declaration//GEN-END:variables
}
