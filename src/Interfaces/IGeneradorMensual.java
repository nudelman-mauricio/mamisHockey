package Interfaces;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.Mes;
import main.ControladoraGlobal;

public class IGeneradorMensual extends javax.swing.JInternalFrame {

    private ControladoraGlobal unaControladoraGlobal;
    private ConceptoDeportivo unConceptoDeportivoSeleccionado;
    private DefaultTableModel modeloTable;

    public IGeneradorMensual(ControladoraGlobal unaControladoraGlobal) {
        initComponents();
        setFrameIcon(new ImageIcon(getClass().getResource("../Iconos Nuevos/Contabilidad.png")));//Icono de la ventana        
        IMenuPrincipalInterface.centrar(this);//Centrar        
        this.setTitle("Generador Deudas Mensuales");//Titulo Ventana

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.modeloTable = (DefaultTableModel) jTableConceptos.getModel();
        cargarTabla();
        camposLimpiar();
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int filas = modeloTabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTabla.removeRow(0);
        }
    }

    //Cargar Tabla con los conceptos deportivos
    public void cargarTabla() {
        limpiarTabla(modeloTable);
        String unAfectado = "No Especificado";
        for (ConceptoDeportivo unConceptoDeportivo : unaControladoraGlobal.getConceptosDeportivosBD()) {
            if (unConceptoDeportivo.getUnTipoCancha() != null) {
                unAfectado = "Cancha: " + unConceptoDeportivo.getUnTipoCancha().getNombre();
            }
            if (unConceptoDeportivo.getUnTipoEstado() != null) {
                unAfectado = "Estado: " + unConceptoDeportivo.getUnTipoEstado().getNombre();
            }
            this.modeloTable.addRow(new Object[]{unConceptoDeportivo.getIdConceptoDeportivo(), unConceptoDeportivo.getConcepto(), unConceptoDeportivo.getMonto(), unAfectado});
            unAfectado = "No Especificado";
        }
    }

    //blanquea componentes editables
    void camposLimpiar() {
        jTextFieldConcepto.setText("");
        jTextFieldMonto.setText("");
        camposLimpiarFrecuencia();
    }

    void camposLimpiarFrecuencia() {
        jTextFieldAfectados.setText("");
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        jCheckBox5.setSelected(false);
        jCheckBox6.setSelected(false);
        jCheckBox7.setSelected(false);
        jCheckBox8.setSelected(false);
        jCheckBox9.setSelected(false);
        jCheckBox10.setSelected(false);
        jCheckBox11.setSelected(false);
        jCheckBox12.setSelected(false);
    }

    //cargar Check de Meses Frecuencia
    void setMeses() {
        for (Mes unMes : unConceptoDeportivoSeleccionado.getMeses()) {
            switch (unMes.getNombre()) {
                case "Enero":
                    jCheckBox1.setSelected(true);
                    break;
                case "Febrero":
                    jCheckBox2.setSelected(true);
                    break;
                case "Marzo":
                    jCheckBox3.setSelected(true);
                    break;
                case "Abril":
                    jCheckBox4.setSelected(true);
                    break;
                case "Mayo":
                    jCheckBox5.setSelected(true);
                    break;
                case "Junio":
                    jCheckBox6.setSelected(true);
                    break;
                case "Julio":
                    jCheckBox7.setSelected(true);
                    break;
                case "Agosto":
                    jCheckBox8.setSelected(true);
                    break;
                case "Septiembre":
                    jCheckBox9.setSelected(true);
                    break;
                case "Octubre":
                    jCheckBox10.setSelected(true);
                    break;
                case "Nobiembre":
                    jCheckBox11.setSelected(true);
                    break;
                case "Diciembre":
                    jCheckBox12.setSelected(true);
                    break;
            }
        }
    }

    //actualizar campos al seleccionar en la tabla
    void camposCargar() {
        if (jTableConceptos.getSelectedRow() > -1) {
            if (jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0) != null) {
                unConceptoDeportivoSeleccionado = unaControladoraGlobal.getConceptoDeportivoBD((Long) jTableConceptos.getValueAt(jTableConceptos.getSelectedRow(), 0));

                camposLimpiar();

                jTextFieldConcepto.setText(unConceptoDeportivoSeleccionado.getConcepto());
                jTextFieldMonto.setText(String.valueOf(unConceptoDeportivoSeleccionado.getMonto()));

                if (unConceptoDeportivoSeleccionado.getUnTipoCancha() != null) {
                    jTextFieldAfectados.setText(unConceptoDeportivoSeleccionado.getUnTipoCancha().toString());
                }
                if (unConceptoDeportivoSeleccionado.getUnTipoEstado() != null) {
                    jTextFieldAfectados.setText(unConceptoDeportivoSeleccionado.getUnTipoEstado().toString());
                }
                if (!unConceptoDeportivoSeleccionado.getMeses().isEmpty()) {
                    setMeses();
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotones = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jDateChooserFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabelFecha = new javax.swing.JLabel();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableConceptos = new javax.swing.JTable();
        jPanelDetalles = new javax.swing.JPanel();
        jLabelConcepto = new javax.swing.JLabel();
        jLabelMonto = new javax.swing.JLabel();
        jTextFieldConcepto = new javax.swing.JTextField();
        jTextFieldMonto = new javax.swing.JTextField();
        jLabelAfectados = new javax.swing.JLabel();
        jTextFieldAfectados = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabelMeses = new javax.swing.JLabel();

        jPanelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Config.png"))); // NOI18N
        jButtonNuevo.setText("Generar");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jLabelFecha.setText("Vencimiento: ");

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButtonNuevo)
                .addGap(3, 3, 3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTableConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Objeto", "Generar?", "Concepto Deportivo", "Monto ($)", "Le corresponde a"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableConceptos);
        if (jTableConceptos.getColumnModel().getColumnCount() > 0) {
            jTableConceptos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableConceptos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableConceptos.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableConceptos.getColumnModel().getColumn(1).setMinWidth(60);
            jTableConceptos.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableConceptos.getColumnModel().getColumn(1).setMaxWidth(60);
            jTableConceptos.getColumnModel().getColumn(3).setMinWidth(100);
            jTableConceptos.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableConceptos.getColumnModel().getColumn(3).setMaxWidth(100);
            jTableConceptos.getColumnModel().getColumn(4).setMinWidth(110);
            jTableConceptos.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTableConceptos.getColumnModel().getColumn(4).setMaxWidth(110);
        }

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanelDetalles.setName(""); // NOI18N

        jLabelConcepto.setText("Concepto Deportivo");

        jLabelMonto.setText("Monto ($)");

        jTextFieldConcepto.setEditable(false);

        jTextFieldMonto.setEditable(false);

        jLabelAfectados.setText("Le corresponde pagar a");

        jTextFieldAfectados.setEditable(false);

        jCheckBox1.setText("E");
        jCheckBox1.setEnabled(false);

        jCheckBox2.setText("F");
        jCheckBox2.setEnabled(false);

        jCheckBox3.setText("M");
        jCheckBox3.setEnabled(false);

        jCheckBox4.setText("A");
        jCheckBox4.setEnabled(false);

        jCheckBox5.setText("M");
        jCheckBox5.setEnabled(false);

        jCheckBox6.setText("J");
        jCheckBox6.setEnabled(false);
        jCheckBox6.setMaximumSize(new java.awt.Dimension(33, 23));
        jCheckBox6.setMinimumSize(new java.awt.Dimension(33, 23));

        jCheckBox8.setText("A");
        jCheckBox8.setEnabled(false);

        jCheckBox9.setText("S");
        jCheckBox9.setEnabled(false);

        jCheckBox10.setText("O");
        jCheckBox10.setEnabled(false);

        jCheckBox11.setText("N");
        jCheckBox11.setEnabled(false);

        jCheckBox12.setText("D");
        jCheckBox12.setEnabled(false);

        jCheckBox7.setText("J");
        jCheckBox7.setEnabled(false);

        jLabelMeses.setText("Meses a los que se aplica");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox9)
                        .addGap(4, 4, 4)
                        .addComponent(jCheckBox10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabelMeses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelMeses)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox11)
                    .addComponent(jCheckBox12))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanelDetallesLayout = new javax.swing.GroupLayout(jPanelDetalles);
        jPanelDetalles.setLayout(jPanelDetallesLayout);
        jPanelDetallesLayout.setHorizontalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetallesLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMonto)
                    .addComponent(jLabelConcepto)
                    .addComponent(jLabelAfectados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jTextFieldAfectados))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetallesLayout.setVerticalGroup(
            jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDetallesLayout.createSequentialGroup()
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelConcepto)
                            .addComponent(jTextFieldConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMonto)
                            .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAfectados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAfectados))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDetalles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        Object[] options = {"OK", "Cancelar"};
        if (0 == JOptionPane.showOptionDialog(
                this,
                "Confirme que desea agregar un registro de Acta de Compromiso firmada con fecha actual.",
                "Confirme",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options)) {
            unaControladoraGlobal.agregarActaCompromiso(unaPersonaAuxiliar, unaControladoraGlobal.fechaSistema());
            cargarTabla();
            this.jButtonNuevo.setEnabled(false);
        }
        
        
