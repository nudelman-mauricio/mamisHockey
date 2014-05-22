/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import logicaNegocios.PersonaAuxiliar;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IEquipo extends javax.swing.JInternalFrame {

    private JDesktopPane unjDesktopPane1;
    private JInternalFrame unJInternalFrame;

    private ControladoraGlobal unaControladoraGlobal;
    private Equipo unEquipo = null;  

    //LLAMADO DESDE EL MENUPRINCIPAL
    public IEquipo(ControladoraGlobal unaControladoraGlobal, JDesktopPane unjDesktopPane1) {
        initComponents();
        this.unjDesktopPane1 = unjDesktopPane1;
        
        SeInicio(unaControladoraGlobal);
        jTextFieldNombre.setEditable(true);
        jComboBoxClub.setEnabled(true);
        jComboBoxDT.setEnabled(true);
        jComboBoxPF.setEnabled(true);
        jComboBoxAC.setEnabled(true);
        
        jButtonCancelar.setEnabled(false);
        camposLimpiar();
    }
    
    //LLAMADO DESDE UN INTERNAL FRAME
    public IEquipo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        
        SeInicio(unaControladoraGlobal);
        
        jButtonCancelar.setEnabled(false);
        camposLimpiar();
    }
    
    //LLAMADO MOSTRANDO UN CLUB
    public IEquipo(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Equipo unEquipo) {
        initComponents();
        
        this.unEquipo = unEquipo;
        this.unJInternalFrame = unJInternalFrame;
        
        this.setTitle("Club: " + unEquipo.getNombre());
        
        SeInicio(unaControladoraGlobal);
                

        jButtonCancelar.setEnabled(true);

        camposCargar(unEquipo);
    }
    
    public void SeInicio(ControladoraGlobal unaControladoraGlobal) {
        this.unaControladoraGlobal = unaControladoraGlobal;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Equipoo.png")));

        camposActivo(false);
        
        cargarCombosBox();
        
        IMenuPrincipalInterface.centrar(this);
    }

    public void cargarCombosBox() {
        cargarComboBox(this.jComboBoxClub, (Vector) unaControladoraGlobal.getClubesBD());
        
        cargarComboBox(this.jComboBoxDT, (Vector) unaControladoraGlobal.getCuerposTecnicosBD());
        cargarComboBox(this.jComboBoxPF, (Vector) unaControladoraGlobal.getCuerposTecnicosBD());
        cargarComboBox(this.jComboBoxAC, (Vector) unaControladoraGlobal.getCuerposTecnicosBD());
        
        cargarComboBox(this.jComboBoxDelegada, (Vector) unEquipo.getPlantel());
        cargarComboBox(this.jComboBoxDelegadaSup, (Vector) unEquipo.getPlantel());        
        cargarComboBox(this.jComboBoxCapitana, (Vector) unEquipo.getPlantel());
        cargarComboBox(this.jComboBoxCapitanaSup, (Vector) unEquipo.getPlantel());
    }
    
    public void cargarComboBox(JComboBox unComboBox, Vector unVector) {
        DefaultComboBoxModel modelCombo = new DefaultComboBoxModel(unVector);
        unComboBox.setModel(modelCombo);
    }

    public void camposCargar(Equipo unEquipo) {
        jTextFieldNombre.setText(unEquipo.getNombre());
        
        //FALTAN METODOS PARA ESTE COMBO
        jComboBoxClub.setSelectedItem(unEquipo);
        
        jComboBoxDT.setSelectedItem(unEquipo.getUnDT());
        jComboBoxPF.setSelectedItem(unEquipo.getUnPreparadorFisico());
        jComboBoxAC.setSelectedItem(unEquipo.getUnAyudanteCampo());
        jComboBoxDelegada.setSelectedItem(unEquipo.getUnaDelegada());
        jComboBoxDelegadaSup.setSelectedItem(unEquipo.getUnaDelegadaSuplente());
        jComboBoxCapitana.setSelectedItem(unEquipo.getUnaCapitana());
        jComboBoxCapitanaSup.setSelectedItem(unEquipo.getUnaCapitanaSuplente());
    }
    
    public void camposActivo(boolean Editable) {
        jTextFieldNombre.setEditable(Editable);
        jComboBoxClub.setEnabled(Editable);
        jComboBoxDT.setEnabled(Editable);
        jComboBoxPF.setEnabled(Editable);
        jComboBoxAC.setEnabled(Editable);
        jComboBoxDelegada.setEnabled(Editable);
        jComboBoxDelegadaSup.setEnabled(Editable);
        jComboBoxCapitana.setEnabled(Editable);
        jComboBoxCapitanaSup.setEnabled(Editable);
        
        jButtonGuardar.setEnabled(Editable);
        jButtonCancelar.setEnabled(Editable);
        jButtonNuevo.setEnabled(!Editable);
    }

    public void camposLimpiar() {
        jTextFieldNombre.setText("");
        jComboBoxClub.setSelectedIndex(-1);
        jComboBoxClub.setSelectedIndex(-1);
        jComboBoxDT.setSelectedIndex(-1);
        jComboBoxPF.setSelectedIndex(-1);
        jComboBoxAC.setSelectedIndex(-1);
        jComboBoxDelegada.setSelectedIndex(-1);
        jComboBoxDelegadaSup.setSelectedIndex(-1);
        jComboBoxCapitana.setSelectedIndex(-1);
        jComboBoxCapitanaSup.setSelectedIndex(-1);
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
        jPanel3 = new javax.swing.JPanel();
        jLabelDni = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jLabelNombres = new javax.swing.JLabel();
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEditar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jLabelDni.setText("Nombre");

        jLabelApellido.setText("Club");

        jLabelNombres.setText("Director Tecnico");

        jLabelLocalidad.setText("Preparador Fisico");

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jLabelDomicilio.setText("Ayudante de Campo");

        jLabelFechaNacimiento.setText("Delegada");

        jLabelFechaIngreso.setText("Delegada Suplente");

        jComboBoxClub.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelFechaNacimiento1.setText("Capitana");

        jLabelFechaIngreso1.setText("Capitana Suplente");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombres, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLocalidad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaIngreso, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelApellido, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaNacimiento1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelFechaIngreso1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxClub, 0, 160, Short.MAX_VALUE)
                            .addComponent(jComboBoxDT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxPF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxAC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxDelegada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxDelegadaSup, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCapitana, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCapitanaSup, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelDni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(92, 92, 92))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido)
                    .addComponent(jComboBoxClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombres)
                    .addComponent(jComboBoxDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jComboBoxPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jComboBoxAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jComboBoxDelegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso)
                    .addComponent(jComboBoxDelegadaSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento1)
                    .addComponent(jComboBoxCapitana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaIngreso1)
                    .addComponent(jComboBoxCapitanaSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if (this.unEquipo == null){
            camposLimpiar();
        }else{
            camposCargar(unEquipo);
        }
        camposActivo(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jTextFieldNombre.setEditable(true);
        jComboBoxClub.setEnabled(true);
        jComboBoxDT.setEnabled(true);  
        jComboBoxPF.setEnabled(true);
        jComboBoxAC.setEnabled(true);

        camposLimpiar();
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        camposActivo(true);
        jButtonEditar.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
           if (this.unEquipo == null){
            unEquipo = unaControladoraGlobal.crearEquipo((Club)jComboBoxClub.getSelectedItem(), jTextFieldNombre.getName(), (PersonaAuxiliar)jComboBoxDT.getSelectedItem());
               if (jComboBoxPF.getSelectedIndex() == -1) {
                   unaControladoraGlobal.modificarEquipo(unEquipo, jTextFieldNombre.getName(), null, null, null, null, null, (PersonaAuxiliar)jComboBoxPF.getSelectedItem(), null, false);
               }
               if (jComboBoxPF.getSelectedIndex() == -1) {
                   unaControladoraGlobal.modificarEquipo(unEquipo, jTextFieldNombre.getName(), null, null, null, null, null, null,(PersonaAuxiliar)jComboBoxAC.getSelectedItem(), false);
               }
            JOptionPane.showMessageDialog(this, "Equipo creado con exito");            
        }else{
            unaControladoraGlobal.modificarEquipo(unEquipo, 
                    jTextFieldNombre.getName(),
                    (Socia) jComboBoxCapitana.getSelectedItem(),
                    (Socia) jComboBoxCapitanaSup.getSelectedItem(),
                    (Socia) jComboBoxDelegada.getSelectedItem(),
                    (Socia) jComboBoxDelegadaSup.getSelectedItem(),
                    (PersonaAuxiliar)jComboBoxDT.getSelectedItem(),
                    (PersonaAuxiliar)jComboBoxPF.getSelectedItem(),
                    (PersonaAuxiliar)jComboBoxAC.getSelectedItem(),
                    false);
            JOptionPane.showMessageDialog(this, "Equipo editado con exito");  
        }
        camposActivo(false);
        jButtonEditar.setEnabled(true);
    }//GEN-LAST:event_jButtonGuardarActionPerformed

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
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelFechaIngreso1;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFechaNacimiento1;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
