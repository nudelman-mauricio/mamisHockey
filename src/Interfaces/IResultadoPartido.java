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

        //Detalles
        jTextFieldArbitro1.setText(unPartido.getUnArbitro1().getApellido() + ", " + unPartido.getUnArbitro1().getNombre());
        jTextFieldArbitro2.setText(unPartido.getUnArbitro2().getApellido() + ", " + unPartido.getUnArbitro2().getNombre());
        jTextFieldArbitro2.setText(unPartido.getUnArbitro2().getApellido() + ", " + unPartido.getUnArbitro2().getNombre());
        jTextFieldCancha.setText(unPartido.getUnaCancha().getNombre());
        jTextFieldVeedor.setText(unPartido.getNombreVeedor());
        jTextAreaDetalle.setText(unPartido.getObservaciones());
        jLabelResultado.setText("- a -");

        this.modeloTableLocal = (DefaultTableModel) jTableLocal.getModel();
        cargarCamposTabla(unPartido.getUnEquipoLocal(), modeloTableLocal, unPartido.getPlantelLocal());
        this.modeloTableVisitante = (DefaultTableModel) jTableVisitante.getModel();
        cargarCamposTabla(unPartido.getUnEquipoVisitante(), modeloTableVisitante, unPartido.getPlantelVisitante());
    }

    public void InicioNuevo() {
        //Local
        jTextFieldDTLocal.setText(unPartido.getUnEquipoLocal().getUnDT().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnDT().getNombre());
        jTextFieldAyudanteCampoLocal.setText(unPartido.getUnEquipoLocal().getUnAyudanteCampo().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnAyudanteCampo().getNombre());
        jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnEquipoLocal().getUnPreparadorFisico().getApellido() + ", " + unPartido.getUnEquipoLocal().getUnPreparadorFisico().getNombre());
        jTextFieldAyudanteDeMesaLocal.setText(unPartido.getNombreAyudanteMesaLocal());
        cargarCamposTablaControlando(unPartido.getUnEquipoLocal(), modeloTableLocal);

        //Visitante
        jTextFieldDTVisitante.setText(unPartido.getUnEquipoVisitante().getUnDT().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnDT().getNombre());
        jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnEquipoVisitante().getUnAyudanteCampo().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnAyudanteCampo().getNombre());
        jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnEquipoVisitante().getUnPreparadorFisico().getApellido() + ", " + unPartido.getUnEquipoVisitante().getUnPreparadorFisico().getNombre());
        jTextFieldAyudanteDeMesaVisitante.setText(unPartido.getNombreAyudanteMesaVisitante());
        cargarCamposTablaControlando(unPartido.getUnEquipoVisitante(), modeloTableVisitante);
    }

    public void InicioGuardado() {
        //Local
        jTextFieldDTLocal.setText(unPartido.getUnDTLocal().getApellido() + ", " + unPartido.getUnDTLocal().getNombre());
        jTextFieldAyudanteCampoLocal.setText(unPartido.getUnAyudanteCampoLocal().getApellido() + ", " + unPartido.getUnAyudanteCampoLocal().getNombre());
        jTextFieldPreparadorFisicoLocal.setText(unPartido.getUnPreparadorFisicoLocal().getApellido() + ", " + unPartido.getUnPreparadorFisicoLocal().getNombre());
        jTextFieldAyudanteDeMesaLocal.setText(unPartido.getNombreAyudanteMesaLocal());
        cargarCamposTabla(unPartido.getUnEquipoLocal(), modeloTableLocal, unPartido.getPlantelLocal());

        //Visitante
        jTextFieldDTVisitante.setText(unPartido.getUnDTVisitante().getApellido() + ", " + unPartido.getUnDTVisitante().getNombre());
        jTextFieldAyudanteCampoVisitante.setText(unPartido.getUnAyudanteCampoVisitante().getApellido() + ", " + unPartido.getUnAyudanteCampoVisitante().getNombre());
        jTextFieldPreparadorFisicoVisitante.setText(unPartido.getUnPreparadorFisicoVisitante().getApellido() + ", " + unPartido.getUnPreparadorFisicoVisitante().getNombre());
        jTextFieldAyudanteDeMesaVisitante.setText(unPartido.getNombreAyudanteMesaVisitante());
        cargarCamposTabla(unPartido.getUnEquipoVisitante(), modeloTableVisitante, unPartido.getPlantelVisitante());
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
                if (v1 == null){
                    v1.setTiempo("-");
                    v1.setMinuto("-");
                }
                if (v2 == null){
                    v2.setTiempo("-");
                    v2.setMinuto("-");
                }
                if (a1 == null){
                    a1.setTiempo("-");
                    a1.setMinuto("-");
                }
                if (a2 == null){
                    a2.setTiempo("-");
                    a2.setMinuto("-");
                }
                if (ra == null){
                    ra.setTiempo("-");
                    ra.setMinuto("-");
                }
                if (rd == null){
                    rd.setTiempo("-");
                    rd.setMinuto("-");
                }
            }
            modeloTable.addRow(new Object[]{unaSocia.getNumeroCamiseta(), unaSocia.getApellido() + ", " + unaSocia.getNombre(), 
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
        jButtonEditar1 = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
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
        jTextAreaDetalle = new javax.swing.JTextArea();
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
        jTableVisitante1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabelEquipoVisitante1 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableGolLocal2 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jButtonGolVisitante = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVisitante = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabelEquipoVisitante = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableGolLocal1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jButtonGol = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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

        jButtonEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Edit2.png"))); // NOI18N
        jButtonEditar1.setText("Editar");
        jButtonEditar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/cancel.png"))); // NOI18N
        jButtonEditar.setText("Cancelar");
        jButtonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanelBotonesLayout.createSequentialGroup()
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanelBotonesLayout.createSequentialGroup()
                        .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonEditar1)
                            .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonActualizar))
                        .addGap(3, 3, 3))))
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

        jLabel14.setText("Arbitro 2");

        jLabel13.setText("Arbitro 3");

        jLabel16.setText("Chancha");

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

        jTextAreaDetalle.setColumns(20);
        jTextAreaDetalle.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDetalle);

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

        jLabel17.setText("Ayudante de Campo");

        jLabel18.setText("Preparador Físico");

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

        jLabel21.setText("Ayudante de Campo");

        jLabel22.setText("Preparador Físico");

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
                {null, null, null, null, null, null, null, null, null, null, null, null, null, ""},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cam", "Apellido y Nombre", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true, true, true, true
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
            jTableLocal.getColumnModel().getColumn(0).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableLocal.getColumnModel().getColumn(2).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(2).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(3).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(3).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(4).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(4).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(5).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(5).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(5).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(6).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(6).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(6).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(7).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(7).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(7).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(8).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(8).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(8).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(9).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(9).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(9).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(10).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(10).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(10).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(11).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(11).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(11).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(12).setMinWidth(20);
            jTableLocal.getColumnModel().getColumn(12).setPreferredWidth(20);
            jTableLocal.getColumnModel().getColumn(12).setMaxWidth(20);
            jTableLocal.getColumnModel().getColumn(13).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(13).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(13).setMaxWidth(35);
        }

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

        jTableVisitante1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, ""},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cam", "Apellido y Nombre", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableVisitante1);
        if (jTableVisitante1.getColumnModel().getColumnCount() > 0) {
            jTableVisitante1.getColumnModel().getColumn(0).setMinWidth(35);
            jTableVisitante1.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableVisitante1.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableVisitante1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableVisitante1.getColumnModel().getColumn(2).setMinWidth(20);
            jTableVisitante1.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableVisitante1.getColumnModel().getColumn(2).setMaxWidth(20);
            jTableVisitante1.getColumnModel().getColumn(3).setMinWidth(35);
            jTableVisitante1.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableVisitante1.getColumnModel().getColumn(3).setMaxWidth(35);
            jTableVisitante1.getColumnModel().getColumn(4).setMinWidth(20);
            jTableVisitante1.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableVisitante1.getColumnModel().getColumn(4).setMaxWidth(20);
            jTableVisitante1.getColumnModel().getColumn(5).setMinWidth(35);
            jTableVisitante1.getColumnModel().getColumn(5).setPreferredWidth(35);
            jTableVisitante1.getColumnModel().getColumn(5).setMaxWidth(35);
            jTableVisitante1.getColumnModel().getColumn(6).setMinWidth(20);
            jTableVisitante1.getColumnModel().getColumn(6).setPreferredWidth(20);
            jTableVisitante1.getColumnModel().getColumn(6).setMaxWidth(20);
            jTableVisitante1.getColumnModel().getColumn(7).setMinWidth(35);
            jTableVisitante1.getColumnModel().getColumn(7).setPreferredWidth(35);
            jTableVisitante1.getColumnModel().getColumn(7).setMaxWidth(35);
            jTableVisitante1.getColumnModel().getColumn(8).setMinWidth(20);
            jTableVisitante1.getColumnModel().getColumn(8).setPreferredWidth(20);
            jTableVisitante1.getColumnModel().getColumn(8).setMaxWidth(20);
            jTableVisitante1.getColumnModel().getColumn(9).setMinWidth(35);
            jTableVisitante1.getColumnModel().getColumn(9).setPreferredWidth(35);
            jTableVisitante1.getColumnModel().getColumn(9).setMaxWidth(35);
            jTableVisitante1.getColumnModel().getColumn(10).setMinWidth(20);
            jTableVisitante1.getColumnModel().getColumn(10).setPreferredWidth(20);
            jTableVisitante1.getColumnModel().getColumn(10).setMaxWidth(20);
            jTableVisitante1.getColumnModel().getColumn(11).setMinWidth(35);
            jTableVisitante1.getColumnModel().getColumn(11).setPreferredWidth(35);
            jTableVisitante1.getColumnModel().getColumn(11).setMaxWidth(35);
            jTableVisitante1.getColumnModel().getColumn(12).setMinWidth(20);
            jTableVisitante1.getColumnModel().getColumn(12).setPreferredWidth(20);
            jTableVisitante1.getColumnModel().getColumn(12).setMaxWidth(20);
            jTableVisitante1.getColumnModel().getColumn(13).setMinWidth(35);
            jTableVisitante1.getColumnModel().getColumn(13).setPreferredWidth(35);
            jTableVisitante1.getColumnModel().getColumn(13).setMaxWidth(35);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Equipo Visitante: ");

        jLabelEquipoVisitante1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoVisitante1.setText("Nombre Equipo");

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
                        .addComponent(jLabelEquipoVisitante1)
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
                    .addComponent(jLabelEquipoVisitante1)
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

        jTableVisitante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, ""},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cam", "Apellido y Nombre", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min", "T", "Min"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableVisitante);
        if (jTableVisitante.getColumnModel().getColumnCount() > 0) {
            jTableVisitante.getColumnModel().getColumn(0).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableVisitante.getColumnModel().getColumn(2).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(2).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(3).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(3).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(4).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(4).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(5).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(5).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(5).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(6).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(6).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(6).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(7).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(7).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(7).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(8).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(8).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(8).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(9).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(9).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(9).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(10).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(10).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(10).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(11).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(11).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(11).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(12).setMinWidth(20);
            jTableVisitante.getColumnModel().getColumn(12).setPreferredWidth(20);
            jTableVisitante.getColumnModel().getColumn(12).setMaxWidth(20);
            jTableVisitante.getColumnModel().getColumn(13).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(13).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(13).setMaxWidth(35);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Equipo Visitante: ");

        jLabelEquipoVisitante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoVisitante.setText("Nombre Equipo");

        jTableGolLocal1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTableGolLocal1);
        if (jTableGolLocal1.getColumnModel().getColumnCount() > 0) {
            jTableGolLocal1.getColumnModel().getColumn(0).setMinWidth(35);
            jTableGolLocal1.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableGolLocal1.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableGolLocal1.getColumnModel().getColumn(1).setMinWidth(20);
            jTableGolLocal1.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableGolLocal1.getColumnModel().getColumn(1).setMaxWidth(20);
            jTableGolLocal1.getColumnModel().getColumn(2).setMinWidth(35);
            jTableGolLocal1.getColumnModel().getColumn(2).setPreferredWidth(35);
            jTableGolLocal1.getColumnModel().getColumn(2).setMaxWidth(35);
        }

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Goles");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonGol.setText("Agregar Gol");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoVisitante))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1282, 1282, 1282)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addComponent(jButtonGol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelEquipoVisitante)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGol))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipo Visitante: Nombre Equipo", jPanel6);

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
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEditar1;
    private javax.swing.JButton jButtonGol;
    private javax.swing.JButton jButtonGolLocal;
    private javax.swing.JButton jButtonGolVisitante;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabelEquipoVisitante1;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableGolLocal;
    private javax.swing.JTable jTableGolLocal1;
    private javax.swing.JTable jTableGolLocal2;
    private javax.swing.JTable jTableLocal;
    private javax.swing.JTable jTableVisitante;
    private javax.swing.JTable jTableVisitante1;
    private javax.swing.JTextArea jTextAreaDetalle;
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