//        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
//        Object[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
//        String fechaVencimiento = String.valueOf(JOptionPane.showInputDialog(
//                this,
//                "Va a generar las deudas correspondientes al mes de " + sdf.format(unaControladoraGlobal.fechaSistema()).toUpperCase() + " del corriente año." + System.lineSeparator() + System.lineSeparator() + "Los conceptos a generar serán: " + System.lineSeparator() + "Cuota Socia" + System.lineSeparator() + "Cuota Jugadora" + System.lineSeparator() + "Cuota Licencia" + System.lineSeparator() + "Cuota Baja por Mora" + System.lineSeparator() + "Pase" + System.lineSeparator() + System.lineSeparator() + "Seleccione el día de vencimiento por favor:",
//                "Generar Deudas",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                dias,
//                "15"));
//        if (!fechaVencimiento.equals("null")) {
//            fechaVencimiento += "/" + (unaControladoraGlobal.fechaSistema().getMonth() + 1) + "/" + (unaControladoraGlobal.fechaSistema().getYear() + 1900);            
//            try {
//                unaControladoraGlobal.crearDeudasMensualesAutomaticas(new java.sql.Date(df.parse(fechaVencimiento).getTime()));
//                JOptionPane.showMessageDialog(this, "Se generaron las deudas Exitosamente. Gracias por aguardar.");
//            } catch (ParseException ex) {
//                Logger.getLogger(IMenuPrincipalInterface.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        
    }//GEN-LAST:event_jButtonNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private com.toedter.calendar.JDateChooser jDateChooserFechaVencimiento;
    private javax.swing.JLabel jLabelAfectados;
    private javax.swing.JLabel jLabelConcepto;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelMeses;
    private javax.swing.JLabel jLabelMonto;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableConceptos;
    private javax.swing.JTextField jTextFieldAfectados;
    private javax.swing.JTextField jTextFieldConcepto;
    private javax.swing.JTextField jTextFieldMonto;
    // End of variables declaration//GEN-END:variables
}
