package Interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.PanelUI;
import logicaNegocios.Localidad;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class ISocia extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private DateFormat df = DateFormat.getDateInstance();
    private Socia unaSocia = null;

    //LLAMADO PARA UNA NUEVA SOCIA
    public ISocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;

        this.setTitle("Nueva Socia");//titulo de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Socia2.png")));//Icono de la ventana
        IMenuPrincipalInterface.centrar(this);//centrar ventana

        this.jComboBoxLocalidad.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getLocalidadesBD()));       
        camposLimpiar();
        camposActivo(jPanelDetalles, true);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        
        jTextFieldFechaIngreso.setText(df.format(unaControladoraGlobal.fechaSistema()));
    }

    //LLAMADO MOSTRANDO UNA SOCIA
    public ISocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        this(unaControladoraGlobal, unJInternalFrame);
        this.unaSocia = unaSocia;

        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());

        camposCargar(unaSocia);
        camposActivo(jPanelDetalles, false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(true);       
    }

    public void camposCargar(Socia unaSocia) {
        jTextFieldDNI.setText(unaSocia.getDni().toString());
        jTextFieldApellido.setText(unaSocia.getApellido());
        jTextFieldNombres.setText(unaSocia.getNombre());
        jComboBoxLocalidad.setSelectedItem(unaSocia.getUnaLocalidad());
        jTextFieldDomicilio.setText(unaSocia.getDomicilio());
        jDateChooserFechaNacimiento.setDate((unaSocia.getFechaNacimiento()));
        jTextFieldFechaIngreso.setText(df.format(unaSocia.getFechaIngreso()));
        jTextFieldEmail.setText(unaSocia.getEmail());
        jTextFieldTelFijo.setText(unaSocia.getTelFijo());
        jTextFieldTelCelular.setText(unaSocia.getTelCelular());
        jCheckBoxExJugadora.setSelected(unaSocia.isExJugadora());
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
    }

    public void camposLimpiar() {
        jTextFieldDNI.setText("");
        jTextFieldApellido.setText("");
        jTextFieldNombres.setText("");
        jTextFieldDomicilio.setText("");
        jTextFieldEmail.setText("");
        jDateChooserFechaNacimiento.setDate(null);
        jTextFieldFechaIngreso.setText("");
        jTextFieldTelFijo.setText("");
        jTextFieldTelCelular.setText("");
        jCheckBoxExJugadora.setSelected(false);
        jComboBoxLocalidad.setSelectedIndex(-1);
    }

    public boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldDNI.getText().isEmpty()) {
            jLabelDni.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelDni.setForeground(Color.black);
        }
        if (jTextFieldApellido.getText().isEmpty()) {
            jLabelApellido.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelApellido.setForeground(Color.black);
        }

        if (jTextFieldNombres.getText().isEmpty()) {
            jLabelNombres.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNombres.setForeground(Color.black);
        }
        if (jComboBoxLocalidad.getSelectedIndex() == -1) {
            jLabelLocalidad.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelLocalidad.setForeground(Color.black);
        }
        if (jTextFieldDomicilio.getText().isEmpty()) {
            jLabelDomicilio.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelDomicilio.setForeground(Color.black);
        }
        if (jDateChooserFechaNacimiento.getDate() == null) {
            jLabelFechaNacimiento.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFechaNacimiento.setForeground(Color.black);
        }
        if (jTextFieldFechaIngreso.getText().isEmpty()) {
            jLabelFechaIngreso.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFechaIngreso.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelDni = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jLabelNombres = new javax.swing.JLabel();
        jLabelLocalidad = new javax.swing.JLabel();
        jComboBoxLocalidad = new javax.swing.JComboBox();
        jTextFieldNombres = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jTextFieldDNI = new javax.swing.JTextField();
        jLabelDomicilio = new javax.swing.JLabel();
        jTextFieldDomicilio = new javax.swing.JTextField();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jLabelFechaIngreso = new javax.swing.JLabel();
        jLabelExJugadora = new javax.swing.JLabel();
        jCheckBoxExJugadora = new javax.swing.JCheckBox();
        jTextFieldFechaIngreso = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelTelefonoFijo = new javax.swing.JLabel();
        jTextFieldTelFijo = new javax.swing.JTextField();
        jLabelTelefonoCelular = new javax.swing.JLabel();
        jTextFieldTelCelular = new javax.swing.JTextField();
        jDateChooserFechaNacimiento = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(650, 433));
        setMinimumSize(new java.awt.Dimension(650, 433));
        setPreferredSize(new java.awt.Dimension(650, 433));
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
                .addContainerGap(151, Short.MAX_VALUE))
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

        jLabelDni.setText("DNI");

        jLabelApellido.setText("Apellido");

        jLabelNombres.setText("Nombres");

        jLabelLocalidad.setText("Localidad");

        jLabelDomicilio.setText("Domicilio");

        jLabelFechaNacimiento.setText("Fecha de Nacimiento");

        jLabelFechaIngreso.setText("Fecha Ingreso");

        jLabelExJugadora.setText("ExJugadora ");

        jLabelEmail.setText("E-mail");

        jLabelTelefonoFijo.setText("Teléfono Fijo");

        jLabelTelefonoCelular.setText("Teléfono Celular");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombres, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLocalidad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaIngreso, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelExJugadora, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelTelefonoFijo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelTelefonoCelular, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBoxExJugadora)
                            .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jComboBoxLocalidad, 0, 160, Short.MAX_VALUE)
                            .addComponent(jTextFieldNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jDateChooserFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDni)
                            .addComponent(jLabelApellido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombres))
                .addGap(12, 12, 12)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLocalidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jDateChooserFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonoFijo)
                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonoCelular)
                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExJugadora)
                    .addComponent(jCheckBoxExJugadora))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);       

        camposActivo(jPanelDetalles, true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            try {
                Date fechaNacimiento = new java.sql.Date((jDateChooserFechaNacimiento.getDate()).getTime());
                Date fechaIngreso = new java.sql.Date(df.parse(jTextFieldFechaIngreso.getText()).getTime());
                if (unaSocia == null) {
                    unaControladoraGlobal.crearSocia(
                            Long.parseLong(jTextFieldDNI.getText()),
                            jTextFieldApellido.getText(),
                            jTextFieldNombres.getText(),
                            (Localidad) jComboBoxLocalidad.getSelectedItem(),
                            jTextFieldDomicilio.getText(),
                            fechaNacimiento,
                            fechaIngreso,
                            "FOTO CARNET",
                            jCheckBoxExJugadora.isSelected(),
                            jTextFieldEmail.getText(),
                            jTextFieldTelFijo.getText(),
                            jTextFieldTelCelular.getText());
                    JOptionPane.showMessageDialog(this, "Socia Guardada");
                } else {
                    unaControladoraGlobal.modificarSocia(
                            unaSocia,
                            Long.parseLong(jTextFieldDNI.getText()),
                            jTextFieldApellido.getText(),
                            jTextFieldNombres.getText(),
                            (Localidad) jComboBoxLocalidad.getSelectedItem(),
                            jTextFieldDomicilio.getText(),
                            fechaNacimiento,
                            jTextFieldTelFijo.getText(),
                            jTextFieldTelCelular.getText(),
                            jTextFieldEmail.getText(),
                            fechaIngreso,
                            unaSocia.isBorradoLogico(),
                            "FOTO CARNET",
                            jCheckBoxExJugadora.isSelected()
                    );
                    JOptionPane.showMessageDialog(this, "Socia Modificada");
                }
                this.dispose();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxExJugadora;
    private javax.swing.JComboBox jComboBoxLocalidad;
    private com.toedter.calendar.JDateChooser jDateChooserFechaNacimiento;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelExJugadora;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JLabel jLabelTelefonoCelular;
    private javax.swing.JLabel jLabelTelefonoFijo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDomicilio;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFechaIngreso;
    private javax.swing.JTextField jTextFieldNombres;
    private javax.swing.JTextField jTextFieldTelCelular;
    private javax.swing.JTextField jTextFieldTelFijo;
    // End of variables declaration//GEN-END:variables
}
