/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logicaNegocios.Localidad;
import logicaNegocios.PersonaAuxiliar;
import main.ControladoraGlobal;

public class IPersonaAuxiliar extends javax.swing.JInternalFrame {

    JInternalFrame unJInternalFrame;
    ControladoraGlobal unaControladoraGlobal;
    PersonaAuxiliar unaPersonaAuxiliar = null;

    public IPersonaAuxiliar(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/referee.png")));
        IMenuPrincipalInterface.centrar(this);
        SeInicio(unaControladoraGlobal);

        jButtonEditar.setEnabled(false);

        camposLimpiar();

    }

    public IPersonaAuxiliar(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/referee.png")));
        IMenuPrincipalInterface.centrar(this);
        SeInicio(unaControladoraGlobal);

        jButtonEditar.setEnabled(false);

        camposLimpiar();

    }

    public IPersonaAuxiliar(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, PersonaAuxiliar unaPersonaAuxiliar) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.setTitle("Arbitro: " + unaPersonaAuxiliar.getApellido() + " " + unaPersonaAuxiliar.getNombre());
        SeInicio(unaControladoraGlobal);

        this.unaPersonaAuxiliar = unaPersonaAuxiliar;

        jButtonEditar.setEnabled(true);

        camposCargar(unaPersonaAuxiliar);

    }

    public void SeInicio(ControladoraGlobal unaControladoraGlobal) {
        this.unaControladoraGlobal = unaControladoraGlobal;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/referee.png")));

        cargarComboBoxLocalidades();
        jComboBoxLocalidad.setSelectedIndex(-1);
        IMenuPrincipalInterface.centrar(this);

        camposActivo(false);
    }

    public void camposCargar(PersonaAuxiliar unaPersonaAuxiliar) {
        jTextFieldDNI.setText(unaPersonaAuxiliar.getDni().toString());
        jTextFieldApellido.setText(unaPersonaAuxiliar.getApellido());
        jTextFieldNombre.setText(unaPersonaAuxiliar.getNombre());
        jComboBoxLocalidad.setSelectedItem(unaPersonaAuxiliar.getUnaLocalidad());
        jTextFieldDomicilio.setText(unaPersonaAuxiliar.getDomicilio());
        jTextFieldEmail.setText(unaPersonaAuxiliar.getEmail());

        DateFormat df = DateFormat.getDateInstance();
        jTextFieldFechaNacimiento.setText(df.format(unaPersonaAuxiliar.getFechaNacimiento()));
        jTextFieldFechaIngreso.setText(df.format(unaPersonaAuxiliar.getFechaIngreso()));

        jTextFieldTelFijo.setText(unaPersonaAuxiliar.getTelFijo());
        jTextFieldTelCelular.setText(unaPersonaAuxiliar.getTelCelular());

    }

    public void cargarComboBoxLocalidades() {
        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getLocalidadesBD());
        this.jComboBoxLocalidad.setModel(modelCombo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabelDni10 = new javax.swing.JLabel();
        jLabelApellido10 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelLocalidad10 = new javax.swing.JLabel();
        jComboBoxLocalidad = new javax.swing.JComboBox();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jTextFieldDNI = new javax.swing.JTextField();
        jLabelDomicilio16 = new javax.swing.JLabel();
        jTextFieldDomicilio = new javax.swing.JTextField();
        jLabelFechaIngreso24 = new javax.swing.JLabel();
        jTextFieldFechaNacimiento = new javax.swing.JTextField();
        jTextFieldFechaIngreso = new javax.swing.JTextField();
        jLabelFotocopiaDni = new javax.swing.JLabel();
        jTextFieldFotocopiaDni = new javax.swing.JTextField();
        jButtonExaminarFotocopia = new javax.swing.JButton();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelTelFijo = new javax.swing.JLabel();
        jTextFieldTelFijo = new javax.swing.JTextField();
        jLabelTelCelular = new javax.swing.JLabel();
        jTextFieldTelCelular = new javax.swing.JTextField();
        jLabelFechaNacimiento8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBoxEsArbitro = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jCheckBoxEsCuerpoTecnico = new javax.swing.JCheckBox();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
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

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Edit2.png"))); // NOI18N
        jButtonEditar.setText("Editar");
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
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jLabelDni10.setText("DNI");

        jLabelApellido10.setText("Apellido");

        jLabelNombre.setText("Nombres");

        jLabelLocalidad10.setText("Localidad");

        jTextFieldDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDNIActionPerformed(evt);
            }
        });

        jLabelDomicilio16.setText("Domicilio");

        jLabelFechaIngreso24.setText("Fecha Ingreso");

        jTextFieldFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaNacimientoActionPerformed(evt);
            }
        });

        jTextFieldFechaIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaIngresoActionPerformed(evt);
            }
        });

        jLabelFotocopiaDni.setText("Fotocopia de DNI");

        jButtonExaminarFotocopia.setText("...");

        jLabelEmail.setText("E-mail");

        jLabelTelFijo.setText("Telefono Fijo");

        jLabelTelCelular.setText("Telefono Celular");

        jLabelFechaNacimiento8.setText("Fecha de Nacimiento");

        jLabel2.setText("¿Es Arbitro?");

        jCheckBoxEsArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEsArbitroActionPerformed(evt);
            }
        });

        jLabel3.setText("¿Es Cuerpo Tecnico?");

        jCheckBoxEsCuerpoTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEsCuerpoTecnicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(64, 64, 64)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(51, 51, 51))
                                .addComponent(jLabelTelCelular)
                                .addComponent(jLabelDni10)
                                .addComponent(jLabelApellido10)
                                .addComponent(jLabelNombre)
                                .addComponent(jLabelLocalidad10)
                                .addComponent(jLabelDomicilio16)
                                .addComponent(jLabelEmail)
                                .addComponent(jLabelFechaIngreso24)
                                .addComponent(jLabelFechaNacimiento8)
                                .addComponent(jLabelTelFijo)
                                .addComponent(jLabelFotocopiaDni, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(jTextFieldFotocopiaDni, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonExaminarFotocopia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTextFieldTelFijo)
                        .addComponent(jTextFieldEmail)
                        .addComponent(jTextFieldApellido)
                        .addComponent(jTextFieldDNI)
                        .addComponent(jTextFieldNombre)
                        .addComponent(jComboBoxLocalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDomicilio)
                        .addComponent(jTextFieldFechaNacimiento)
                        .addComponent(jTextFieldFechaIngreso)
                        .addComponent(jTextFieldTelCelular))
                    .addComponent(jCheckBoxEsArbitro)
                    .addComponent(jCheckBoxEsCuerpoTecnico))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDni10)
                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido10)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLocalidad10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilio16)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaNacimiento8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso24)
                    .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelFijo)
                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTelCelular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFotocopiaDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExaminarFotocopia)
                    .addComponent(jLabelFotocopiaDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxEsArbitro)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCheckBoxEsCuerpoTecnico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        } else {
            IMenuPrincipalInterface.jDesktopPane.setVisible(true);
        }    // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        camposActivo(true);
        camposLimpiar();
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    public void camposActivo(boolean Editable) {
        jTextFieldDNI.setEditable(Editable);
        jTextFieldApellido.setEditable(Editable);
        jTextFieldNombre.setEditable(Editable);
        jComboBoxLocalidad.setEnabled(Editable);
        jTextFieldDomicilio.setEditable(Editable);
        jTextFieldEmail.setEditable(Editable);
        jTextFieldFechaNacimiento.setEditable(Editable);
        jTextFieldFechaIngreso.setEditable(Editable);
        jTextFieldTelFijo.setEditable(Editable);
        jTextFieldTelCelular.setEditable(Editable);
        jTextFieldFotocopiaDni.setEditable(Editable);

        jButtonGuardar.setEnabled(Editable);
        jButtonCancelar.setEnabled(Editable);
        jButtonNuevo.setEnabled(!Editable);
    }

    public void camposLimpiar() {
        jTextFieldDNI.setText("");
        jTextFieldApellido.setText("");
        jTextFieldNombre.setText("");
        jTextFieldDomicilio.setText("");
        jTextFieldEmail.setText("");
        jTextFieldFechaNacimiento.setText("");
        jTextFieldFechaIngreso.setText("");
        jTextFieldTelFijo.setText("");
        jTextFieldTelCelular.setText("");
        jTextFieldFotocopiaDni.setText("");
    }

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (unaPersonaAuxiliar == null) {
            DateFormat df = DateFormat.getDateInstance();
            try {
                Date fechaNacimiento = new java.sql.Date(df.parse(jTextFieldFechaNacimiento.getText()).getTime());
                Date fechaIngreso = new java.sql.Date(df.parse(jTextFieldFechaIngreso.getText()).getTime());

                unaControladoraGlobal.crearPersonaAuxiliar(Long.parseLong(jTextFieldDNI.getText()),
                        jTextFieldApellido.getText(),
                        jTextFieldNombre.getText(),
                        (Localidad) jComboBoxLocalidad.getSelectedItem(),
                        jTextFieldDomicilio.getText(),
                        fechaNacimiento,
                        fechaIngreso,                        
                        jTextFieldEmail.getText(),
                        jTextFieldTelFijo.getText(),
                        jTextFieldTelCelular.getText(),
                        jCheckBoxEsArbitro.isSelected(),
                        jCheckBoxEsCuerpoTecnico.isSelected(),
                        false);
                JOptionPane.showMessageDialog(this, "Persona Auxiliar Generada");                
                camposActivo(false);
                camposLimpiar();              

            } catch (ParseException e) {
                System.out.println("ERROR EN LAS FECHAS SOCIA" + e.getMessage());
            }
        } else {
            DateFormat df = DateFormat.getDateInstance();
            try {
                Date fechaNacimiento = new java.sql.Date(df.parse(jTextFieldFechaNacimiento.getText()).getTime());
                Date fechaIngreso = new java.sql.Date(df.parse(jTextFieldFechaIngreso.getText()).getTime());
                unaControladoraGlobal.modificarPersonaAuxiliar(unaPersonaAuxiliar,
                        Long.parseLong(jTextFieldDNI.getText()),
                        jTextFieldApellido.getText(),
                        jTextFieldNombre.getText(),
                        (Localidad) jComboBoxLocalidad.getSelectedItem(),
                        jTextFieldDomicilio.getText(),
                        fechaNacimiento,
                        jTextFieldTelFijo.getText(),
                        jTextFieldTelCelular.getText(),
                        jTextFieldEmail.getText(),
                        fechaIngreso,
                        "Fotocopia", 
                        jCheckBoxEsArbitro.isSelected(),
                        jCheckBoxEsCuerpoTecnico.isSelected(),                       
                        false,
                        false
                        );             
               
            }  catch (ParseException e) {
                System.out.println("ERROR EN LAS FECHAS PERSONA AUXILIAR" + e.getMessage());
            }
        }


    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if (this.unaPersonaAuxiliar == null) {
            camposLimpiar();
        } else {
            camposCargar(unaPersonaAuxiliar);
        }
        camposActivo(false);
        jButtonNuevo.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        camposActivo(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jTextFieldFechaIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaIngresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaIngresoActionPerformed

    private void jTextFieldFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaNacimientoActionPerformed

    private void jTextFieldDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDNIActionPerformed

    private void jCheckBoxEsArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEsArbitroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEsArbitroActionPerformed

    private void jCheckBoxEsCuerpoTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEsCuerpoTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEsCuerpoTecnicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExaminarFotocopia;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxEsArbitro;
    private javax.swing.JCheckBox jCheckBoxEsCuerpoTecnico;
    private javax.swing.JComboBox jComboBoxLocalidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelApellido10;
    private javax.swing.JLabel jLabelDni10;
    private javax.swing.JLabel jLabelDomicilio16;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelFechaIngreso24;
    private javax.swing.JLabel jLabelFechaNacimiento8;
    private javax.swing.JLabel jLabelFotocopiaDni;
    private javax.swing.JLabel jLabelLocalidad10;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTelCelular;
    private javax.swing.JLabel jLabelTelFijo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDomicilio;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFechaIngreso;
    private javax.swing.JTextField jTextFieldFechaNacimiento;
    private javax.swing.JTextField jTextFieldFotocopiaDni;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelCelular;
    private javax.swing.JTextField jTextFieldTelFijo;
    // End of variables declaration//GEN-END:variables
}
