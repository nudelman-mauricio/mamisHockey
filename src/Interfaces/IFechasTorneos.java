/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Collection;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Cancha;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Partido;
import logicaNegocios.PersonaAuxiliar;
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
    DefaultComboBoxModel modelComboLocal, modelComboVisitante;
    DefaultTableModel modeloFechaTorneo;
    FechaTorneo unaFechaTorneo;

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
        this.modeloFechaTorneo = (DefaultTableModel) jTableFechasTorneo.getModel();
        this.setTitle("Torneo: " + unTorneo.getNombre());
        SeInicio();
        if (unTorneo.getCantidadFechas() > 0) {
            this.unaFechaTorneo = unaControladoraGlobal.getUnaFecha(1, unTorneo);
            cargarTabla(unaFechaTorneo);
        }
        this.jButtonEditar.setEnabled(false);
        habilitarCampos(false);
        seleccionarObjetoTabla(false);
    }

    public void SeInicio() {
        //Icono de la ventana
        //setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Equipoo.png")));       
        IMenuPrincipalInterface.centrar(this);
        jLabelTotalFechas.setText(String.valueOf(unTorneo.getCantidadFechas()));

    }

    private void habilitarCampos(boolean estado) {
        this.jComboBoxArbitro1.setEnabled(estado);
        this.jComboBoxArbitro2.setEnabled(estado);
        this.jComboBoxArbitro3.setEnabled(estado);
        this.jComboBoxCancha.setEnabled(estado);
        this.jComboBoxEquipoLocal.setEnabled(estado);
        this.jComboBoxEquipoVisitante.setEnabled(estado);
        this.jButtonGuardar.setEnabled(estado);
        this.jButtonCancelar.setEnabled(estado);
        this.jButtonEliminar.setEnabled(!estado);
        this.jButtonNuevo.setEnabled(!estado);
        this.jButtonImprimir.setEnabled(!estado);
    }

    private void limpiarCampos() {
        this.jComboBoxArbitro1.setSelectedIndex(-1);
        this.jComboBoxArbitro2.setSelectedIndex(-1);
        this.jComboBoxArbitro3.setSelectedIndex(-1);
        this.jComboBoxCancha.setSelectedIndex(-1);
        this.jComboBoxEquipoLocal.setSelectedIndex(-1);
        this.jComboBoxEquipoVisitante.setSelectedIndex(-1);
    }

    private void cargarCombos() {

        this.modelComboLocal = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getEquipoPorFecha(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo)));

        this.jComboBoxEquipoLocal.setModel(modelComboLocal);

        this.modelComboVisitante = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getEquipoPorFecha(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo)));
        this.jComboBoxEquipoVisitante.setModel(modelComboVisitante);

        DefaultComboBoxModel modelComboCancha = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getCanchasPorFecha(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo)));
        this.jComboBoxCancha.setModel(modelComboCancha);

        DefaultComboBoxModel modelComboArbitro = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getArbitrosPorFecha(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo)));
        this.jComboBoxArbitro1.setModel(modelComboArbitro);

        modelComboArbitro = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getArbitrosPorFecha(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo)));
        this.jComboBoxArbitro2.setModel(modelComboArbitro);

        modelComboArbitro = new DefaultComboBoxModel((Vector) unaControladoraGlobal.getArbitrosPorFecha(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo)));
        this.jComboBoxArbitro3.setModel(modelComboArbitro);

    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int filas = modeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTabla.removeRow(0);
        }
    }

    private void cargarTabla(FechaTorneo unaFecha) {
        limpiarTabla(modeloFechaTorneo);
        if (unaFecha != null) {
            this.modeloFechaTorneo = (DefaultTableModel) jTableFechasTorneo.getModel();
            Collection<Partido> listaPartidos = unaFecha.getPartidos();
            if (listaPartidos != null) {
                for (Partido unPartido : listaPartidos) {
                    if (!unPartido.isBorradoLogico()) {
                        this.modeloFechaTorneo.addRow(new Object[]{unPartido.getIdPartido(), unPartido.getUnEquipoLocal(), "", "", unPartido.getUnEquipoVisitante(), unPartido.getUnaCancha(), unPartido.getUnArbitro1(), unPartido.getUnArbitro2(), unPartido.getUnArbitro3()});
                    }
                }
            }
        }
    }

    public void botonesNavegacion(boolean estado) {
        this.jButtonAnterior.setEnabled(estado);
        this.jButtonFinal.setEnabled(estado);
        this.jButtonInicio.setEnabled(estado);
        this.jButtonSiguiente.setEnabled(estado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonResultadoPartido = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFechasTorneo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxEquipoLocal = new javax.swing.JComboBox();
        jComboBoxEquipoVisitante = new javax.swing.JComboBox();
        jComboBoxCancha = new javax.swing.JComboBox();
        jComboBoxArbitro1 = new javax.swing.JComboBox();
        jComboBoxArbitro2 = new javax.swing.JComboBox();
        jLabelApellido = new javax.swing.JLabel();
        jLabelNombre1 = new javax.swing.JLabel();
        jLabelLocalidad = new javax.swing.JLabel();
        jLabelDomicilio = new javax.swing.JLabel();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jLabelFechaNacimiento1 = new javax.swing.JLabel();
        jComboBoxArbitro3 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButtonInicio = new javax.swing.JButton();
        jTextFieldNroFecha = new javax.swing.JTextField();
        jButtonSiguiente = new javax.swing.JButton();
        jButtonAnterior = new javax.swing.JButton();
        jButtonFinal = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabelFecha1 = new javax.swing.JLabel();
        jLabelTotalFechas = new javax.swing.JLabel();

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

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/deletered.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
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

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jButtonResultadoPartido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PanillaResultados.png"))); // NOI18N
        jButtonResultadoPartido.setText("Rtdo. Partido");
        jButtonResultadoPartido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonResultadoPartido.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonResultadoPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResultadoPartidoActionPerformed(evt);
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

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
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
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonResultadoPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResultadoPartido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableFechasTorneo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Equipo Local", "GL", "GV", "Equipo Visitante", "Cancha", "Arbitro 1", "Arbitro 2", "Arbitro 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFechasTorneo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableFechasTorneoFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFechasTorneo);
        if (jTableFechasTorneo.getColumnModel().getColumnCount() > 0) {
            jTableFechasTorneo.getColumnModel().getColumn(0).setMinWidth(0);
            jTableFechasTorneo.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableFechasTorneo.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableFechasTorneo.getColumnModel().getColumn(2).setResizable(false);
            jTableFechasTorneo.getColumnModel().getColumn(2).setPreferredWidth(5);
            jTableFechasTorneo.getColumnModel().getColumn(3).setResizable(false);
            jTableFechasTorneo.getColumnModel().getColumn(3).setPreferredWidth(5);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha n°:");

        jLabelFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelFecha.setText("0");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxEquipoLocal.setEnabled(false);

        jComboBoxEquipoVisitante.setEnabled(false);
        jComboBoxEquipoVisitante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEquipoVisitanteItemStateChanged(evt);
            }
        });

        jComboBoxCancha.setEnabled(false);

        jComboBoxArbitro1.setEnabled(false);

        jComboBoxArbitro2.setEnabled(false);

        jLabelApellido.setText("Equipo Local");

        jLabelNombre1.setText("Equipo Visitante");

        jLabelLocalidad.setText("Cancha");

        jLabelDomicilio.setText("Arbitro 1");

        jLabelFechaNacimiento.setText("Arbitro 2");

        jLabelFechaNacimiento1.setText("Arbitro 3");

        jComboBoxArbitro3.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLocalidad)
                    .addComponent(jLabelApellido)
                    .addComponent(jLabelNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelFechaNacimiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxArbitro2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelDomicilio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelFechaNacimiento1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jComboBoxCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellido)
                    .addComponent(jComboBoxEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDomicilio)
                    .addComponent(jComboBoxArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre1)
                    .addComponent(jComboBoxEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaNacimiento)
                    .addComponent(jComboBoxArbitro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaNacimiento1)
                    .addComponent(jComboBoxArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLocalidad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/backFin.png"))); // NOI18N
        jButtonInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonInicioMouseClicked(evt);
            }
        });

        jTextFieldNroFecha.setEditable(false);
        jTextFieldNroFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldNroFecha.setText("88");

        jButtonSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/NextNavegacion.png"))); // NOI18N
        jButtonSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSiguienteMouseClicked(evt);
            }
        });

        jButtonAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/backNevagacion.png"))); // NOI18N
        jButtonAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAnteriorMouseClicked(evt);
            }
        });

        jButtonFinal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/NextFin.png"))); // NOI18N
        jButtonFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFinalMouseClicked(evt);
            }
        });

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Nuevo2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelFecha1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelFecha1.setText("de");

        jLabelTotalFechas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTotalFechas.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelFecha1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTotalFechas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTotalFechas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonResultadoPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResultadoPartidoActionPerformed
        Partido unPartidoSeleccionado = unaControladoraGlobal.getPartidoBD((Long) jTableFechasTorneo.getValueAt(jTableFechasTorneo.getSelectedRow(), 0));
        IResultadoPartido unIResultadoPartido = new IResultadoPartido(unaControladoraGlobal, this, unPartidoSeleccionado);
        unIResultadoPartido.pack();
        unIResultadoPartido.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIResultadoPartido);
    }//GEN-LAST:event_jButtonResultadoPartidoActionPerformed

    private void jButtonSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSiguienteMouseClicked
        if (unTorneo.getCantidadFechas() > Integer.parseInt(jTextFieldNroFecha.getText())) {
            jTextFieldNroFecha.setText(String.valueOf(Integer.parseInt(this.jTextFieldNroFecha.getText()) + 1));
            this.unaFechaTorneo = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo);
            cargarTabla(unaFechaTorneo);
            jLabelFecha.setText(jTextFieldNroFecha.getText());
        }
        seleccionarObjetoTabla(false);
    }//GEN-LAST:event_jButtonSiguienteMouseClicked

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        habilitarCampos(true);
        cargarCombos();
        limpiarCampos();
        seleccionarObjetoTabla(false);
        botonesNavegacion(false);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        habilitarCampos(false);
        limpiarCampos();
        botonesNavegacion(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        habilitarCampos(true);
        botonesNavegacion(false);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (jComboBoxEquipoLocal.getSelectedItem() != null && jComboBoxEquipoVisitante.getSelectedItem() != null && jComboBoxCancha.getSelectedItem() != null && jComboBoxArbitro1.getSelectedItem() != null && jComboBoxArbitro2.getSelectedItem() != null) {
            if (jComboBoxEquipoLocal.getSelectedItem() == jComboBoxEquipoVisitante.getSelectedItem()) {
                JOptionPane.showMessageDialog(null, "Error, El equipo local y visitante son iguales.");
            } else {
                if ((jComboBoxArbitro1.getSelectedItem() == jComboBoxArbitro2.getSelectedItem()) && (jComboBoxArbitro1.getSelectedItem() == jComboBoxArbitro3.getSelectedItem()) && (jComboBoxArbitro2.getSelectedItem() == jComboBoxArbitro3.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Error, verifique la asignacion de los arbitros.");
                } else {
                    unaControladoraGlobal.crearPartido(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo), (Equipo) jComboBoxEquipoVisitante.getSelectedItem(), null, (PersonaAuxiliar) jComboBoxArbitro1.getSelectedItem(), (PersonaAuxiliar) jComboBoxArbitro2.getSelectedItem(), (PersonaAuxiliar) jComboBoxArbitro3.getSelectedItem(), (Cancha) jComboBoxCancha.getSelectedItem(), title, (Equipo) jComboBoxEquipoLocal.getSelectedItem());
                    cargarTabla(unaFechaTorneo);
                    habilitarCampos(false);
                    seleccionarObjetoTabla(false);
                    botonesNavegacion(true);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Hay campos sin completar");
        }

    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jComboBoxEquipoVisitanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxEquipoVisitanteItemStateChanged

    }//GEN-LAST:event_jComboBoxEquipoVisitanteItemStateChanged

    private void jTableFechasTorneoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableFechasTorneoFocusGained
        seleccionarObjetoTabla(true);
    }//GEN-LAST:event_jTableFechasTorneoFocusGained

    private void jButtonAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnteriorMouseClicked
        if (Integer.parseInt(jTextFieldNroFecha.getText()) > 1) {
            jTextFieldNroFecha.setText(String.valueOf(Integer.parseInt(this.jTextFieldNroFecha.getText()) - 1));
            this.unaFechaTorneo = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo);
            cargarTabla(this.unaFechaTorneo);
            jLabelFecha.setText(jTextFieldNroFecha.getText());
        }
        seleccionarObjetoTabla(false);
    }//GEN-LAST:event_jButtonAnteriorMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        unaControladoraGlobal.crearFechaTorneo(unTorneo, unTorneo.getCantidadFechas() + 1);
        if (Integer.parseInt(this.jLabelTotalFechas.getText()) == 0) {
            jLabelFecha.setText("1");
            jTextFieldNroFecha.setText("1");
        }
        jLabelTotalFechas.setText(String.valueOf(unTorneo.getCantidadFechas()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFinalMouseClicked
        jTextFieldNroFecha.setText(String.valueOf(unTorneo.getCantidadFechas()));
        this.unaFechaTorneo = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo);
        cargarTabla(unaFechaTorneo);
        jLabelFecha.setText(jTextFieldNroFecha.getText());
        habilitarCampos(false);
        seleccionarObjetoTabla(false);
    }//GEN-LAST:event_jButtonFinalMouseClicked

    private void jButtonInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonInicioMouseClicked
        if (unTorneo.getCantidadFechas() > 0) {
            jTextFieldNroFecha.setText(String.valueOf(1));
            this.unaFechaTorneo = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo);
            cargarTabla(this.unaFechaTorneo);
        } else {
            jTextFieldNroFecha.setText(String.valueOf(0));
        }
        jLabelFecha.setText(jTextFieldNroFecha.getText());
        habilitarCampos(false);
        seleccionarObjetoTabla(false);
    }//GEN-LAST:event_jButtonInicioMouseClicked

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int vacio = 0;
        for (Partido unPartido : this.unaFechaTorneo.getPartidos()) {
            if (!unPartido.isBorradoLogico()) {
                vacio++;
            }
        }
        if (vacio == 0 && unTorneo.getCantidadFechas() == Integer.parseInt(jTextFieldNroFecha.getText())) {
            Object[] options = {"Aceptar", "Cancelar"};
            if (0 == JOptionPane.showOptionDialog(
                    this,
                    "¿Desea eliminar la fecha?",
                    "Eliminar",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options)) {
                unaControladoraGlobal.eliminarFechaTorneo(this.unaFechaTorneo);
                unaFechaTorneo = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()) - 1, unTorneo);
                this.jLabelTotalFechas.setText(String.valueOf(unTorneo.getCantidadFechas()));
                this.jTextFieldNroFecha.setText(this.jLabelTotalFechas.getText());
                this.jLabelFecha.setText(this.jLabelTotalFechas.getText());
                cargarTabla(unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo));
            }
        } else {
            if (vacio == 0) {
                JOptionPane.showMessageDialog(this, "Solo se puede borrar la ultima fecha de un Torneo");
            } else {
                if (jTableFechasTorneo.getSelectedRow() != (-1)) {
                    if (jTableFechasTorneo.getValueAt(jTableFechasTorneo.getSelectedRow(), 0) != null) {
                        Object[] options = {"Aceptar", "Cancelar"};
                        if (0 == JOptionPane.showOptionDialog(
                                this,
                                "¿Desea eliminar el partido?",
                                "Eliminar",
                                JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.WARNING_MESSAGE,
                                null,
                                options,
                                options)) {
                            Partido unPartidoSeleccionado = unaControladoraGlobal.getPartidoBD((Long) jTableFechasTorneo.getValueAt(jTableFechasTorneo.getSelectedRow(), 0));
                            unaControladoraGlobal.eliminarPartido(unPartidoSeleccionado);
                            cargarTabla(unaFechaTorneo);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un partido para eliminar");
                }
            }

        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    public void seleccionarObjetoTabla(boolean estado) {
        this.jButtonEditar.setEnabled(estado);
        this.jButtonResultadoPartido.setEnabled(estado);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonFinal;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonResultadoPartido;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JComboBox jComboBoxArbitro1;
    private javax.swing.JComboBox jComboBoxArbitro2;
    private javax.swing.JComboBox jComboBoxArbitro3;
    private javax.swing.JComboBox jComboBoxCancha;
    private javax.swing.JComboBox jComboBoxEquipoLocal;
    private javax.swing.JComboBox jComboBoxEquipoVisitante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFecha1;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFechaNacimiento1;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre1;
    private javax.swing.JLabel jLabelTotalFechas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFechasTorneo;
    private javax.swing.JTextField jTextFieldNroFecha;
    // End of variables declaration//GEN-END:variables
}
