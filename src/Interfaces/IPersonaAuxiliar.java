package Interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logicaNegocios.Localidad;
import logicaNegocios.PersonaAuxiliar;
import main.ControladoraGlobal;

public class IPersonaAuxiliar extends javax.swing.JInternalFrame {
    
    private JInternalFrame unJInternalFrame;
    private ControladoraGlobal unaControladoraGlobal;
    private PersonaAuxiliar unaPersonaAuxiliar = null;
    private DateFormat df = DateFormat.getDateInstance();

    //LLAMADO PARA NUEVO PERSONA AUXILIAR
    public IPersonaAuxiliar(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();
        IMenuPrincipalInterface.centrar(this);
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/referee.png")));//Icono de la ventana

        this.jComboBoxLocalidad.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getLocalidadesBD()));        
        
        camposLimpiar();
        camposActivo(jPanelDetalles, true);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
    }

    //LLAMADO MOSTRANDO UNA PERSONA AUXILIAR
    public IPersonaAuxiliar(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, PersonaAuxiliar unaPersonaAuxiliar) {
        this(unaControladoraGlobal, unJInternalFrame);
        this.unaPersonaAuxiliar = unaPersonaAuxiliar;
        this.setTitle("Arbitro: " + unaPersonaAuxiliar.getApellido() + " " + unaPersonaAuxiliar.getNombre());
        camposCargar(unaPersonaAuxiliar);
        camposActivo(jPanelDetalles, false);
        
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(true);
        jButtonImprimir.setEnabled(true);
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
        jTextFieldNombre.setText("");
        jTextFieldDomicilio.setText("");
        jTextFieldEmail.setText("");
        jTextFieldFechaNacimiento.setText("");
        jTextFieldFechaIngreso.setText("");
        jTextFieldTelFijo.setText("");
        jTextFieldTelCelular.setText("");
        jTextFieldFotocopiaDni.setText("");
        jCheckBoxEsArbitro.setSelected(false);
        jCheckBoxEsCuerpoTecnico.setSelected(false);
        jComboBoxLocalidad.setSelectedIndex(-1);
    }
    
    private void camposCargar(PersonaAuxiliar unaPersonaAuxiliar) {
        jTextFieldDNI.setText(unaPersonaAuxiliar.getDni().toString());
        jTextFieldApellido.setText(unaPersonaAuxiliar.getApellido());
        jTextFieldNombre.setText(unaPersonaAuxiliar.getNombre());
        jComboBoxLocalidad.setSelectedItem(unaPersonaAuxiliar.getUnaLocalidad());
        jTextFieldDomicilio.setText(unaPersonaAuxiliar.getDomicilio());
        jTextFieldEmail.setText(unaPersonaAuxiliar.getEmail());
        jTextFieldFechaNacimiento.setText(df.format(unaPersonaAuxiliar.getFechaNacimiento()));
        jTextFieldFechaIngreso.setText(df.format(unaPersonaAuxiliar.getFechaIngreso()));
        jTextFieldTelFijo.setText(unaPersonaAuxiliar.getTelFijo());
        jTextFieldTelCelular.setText(unaPersonaAuxiliar.getTelCelular());
    }
    
    private boolean camposValidar() {
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
        
        if (jTextFieldNombre.getText().isEmpty()) {
            jLabelNombre.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNombre.setForeground(Color.black);
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
            return bandera;
        }
        if (!jCheckBoxEsArbitro.isSelected() && !jCheckBoxEsCuerpoTecnico.isSelected() && bandera) {
            JOptionPane.showMessageDialog(this, "Por favor especifique si es Árbitro, Cuerpo Técnico o ambos");
            bandera = false;
        }
        return bandera;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelDni = new javax.swing.JLabel();
        jTextFieldDNI = new javax.swing.JTextField();
        jLabelApellido = new javax.swing.JLabel();
        jTextFieldApellido = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelLocalidad = new javax.swing.JLabel();
        jComboBoxLocalidad = new javax.swing.JComboBox();
        jLabelDomicilio = new javax.swing.JLabel();
        jTextFieldDomicilio = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jTextFieldFechaNacimiento = new javax.swing.JTextField();
        jLabelFechaIngreso = new javax.swing.JLabel();
        jTextFieldFechaIngreso = new javax.swing.JTextField();
        jLabelTelFijo = new javax.swing.JLabel();
        jTextFieldTelFijo = new javax.swing.JTextField();
        jLabelTelCelular = new javax.swing.JLabel();
        jTextFieldTelCelular = new javax.swing.JTextField();
        jLabelFotocopiaDni = new javax.swing.JLabel();
        jTextFieldFotocopiaDni = new javax.swing.JTextField();
        jButtonExaminarFotocopia = new javax.swing.JButton();
        jLabelArbitro = new javax.swing.JLabel();
        jCheckBoxEsArbitro = new javax.swing.JCheckBox();
        jLabelTecnico = new javax.swing.JLabel();
        jCheckBoxEsCuerpoTecnico = new javax.swing.JCheckBox();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 478));
        setMinimumSize(new java.awt.Dimension(650, 478));
        setPreferredSize(new java.awt.Dimension(650, 478));
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

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setEnabled(false);
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setEnabled(false);
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonEditar)
                        .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(3, 3, 3))
        );

        jLabelDni.setText("DNI");

        jLabelApellido.setText("Apellido");

        jLabelNombre.setText("Nombres");

        jLabelLocalidad.setText("Localidad");

        jLabelDomicilio.setText("Domicilio");

        jLabelEmail.setText("E-mail");

        jLabelFechaNacimiento.setText("Fecha de Nacimiento");

        jLabelFechaIngreso.setText("Fecha Ingreso");

        jLabelTelFijo.setText("Teléfono Fijo");

        jLabelTelCelular.setText("Teléfono Celular");

        jLabelFotocopiaDni.setText("Fotocopia de DNI");

        jButtonExaminarFotocopia.setText("...");

        jLabelArbitro.setText("¿Es Árbitro?");

        jLabelTecnico.setText("¿Es Cuerpo Técnico?");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTecnico)
                    .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDetallesLayout.createSequentialGroup()
                            .addGap(138, 138, 138)
                            .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelTelCelular)
                                .addComponent(jLabelDni)
                                .addComponent(jLabelApellido)
                                .addComponent(jLabelNombre)
                                .addComponent(jLabelLocalidad)
                                .addComponent(jLabelDomicilio)
                                .addComponent(jLabelEmail)
                                .addComponent(jLabelFechaIngreso)
                                .addComponent(jLabelFechaNacimiento)
                                .addComponent(jLabelTelFijo)
                                .addComponent(jLabelFotocopiaDni, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabelArbitro))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                            .addComponent(jTextFieldFotocopiaDni, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonExaminarFotocopia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBoxEsArbitro)
                    .addComponent(jCheckBoxEsCuerpoTecnico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDni)
                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jTextFieldFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelFijo)
                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTelCelular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFotocopiaDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExaminarFotocopia)
                    .addComponent(jLabelFotocopiaDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxEsArbitro)
                    .addComponent(jLabelArbitro))
                .addGap(3, 3, 3)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTecnico)
                    .addComponent(jCheckBoxEsCuerpoTecnico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            try {
                Date fechaNacimiento = new java.sql.Date(df.parse(jTextFieldFechaNacimiento.getText()).getTime());
                Date fechaIngreso = new java.sql.Date(df.parse(jTextFieldFechaIngreso.getText()).getTime());
                if (unaPersonaAuxiliar == null) {
                    unaControladoraGlobal.crearPersonaAuxiliar(
                            Long.parseLong(jTextFieldDNI.getText()),
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
                            jCheckBoxEsCuerpoTecnico.isSelected()
                    );
                    JOptionPane.showMessageDialog(this, "Persona Auxiliar Guardado");
                } else {
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
                            "Fotocopia Dni",
                            jCheckBoxEsArbitro.isSelected(),
                            jCheckBoxEsCuerpoTecnico.isSelected(),
                            unaPersonaAuxiliar.isBorradoLogico()
                    );
                    JOptionPane.showMessageDialog(this, "Persona Auxiliar Modificada");
                }
                this.dispose();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el DNI sin '.'");
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonImprimir.setEnabled(false);
        
        camposActivo(jPanelDetalles, true);
    }//GEN-LAST:event_jButtonEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExaminarFotocopia;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxEsArbitro;
    private javax.swing.JCheckBox jCheckBoxEsCuerpoTecnico;
    private javax.swing.JComboBox jComboBoxLocalidad;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelArbitro;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFotocopiaDni;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTecnico;
    private javax.swing.JLabel jLabelTelCelular;
    private javax.swing.JLabel jLabelTelFijo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
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
