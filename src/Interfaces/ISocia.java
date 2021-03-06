package Interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import logicaNegocios.Club;
import logicaNegocios.Localidad;
import logicaNegocios.Socia;
import main.ControladoraGlobal;
import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

public class ISocia extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private DateFormat df = DateFormat.getDateInstance();
    private Socia unaSocia = null;
    private File archivoImagen = null;
    private static final int IMG_WIDTH = 129;
    private static final int IMG_HEIGHT = 126;
    private String ext = null;

    //LLAMADO PARA UNA NUEVA SOCIA
    public ISocia(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();

        IMenuPrincipal.jDesktopPane.add(this);
        IMenuPrincipal.centrarYalFrente(this);

        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;

        this.setTitle("Nueva Socia");//titulo de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("/Iconos Nuevos/Socia2.png")));//Icono de la ventana

        this.jComboBoxLocalidad.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getLocalidadesBD()));
        camposLimpiar();
        camposActivo(jPanelDetalles, true);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        
        jCheckBoxResponsableSede.setSelected(false);
        jComboBoxResponsableSede.setEnabled(false);
        
        
        //PARA REEVER PERO PARA LUCAS ACA ESTABA EL ERROR Y QDO FUNCIONANDO
        //jDateChooserFechaIngreso.setDateFormatString(df.format(unaControladoraGlobal.fechaSistema()));
        jDateChooserFechaIngreso.setDate(unaControladoraGlobal.fechaSistema());
        
        this.jComboBoxResponsableSede.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getClubesBD()));
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
       
        this.jComboBoxResponsableSede.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getClubesBD()));
        jComboBoxResponsableSede.setSelectedIndex(-1);
        for (Club unClub : unaControladoraGlobal.getClubesBD()) {
            if (unClub.getUnaResponsableSede() != null) {
                if (unClub.getUnaResponsableSede().equals(unaSocia)) {
                    jComboBoxResponsableSede.setSelectedItem(unClub);
                    jCheckBoxResponsableSede.setSelected(true);                    
                }
            }
        }
        if(jCheckBoxResponsableSede.isSelected()){
             jComboBoxResponsableSede.setEnabled(false);
        } else {
             jComboBoxResponsableSede.setEnabled(true);
        }
    }

    public void camposCargar(Socia unaSocia) {
        jTextFieldDNI.setText(unaSocia.getDni().toString());
        jTextFieldApellido.setText(unaSocia.getApellido());
        jTextFieldNombres.setText(unaSocia.getNombre());
        jComboBoxLocalidad.setSelectedItem(unaSocia.getUnaLocalidad());
        jTextFieldDomicilio.setText(unaSocia.getDomicilio());
        jDateChooserFechaNacimiento.setDate(unaSocia.getFechaNacimiento());
        jDateChooserFechaIngreso.setDate(unaSocia.getFechaIngreso());
        jTextFieldEmail.setText(unaSocia.getEmail());
        jTextFieldTelFijo.setText(unaSocia.getTelFijo());
        jTextFieldTelCelular.setText(unaSocia.getTelCelular());
        jCheckBoxExJugadora.setSelected(unaSocia.isExJugadora());

        //cargar imagen
        if (unaSocia.getFotoCarnet() != null) {
            ToolkitImage image = new ToolkitImage(new ByteArrayImageSource(unaSocia.getFotoCarnet()));
            this.jLabelFotoCarnet.setIcon(new ImageIcon(image));
        }

    }

    //deshabilitar todo lo de un contenedor
    private void camposActivo(Container c, boolean bandera) {
        Component[] components = c.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (!(components[i] instanceof JLabel)) {
                if (components[i] instanceof JTextField) {
                    ((JTextField) components[i]).setEditable(bandera);
                } else {
                    components[i].setEnabled(bandera);
                }
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
        jDateChooserFechaIngreso.setDate(null);
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
        if (jDateChooserFechaIngreso.getDate() == null) {
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
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelTelefonoFijo = new javax.swing.JLabel();
        jTextFieldTelFijo = new javax.swing.JTextField();
        jLabelTelefonoCelular = new javax.swing.JLabel();
        jTextFieldTelCelular = new javax.swing.JTextField();
        jDateChooserFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jDateChooserFechaIngreso = new com.toedter.calendar.JDateChooser();
        jButtonExaminar = new javax.swing.JButton();
        jLabelFotoCarnet = new javax.swing.JLabel();
        jLabelLocalidad1 = new javax.swing.JLabel();
        jComboBoxResponsableSede = new javax.swing.JComboBox();
        jCheckBoxResponsableSede = new javax.swing.JCheckBox();

        setClosable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(650, 442));
        setMinimumSize(new java.awt.Dimension(650, 442));
        setPreferredSize(new java.awt.Dimension(650, 442));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jTextFieldDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDNIActionPerformed(evt);
            }
        });

        jLabelDomicilio.setText("Domicilio");

        jTextFieldDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDomicilioActionPerformed(evt);
            }
        });

        jLabelFechaNacimiento.setText("Fecha de Nacimiento");

        jLabelFechaIngreso.setText("Fecha Ingreso");

        jLabelExJugadora.setText("ExJugadora ");

        jLabelEmail.setText("E-mail");

        jLabelTelefonoFijo.setText("Teléfono Fijo");

        jLabelTelefonoCelular.setText("Teléfono Celular");

        jDateChooserFechaNacimiento.setToolTipText("");

        jDateChooserFechaIngreso.setToolTipText("");

        jButtonExaminar.setText("Examinar");
        jButtonExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExaminarActionPerformed(evt);
            }
        });

        jLabelFotoCarnet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelLocalidad1.setText("Responsable de Sede");

        jComboBoxResponsableSede.setEnabled(false);

        jCheckBoxResponsableSede.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxResponsableSedeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jLabelExJugadora)
                    .addComponent(jLabelEmail)
                    .addComponent(jLabelTelefonoFijo)
                    .addComponent(jLabelTelefonoCelular)
                    .addComponent(jLabelLocalidad1)
                    .addComponent(jLabelNombres)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jLabelApellido)
                    .addComponent(jLabelDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBoxExJugadora)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jCheckBoxResponsableSede)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxResponsableSede, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldNombres, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserFechaIngreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldApellido)
                    .addComponent(jTextFieldDNI))
                .addGap(30, 30, 30)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFotoCarnet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jButtonExaminar)
                        .addGap(24, 24, 24)))
                .addGap(68, 68, 68))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombres))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jLabelLocalidad)
                                .addGap(12, 12, 12)
                                .addComponent(jLabelDomicilio))
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEmail)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaNacimiento))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaIngreso))
                        .addGap(6, 6, 6))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jLabelFotoCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExaminar)))
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jLabelTelefonoFijo))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonoCelular)
                    .addComponent(jTextFieldTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExJugadora)
                    .addComponent(jCheckBoxExJugadora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxResponsableSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCheckBoxResponsableSede)
                        .addComponent(jLabelLocalidad1)))
                .addContainerGap())
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if(jCheckBoxResponsableSede.isSelected()){
             jComboBoxResponsableSede.setEnabled(true);
        } else {
             jComboBoxResponsableSede.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            Date fechaNacimiento = new java.sql.Date((jDateChooserFechaNacimiento.getDate()).getTime());
            Date fechaIngreso = new java.sql.Date((jDateChooserFechaIngreso.getDate()).getTime());
            System.out.println(fechaIngreso);
            if (unaSocia == null) {
                unaControladoraGlobal.crearSocia(
                        Long.parseLong(jTextFieldDNI.getText()),
                        jTextFieldApellido.getText(),
                        jTextFieldNombres.getText(),
                        (Localidad) jComboBoxLocalidad.getSelectedItem(),
                        jTextFieldDomicilio.getText(),
                        fechaNacimiento,
                        fechaIngreso,
                        jCheckBoxExJugadora.isSelected(),
                        jTextFieldEmail.getText(),
                        jTextFieldTelFijo.getText(),
                        jTextFieldTelCelular.getText(),
                        cargarImagen(archivoImagen));                
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
                        cargarImagen(archivoImagen),
                        jCheckBoxExJugadora.isSelected()
                );                
                JOptionPane.showMessageDialog(this, "Socia Modificada");
            }
            if(jCheckBoxResponsableSede.isSelected()){
                    Club unClubSeleccionado = (Club) jComboBoxResponsableSede.getSelectedItem();
                    unClubSeleccionado.setUnaResponsableSede(unaControladoraGlobal.getSociaBD(Long.valueOf(jTextFieldDNI.getText())));
             }
            this.dispose();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExaminarActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == returnVal) {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                archivoImagen = chooser.getSelectedFile();
                ext = chooser.getTypeDescription(archivoImagen);
                if (ext.equals("Imagen JPEG") || ext.equals("Imagen GIF")) {
                    ImageIcon icon = new ImageIcon(archivoImagen.toString());
                    Icon icono = new ImageIcon(icon.getImage().getScaledInstance(jLabelFotoCarnet.getWidth(), jLabelFotoCarnet.getHeight(), Image.SCALE_DEFAULT));
                    jLabelFotoCarnet.setText(null);
                    jLabelFotoCarnet.setIcon(icono);
                } else {
                    JOptionPane.showMessageDialog(this, "La extension del archivo es incorrecta.");
                }

            }

        }
    }//GEN-LAST:event_jButtonExaminarActionPerformed

    private void jCheckBoxResponsableSedeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxResponsableSedeItemStateChanged
        if (jCheckBoxResponsableSede.isSelected()) {
            jComboBoxResponsableSede.setEnabled(true);
        } else {
            jComboBoxResponsableSede.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxResponsableSedeItemStateChanged

    private void jTextFieldDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDNIActionPerformed

    private void jTextFieldDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDomicilioActionPerformed

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        return resizedImage;
    }

    private byte[] cargarImagen(File archivoImagen) {
        byte[] buffer = null;
        if (ext != null) {
            if (ext.equals("Imagen JPEG") || ext.equals("Imagen GIF")) {
                if (archivoImagen != null) {
                    try {
                        BufferedImage originalImage = ImageIO.read(archivoImagen);
                        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                        BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                        ImageIO.write(resizeImageJpg, "jpg", archivoImagen);
                        InputStream is = new FileInputStream(archivoImagen);
                        buffer = new byte[(int) archivoImagen.length()];
                        int readers = is.read(buffer);
                    } catch (IOException ex) {
                        Logger.getLogger(ISocia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return buffer;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExaminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxExJugadora;
    private javax.swing.JCheckBox jCheckBoxResponsableSede;
    private javax.swing.JComboBox jComboBoxLocalidad;
    private javax.swing.JComboBox jComboBoxResponsableSede;
    private com.toedter.calendar.JDateChooser jDateChooserFechaIngreso;
    private com.toedter.calendar.JDateChooser jDateChooserFechaNacimiento;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelExJugadora;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFotoCarnet;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelLocalidad1;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JLabel jLabelTelefonoCelular;
    private javax.swing.JLabel jLabelTelefonoFijo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDomicilio;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNombres;
    private javax.swing.JTextField jTextFieldTelCelular;
    private javax.swing.JTextField jTextFieldTelFijo;
    // End of variables declaration//GEN-END:variables
}
