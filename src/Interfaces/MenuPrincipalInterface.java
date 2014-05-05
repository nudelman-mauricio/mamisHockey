/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Leanwit
 */
public class MenuPrincipalInterface extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipalInterface
     */
    public MenuPrincipalInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuSocias = new javax.swing.JMenu();
        jMenuItemAdmSocias = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuEquipo = new javax.swing.JMenu();
        jMenuItemAdmEquipos = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemDT = new javax.swing.JMenuItem();
        jMenuItemDelegadas = new javax.swing.JMenuItem();
        jMenuItemIndumentaria = new javax.swing.JMenuItem();
        jMenuItemPrepFisico = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemAdmSanciones = new javax.swing.JMenuItem();
        jMenuItemFallosTribunal = new javax.swing.JMenuItem();
        jMenuClub = new javax.swing.JMenu();
        jMenuItemAdmClubes = new javax.swing.JMenuItem();
        jMenuContabilidad = new javax.swing.JMenu();
        jMenuItemAdmIngresos = new javax.swing.JMenuItem();
        jMenuItemAdmEgresos = new javax.swing.JMenuItem();
        jMenuItemBalanceMensual = new javax.swing.JMenuItem();
        jMenuTorneo = new javax.swing.JMenu();
        jMenuItemAdmTorneo = new javax.swing.JMenuItem();
        jMenuItemEstadisticaTorneo = new javax.swing.JMenuItem();
        jMenuItemArbitros = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuArchivo.setText("Archivo");

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemSalir);

        jMenuBar1.add(jMenuArchivo);

        jMenuSocias.setText("Socias");

        jMenuItemAdmSocias.setText("Nueva Socia");
        jMenuItemAdmSocias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdmSociasActionPerformed(evt);
            }
        });
        jMenuSocias.add(jMenuItemAdmSocias);

        jMenuItem2.setText("Administrar Socia");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuSocias.add(jMenuItem2);

        jMenuBar1.add(jMenuSocias);

        jMenuEquipo.setText("Equipo");

        jMenuItemAdmEquipos.setText("Administrar Equipos");
        jMenuEquipo.add(jMenuItemAdmEquipos);

        jMenuItem1.setText("Plantilla");
        jMenuEquipo.add(jMenuItem1);

        jMenuItemDT.setText("Director Tecnico");
        jMenuItemDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDTActionPerformed(evt);
            }
        });
        jMenuEquipo.add(jMenuItemDT);

        jMenuItemDelegadas.setText("Delegadas");
        jMenuItemDelegadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDelegadasActionPerformed(evt);
            }
        });
        jMenuEquipo.add(jMenuItemDelegadas);

        jMenuItemIndumentaria.setText("Indumentaria");
        jMenuEquipo.add(jMenuItemIndumentaria);

        jMenuItemPrepFisico.setText("Preparador Fisico");
        jMenuEquipo.add(jMenuItemPrepFisico);

        jMenuBar1.add(jMenuEquipo);

        jMenu1.setText("Sanciones");

        jMenuItemAdmSanciones.setText("Administrar Sanciones");
        jMenu1.add(jMenuItemAdmSanciones);

        jMenuItemFallosTribunal.setText("FallosTribunal");
        jMenu1.add(jMenuItemFallosTribunal);

        jMenuBar1.add(jMenu1);

        jMenuClub.setText("Club");

        jMenuItemAdmClubes.setText("Administrar Clubes");
        jMenuClub.add(jMenuItemAdmClubes);

        jMenuBar1.add(jMenuClub);

        jMenuContabilidad.setText("Contabilidad");

        jMenuItemAdmIngresos.setText("Administrar Ingresos");
        jMenuContabilidad.add(jMenuItemAdmIngresos);

        jMenuItemAdmEgresos.setText("Administrar Egresos");
        jMenuContabilidad.add(jMenuItemAdmEgresos);

        jMenuItemBalanceMensual.setText("Balance Mensual");
        jMenuContabilidad.add(jMenuItemBalanceMensual);

        jMenuBar1.add(jMenuContabilidad);

        jMenuTorneo.setText("Torneo");

        jMenuItemAdmTorneo.setText("Administrar Torneo");
        jMenuItemAdmTorneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdmTorneoActionPerformed(evt);
            }
        });
        jMenuTorneo.add(jMenuItemAdmTorneo);

        jMenuItemEstadisticaTorneo.setText("Estadisticas");
        jMenuItemEstadisticaTorneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEstadisticaTorneoActionPerformed(evt);
            }
        });
        jMenuTorneo.add(jMenuItemEstadisticaTorneo);

        jMenuItemArbitros.setText("Arbitros");
        jMenuTorneo.add(jMenuItemArbitros);

        jMenuItem3.setText("Canchas");
        jMenuTorneo.add(jMenuItem3);

        jMenuBar1.add(jMenuTorneo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed

    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemAdmTorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmTorneoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemAdmTorneoActionPerformed

    private void jMenuItemEstadisticaTorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEstadisticaTorneoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemEstadisticaTorneoActionPerformed

    private void jMenuItemDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemDTActionPerformed

    private void jMenuItemDelegadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDelegadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemDelegadasActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemAdmSociasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmSociasActionPerformed
        InterfaceBuscarSocia unaInterfaceBuscarSocia = new InterfaceBuscarSocia();
        unaInterfaceBuscarSocia.setVisible(true);

    }//GEN-LAST:event_jMenuItemAdmSociasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("skins\blueMetalthemepack.zip"));
            UIManager.setLookAndFeel("com.12fprod.gui.plaf.skin.SkinLookAndFeel");
            
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuClub;
    private javax.swing.JMenu jMenuContabilidad;
    private javax.swing.JMenu jMenuEquipo;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemAdmClubes;
    private javax.swing.JMenuItem jMenuItemAdmEgresos;
    private javax.swing.JMenuItem jMenuItemAdmEquipos;
    private javax.swing.JMenuItem jMenuItemAdmIngresos;
    private javax.swing.JMenuItem jMenuItemAdmSanciones;
    private javax.swing.JMenuItem jMenuItemAdmSocias;
    private javax.swing.JMenuItem jMenuItemAdmTorneo;
    private javax.swing.JMenuItem jMenuItemArbitros;
    private javax.swing.JMenuItem jMenuItemBalanceMensual;
    private javax.swing.JMenuItem jMenuItemDT;
    private javax.swing.JMenuItem jMenuItemDelegadas;
    private javax.swing.JMenuItem jMenuItemEstadisticaTorneo;
    private javax.swing.JMenuItem jMenuItemFallosTribunal;
    private javax.swing.JMenuItem jMenuItemIndumentaria;
    private javax.swing.JMenuItem jMenuItemPrepFisico;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenu jMenuSocias;
    private javax.swing.JMenu jMenuTorneo;
    // End of variables declaration//GEN-END:variables
}
