package Interfaces;

import DataSources.ClubDS;
import DataSources.Club_CanchaDS;
import DataSources.Club_EquipoDS;
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
import logicaNegocios.Club;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class IGestionClub extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaClub;
    private Club unClubSeleccionado = null;    

    public IGestionClub(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaClub = (DefaultTableModel) jTableClub.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Club.png")));
        this.setTitle("Gestión de Clubes");
        IMenuPrincipalInterface.centrar(this);
    }

    private void limpiarTabla() {
        int filas = this.modeloTablaClub.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaClub.removeRow(0);
        }
    }

    private void camposCargar() {
        if (jTableClub.getSelectedRow() > -1) {
            if (jTableClub.getValueAt(jTableClub.getSelectedRow(), 0) != null) {
                unClubSeleccionado = unaControladoraGlobal.getClubBD((Long) jTableClub.getValueAt(jTableClub.getSelectedRow(), 0));
                camposActivo(true);
            }
        }
    }

    private void camposActivo(boolean Editable) {
        jButtonCanchas.setEnabled(Editable);
        jButtonDatos.setEnabled(Editable);
        jButtonEquipos.setEnabled(Editable);
        jButtonEliminar.setEnabled(Editable);
        jButtonImprimir.setEnabled(Editable);
    }

    private void cargarTabla() {
        limpiarTabla();
        for (Club unClub : this.unaControladoraGlobal.getClubesBDFiltro(jTextFieldBusqueda.getText())) {
            this.modeloTablaClub.addRow(new Object[]{unClub.getIdClub(), unClub.getNombre(), unClub.getUnaLocalidad().getNombre(), unClub.getNombrePresidente()});
        }
        camposActivo(false);
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
        jLabel1 = new javax.swing.JLabel();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClub = new javax.swing.JTable();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonDatos = new javax.swing.JButton();
        jButtonEquipos = new javax.swing.JButton();
        jButtonCanchas = new javax.swing.JButton();

        setClosable(true);
        setTitle("Gestión de Clubes");
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
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonImprimir)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel1))
        );

        jTableClub.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdClub", "Nombre", "Localidad", "Presidente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableClub);
        if (jTableClub.getColumnModel().getColumnCount() > 0) {
            jTableClub.getColumnModel().getColumn(0).setMinWidth(0);
            jTableClub.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableClub.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableClub.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        jPanelBotones2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jButtonEquipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Equipoo.png"))); // NOI18N
        jButtonEquipos.setText("Equipos");
        jButtonEquipos.setEnabled(false);
        jButtonEquipos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEquipos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEquiposActionPerformed(evt);
            }
        });

        jButtonCanchas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Club.png"))); // NOI18N
        jButtonCanchas.setText("Canchas");
        jButtonCanchas.setEnabled(false);
        jButtonCanchas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCanchas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCanchas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCanchasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotones2Layout = new javax.swing.GroupLayout(jPanelBotones2);
        jPanelBotones2.setLayout(jPanelBotones2Layout);
        jPanelBotones2Layout.setHorizontalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCanchas, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotones2Layout.setVerticalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDatos)
                    .addComponent(jButtonCanchas)
                    .addComponent(jButtonEquipos))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotones2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        Club_EquipoDS unEquipoDS = new Club_EquipoDS(unClubSeleccionado.getEquipos());
        Club_CanchaDS unaCanchaDS = new Club_CanchaDS(unClubSeleccionado);        
        ClubDS unClubDS = new ClubDS(unClubSeleccionado);
        File archivo = new File("reportes/reporteClub.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            Map parameters = new HashMap();
            parameters.put("subreport_datasource_Equipo", unEquipoDS);
            parameters.put("subreport_datasource_Cancha", unaCanchaDS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, unClubDS);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        IClub unClub = new IClub(unaControladoraGlobal, this);
        unClub.pack();
        unClub.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unClub);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonCanchasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCanchasActionPerformed
        Club unClubSeleccionado = unaControladoraGlobal.getClubBD((Long) jTableClub.getValueAt(jTableClub.getSelectedRow(), 0));
        ICancha unaICancha = new ICancha(unaControladoraGlobal, this, unClubSeleccionado);
        unaICancha.pack();
        unaICancha.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaICancha);
    }//GEN-LAST:event_jButtonCanchasActionPerformed

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        cargarTabla();
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        jButtonNuevo.setEnabled(true);
        camposActivo(false);
        jTableClub.setEnabled(true);
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar el Club: " + unClubSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarClub(unClubSeleccionado);
            jTextFieldBusqueda.setText("");
            cargarTabla();
        }
        jTableClub.clearSelection();
        unClubSeleccionado = null;
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosActionPerformed
        IClub unIClub = new IClub(unaControladoraGlobal, this, unClubSeleccionado);
        unIClub.pack();
        unIClub.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIClub);
    }//GEN-LAST:event_jButtonDatosActionPerformed

    private void jButtonEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEquiposActionPerformed
        IClubEquipo unIClubEquipo = new IClubEquipo(unaControladoraGlobal, unClubSeleccionado, this);
        unIClubEquipo.pack();
        unIClubEquipo.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unIClubEquipo);
    }//GEN-LAST:event_jButtonEquiposActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        jTextFieldBusqueda.setText("");
        cargarTabla();
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCanchas;
    private javax.swing.JButton jButtonDatos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEquipos;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelBotones2;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClub;
    private javax.swing.JTextField jTextFieldBusqueda;
    // End of variables declaration//GEN-END:variables
}
