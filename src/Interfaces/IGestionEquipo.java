package Interfaces;

import DataSources.EquipoDS;
import DataSources.Equipo_PlantelDS;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class IGestionEquipo extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaEquipo;
    private Equipo unEquipoSeleccionado = null;

    public IGestionEquipo(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaEquipo = (DefaultTableModel) jTableEquipo.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Equipoo.png")));
        this.setTitle("Gestión de Equipos");
        IMenuPrincipalInterface.centrar(this);
    }

    private void limpiarTabla() {
        int filas = this.modeloTablaEquipo.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaEquipo.removeRow(0);
        }
    }

    private void camposCargar() {
        if (jTableEquipo.getSelectedRow() > -1) {
            if (jTableEquipo.getValueAt(jTableEquipo.getSelectedRow(), 0) != null) {
                unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquipo.getValueAt(jTableEquipo.getSelectedRow(), 0));
                camposActivo(true);
            }
        }
    }

    private void camposActivo(boolean Editable) {
        jButtonDatos.setEnabled(Editable);
        jButtonPlantel.setEnabled(Editable);
        jButtonSancion.setEnabled(Editable);
        jButtonContabilidad.setEnabled(Editable);
        jButtonIndumentaria.setEnabled(Editable);
        jButtonPlanillaPagos.setEnabled(Editable);
        jButtonHistorialPagos.setEnabled(Editable);
        jButtonEliminar.setEnabled(Editable);
        jButtonImprimir.setEnabled(Editable);
    }

    private void cargarTabla() {
        limpiarTabla();
        for (Equipo unEquipo : this.unaControladoraGlobal.getEquiposBDFiltro(jTextFieldBusqueda.getText())) {
            this.modeloTablaEquipo.addRow(new Object[]{unEquipo.getIdEquipo(), unEquipo.getNombre(), unaControladoraGlobal.getClubBD(unEquipo), unEquipo.getUnaDelegada(), unEquipo.getUnDT().getApellido() + ", " + unEquipo.getUnDT().getNombre()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonImprimir1 = new javax.swing.JButton();
        jPanelFiltro = new javax.swing.JPanel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEquipo = new javax.swing.JTable();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonSancion = new javax.swing.JButton();
        jButtonDatos = new javax.swing.JButton();
        jButtonPlantel = new javax.swing.JButton();
        jButtonContabilidad = new javax.swing.JButton();
        jButtonIndumentaria = new javax.swing.JButton();
        jButtonPlanillaPagos = new javax.swing.JButton();
        jButtonHistorialPagos = new javax.swing.JButton();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(845, 660));
        setMinimumSize(new java.awt.Dimension(845, 660));
        setPreferredSize(new java.awt.Dimension(845, 660));
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
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jButtonImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/printer.png"))); // NOI18N
        jButtonImprimir1.setText("Imprimir Lista");
        jButtonImprimir1.setEnabled(false);
        jButtonImprimir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir1)
                .addGap(3, 3, 3))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonImprimir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        jPanelFiltro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldBusqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Filtro2.png"))); // NOI18N
        jLabel3.setText("Filtrar:");

        jLabel1.setText("* por nombre");

        javax.swing.GroupLayout jPanelFiltroLayout = new javax.swing.GroupLayout(jPanelFiltro);
        jPanelFiltro.setLayout(jPanelFiltroLayout);
        jPanelFiltroLayout.setHorizontalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBusqueda)
                .addContainerGap())
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(0, 0, 0))
        );

        jTableEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdEquipo", "Nombre", "Club", "Delegada", "Director Tecnico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEquipo);
        if (jTableEquipo.getColumnModel().getColumnCount() > 0) {
            jTableEquipo.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEquipo.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEquipo.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableEquipo.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );

        jPanelBotones2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonSancion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/sanciones.png"))); // NOI18N
        jButtonSancion.setText("Sanciones");
        jButtonSancion.setEnabled(false);
        jButtonSancion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSancion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSancionActionPerformed(evt);
            }
        });

        jButtonDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Datos.png"))); // NOI18N
        jButtonDatos.setText("Datos");
        jButtonDatos.setEnabled(false);
        jButtonDatos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDatos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDatosActionPerformed(evt);
            }
        });

        jButtonPlantel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/plantel.png"))); // NOI18N
        jButtonPlantel.setText("Plantel");
        jButtonPlantel.setEnabled(false);
        jButtonPlantel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPlantel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPlantel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlantelActionPerformed(evt);
            }
        });

        jButtonContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png"))); // NOI18N
        jButtonContabilidad.setText("Deudas");
        jButtonContabilidad.setEnabled(false);
        jButtonContabilidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonContabilidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonContabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContabilidadActionPerformed(evt);
            }
        });

        jButtonIndumentaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Equipo.png"))); // NOI18N
        jButtonIndumentaria.setText("Indumentaria");
        jButtonIndumentaria.setEnabled(false);
        jButtonIndumentaria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonIndumentaria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonIndumentaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIndumentariaActionPerformed(evt);
            }
        });

        jButtonPlanillaPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PanillaPagos.png"))); // NOI18N
        jButtonPlanillaPagos.setText("Planilla Pagos");
        jButtonPlanillaPagos.setEnabled(false);
        jButtonPlanillaPagos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPlanillaPagos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPlanillaPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlanillaPagosActionPerformed(evt);
            }
        });

        jButtonHistorialPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/HistorialPagos.png"))); // NOI18N
        jButtonHistorialPagos.setText("Historico Pagos");
        jButtonHistorialPagos.setEnabled(false);
        jButtonHistorialPagos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHistorialPagos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonHistorialPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorialPagosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotones2Layout = new javax.swing.GroupLayout(jPanelBotones2);
        jPanelBotones2.setLayout(jPanelBotones2Layout);
        jPanelBotones2Layout.setHorizontalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPlantel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSancion, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonIndumentaria, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonContabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPlanillaPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHistorialPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanelBotones2Layout.setVerticalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSancion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPlantel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonContabilidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonIndumentaria)
                    .addComponent(jButtonPlanillaPagos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHistorialPagos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotones2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
//        DEBERIA SER LA VENTANA!!!

//        IImprimirEquipo unIImprimirEquipo = new IImprimirEquipo(unaControladoraGlobal, this, unEquipoSeleccionado);
//        unIImprimirEquipo.pack();
//        unIImprimirEquipo.setVisible(true);
//        this.setVisible(false);
//        IMenuPrincipalInterface.jDesktopPane.add(unIImprimirEquipo);
//
//          NO BORRAR LO ANTERIOR
//        
        JOptionPane.showMessageDialog(this, "se tiene que cambiar de lugar este reporte");
        
        Equipo_PlantelDS unPlantelDS = new Equipo_PlantelDS(unEquipoSeleccionado.getPlantel());
        EquipoDS unEquipoDS = new EquipoDS(unaControladoraGlobal, unEquipoSeleccionado);
        File archivo = new File("reportes/reporteEquipo.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            Map parameters = new HashMap();
            parameters.put("subreport_datasource", unPlantelDS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, unEquipoDS);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonSancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSancionActionPerformed
        ISancion unaISancion = new ISancion(this, unEquipoSeleccionado, unaControladoraGlobal);
        unaISancion.pack();
        unaISancion.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaISancion);
    }//GEN-LAST:event_jButtonSancionActionPerformed

    private void jButtonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosActionPerformed
        IEquipo unEquipo = new IEquipo(unaControladoraGlobal, this, unEquipoSeleccionado);
        unEquipo.pack();
        unEquipo.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unEquipo);
    }//GEN-LAST:event_jButtonDatosActionPerformed

    private void jButtonContabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContabilidadActionPerformed
        IContabilidadEquipo unaIContabilidadEquipo = new IContabilidadEquipo(unaControladoraGlobal, this, unEquipoSeleccionado);
        unaIContabilidadEquipo.pack();
        unaIContabilidadEquipo.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaIContabilidadEquipo);
    }//GEN-LAST:event_jButtonContabilidadActionPerformed

    private void jButtonPlantelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlantelActionPerformed
        IPlantel unIPlantel = new IPlantel(unaControladoraGlobal, this, unEquipoSeleccionado);
        unIPlantel.pack();
        unIPlantel.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIPlantel);
    }//GEN-LAST:event_jButtonPlantelActionPerformed

    private void jButtonIndumentariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIndumentariaActionPerformed
        IIndumentaria unaIIndumentaria = new IIndumentaria(unaControladoraGlobal, this, unEquipoSeleccionado);
        unaIIndumentaria.pack();
        unaIIndumentaria.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaIIndumentaria);
    }//GEN-LAST:event_jButtonIndumentariaActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        IEquipo unEquipo = new IEquipo(unaControladoraGlobal, this);
        unEquipo.pack();
        unEquipo.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unEquipo);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        camposActivo(false);
        jTableEquipo.setEnabled(true);
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el Equipo: " + unEquipoSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarEquipo(unEquipoSeleccionado);
            jTextFieldBusqueda.setText("");
            cargarTabla();
        }
        jTableEquipo.clearSelection();
        unEquipoSeleccionado = null;
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        jTextFieldBusqueda.setText("");
        cargarTabla();
        camposActivo(false);
    }//GEN-LAST:event_formComponentShown

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        cargarTabla();
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void jButtonPlanillaPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlanillaPagosActionPerformed
        IPlanillaCobranza unaIPlanillaCobranza = new IPlanillaCobranza(unaControladoraGlobal, this, unEquipoSeleccionado);
        unaIPlanillaCobranza.pack();
        unaIPlanillaCobranza.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaIPlanillaCobranza);
    }//GEN-LAST:event_jButtonPlanillaPagosActionPerformed

    private void jButtonHistorialPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorialPagosActionPerformed
        IHistoricoPagos unaIHistoricoPagos = new IHistoricoPagos(unaControladoraGlobal, this, unEquipoSeleccionado);
        unaIHistoricoPagos.pack();
        unaIHistoricoPagos.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaIHistoricoPagos);
    }//GEN-LAST:event_jButtonHistorialPagosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonContabilidad;
    private javax.swing.JButton jButtonDatos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonHistorialPagos;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonImprimir1;
    private javax.swing.JButton jButtonIndumentaria;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonPlanillaPagos;
    private javax.swing.JButton jButtonPlantel;
    private javax.swing.JButton jButtonSancion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelBotones2;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEquipo;
    private javax.swing.JTextField jTextFieldBusqueda;
    // End of variables declaration//GEN-END:variables
}
