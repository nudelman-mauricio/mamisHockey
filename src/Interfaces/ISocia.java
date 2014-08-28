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
import logicaNegocios.Localidad;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class ISocia extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;

    private boolean modificar = false;
    private Socia unaSocia = null;

    //LLAMADO DESDE EL MENUPRINCIPAL
    public ISocia(ControladoraGlobal unaControladoraGlobal) {
        SeInicio(unaControladoraGlobal);
    }

    //LLAMADO DESDE UN INTERNAL FRAME
    public ISocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        this.unJInternalFrame = unJInternalFrame;
        SeInicio(unaControladoraGlobal);
    }

    //LLAMADO MOSTRANDO UNA SOCIA
    public ISocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        this.unJInternalFrame = unJInternalFrame;
        this.unaSocia = unaSocia;

        SeInicio(unaControladoraGlobal);
        this.setTitle("Socia: " + unaSocia.getApellido() + " " + unaSocia.getNombre());

        jButtonImprimir.setEnabled(true);
        jButtonEditar.setEnabled(true);

        camposCargar(unaSocia);
        camposActivo(false);
        modificar = true;
    }

    public void SeInicio(ControladoraGlobal unaControladoraGlobal) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        initComponents();

        this.setTitle("Panel de Nueva Socia");//titulo de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Socia2.png")));//Icono de la ventana
        IMenuPrincipalInterface.centrar(this);//centrar ventana

        cargarComboBoxLocalidades();
        camposLimpiar();
        camposActivo(true);
        jButtonImprimir.setEnabled(false);
        jButtonEditar.setEnabled(false);
        modificar = false;
    }

    public void cargarComboBoxLocalidades() {
        this.jComboBoxLocalidad.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getLocalidadesBD()));
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
        if (jTextFieldFechaNacimiento.getText().isEmpty()) {
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

        jPanel1 = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
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
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelTelefonoFijo = new javax.swing.JLabel();
        jTextFieldTelFijo = new javax.swing.JTextField();
        jLabelTelefonoCelular = new javax.swing.JLabel();
        jTextFieldTelCelular = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();
        jButtonExaminarImagen = new javax.swing.JButton();

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
                .addContainerGap(198, Short.MAX_VALUE))
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

        jLabelDni.setText("DNI *");

        jLabelApellido.setText("Apellido *");

        jLabelNombres.setText("Nombres *");

        jLabelLocalidad.setText("Localidad *");

        jLabelDomicilio.setText("Domicilio *");

        jLabelFechaNacimiento.setText("Fecha de Nacimiento *");

        jLabelFechaIngreso.setText("Fecha Ingreso *");

        jLabelExJugadora.setText("ExJugadora ");

        jLabelEmail.setText("E-mail");

        jLabelTelefonoFijo.setText("Telefono Fijo");

        jLabelTelefonoCelular.setText("Telefono Celular");

        jLabel1.setText("* Campos Obligatorios");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCheckBoxExJugadora)
                                    .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDni)
                                    .addComponent(jLabelApellido))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLocalidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonoFijo)
                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonoCelular)
                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExJugadora)
                    .addComponent(jCheckBoxExJugadora))
                .addGap(0, 0, 0)
                .addComponent(jLabel1))
        );

        jLabelImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonExaminarImagen.setText("Seleccionar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExaminarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        modificar = false;
        camposActivo(true);
        camposLimpiar();
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            DateFormat df = DateFormat.getDateInstance();
            try {
                Date fechaNacimiento = new java.sql.Date(df.parse(jTextFieldFechaNacimiento.getText()).getTime());
                Date fechaIngreso = new java.sql.Date(df.parse(jTextFieldFechaIngreso.getText()).getTime());
                if (!modificar) {
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
                            false,
                            "FOTO CARNET",
                            jCheckBoxExJugadora.isSelected()
                    );
                    JOptionPane.showMessageDialog(this, "Socia Modificada");
                }
                camposActivo(false);

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
            }        
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        modificar = false;
        if (this.unaSocia == null) {
            camposLimpiar();

        } else {
            camposCargar(unaSocia);
        }
        camposActivo(false);
        jButtonNuevo.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

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
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelExJugadora;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JLabel jLabelTelefonoCelular;
    private javax.swing.JLabel jLabelTelefonoFijo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
