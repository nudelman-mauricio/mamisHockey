package Interfaces;

import java.awt.Color;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Gol;
import logicaNegocios.Partido;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import main.ControladoraGlobal;
import main.TableCellRendererColor;

public class IResultadoPartido extends javax.swing.JInternalFrame {

    ControladoraGlobal unaControladoraGlobal;
    JInternalFrame unJInternalFrame;
    Partido unPartido;

    private DefaultTableModel modeloTableLocal;
    private DefaultTableModel modeloTableGolLocal;
    private DefaultTableModel modeloTableVisitante;
    private DefaultTableModel modeloTableGolVisitante;

    public IResultadoPartido(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Partido unPartido) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unPartido = unPartido;

        this.jTableLocal.setDefaultRenderer(Object.class, new TableCellRendererColor());
        this.jTableVisitante.setDefaultRenderer(Object.class, new TableCellRendererColor());

        this.modeloTableLocal = (DefaultTableModel) jTableLocal.getModel();
        this.modeloTableGolLocal = (DefaultTableModel) jTableGolLocal.getModel();
        this.modeloTableVisitante = (DefaultTableModel) jTableVisitante.getModel();
        this.modeloTableGolVisitante = (DefaultTableModel) jTableGolVisitante.getModel();

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/PanillaResultados.png")));
        this.setTitle(unPartido.getUnEquipoLocal().getNombre() + " vs " + unPartido.getUnEquipoVisitante().getNombre());
        IMenuPrincipalInterface.centrar(this);

        cargarCampos();
        camposActivo(false);

