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
        //jLabelEquipoVisitante.setText(unPartido.getUnEquipoVisitante().getNombre());

        this.modeloTableLocal = (DefaultTableModel) jTableLocal.getModel();
        cargarCamposTabla(unPartido.getUnEquipoLocal(), modeloTableLocal, unPartido.getPlantelLocal());
        //this.modeloTableVisitante = (DefaultTableModel) jTableVisitante.getModel();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLocal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelEquipoLocal = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableGolLocal = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLocal1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabelEquipoLocal1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableGolLocal1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

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
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel9))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipo Local: Nombre Equipo", jPanel5);

        jTableLocal1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableLocal1);
        if (jTableLocal1.getColumnModel().getColumnCount() > 0) {
            jTableLocal1.getColumnModel().getColumn(0).setMinWidth(35);
            jTableLocal1.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTableLocal1.getColumnModel().getColumn(0).setMaxWidth(35);
            jTableLocal1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableLocal1.getColumnModel().getColumn(2).setMinWidth(20);
            jTableLocal1.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableLocal1.getColumnModel().getColumn(2).setMaxWidth(20);
            jTableLocal1.getColumnModel().getColumn(3).setMinWidth(35);
            jTableLocal1.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableLocal1.getColumnModel().getColumn(3).setMaxWidth(35);
            jTableLocal1.getColumnModel().getColumn(4).setMinWidth(20);
            jTableLocal1.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableLocal1.getColumnModel().getColumn(4).setMaxWidth(20);
            jTableLocal1.getColumnModel().getColumn(5).setMinWidth(35);
            jTableLocal1.getColumnModel().getColumn(5).setPreferredWidth(35);
            jTableLocal1.getColumnModel().getColumn(5).setMaxWidth(35);
            jTableLocal1.getColumnModel().getColumn(6).setMinWidth(20);
            jTableLocal1.getColumnModel().getColumn(6).setPreferredWidth(20);
            jTableLocal1.getColumnModel().getColumn(6).setMaxWidth(20);
            jTableLocal1.getColumnModel().getColumn(7).setMinWidth(35);
            jTableLocal1.getColumnModel().getColumn(7).setPreferredWidth(35);
            jTableLocal1.getColumnModel().getColumn(7).setMaxWidth(35);
            jTableLocal1.getColumnModel().getColumn(8).setMinWidth(20);
            jTableLocal1.getColumnModel().getColumn(8).setPreferredWidth(20);
            jTableLocal1.getColumnModel().getColumn(8).setMaxWidth(20);
            jTableLocal1.getColumnModel().getColumn(9).setMinWidth(35);
            jTableLocal1.getColumnModel().getColumn(9).setPreferredWidth(35);
            jTableLocal1.getColumnModel().getColumn(9).setMaxWidth(35);
            jTableLocal1.getColumnModel().getColumn(10).setMinWidth(20);
            jTableLocal1.getColumnModel().getColumn(10).setPreferredWidth(20);
            jTableLocal1.getColumnModel().getColumn(10).setMaxWidth(20);
            jTableLocal1.getColumnModel().getColumn(11).setMinWidth(35);
            jTableLocal1.getColumnModel().getColumn(11).setPreferredWidth(35);
            jTableLocal1.getColumnModel().getColumn(11).setMaxWidth(35);
            jTableLocal1.getColumnModel().getColumn(12).setMinWidth(20);
            jTableLocal1.getColumnModel().getColumn(12).setPreferredWidth(20);
            jTableLocal1.getColumnModel().getColumn(12).setMaxWidth(20);
            jTableLocal1.getColumnModel().getColumn(13).setMinWidth(35);
            jTableLocal1.getColumnModel().getColumn(13).setPreferredWidth(35);
            jTableLocal1.getColumnModel().getColumn(13).setMaxWidth(35);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Equipo Visitante: ");

        jLabelEquipoLocal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEquipoLocal1.setText("Nombre Equipo");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEquipoLocal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelEquipoLocal1)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane7))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipo Visitantel: Nombre Equipo", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEditar1;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEquipoLocal;
    private javax.swing.JLabel jLabelEquipoLocal1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableGolLocal;
    private javax.swing.JTable jTableGolLocal1;
    private javax.swing.JTable jTableLocal;
    private javax.swing.JTable jTableLocal1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
