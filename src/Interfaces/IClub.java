package Interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logicaNegocios.Club;
import logicaNegocios.Localidad;
import main.ControladoraGlobal;

public class IClub extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private ControladoraGlobal unaControladoraGlobal;
    private Club unClub = null;

    //LLAMADO PARA NUEVO CLUB
    public IClub(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();
        IMenuPrincipalInterface.jDesktopPane.add(this);
        IMenuPrincipalInterface.centrarYalFrente(this);

        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        setFrameIcon(new ImageIcon(getClass().getResource("/Iconos Nuevos/Club.png")));//Icono de la ventana
        this.jComboBoxLocalidad.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getLocalidadesBD()));

        camposLimpiar();
        camposActivo(jPanelDetalles, true);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
    }

    //LLAMADO MOSTRANDO UN CLUB
    public IClub(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Club unClub) {
        this(unaControladoraGlobal, unJInternalFrame);
        this.unClub = unClub;
        this.setTitle("Datos de Club: " + unClub.getNombre());

        camposCargar(unClub);
        camposActivo(jPanelDetalles, false);

        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(true);
    }

    //deshabilitar todo lo de un contenedor
    private void camposActivo(Container c, boolean bandera) {
        Component[] components = c.getComponents();
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(bandera);
            if (components[i] instanceof JTextField) {
                ((JTextField) components[i]).setEditable(bandera);
            }
            if (components[i] instanceof Container) {
                camposActivo((Container) components[i], bandera);
            }
        }
        jTextFieldResponsableSede.setEnabled(false);
    }

    public void camposLimpiar() {
        jTextFieldNombre.setText("");
        jTextFieldPresidente.setText("");
        jComboBoxLocalidad.setSelectedIndex(-1);
    }

    public void camposCargar(Club unClub) {
        jTextFieldNombre.setText(unClub.getNombre());
        jTextFieldPresidente.setText(unClub.getNombrePresidente());
        jComboBoxLocalidad.setSelectedItem(unClub.getUnaLocalidad());
        if (unClub.getUnaResponsableSede() != null) {
            jTextFieldResponsableSede.setText(unClub.getUnaResponsableSede().toString());
        }
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldNombre.getText().isEmpty()) {
            jLabelNombre.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNombre.setForeground(Color.black);
        }
        if (jTextFieldPresidente.getText().isEmpty()) {
            jLabelPresidente.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelPresidente.setForeground(Color.black);
        }
        if (jComboBoxLocalidad.getSelectedIndex() == -1) {
            jLabelLocalidad.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelLocalidad.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelPresidente = new javax.swing.JLabel();
        jLabelLocalidad = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldPresidente = new javax.swing.JTextField();
        jComboBoxLocalidad = new javax.swing.JComboBox();
        jLabelLocalidad1 = new javax.swing.JLabel();
        jTextFieldResponsableSede = new javax.swing.JTextField();
        jPanelEscudo = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();
        jButtonExaminarImagen = new javax.swing.JButton();

        setClosable(true);
        setTitle("Nuevo Club");
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

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setEnabled(false);
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jLabelNombre.setText("Nombre");

        jLabelPresidente.setText("Presidente");

        jLabelLocalidad.setText("Localidad");

        jLabelLocalidad1.setText("Responsable de Sede");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jLabelLocalidad1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldResponsableSede, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelPresidente)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelLocalidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldPresidente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPresidente)
                    .addComponent(jTextFieldPresidente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocalidad1)
                    .addComponent(jTextFieldResponsableSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabelImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonExaminarImagen.setText("Seleccionar");
        jButtonExaminarImagen.setEnabled(false);

        javax.swing.GroupLayout jPanelEscudoLayout = new javax.swing.GroupLayout(jPanelEscudo);
        jPanelEscudo.setLayout(jPanelEscudoLayout);
        jPanelEscudoLayout.setHorizontalGroup(
            jPanelEscudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEscudoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEscudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExaminarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelEscudoLayout.setVerticalGroup(
            jPanelEscudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEscudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExaminarImagen)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanelEscudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelEscudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            if (this.unClub == null) {
                unaControladoraGlobal.crearClub(jTextFieldNombre.getText(), jTextFieldPresidente.getText(), (Localidad) jComboBoxLocalidad.getSelectedItem());
                JOptionPane.showMessageDialog(this, "Club Guardado");
            } else {
                unaControladoraGlobal.modificarClub(unClub, jTextFieldNombre.getText(), jTextFieldPresidente.getText(), (Localidad) jComboBoxLocalidad.getSelectedItem(), unClub.isBorradoLogico());
                JOptionPane.showMessageDialog(this, "Club Modificado");
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);

        camposActivo(jPanelDetalles, true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExaminarImagen;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JComboBox jComboBoxLocalidad;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelLocalidad1;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPresidente;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelEscudo;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPresidente;
    private javax.swing.JTextField jTextFieldResponsableSede;
    // End of variables declaration//GEN-END:variables
}
