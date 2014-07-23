/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logicaNegocios.Localidad;
import logicaNegocios.Socia;
import main.ControladoraGlobal;


public class ISocia extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JDesktopPane unjDesktopPane1;
    private JInternalFrame unJInternalFrame;
    
    private boolean modificar = false;
    private Socia unaSocia = null;

    //LLAMADO DESDE EL MENUPRINCIPAL
    public ISocia(ControladoraGlobal unaControladoraGlobal, JDesktopPane unjDesktopPane1) {
        initComponents();
        this.unjDesktopPane1 = unjDesktopPane1;
        SeInicio(unaControladoraGlobal);

        jButtonImprimir.setEnabled(false);
        jButtonEditar.setEnabled(false);
        camposLimpiar();
    }

    //LLAMADO DESDE UN INTERNAL FRAME
    public ISocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        SeInicio(unaControladoraGlobal);

        jButtonImprimir.setEnabled(false);
        jButtonEditar.setEnabled(false);
        camposLimpiar();
    }

    //LLAMADO MOSTRANDO UNA SOCIA
    public ISocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;
        
        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());
        SeInicio(unaControladoraGlobal);

        jButtonImprimir.setEnabled(true);
        jButtonEditar.setEnabled(true);

        camposCargar(unaSocia);
    }

    public void SeInicio(ControladoraGlobal unaControladoraGlobal) {
        this.unaControladoraGlobal = unaControladoraGlobal;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Socia2.png")));

        cargarComboBoxLocalidades();
        IMenuPrincipalInterface.centrar(this);

        camposActivo(false);
    }

    public void cargarComboBoxLocalidades() {
        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getLocalidadesBD());
        this.jComboBoxLocalidad.setModel(modelCombo);
        jComboBoxLocalidad.setSelectedIndex(-1);
    }

    public void camposCargar(Socia unaSocia) {
        jTextFieldDNI.setText(unaSocia.getDni().toString());
        jTextFieldApellido.setText(unaSocia.getApellido());
        jTextFieldNombres.setText(unaSocia.getNombre());
        jComboBoxLocalidad.setSelectedItem(unaSocia.getUnaLocalidad());
        jTextFieldDomicilio.setText(unaSocia.getDomicilio());

        DateFormat df = DateFormat.getDateInstance();
        jTextFieldFechaNacimiento.setText(df.format(unaSocia.getFechaNacimiento()));
        jTextFieldFechaIngreso.setText(df.format(unaSocia.getFechaIngreso()));
        jTextFieldEmail.setText(unaSocia.getEmail());
        jTextFieldTelFijo.setText(unaSocia.getTelFijo());
        jTextFieldTelCelular.setText(unaSocia.getTelCelular());
        jCheckBoxExJugadora.setSelected(unaSocia.isExJugadora());
    }

    public void camposActivo(boolean Editable) {
        jTextFieldDNI.setEditable(Editable);
        jTextFieldApellido.setEditable(Editable);
        jTextFieldNombres.setEditable(Editable);
        jComboBoxLocalidad.setEnabled(Editable);
        jTextFieldDomicilio.setEditable(Editable);
        jTextFieldEmail.setEditable(Editable);
        jTextFieldFechaNacimiento.setEditable(Editable);
        jTextFieldFechaIngreso.setEditable(Editable);
        jTextFieldTelFijo.setEditable(Editable);
        jTextFieldTelCelular.setEditable(Editable);
        jCheckBoxExJugadora.setEnabled(Editable);

        jButtonGuardar.setEnabled(Editable);
        jButtonCancelar.setEnabled(Editable);
        jButtonNuevo.setEnabled(!Editable);
    }

    public void camposLimpiar() {
        jTextFieldDNI.setText("");
        jTextFieldApellido.setText("");
        jTextFieldNombres.setText("");
        jTextFieldDomicilio.setText("");
        jTextFieldEmail.setText("");
        jTextFieldFechaNacimiento.setText("");
        jTextFieldFechaIngreso.setText("");
        jTextFieldTelFijo.setText("");
        jTextFieldTelCelular.setText("");
        jCheckBoxExJugadora.setSelected(false);
        jComboBoxLocalidad.setSelectedIndex(-1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();
        jButtonExaminarImagen = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
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
        jTextFieldFechaNacimiento = new javax.swing.JTextField();
        jTextFieldFechaIngreso = new javax.swing.JTextField();
        jLabelDomicilio1 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelFechaIngreso1 = new javax.swing.JLabel();
        jTextFieldTelFijo = new javax.swing.JTextField();
        jLabelFechaIngreso2 = new javax.swing.JLabel();
        jTextFieldTelCelular = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        jButtonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
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

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jLabelImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonExaminarImagen.setText("Seleccionar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExaminarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExaminarImagen)
                .addContainerGap())
        );

        jLabelDni.setText("DNI *");

        jLabelApellido.setText("Apellido *");

        jLabelNombres.setText("Nombres *");

        jLabelLocalidad.setText("Localidad *");

        jTextFieldDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDNIActionPerformed(evt);
            }
        });

        jLabelDomicilio.setText("Domicilio *");

        jLabelFechaNacimiento.setText("Fecha de Nacimiento *");

        jLabelFechaIngreso.setText("Fecha Ingreso *");

        jLabelExJugadora.setText("ExJugadora *");

        jCheckBoxExJugadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxExJugadoraActionPerformed(evt);
            }
        });

        jLabelDomicilio1.setText("E-mail");

        jLabelFechaIngreso1.setText("Telefono Fijo");

        jLabelFechaIngreso2.setText("Telefono Celular");

        jLabel1.setText("* Campos Obligatorios");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNombres, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelLocalidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelFechaIngreso, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelExJugadora, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDomicilio1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelFechaIngreso1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelFechaIngreso2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.Alignment.TRAILING, 0, 160, Short.MAX_VALUE)
                                            .addComponent(jTextFieldNombres)
                                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBoxExJugadora)
                                            .addComponent(jTextFieldFechaIngreso)
                                            .addComponent(jTextFieldTelFijo)
                                            .addComponent(jTextFieldTelCelular)))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDni)
                                    .addComponent(jLabelApellido))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(jTextFieldDNI)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLocalidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilio1)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso1)
                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso2)
                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExJugadora)
                    .addComponent(jCheckBoxExJugadora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDNIActionPerformed

    private void jCheckBoxExJugadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxExJugadoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxExJugadoraActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        }
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        modificar = true;
        camposActivo(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        modificar=false;
        camposActivo(true);
        camposLimpiar();
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        DateFormat df = DateFormat.getDateInstance();
        try {
            Date fechaNacimiento = new java.sql.Date(df.parse(jTextFieldFechaNacimiento.getText()).getTime());
            Date fechaIngreso = new java.sql.Date(df.parse(jTextFieldFechaIngreso.getText()).getTime());
            if(!modificar){
            unaControladoraGlobal.crearSocia(Long.parseLong(jTextFieldDNI.getText()),
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
            JOptionPane.showMessageDialog(this, "Socia Guardada");}
            else { 
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
                false,
                "FOTO CARNET",
                jCheckBoxExJugadora.isSelected()                                
                );
            JOptionPane.showMessageDialog(this, "Socia Modificada");
            }            
            camposActivo(false);

        } catch (ParseException e) {
            System.out.println("ERROR EN LAS FECHAS SOCIA" + e.getMessage());
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        modificar = false;
        if (this.unaSocia == null){
            camposLimpiar();
            
        }else{
            camposCargar(unaSocia);
        }
        camposActivo(false);
        jButtonNuevo.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExaminarImagen;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxExJugadora;
    private javax.swing.JComboBox jComboBoxLocalidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelDomicilio1;
    private javax.swing.JLabel jLabelExJugadora;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelFechaIngreso1;
    private javax.swing.JLabel jLabelFechaIngreso2;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDomicilio;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFechaIngreso;
    private javax.swing.JTextField jTextFieldFechaNacimiento;
    private javax.swing.JTextField jTextFieldNombres;
    private javax.swing.JTextField jTextFieldTelCelular;
    private javax.swing.JTextField jTextFieldTelFijo;
    // End of variables declaration//GEN-END:variables
}
