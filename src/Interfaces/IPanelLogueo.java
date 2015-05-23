package Interfaces;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import main.ControladoraGlobal;

public class IPanelLogueo extends javax.swing.JFrame {

    private final ControladoraGlobal unaControladoraGlobal;
    private int intentos = 0;
    private boolean cambiarPass;
    private String pass;

    public IPanelLogueo(ControladoraGlobal unaControladoraGlobal, boolean cambiarPass) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.cambiarPass = cambiarPass;
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Iconos Nuevos/Logo.png")).getImage());
        this.setTitle("Mami's Hockey Misiones");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelPass = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 150));
        setMinimumSize(new java.awt.Dimension(400, 150));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jLabelPass.setText("Contraseña:");

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 69, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelPass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(97, 97, 97))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPass))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonAceptar))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        if (intentos < 2) {
            if ((unaControladoraGlobal.getConfiguracion("password").equals(jPasswordField.getText())) || (jLabelPass.getText().equalsIgnoreCase("NUEVA:")) || (jLabelPass.getText().equalsIgnoreCase("CONFIRMAR NUEVA:"))) {
                if (cambiarPass) {
                    if (jLabelPass.getText().equalsIgnoreCase("Contraseña:")) {
                        jLabelPass.setText("NUEVA:");
                        jPasswordField.setText("");
                    } else {
                        if (jLabelPass.getText().equalsIgnoreCase("NUEVA:")) {
                            pass = jPasswordField.getText();
                            jLabelPass.setText("CONFIRMAR NUEVA:");
                            jPasswordField.setText("");
                        } else {
                            if (jLabelPass.getText().equalsIgnoreCase("CONFIRMAR NUEVA:")) {
                                if (pass.equals(jPasswordField.getText())) {
                                    unaControladoraGlobal.setConfiguracion("password", pass);
                                    JOptionPane.showMessageDialog(null, "Se cambió la contraseña correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                                    this.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se ingresó correctamente", "Error", JOptionPane.ERROR_MESSAGE);
                                    this.dispose();
                                }
                            }
                        }
                    }
                } else {
                    IMenuPrincipal unaVentanaPrincipal = new IMenuPrincipal(unaControladoraGlobal);
                    SwingUtilities.updateComponentTreeUI(unaVentanaPrincipal);
                    unaVentanaPrincipal.setLocationRelativeTo(null); //Mandar al centro
                    unaVentanaPrincipal.setVisible(true);
                    this.dispose();
                }
            } else {
                intentos++;
                jPasswordField.setText("");
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            this.dispose();
            if (!cambiarPass) {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
        if (!cambiarPass) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    // End of variables declaration//GEN-END:variables
}
