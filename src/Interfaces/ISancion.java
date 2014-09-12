package Interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import logicaNegocios.Partido;
import logicaNegocios.PersonaAuxiliar;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class ISancion extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Socia unaSocia = null;
    private PersonaAuxiliar unaPersonaAuxiliar = null;
    private Equipo unEquipo = null;
    private SancionTribunal unaSancionSeleccionada = null;
    private DateFormat df = DateFormat.getDateInstance();
    private DefaultTableModel modeloTableSancion;

    //Llamado Generico
    public ISancion(JInternalFrame unJInternalFrame, ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTableSancion = (DefaultTableModel) jTableSancion.getModel();

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/sanciones.png")));
        IMenuPrincipalInterface.centrar(this);

        camposActivo(jPanelDetalles, false);
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }

    //Llamado desde Gestion SOCIA
    public ISancion(JInternalFrame unJInternalFrame, Socia unaSocia, ControladoraGlobal unaControladoraGlobal) {
        this(unJInternalFrame, unaControladoraGlobal);
        this.unaSocia = unaSocia;
        this.setTitle("Socia: " + unaSocia.getApellido() + ", " + unaSocia.getNombre());
        cargarTabla();
    }

    //Llamado desde Gestion AUXILIARES
    public ISancion(JInternalFrame unJInternalFrame, PersonaAuxiliar unaPersonaAuxiliar, ControladoraGlobal unaControladoraGlobal) {
        this(unJInternalFrame, unaControladoraGlobal);
        this.unaPersonaAuxiliar = unaPersonaAuxiliar;
        this.setTitle("Auxiliar: " + unaPersonaAuxiliar.getApellido() + ", " + unaPersonaAuxiliar.getNombre());
        cargarTabla();
    }

    //Llamado desde Gestion EQUIPO
    public ISancion(JInternalFrame unJInternalFrame, Equipo unEquipo, ControladoraGlobal unaControladoraGlobal) {
        this(unJInternalFrame, unaControladoraGlobal);
        this.unEquipo = unEquipo;
        this.setTitle("Equipo: " + unEquipo.getNombre());
        cargarTabla();
    }

    //deshabilitar todo lo de un contenedor
    private void camposActivo(Container c, boolean bandera) {
        Component[] components = c.getComponents();
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(bandera);
            if (components[i] instanceof JTextField) {
                ((JTextField) components[i]).setEditable(bandera);
            }
            if (components[i] instanceof JTextPane) {
                if (bandera) {
                    components[i].setBackground(Color.WHITE);
                } else {                    
                    components[i].setBackground(new Color(228, 231, 237));
                }
            }
            if (components[i] instanceof Container) {
                camposActivo((Container) components[i], bandera);
            }
        }
    }

    private void camposLimpiar() {
        this.jTextFieldFecha.setText("");
        this.jTextFieldNumResolucion.setText("");
        this.jTextFieldPenalizacion.setText("");
        this.buttonGroup1.clearSelection();
        this.jTextFieldMotivo.setText("");
        this.jTextPaneDetalle.setText("");
        this.jTextFieldPartido.setText("");
        this.jTextFieldTarjeta.setText("");
    }

    private void camposCargar() {
        if (jTableSancion.getSelectedRow() > -1) {
            if (jTableSancion.getValueAt(jTableSancion.getSelectedRow(), 0) != null) {
                unaSancionSeleccionada = unaControladoraGlobal.getSancionTribunalBD((Long) jTableSancion.getValueAt(jTableSancion.getSelectedRow(), 0));
                this.jTextFieldFecha.setText(df.format(unaSancionSeleccionada.getFecha()));
                this.jTextFieldNumResolucion.setText(unaSancionSeleccionada.getNumeroResolucion());
                this.jTextFieldMotivo.setText(unaSancionSeleccionada.getMotivo());
                this.jTextPaneDetalle.setText(unaSancionSeleccionada.getDetalles());
                if (unaSancionSeleccionada.getVencimiento() != null) {
                    this.jTextFieldPenalizacion.setText(df.format(unaSancionSeleccionada.getVencimiento()));
                    this.jRadioButtonHasta.setSelected(true);
                }
                if (unaSancionSeleccionada.getCantFechas() != 0) {
                    this.jTextFieldPenalizacion.setText(Integer.toString(unaSancionSeleccionada.getCantFechas()));
                    this.jRadioButtonCantFechas.setSelected(true);
                }
                this.jTextFieldPartido.setText(unaSancionSeleccionada.getUnPartido().toString());
                this.jTextFieldTarjeta.setText(unaSancionSeleccionada.getUnaTarjeta().getTipo());

                jButtonEliminar.setEnabled(true);
                jButtonEditar.setEnabled(true);
                jButtonImprimir.setEnabled(true);
            }
        }
    }

    private void cargarTabla() {
        limpiarTabla(modeloTableSancion);
        Collection<SancionTribunal> sanciones = null;
        if (unaSocia != null) {
            sanciones = unaSocia.getSancionesTribunal();
        }
        if (unaPersonaAuxiliar != null) {
            sanciones = unaPersonaAuxiliar.getSancionesTribunal();
        }
        if (unEquipo != null) {
            sanciones = unEquipo.getSancionesTribunal();
        }
        for (SancionTribunal unaSancion : sanciones) {
            String penalizacion = "-";
            if (!unaSancion.isBorradoLogico()) {
                if (unaSancion.getCantFechas() != 0) {
                    penalizacion = Integer.toString(unaSancion.getCantFechas());
                }
                if (unaSancion.getVencimiento() != null) {
                    penalizacion = df.format(unaSancion.getVencimiento());
                }
                this.modeloTableSancion.addRow(new Object[]{unaSancion.getIdSancionTribunal(), df.format(unaSancion.getFecha()), unaSancion.getMotivo(), unaSancion.getNumeroResolucion(), penalizacion, unaSancion.getCantFechasCumplidas(), unaSancion.getUnPartido().toString()});
            }
        }
        jButtonEliminar.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonImprimir.setEnabled(false);
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        try {
            int filas = modeloTabla.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTabla.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldFecha.getText().isEmpty()) {
            jLabelFecha.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFecha.setForeground(Color.black);
        }
        if (jTextFieldMotivo.getText().isEmpty()) {
            jLabelMotivo.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMotivo.setForeground(Color.black);
        }
        if (!jRadioButtonCantFechas.isSelected() && !jRadioButtonHasta.isSelected()) {
            bandera = false;
            jPanelPenalizacion.setForeground(Color.red);
        } else {
            jPanelPenalizacion.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
            return bandera;
        }
        if ((!jTextFieldPenalizacion.getText().isEmpty()) && !jRadioButtonCantFechas.isSelected() && !jRadioButtonHasta.isSelected()) {
            JOptionPane.showMessageDialog(this, "Error en la Penalización. Indique si es una cantidad de fechas determinada o si es hasta una fecha calendario.");
            bandera = false;
            jPanelPenalizacion.setForeground(Color.red);
            return bandera;
        } else {
            jPanelPenalizacion.setForeground(Color.black);
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelBotones = new javax.swing.JPanel();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSancion = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jLabelDetalle = new javax.swing.JLabel();
        jTextFieldFecha = new javax.swing.JTextField();
        jLabel1NumResolucion = new javax.swing.JLabel();
        jTextFieldNumResolucion = new javax.swing.JTextField();
        jLabelMotivo = new javax.swing.JLabel();
        jTextFieldMotivo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneDetalle = new javax.swing.JTextPane();
        jPanelPenalizacion = new javax.swing.JPanel();
        jRadioButtonCantFechas = new javax.swing.JRadioButton();
        jRadioButtonHasta = new javax.swing.JRadioButton();
        jTextFieldPenalizacion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldFechasCumplidas = new javax.swing.JTextField();
        jTextFieldPartido = new javax.swing.JTextField();
        jTextFieldTarjeta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 534));
        setMinimumSize(new java.awt.Dimension(650, 534));
        setPreferredSize(new java.awt.Dimension(650, 534));
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
        jButtonImprimir.setEnabled(false);
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableSancion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Sancion", "Fecha", "Motivo", "N° de Resolución", "Penalización", "Fechas Cumplidas", "Partido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableSancion);
        if (jTableSancion.getColumnModel().getColumnCount() > 0) {
            jTableSancion.getColumnModel().getColumn(0).setMinWidth(0);
            jTableSancion.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableSancion.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableSancion.getSelectionModel () .addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelFecha.setText("Fecha");

        jLabelDetalle.setText("Detalle");

        jTextFieldFecha.setEditable(false);

        jLabel1NumResolucion.setText("N° de Resolución");

        jTextFieldNumResolucion.setEditable(false);

        jLabelMotivo.setText("Motivo");

        jTextFieldMotivo.setEditable(false);

        jTextPaneDetalle.setEnabled(false);
        jScrollPane3.setViewportView(jTextPaneDetalle);

        jPanelPenalizacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Penalización", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(jRadioButtonCantFechas);
        jRadioButtonCantFechas.setText("Cant. Fechas");
        jRadioButtonCantFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCantFechasActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonHasta);
        jRadioButtonHasta.setText("Hasta");
        jRadioButtonHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHastaActionPerformed(evt);
            }
        });

        jTextFieldPenalizacion.setEditable(false);

        jLabel1.setText("Fechas Cumplidas");

        jTextFieldFechasCumplidas.setEditable(false);

        javax.swing.GroupLayout jPanelPenalizacionLayout = new javax.swing.GroupLayout(jPanelPenalizacion);
        jPanelPenalizacion.setLayout(jPanelPenalizacionLayout);
        jPanelPenalizacionLayout.setHorizontalGroup(
            jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPenalizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPenalizacionLayout.createSequentialGroup()
                        .addComponent(jRadioButtonCantFechas)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonHasta))
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldFechasCumplidas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPenalizacionLayout.setVerticalGroup(
            jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPenalizacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelPenalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonCantFechas)
                    .addComponent(jRadioButtonHasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFechasCumplidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextFieldPartido.setEditable(false);

        jTextFieldTarjeta.setEditable(false);

        jLabel2.setText("Partido");

        jLabel3.setText("Tarjeta");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDetalle)
                            .addComponent(jLabelMotivo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1NumResolucion)
                            .addComponent(jLabelFecha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNumResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPenalizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(74, 74, 74))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFecha)
                            .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1NumResolucion)
                            .addComponent(jTextFieldNumResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMotivo)
                            .addComponent(jTextFieldMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDetalle)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jPanelPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
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
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (unJInternalFrame != null) {
            this.unJInternalFrame.setVisible(true);
        }
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);

        jTableSancion.setEnabled(false);

        camposActivo(jPanelDetalles, true);
        camposLimpiar();
        unaSancionSeleccionada = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            try {
                Date fecha = new java.sql.Date(df.parse(jTextFieldFecha.getText()).getTime());
                Date fechaCaducidad = null;
                int cantidadFechas = 0;
                if (jRadioButtonHasta.isSelected()) {
                    fechaCaducidad = new java.sql.Date(df.parse(jTextFieldPenalizacion.getText()).getTime());
                }
                if (jRadioButtonCantFechas.isSelected()) {
                    cantidadFechas = Integer.parseInt(jTextFieldPenalizacion.getText());
                }
                if (unaSancionSeleccionada == null) {
                    SancionTribunal unaNuevaSancion = null;
                    if (unaSocia != null) {
                        unaNuevaSancion = unaControladoraGlobal.crearSancionTribunal(null, unaSocia, fecha, jTextFieldMotivo.getText(), jTextPaneDetalle.getText());
                    }
                    if (unaPersonaAuxiliar != null) {
                        unaNuevaSancion = unaControladoraGlobal.crearSancionTribunal(null, unaPersonaAuxiliar, fecha, jTextFieldMotivo.getText(), jTextPaneDetalle.getText());
                    }
                    if (unEquipo != null) {
                        unaNuevaSancion = unaControladoraGlobal.crearSancionTribunal(unEquipo, null, fecha, jTextFieldMotivo.getText(), jTextPaneDetalle.getText());
                    }
                    unaControladoraGlobal.modificarSancionTribunal(unaNuevaSancion, fecha, jTextFieldMotivo.getText(), jTextPaneDetalle.getText(), jTextFieldNumResolucion.getText(), fechaCaducidad, cantidadFechas, unaNuevaSancion.isBorradoLogico());
                    JOptionPane.showMessageDialog(this, "Sanción Guardada");
                } else {
                    unaControladoraGlobal.modificarSancionTribunal(unaSancionSeleccionada, fecha, jTextFieldMotivo.getText(), jTextPaneDetalle.getText(), jTextFieldNumResolucion.getText(), fechaCaducidad, cantidadFechas, unaSancionSeleccionada.isBorradoLogico());
                    JOptionPane.showMessageDialog(this, "Sancion Modificada");
                    unaSancionSeleccionada = null;
                }
                //Cartel donde se indica que la socia estaba inscripta a partidos que la involucran y se tiene que actualizar la tabla del partido para impactar la penalizacion
                if (unaSocia != null) {
                    String partidos = "", enter = System.getProperty("line.separator");
                    for (Partido aux : unaControladoraGlobal.getPartidosConPlantelNoJugadosBD(fecha)) {
                        if (aux.isJugadoraInscripta(unaSocia)) {
                            partidos += (aux.toString() + enter);
                        }
                    }
                    if (!partidos.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "La Socia " + unaSocia.getApellido() + ", " + unaSocia.getNombre() + "tiene uno o más partidos por jugar." + enter + "Es MUY NECESARIO actualizar las planillas de los siguientes partidos para que se refleje la Penalización: " + enter + partidos);
                    }
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
            }

            jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);
            jButtonImprimir.setEnabled(false);

            jTableSancion.setEnabled(true);

            camposActivo(jPanelDetalles, false);
            camposLimpiar();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonNuevo.setEnabled(false);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);

        jTableSancion.setEnabled(false);

        camposActivo(jPanelDetalles, true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);

        jTableSancion.setEnabled(true);

        camposActivo(jPanelDetalles, false);
        camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonImprimir.setEnabled(false);

        jTableSancion.setEnabled(true);

        camposActivo(jPanelDetalles, false);

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar la Sancion?",
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarSancionTribunal(unaSancionSeleccionada);
            unaSancionSeleccionada = null;
            cargarTabla();
        }
        jTableSancion.clearSelection();
        camposLimpiar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jRadioButtonCantFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCantFechasActionPerformed
        jTextFieldPenalizacion.setEditable(true);
    }//GEN-LAST:event_jRadioButtonCantFechasActionPerformed

    private void jRadioButtonHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHastaActionPerformed
        jTextFieldPenalizacion.setEditable(true);
    }//GEN-LAST:event_jRadioButtonHastaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1NumResolucion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDetalle;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelMotivo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelPenalizacion;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JRadioButton jRadioButtonCantFechas;
    private javax.swing.JRadioButton jRadioButtonHasta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableSancion;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldFechasCumplidas;
    private javax.swing.JTextField jTextFieldMotivo;
    private javax.swing.JTextField jTextFieldNumResolucion;
    private javax.swing.JTextField jTextFieldPartido;
    private javax.swing.JTextField jTextFieldPenalizacion;
    private javax.swing.JTextField jTextFieldTarjeta;
    private javax.swing.JTextPane jTextPaneDetalle;
    // End of variables declaration//GEN-END:variables
}
