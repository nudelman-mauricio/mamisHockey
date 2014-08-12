package Interfaces;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.PersonaAuxiliar;
import main.ControladoraGlobal;

public class IGestionPersonaAuxiliar extends javax.swing.JInternalFrame {

    ControladoraGlobal unaControladoraGlobal;
    private DefaultTableModel modeloTablaPersonaAuxiliar;

    public IGestionPersonaAuxiliar(ControladoraGlobal unaControladoraGlobal) {
        initComponents();

        this.unaControladoraGlobal = unaControladoraGlobal;
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/referee.png")));
        IMenuPrincipalInterface.centrar(this);

        this.modeloTablaPersonaAuxiliar = (DefaultTableModel) jTablePersonaAuxiliar.getModel();
        this.SeleccionarObjetoTabla(false);
        filtrarPersonaAuxiliar("");
    }
    
    private void filtrarPersonaAuxiliar(String dato) {
        limpiarTabla(modeloTablaPersonaAuxiliar);
        List<PersonaAuxiliar> unaListaResultado = this.unaControladoraGlobal.getPersonaAuxiliarBDFiltro(dato);
        for (PersonaAuxiliar unaPersonaAux : unaListaResultado) {
            this.modeloTablaPersonaAuxiliar.addRow(new Object[]{unaPersonaAux.getDni(), unaPersonaAux.getApellido(), unaPersonaAux.getNombre()});
        }
    }
    
    private void filtrarArbitros(String dato) {
        limpiarTabla(modeloTablaPersonaAuxiliar);
        List<PersonaAuxiliar> unaListaResultado = this.unaControladoraGlobal.getArbitrosBDFiltro(dato);
        for (PersonaAuxiliar unaPersonaAux : unaListaResultado) {
            this.modeloTablaPersonaAuxiliar.addRow(new Object[]{unaPersonaAux.getDni(), unaPersonaAux.getApellido(), unaPersonaAux.getNombre()});
        }
    }

    private void filtrarCuerpoTecnicos(String dato) {
        limpiarTabla(modeloTablaPersonaAuxiliar);
        List<PersonaAuxiliar> unaListaResultado = this.unaControladoraGlobal.getCuerposTecnicosBDFiltro(dato);
        for (PersonaAuxiliar unaPersonaAux : unaListaResultado) {
            this.modeloTablaPersonaAuxiliar.addRow(new Object[]{unaPersonaAux.getDni(), unaPersonaAux.getApellido(), unaPersonaAux.getNombre()});
        }
    }

    private void SeleccionarObjetoTabla(boolean estado) {
        jButtonDatos.setEnabled(estado);
        jButtonImprimir.setEnabled(estado);
        jButtonEliminar.setEnabled(estado);
        if (!estado) {
            jTablePersonaAuxiliar.clearSelection();
        }
    }
    
    private void limpiarTabla(DefaultTableModel modeloTablaPersonaAuxiliar) {
        try {
            int filas = modeloTablaPersonaAuxiliar.getRowCount();
            for (int i = 0; i < filas; i++) {
                modeloTablaPersonaAuxiliar.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanelBotones = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonNuevo = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jPanelFiltro = new javax.swing.JPanel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCheckBoxTodos = new javax.swing.JCheckBox();
        jCheckBoxCuerpoTecnicos = new javax.swing.JCheckBox();
        jCheckBoxArbitros = new javax.swing.JCheckBox();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePersonaAuxiliar = new javax.swing.JTable();
        jPanelBotones2 = new javax.swing.JPanel();
        jButtonDatos = new javax.swing.JButton();

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
        jTextFieldBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Filtro2.png"))); // NOI18N
        jLabel5.setText("Filtrar:");

        jCheckBoxTodos.setSelected(true);
        jCheckBoxTodos.setText("Todos");
        jCheckBoxTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTodosActionPerformed(evt);
            }
        });

