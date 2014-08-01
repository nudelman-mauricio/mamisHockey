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

    public void cargarCamposTabla(Equipo unEquipo, DefaultTableModel modeloTable, Collection<Socia> plantel) {
        limpiarTabla(modeloTable);
        String v1 = "-", v2 = "-", a1 = "-", a2 = "-", ra = "-", rd = "-";
        for (Socia unaSocia : plantel) {
            for (Tarjeta unTarjeta : unPartido.getTarjetas()) {
                if (unaSocia.getTarjetas().contains(unTarjeta)) {
                    if ("Verde".equals(unTarjeta.getTipo())) {
                        if (v1 == "-") {
                            v1 = unTarjeta.getMinuto();
                        } else {
                            if (Double.parseDouble(v1) < Double.parseDouble(unTarjeta.getMinuto())) {
                                v2 = unTarjeta.getMinuto();
                            } else {
                                v2 = v1;
                                v1 = unTarjeta.getMinuto();
                            }
                        }
                    }
                    if ("Amarrilla".equals(unTarjeta.getTipo())) {
                        if (a1 == "-") {
                            a1 = unTarjeta.getMinuto();
                        } else {
                            if (Double.parseDouble(a1) < Double.parseDouble(unTarjeta.getMinuto())) {
                                a2 = unTarjeta.getMinuto();
                            } else {
                                a2 = a1;
                                a1 = unTarjeta.getMinuto();
                            }
                        }
                    }
                    if ("Roja".equals(unTarjeta.getTipo())) {
                        rd = unTarjeta.getMinuto();
                    }
                }
                if ((!"-".equals(a1)) && (!"-".equals(a2))) {
                    ra = rd;
                    rd = "-";
                }
            }
            modeloTable.addRow(new Object[]{v1, v2, a1, a2, ra, rd, unaSocia.getNumeroCamiseta(), unaSocia.getApellido() + ", " + unaSocia.getNombre()});
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

        jPanel1 = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEditar1 = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLocal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelEquipoLocal = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableGolLocal = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVisitante = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabelEquipoVisitante = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableGolVisitante = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Edit2.png"))); // NOI18N
        jButtonEditar1.setText("Editar");
        jButtonEditar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/cancel.png"))); // NOI18N
        jButtonEditar.setText("Cancelar");
        jButtonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditar1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );

        jTableLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, "", null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "1° V", "2° V", "1° A", "2° A", "RA", "RD", "Cam", "Apellido y Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
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
            jTableLocal.getColumnModel().getColumn(1).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(1).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(1).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(2).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(2).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(2).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(3).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(3).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(4).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(4).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(4).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(5).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(5).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(5).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(6).setMinWidth(35);
            jTableLocal.getColumnModel().getColumn(6).setPreferredWidth(35);
            jTableLocal.getColumnModel().getColumn(6).setMaxWidth(35);
            jTableLocal.getColumnModel().getColumn(7).setPreferredWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Equipo Local: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Resultado Local: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("#");

        jLabelEquipoLocal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoLocal.setText("Nombre Equipo");

        jTableGolLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, ""},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cam", "Min"
            }
        ));
        jScrollPane4.setViewportView(jTableGolLocal);
        if (jTableGolLocal.getColumnModel().getColumnCount() > 0) {
            jTableGolLocal.getColumnModel().getColumn(0).setMinWidth(35);
            jTableGolLocal.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableGolLocal.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableGolLocal.getColumnModel().getColumn(1).setMinWidth(35);
            jTableGolLocal.getColumnModel().getColumn(1).setPreferredWidth(35);
            jTableGolLocal.getColumnModel().getColumn(1).setMaxWidth(35);
        }

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
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabelEquipoLocal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Ayudante de Mesa Local");

        jLabel8.setText("Ayudante de Mesa Visitante");

        jLabel9.setText("Observación");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel12.setText("Veedor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addContainerGap())
        );

        jTableVisitante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, "", null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "1° V", "2° V", "1° A", "2° A", "RA", "RD", "Cam", "Apellido y Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
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
            jTableVisitante.getColumnModel().getColumn(0).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(1).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(1).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(1).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(2).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(2).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(2).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(3).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(3).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(4).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(4).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(4).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(5).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(5).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(5).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(6).setMinWidth(35);
            jTableVisitante.getColumnModel().getColumn(6).setPreferredWidth(35);
            jTableVisitante.getColumnModel().getColumn(6).setMaxWidth(35);
            jTableVisitante.getColumnModel().getColumn(7).setPreferredWidth(150);
        }

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Equipo Visitante: ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Resultado Visitante: ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("#");

        jLabelEquipoVisitante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoVisitante.setText("Nombre Visitante");

        jTableGolVisitante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, ""},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cam", "Min"
            }
        ));
        jScrollPane6.setViewportView(jTableGolVisitante);
        if (jTableGolVisitante.getColumnModel().getColumnCount() > 0) {
            jTableGolVisitante.getColumnModel().getColumn(0).setMinWidth(35);
            jTableGolVisitante.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableGolVisitante.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableGolVisitante.getColumnModel().getColumn(1).setMinWidth(35);
            jTableGolVisitante.getColumnModel().getColumn(1).setPreferredWidth(35);
            jTableGolVisitante.getColumnModel().getColumn(1).setMaxWidth(35);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoVisitante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabelEquipoVisitante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEditar1;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEquipoLocal;
    private javax.swing.JLabel jLabelEquipoVisitante;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableGolLocal;
    private javax.swing.JTable jTableGolVisitante;
    private javax.swing.JTable jTableLocal;
    private javax.swing.JTable jTableVisitante;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
