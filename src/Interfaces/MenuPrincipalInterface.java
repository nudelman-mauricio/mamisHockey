/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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
        //Icono de la ventana
        setIconImage(new ImageIcon(getClass().getResource("../Iconos Nuevos/Hockey.png")).getImage());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSocias = new javax.swing.JMenu();
        jMenuItemAdmSocias = new javax.swing.JMenuItem();
        jMenuItemAdministrarSocias = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemNuevoArbitro = new javax.swing.JMenuItem();
        jMenuItemGestionarArbitro = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNuevoCuerpoTecnico = new javax.swing.JMenuItem();
        jMenuItemGestionarCuerpoTecnico = new javax.swing.JMenuItem();
        jMenuTorneo = new javax.swing.JMenu();
        jMenuItemAdmTorneo = new javax.swing.JMenuItem();
        jMenuItemEstadisticaTorneo = new javax.swing.JMenuItem();
        jMenuClub = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItemAdmClubes = new javax.swing.JMenuItem();
        jMenuEquipo = new javax.swing.JMenu();
        jMenuItemAdmEquipos = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemDT = new javax.swing.JMenuItem();
        jMenuItemDelegadas = new javax.swing.JMenuItem();
        jMenuItemIndumentaria = new javax.swing.JMenuItem();
        jMenuItemPrepFisico = new javax.swing.JMenuItem();
        jMenuContabilidad = new javax.swing.JMenu();
        jMenuItemAdmIngresos = new javax.swing.JMenuItem();
        jMenuItemAdmEgresos = new javax.swing.JMenuItem();
        jMenuItemBalanceMensual = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asociación de Mami's Hockey");
        setExtendedState(1);
        setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuSocias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Socia2.png"))); // NOI18N
        jMenuSocias.setText("Socias        ");
        jMenuSocias.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenuSocias.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemAdmSocias.setText("Nueva Socia");
        jMenuItemAdmSocias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdmSociasActionPerformed(evt);
            }
        });
        jMenuSocias.add(jMenuItemAdmSocias);

        jMenuItemAdministrarSocias.setText("Gestionar Socias");
        jMenuItemAdministrarSocias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdministrarSociasActionPerformed(evt);
            }
        });
        jMenuSocias.add(jMenuItemAdministrarSocias);

        jMenuBar1.add(jMenuSocias);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/referee.png"))); // NOI18N
        jMenu3.setText("Arbitros");
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu3.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemNuevoArbitro.setText("Nuevo Arbitro");
        jMenu3.add(jMenuItemNuevoArbitro);

        jMenuItemGestionarArbitro.setText("Gestionar Arbitros");
        jMenuItemGestionarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionarArbitroActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemGestionarArbitro);

        jMenuBar1.add(jMenu3);
        jMenu3.getAccessibleContext().setAccessibleName("       Arbitros     ");

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/CuerpoTecnico.png"))); // NOI18N
        jMenu2.setText("Cpo. Tecnicos");
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu2.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemNuevoCuerpoTecnico.setText("Nuevo Cuerpo Tecnico");
        jMenuItemNuevoCuerpoTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoCuerpoTecnicoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemNuevoCuerpoTecnico);

        jMenuItemGestionarCuerpoTecnico.setText("Gestionar Cuerpos Tecnicos");
        jMenuItemGestionarCuerpoTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionarCuerpoTecnicoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemGestionarCuerpoTecnico);

        jMenuBar1.add(jMenu2);

        jMenuTorneo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Torneo.png"))); // NOI18N
        jMenuTorneo.setText("Torneos");
        jMenuTorneo.setMaximumSize(new java.awt.Dimension(129, 32767));

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

        jMenuBar1.add(jMenuTorneo);

        jMenuClub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Club.png"))); // NOI18N
        jMenuClub.setText("Clubes");
        jMenuClub.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItem2.setText("Nuevo Club");
        jMenuClub.add(jMenuItem2);

        jMenuItemAdmClubes.setText("Gestionar Clubes");
        jMenuItemAdmClubes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdmClubesActionPerformed(evt);
            }
        });
        jMenuClub.add(jMenuItemAdmClubes);

        jMenuBar1.add(jMenuClub);

        jMenuEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Equipoo.png"))); // NOI18N
        jMenuEquipo.setText("Equipos");
        jMenuEquipo.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemAdmEquipos.setText("Gestionar Equipos");
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

        jMenuContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png"))); // NOI18N
        jMenuContabilidad.setText("Contabilidad");
        jMenuContabilidad.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemAdmIngresos.setText("Gestionar Ingresos");
        jMenuContabilidad.add(jMenuItemAdmIngresos);

        jMenuItemAdmEgresos.setText("Gestionar Egresos");
        jMenuItemAdmEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdmEgresosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemAdmEgresos);

        jMenuItemBalanceMensual.setText("Balance Mensual");
        jMenuContabilidad.add(jMenuItemBalanceMensual);

        jMenuBar1.add(jMenuContabilidad);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Salir.png"))); // NOI18N
        jMenu4.setText("Salir");
        jMenu4.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemDTActionPerformed

    private void jMenuItemDelegadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDelegadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemDelegadasActionPerformed

    private void jMenuItemAdministrarSociasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdministrarSociasActionPerformed
        GestionSocias unaGestionSocias = new GestionSocias(jDesktopPane1);
        unaGestionSocias.pack();
        unaGestionSocias.setVisible(true); 
        centrar(unaGestionSocias);
        this.jDesktopPane1.add(unaGestionSocias);  
    }//GEN-LAST:event_jMenuItemAdministrarSociasActionPerformed

    private void jMenuItemAdmSociasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmSociasActionPerformed
        Socia unaSocia = new Socia(jDesktopPane1);
        unaSocia.pack();
        unaSocia.setVisible(true);
        centrar(unaSocia);
        this.jDesktopPane1.add(unaSocia);        
    }//GEN-LAST:event_jMenuItemAdmSociasActionPerformed

    private void jMenuItemAdmClubesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmClubesActionPerformed
        GestionClub unaGestionClub = new GestionClub(jDesktopPane1);
        unaGestionClub.pack();
        unaGestionClub.setVisible(true);
        centrar(unaGestionClub);
        this.jDesktopPane1.add(unaGestionClub);

    }//GEN-LAST:event_jMenuItemAdmClubesActionPerformed

    private void jMenuItemNuevoCuerpoTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoCuerpoTecnicoActionPerformed
        CuerpoTecnico unaCuerpoTecnico = new CuerpoTecnico(jDesktopPane1);
        unaCuerpoTecnico.pack();
        unaCuerpoTecnico.setVisible(true);
        centrar(unaCuerpoTecnico);
        this.jDesktopPane1.add(unaCuerpoTecnico);
    }//GEN-LAST:event_jMenuItemNuevoCuerpoTecnicoActionPerformed

    private void jMenuItemGestionarCuerpoTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarCuerpoTecnicoActionPerformed
        GestionCuerpoTecnico unGestionCuerpoTecnico = new GestionCuerpoTecnico(jDesktopPane1);
        unGestionCuerpoTecnico.pack();
        unGestionCuerpoTecnico.setVisible(true);
        centrar(unGestionCuerpoTecnico);
        this.jDesktopPane1.add(unGestionCuerpoTecnico);
    }//GEN-LAST:event_jMenuItemGestionarCuerpoTecnicoActionPerformed

    private void jMenuItemGestionarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarArbitroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemGestionarArbitroActionPerformed

    private void jMenuItemEstadisticaTorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEstadisticaTorneoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemEstadisticaTorneoActionPerformed

    private void jMenuItemAdmTorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmTorneoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemAdmTorneoActionPerformed

    private void jMenuItemAdmEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmEgresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemAdmEgresosActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4ActionPerformed

    public void centrar (JInternalFrame unJInternalFrame){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = unJInternalFrame.getSize();
        unJInternalFrame.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuClub;
    private javax.swing.JMenu jMenuContabilidad;
    private javax.swing.JMenu jMenuEquipo;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemAdmClubes;
    private javax.swing.JMenuItem jMenuItemAdmEgresos;
    private javax.swing.JMenuItem jMenuItemAdmEquipos;
    private javax.swing.JMenuItem jMenuItemAdmIngresos;
    private javax.swing.JMenuItem jMenuItemAdmSocias;
    private javax.swing.JMenuItem jMenuItemAdmTorneo;
    private javax.swing.JMenuItem jMenuItemAdministrarSocias;
    private javax.swing.JMenuItem jMenuItemBalanceMensual;
    private javax.swing.JMenuItem jMenuItemDT;
    private javax.swing.JMenuItem jMenuItemDelegadas;
    private javax.swing.JMenuItem jMenuItemEstadisticaTorneo;
    private javax.swing.JMenuItem jMenuItemGestionarArbitro;
    private javax.swing.JMenuItem jMenuItemGestionarCuerpoTecnico;
    private javax.swing.JMenuItem jMenuItemIndumentaria;
    private javax.swing.JMenuItem jMenuItemNuevoArbitro;
    private javax.swing.JMenuItem jMenuItemNuevoCuerpoTecnico;
    private javax.swing.JMenuItem jMenuItemPrepFisico;
    private javax.swing.JMenu jMenuSocias;
    private javax.swing.JMenu jMenuTorneo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
