package Interfaces;

import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logicaNegocios.Partido;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class ICargarTarjeta extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Socia unaSocia;
    private Partido unPartido;

    public ICargarTarjeta(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia, Partido unPartido) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;
        this.unPartido = unPartido;

        this.jTextFieldCamiseta.setText(unaSocia.getNumeroCamiseta());
        this.jTextFieldNombre.setText(unaSocia.getApellido() + ", " + unaSocia.getNombre());
        this.jComboBoxTipoTarjeta.setSelectedIndex(-1);
        this.jComboBoxTipoTarjeta.setSelectedIndex(-1);
        this.jTextFieldMinuto.setText("");

    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldMinuto.getText().isEmpty()) {
            jLabelMinuto.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMinuto.setForeground(Color.black);
        }
        if (jComboBoxTipoTarjeta.getSelectedIndex() == -1) {
            jLabelTarjeta.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelTarjeta.setForeground(Color.black);
        }
        if (jComboBoxTiempo.getSelectedIndex() == -1) {
            jLabelTiempo.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelTiempo.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelTiempo = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelMinuto = new javax.swing.JLabel();
        jTextFieldMinuto = new javax.swing.JTextField();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jComboBoxTiempo = new javax.swing.JComboBox();
        jLabelCamiseta = new javax.swing.JLabel();
        jTextFieldCamiseta = new javax.swing.JTextField();
        jLabelTarjeta = new javax.swing.JLabel();
        jComboBoxTipoTarjeta = new javax.swing.JComboBox();

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel4.setName(""); // NOI18N

        jLabelNombre.setText("Jugadora");

        jLabelTiempo.setText("Tiempo");

        jTextFieldNombre.setEditable(false);

        jLabelMinuto.setText("Minuto");

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jComboBoxTiempo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Primer Tiempo", "Segundo Tiempo" }));

        jLabelCamiseta.setText("Camiseta");

        jTextFieldCamiseta.setEditable(false);

        jLabelTarjeta.setText("Tipo Tarjeta");

        jComboBoxTipoTarjeta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Verde", "Amarilla", "Roja" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTiempo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMinuto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCamiseta, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTarjeta, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNombre)
                    .addComponent(jTextFieldMinuto, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jComboBoxTiempo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldCamiseta)
                    .addComponent(jComboBoxTipoTarjeta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCamiseta)
                    .addComponent(jTextFieldCamiseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTarjeta)
                    .addComponent(jComboBoxTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTiempo)
                    .addComponent(jComboBoxTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMinuto)
                    .addComponent(jTextFieldMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        if (camposValidar()) {
            if (jComboBoxTiempo.getSelectedIndex() == 0) {
                if (jComboBoxTipoTarjeta.getSelectedIndex() == 0) {//Tarjeta Verde
                    unaControladoraGlobal.crearTarjeta(unaSocia, unPartido, "Verde", "", "1", jTextFieldMinuto.getText());
                    //Recorrer las tarjetas de esta socia en este partido. Si tiene tres verdes, Agregar una amarilla por acumulacion
                }
                if (jComboBoxTipoTarjeta.getSelectedIndex() == 1) {//Tarjeta Amarilla
                    unaControladoraGlobal.crearTarjeta(unaSocia, unPartido, "Amarilla", "", "1", jTextFieldMinuto.getText());
                }
                if (jComboBoxTipoTarjeta.getSelectedIndex() == 2) {//Tarjeta Roja
                    unaControladoraGlobal.crearTarjeta(unaSocia, unPartido, "Roja", "", "1", jTextFieldMinuto.getText());
                }
            } else {
                if (jComboBoxTipoTarjeta.getSelectedIndex() == 0) {//Tarjeta Verde
                    unaControladoraGlobal.crearTarjeta(unaSocia, unPartido, "Verde", "", "2", jTextFieldMinuto.getText());
                }
                if (jComboBoxTipoTarjeta.getSelectedIndex() == 1) {//Tarjeta Amarilla
                    unaControladoraGlobal.crearTarjeta(unaSocia, unPartido, "Amarilla", "", "2", jTextFieldMinuto.getText());
                }
                if (jComboBoxTipoTarjeta.getSelectedIndex() == 2) {//Tarjeta Roja
                    unaControladoraGlobal.crearTarjeta(unaSocia, unPartido, "Roja", "Roja Directa.", "2", jTextFieldMinuto.getText());
                }
            }
            
            
            if (unJInternalFrame != null) {
                this.setVisible(false);
                this.dispose();
                this.unJInternalFrame.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if (unJInternalFrame != null) {
            this.setVisible(false);
            this.dispose();
            this.unJInternalFrame.setVisible(true);
        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox jComboBoxTiempo;
    private javax.swing.JComboBox jComboBoxTipoTarjeta;
    private javax.swing.JLabel jLabelCamiseta;
    private javax.swing.JLabel jLabelMinuto;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTarjeta;
    private javax.swing.JLabel jLabelTiempo;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextFieldCamiseta;
    private javax.swing.JTextField jTextFieldMinuto;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
