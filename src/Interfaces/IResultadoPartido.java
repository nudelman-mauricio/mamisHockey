package Interfaces;

import java.text.DateFormat;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import logicaNegocios.Partido;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import main.ControladoraGlobal;

public class IResultadoPartido extends javax.swing.JInternalFrame {

    ControladoraGlobal unaControladoraGlobal;
    JInternalFrame unJInternalFrame;
    Partido unPartido;

    private DefaultTableModel modeloTableLocal;
    private DefaultTableModel modeloTableVisitante;

    public IResultadoPartido(ControladoraGlobal unaControladoraGlobal, JInternalFrame unJInternalFrame, Partido unPartido) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unJInternalFrame = unJInternalFrame;
        this.unPartido = unPartido;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/PanillaResultados.png")));
        this.setTitle(unPartido.getUnEquipoLocal().getNombre() + " vs " + unPartido.getUnEquipoVisitante().getNombre());
        IMenuPrincipalInterface.centrar(this);

        jLabelEquipoLocal.setText(unPartido.getUnEquipoLocal().getNombre());
        jLabelEquipoVisitante.setText(unPartido.getUnEquipoVisitante().getNombre());

        this.modeloTableLocal = (DefaultTableModel) jTableLocal.getModel();
        cargarCamposTabla(unPartido.getUnEquipoLocal(), modeloTableLocal, unPartido.getPlantelLocal());
        this.modeloTableVisitante = (DefaultTableModel) jTableVisitante.getModel();
        cargarCamposTabla(unPartido.getUnEquipoVisitante(), modeloTableVisitante, unPartido.getPlantelVisitante());
    }

    public void camposActivo(boolean Editable) {
        jTextFieldVeedor.setEditable(Editable);
        jTextFieldAyudanteDeMesaLocal.setEditable(Editable);
        jTextFieldAyudanteDeMesaVisitante.setEditable(Editable);
        jTextAreaObservacion.setEditable(Editable);

        //jTableLocal.setEnabled(false);
        //jTableVisitante.setEnabled(false);
    }

    void camposCargar() {
        //Detalles
        jTextFieldArbitro1.setText(unPartido.getUnArbitro1().getApellido() + ", " + unPartido.getUnArbitro1().getNombre());
        jTextFieldArbitro2.setText(unPartido.getUnArbitro2().getApellido() + ", " + unPartido.getUnArbitro2().getNombre());
        jTextFieldArbitro2.setText(unPartido.getUnArbitro2().getApellido() + ", " + unPartido.getUnArbitro2().getNombre());
        jTextFieldCancha.setText(unPartido.getUnaCancha().getNombre());
        jTextFieldVeedor.setText(unPartido.getNombreVeedor());
        // <editor-fold defaultstate="collapsed" desc="Local">
        //DT
        if (unPartido.getUnDTLocal() == null) {
            jTextFieldDTLocal.setText(unPartido.getUnEquipoLocal().getUnDT().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnDT().getNombre());
        } else {
            jTextFieldDTLocal.setText(unPartido.getUnDTLocal().getApellido() + ", " + unPartido.getUnDTLocal().getNombre());
        }
        //AC
        if (unPartido.getUnAyudanteCampoLocal() == null) {
            jTextFieldAyudanteCampoLocal.setText(unPartido.getUnEquipoLocal().getUnAyudanteCampo().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnAyudanteCampo().getNombre());
        } else {
            jTextFieldAyudanteCampoLocal.setText(unPartido.getUnAyudanteCampoLocal().getApellido() + ", " + unPartido.getUnAyudanteCampoLocal().getNombre());
        }
        //PF
        if (unPartido.getUnPreparadorFisicoLocal() == null) {
            jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnEquipoLocal().getUnPreparadorFisico().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnPreparadorFisico().getNombre());
        } else {
            jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnPreparadorFisicoLocal().getApellido() + ", " + unPartido.getUnPreparadorFisicoLocal().getNombre());
        }
        jTextFieldAyudanteDeMesaLocal.setText(unPartido.getNombreAyudanteMesaLocal());
        
        //CARGAR TABLA Local cargarCamposTablaControlando(unPartido.getUnEquipoLocal(), modeloTableLocal);
        //CARGAR TABLA GOLES
        
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Visitante">
        //DT
        if (unPartido.getUnDTVisitante() == null) {
        jTextFieldDTVisitante.setText(unPartido.getUnEquipoVisitante().getUnDT().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnDT().getNombre());
        } else {
        jTextFieldDTVisitante.setText(unPartido.getUnDTVisitante().getApellido() + ", " + unPartido.getUnDTVisitante().getNombre());
        }
        //AC
        if (unPartido.getUnAyudanteCampoVisitante() == null) {
        jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnEquipoVisitante().getUnAyudanteCampo().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnAyudanteCampo().getNombre());
        } else {
        jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnAyudanteCampoVisitante().getApellido() + ", " + unPartido.getUnAyudanteCampoVisitante().getNombre());
        }
        //PF
        if (unPartido.getUnPreparadorFisicoVisitante() == null) {
        jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnEquipoVisitante().getUnPreparadorFisico().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnPreparadorFisico().getNombre());
        } else {
        jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnPreparadorFisicoVisitante().getApellido() + ", " + unPartido.getUnPreparadorFisicoVisitante().getNombre());
        }
        jTextFieldAyudanteDeMesaVisitante.setText(unPartido.getNombreAyudanteMesaVisitante());        
        
        //CARGAR TABLA Visitante cargarCamposTabla(unPartido.getUnEquipoVisitante(), modeloTableVisitante, unPartido.getPlantelVisitante());
        //CARGAR TABLA GOLES
        
        // </editor-fold>
        jTextAreaObservacion.setText(unPartido.getObservaciones());
        
        //REVISAR Y CARGAR LOS GOLES --- ACA ME QUEDE
        jLabelResultado.setText("- a -");
    }

    public void cargarCamposTablaControlando(Equipo unEquipo, DefaultTableModel modeloTable) {
        Tarjeta v1 = null, v2 = null, a1 = null, a2 = null, ra = null, rd = null;
        limpiarTabla(modeloTable);
        for (Socia unaSocia : unaControladoraGlobal.getJugadorasHabilitadas(unEquipo)) {
            for (Tarjeta unTarjeta : unaControladoraGlobal.getTarjetaSociaPartido(unPartido, unaSocia)) {
                if (unTarjeta != null) {
                    if ("Verde".equals(unTarjeta.getTipo())) {
                        if (v1 == null) {
                            v1 = unTarjeta;
                        } else {
                            if (Double.parseDouble(v1.getTiempo()) <= Double.parseDouble(unTarjeta.getTiempo())) {
                                if (Double.parseDouble(v1.getMinuto()) > Double.parseDouble(unTarjeta.getMinuto())) {
                                    v2 = unTarjeta;
                                } else {
                                    v2 = v1;
                                    v1 = unTarjeta;
                                }
                            } else {
                                v2 = v1;
                                v1 = unTarjeta;
                            }
                        }
                    }
                    if ("Amarrilla".equals(unTarjeta.getTipo())) {
                        if (a1 == null) {
                            a1 = unTarjeta;
                        } else {
                            if (Double.parseDouble(a1.getTiempo()) <= Double.parseDouble(unTarjeta.getTiempo())) {
                                if (Double.parseDouble(a1.getMinuto()) > Double.parseDouble(unTarjeta.getMinuto())) {
                                    a2 = unTarjeta;
                                } else {
                                    a2 = v1;
                                    a1 = unTarjeta;
                                }
                            } else {
                                a2 = a1;
                                a1 = unTarjeta;
                            }
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
                if (v1 == null) {
                    v1.setTiempo("-");
                    v1.setMinuto("-");
                }
                if (v2 == null) {
                    v2.setTiempo("-");
                    v2.setMinuto("-");
                }
                if (a1 == null) {
                    a1.setTiempo("-");
                    a1.setMinuto("-");
                }
                if (a2 == null) {
                    a2.setTiempo("-");
                    a2.setMinuto("-");
                }
                if (ra == null) {
                    ra.setTiempo("-");
                    ra.setMinuto("-");
                }
                if (rd == null) {
                    rd.setTiempo("-");
                    rd.setMinuto("-");
                }
            }
            modeloTable.addRow(new Object[]{String.valueOf(unaSocia.getDni()), unaSocia.getNumeroCamiseta(), unaSocia.getApellido() + ", " + unaSocia.getNombre(),
                v1.getTiempo(), v1.getMinuto(),
                v2.getTiempo(), v2.getMinuto(),
                a1.getTiempo(), a1.getMinuto(),
                a2.getTiempo(), a2.getMinuto(),
                ra.getTiempo(), ra.getMinuto(),
                rd.getTiempo(), rd.getMinuto()});
        }
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
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaObservacion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldDTLocal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldAyudanteCampoLocal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldPreparadorFisicoLocal = new javax.swing.JTextField();
        jTextFieldAyudanteDeMesaLocal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldDTVisitante = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldAyudanteCampoVisitante = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldPreparadorFisicoVisitante = new javax.swing.JTextField();
        jTextFieldAyudanteDeMesaVisitante = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLocal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelEquipoLocal = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableGolLocal = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButtonGolLocal = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVisitante = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabelEquipoVisitante = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableGolLocal2 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jButtonGolVisitante = new javax.swing.JButton();

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

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelTituloLayout.setVerticalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResultado)
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

        jLabel12.setText("Veedor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextFieldArbitro3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldArbitro2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldArbitro1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCancha)
                    .addComponent(jTextFieldVeedor, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(50, 50, 50))
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
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldArbitro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTextAreaObservacion.setColumns(20);
        jTextAreaObservacion.setRows(5);
        jScrollPane3.setViewportView(jTextAreaObservacion);

        jLabel9.setText("Observación");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipo Local"));

        jLabel7.setText("Director Tecnico");

        jTextFieldDTLocal.setEditable(false);

        jLabel17.setText("Ayudante de Campo");

        jTextFieldAyudanteCampoLocal.setEditable(false);

        jLabel18.setText("Preparador Físico");

        jTextFieldPreparadorFisicoLocal.setEditable(false);

        jLabel19.setText("Ayudante de Mesa Local");

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
                            .addComponent(jLabel19)
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
                    .addComponent(jLabel19)
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

        jLabel23.setText("Ayudante de Mesa Visitante");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel23)
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
                    .addComponent(jLabel23)
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
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, ""},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "Cam", "Apellido y Nombre", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
            jTableLocal.getColumnModel().getColumn(3).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(3).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(4).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(4).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(4).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(5).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(5).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(5).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(6).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(6).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(6).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(7).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(7).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(7).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(8).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(8).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(8).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(9).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(9).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(9).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(10).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(10).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(10).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(11).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(11).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(11).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(12).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(12).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(12).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(13).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(13).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(13).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(14).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(14).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(14).setMaxWidth(35);
        }
        jTableLocal.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                jButtonGolLocal.setEnabled(true);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Equipo Local: ");

        jLabelEquipoLocal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoLocal.setText("Nombre Equipo");

        jTableGolLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, ""},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cam", "T", "Min"
            }
        ));
        jTableGolLocal.setEnabled(false);
        jScrollPane4.setViewportView(jTableGolLocal);
        if (jTableGolLocal.getColumnModel().getColumnCount() > 0) {
            jTableGolLocal.getColumnModel().getColumn(0).setMinWidth(35);
            jTableGolLocal.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableGolLocal.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableGolLocal.getColumnModel().getColumn(1).setMinWidth(20);
            jTableGolLocal.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableGolLocal.getColumnModel().getColumn(1).setMaxWidth(20);
            jTableGolLocal.getColumnModel().getColumn(2).setMinWidth(35);
            jTableGolLocal.getColumnModel().getColumn(2).setPreferredWidth(35);
            jTableGolLocal.getColumnModel().getColumn(2).setMaxWidth(35);
        }

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Goles");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonGolLocal.setText("Agregar Gol");
        jButtonGolLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGolLocalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoLocal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButtonGolLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabelEquipoLocal))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGolLocal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipo Local", jPanel5);

        jTableVisitante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, ""},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "Cam", "Apellido y Nombre", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
            jTableVisitante.getColumnModel().getColumn(3).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(3).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(4).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(4).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(4).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(5).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(5).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(5).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(6).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(6).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(6).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(7).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(7).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(7).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(8).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(8).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(8).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(9).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(9).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(9).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(10).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(10).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(10).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(11).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(11).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(11).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(12).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(12).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(12).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(13).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(13).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(13).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(14).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(14).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(14).setMaxWidth(35);
        }
        jTableVisitante.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                jButtonGolVisitante.setEnabled(true);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Equipo Visitante: ");

        jLabelEquipoVisitante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoVisitante.setText("Nombre Equipo");

        jTableGolLocal2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, ""},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cam", "T", "Min"
            }
        ));
        jTableGolLocal2.setEnabled(false);
        jScrollPane8.setViewportView(jTableGolLocal2);
        if (jTableGolLocal2.getColumnModel().getColumnCount() > 0) {
            jTableGolLocal2.getColumnModel().getColumn(0).setMinWidth(35);
            jTableGolLocal2.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableGolLocal2.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableGolLocal2.getColumnModel().getColumn(1).setMinWidth(20);
            jTableGolLocal2.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableGolLocal2.getColumnModel().getColumn(1).setMaxWidth(20);
            jTableGolLocal2.getColumnModel().getColumn(2).setMinWidth(35);
            jTableGolLocal2.getColumnModel().getColumn(2).setPreferredWidth(35);
            jTableGolLocal2.getColumnModel().getColumn(2).setMaxWidth(35);
        }

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Goles");
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonGolVisitante.setText("Agregar Gol");
        jButtonGolVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGolVisitanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoVisitante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jButtonGolVisitante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(13, 13, 13))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelEquipoVisitante)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGolVisitante)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipo Visitante", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        unPartido.setNombreVeedor(jTextFieldVeedor.getText());
        unPartido.setObservaciones(jTextAreaObservacion.getText());

        //Local
        jTextFieldDTLocal.getText();
        jTextFieldAyudanteCampoLocal.getText();
        jTextFieldPreparadorFisicoLocal.getText();
        unPartido.setNombreAyudanteMesaLocal(jTextFieldAyudanteDeMesaLocal.getText());

        //Visitante
        jTextFieldDTVisitante.getText();
        jTextFieldAyudanteCampoVisitante.getText();
        jTextFieldPreparadorFisicoVisitante.getText();
        unPartido.setNombreAyudanteMesaVisitante(jTextFieldAyudanteDeMesaVisitante.getText());

        if (!(jTextFieldNombre.getText().isEmpty()) && !(jTextFieldCodPostal.getText().isEmpty())) {
            if (unaLocalidadSeleccionada == null) {
                unaControladoraGlobal.crearLocalidad(jTextFieldNombre.getText(), jTextFieldCodPostal.getText());
                JOptionPane.showMessageDialog(this, "Localidad Guardada");

            } else {
                unaControladoraGlobal.modificarLocalidad(unaLocalidadSeleccionada, jTextFieldNombre.getText(), jTextFieldCodPostal.getText(), unaLocalidadSeleccionada.isBorradoLogico());
                unaLocalidadSeleccionada = null;
                JOptionPane.showMessageDialog(this, "Localidad Modificada");
            }
            jButtonNuevo.setEnabled(true);
            jButtonEditar.setEnabled(false);
            jButtonGuardar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonEliminar.setEnabled(false);

            camposActivo(false);
            camposLimpiar();

            cargarTabla();
            jTableLocalidad.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonGolLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGolLocalActionPerformed
        Socia unaSociaSeleccionada = unaControladoraGlobal.getSociaBD((Long) jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0));
        IGol unIGol = new IGol(unaControladoraGlobal, unJInternalFrame, unaSociaSeleccionada, unPartido);
        unIGol.pack();
        unIGol.setVisible(true);

        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIGol);
    }//GEN-LAST:event_jButtonGolLocalActionPerformed

    private void jButtonGolVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGolVisitanteActionPerformed
        Socia unaSociaSeleccionada = unaControladoraGlobal.getSociaBD((Long) jTableVisitante.getValueAt(jTableVisitante.getSelectedRow(), 0));
        IGol unIGol = new IGol(unaControladoraGlobal, unJInternalFrame, unaSociaSeleccionada, unPartido);
        unIGol.pack();
        unIGol.setVisible(true);

        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIGol);
    }//GEN-LAST:event_jButtonGolVisitanteActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonCancelar.setEnabled(false);
        jButtonGuardar.setEnabled(true);
        jButtonCancelar.setEnabled(true);

        jTableLocalidad.setEnabled(false);

        camposActivo(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonEditar.setEnabled(false);
        jButtonGuardar.setEnabled(false);
        jButtonCancelar.setEnabled(false);

        jTableLocalidad.setEnabled(true);

        camposActivo(false);

        //¿Aca un Actualizar?
        //camposLimpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGolLocal;
    private javax.swing.JButton jButtonGolVisitante;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEquipoLocal;
    private javax.swing.JLabel jLabelEquipoVisitante;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelTitulo;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableGolLocal;
    private javax.swing.JTable jTableGolLocal2;
    private javax.swing.JTable jTableLocal;
    private javax.swing.JTable jTableVisitante;
    private javax.swing.JTextArea jTextAreaObservacion;
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
    // End of variables declaration//GEN-END:variables
}