        jCheckBoxCuerpoTecnicos.setText("Cuerpos Tecnicos");
        jCheckBoxCuerpoTecnicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCuerpoTecnicosActionPerformed(evt);
            }
        });

        jCheckBoxArbitros.setText("Arbitros");
        jCheckBoxArbitros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxArbitrosActionPerformed(evt);
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
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBoxTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxCuerpoTecnicos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxArbitros)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelFiltroLayout.setVerticalGroup(
            jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTodos)
                    .addComponent(jCheckBoxCuerpoTecnicos)
                    .addComponent(jCheckBoxArbitros)))
        );

        jTablePersonaAuxiliar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Apellido", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePersonaAuxiliar);
        jTablePersonaAuxiliar.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                SeleccionarObjetoTabla(true);
            }
        });

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        jPanelBotones2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Datos.png"))); // NOI18N
        jButtonDatos.setText("Datos");
        jButtonDatos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDatos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotones2Layout = new javax.swing.GroupLayout(jPanelBotones2);
        jPanelBotones2.setLayout(jPanelBotones2Layout);
        jPanelBotones2Layout.setHorizontalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotones2Layout.setVerticalGroup(
            jPanelBotones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotones2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonDatos)
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

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        IPersonaAuxiliar unaPersonaAuxiliar = new IPersonaAuxiliar(unaControladoraGlobal, this);
        unaPersonaAuxiliar.pack();
        unaPersonaAuxiliar.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaPersonaAuxiliar);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosActionPerformed
        PersonaAuxiliar unPersonaAuxSeleccionado = unaControladoraGlobal.getPersonaAuxiliarBD((Long) jTablePersonaAuxiliar.getValueAt(jTablePersonaAuxiliar.getSelectedRow(), 0));
        IPersonaAuxiliar unaIPersonaAuxiliar = new IPersonaAuxiliar(unaControladoraGlobal, this, unPersonaAuxSeleccionado);
        unaIPersonaAuxiliar.pack();
        unaIPersonaAuxiliar.setVisible(true);
        this.setVisible(false);
        IMenuPrincipalInterface.jDesktopPane.add(unaIPersonaAuxiliar);
    }//GEN-LAST:event_jButtonDatosActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        filtrarPersonaAuxiliar(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_formComponentShown

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        filtrarPersonaAuxiliar(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        PersonaAuxiliar unPersonaAuxSeleccionado = unaControladoraGlobal.getArbitroBD((Long) jTablePersonaAuxiliar.getValueAt(jTablePersonaAuxiliar.getSelectedRow(), 0));

        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Desea eliminar la persona auxiliar: " + unPersonaAuxSeleccionado.getApellido() + " " + unPersonaAuxSeleccionado.getNombre(),
                "Eliminar",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.eliminarPersonaAuxiliar(unPersonaAuxSeleccionado);
            jTextFieldBusqueda.setText("");
            filtrarPersonaAuxiliar("");
            this.SeleccionarObjetoTabla(false);
        }

    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jCheckBoxTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTodosActionPerformed
        jCheckBoxArbitros.setSelected(false);
        jCheckBoxCuerpoTecnicos.setSelected(false);
        filtrarPersonaAuxiliar(this.jTextFieldBusqueda.getText());
    }//GEN-LAST:event_jCheckBoxTodosActionPerformed

    private void jCheckBoxCuerpoTecnicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCuerpoTecnicosActionPerformed
        jCheckBoxArbitros.setSelected(false);
        jCheckBoxTodos.setSelected(false);
        filtrarCuerpoTecnicos(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_jCheckBoxCuerpoTecnicosActionPerformed

    private void jCheckBoxArbitrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxArbitrosActionPerformed
        jCheckBoxTodos.setSelected(false);
        jCheckBoxCuerpoTecnicos.setSelected(false);
        filtrarArbitros(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_jCheckBoxArbitrosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDatos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBoxArbitros;
    private javax.swing.JCheckBox jCheckBoxCuerpoTecnicos;
    private javax.swing.JCheckBox jCheckBoxTodos;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelBotones2;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePersonaAuxiliar;
    private javax.swing.JTextField jTextFieldBusqueda;
    // End of variables declaration//GEN-END:variables
}
