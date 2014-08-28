package Interfaces;

import java.awt.Color;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logicaNegocios.Categoria;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;

public class ITorneo extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private ControladoraGlobal unaControladoraGlobal;
    private Torneo unTorneo;
    private boolean modificar = false;

    public ITorneo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        propiedadesVentana();
        camposActivo(false);
    }

    public ITorneo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Torneo unTorneo) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneo;
        this.setTitle("Torneo: " + unTorneo.getNombre());
        propiedadesVentana();

        jButtonCancelar.setEnabled(true);
        jButtonGuardar.setEnabled(true);
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        camposCargar(unTorneo);
        camposActivo(true);
    }

    public ITorneo(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        propiedadesVentana();
        camposActivo(false);
    }

    private void propiedadesVentana() {
        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Torneo.png")));
        IMenuPrincipalInterface.centrar(this);
        cargarJComboBoxCategoria();

    }

    private void camposCargar(Torneo unTorneo) {
        DateFormat df = DateFormat.getDateInstance();
        jTextFieldFechaInicio.setText(df.format(unTorneo.getFechaInicio()));
        this.jTextFieldNombreTorneo.setText(unTorneo.getNombre());
        this.jComboBoxCategoria.setSelectedItem(unTorneo.getUnaCategoria());
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldNombreTorneo.getText().isEmpty()) {
            jLabelNombreTorneo.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNombreTorneo.setForeground(Color.black);
        }
        if (jComboBoxCategoria.getSelectedIndex() == -1) {
            jLabelCategoria.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelCategoria.setForeground(Color.black);
        }
        if (jTextFieldFechaInicio.getText().isEmpty()) {
            jLabelFechaInicio.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFechaInicio.setForeground(Color.black);
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
        jButtonCancelar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelFechaInicio = new javax.swing.JLabel();
        jComboBoxCategoria = new javax.swing.JComboBox();
        jLabelCategoria = new javax.swing.JLabel();
        jLabelNombreTorneo = new javax.swing.JLabel();
        jTextFieldFechaInicio = new javax.swing.JTextField();
        jTextFieldNombreTorneo = new javax.swing.JTextField();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 220));
        setMinimumSize(new java.awt.Dimension(650, 220));
        setPreferredSize(new java.awt.Dimension(650, 220));
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(3, 3, 3))
        );

        jLabelFechaInicio.setText("Fecha de Inicio");

        jLabelCategoria.setText("Categorias");

        jLabelNombreTorneo.setText("Nombre del Torneo");

        jTextFieldFechaInicio.setText("dd/mm/aaaa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFechaInicio)
                    .addComponent(jLabelNombreTorneo)
                    .addComponent(jLabelCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNombreTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombreTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombreTorneo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaInicio)
                    .addComponent(jTextFieldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCategoria)
                    .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        //SE cerro
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        } else {
            IMenuPrincipalInterface.jDesktopPane.setVisible(true);
        }
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        this.modificar = false;
        this.jButtonNuevo.setEnabled(false);
        this.jButtonCancelar.setEnabled(true);
        this.jButtonGuardar.setEnabled(true);
        camposActivo(true);
        camposLimpiar();
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            DateFormat df = DateFormat.getDateInstance();
            try {
                Date fechaInicio = new java.sql.Date(df.parse(jTextFieldFechaInicio.getText()).getTime());
                if (!modificar) {
                    unaControladoraGlobal.crearTorneo(fechaInicio, (Categoria) jComboBoxCategoria.getSelectedItem(), jTextFieldNombreTorneo.getText());
                    JOptionPane.showMessageDialog(this, "Torneo Guardado");
                } else {
                    unaControladoraGlobal.modificarTorneo(unTorneo, fechaInicio, (Categoria) jComboBoxCategoria.getSelectedItem(), jTextFieldNombreTorneo.getText());
                    JOptionPane.showMessageDialog(this, "Torneo Modificado");
                }
                camposActivo(false);
                this.jButtonNuevo.setEnabled(true);
                this.jButtonCancelar.setEnabled(false);
                this.jButtonGuardar.setEnabled(false);
                this.jButtonEditar.setEnabled(true);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        camposActivo(false);
        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        camposActivo(true);
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEditar.setEnabled(false);
        this.modificar = true;
    }//GEN-LAST:event_jButtonEditarActionPerformed

    public void camposActivo(boolean Editable) {

        jTextFieldFechaInicio.setEditable(Editable);
        jTextFieldNombreTorneo.setEditable(Editable);
        jComboBoxCategoria.setEnabled(Editable);

    }

    public void camposLimpiar() {
        jTextFieldFechaInicio.setText("");
        jTextFieldNombreTorneo.setText("");
        jComboBoxCategoria.setSelectedIndex(-1);
    }

    private void cargarJComboBoxCategoria() {
        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel((Vector) this.unaControladoraGlobal.getCategoriasBD());
        this.jComboBoxCategoria.setModel(modelCombo);
        jComboBoxCategoria.setSelectedIndex(-1);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JComboBox jComboBoxCategoria;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelFechaInicio;
    private javax.swing.JLabel jLabelNombreTorneo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldFechaInicio;
    private javax.swing.JTextField jTextFieldNombreTorneo;
    // End of variables declaration//GEN-END:variables

}
