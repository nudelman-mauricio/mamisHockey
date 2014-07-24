/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;

/**
 *
 * @author Lucas
 */
public class IFechasTorneos extends javax.swing.JInternalFrame {

    ControladoraGlobal unaControladoraGlobal;
    JInternalFrame unJInternalFrame;
    Torneo unTorneo;
    Collection FechasTorneo;

    IFechasTorneos(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Torneo unTorneoSeleccionado) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneoSeleccionado;
        FechasTorneo = unTorneo.getFechasTorneo();
        if (unTorneo.getCantidadFechas() > 0) {
            this.jLabelFecha.setText("1");
            this.jTextFieldNroFecha.setText("1");
        } else {
            this.jTextFieldNroFecha.setText("0");
        }

        this.setTitle("Torneo: " + unTorneo.getNombre());
        SeInicio();
        cargarTabla();
    }

    public void SeInicio() {
        //Icono de la ventana
        //setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Equipoo.png")));
        jButtonEliminar.setEnabled(false);
        IMenuPrincipalInterface.centrar(this);

    }

    private void cargarTabla() {

    }
    
    private void cargarTabla(FechaTorneo fecha) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonFechas = new javax.swing.JButton();
        jButtonPases = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSocias = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonInicio = new javax.swing.JButton();
        jTextFieldNroFecha = new javax.swing.JTextField();
        jButtonSiguiente = new javax.swing.JButton();
        jButtonAnterior = new javax.swing.JButton();
        jButtonFinal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxLocalidad4 = new javax.swing.JComboBox();
        jComboBoxLocalidad6 = new javax.swing.JComboBox();
        jComboBoxLocalidad = new javax.swing.JComboBox();
        jComboBoxLocalidad1 = new javax.swing.JComboBox();
        jComboBoxLocalidad2 = new javax.swing.JComboBox();
        jLabelApellido = new javax.swing.JLabel();
        jLabelNombre1 = new javax.swing.JLabel();
        jLabelLocalidad = new javax.swing.JLabel();
        jLabelDomicilio = new javax.swing.JLabel();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jLabelFechaNacimiento1 = new javax.swing.JLabel();
        jComboBoxLocalidad3 = new javax.swing.JComboBox();
        jButtonArgregar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEliminarPartido = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/deletered.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
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

        jButtonFechas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PanillaResultados.png"))); // NOI18N
        jButtonFechas.setText("Rtdo. Partido");
        jButtonFechas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFechas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFechasActionPerformed(evt);
            }
        });

        jButtonPases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Partido.png"))); // NOI18N
        jButtonPases.setText("Partido");
        jButtonPases.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPases.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasesActionPerformed(evt);
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
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPases, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        jTableSocias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Equipo Local", "GL", "GV", "Equipo Visitante", "Cancha", "Arbitro 1", "Arbitro 2", "Arbitro 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableSocias);
        if (jTableSocias.getColumnModel().getColumnCount() > 0) {
            jTableSocias.getColumnModel().getColumn(1).setResizable(false);
            jTableSocias.getColumnModel().getColumn(1).setPreferredWidth(5);
            jTableSocias.getColumnModel().getColumn(2).setResizable(false);
            jTableSocias.getColumnModel().getColumn(2).setPreferredWidth(5);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha n°:");

        jLabelFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelFecha.setText("0");

        jButtonInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/backFin.png"))); // NOI18N

        jTextFieldNroFecha.setEditable(false);
        jTextFieldNroFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldNroFecha.setText("88");
        jTextFieldNroFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNroFechaActionPerformed(evt);
            }
        });

        jButtonSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/NextNavegacion.png"))); // NOI18N
        jButtonSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSiguienteMouseClicked(evt);
            }
        });
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });

        jButtonAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/backNevagacion.png"))); // NOI18N

        jButtonFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/NextFin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNroFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButtonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNroFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 281, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFecha)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelFecha))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelApellido.setText("Equipo Local");

        jLabelNombre1.setText("Equipo Visitante");

        jLabelLocalidad.setText("Cancha");

        jLabelDomicilio.setText("Arbitro 1");

        jLabelFechaNacimiento.setText("Arbitro 2");

        jLabelFechaNacimiento1.setText("Arbitro 3");

        jButtonArgregar.setText("Agregar");

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonEliminarPartido.setText("Eliminar");
        jButtonEliminarPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarPartidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jLabelApellido)
                    .addComponent(jLabelNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxLocalidad4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxLocalidad6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelFechaNacimiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxLocalidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelDomicilio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxLocalidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelFechaNacimiento1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxLocalidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonArgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarPartido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido)
                    .addComponent(jComboBoxLocalidad4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jComboBoxLocalidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonArgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre1)
                    .addComponent(jComboBoxLocalidad6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jComboBoxLocalidad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento1)
                    .addComponent(jComboBoxLocalidad3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jButtonEliminarPartido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonPasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasesActionPerformed

    }//GEN-LAST:event_jButtonPasesActionPerformed

    private void jButtonFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFechasActionPerformed

    }//GEN-LAST:event_jButtonFechasActionPerformed

    private void jButtonSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSiguienteMouseClicked
        Collection<FechaTorneo> fechas = this.unTorneo.getFechasTorneo();
        FechaTorneo fechaSiguiente;
        for (FechaTorneo aux : fechas) {
            if(aux.getNumeroFecha() == Integer.parseInt(jTextFieldNroFecha.getText())){
                fechaSiguiente=this.unaControladoraGlobal.getSiguienteFecha(aux,unTorneo);
                if(fechaSiguiente != null){
                   jTextFieldNroFecha.setText(String.valueOf(fechaSiguiente.getNumeroFecha()));
                   cargarTabla(fechaSiguiente);
                }                    
            }                
        }       
    }//GEN-LAST:event_jButtonSiguienteMouseClicked

    private void jTextFieldNroFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNroFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNroFechaActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        this.unaControladoraGlobal.crearFechaTorneo(unTorneo, unTorneo.getCantidadFechas()+1);
        if (jLabelFecha.getText() == "0") {
            jLabelFecha.setText("1");
            jTextFieldNroFecha.setText("1");
        }
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarPartidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEliminarPartidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonArgregar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEliminarPartido;
    private javax.swing.JButton jButtonFechas;
    private javax.swing.JButton jButtonFinal;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonPases;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JComboBox jComboBoxLocalidad;
    private javax.swing.JComboBox jComboBoxLocalidad1;
    private javax.swing.JComboBox jComboBoxLocalidad2;
    private javax.swing.JComboBox jComboBoxLocalidad3;
    private javax.swing.JComboBox jComboBoxLocalidad4;
    private javax.swing.JComboBox jComboBoxLocalidad6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFechaNacimiento1;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSocias;
    private javax.swing.JTextField jTextFieldNroFecha;
    // End of variables declaration//GEN-END:variables
}
