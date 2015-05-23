package Interfaces;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import logicaNegocios.Equipo;
import logicaNegocios.PlanillaPago;

public class IHistoricoPagos extends javax.swing.JInternalFrame {

    private JInternalFrame unJInternalFrame;
    private DefaultTableModel modeloTable;
    private DateFormat df = DateFormat.getDateInstance();

    public IHistoricoPagos(JInternalFrame unJInternalFrame, Equipo unEquipo) {
        initComponents();
        
        IMenuPrincipal.jDesktopPane.add(this);
        IMenuPrincipal.centrarYalFrente(this);
        
        this.unJInternalFrame = unJInternalFrame;
        this.modeloTable = (DefaultTableModel) jTableHistorico.getModel();
        this.jTableHistorico.getTableHeader().setReorderingAllowed(false);

        //Icono de la ventana
        setFrameIcon(new ImageIcon(getClass().getResource("/Iconos Nuevos/HistorialPagos.png")));
        this.setTitle("Historial de Pagos Mensuales de: " + unEquipo.getNombre());

        for (PlanillaPago unaPlanillaPago : unEquipo.getPlanillasPagos()) {
            this.modeloTable.addRow(new Object[]{unaPlanillaPago.getId(), unaPlanillaPago.getRutaPDF(), df.format(unaPlanillaPago.getFechaPago()), unaPlanillaPago.getResponsablePago(), unaPlanillaPago.getNroRecibo(), unaPlanillaPago.getMonto()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorico = new javax.swing.JTable();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jTableHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Planilla", "rutaPDF", "Fecha de Pago", "Pagado por", "Nro Recibo", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableHistorico.getTableHeader().setReorderingAllowed(false);
        jTableHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHistoricoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableHistorico);
        if (jTableHistorico.getColumnModel().getColumnCount() > 0) {
            jTableHistorico.getColumnModel().getColumn(0).setMinWidth(90);
            jTableHistorico.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTableHistorico.getColumnModel().getColumn(0).setMaxWidth(90);
            jTableHistorico.getColumnModel().getColumn(1).setMinWidth(0);
            jTableHistorico.getColumnModel().getColumn(1).setPreferredWidth(0);
            jTableHistorico.getColumnModel().getColumn(1).setMaxWidth(0);
            jTableHistorico.getColumnModel().getColumn(2).setMinWidth(90);
            jTableHistorico.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTableHistorico.getColumnModel().getColumn(2).setMaxWidth(90);
            jTableHistorico.getColumnModel().getColumn(4).setMinWidth(90);
            jTableHistorico.getColumnModel().getColumn(4).setPreferredWidth(90);
            jTableHistorico.getColumnModel().getColumn(4).setMaxWidth(90);
            jTableHistorico.getColumnModel().getColumn(5).setMinWidth(90);
            jTableHistorico.getColumnModel().getColumn(5).setPreferredWidth(90);
            jTableHistorico.getColumnModel().getColumn(5).setMaxWidth(90);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableHistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHistoricoMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            if (jTableHistorico.getSelectedRow() > -1) {
                if (jTableHistorico.getValueAt(jTableHistorico.getSelectedRow(), 1) != null) {
                    String unPDF = (String) jTableHistorico.getValueAt(jTableHistorico.getSelectedRow(), 1);
                    try {
                        File path = new File(unPDF);
                        Desktop.getDesktop().open(path);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_jTableHistoricoMouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.unJInternalFrame.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    public void metodoParaRecorrerUnaCarpeta() {
        File directorio = new File("reportes/"); //Directorio donde queres que recorra para buscar los archivos
        String[] listaDirectorio = directorio.list();
        if (listaDirectorio == null) {
            System.out.println("No hay ficheros en el directorio especificado");
        } else {
            for (int x = 0; x < listaDirectorio.length; x++) {
                System.out.println(listaDirectorio[x]);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistorico;
    // End of variables declaration//GEN-END:variables
}
