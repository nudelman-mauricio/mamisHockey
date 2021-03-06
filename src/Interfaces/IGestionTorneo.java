package Interfaces;

import DataSources.FixtureDS;
import DataSources.TablaGoleadorasDS;
import DataSources.TablaPosicionesDS;
import static com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler.BUFFER_SIZE;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;

public class IGestionTorneo extends javax.swing.JInternalFrame {

    private DefaultTableModel modeloTablaTorneo;
    private ControladoraGlobal unaControladoraGlobal;
    private Torneo unTorneoSeleccionado = null;
    private DateFormat df = DateFormat.getDateInstance();

    public IGestionTorneo(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        IMenuPrincipal.jDesktopPane.add(this);
        IMenuPrincipal.centrarYalFrente(this);
        
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaTorneo = (DefaultTableModel) jTableTorneo.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("/Iconos Nuevos/Torneo.png")));
        this.setTitle("Gestión de Torneos");
        this.jTableTorneo.getTableHeader().setReorderingAllowed(false);

        jTextFieldBusqueda.setText("");
        cargarTabla();
        camposActivo(false);
    }

    private void limpiarTabla() {
        int filas = modeloTablaTorneo.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaTorneo.removeRow(0);
        }
    }

    private void camposCargar() {
        if (jTableTorneo.getSelectedRow() > -1) {
            if (jTableTorneo.getValueAt(jTableTorneo.getSelectedRow(), 0) != null) {
                unTorneoSeleccionado = unaControladoraGlobal.getTorneoBD((Long) jTableTorneo.getValueAt(jTableTorneo.getSelectedRow(), 0));
                camposActivo(true);
                if (unTorneoSeleccionado.getEquiposInscriptos().isEmpty()) {
                    jButtonFechas.setEnabled(false);
                    jButtonTablaPosiciones.setEnabled(false);
                    jButtonGoleadoras.setEnabled(false);
                    jButtonFixtures.setEnabled(false);
                }
            }
        }
    }

    private void camposActivo(boolean Editable) {
        jButtonEliminar.setEnabled(Editable);
        //jButtonImprimir.setEnabled(Editable);
        jButtonDatos.setEnabled(Editable);
        jButtonEquipos.setEnabled(Editable);
        jButtonFechas.setEnabled(Editable);
        jButtonTablaPosiciones.setEnabled(Editable);
        jButtonGoleadoras.setEnabled(Editable);
        jButtonFixtures.setEnabled(Editable);
    }

    private void cargarTabla() {
        limpiarTabla();
        String torneoPadre;
        for (Torneo unTorneo : this.unaControladoraGlobal.getTorneosBDFiltro(jTextFieldBusqueda.getText())) {
            if (unTorneo.getUnTorneoPadre() == null) {
                torneoPadre = "---";
            } else {
                torneoPadre = unTorneo.getUnTorneoPadre().getNombre();
            }
            this.modeloTablaTorneo.addRow(new Object[]{unTorneo.getIdTorneo(), df.format(unTorneo.getFechaInicio()), unTorneo.getNombre(), torneoPadre, unTorneo.getUnaCategoria().getNombre(), unTorneo.getCantidadFechas(), unTorneo.getCantidadEquiposInscriptos()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jPanelFiltro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTorneo = new javax.swing.JTable();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonEquipos = new javax.swing.JButton();
        jButtonFechas = new javax.swing.JButton();
        jButtonDatos = new javax.swing.JButton();
        jButtonTablaPosiciones = new javax.swing.JButton();
        jButtonGoleadoras = new javax.swing.JButton();
        jButtonFixtures = new javax.swing.JButton();

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
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonEliminar))
                .addGap(3, 3, 3))
        );

        jPanelFiltro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Filtro2.png"))); // NOI18N
        jLabel1.setText("Filtrar:");

        jTextFieldBusqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelFiltroLayout = new javax.swing.GroupLayout(jPanelFiltro);
        jPanelFiltro.setLayout(jPanelFiltroLayout);
        jPanelFiltroLayout.setHorizontalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBusqueda)
                .addContainerGap())
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFiltroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTableTorneo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha de Inicio", "Nombre", "Torneo Padre", "Categoria", "Cantidad de Fechas", "Cantidad de Equipos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTorneo);
        if (jTableTorneo.getColumnModel().getColumnCount() > 0) {
            jTableTorneo.getColumnModel().getColumn(0).setMinWidth(0);
            jTableTorneo.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableTorneo.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        jTableTorneo.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        jPanelBotones2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jButtonFechas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Fechas.png"))); // NOI18N
        jButtonFechas.setText("Fechas");
        jButtonFechas.setEnabled(false);
        jButtonFechas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFechas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFechasActionPerformed(evt);
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

        jButtonTablaPosiciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Estados.png"))); // NOI18N
        jButtonTablaPosiciones.setText("Posiciones");
        jButtonTablaPosiciones.setEnabled(false);
        jButtonTablaPosiciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTablaPosiciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTablaPosiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTablaPosicionesActionPerformed(evt);
            }
        });

        jButtonGoleadoras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PlantillasPredeterminadas.png"))); // NOI18N
        jButtonGoleadoras.setText("Goleadoras");
        jButtonGoleadoras.setEnabled(false);
        jButtonGoleadoras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGoleadoras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGoleadoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoleadorasActionPerformed(evt);
            }
        });

        jButtonFixtures.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PlantillasPredeterminadas.png"))); // NOI18N
        jButtonFixtures.setText("Fixtures");
        jButtonFixtures.setEnabled(false);
        jButtonFixtures.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFixtures.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFixtures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFixturesActionPerformed(evt);
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
                .addComponent(jButtonEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTablaPosiciones, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGoleadoras, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFixtures, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotones2Layout.setVerticalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonEquipos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDatos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonTablaPosiciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGoleadoras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFixtures, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBotones2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEquiposActionPerformed
        ITorneoEquipos unIEquipoTorneo = new ITorneoEquipos(unaControladoraGlobal, this, unTorneoSeleccionado);
        unIEquipoTorneo.pack();
        unIEquipoTorneo.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonEquiposActionPerformed

    private void jButtonFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFechasActionPerformed
        ITorneoFechas unaFechaTorneo = new ITorneoFechas(unaControladoraGlobal, this, unTorneoSeleccionado);
        unaFechaTorneo.pack();
        unaFechaTorneo.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonFechasActionPerformed

    private void jButtonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosActionPerformed
        ITorneo unTorneo = new ITorneo(unaControladoraGlobal, this, unTorneoSeleccionado);
        unTorneo.pack();
        unTorneo.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonDatosActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        ITorneo unTorneo = new ITorneo(unaControladoraGlobal, this);
        unTorneo.pack();
        unTorneo.setVisible(true);
        this.setVisible(false);
        jTableTorneo.clearSelection();
        unTorneoSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        cargarTabla();
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar al Torneo: " + unTorneoSeleccionado.getNombre() + " de la categoria " + unTorneoSeleccionado.getUnaCategoria().getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarTorneo(unTorneoSeleccionado);
            jTextFieldBusqueda.setText("");
            cargarTabla();
            jTableTorneo.clearSelection();
            unTorneoSeleccionado = null;
        }
        jButtonNuevo.setEnabled(true);
        camposActivo(false);
        jTableTorneo.setEnabled(true);
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        int filaSeleccionada = jTableTorneo.getSelectedRow();
        if (filaSeleccionada == -1) {
            jTextFieldBusqueda.setText("");
            cargarTabla();
            camposActivo(false);
        } else {
            cargarTabla();
            jTableTorneo.getSelectionModel().setSelectionInterval(filaSeleccionada, filaSeleccionada);
        }
    }//GEN-LAST:event_formComponentShown

    private void jButtonTablaPosicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTablaPosicionesActionPerformed
        String rutaExcel = "";
        try {
            rutaExcel = unaControladoraGlobal.generarExcelTorneoPosiciones(unTorneoSeleccionado);
        } catch (IOException ex) {
            Logger.getLogger(IGestionTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        unaControladoraGlobal.subirAchivoFTP(rutaExcel);
        
        TablaPosicionesDS unaTablaPosicionesDS = new TablaPosicionesDS(unaControladoraGlobal, unTorneoSeleccionado);
        unaTablaPosicionesDS.verReporte();
    }//GEN-LAST:event_jButtonTablaPosicionesActionPerformed

    private void jButtonGoleadorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoleadorasActionPerformed
        TablaGoleadorasDS unaTablaGoleadoraDS = new TablaGoleadorasDS(unaControladoraGlobal, unTorneoSeleccionado);
        unaTablaGoleadoraDS.verReporte();
    }//GEN-LAST:event_jButtonGoleadorasActionPerformed

    private void jButtonFixturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFixturesActionPerformed
        FixtureDS unFixtureDS = new FixtureDS(unaControladoraGlobal, unTorneoSeleccionado, null);
        unFixtureDS.verReporte();
    }//GEN-LAST:event_jButtonFixturesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDatos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEquipos;
    private javax.swing.JButton jButtonFechas;
    private javax.swing.JButton jButtonFixtures;
    private javax.swing.JButton jButtonGoleadoras;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonTablaPosiciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelBotones2;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTorneo;
    private javax.swing.JTextField jTextFieldBusqueda;
    // End of variables declaration//GEN-END:variables
}
