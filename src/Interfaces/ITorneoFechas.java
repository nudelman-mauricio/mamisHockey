package Interfaces;

import DataSources.FixtureDS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Cancha;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Partido;
import logicaNegocios.PersonaAuxiliar;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;

public class ITorneoFechas extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Torneo unTorneo;
    private FechaTorneo unaFechaTorneoSeleccionada;
    private Partido unPartidoSeleccionado;
    private DefaultTableModel modeloTable;
    private DateFormat df = DateFormat.getDateInstance();

    ITorneoFechas(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Torneo unTorneo) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unTorneo = unTorneo;
        this.modeloTable = (DefaultTableModel) jTableFechasTorneo.getModel();

        this.setTitle("Torneo: " + unTorneo.getNombre());
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Torneo.png")));
        IMenuPrincipalInterface.centrar(this);

        if (unTorneo.getCantidadFechas() > 0) {
            this.unaFechaTorneoSeleccionada = unaControladoraGlobal.getUnaFecha(1, unTorneo);
            cargarTabla();
        }

        setearLabelsCantidadFechas();
        cargarCombos();
        camposActivo(jPanelDetalles, false);
    }

    private void setearLabelsCantidadFechas() {
        if (unTorneo.getCantidadFechas() > 0) {
            this.jLabelFecha.setText("1");
            this.jTextFieldNroFecha.setText("1");
        } else {
            this.jTextFieldNroFecha.setText("0");
            this.jButtonNuevo.setEnabled(false);
            camposActivo(jPanelBotones2, false);
        }
        jLabelTotalFechas.setText(String.valueOf(unTorneo.getCantidadFechas()));
    }

    private void cargarCombos() {
        //combobox Local     
        FechaTorneo unaFechaAux = unTorneo.getUnaFecha(Integer.valueOf(jTextFieldNroFecha.getText()));
        List equiposParticipantes = new ArrayList();
        jComboBoxEquipoLocal.removeAllItems();
        jComboBoxEquipoVisitante.removeAllItems();
        if (unaFechaAux != null) {
            for (Partido unPartido : unaFechaAux.getPartidos()) {
                equiposParticipantes.add(unPartido.getUnEquipoLocal().getNombre());
                equiposParticipantes.add(unPartido.getUnEquipoVisitante().getNombre());
            }
            for (Equipo unEquipo : unTorneo.getEquiposInscriptos()) {
                if (!equiposParticipantes.contains(unEquipo.getNombre())) {
                    jComboBoxEquipoLocal.addItem(unEquipo.getNombre());
                    jComboBoxEquipoVisitante.addItem(unEquipo.getNombre());
                }

            }
        }

        //jComboBoxEquipoLocal.setModel(new DefaultComboBoxModel((Vector) unTorneo.getEquiposInscriptos()));
        jComboBoxEquipoLocal.setSelectedIndex(-1);

        //combox visitante
        //jComboBoxEquipoVisitante.setModel(new DefaultComboBoxModel((Vector) unTorneo.getEquiposInscriptos()));
        jComboBoxEquipoVisitante.setSelectedIndex(-1);

        jComboBoxCancha.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getCanchasBD()));
        jComboBoxCancha.setSelectedIndex(-1);
        jComboBoxArbitro1.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getArbitrosBD()));
        jComboBoxArbitro1.setSelectedIndex(-1);
        jComboBoxArbitro2.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getArbitrosBD()));
        jComboBoxArbitro2.setSelectedIndex(-1);
        jComboBoxArbitro3.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getArbitrosBD()));
        jComboBoxArbitro3.setSelectedIndex(-1);
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int filas = modeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTabla.removeRow(0);
        }
    }

    private void cargarTabla() {
        limpiarTabla(modeloTable);
        if (unaFechaTorneoSeleccionada != null) {
            for (Partido unPartido : unaFechaTorneoSeleccionada.getPartidos()) {
                if (!unPartido.isBorradoLogico()) {
                    if (unPartido.getNombreVeedor() == null) {
                        this.modeloTable.addRow(new Object[]{unPartido.getIdPartido(), df.format(unPartido.getFecha()), unPartido.getUnEquipoLocal(), "", "", unPartido.getUnEquipoVisitante(), unPartido.getUnaCancha(), unPartido.getUnArbitro1(), unPartido.getUnArbitro2(), unPartido.getUnArbitro3()});
                    } else {
                        this.modeloTable.addRow(new Object[]{unPartido.getIdPartido(), df.format(unPartido.getFecha()), unPartido.getUnEquipoLocal(), unaControladoraGlobal.getGolesLocal(unPartido), unaControladoraGlobal.getGolesVisitante(unPartido), unPartido.getUnEquipoVisitante(), unPartido.getUnaCancha(), unPartido.getUnArbitro1(), unPartido.getUnArbitro2(), unPartido.getUnArbitro3()});
                    }
                }
            }
        }
        jButtonEditar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(true);
        jButtonResultadoPartido.setEnabled(false);
    }

    //actualizar los campos al seleccionar una cancha en la tabla
    private void camposCargar() {
        if (jTableFechasTorneo.getSelectedRow() > -1) {
            if (jTableFechasTorneo.getValueAt(jTableFechasTorneo.getSelectedRow(), 0) != null) {
                unPartidoSeleccionado = unaControladoraGlobal.getPartidoBD((Long) jTableFechasTorneo.getValueAt(jTableFechasTorneo.getSelectedRow(), 0));

                camposLimpiar();              
                jDateChooserFecha.setDate(unPartidoSeleccionado.getFecha());
                jComboBoxCancha.setSelectedItem(unPartidoSeleccionado.getUnaCancha());
                jComboBoxArbitro1.setSelectedItem(unPartidoSeleccionado.getUnArbitro1());
                jComboBoxArbitro2.setSelectedItem(unPartidoSeleccionado.getUnArbitro2());
                jComboBoxArbitro3.setSelectedItem(unPartidoSeleccionado.getUnArbitro3());                
               
                jComboBoxEquipoLocal.addItem(unPartidoSeleccionado.getUnEquipoLocal());
                jComboBoxEquipoLocal.setSelectedItem(unPartidoSeleccionado.getUnEquipoLocal());
                jComboBoxEquipoVisitante.addItem(unPartidoSeleccionado.getUnEquipoVisitante());
                jComboBoxEquipoVisitante.setSelectedItem(unPartidoSeleccionado.getUnEquipoVisitante());

                jButtonEditar.setEnabled(true);
                jButtonEliminar.setEnabled(true);
                jButtonImprimir.setEnabled(true);
                jButtonResultadoPartido.setEnabled(true);
            }
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
        this.jComboBoxArbitro1.setSelectedIndex(-1);
        this.jComboBoxArbitro2.setSelectedIndex(-1);
        this.jComboBoxArbitro3.setSelectedIndex(-1);
        this.jComboBoxCancha.setSelectedIndex(-1);
        this.jComboBoxEquipoLocal.setSelectedIndex(-1);
        this.jComboBoxEquipoVisitante.setSelectedIndex(-1);
        this.jDateChooserFecha.setDate(null);
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jDateChooserFecha.getDate() == null) {
            jLabelFechaCalendario.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFechaCalendario.setForeground(Color.black);
        }
        if (jComboBoxEquipoLocal.getSelectedIndex() == -1) {
            jLabelLocal.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelLocal.setForeground(Color.black);
        }
        if (jComboBoxEquipoVisitante.getSelectedIndex() == -1) {
            jLabelVisitante.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelVisitante.setForeground(Color.black);
        }
        if (jComboBoxCancha.getSelectedIndex() == -1) {
            jLabelCancha.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelCancha.setForeground(Color.black);
        }
        if (jComboBoxArbitro1.getSelectedIndex() == -1) {
            jLabelArbitro1.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelArbitro1.setForeground(Color.black);
        }
        if (jComboBoxArbitro2.getSelectedIndex() == -1) {
            jLabelArbitro2.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelArbitro2.setForeground(Color.black);
        }

        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.");
            return bandera;
        }
        if (jComboBoxEquipoLocal.getSelectedItem() == jComboBoxEquipoVisitante.getSelectedItem()) {
            JOptionPane.showMessageDialog(this, "No se puede seleccionar el mismo equipo como Local y Visitante.");
            jLabelLocal.setForeground(Color.red);
            jLabelVisitante.setForeground(Color.red);
            return false;
        } else {
            jLabelLocal.setForeground(Color.black);
            jLabelVisitante.setForeground(Color.black);
        }
        if ((jComboBoxArbitro1.getSelectedItem() == jComboBoxArbitro2.getSelectedItem()) || (jComboBoxArbitro1.getSelectedItem() == jComboBoxArbitro3.getSelectedItem()) || (jComboBoxArbitro2.getSelectedItem() == jComboBoxArbitro3.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "No se puede seleccionar el mismo árbitro mas de una vez.");
            return false;
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonResultadoPartido = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jPanelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelFecha1 = new javax.swing.JLabel();
        jLabelTotalFechas = new javax.swing.JLabel();
        jButtonAgregarFecha = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFechasTorneo = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jComboBoxEquipoLocal = new javax.swing.JComboBox();
        jComboBoxEquipoVisitante = new javax.swing.JComboBox();
        jComboBoxCancha = new javax.swing.JComboBox();
        jComboBoxArbitro1 = new javax.swing.JComboBox();
        jComboBoxArbitro2 = new javax.swing.JComboBox();
        jLabelLocal = new javax.swing.JLabel();
        jLabelVisitante = new javax.swing.JLabel();
        jLabelCancha = new javax.swing.JLabel();
        jLabelArbitro1 = new javax.swing.JLabel();
        jLabelArbitro2 = new javax.swing.JLabel();
        jLabelArbitro3 = new javax.swing.JLabel();
        jComboBoxArbitro3 = new javax.swing.JComboBox();
        jLabelFechaCalendario = new javax.swing.JLabel();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonInicio = new javax.swing.JButton();
        jTextFieldNroFecha = new javax.swing.JTextField();
        jButtonSiguiente = new javax.swing.JButton();
        jButtonAnterior = new javax.swing.JButton();
        jButtonFinal = new javax.swing.JButton();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(858, 527));
        setMinimumSize(new java.awt.Dimension(858, 527));
        setPreferredSize(new java.awt.Dimension(858, 527));
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
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/deletered.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/add2.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo Partido");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setEnabled(false);
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jButtonResultadoPartido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PanillaResultados.png"))); // NOI18N
        jButtonResultadoPartido.setText("Rtdo. Partido");
        jButtonResultadoPartido.setEnabled(false);
        jButtonResultadoPartido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonResultadoPartido.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonResultadoPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResultadoPartidoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo)
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
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResultadoPartido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha n°:");

        jLabelFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelFecha.setText("0");

        jLabelFecha1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelFecha1.setText("de");

        jLabelTotalFechas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTotalFechas.setText("0");

        jButtonAgregarFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Nuevo2.png"))); // NOI18N
        jButtonAgregarFecha.setText("Agregar Fecha");
        jButtonAgregarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTituloLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFecha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTotalFechas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAgregarFecha)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTituloLayout.setVerticalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTituloLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAgregarFecha)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTotalFechas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTableFechasTorneo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Equipo Local", "GL", "GV", "Equipo Visitante", "Cancha", "Árbitro 1", "Árbitro 2", "Árbitro 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFechasTorneo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFechasTorneoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFechasTorneo);
        if (jTableFechasTorneo.getColumnModel().getColumnCount() > 0) {
            jTableFechasTorneo.getColumnModel().getColumn(0).setMinWidth(0);
            jTableFechasTorneo.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableFechasTorneo.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableFechasTorneo.getColumnModel().getColumn(3).setMinWidth(40);
            jTableFechasTorneo.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableFechasTorneo.getColumnModel().getColumn(3).setMaxWidth(40);
            jTableFechasTorneo.getColumnModel().getColumn(4).setMinWidth(40);
            jTableFechasTorneo.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTableFechasTorneo.getColumnModel().getColumn(4).setMaxWidth(40);
        }
        jTableFechasTorneo.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles Partido"));

        jComboBoxEquipoLocal.setEnabled(false);

        jComboBoxEquipoVisitante.setEnabled(false);

        jComboBoxCancha.setEnabled(false);

        jComboBoxArbitro1.setEnabled(false);

        jComboBoxArbitro2.setEnabled(false);

        jLabelLocal.setText("Equipo Local");

        jLabelVisitante.setText("Equipo Visitante");

        jLabelCancha.setText("Cancha");

        jLabelArbitro1.setText("Árbitro 1");

        jLabelArbitro2.setText("Árbitro 2");

        jLabelArbitro3.setText("Árbitro 3");

        jComboBoxArbitro3.setEnabled(false);

        jLabelFechaCalendario.setText("Fecha");

        jDateChooserFecha.setEnabled(false);

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCancha)
                    .addComponent(jLabelLocal)
                    .addComponent(jLabelVisitante)
                    .addComponent(jLabelFechaCalendario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jLabelArbitro2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxArbitro2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jLabelArbitro1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addComponent(jLabelArbitro3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDateChooserFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxCancha, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocal)
                    .addComponent(jComboBoxEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelArbitro1)
                    .addComponent(jComboBoxArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVisitante)
                    .addComponent(jComboBoxEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelArbitro2)
                    .addComponent(jComboBoxArbitro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelArbitro3)
                    .addComponent(jComboBoxArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCancha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaCalendario)
                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jTextFieldNroFecha.setText("0");

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

        javax.swing.GroupLayout jPanelBotones2Layout = new javax.swing.GroupLayout(jPanelBotones2);
        jPanelBotones2.setLayout(jPanelBotones2Layout);
        jPanelBotones2Layout.setHorizontalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotones2Layout.createSequentialGroup()
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
        jPanelBotones2Layout.setVerticalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNroFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBotones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBotones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonResultadoPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResultadoPartidoActionPerformed
        IResultadoPartido unIResultadoPartido = new IResultadoPartido(unaControladoraGlobal, this, unPartidoSeleccionado);
        unIResultadoPartido.pack();
        unIResultadoPartido.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIResultadoPartido);
    }//GEN-LAST:event_jButtonResultadoPartidoActionPerformed

    private void jButtonSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSiguienteMouseClicked
        if (unTorneo.getCantidadFechas() > Integer.parseInt(jTextFieldNroFecha.getText())) {
            jTextFieldNroFecha.setText(String.valueOf(Integer.parseInt(this.jTextFieldNroFecha.getText()) + 1));
            this.unaFechaTorneoSeleccionada = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo);
            cargarTabla();
            camposLimpiar();
            camposActivo(jPanelDetalles, false);
            jLabelFecha.setText(jTextFieldNroFecha.getText());
            cargarCombos();
        }
    }//GEN-LAST:event_jButtonSiguienteMouseClicked

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
        jButtonResultadoPartido.setEnabled(false);
        jButtonAgregarFecha.setEnabled(false);

        jTableFechasTorneo.setEnabled(false);
        jTableFechasTorneo.clearSelection();

        camposActivo(jPanelDetalles, true);
        camposActivo(jPanelBotones2, false);
        camposLimpiar();
        cargarCombos();
        unPartidoSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(true);
        jButtonResultadoPartido.setEnabled(false);
        jButtonAgregarFecha.setEnabled(true);

        jTableFechasTorneo.setEnabled(true);
        jTableFechasTorneo.clearSelection();

        camposActivo(jPanelDetalles, false);
        camposActivo(jPanelBotones2, true);
        camposLimpiar();
        cargarCombos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
        jButtonResultadoPartido.setEnabled(false);
        jButtonAgregarFecha.setEnabled(false);

        jTableFechasTorneo.setEnabled(false);

        camposActivo(jPanelDetalles, true);
        camposActivo(jPanelBotones2, false);
        
        String unEquipoLocal = unPartidoSeleccionado.getUnEquipoLocal().getNombre(), unEquipoVisitante = unPartidoSeleccionado.getUnEquipoVisitante().getNombre();
        cargarCombos();
        jComboBoxEquipoLocal.addItem(unEquipoLocal);
        jComboBoxEquipoLocal.setSelectedItem(unEquipoLocal);
        jComboBoxEquipoLocal.addItem(unEquipoVisitante);        
        jComboBoxEquipoVisitante.addItem(unEquipoLocal);
        jComboBoxEquipoVisitante.addItem(unEquipoVisitante);
        jComboBoxEquipoVisitante.setSelectedItem(unEquipoVisitante);  
        jComboBoxArbitro1.setSelectedItem(unPartidoSeleccionado.getUnArbitro1());
        jComboBoxArbitro2.setSelectedItem(unPartidoSeleccionado.getUnArbitro2());
        jComboBoxArbitro3.setSelectedItem(unPartidoSeleccionado.getUnArbitro3());
        jComboBoxCancha.setSelectedItem(unPartidoSeleccionado.getUnaCancha());
        jDateChooserFecha.setDate(unPartidoSeleccionado.getFecha());
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            Date fecha = new java.sql.Date(jDateChooserFecha.getDate().getTime());
            PersonaAuxiliar arbitro3 = null;
            if (jComboBoxArbitro3.getSelectedIndex() != -1) {
                arbitro3 = (PersonaAuxiliar) jComboBoxArbitro3.getSelectedItem();
            }
            if (unPartidoSeleccionado == null) {
                Equipo unEquipoAuxLocal = null, unEquipoAuxVisitante = null;
                for (Equipo unEquipo : unTorneo.getEquiposInscriptos()) {
                    if (unEquipo.getNombre().equals(jComboBoxEquipoLocal.getSelectedItem())) {
                        unEquipoAuxLocal = unEquipo;
                    } else {
                        if (unEquipo.getNombre().equals(jComboBoxEquipoVisitante.getSelectedItem())) {
                            unEquipoAuxVisitante = unEquipo;
                        }
                    }
                }
                unaControladoraGlobal.crearPartido(unaFechaTorneoSeleccionada, fecha, (Cancha) jComboBoxCancha.getSelectedItem(), unEquipoAuxLocal, unEquipoAuxVisitante, (PersonaAuxiliar) jComboBoxArbitro1.getSelectedItem(), (PersonaAuxiliar) jComboBoxArbitro2.getSelectedItem(), arbitro3);
                JOptionPane.showMessageDialog(this, "Partido Guardado");
            } else {
                Equipo unEquipoAuxLocal = null, unEquipoAuxVisitante = null;
                for (Equipo unEquipo : unTorneo.getEquiposInscriptos()) {
                    if (unEquipo.getNombre().equals(jComboBoxEquipoLocal.getSelectedItem())) {
                        unEquipoAuxLocal = unEquipo;
                    } else {
                        if (unEquipo.getNombre().equals(jComboBoxEquipoVisitante.getSelectedItem())) {
                            unEquipoAuxVisitante = unEquipo;
                        }
                    }
                }
                unaControladoraGlobal.modificarPartido(unPartidoSeleccionado, fecha, (Cancha) jComboBoxCancha.getSelectedItem(), unEquipoAuxLocal, unEquipoAuxVisitante, (PersonaAuxiliar) jComboBoxArbitro1.getSelectedItem(), (PersonaAuxiliar) jComboBoxArbitro2.getSelectedItem(), arbitro3, unPartidoSeleccionado.isBorradoLogico());
                JOptionPane.showMessageDialog(this, "Partido Modificado");
                unPartidoSeleccionado = null;
            }
            cargarTabla();
            jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(true);
            jButtonImprimir.setEnabled(false);
            jButtonResultadoPartido.setEnabled(false);
            jButtonAgregarFecha.setEnabled(true);
            jTableFechasTorneo.setEnabled(true);
            camposActivo(jPanelDetalles, false);
            camposActivo(jPanelBotones2, true);
            camposLimpiar();
            cargarCombos();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnteriorMouseClicked
        if (Integer.parseInt(jTextFieldNroFecha.getText()) > 1) {
            if (unaFechaTorneoSeleccionada.getPartidos().size() < 1 && unaFechaTorneoSeleccionada.getNumeroFecha() == unTorneo.getCantidadFechas()) {
                unaControladoraGlobal.eliminarFechaTorneo(unaFechaTorneoSeleccionada);
                this.jLabelTotalFechas.setText(String.valueOf(this.unTorneo.getCantidadFechas()));
            }
            jTextFieldNroFecha.setText(String.valueOf(Integer.parseInt(this.jTextFieldNroFecha.getText()) - 1));
            this.unaFechaTorneoSeleccionada = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()), unTorneo);
            cargarTabla();
            camposLimpiar();
            cargarCombos();
            camposActivo(jPanelDetalles, false);
            jLabelFecha.setText(jTextFieldNroFecha.getText());
        }
    }//GEN-LAST:event_jButtonAnteriorMouseClicked

    private void jButtonAgregarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarFechaActionPerformed
        unaControladoraGlobal.crearFechaTorneo(unTorneo, unTorneo.getCantidadFechas() + 1);
        if (Integer.parseInt(this.jLabelTotalFechas.getText()) == 0) {
            this.jButtonNuevo.setEnabled(true);
            camposActivo(jPanelBotones2, true);
            jLabelFecha.setText("1");
            jTextFieldNroFecha.setText("1");
        }
        jLabelTotalFechas.setText(String.valueOf(unTorneo.getCantidadFechas()));
        irFinal();
    }//GEN-LAST:event_jButtonAgregarFechaActionPerformed

    private void irFinal() {
        jTextFieldNroFecha.setText(String.valueOf(unTorneo.getCantidadFechas()));
        this.unaFechaTorneoSeleccionada = unaControladoraGlobal.getUnaFecha(unTorneo.getCantidadFechas(), unTorneo);
        cargarTabla();
        camposLimpiar();
        camposActivo(jPanelDetalles, false);
        jLabelFecha.setText(jTextFieldNroFecha.getText());
    }

    private void jButtonFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFinalMouseClicked
        irFinal();
    }//GEN-LAST:event_jButtonFinalMouseClicked

    private void jButtonInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonInicioMouseClicked
        if (unTorneo.getCantidadFechas() > 0) {
            jTextFieldNroFecha.setText("1");
            this.unaFechaTorneoSeleccionada = unaControladoraGlobal.getUnaFecha(1, unTorneo);
            cargarTabla();
            camposLimpiar();
            cargarCombos();
            camposActivo(jPanelDetalles, false);
            jLabelFecha.setText(jTextFieldNroFecha.getText());
        }
    }//GEN-LAST:event_jButtonInicioMouseClicked

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonAgregarFecha.setEnabled(true);

        jTableFechasTorneo.setEnabled(true);

        camposActivo(jPanelDetalles, false);

        int vacio = 0;
        for (Partido unPartido : this.unaFechaTorneoSeleccionada.getPartidos()) {
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
                unaControladoraGlobal.eliminarFechaTorneo(this.unaFechaTorneoSeleccionada);
                unaFechaTorneoSeleccionada = unaControladoraGlobal.getUnaFecha(Integer.parseInt(jTextFieldNroFecha.getText()) - 1, unTorneo);
                this.jLabelTotalFechas.setText(String.valueOf(unTorneo.getCantidadFechas()));
                this.jTextFieldNroFecha.setText(this.jLabelTotalFechas.getText());
                this.jLabelFecha.setText(this.jLabelTotalFechas.getText());
                cargarTabla();
                camposLimpiar();
                cargarCombos();
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
                            unaControladoraGlobal.eliminarPartido(unPartidoSeleccionado);
                            unPartidoSeleccionado = null;
                            cargarTabla();
                            camposLimpiar();
                            cargarCombos();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un partido para eliminar");
                }
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTableFechasTorneoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFechasTorneoMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            IResultadoPartido unIResultadoPartido = new IResultadoPartido(unaControladoraGlobal, this, unPartidoSeleccionado);
            unIResultadoPartido.pack();
            unIResultadoPartido.setVisible(true);
            this.setVisible(false);
            IMenuPrincipalInterface.jDesktopPane.add(unIResultadoPartido);
        }
    }//GEN-LAST:event_jTableFechasTorneoMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        limpiarTabla(modeloTable);
        cargarTabla();
    }//GEN-LAST:event_formComponentShown

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        FixtureDS unFixtureDS = new FixtureDS(unaControladoraGlobal, unTorneo, unaFechaTorneoSeleccionada);
        unFixtureDS.verReporte();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarFecha;
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
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelArbitro1;
    private javax.swing.JLabel jLabelArbitro2;
    private javax.swing.JLabel jLabelArbitro3;
    private javax.swing.JLabel jLabelCancha;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFecha1;
    private javax.swing.JLabel jLabelFechaCalendario;
    private javax.swing.JLabel jLabelLocal;
    private javax.swing.JLabel jLabelTotalFechas;
    private javax.swing.JLabel jLabelVisitante;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelBotones2;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFechasTorneo;
    private javax.swing.JTextField jTextFieldNroFecha;
    // End of variables declaration//GEN-END:variables
}
