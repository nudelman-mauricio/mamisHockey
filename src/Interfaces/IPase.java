package Interfaces;

import DataSources.FormularioPaseDS;
import java.awt.Color;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import logicaNegocios.Pase;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IPase extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private ControladoraGlobal unaControladoraGlobal;
    private Socia unaSocia;
    private Pase unPaseSeleccionado = null;
    private DefaultTableModel modeloTablePases;
    private DateFormat df = DateFormat.getDateInstance();
    private Calendar FechaSO = Calendar.getInstance();

    //LLAMADO A TRAVES DE UNA SOCIA (unico)
    public IPase(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Socia unaSocia) {
        initComponents();
        this.unJInternalFrame = unJInternalFrame;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaSocia = unaSocia;
        this.modeloTablePases = (DefaultTableModel) jTablePases.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Transferencia.png"))); //Icono de la ventana                
        this.setTitle("Pases de: " + unaSocia.toString()); //Titulo Ventana
        IMenuPrincipalInterface.centrar(this); //Centrar
        this.jComboBoxEquipoDestino.setModel(new DefaultComboBoxModel((Vector) unaControladoraGlobal.getEquiposBD()));
        this.jComboBoxEquipoDestino.setSelectedIndex(-1);
        this.jComboBoxCuota.setSelectedIndex(-1);
        cargarTabla();
    }

    public void camposActivo(boolean Editable) {
        jTextFieldFechaRealizacion.setEditable(Editable);
        jTextFieldEquipoOrigen.setEditable(Editable);
        jComboBoxEquipoDestino.setEnabled(Editable);
        jTextFieldCamiseta.setEditable(Editable);
        jTextFieldMonto.setEditable(Editable);
        jComboBoxCuota.setEnabled(Editable);
        jTextFieldFechaVencimiento.setEditable(Editable);
        jTextPaneDetalle.setEditable(Editable);
        jCheckBoxLibreDeudaClub.setEnabled(Editable);
        jCheckBoxSolicitudPase.setEnabled(Editable);
        if (Editable) {
            jTextPaneDetalle.setBackground(Color.WHITE);
        } else {
            jTextPaneDetalle.setBackground(new Color(228, 231, 237));
        }
    }

    public void camposLimpiar() {
        jTextFieldFechaRealizacion.setText("");
        jTextFieldEquipoOrigen.setText("");
        jComboBoxEquipoDestino.setSelectedIndex(-1);
        jTextFieldCamiseta.setText("");
        jTextFieldMonto.setText("");
        jComboBoxCuota.setSelectedIndex(-1);
        jTextFieldFechaVencimiento.setText("");
        jTextPaneDetalle.setText("");
        jCheckBoxLibreDeudaClub.setSelected(false);
        jCheckBoxSolicitudPase.setSelected(false);
    }

    public void camposCargar() {
        if (jTablePases.getSelectedRow() > -1) {
            if (jTablePases.getValueAt(jTablePases.getSelectedRow(), 0) != null) {
                unPaseSeleccionado = unaControladoraGlobal.getPaseBD((Long) jTablePases.getValueAt(jTablePases.getSelectedRow(), 0));
                jLabelNumeroPase.setText(Integer.toString((int) jTablePases.getValueAt(jTablePases.getSelectedRow(), 1)));
                jTextFieldFechaRealizacion.setText(df.format(unPaseSeleccionado.getFecha()));
                if (jTablePases.getSelectedRow() > 0) {
                    jTextFieldEquipoOrigen.setText(jTablePases.getValueAt(jTablePases.getSelectedRow() - 1, 3).toString());
                } else {
                    jTextFieldEquipoOrigen.setText("----");
                }
                jComboBoxEquipoDestino.setSelectedItem(unPaseSeleccionado.getUnEquipo());
                jTextFieldMonto.setText(Double.toString(unPaseSeleccionado.getUnaDeuda().getMontoTotal()));
                jComboBoxCuota.setSelectedIndex(unPaseSeleccionado.getUnaDeuda().getCantidadCuotas() - 1);
                jTextFieldFechaVencimiento.setText(df.format(unPaseSeleccionado.getUnaDeuda().getPrimerVencimiento()));
                jTextPaneDetalle.setText(unPaseSeleccionado.getObservacion());
                jCheckBoxLibreDeudaClub.setSelected(unPaseSeleccionado.isLibreDeudaClub());
                jCheckBoxSolicitudPase.setSelected(unPaseSeleccionado.isSolicitudPase());

                jButtonEliminar.setEnabled(true);               
            }
        }
    }

    //Cargar Tabla con los pases de la Socia
    public void cargarTabla() {
        int nPase = 0;
        limpiarTabla();
        for (Pase unPase : unaSocia.getPasesValidos()) {
            this.modeloTablePases.addRow(new Object[]{unPase.getIdPase(), nPase, df.format(unPase.getFecha()), unPase.getUnEquipo(), "$ " + unPase.getUnaDeuda().getMontoTotal()});
            nPase++;
        }
    }

    private void limpiarTabla() {
        int filas = modeloTablePases.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablePases.removeRow(0);
        }
    }

    public boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldFechaRealizacion.getText().isEmpty()) {
            jLabelMonto.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMonto.setForeground(Color.black);
        }
        if (jComboBoxEquipoDestino.getSelectedIndex() == -1) {
            jLabelDestino.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelDestino.setForeground(Color.black);
        }
        if (jTextFieldCamiseta.getText().isEmpty()) {
            jLabelCamiseta.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelCamiseta.setForeground(Color.black);
        }
        if (jTextFieldMonto.getText().isEmpty()) {
            jLabelMonto.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelMonto.setForeground(Color.black);
        }
        if (jTextFieldFechaVencimiento.getText().isEmpty()) {
            jLabelFechaVencimiento.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelFechaVencimiento.setForeground(Color.black);
        }
        if (!jCheckBoxLibreDeudaClub.isSelected() && !jCheckBoxSolicitudPase.isSelected()) {
            jCheckBoxLibreDeudaClub.setForeground(Color.red);
            jCheckBoxSolicitudPase.setForeground(Color.red);
            bandera = false;
            JOptionPane.showMessageDialog(this, "Es necesario que la socia entregue al menos un documento de los solicitdos: Libre Deuda o Solicitud Pase");
            return bandera;
        } else {
            jCheckBoxLibreDeudaClub.setForeground(Color.black);
            jCheckBoxSolicitudPase.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
            return bandera;
        }
        if (jTextFieldEquipoOrigen.getText().equals(jComboBoxEquipoDestino.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "No se puede generar un pase entre equipos iguales");
            jLabelDestino.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelDestino.setForeground(Color.black);
        }
        return bandera;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePases = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelPaseNumero = new javax.swing.JLabel();
        jLabelNumeroPase = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelMonto = new javax.swing.JLabel();
        jTextFieldMonto = new javax.swing.JTextField();
        jLabelFechaRealizacion5 = new javax.swing.JLabel();
        jComboBoxCuota = new javax.swing.JComboBox();
        jLabelFechaVencimiento = new javax.swing.JLabel();
        jTextFieldFechaVencimiento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelFechaRealizacion = new javax.swing.JLabel();
        jTextFieldFechaRealizacion = new javax.swing.JTextField();
        jLabelOrigen = new javax.swing.JLabel();
        jTextFieldEquipoOrigen = new javax.swing.JTextField();
        jLabelDestino = new javax.swing.JLabel();
        jComboBoxEquipoDestino = new javax.swing.JComboBox();
        jTextFieldCamiseta = new javax.swing.JTextField();
        jLabelCamiseta = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelDestino1 = new javax.swing.JLabel();
        jLabelDestino2 = new javax.swing.JLabel();
        jCheckBoxLibreDeudaClub = new javax.swing.JCheckBox();
        jCheckBoxSolicitudPase = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneDetalle = new javax.swing.JTextPane();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(650, 664));
        setMinimumSize(new java.awt.Dimension(650, 664));
        setPreferredSize(new java.awt.Dimension(650, 664));
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
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNuevo)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonGuardar))
                .addGap(3, 3, 3))
        );

        jTablePases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idPase", "N° Pase", "Fecha", "Equipo Destino", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePases);
        if (jTablePases.getColumnModel().getColumnCount() > 0) {
            jTablePases.getColumnModel().getColumn(0).setMinWidth(0);
            jTablePases.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTablePases.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTablePases.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelPaseNumero.setText("Pase Numero:");

        jLabelNumeroPase.setText("nn");

        jLabel3.setText("* En caso de seleccionar más de una cuota, los vencimientos serán el mismo día, uno por mes.");

        jLabelMonto.setText("Monto");

        jTextFieldMonto.setEditable(false);

        jLabelFechaRealizacion5.setText("Cuotas");

        jComboBoxCuota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBoxCuota.setEnabled(false);

        jLabelFechaVencimiento.setText("Fecha 1° Vto");

        jTextFieldFechaVencimiento.setEditable(false);

        jLabel2.setText("*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaRealizacion5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMonto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFechaVencimiento, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldFechaVencimiento)
                    .addComponent(jComboBoxCuota, 0, 168, Short.MAX_VALUE)
                    .addComponent(jTextFieldMonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaRealizacion5)
                    .addComponent(jComboBoxCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaVencimiento)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jLabelFechaRealizacion.setText("Fecha de Realización");

        jTextFieldFechaRealizacion.setEditable(false);

        jLabelOrigen.setText("Equipo Origen");

        jTextFieldEquipoOrigen.setEditable(false);

        jLabelDestino.setText("Equipo Destino");

        jComboBoxEquipoDestino.setEnabled(false);

        jTextFieldCamiseta.setEditable(false);

        jLabelCamiseta.setText("Nº Camiseta");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelOrigen)
                    .addComponent(jLabelFechaRealizacion)
                    .addComponent(jLabelDestino)
                    .addComponent(jLabelCamiseta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldEquipoOrigen)
                    .addComponent(jComboBoxEquipoDestino, 0, 168, Short.MAX_VALUE)
                    .addComponent(jTextFieldFechaRealizacion)
                    .addComponent(jTextFieldCamiseta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaRealizacion)
                    .addComponent(jTextFieldFechaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrigen)
                    .addComponent(jTextFieldEquipoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEquipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCamiseta)
                    .addComponent(jTextFieldCamiseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabelDestino1.setText("Observación");

        jLabelDestino2.setText("Entrego:");

        jCheckBoxLibreDeudaClub.setText("Libre deuda del Club");
        jCheckBoxLibreDeudaClub.setEnabled(false);

        jCheckBoxSolicitudPase.setText("Solicitud de Pase");
        jCheckBoxSolicitudPase.setEnabled(false);

        jTextPaneDetalle.setEditable(false);
        jScrollPane3.setViewportView(jTextPaneDetalle);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDestino2)
                            .addComponent(jLabelDestino1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxLibreDeudaClub)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxSolicitudPase)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabelDestino1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDestino2)
                    .addComponent(jCheckBoxLibreDeudaClub)
                    .addComponent(jCheckBoxSolicitudPase))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelPaseNumero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNumeroPase))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                                .addGap(306, 306, 306)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNumeroPase)
                            .addComponent(jLabelPaseNumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        //Cargar campos
        camposLimpiar();
        jLabelNumeroPase.setText(Integer.toString(unaSocia.getPasesValidos().size()));
        int nPase = unaSocia.getPasesValidos().size();
        Double montoPaseCero = unaControladoraGlobal.getConceptoDeportivoBD("Pase").getMonto();
        if (nPase == 1) {
            jTextFieldMonto.setText(String.valueOf(montoPaseCero));
        } else {
            jTextFieldMonto.setText(String.valueOf(nPase * (montoPaseCero + (montoPaseCero * 0.25))));
        }
        jTextFieldFechaRealizacion.setText(df.format(FechaSO.getTime()));
        jTextFieldFechaVencimiento.setText(df.format(FechaSO.getTime()));
        if (unaSocia.getEquipoActual() != null) {
            jTextFieldEquipoOrigen.setText(unaSocia.getEquipoActual().getNombre());
        }
        jComboBoxCuota.setSelectedIndex(0);

        //Comportamiento Campos
        jTablePases.clearSelection();
        jTablePases.setEnabled(false);
        camposActivo(true);
        unPaseSeleccionado = null;

        //Comportamiento Botones
        jButtonNuevo.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEliminar.setEnabled(false);        
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (camposValidar()) {
            try {
                Date fechaRealizacion = new java.sql.Date(df.parse(jTextFieldFechaRealizacion.getText()).getTime());
                Date fechaVencimiento = new java.sql.Date(df.parse(jTextFieldFechaVencimiento.getText()).getTime());
                Pase unPase = unaControladoraGlobal.crearPase(unaSocia, fechaRealizacion, Double.parseDouble(jTextFieldMonto.getText()), Integer.valueOf(jComboBoxCuota.getSelectedItem().toString()), fechaVencimiento, (Equipo) jComboBoxEquipoDestino.getSelectedItem(), jCheckBoxLibreDeudaClub.isSelected(), jCheckBoxSolicitudPase.isSelected(), jTextPaneDetalle.getText());
                unaControladoraGlobal.modificarNumeroCamiseta(unaSocia, jTextFieldCamiseta.getText());
                JOptionPane.showMessageDialog(this, "Pase Guardado y Deuda Generada");

                //Comportamientos Extras
                jButtonNuevo.setEnabled(true);
                jButtonGuardar.setEnabled(false);
                jButtonCancelar.setEnabled(false);
                jButtonEliminar.setEnabled(false);                

                camposLimpiar();
                camposActivo(false);
                jTablePases.setEnabled(true);
                cargarTabla();
                
                //Formulario de Pase
                FormularioPaseDS unFormularioPase = new FormularioPaseDS(unaControladoraGlobal, unaSocia, unPase);
                unFormularioPase.verReporte();
                
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "La fecha tiene un formato erróneo. Lo correcto es dd/mm/aaaa");
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jTablePases.clearSelection();
        jTablePases.setEnabled(true);
        camposLimpiar();
        camposActivo(false);
        jButtonNuevo.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonEliminar.setEnabled(false);       
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        //Comprueba que la fila a eliminar sea la última si o si. Porque solo se permite eliminar el último pase realizado
        //Comprueba ademas que no se elimine el pase cero
        if ((jTablePases.isRowSelected(jTablePases.getRowCount() - 1)) && (jTablePases.getRowCount() > 1)) {
            Object[] options = {"OK", "Cancelar"};
            if (0 == JOptionPane.showOptionDialog(
                    this,
                    "Desea eliminar el pase al Equipo: " + unPaseSeleccionado.getUnEquipo().getNombre(),
                    "Eliminar",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options)) {
                unaControladoraGlobal.eliminarUltimoPase(unPaseSeleccionado, this.unaSocia);
                unPaseSeleccionado = null;
                cargarTabla();
                jTablePases.clearSelection();
                camposLimpiar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Únicamente se permite eliminar el último pase. Por favor seleccione el último pase.", "Seleccion Incorrecta", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxLibreDeudaClub;
    private javax.swing.JCheckBox jCheckBoxSolicitudPase;
    private javax.swing.JComboBox jComboBoxCuota;
    private javax.swing.JComboBox jComboBoxEquipoDestino;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCamiseta;
    private javax.swing.JLabel jLabelDestino;
    private javax.swing.JLabel jLabelDestino1;
    private javax.swing.JLabel jLabelDestino2;
    private javax.swing.JLabel jLabelFechaRealizacion;
    private javax.swing.JLabel jLabelFechaRealizacion5;
    private javax.swing.JLabel jLabelFechaVencimiento;
    private javax.swing.JLabel jLabelMonto;
    private javax.swing.JLabel jLabelNumeroPase;
    private javax.swing.JLabel jLabelOrigen;
    private javax.swing.JLabel jLabelPaseNumero;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTablePases;
    private javax.swing.JTextField jTextFieldCamiseta;
    private javax.swing.JTextField jTextFieldEquipoOrigen;
    private javax.swing.JTextField jTextFieldFechaRealizacion;
    private javax.swing.JTextField jTextFieldFechaVencimiento;
    private javax.swing.JTextField jTextFieldMonto;
    private javax.swing.JTextPane jTextPaneDetalle;
    // End of variables declaration//GEN-END:variables
}
