package Interfaces;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;
import DataSources.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class IGestionEquipo extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private JDesktopPane jDesktopPane1;
    private DefaultTableModel modeloTablaEquipo;

    public IGestionEquipo(ControladoraGlobal unaControladoraGlobal, JDesktopPane unjDesktopPanel) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.jDesktopPane1 = unjDesktopPanel;

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Equipoo.png")));

        IMenuPrincipalInterface.centrar(this);

        this.modeloTablaEquipo = (DefaultTableModel) jTableEquipo.getModel();

        this.SeleccionarObjetoTabla(false);

        filtrarEquipo("");
    }

    private void filtrarEquipo(String dato) {
        limpiarTabla(modeloTablaEquipo);
        List<Equipo> unaListaResultado = this.unaControladoraGlobal.getEquiposBDFiltro(dato);
        for (Equipo unEquipo : unaListaResultado) {
            this.modeloTablaEquipo.addRow(new Object[]{unEquipo.getIdEquipo(), unEquipo.getNombre(), unaControladoraGlobal.getClubBD(unEquipo), unEquipo.getUnaDelegada(), unEquipo.getUnaDelegadaSuplente(), unEquipo.getUnDT().getApellido() + ", " + unEquipo.getUnDT().getNombre()});
        }
    }

    private void limpiarTabla(DefaultTableModel modeloTablaEquipo) {
        try {
            int filas = modeloTablaEquipo.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTablaEquipo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    private void SeleccionarObjetoTabla(boolean estado) {
        jButtonDatos.setEnabled(estado);
        jButtonPlantel.setEnabled(estado);
        jButtonSancion.setEnabled(estado);
        jButtonContabilidad.setEnabled(estado);
        jButtonIndumentaria.setEnabled(estado);
        jButtonImprimir.setEnabled(estado);
        jButtonEliminar.setEnabled(estado);
        if (!estado) {
            jTableEquipo.clearSelection();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jPanelFiltro = new javax.swing.JPanel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEquipo = new javax.swing.JTable();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonSancion = new javax.swing.JButton();
        jButtonDatos = new javax.swing.JButton();
        jButtonPlantel = new javax.swing.JButton();
        jButtonContabilidad = new javax.swing.JButton();
        jButtonIndumentaria = new javax.swing.JButton();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(726, 544));
        setMinimumSize(new java.awt.Dimension(726, 544));
        setPreferredSize(new java.awt.Dimension(726, 544));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
                .addGap(3, 3, 3))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        jPanelFiltro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldBusqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBusquedaFocusGained(evt);
            }
        });
        jTextFieldBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBusquedaActionPerformed(evt);
            }
        });
        jTextFieldBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Filtro2.png"))); // NOI18N
        jLabel3.setText("Filtrar:");

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
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTableEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdEquipo", "Nombre", "Club", "Delegada", "Delegada Sup.", "Director Tecnico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEquipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableEquipoFocusGained(evt);
            }
        });
        jTableEquipo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTableEquipoComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEquipo);
        if (jTableEquipo.getColumnModel().getColumnCount() > 0) {
            jTableEquipo.getColumnModel().getColumn(0).setMinWidth(0);
            jTableEquipo.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableEquipo.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        jPanelBotones2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonSancion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/sanciones.png"))); // NOI18N
        jButtonSancion.setText("Sanciones");
        jButtonSancion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSancion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSancionActionPerformed(evt);
            }
        });

        jButtonDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Datos.png"))); // NOI18N
        jButtonDatos.setText("Datos");
        jButtonDatos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDatos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDatosActionPerformed(evt);
            }
        });

        jButtonPlantel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/plantel.png"))); // NOI18N
        jButtonPlantel.setText("Plantel");
        jButtonPlantel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPlantel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPlantel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlantelActionPerformed(evt);
            }
        });

        jButtonContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png"))); // NOI18N
        jButtonContabilidad.setText("Contabilidad");
        jButtonContabilidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonContabilidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonContabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContabilidadActionPerformed(evt);
            }
        });

        jButtonIndumentaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Equipo.png"))); // NOI18N
        jButtonIndumentaria.setText("Indumentaria");
        jButtonIndumentaria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonIndumentaria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonIndumentaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIndumentariaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotones2Layout = new javax.swing.GroupLayout(jPanelBotones2);
        jPanelBotones2.setLayout(jPanelBotones2Layout);
        jPanelBotones2Layout.setHorizontalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPlantel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSancion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonContabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonIndumentaria, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
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
                    .addComponent(jButtonIndumentaria))
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
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        DataSource datasource = new DataSource(this.unaControladoraGlobal, (List) this.unaControladoraGlobal.getEquiposBDFiltro(""));
        File archivo = new File("reportes/reporteTodosLosEquipos.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false); //generas tu visor del reporte
            jasperViewer.setVisible(true);
            
            //Para exportar a pdf
            /*JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportes/reporteClub"));
            exporter.exportReport();*/
          
        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonSancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSancionActionPerformed

    }//GEN-LAST:event_jButtonSancionActionPerformed

    private void jButtonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosActionPerformed
        Equipo unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquipo.getValueAt(jTableEquipo.getSelectedRow(), 0));
        IEquipo unEquipo = new IEquipo(unaControladoraGlobal, this, unEquipoSeleccionado);
        unEquipo.pack();
        unEquipo.setVisible(true);
        this.setVisible(false);
        this.jDesktopPane1.add(unEquipo);
    }//GEN-LAST:event_jButtonDatosActionPerformed

    private void jButtonContabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContabilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonContabilidadActionPerformed

    private void jButtonPlantelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlantelActionPerformed
        Equipo unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquipo.getValueAt(jTableEquipo.getSelectedRow(), 0));
        IPlantel unIPlantel = new IPlantel(unaControladoraGlobal, this, unEquipoSeleccionado);
        unIPlantel.pack();
        unIPlantel.setVisible(true);
        this.setVisible(false);
        this.jDesktopPane1.add(unIPlantel);          // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPlantelActionPerformed

    private void jButtonIndumentariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIndumentariaActionPerformed
        Equipo unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquipo.getValueAt(jTableEquipo.getSelectedRow(), 0));
        IIndumentaria unaIIndumentaria = new IIndumentaria(unaControladoraGlobal, this, unEquipoSeleccionado);
        unaIIndumentaria.pack();
        unaIIndumentaria.setVisible(true);
        this.setVisible(false);
        this.jDesktopPane1.add(unaIIndumentaria);         // TODO add your handling code here:
    }//GEN-LAST:event_jButtonIndumentariaActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        IEquipo unEquipo = new IEquipo(unaControladoraGlobal, this);
        unEquipo.pack();
        unEquipo.setVisible(true);
        this.setVisible(false);
        this.jDesktopPane1.add(unEquipo);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jTextFieldBusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaFocusGained
        this.SeleccionarObjetoTabla(false);
    }//GEN-LAST:event_jTextFieldBusquedaFocusGained

    private void jTextFieldBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBusquedaActionPerformed

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        filtrarEquipo(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void jTableEquipoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTableEquipoComponentShown
        filtrarEquipo("");
    }//GEN-LAST:event_jTableEquipoComponentShown

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        Equipo unEquipoSeleccionado = unaControladoraGlobal.getEquipoBD((Long) jTableEquipo.getValueAt(jTableEquipo.getSelectedRow(), 0));

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar al equipo: " + unEquipoSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarEquipo(unEquipoSeleccionado);

            jTextFieldBusqueda.setText("");
            filtrarEquipo("");
            this.SeleccionarObjetoTabla(false);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTableEquipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableEquipoFocusGained
        this.SeleccionarObjetoTabla(true);
    }//GEN-LAST:event_jTableEquipoFocusGained

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        filtrarEquipo(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonContabilidad;
    private javax.swing.JButton jButtonDatos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonIndumentaria;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonPlantel;
    private javax.swing.JButton jButtonSancion;
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
