package Interfaces;

import DataSources.PlanillaPartidoDS;
import DataSources.PlanillaPartidoDS_Plantel;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.LEFT;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Gol;
import logicaNegocios.Jugadora;
import logicaNegocios.Partido;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class IResultadoPartido extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JInternalFrame unJInternalFrame;
    private Partido unPartido;
    private DateFormat df = DateFormat.getDateInstance();

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

        camposActivo(false);
        // <editor-fold defaultstate="collapsed" desc="Control de Botones">
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        if (unaControladoraGlobal.isPartidoAnteriorJugado(unPartido) && (!unPartido.isJugado())) {
            jButtonImprimir.setEnabled(true);
        } else {
            jButtonImprimir.setEnabled(false);
        }
        if ((!unPartido.getJugadoras().isEmpty()) && (!unPartido.isJugado())) {
            jButtonActualizar.setEnabled(true);
        } else {
            jButtonActualizar.setEnabled(false);
        }
        if (!unPartido.isJugado()) {
            if ((unaControladoraGlobal.fechaSistema().after(unPartido.getFecha())) || (unaControladoraGlobal.fechaSistema().equals(unPartido.getFecha()))) {
                if (unPartido.getJugadoras().isEmpty()) {
                    jButtonEditar.setEnabled(false);
                } else {
                    jButtonEditar.setEnabled(true);
                }
            } else {
                jButtonEditar.setEnabled(false);
            }
        } else {
            jButtonEditar.setEnabled(false);
        }
        // </editor-fold>
    }

    // <editor-fold defaultstate="collapsed" desc="CLASS Renderización de la tabla para aplicar color">
    public class TableCellRendererColor extends DefaultTableCellRenderer {

        private JLabel componente;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            componente = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.      
            if ((column == 2)) {
                componente.setHorizontalAlignment(LEFT);
            } else {
                componente.setHorizontalAlignment(CENTER);
            }

            if (value instanceof Tarjeta) {
                if ((column >= 3) && (column < 5)) {
                    componente.setBackground(Color.green);
                } else if ((column >= 5) && (column < 7)) {
                    componente.setBackground(Color.yellow);
                } else if (column == 7) {
                    componente.setBackground(Color.red);
                }
                if (isSelected) {
                    componente.setForeground(Color.black);
                }
            } else {
                if (isSelected) {
                    componente.setBackground(Color.GRAY);
                } else {
                    componente.setBackground(Color.white);
                }
            }

            return componente;

        }

    }
    // </editor-fold>

    public void cargarCampos() {
        // <editor-fold defaultstate="collapsed" desc="Encabezado de la Ventana">
        jLabelTitulo.setText(unPartido.getUnEquipoLocal().getNombre() + " vs " + unPartido.getUnEquipoVisitante().getNombre());
        if (unPartido.isJugado()) { //El partido se jugo
            jLabelResultado.setText(unaControladoraGlobal.getGolesLocal(unPartido) + " a " + unaControladoraGlobal.getGolesVisitante(unPartido));
        } else {
            jLabelResultado.setText("- a -");
        }
        jLabelFechaPartido.setText(df.format(unPartido.getFecha()));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Datos Generales">        
        jTextFieldArbitro1.setText(unPartido.getUnArbitro1().getApellido() + ", " + unPartido.getUnArbitro1().getNombre());
        jTextFieldArbitro2.setText(unPartido.getUnArbitro2().getApellido() + ", " + unPartido.getUnArbitro2().getNombre());
        if (unPartido.getUnArbitro3() != null) {
            jTextFieldArbitro3.setText(unPartido.getUnArbitro3().getApellido() + ", " + unPartido.getUnArbitro3().getNombre());
        }
        jTextFieldCancha.setText(unPartido.getUnaCancha().getNombre());
        jTextPaneObservacion.setText(unPartido.getObservaciones());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Datos Local">
        // <editor-fold defaultstate="collapsed" desc="Equipo - Local">
        jLabelEquipoLocal.setText(unPartido.getUnEquipoLocal().getNombre());
        // </editor-fold>        
        // <editor-fold defaultstate="collapsed" desc="Director Tecnico - Local">
        if (unPartido.getUnDTLocal() == null) {
            jTextFieldDTLocal.setText(unPartido.getUnEquipoLocal().getUnDT().toString());
        } else {
            jTextFieldDTLocal.setText(unPartido.getUnDTLocal().toString());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Ayudante de Campo - Local">
        if (unPartido.getUnAyudanteCampoLocal() == null) {
            if (unPartido.getUnEquipoLocal().getUnAyudanteCampo() != null) {
                jTextFieldAyudanteCampoLocal.setText(unPartido.getUnEquipoLocal().toString());
            } else {
                jTextFieldAyudanteCampoLocal.setText("-");
            }
        } else {
            jTextFieldAyudanteCampoLocal.setText(unPartido.getUnAyudanteCampoLocal().toString());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Preparador Fisico - Local">
        if (unPartido.getUnPreparadorFisicoLocal() == null) {
            if (unPartido.getUnEquipoLocal().getUnPreparadorFisico() != null) {
                jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnEquipoLocal().getUnPreparadorFisico().toString());
            } else {
                jTextFieldPreparadorFisicoLocal.setText("-");
            }
        } else {
            jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnPreparadorFisicoLocal().toString());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Ayudante de Mesa - Local">
        if (jTextFieldAyudanteDeMesaLocal.getText().equals("")) {
            jTextFieldAyudanteDeMesaLocal.setText(unPartido.getNombreAyudanteMesaLocal());
        }
        // </editor-fold>
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Datos Visitante">
        // <editor-fold defaultstate="collapsed" desc="Equipo - Visitante">
        jLabelEquipoVisitante.setText(unPartido.getUnEquipoVisitante().getNombre());
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Director Tecnico - Visitante">
        if (unPartido.getUnDTVisitante() == null) {
            jTextFieldDTVisitante.setText(unPartido.getUnEquipoVisitante().getUnDT().toString());
        } else {
            jTextFieldDTVisitante.setText(unPartido.getUnDTVisitante().toString());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Ayudante de Campo - Visitante">
        if (unPartido.getUnAyudanteCampoVisitante() == null) {
            if (unPartido.getUnEquipoVisitante().getUnAyudanteCampo() != null) {
                jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnEquipoVisitante().getUnAyudanteCampo().toString());

            } else {
                jTextFieldAyudanteCampoVisitante.setText("-");
            }
        } else {
            jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnAyudanteCampoVisitante().toString());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Preparador Fisico - Visitante">
        if (unPartido.getUnPreparadorFisicoVisitante() == null) {
            if (unPartido.getUnEquipoVisitante().getUnPreparadorFisico() != null) {
                jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnEquipoVisitante().getUnPreparadorFisico().toString());
            } else {
                jTextFieldPreparadorFisicoVisitante.setText("-");
            }
        } else {
            jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnPreparadorFisicoVisitante().toString());
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Ayudante de Mesa - Visitante">
        if (jTextFieldAyudanteDeMesaVisitante.getText().equals("")) {
            jTextFieldAyudanteDeMesaVisitante.setText(unPartido.getNombreAyudanteMesaVisitante());
        }
        // </editor-fold>
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Datos Planteles">
        if (unPartido.getJugadoras().isEmpty()) {
            cargarPlanteles();
        } else {
            cargarJugadoras();
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Datos Goles">
        cargarGoles();
        // </editor-fold>
    }

    private void cargarGoles() {
        limpiarTabla(modeloTableGolLocal);
        limpiarTabla(modeloTableGolVisitante);
        Jugadora autoraGol = null;
        for (Gol unGol : unPartido.getGoles()) {
            autoraGol = unaControladoraGlobal.getAutoraGol(unPartido, unGol);
            if (autoraGol != null) {
                if (autoraGol.isLocal()) {
                    modeloTableGolLocal.addRow(new Object[]{autoraGol.getUnaSocia().getApellido(), unGol});
                } else {
                    modeloTableGolVisitante.addRow(new Object[]{autoraGol.getUnaSocia().getApellido(), unGol});
                }
            }
        }
    }

    private void cargarJugadoras() {
        limpiarTabla(modeloTableLocal);
        limpiarTabla(modeloTableVisitante);
        for (Jugadora unaJugadora : unPartido.getJugadoras()) {
            if (unaJugadora.isLocal()) {
                cargarCamposTablaControlando(modeloTableLocal, unaJugadora.getUnaSocia(), unaJugadora.getCamiseta());
            } else {
                cargarCamposTablaControlando(modeloTableVisitante, unaJugadora.getUnaSocia(), unaJugadora.getCamiseta());
            }
        }
    }

    private void cargarPlanteles() {
        limpiarTabla(modeloTableLocal);
        limpiarTabla(modeloTableVisitante);
        for (Socia unaSocia : unPartido.getUnEquipoLocal().getPlantel()) {
            if (unaSocia.isHabilitadaParaJugar(unPartido.getFecha())) {
                cargarCamposTablaControlando(modeloTableLocal, unaSocia, unaSocia.getNumeroCamiseta());
            }
        }
        for (Socia unaSocia : unPartido.getUnEquipoVisitante().getPlantel()) {
            if (unaSocia.isHabilitadaParaJugar(unPartido.getFecha())) {
                cargarCamposTablaControlando(modeloTableVisitante, unaSocia, unaSocia.getNumeroCamiseta());
            }
        }
    }

    private void camposActivo(boolean Editable) {
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

    private void cargarCamposTablaControlando(DefaultTableModel modeloTable, Socia unaSocia, String camiseta) {
        // <editor-fold defaultstate="collapsed" desc="Control Tarjetas">        
        Tarjeta v1 = null, v2 = null, a1 = null, a2 = null, roja = null;
        for (Tarjeta unaTarjeta : unPartido.getTarjetas()) {
            if ((!unaTarjeta.isBorradoLogico()) && (unaSocia.isAutoraTarjeta(unaTarjeta))) {
                if ("Verde".equals(unaTarjeta.getTipo())) {
                    if (v1 == null) {
                        v1 = unaTarjeta;
                    } else {
                        v2 = unaTarjeta;
                    }
                }
                if ("Amarilla".equals(unaTarjeta.getTipo())) {
                    if (a1 == null) {
                        a1 = unaTarjeta;
                    } else {
                        a2 = unaTarjeta;
                    }
                }
                if ("Roja".equals(unaTarjeta.getTipo())) {
                    roja = unaTarjeta;
                }
            }
        }
        // </editor-fold>
        modeloTable.addRow(new Object[]{unaSocia.getDni(), camiseta, unaSocia, v1, v2, a1, a2, roja, unaControladoraGlobal.getGolesSocia(unPartido, unaSocia)});
    }

    private boolean camposValidar() {
        boolean bandera = true;
        if (jTextFieldAyudanteDeMesaLocal.getText().isEmpty()) {
            jLabelAML.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelAML.setForeground(Color.black);
        }
        if (jTextFieldAyudanteDeMesaVisitante.getText().isEmpty()) {
            jLabelAMV.setForeground(Color.red);
            bandera = false;
        } else {
            jLabelAMV.setForeground(Color.black);
        }
        if (!bandera) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios");
            return false;
        }
        return bandera;
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int filas = modeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTabla.removeRow(0);
        }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneObservacion = new javax.swing.JTextPane();
        jLabelObservacion = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldArbitro3 = new javax.swing.JTextField();
        jTextFieldArbitro2 = new javax.swing.JTextField();
        jTextFieldArbitro1 = new javax.swing.JTextField();
        jTextFieldCancha = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
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
        setMaximumSize(new java.awt.Dimension(803, 718));
        setMinimumSize(new java.awt.Dimension(803, 718));
        setPreferredSize(new java.awt.Dimension(803, 718));
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

        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Actualizar.png"))); // NOI18N
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
                .addGap(12, 12, 12)
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

        jScrollPane2.setViewportView(jTextPaneObservacion);

        jLabelObservacion.setText("Observación");

        jLabel15.setText("Arbitro 1");

        jLabel14.setText("Arbitro 2");

        jLabel13.setText("Arbitro 3");

        jTextFieldArbitro3.setEditable(false);

        jTextFieldArbitro2.setEditable(false);

        jTextFieldArbitro1.setEditable(false);

        jTextFieldCancha.setEditable(false);

        jLabel16.setText("Chancha");

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jLabelObservacion)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldArbitro2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldArbitro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(29, 29, 29)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelObservacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Generales", jPanelDetalles);

        jTableLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Cam", "Apellido y Nombre", "1° V", "2° V", "1° A", "2° A", "Roja", "Gol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            jTableLocal.getColumnModel().getColumn(8).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(8).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(8).setMaxWidth(35);
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
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoLocal))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Equipo Local", jPanel5);

        jTableVisitante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Cam", "Apellido y Nombre", "1° V", "2° V", "1° A", "2° A", "Roja", "Gol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            jTableVisitante.getColumnModel().getColumn(8).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(8).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(8).setMaxWidth(35);
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
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoVisitante))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelEquipoVisitante)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Equipo Visitante", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        //----------- ACA SE DESCUENTA LA SANCION DE UN FECHA DE LA JUGADORA --------------
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "¿Esta seguro que desea guardar los datos?" + System.getProperty("line.separator") + "Una vez que los guarde ya no se podrán deshacer los cambios ni agregar nuevos." + System.getProperty("line.separator") + System.getProperty("line.separator") + "PIÉNSELO DE NUEVO POR FAVOR.",
                "Advertencia Guardar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {

            if (camposValidar()) {
                unaControladoraGlobal.modificarPartido(unPartido, jTextFieldAyudanteDeMesaLocal.getText(), jTextFieldAyudanteDeMesaVisitante.getText(), jTextPaneObservacion.getText(), true, unPartido.isBorradoLogico());
                unaControladoraGlobal.descontarSancion(unPartido);
                unaControladoraGlobal.computarTarjetasAcumuladas(unPartido);

                jButtonGuardar.setEnabled(false);
                jButtonCancelar.setEnabled(false);
                jButtonEditar.setEnabled(false);
                jButtonImprimir.setEnabled(false);
                jButtonActualizar.setEnabled(false);

                camposActivo(false);
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        if (!unPartido.isJugado()) {
            //Elimina las Jugadoras guardadas anteriormente y Guarda las que esten actualmente en la planilla.
            unaControladoraGlobal.vaciarJugadoras(unPartido);
            jButtonEditar.setEnabled(true);
            for (int i = 0; i < jTableLocal.getRowCount(); i++) {
                unaControladoraGlobal.agregarJugadora(unPartido, (Socia) jTableLocal.getValueAt(i, 2), (String) jTableLocal.getValueAt(i, 1), true);
            }
            for (int i = 0; i < jTableVisitante.getRowCount(); i++) {
                unaControladoraGlobal.agregarJugadora(unPartido, (Socia) jTableVisitante.getValueAt(i, 2), (String) jTableVisitante.getValueAt(i, 1), false);
            }
        }

        //Genera el reporte de la planilla de partido
        PlanillaPartidoDS unaPlanillaPartidoDS = new PlanillaPartidoDS(unaControladoraGlobal, unPartido);
        PlanillaPartidoDS_Plantel unPlantelLocalDS = new PlanillaPartidoDS_Plantel(unaControladoraGlobal, unPartido, unPartido.getJugadorasLocales());
        PlanillaPartidoDS_Plantel unPlantelVisitanteDS = new PlanillaPartidoDS_Plantel(unaControladoraGlobal, unPartido, unPartido.getJugadorasVisitantes());

        File archivo = new File("reportes/reportePlanillaPartido.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            Map parameters = new HashMap();
            parameters.put("subreport_datasource_plantelLocal", unPlantelLocalDS);
            parameters.put("subreport_datasource_plantelVisitante", unPlantelVisitanteDS);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, unaPlanillaPartidoDS);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(IResultadoPartido.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        if (unPartido.isJugado()) {//Habilitado siempre y cuando no se haya jugado el partido.
            Object[] options = {"OK", "Cancelar"};
            if (0 == JOptionPane.showOptionDialog(this, "Esta seguro que desea actualizar los planteles?", "Actualizar Plantel", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options, options)) {
                //Carga la tabla con las socias habilitadas para jugar.
                cargarPlanteles();
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
                    if ((jTableLocal.getSelectedColumn() > 2) && (jTableLocal.getSelectedColumn() < 8)) {
                        Tarjeta unaTarjeta = (Tarjeta) jTableLocal.getValueAt(jTableLocal.getSelectedRow(), jTableLocal.getSelectedColumn());
                        if (unaTarjeta != null) { //Abrir ventana Cargar Tarjeta Mostrando el detalle de una Tarjeta (PARA EDITAR LA MISMA TAMBIEN)
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unaTarjeta);
                        } else {//Abrir ventana Cargar Tarjeta para Crear una Tarjeta
                            String unTipo = "";
                            if ((jTableLocal.getSelectedColumn() >= 3) && (jTableLocal.getSelectedColumn() < 5)) {
                                unTipo = "Verde";
                            } else if ((jTableLocal.getSelectedColumn() >= 5) && (jTableLocal.getSelectedColumn() < 7)) {
                                unTipo = "Amarilla";
                            } else if (jTableLocal.getSelectedColumn() == 7) {
                                unTipo = "Roja";
                            }
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unTipo);
                        }
                        unaICargarTarjeta.pack();
                        unaICargarTarjeta.setVisible(true);
                        this.setVisible(false);
                        IMenuPrincipalInterface.jDesktopPane.add(unaICargarTarjeta);
                    } else if (jTableLocal.getSelectedColumn() == 8) {
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
                    if ((jTableVisitante.getSelectedColumn() > 2) && (jTableVisitante.getSelectedColumn() < 8)) {
                        Tarjeta unaTarjeta = (Tarjeta) jTableVisitante.getValueAt(jTableVisitante.getSelectedRow(), jTableVisitante.getSelectedColumn());
                        if (unaTarjeta != null) { //Abrir ventana Cargar Tarjeta Mostrando el detalle de una Tarjeta (PARA EDITAR LA MISMA TAMBIEN)
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unaTarjeta);
                        } else {//Abrir ventana Cargar Tarjeta para Crear una Tarjeta
                            String unTipo = "";
                            if ((jTableVisitante.getSelectedColumn() >= 3) && (jTableVisitante.getSelectedColumn() < 5)) {
                                unTipo = "Verde";
                            } else if ((jTableVisitante.getSelectedColumn() >= 5) && (jTableVisitante.getSelectedColumn() < 7)) {
                                unTipo = "Amarilla";
                            } else if (jTableVisitante.getSelectedColumn() == 7) {
                                unTipo = "Roja";
                            }
                            unaICargarTarjeta = new IResultadoPartidoCargarTarjeta(unaControladoraGlobal, this, unaSociaSeleccionada, unPartido, unTipo);
                        }
                        unaICargarTarjeta.pack();
                        unaICargarTarjeta.setVisible(true);
                        this.setVisible(false);
                        IMenuPrincipalInterface.jDesktopPane.add(unaICargarTarjeta);
                    } else if (jTableVisitante.getSelectedColumn() == 8) {
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

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cargarCampos();
    }//GEN-LAST:event_formComponentShown

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
    private javax.swing.JTextPane jTextPaneObservacion;
    // End of variables declaration//GEN-END:variables
}
