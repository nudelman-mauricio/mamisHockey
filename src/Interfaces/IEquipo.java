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
import logicaNegocios.Equipo;
import logicaNegocios.PersonaAuxiliar;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IEquipo extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private ControladoraGlobal unaControladoraGlobal;
    private Equipo unEquipo = null;

    //LLAMADO PARA UN NUEVO EQUIPO
    public IEquipo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();
        IMenuPrincipalInterface.centrar(this);
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Equipoo.png")));

        camposActivo(jPanelDetalles, true);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);

        cargarCombosBox();
        camposLimpiar();
    }

    //LLAMADO MOSTRANDO UN EQUIPO
    public IEquipo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Equipo unEquipo) {
        this(unaControladoraGlobal, unJInternalFrame);
        this.unEquipo = unEquipo;
        this.setTitle("Club: " + unEquipo.getNombre());
        cargarCombosBox();
        camposCargar(unEquipo);
        camposActivo(jPanelDetalles, false);

        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEditar.setEnabled(true);
    }

    //LLAMADO ESPECIAL PARA UN NUEVO EQUIPO DE UN CLUB YA SELECCIONADO
    public IEquipo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Club unClub) {
        this(unaControladoraGlobal, unJInternalFrame);
        jComboBoxClub.setSelectedItem(unClub);
        jComboBoxClub.setEnabled(false);
    }

    private void cargarCombosBox() {
        jComboBoxClub.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getClubesBD()));
        jComboBoxDT.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getCuerposTecnicosDesocupadosBD()));
        jComboBoxPF.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getCuerposTecnicosDesocupadosBD()));
        jComboBoxAC.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getCuerposTecnicosDesocupadosBD()));

        if (this.unEquipo != null) {
            jComboBoxDelegada.setModel(new DefaultComboBoxModel((Vector) unEquipo.getPlantel()));
            jComboBoxDelegadaSup.setModel(new DefaultComboBoxModel((Vector) unEquipo.getPlantel()));
            jComboBoxCapitana.setModel(new DefaultComboBoxModel((Vector) unEquipo.getPlantel()));
            jComboBoxCapitanaSup.setModel(new DefaultComboBoxModel((Vector) unEquipo.getPlantel()));
        } else {
            jComboBoxDelegada.setEnabled(false);
            jComboBoxDelegadaSup.setEnabled(false);
            jComboBoxCapitana.setEnabled(false);
            jComboBoxCapitanaSup.setEnabled(false);
        }
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

    private void camposLimpiar() {
        jTextFieldNombre.setText("");
        jComboBoxClub.setSelectedIndex(-1);
        jComboBoxDT.setSelectedIndex(-1);
        jComboBoxPF.setSelectedIndex(-1);
        jComboBoxAC.setSelectedIndex(-1);
        jComboBoxDelegada.setSelectedIndex(-1);
        jComboBoxDelegadaSup.setSelectedIndex(-1);
        jComboBoxCapitana.setSelectedIndex(-1);
        jComboBoxCapitanaSup.setSelectedIndex(-1);
    }

    private void camposCargar(Equipo unEquipo) {
        camposLimpiar();
        jComboBoxDT.setEditable(true);
        jComboBoxAC.setEditable(true);
        jComboBoxPF.setEditable(true);
        jTextFieldNombre.setText(unEquipo.getNombre());
        jComboBoxClub.setSelectedItem(unaControladoraGlobal.getClubBD(unEquipo));
        jComboBoxDT.setSelectedItem(unEquipo.getUnDT());
        if (unEquipo.getUnPreparadorFisico() != null) {
            jComboBoxPF.setSelectedItem(unEquipo.getUnPreparadorFisico());
        } else {
            jComboBoxPF.setSelectedIndex(-1);
        }
        if (unEquipo.getUnAyudanteCampo() != null) {
            jComboBoxAC.setSelectedItem(unEquipo.getUnAyudanteCampo());
        } else {
            jComboBoxAC.setSelectedIndex(-1);
        }
        if (unEquipo.getUnaDelegada() != null) {
            jComboBoxDelegada.setSelectedItem(unEquipo.getUnaDelegada());
        } else {
            jComboBoxDelegada.setSelectedIndex(-1);
        }
        if (unEquipo.getUnaDelegadaSuplente() != null) {
            jComboBoxDelegadaSup.setSelectedItem(unEquipo.getUnaDelegadaSuplente());
        } else {
            jComboBoxDelegadaSup.setSelectedIndex(-1);
        }
        if (unEquipo.getUnaCapitana() != null) {
            jComboBoxCapitana.setSelectedItem(unEquipo.getUnaCapitana());
        } else {
            jComboBoxCapitana.setSelectedIndex(-1);
        }
        if (unEquipo.getUnaCapitanaSuplente() != null) {
            jComboBoxCapitanaSup.setSelectedItem(unEquipo.getUnaCapitanaSuplente());
        } else {
            jComboBoxCapitanaSup.setSelectedIndex(-1);
        }
        jComboBoxDT.setEditable(false);
        jComboBoxAC.setEditable(false);
        jComboBoxPF.setEditable(false);
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldNombre.getText().isEmpty()) {
            jLabelNombre.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelNombre.setForeground(Color.black);
        }
        if (jComboBoxClub.getSelectedIndex() == -1) {
            jLabelClub.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelClub.setForeground(Color.black);
        }
        if (jComboBoxDT.getSelectedIndex() == -1) {
            jLabelDT.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelDT.setForeground(Color.black);
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
        jLabelClub = new javax.swing.JLabel();
        jLabelDT = new javax.swing.JLabel();
        jLabelLocalidad = new javax.swing.JLabel();
        jComboBoxDT = new javax.swing.JComboBox();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelDomicilio = new javax.swing.JLabel();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jLabelFechaIngreso = new javax.swing.JLabel();
        jComboBoxClub = new javax.swing.JComboBox();
        jLabelFechaNacimiento1 = new javax.swing.JLabel();
        jLabelFechaIngreso1 = new javax.swing.JLabel();
        jComboBoxPF = new javax.swing.JComboBox();
        jComboBoxAC = new javax.swing.JComboBox();
        jComboBoxDelegada = new javax.swing.JComboBox();
        jComboBoxDelegadaSup = new javax.swing.JComboBox();
        jComboBoxCapitana = new javax.swing.JComboBox();
        jComboBoxCapitanaSup = new javax.swing.JComboBox();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 377));
        setMinimumSize(new java.awt.Dimension(650, 377));
        setPreferredSize(new java.awt.Dimension(650, 377));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEditar)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jLabelNombre.setText("Nombre");

        jLabelClub.setText("Club");

        jLabelDT.setText("Director Tecnico");

        jLabelLocalidad.setText("Preparador Fisico");

        jLabelDomicilio.setText("Ayudante de Campo");

        jLabelFechaNacimiento.setText("Delegada");

        jLabelFechaIngreso.setText("Delegada Suplente");

        jLabelFechaNacimiento1.setText("Capitana");

        jLabelFechaIngreso1.setText("Capitana Suplente");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDT)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jLabelClub)
                    .addComponent(jLabelFechaNacimiento1)
                    .addComponent(jLabelFechaIngreso1)
                    .addComponent(jLabelNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBoxCapitana, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDelegadaSup, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDelegada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxClub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCapitanaSup, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClub)
                    .addComponent(jComboBoxClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDT)
                    .addComponent(jComboBoxDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jComboBoxPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jComboBoxAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jComboBoxDelegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jComboBoxDelegadaSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento1)
                    .addComponent(jComboBoxCapitana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso1)
                    .addComponent(jComboBoxCapitanaSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);

        camposActivo(jPanelDetalles, true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            Socia unaCapitana = null, unaCapitanaSup = null, unaDelegada = null, unaDelegadaSup = null;
            PersonaAuxiliar unDT = null, unPF = null, unAC = null;
            if (jComboBoxCapitana.getSelectedIndex() != -1) {
                unaCapitana = (Socia) jComboBoxCapitana.getSelectedItem();
            }
            if (jComboBoxCapitanaSup.getSelectedIndex() != -1) {
                unaCapitanaSup = (Socia) jComboBoxCapitanaSup.getSelectedItem();
            }
            if (jComboBoxDelegada.getSelectedIndex() != -1) {
                unaDelegada = (Socia) jComboBoxDelegada.getSelectedItem();
            }
            if (jComboBoxDelegadaSup.getSelectedIndex() != -1) {
                unaDelegadaSup = (Socia) jComboBoxDelegadaSup.getSelectedItem();
            }
            if (jComboBoxPF.getSelectedIndex() != -1) {
                unPF = (PersonaAuxiliar) jComboBoxPF.getSelectedItem();
                unaControladoraGlobal.marcarCuerpoTecnicoActivo(unPF, true);
            }
            if (jComboBoxAC.getSelectedIndex() != -1) {
                unAC = (PersonaAuxiliar) jComboBoxAC.getSelectedItem();
                unaControladoraGlobal.marcarCuerpoTecnicoActivo(unAC, true);
            }
            unDT = (PersonaAuxiliar) jComboBoxDT.getSelectedItem();
            unaControladoraGlobal.marcarCuerpoTecnicoActivo(unDT, true);
            if (this.unEquipo == null) {
                unEquipo = unaControladoraGlobal.crearEquipo((Club) jComboBoxClub.getSelectedItem(), jTextFieldNombre.getText(), unDT, unaCapitana, unaCapitanaSup, unaDelegada, unaDelegadaSup, unPF, unAC);
                JOptionPane.showMessageDialog(this, "Equipo Guardado");
            } else {
                unaControladoraGlobal.modificarEquipo(unEquipo, jTextFieldNombre.getText(), unaCapitana, unaCapitanaSup, unaDelegada, unaDelegadaSup, unDT, unPF, unAC, unEquipo.isBorradoLogico());
                JOptionPane.showMessageDialog(this, "Equipo Modificado");
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JComboBox jComboBoxAC;
    private javax.swing.JComboBox jComboBoxCapitana;
    private javax.swing.JComboBox jComboBoxCapitanaSup;
    private javax.swing.JComboBox jComboBoxClub;
    private javax.swing.JComboBox jComboBoxDT;
    private javax.swing.JComboBox jComboBoxDelegada;
    private javax.swing.JComboBox jComboBoxDelegadaSup;
    private javax.swing.JComboBox jComboBoxPF;
    private javax.swing.JLabel jLabelClub;
    private javax.swing.JLabel jLabelDT;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelFechaIngreso1;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFechaNacimiento1;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
