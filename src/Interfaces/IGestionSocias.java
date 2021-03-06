package Interfaces;

import DataSources.ListaSociasDS;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Socia;
import main.ControladoraGlobal;

public class IGestionSocias extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaSocia;
    private Socia unaSociaSeleccionada = null;

    public IGestionSocias(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        
        IMenuPrincipal.jDesktopPane.add(this);
        IMenuPrincipal.centrarYalFrente(this);
        
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaSocia = (DefaultTableModel) jTableSocias.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("/Iconos Nuevos/Socia2.png")));
        this.setTitle("Gestión de Socias");
        this.jTableSocias.getTableHeader().setReorderingAllowed(false);

        jTextFieldBusqueda.setText("");
        cargarTabla();
        camposActivo(false);
    }

    private void limpiarTabla() {
        int filas = modeloTablaSocia.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaSocia.removeRow(0);
        }
    }

    private void camposCargar() {
        if (jTableSocias.getSelectedRow() > -1) {
            if (jTableSocias.getValueAt(jTableSocias.getSelectedRow(), 0) != null) {
                unaSociaSeleccionada = unaControladoraGlobal.getSociaBD((Long) jTableSocias.getValueAt(jTableSocias.getSelectedRow(), 0));
                camposActivo(true);
            }
        }
    }

    private void camposActivo(boolean Editable) {
        jButtonEliminar.setEnabled(Editable);
        jButtonImprimir.setEnabled(Editable);
        jButtonDatos.setEnabled(Editable);
        jButtonTarjetas.setEnabled(Editable);
        jButtonPases.setEnabled(Editable);
        jButtonSancion.setEnabled(Editable);
        jButtonErgometria.setEnabled(Editable);
        jButtonEstado.setEnabled(Editable);
        jButtonContabilidad.setEnabled(Editable);
    }

    private void cargarTabla() {
        limpiarTabla();
        String ultimoEstado;
        for (Socia unaSocia : this.unaControladoraGlobal.getSociasBDFiltro(jTextFieldBusqueda.getText())) {
            if (unaSocia.getUltimoEstado() != null) {
                ultimoEstado = unaSocia.getUltimoEstado().getUnTipoEstado().getNombre();
            } else {
                ultimoEstado = "-";
            }
            this.modeloTablaSocia.addRow(new Object[]{unaSocia.getDni(), unaSocia.toString(), unaSocia.isExJugadora(), ultimoEstado, unaSocia.getEquipoActual()});
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonImprimir1 = new javax.swing.JButton();
        jPanelFiltro = new javax.swing.JPanel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSocias = new javax.swing.JTable();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonPases = new javax.swing.JButton();
        jButtonSancion = new javax.swing.JButton();
        jButtonTarjetas = new javax.swing.JButton();
        jButtonErgometria = new javax.swing.JButton();
        jButtonDatos = new javax.swing.JButton();
        jButtonEstado = new javax.swing.JButton();
        jButtonContabilidad = new javax.swing.JButton();

        setClosable(true);
        setTitle("Gestión de Socias");
        setMaximumSize(new java.awt.Dimension(900, 660));
        setMinimumSize(new java.awt.Dimension(900, 660));
        setPreferredSize(new java.awt.Dimension(900, 660));
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
        jButtonImprimir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimir1ActionPerformed(evt);
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Filtro2.png"))); // NOI18N
        jLabel1.setText("Filtrar:");

        javax.swing.GroupLayout jPanelFiltroLayout = new javax.swing.GroupLayout(jPanelFiltro);
        jPanelFiltro.setLayout(jPanelFiltroLayout);
        jPanelFiltroLayout.setHorizontalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBusqueda)
                .addContainerGap())
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTableSocias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Apellido, Nombres", "ExJugadora", "Estado", "Equipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableSocias);
        if (jTableSocias.getColumnModel().getColumnCount() > 0) {
            jTableSocias.getColumnModel().getColumn(0).setMinWidth(80);
            jTableSocias.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTableSocias.getColumnModel().getColumn(0).setMaxWidth(80);
            jTableSocias.getColumnModel().getColumn(2).setMinWidth(80);
            jTableSocias.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTableSocias.getColumnModel().getColumn(2).setMaxWidth(80);
            jTableSocias.getColumnModel().getColumn(3).setMinWidth(110);
            jTableSocias.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTableSocias.getColumnModel().getColumn(3).setMaxWidth(110);
        }
        jTableSocias.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
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

        jButtonPases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Transferencia.png"))); // NOI18N
        jButtonPases.setText("Pases");
        jButtonPases.setEnabled(false);
        jButtonPases.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPases.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasesActionPerformed(evt);
            }
        });

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

        jButtonTarjetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/tarjeta-roja-amarilla-verde.png"))); // NOI18N
        jButtonTarjetas.setText("Tarjetas");
        jButtonTarjetas.setEnabled(false);
        jButtonTarjetas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTarjetas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTarjetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTarjetasActionPerformed(evt);
            }
        });

        jButtonErgometria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/corazon.png"))); // NOI18N
        jButtonErgometria.setText("Ergometrias");
        jButtonErgometria.setEnabled(false);
        jButtonErgometria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonErgometria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonErgometria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonErgometriaActionPerformed(evt);
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

        jButtonEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Estados.png"))); // NOI18N
        jButtonEstado.setText("Estados");
        jButtonEstado.setEnabled(false);
        jButtonEstado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEstado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEstadoActionPerformed(evt);
            }
        });

        jButtonContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png"))); // NOI18N
        jButtonContabilidad.setText("Contabilidad");
        jButtonContabilidad.setEnabled(false);
        jButtonContabilidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonContabilidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonContabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContabilidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotones2Layout = new javax.swing.GroupLayout(jPanelBotones2);
        jPanelBotones2.setLayout(jPanelBotones2Layout);
        jPanelBotones2Layout.setHorizontalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPases, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSancion, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonErgometria, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonContabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanelBotones2Layout.setVerticalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonPases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTarjetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonErgometria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSancion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonContabilidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButtonContabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContabilidadActionPerformed
        IContabilidadSocia unaIContabilidadSocia = new IContabilidadSocia(unaControladoraGlobal, this, unaSociaSeleccionada);
        unaIContabilidadSocia.pack();
        unaIContabilidadSocia.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonContabilidadActionPerformed

    private void jButtonEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEstadoActionPerformed
        IEstado unIEstado = new IEstado(unaControladoraGlobal, this, unaSociaSeleccionada);
        unIEstado.pack();
        unIEstado.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonEstadoActionPerformed

    private void jButtonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosActionPerformed
        ISocia unaISocia = new ISocia(unaControladoraGlobal, this, unaSociaSeleccionada);
        unaISocia.pack();
        unaISocia.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonDatosActionPerformed


    private void jButtonErgometriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonErgometriaActionPerformed
        IErgometria unaIErgometria = new IErgometria(unaControladoraGlobal, this, unaSociaSeleccionada);
        unaIErgometria.pack();
        unaIErgometria.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonErgometriaActionPerformed

    private void jButtonTarjetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTarjetasActionPerformed
        ITarjeta unaITarjeta = new ITarjeta(unaControladoraGlobal, this, unaSociaSeleccionada);
        unaITarjeta.pack();
        unaITarjeta.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonTarjetasActionPerformed

    private void jButtonSancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSancionActionPerformed
        ISancion unaISancion = new ISancion(this, unaSociaSeleccionada, unaControladoraGlobal);
        unaISancion.pack();
        unaISancion.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSancionActionPerformed

    private void jButtonPasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasesActionPerformed
        IPase unIPase = new IPase(unaControladoraGlobal, this, unaSociaSeleccionada);
        unIPase.pack();
        unIPase.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonPasesActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        ISocia unaSocia = new ISocia(unaControladoraGlobal, this);
        unaSocia.pack();
        unaSocia.setVisible(true);
        this.setVisible(false);
        this.jTableSocias.clearSelection();
        unaSociaSeleccionada = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        cargarTabla();
        camposActivo(false);
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        int filaSeleccionada = jTableSocias.getSelectedRow();
        if (filaSeleccionada == -1) {
            jTextFieldBusqueda.setText("");
            cargarTabla();
            camposActivo(false);
        } else {
            cargarTabla();
            jTableSocias.getSelectionModel().setSelectionInterval(filaSeleccionada, filaSeleccionada);
        }
    }//GEN-LAST:event_formComponentShown

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar a la Socia: " + unaSociaSeleccionada.getApellido() + " " + unaSociaSeleccionada.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarSocia(unaSociaSeleccionada);
            jTextFieldBusqueda.setText("");
            cargarTabla();
            jTableSocias.clearSelection();
            unaSociaSeleccionada = null;
        }
        jButtonNuevo.setEnabled(true);
        camposActivo(false);
        jTableSocias.setEnabled(true);
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        IImprimirSocia unIImprimirSocia = new IImprimirSocia(unaControladoraGlobal, this, unaSociaSeleccionada);
        unIImprimirSocia.pack();
        unIImprimirSocia.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimir1ActionPerformed
        List<Socia> socias = new ArrayList();
        int filas = this.modeloTablaSocia.getRowCount();
        for (int i = 0; i < filas; i++) {
            socias.add(unaControladoraGlobal.getSociaBD((Long) jTableSocias.getValueAt(i, 0)));
        }
        ListaSociasDS unaListaSociasDS = new ListaSociasDS(unaControladoraGlobal, socias);
        unaListaSociasDS.verReporte();
    }//GEN-LAST:event_jButtonImprimir1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonContabilidad;
    private javax.swing.JButton jButtonDatos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonErgometria;
    private javax.swing.JButton jButtonEstado;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonImprimir1;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonPases;
    private javax.swing.JButton jButtonSancion;
    private javax.swing.JButton jButtonTarjetas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelBotones2;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSocias;
    private javax.swing.JTextField jTextFieldBusqueda;
    // End of variables declaration//GEN-END:variables
}
