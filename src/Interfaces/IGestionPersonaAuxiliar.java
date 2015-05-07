package Interfaces;

import DataSources.ListaPersonaAuxiliarDS;
import DataSources.PersonaAuxiliarDS;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.PersonaAuxiliar;
import main.ControladoraGlobal;

public class IGestionPersonaAuxiliar extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaPersonaAuxiliar;
    private PersonaAuxiliar unaPersonaAuxiliarSeleccionado = null;

    public IGestionPersonaAuxiliar(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        IMenuPrincipalInterface.jDesktopPane.add(this);
        IMenuPrincipalInterface.centrarYalFrente(this);

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTablaPersonaAuxiliar = (DefaultTableModel) jTablePersonaAuxiliar.getModel();
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/referee.png")));
        this.setTitle("Gestión de Auxiliares");
        this.jTablePersonaAuxiliar.getTableHeader().setReorderingAllowed(false);

        jTextFieldBusqueda.setText("");
        cargarTabla();
        camposActivo(false);
    }

    private void limpiarTabla() {
        int filas = modeloTablaPersonaAuxiliar.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTablaPersonaAuxiliar.removeRow(0);
        }
    }

    private void cargarTabla() {
        limpiarTabla();
        List<PersonaAuxiliar> lista = null;
        if (jRadioButtonTodos.isSelected()) {
            lista = this.unaControladoraGlobal.getPersonaAuxiliarBDFiltro(jTextFieldBusqueda.getText());
        }
        if (jRadioButtonArbitros.isSelected()) {
            lista = this.unaControladoraGlobal.getArbitrosBDFiltro(jTextFieldBusqueda.getText());
        }
        if (jRadioButtonTecnicos.isSelected()) {
            lista = this.unaControladoraGlobal.getCuerposTecnicosBDFiltro(jTextFieldBusqueda.getText());
        }
        for (PersonaAuxiliar unaPersonaAux : lista) {
            this.modeloTablaPersonaAuxiliar.addRow(new Object[]{unaPersonaAux.getDni(), unaPersonaAux.getApellido(), unaPersonaAux.getNombre(), unaPersonaAux.isCuerpoTecnico(), unaPersonaAux.isArbitro(), unaPersonaAux.isPlantaPermanente()});
        }
        camposActivo(false);
    }

    private void camposCargar() {
        if (jTablePersonaAuxiliar.getSelectedRow() > -1) {
            if (jTablePersonaAuxiliar.getValueAt(jTablePersonaAuxiliar.getSelectedRow(), 0) != null) {
                unaPersonaAuxiliarSeleccionado = unaControladoraGlobal.getPersonaAuxiliarBD((Long) jTablePersonaAuxiliar.getValueAt(jTablePersonaAuxiliar.getSelectedRow(), 0));
                camposActivo(true);
            }
        }
    }

    private void camposActivo(boolean Editable) {
        jButtonDatos.setEnabled(Editable);
        jButtonSancion.setEnabled(Editable);
        jButtonActasConformidad.setEnabled(Editable);
        jButtonEliminar.setEnabled(Editable);
        jButtonImprimir.setEnabled(Editable);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonImprimir1 = new javax.swing.JButton();
        jPanelFiltro = new javax.swing.JPanel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jRadioButtonTodos = new javax.swing.JRadioButton();
        jRadioButtonArbitros = new javax.swing.JRadioButton();
        jRadioButtonTecnicos = new javax.swing.JRadioButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePersonaAuxiliar = new javax.swing.JTable();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonDatos = new javax.swing.JButton();
        jButtonSancion = new javax.swing.JButton();
        jButtonActasConformidad = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Filtro2.png"))); // NOI18N
        jLabel5.setText("Filtrar:");

        buttonGroup1.add(jRadioButtonTodos);
        jRadioButtonTodos.setSelected(true);
        jRadioButtonTodos.setText("Todos");
        jRadioButtonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTodosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonArbitros);
        jRadioButtonArbitros.setText("Árbitros");
        jRadioButtonArbitros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonArbitrosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonTecnicos);
        jRadioButtonTecnicos.setText("Técnicos");
        jRadioButtonTecnicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTecnicosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFiltroLayout = new javax.swing.GroupLayout(jPanelFiltro);
        jPanelFiltro.setLayout(jPanelFiltroLayout);
        jPanelFiltroLayout.setHorizontalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldBusqueda)
                    .addGroup(jPanelFiltroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonArbitros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonTecnicos)
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFiltroLayout.createSequentialGroup()
                        .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonTodos)
                            .addComponent(jRadioButtonArbitros)
                            .addComponent(jRadioButtonTecnicos)))
                    .addComponent(jLabel5)))
        );

        jTablePersonaAuxiliar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Apellido", "Nombre", "Técnico?", "Árbitro?", "Planta Perm?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePersonaAuxiliar);
        if (jTablePersonaAuxiliar.getColumnModel().getColumnCount() > 0) {
            jTablePersonaAuxiliar.getColumnModel().getColumn(0).setMinWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(0).setMaxWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(3).setMinWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(3).setMaxWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(4).setMinWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(4).setMaxWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(5).setMinWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTablePersonaAuxiliar.getColumnModel().getColumn(5).setMaxWidth(80);
        }
        jTablePersonaAuxiliar.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                camposCargar();
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
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

        jButtonActasConformidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/categoria.png"))); // NOI18N
        jButtonActasConformidad.setText("Actas Conf.");
        jButtonActasConformidad.setEnabled(false);
        jButtonActasConformidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonActasConformidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonActasConformidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActasConformidadActionPerformed(evt);
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
                .addComponent(jButtonSancion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonActasConformidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotones2Layout.setVerticalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonSancion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonActasConformidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        IPersonaAuxiliar unaPersonaAuxiliar = new IPersonaAuxiliar(unaControladoraGlobal, this);
        unaPersonaAuxiliar.pack();
        unaPersonaAuxiliar.setVisible(true);
        this.setVisible(false);
        jTablePersonaAuxiliar.clearSelection();
        unaPersonaAuxiliarSeleccionado = null;
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosActionPerformed
        IPersonaAuxiliar unaIPersonaAuxiliar = new IPersonaAuxiliar(unaControladoraGlobal, this, unaPersonaAuxiliarSeleccionado);
        unaIPersonaAuxiliar.pack();
        unaIPersonaAuxiliar.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonDatosActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        int filaSeleccionada = jTablePersonaAuxiliar.getSelectedRow();
        if (filaSeleccionada == -1) {
            jRadioButtonTodos.setSelected(true);
            jTextFieldBusqueda.setText("");
            cargarTabla();
            camposActivo(false);
        } else {
            cargarTabla();
            jTablePersonaAuxiliar.getSelectionModel().setSelectionInterval(filaSeleccionada, filaSeleccionada);
        }
    }//GEN-LAST:event_formComponentShown

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        cargarTabla();
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar al Auxiliar: " + unaPersonaAuxiliarSeleccionado.getApellido() + " " + unaPersonaAuxiliarSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarPersonaAuxiliar(unaPersonaAuxiliarSeleccionado);
            jTextFieldBusqueda.setText("");
            cargarTabla();
            jTablePersonaAuxiliar.clearSelection();
            unaPersonaAuxiliarSeleccionado = null;
            jRadioButtonTodos.setSelected(true);
        }
        jButtonNuevo.setEnabled(true);
        camposActivo(false);
        jTablePersonaAuxiliar.setEnabled(true);
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jRadioButtonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodosActionPerformed
        cargarTabla();
    }//GEN-LAST:event_jRadioButtonTodosActionPerformed

    private void jRadioButtonArbitrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonArbitrosActionPerformed
        cargarTabla();
    }//GEN-LAST:event_jRadioButtonArbitrosActionPerformed

    private void jRadioButtonTecnicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTecnicosActionPerformed
        cargarTabla();
    }//GEN-LAST:event_jRadioButtonTecnicosActionPerformed

    private void jButtonSancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSancionActionPerformed
        ISancion unaISancion = new ISancion(this, unaPersonaAuxiliarSeleccionado, unaControladoraGlobal);
        unaISancion.pack();
        unaISancion.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSancionActionPerformed

    private void jButtonImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimir1ActionPerformed
        List<PersonaAuxiliar> personasAuxiliares = new ArrayList();
        PersonaAuxiliar unaPersonaAux;
        int filas = this.modeloTablaPersonaAuxiliar.getRowCount();
        for (int i = 0; i < filas; i++) {
            unaPersonaAux = unaControladoraGlobal.getPersonaAuxiliarBD((Long) jTablePersonaAuxiliar.getValueAt(i, 0));
            personasAuxiliares.add(unaPersonaAux);
        }
        String opcion;
        if (jRadioButtonArbitros.isSelected()) {
            opcion = "Árbitros";
        } else {
            if (jRadioButtonTecnicos.isSelected()) {
                opcion = "Técnicos";
            } else {
                opcion = "Personas Auxiliares";
            }
        }
        ListaPersonaAuxiliarDS unaListaPersonaAuxiliarDS = new ListaPersonaAuxiliarDS(unaControladoraGlobal, personasAuxiliares, opcion);
        unaListaPersonaAuxiliarDS.verReporte(false);
    }//GEN-LAST:event_jButtonImprimir1ActionPerformed

    private void jButtonActasConformidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActasConformidadActionPerformed
        IActasCompromiso unaIActasCompromiso = new IActasCompromiso(this, unaControladoraGlobal, unaPersonaAuxiliarSeleccionado);
        unaIActasCompromiso.pack();
        unaIActasCompromiso.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonActasConformidadActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        PersonaAuxiliarDS unaPersonaAuxiliarDS = new PersonaAuxiliarDS(unaControladoraGlobal,unaPersonaAuxiliarSeleccionado);
        unaPersonaAuxiliarDS.verReporte();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonActasConformidad;
    private javax.swing.JButton jButtonDatos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonImprimir1;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonSancion;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelBotones2;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JRadioButton jRadioButtonArbitros;
    private javax.swing.JRadioButton jRadioButtonTecnicos;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePersonaAuxiliar;
    private javax.swing.JTextField jTextFieldBusqueda;
    // End of variables declaration//GEN-END:variables
}