        //Botones
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        //El Partido anterior fue Jugado
        if (unaControladoraGlobal.isPartidoAnteriorJugado(unPartido)) {
            jButtonImprimir.setEnabled(true);
        } else {
            jButtonImprimir.setEnabled(false);
        }
        //El partido fue jugado y 
        if (isPartidoFueJugado()) {
            jButtonEditar.setEnabled(true);
            jButtonActualizar.setEnabled(true);
        } else {
            jButtonEditar.setEnabled(false);
            jButtonActualizar.setEnabled(false);
        }

    }

    public void cargarCampos() {
        // <editor-fold defaultstate="collapsed" desc="Encabezado de la Ventana">
        jLabelTitulo.setText(unPartido.getUnEquipoLocal().getNombre() + " vs " + unPartido.getUnEquipoVisitante().getNombre());
        //Resultados
        if (unPartido.getNombreVeedor() == null) { //El partido se jugo
            jLabelResultado.setText(unaControladoraGlobal.getGolesLocal(unPartido) + " a " + unaControladoraGlobal.getGolesVisitante(unPartido));
        } else {
            jLabelResultado.setText("- a -");
        }
        DateFormat df = DateFormat.getDateInstance();
        jLabelFechaPartido.setText(df.format(unPartido.getFecha()));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Datos Generales">
        //Arbitro 1
        jTextFieldArbitro1.setText(unPartido.getUnArbitro1().getApellido() + ", " + unPartido.getUnArbitro1().getNombre());
        //Arbitro 2
        jTextFieldArbitro2.setText(unPartido.getUnArbitro2().getApellido() + ", " + unPartido.getUnArbitro2().getNombre());
        //Arbitro 3
        if (unPartido.getUnArbitro3() != null) {
            jTextFieldArbitro3.setText(unPartido.getUnArbitro3().getApellido() + ", " + unPartido.getUnArbitro3().getNombre());
        }
        //Cancha
        jTextFieldCancha.setText(unPartido.getUnaCancha().getNombre());
        //Veedor
        jTextFieldVeedor.setText(unPartido.getNombreVeedor());
        jTextPaneObservacion.setText(unPartido.getObservaciones());
        // </editor-fold> 

        cargarInformacionLocal();

        cargarInformacionVisitante();

    }

    public void cargarInformacionLocal() {
        jLabelEquipoLocal.setText(unPartido.getUnEquipoLocal().getNombre());

        // <editor-fold defaultstate="collapsed" desc="Informacion Cuerpos Tecnicos Local">
        //DT
        if (unPartido.getUnDTLocal() == null) {
            jTextFieldDTLocal.setText(unPartido.getUnEquipoLocal().getUnDT().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnDT().getNombre());
        } else {
            jTextFieldDTLocal.setText(unPartido.getUnDTLocal().getApellido() + ", " + unPartido.getUnDTLocal().getNombre());
        }
        //AC
        if (unPartido.getUnAyudanteCampoLocal() == null) {
            if (unPartido.getUnEquipoLocal().getUnAyudanteCampo() != null) {
                jTextFieldAyudanteCampoLocal.setText(unPartido.getUnEquipoLocal().getUnAyudanteCampo().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnAyudanteCampo().getNombre());
            } else {
                jTextFieldAyudanteCampoLocal.setText("-");
            }
        } else {
            jTextFieldAyudanteCampoLocal.setText(unPartido.getUnAyudanteCampoLocal().getApellido() + ", " + unPartido.getUnAyudanteCampoLocal().getNombre());
        }
        //PF
        if (unPartido.getUnPreparadorFisicoLocal() == null) {
            if (unPartido.getUnEquipoLocal().getUnPreparadorFisico() != null) {
                jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnEquipoLocal().getUnPreparadorFisico().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnPreparadorFisico().getNombre());
            } else {
                jTextFieldPreparadorFisicoLocal.setText("-");
            }
        } else {
            jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnPreparadorFisicoLocal().getApellido() + ", " + unPartido.getUnPreparadorFisicoLocal().getNombre());
        }
        jTextFieldAyudanteDeMesaLocal.setText(unPartido.getNombreAyudanteMesaLocal());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Cargar Tabla Local">
        limpiarTabla(modeloTableLocal);
        if (unPartido.getUnPlantelLocal() != null) {
            if (unPartido.getUnPlantelLocal().isEmpty()) {
                for (Socia unaSocia : unaControladoraGlobal.getJugadorasHabilitadas(unPartido.getUnEquipoLocal(), unPartido.getFecha())) {
                    cargarCamposTablaControlando(unaSocia, modeloTableLocal);
                }
            } else {
                for (Socia unaSocia : unPartido.getUnPlantelLocal()) {
                    cargarCamposTablaControlando(unaSocia, modeloTableLocal);
                }
            }
        }
        // </editor-fold>

        //Cargar Goles Visitante
        cargarGoles(modeloTableGolLocal, unPartido.getUnPlantelLocal());
    }

    public void cargarInformacionVisitante() {
        jLabelEquipoVisitante.setText(unPartido.getUnEquipoVisitante().getNombre());

        // <editor-fold defaultstate="collapsed" desc="Informacion Cuerpos Tecnicos Visitante">
        //DT
        if (unPartido.getUnDTVisitante() == null) {
            jTextFieldDTVisitante.setText(unPartido.getUnEquipoVisitante().getUnDT().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnDT().getNombre());
        } else {
            jTextFieldDTVisitante.setText(unPartido.getUnDTVisitante().getApellido() + ", " + unPartido.getUnDTVisitante().getNombre());
        }
        //AC
        if (unPartido.getUnAyudanteCampoVisitante() == null) {
            if (unPartido.getUnEquipoVisitante().getUnAyudanteCampo() != null) {
                jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnEquipoVisitante().getUnAyudanteCampo().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnAyudanteCampo().getNombre());

            } else {
                jTextFieldAyudanteCampoVisitante.setText("-");
            }
        } else {
            jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnAyudanteCampoVisitante().getApellido() + ", " + unPartido.getUnAyudanteCampoVisitante().getNombre());
        }
        //PF
        if (unPartido.getUnPreparadorFisicoVisitante() == null) {
            if (unPartido.getUnEquipoVisitante().getUnPreparadorFisico() != null) {
                jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnEquipoVisitante().getUnPreparadorFisico().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnPreparadorFisico().getNombre());
            } else {
                jTextFieldPreparadorFisicoVisitante.setText("-");
            }
        } else {
            jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnPreparadorFisicoVisitante().getApellido() + ", " + unPartido.getUnPreparadorFisicoVisitante().getNombre());
        }
        jTextFieldAyudanteDeMesaVisitante.setText(unPartido.getNombreAyudanteMesaVisitante());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Cargar Tabla Visitante">
        limpiarTabla(modeloTableVisitante);
        if (unPartido.getUnPlantelVisitante() != null) {
            if (unPartido.getUnPlantelVisitante().isEmpty()) {
                for (Socia unaSocia : unaControladoraGlobal.getJugadorasHabilitadas(unPartido.getUnEquipoVisitante(), unPartido.getFecha())) {
                    cargarCamposTablaControlando(unaSocia, modeloTableVisitante);
                }
            } else {
                for (Socia unaSocia : unPartido.getUnPlantelVisitante()) {
                    cargarCamposTablaControlando(unaSocia, modeloTableVisitante);
                }
            }
        }
        // </editor-fold>

        //Cargar Goles Visitante
        cargarGoles(modeloTableGolVisitante, unPartido.getUnPlantelVisitante());
    }

    public void camposActivo(boolean Editable) {
        jTextFieldVeedor.setEditable(Editable);
        jTextFieldAyudanteDeMesaLocal.setEditable(Editable);
        jTextFieldAyudanteDeMesaVisitante.setEditable(Editable);
        jTextPaneObservacion.setEditable(Editable);

        jTableLocal.setEnabled(Editable);
        jTableGolLocal.setEnabled(Editable);
        jTableVisitante.setEnabled(Editable);
        jTableGolVisitante.setEnabled(Editable);

        if (Editable) {
            jTextPaneObservacion.setBackground(Color.WHITE);
        } else {
            jTextPaneObservacion.setBackground(new Color(228, 231, 237));
        }
    }

    public void cargarCamposTablaControlando(Socia unaSocia, DefaultTableModel modeloTable) {
        Tarjeta v1 = null, v2 = null, v3 = null, a1 = null, a2 = null, ra = null, rd = null;
        for (Tarjeta unTarjeta : unPartido.getTarjetas()) {
            for (Tarjeta unTarjetaSocia : unaSocia.getTarjetas()) {
                if ((unTarjeta == unTarjetaSocia) && (!unTarjeta.isBorradoLogico())) {
                    if ("Verde".equals(unTarjeta.getTipo())) {
                        if (v1 == null) {
                            v1 = unTarjeta;
                        } else {
                            if (v2 == null) {
                                v2 = unTarjeta;
                            } else {
                                if (v3 == null) {
                                    v3 = unTarjeta;
                                }
                            }
                        }
                    }
                    if ("Amarilla".equals(unTarjeta.getTipo())) {
                        if (a1 == null) {
                            a1 = unTarjeta;
                        } else {
                            a2 = unTarjeta;
                        }
                    }
                    if ("Roja".equals(unTarjeta.getTipo())) {
                        rd = unTarjeta;
                    }
                }
                if ((a1 == null) && (a2 == null)) {
                    ra = rd;
                    rd = null;
                }
            }
        }
        modeloTable.addRow(new Object[]{unaSocia.getDni(), unaSocia.getNumeroCamiseta(), unaSocia.toString(), v1, v2, v3, a1, a2, ra, rd, unaControladoraGlobal.getGolesSocia(unPartido, unaSocia)});
    }

    public void cargarGoles(DefaultTableModel modeloTable, Collection<Socia> unPlantel) {
        limpiarTabla(modeloTable);
        for (Gol unGol : unPartido.getGoles()) {
            System.out.println(unGol);
            if (unPlantel != null) {
                System.out.println("el plantel no es null");
            }
            for (Socia unaSocia : unPlantel) {
                System.out.println(unaSocia);
                if ((unaSocia.getGoles().contains(unGol)) && (!unGol.isBorradoLogico())) {
                    modeloTable.addRow(new Object[]{unaSocia.getApellido(), unGol});
                }
            }
        }
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldVeedor.getText().isEmpty()) {
            jLabelVeedor.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelVeedor.setForeground(Color.black);
        }
        if (jTextFieldAyudanteCampoLocal.getText().isEmpty()) {
            jLabelAML.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelAML.setForeground(Color.black);
        }
        if (jTextFieldAyudanteCampoVisitante.getText().isEmpty()) {
            jLabelAMV.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelAMV.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
        }
        return bandera;
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

    private boolean isPartidoFueJugado() {
        boolean resultado = false;
        if ((unPartido.getUnPlantelLocal() != null) && (unPartido.getUnPlantelVisitante() != null) && (unaControladoraGlobal.fechaSistema().after(unPartido.getFecha()))) {
            resultado = true;
        }
        return resultado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelResultado = new javax.swing.JLabel();
        jLabelFechaPartido = new javax.swing.JLabel();
        jLabelFechaPartido1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelDetalles = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldArbitro1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldArbitro2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldArbitro3 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldCancha = new javax.swing.JTextField();
        jTextFieldVeedor = new javax.swing.JTextField();
        jLabelVeedor = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelObservacion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneObservacion = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldDTLocal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldAyudanteCampoLocal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldPreparadorFisicoLocal = new javax.swing.JTextField();
        jTextFieldAyudanteDeMesaLocal = new javax.swing.JTextField();
        jLabelAML = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldDTVisitante = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldAyudanteCampoVisitante = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldPreparadorFisicoVisitante = new javax.swing.JTextField();
        jTextFieldAyudanteDeMesaVisitante = new javax.swing.JTextField();
        jLabelAMV = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLocal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelEquipoLocal = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableGolLocal = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVisitante = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabelEquipoVisitante = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableGolVisitante = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

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
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
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

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/cancel.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Equipo.png"))); // NOI18N
        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonActualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEditar)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonActualizar))
                .addGap(3, 3, 3))
        );

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Equipo Local vs Equipo Visitante");

        jLabelResultado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResultado.setText("4 a 2");

        jLabelFechaPartido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFechaPartido.setText("dd/mm/aaaa");

        jLabelFechaPartido1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFechaPartido1.setText("Fecha del Partido: ");

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTituloLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelFechaPartido1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFechaPartido)))
                .addContainerGap())
        );
        jPanelTituloLayout.setVerticalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTituloLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTituloLayout.createSequentialGroup()
                        .addComponent(jLabelFechaPartido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelResultado))
                    .addComponent(jLabelFechaPartido1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("Arbitro 1");

        jTextFieldArbitro1.setEditable(false);

        jLabel14.setText("Arbitro 2");

        jTextFieldArbitro2.setEditable(false);

        jLabel13.setText("Arbitro 3");

        jTextFieldArbitro3.setEditable(false);

        jLabel16.setText("Chancha");

        jTextFieldCancha.setEditable(false);

        jLabelVeedor.setText("Veedor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextFieldArbitro3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldArbitro2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldArbitro1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabelVeedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCancha)
                    .addComponent(jTextFieldVeedor, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextFieldArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextFieldArbitro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldVeedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelVeedor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabelObservacion.setText("Observación");

        jScrollPane2.setViewportView(jTextPaneObservacion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelObservacion)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelObservacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipo Local"));

        jLabel7.setText("Director Tecnico");

        jTextFieldDTLocal.setEditable(false);

        jLabel17.setText("Ayudante de Campo");

        jTextFieldAyudanteCampoLocal.setEditable(false);

        jLabel18.setText("Preparador Físico");

        jTextFieldPreparadorFisicoLocal.setEditable(false);

        jLabelAML.setText("Ayudante de Mesa Local");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDTLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAyudanteCampoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabelAML)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldAyudanteDeMesaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldPreparadorFisicoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDTLocal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAyudanteCampoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldPreparadorFisicoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAML)
                    .addComponent(jTextFieldAyudanteDeMesaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipo Visitante"));

        jLabel20.setText("Director Tecnico");

        jTextFieldDTVisitante.setEditable(false);

        jLabel21.setText("Ayudante de Campo");

        jTextFieldAyudanteCampoVisitante.setEditable(false);

        jLabel22.setText("Preparador Físico");

        jTextFieldPreparadorFisicoVisitante.setEditable(false);

        jLabelAMV.setText("Ayudante de Mesa Visitante");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabelAMV)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldAyudanteDeMesaVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldPreparadorFisicoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldDTVisitante)
                            .addComponent(jTextFieldAyudanteCampoVisitante, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDTVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAyudanteCampoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldPreparadorFisicoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAMV)
                    .addComponent(jTextFieldAyudanteDeMesaVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos Generales", jPanelDetalles);

        jTableLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "Cam", "Apellido y Nombre", "1° V", "2° V", "3° V", "1° A", "2° A", "RA", "RD", "Gol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLocal.setEnabled(false);
        jTableLocal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLocalMouseClicked(evt);
            }
        });
        jTableLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableLocalKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLocal);
        if (jTableLocal.getColumnModel().getColumnCount() > 0) {
            jTableLocal.getColumnModel().getColumn(0).setMinWidth(0);
            jTableLocal.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableLocal.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableLocal.getColumnModel().getColumn(1).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(1).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(1).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTableLocal.getColumnModel().getColumn(3).setMinWidth(40);
            jTableLocal.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableLocal.getColumnModel().getColumn(3).setMaxWidth(40);
            jTableLocal.getColumnModel().getColumn(4).setMinWidth(40);
            jTableLocal.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTableLocal.getColumnModel().getColumn(4).setMaxWidth(40);
            jTableLocal.getColumnModel().getColumn(5).setMinWidth(40);
            jTableLocal.getColumnModel().getColumn(5).setPreferredWidth(40);
            jTableLocal.getColumnModel().getColumn(5).setMaxWidth(40);
            jTableLocal.getColumnModel().getColumn(6).setMinWidth(40);
            jTableLocal.getColumnModel().getColumn(6).setPreferredWidth(40);
            jTableLocal.getColumnModel().getColumn(6).setMaxWidth(40);
            jTableLocal.getColumnModel().getColumn(7).setMinWidth(40);
            jTableLocal.getColumnModel().getColumn(7).setPreferredWidth(40);
            jTableLocal.getColumnModel().getColumn(7).setMaxWidth(40);
            jTableLocal.getColumnModel().getColumn(8).setMinWidth(40);
            jTableLocal.getColumnModel().getColumn(8).setPreferredWidth(40);
            jTableLocal.getColumnModel().getColumn(8).setMaxWidth(40);
            jTableLocal.getColumnModel().getColumn(9).setMinWidth(40);
            jTableLocal.getColumnModel().getColumn(9).setPreferredWidth(40);
            jTableLocal.getColumnModel().getColumn(9).setMaxWidth(40);
            jTableLocal.getColumnModel().getColumn(10).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(10).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(10).setMaxWidth(35);
        }
        jTableLocal.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                //jButtonGolLocal.setEnabled(true);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Equipo Local: ");

        jLabelEquipoLocal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoLocal.setText("Nombre Equipo");

        jTableGolLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, ""},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jugadora", "Gol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGolLocal.setEnabled(false);
        jTableGolLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableGolLocalKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(jTableGolLocal);
        if (jTableGolLocal.getColumnModel().getColumnCount() > 0) {
            jTableGolLocal.getColumnModel().getColumn(0).setMinWidth(80);
            jTableGolLocal.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTableGolLocal.getColumnModel().getColumn(0).setMaxWidth(80);
            jTableGolLocal.getColumnModel().getColumn(1).setMinWidth(40);
            jTableGolLocal.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableGolLocal.getColumnModel().getColumn(1).setMaxWidth(40);
        }

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Goles");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoLocal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelEquipoLocal)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipo Local", jPanel5);

        jTableVisitante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "Cam", "Apellido y Nombre", "1° V", "2° V", "3° V", "1° A", "2° A", "RA", "RD", "Gol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVisitante.setEnabled(false);
        jTableVisitante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVisitanteMouseClicked(evt);
            }
        });
        jTableVisitante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableVisitanteKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(jTableVisitante);
        if (jTableVisitante.getColumnModel().getColumnCount() > 0) {
            jTableVisitante.getColumnModel().getColumn(0).setMinWidth(0);
            jTableVisitante.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableVisitante.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableVisitante.getColumnModel().getColumn(1).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(1).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(1).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTableVisitante.getColumnModel().getColumn(3).setMinWidth(40);
            jTableVisitante.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableVisitante.getColumnModel().getColumn(3).setMaxWidth(40);
            jTableVisitante.getColumnModel().getColumn(4).setMinWidth(40);
            jTableVisitante.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTableVisitante.getColumnModel().getColumn(4).setMaxWidth(40);
            jTableVisitante.getColumnModel().getColumn(5).setMinWidth(40);
            jTableVisitante.getColumnModel().getColumn(5).setPreferredWidth(40);
            jTableVisitante.getColumnModel().getColumn(5).setMaxWidth(40);
            jTableVisitante.getColumnModel().getColumn(6).setMinWidth(40);
            jTableVisitante.getColumnModel().getColumn(6).setPreferredWidth(40);
            jTableVisitante.getColumnModel().getColumn(6).setMaxWidth(40);
            jTableVisitante.getColumnModel().getColumn(7).setMinWidth(40);
            jTableVisitante.getColumnModel().getColumn(7).setPreferredWidth(40);
            jTableVisitante.getColumnModel().getColumn(7).setMaxWidth(40);
            jTableVisitante.getColumnModel().getColumn(8).setMinWidth(40);
            jTableVisitante.getColumnModel().getColumn(8).setPreferredWidth(40);
            jTableVisitante.getColumnModel().getColumn(8).setMaxWidth(40);
            jTableVisitante.getColumnModel().getColumn(9).setMinWidth(40);
            jTableVisitante.getColumnModel().getColumn(9).setPreferredWidth(40);
            jTableVisitante.getColumnModel().getColumn(9).setMaxWidth(40);
            jTableVisitante.getColumnModel().getColumn(10).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(10).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(10).setMaxWidth(35);
        }
        jTableVisitante.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                //
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Equipo Visitante: ");

        jLabelEquipoVisitante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoVisitante.setText("Nombre Equipo");

        jTableGolVisitante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, ""},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jugadora", "Gol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGolVisitante.setEnabled(false);
        jTableGolVisitante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableGolVisitanteKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(jTableGolVisitante);
        if (jTableGolVisitante.getColumnModel().getColumnCount() > 0) {
            jTableGolVisitante.getColumnModel().getColumn(0).setMinWidth(80);
            jTableGolVisitante.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTableGolVisitante.getColumnModel().getColumn(0).setMaxWidth(80);
            jTableGolVisitante.getColumnModel().getColumn(1).setMinWidth(40);
            jTableGolVisitante.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableGolVisitante.getColumnModel().getColumn(1).setMaxWidth(40);
        }

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Goles");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoVisitante)))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelEquipoVisitante))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipo Visitante", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        //----------- ACA SE DESCUENTA LA SANCION DE UN FECHA DE LA JUGADORA --------------
        if (camposValidar()) {
            if (unPartido.getNombreVeedor() == null) {//unicamente va descontar la primera vez que se precione el boton guardar
                unaControladoraGlobal.descontarSancion(unPartido.getUnPlantelLocal(), unPartido.getFecha());
                unaControladoraGlobal.descontarSancion(unPartido.getUnPlantelVisitante(), unPartido.getFecha());
            }
            unaControladoraGlobal.modificarPartido(unPartido, jTextFieldVeedor.getText(), jTextFieldAyudanteDeMesaLocal.getText(), jTextFieldAyudanteDeMesaVisitante.getText(), jTextPaneObservacion.getText(), unPartido.isBorradoLogico());

            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEditar.setEnabled(true);
            jButtonImprimir.setEnabled(true);
            jButtonActualizar.setEnabled(false);

            camposActivo(false);
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed

        //Guarda el plantel e imprime la planilla de resultados de partido
        //if (unPartido.getPlantelLocal() == null) {
        Collection<Socia> unPlantelLocal = new ArrayList();

        for (int i = 0; i < jTableLocal.getRowCount(); i++) {
            unPlantelLocal.add(unaControladoraGlobal.getSociaBD((Long) jTableLocal.getValueAt(i, 0)));
        }
        unaControladoraGlobal.modificarPartidoPlantelLocal(unPartido, unPlantelLocal);
        //}

        //if (unPartido.getPlantelVisitante() == null) {
        Collection<Socia> unPlantelVisitante = null;
        for (int i = 0; i < jTableVisitante.getRowCount(); i++) {
            unPlantelVisitante.add(unaControladoraGlobal.getSociaBD((Long) jTableVisitante.getValueAt(i, 0)));
        }
        unaControladoraGlobal.modificarPartidoPlantelVisitante(unPartido, unPlantelVisitante);
        //}

        //Genera el reporte de la planilla de partido
        System.out.println("Se mostro el informe de Planilla de Partido");
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonCancelar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonEditar.setEnabled(false);

        camposActivo(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonEditar.setEnabled(true);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);

        camposActivo(false);

        //¿Aca un Actualizar?
        //camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cargarCampos();
    }//GEN-LAST:event_formComponentShown

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        //Carga la tabla con las socias habilitadas para jugar. ok
        //-Avisar si existe algun cambio, si es que hay un plantel guardado (Se imprimio la planilla de partido) FALTA
        //-Habilitado siempre y cuando no se haya jugado el partido. ok

        if (unPartido.getNombreVeedor() == null) {//Siempre y cuando el partodo no se haya jugado
            //Local
            if (unPartido.getUnPlantelLocal() != null) {
                Object[] options = {"OK", "Cancelar"};
                if (0 == JOptionPane.showOptionDialog(this, "Esta seguro que desea actualizar el plantel Local", "Actualizar Plantel", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options, options)) {
                    //Cargar Tabla Local
                    limpiarTabla(modeloTableLocal);

                    for (Socia unaSocia : unaControladoraGlobal.getJugadorasHabilitadas(unPartido.getUnEquipoLocal(), unPartido.getFecha())) {
                        cargarCamposTablaControlando(unaSocia, modeloTableLocal);
                    }
                }
            }

            //Visitante
            if (unPartido.getUnPlantelVisitante() != null) {
                Object[] options = {"OK", "Cancelar"};
                if (0 == JOptionPane.showOptionDialog(this, "Esta seguro que desea actualizar el Plantel Visitante", "Actualizar Plantel", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options, options)) {
                    //Cargar Tabla Visitante
                    limpiarTabla(modeloTableVisitante);
                    for (Socia unaSocia : unaControladoraGlobal.getJugadorasHabilitadas(unPartido.getUnEquipoVisitante(), unPartido.getFecha())) {
                        cargarCamposTablaControlando(unaSocia, modeloTableVisitante);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void jTableLocalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLocalMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            if (jTableLocal.getSelectedRow() > -1) {
                if (jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0) != null) {
                    Socia unaSociaSeleccionada = unaControladoraGlobal.getSociaBD((Long) jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0));
                    IResultadoPartidoCargarTarjeta unaICargarTarjeta;
                    if ((jTableLocal.getSelectedColumn() > 2) && (jTableLocal.getSelectedColumn() < 10)) {
                        Tarjeta unaTarjeta = (Tarjeta) jTableLocal.getValueAt(jTableLocal.getSelectedRow(), jTableLocal.getSelectedColumn());
                        if (unaTarjeta != null) { //Abrir ventana Cargar Tarjeta Mostrando el detalle de una Tarjeta (PARA EDITAR LA MISMA TAMBIEN)
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unaTarjeta);
                        } else {//Abrir ventana Cargar Tarjeta para Crear una Tarjeta
                            String unTipo = "";
                            if ((jTableLocal.getSelectedColumn() >= 3) && (jTableLocal.getSelectedColumn() < 5)) {
                                unTipo = "Verde";
                            } else if ((jTableLocal.getSelectedColumn() >= 6) && (jTableLocal.getSelectedColumn() < 8)) {
                                unTipo = "Amarilla";
                            } else if ((jTableLocal.getSelectedColumn() >= 8) && (jTableLocal.getSelectedColumn() < 10)) {
                                unTipo = "Roja";
                            }
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unTipo);
                        }
                        unaICargarTarjeta.pack();
                        unaICargarTarjeta.setVisible(true);
                        this.setVisible(false);
                        IMenuPrincipalInterface.jDesktopPane.add(unaICargarTarjeta);
                    } else if (jTableLocal.getSelectedColumn() == 10) {
                        IResultadoPartidoCargarGol unaICargarGol = new IResultadoPartidoCargarGol(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido);
                        unaICargarGol.pack();
                        unaICargarGol.setVisible(true);
                        this.setVisible(false);
                        IMenuPrincipalInterface.jDesktopPane.add(unaICargarGol);
                    }
                }
            }
        }
    }//GEN-LAST:event_jTableLocalMouseClicked

    private void jTableLocalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableLocalKeyTyped
        if (evt.getKeyChar() == 127) {
            Tarjeta unaTarjeta = (Tarjeta) jTableLocal.getValueAt(jTableLocal.getSelectedRow(), jTableLocal.getSelectedColumn());
            if (unaTarjeta != null) {
                Object[] options = {"OK", "Cancelar"};
                if (0 == JOptionPane.showOptionDialog(
                        this,
                        "¿Esta seguro que desea eliminar la Tarjeta?",
                        "Modificar",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options)) {
                    unaControladoraGlobal.eliminarTarjeta(unaTarjeta);
                    cargarCampos();
                }
            }
        }
    }//GEN-LAST:event_jTableLocalKeyTyped

    private void jTableVisitanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableVisitanteKeyTyped
        if (evt.getKeyChar() == 127) {
            Tarjeta unaTarjeta = (Tarjeta) jTableVisitante.getValueAt(jTableVisitante.getSelectedRow(), jTableVisitante.getSelectedColumn());
            if (unaTarjeta != null) {
                Object[] options = {"OK", "Cancelar"};
                if (0 == JOptionPane.showOptionDialog(
                        this,
                        "¿Esta seguro que desea eliminar la Tarjeta?",
                        "Modificar",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options)) {
                    unaControladoraGlobal.eliminarTarjeta(unaTarjeta);
                    cargarCampos();
                }
            }
        }
    }//GEN-LAST:event_jTableVisitanteKeyTyped

    private void jTableVisitanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVisitanteMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            if (jTableVisitante.getSelectedRow() > -1) {
                if (jTableVisitante.getValueAt(jTableVisitante.getSelectedRow(), 0) != null) {
                    Socia unaSociaSeleccionada = unaControladoraGlobal.getSociaBD((Long) jTableVisitante.getValueAt(jTableVisitante.getSelectedRow(), 0));
                    IResultadoPartidoCargarTarjeta unaICargarTarjeta;
                    if ((jTableVisitante.getSelectedColumn() > 2) && (jTableVisitante.getSelectedColumn() < 10)) {
                        Tarjeta unaTarjeta = (Tarjeta) jTableVisitante.getValueAt(jTableVisitante.getSelectedRow(), jTableVisitante.getSelectedColumn());
                        if (unaTarjeta != null) { //Abrir ventana Cargar Tarjeta Mostrando el detalle de una Tarjeta (PARA EDITAR LA MISMA TAMBIEN)
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unaTarjeta);
                        } else {//Abrir ventana Cargar Tarjeta para Crear una Tarjeta
                            String unTipo = "";
                            if ((jTableVisitante.getSelectedColumn() >= 3) && (jTableVisitante.getSelectedColumn() < 5)) {
                                unTipo = "Verde";
                            } else if ((jTableVisitante.getSelectedColumn() >= 6) && (jTableVisitante.getSelectedColumn() < 8)) {
                                unTipo = "Amarilla";
                            } else if ((jTableVisitante.getSelectedColumn() >= 8) && (jTableVisitante.getSelectedColumn() < 10)) {
                                unTipo = "Roja";
                            }
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unTipo);
                        }
                        unaICargarTarjeta.pack();
                        unaICargarTarjeta.setVisible(true);
                        this.setVisible(false);
                        IMenuPrincipalInterface.jDesktopPane.add(unaICargarTarjeta);
                    } else if (jTableVisitante.getSelectedColumn() == 10) {
                        IResultadoPartidoCargarGol unaICargarGol = new IResultadoPartidoCargarGol(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido);
                        unaICargarGol.pack();
                        unaICargarGol.setVisible(true);
                        this.setVisible(false);
                        IMenuPrincipalInterface.jDesktopPane.add(unaICargarGol);
                    }
                }
            }
        }
    }//GEN-LAST:event_jTableVisitanteMouseClicked

    private void jTableGolLocalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableGolLocalKeyTyped
        if (evt.getKeyChar() == 127) {
            Gol unGol = (Gol) jTableLocal.getValueAt(jTableGolLocal.getSelectedRow(), 1);
            if (unGol != null) {
                Object[] options = {"OK", "Cancelar"};
                if (0 == JOptionPane.showOptionDialog(
                        this,
                        "¿Esta seguro que desea eliminar el Gol",
                        "Modificar",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options)) {
                    unaControladoraGlobal.eliminarGol(unGol);
                    cargarCampos();
                }
            }
        }
    }//GEN-LAST:event_jTableGolLocalKeyTyped

    private void jTableGolVisitanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableGolVisitanteKeyTyped
        if (evt.getKeyChar() == 127) {
            Gol unGol = (Gol) jTableGolVisitante.getValueAt(jTableGolVisitante.getSelectedRow(), 1);
            if (unGol != null) {
                Object[] options = {"OK", "Cancelar"};
                if (0 == JOptionPane.showOptionDialog(
                        this,
                        "¿Esta seguro que desea eliminar el Gol",
                        "Modificar",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options)) {
                    unaControladoraGlobal.eliminarGol(unGol);
                    cargarCampos();
                }
            }
        }
    }//GEN-LAST:event_jTableGolVisitanteKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelAML;
    private javax.swing.JLabel jLabelAMV;
    private javax.swing.JLabel jLabelEquipoLocal;
    private javax.swing.JLabel jLabelEquipoVisitante;
    private javax.swing.JLabel jLabelFechaPartido;
    private javax.swing.JLabel jLabelFechaPartido1;
    private javax.swing.JLabel jLabelObservacion;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelVeedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableGolLocal;
    private javax.swing.JTable jTableGolVisitante;
    private javax.swing.JTable jTableLocal;
    private javax.swing.JTable jTableVisitante;
    private javax.swing.JTextField jTextFieldArbitro1;
    private javax.swing.JTextField jTextFieldArbitro2;
    private javax.swing.JTextField jTextFieldArbitro3;
    private javax.swing.JTextField jTextFieldAyudanteCampoLocal;
    private javax.swing.JTextField jTextFieldAyudanteCampoVisitante;
    private javax.swing.JTextField jTextFieldAyudanteDeMesaLocal;
    private javax.swing.JTextField jTextFieldAyudanteDeMesaVisitante;
    private javax.swing.JTextField jTextFieldCancha;
    private javax.swing.JTextField jTextFieldDTLocal;
    private javax.swing.JTextField jTextFieldDTVisitante;
    private javax.swing.JTextField jTextFieldPreparadorFisicoLocal;
    private javax.swing.JTextField jTextFieldPreparadorFisicoVisitante;
    private javax.swing.JTextField jTextFieldVeedor;
    private javax.swing.JTextPane jTextPaneObservacion;
    // End of variables declaration//GEN-END:variables
}
