package Interfaces;


import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import main.ControladoraGlobal;

public class IMenuPrincipalInterface extends javax.swing.JFrame {

    private ControladoraGlobal unaControladoraGlobal;
    
    private BufferedImage img;

    public IMenuPrincipalInterface(ControladoraGlobal ControladoraGlobal) {
        initComponents();
       
        this.unaControladoraGlobal = ControladoraGlobal;
        
        //Icono de la ventana
        setIconImage(new ImageIcon(getClass().getResource("../Iconos Nuevos/Hockey.png")).getImage());

        //Setea fullscreen teniendo en cuenta el tamaño de la pantalla de windows sin el inicio
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //FONDO jDesktopPane      - TODAVIA ME ESTA GANANDO - 
        //jDesktopPane.setBorder(new ImagenFondo());
        //https://www.youtube.com/watch?v=vrNc5bBbJ3g
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSocias = new javax.swing.JMenu();
        jMenuItemNuevaSocias = new javax.swing.JMenuItem();
        jMenuItemAdministrarSocias = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemNuevoArbitro = new javax.swing.JMenuItem();
        jMenuItemGestionarArbitro = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNuevoCuerpoTecnico = new javax.swing.JMenuItem();
        jMenuItemGestionarCuerpoTecnico = new javax.swing.JMenuItem();
        jMenuTorneo = new javax.swing.JMenu();
        jMenuItemGestionTorneo = new javax.swing.JMenuItem();
        jMenuItemEstadisticaTorneo = new javax.swing.JMenuItem();
        jMenuClub = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItemAdmClubes = new javax.swing.JMenuItem();
        jMenuEquipo = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItemAdmEquipos = new javax.swing.JMenuItem();
        jMenuContabilidad = new javax.swing.JMenu();
        jMenuItemGestionarIngresos = new javax.swing.JMenuItem();
        jMenuItemGestionarEgresos = new javax.swing.JMenuItem();
        jMenuItemBalanceMensual = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemConceptosEgresos = new javax.swing.JMenuItem();
        jMenuItemConceptoIngreso = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asociación de Mami's Hockey");
        setExtendedState(1);
        setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuSocias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Socia2.png"))); // NOI18N
        jMenuSocias.setText("Socias        ");
        jMenuSocias.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenuSocias.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemNuevaSocias.setText("Nueva Socia");
        jMenuItemNuevaSocias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevaSociasActionPerformed(evt);
            }
        });
        jMenuSocias.add(jMenuItemNuevaSocias);

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
        jMenuItemNuevoArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoArbitroActionPerformed(evt);
            }
        });
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

        jMenuItemGestionTorneo.setText("Gestionar Torneos");
        jMenuItemGestionTorneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionTorneoActionPerformed(evt);
            }
        });
        jMenuTorneo.add(jMenuItemGestionTorneo);

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

        jMenuItem6.setText("Nuevo Equipo");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuEquipo.add(jMenuItem6);

        jMenuItemAdmEquipos.setText("Gestionar Equipos");
        jMenuItemAdmEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdmEquiposActionPerformed(evt);
            }
        });
        jMenuEquipo.add(jMenuItemAdmEquipos);

        jMenuBar1.add(jMenuEquipo);

        jMenuContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png"))); // NOI18N
        jMenuContabilidad.setText("Contabilidad");
        jMenuContabilidad.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemGestionarIngresos.setText("Gestionar Ingresos");
        jMenuContabilidad.add(jMenuItemGestionarIngresos);

        jMenuItemGestionarEgresos.setText("Gestionar Egresos");
        jMenuItemGestionarEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionarEgresosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemGestionarEgresos);

        jMenuItemBalanceMensual.setText("Balance Mensual");
        jMenuContabilidad.add(jMenuItemBalanceMensual);
        jMenuContabilidad.add(jSeparator1);

        jMenuItemConceptosEgresos.setText("Conceptos Egresos");
        jMenuItemConceptosEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConceptosEgresosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemConceptosEgresos);

        jMenuItemConceptoIngreso.setText("Conceptos Ingreso");
        jMenuItemConceptoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConceptoIngresoActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemConceptoIngreso);

        jMenuItem3.setText("Conceptos Deportivos");
        jMenuContabilidad.add(jMenuItem3);

        jMenuBar1.add(jMenuContabilidad);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PlantillasPredeterminadas.png"))); // NOI18N
        jMenu1.setText("Formularios");
        jMenu1.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenu1.setPreferredSize(new java.awt.Dimension(111, 19));

        jMenuItem1.setText("Formulario de Pase");
        jMenu1.add(jMenuItem1);

        jMenuItem4.setText("Formulario Cambio de Estado");
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenu5.setText("Formulario Nuevo/a");

        jMenuItem5.setText("Socia");
        jMenu5.add(jMenuItem5);

        jMenuItem7.setText("Arbitros");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuItem8.setText("Cuerpo Tecnico");
        jMenu5.add(jMenuItem8);
        jMenu5.add(jSeparator3);

        jMenuItem10.setText("Club");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuItem9.setText("Equipo");
        jMenu5.add(jMenuItem9);

        jMenu1.add(jMenu5);

        jMenuBar1.add(jMenu1);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Config.png"))); // NOI18N
        jMenu4.setText("Configuración");
        jMenu4.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItem11.setText("Estados de una Socia");
        jMenu4.add(jMenuItem11);

        jMenuItem12.setText("Localidades");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText("Skin");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuBar1.add(jMenu4);

        jMenuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Salir.png"))); // NOI18N
        jMenuSalir.setText("Salir");
        jMenuSalir.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSalirMouseClicked(evt);
            }
        });
        jMenuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSalirActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenuSalir);

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

    private void jMenuItemAdministrarSociasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdministrarSociasActionPerformed
        IGestionSocias unaGestionSocias = new IGestionSocias(unaControladoraGlobal, jDesktopPane);
        unaGestionSocias.pack();
        unaGestionSocias.setVisible(true);
        this.jDesktopPane.add(unaGestionSocias);
    }//GEN-LAST:event_jMenuItemAdministrarSociasActionPerformed

    private void jMenuItemNuevaSociasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevaSociasActionPerformed
        ISocia unaSocia = new ISocia(unaControladoraGlobal, jDesktopPane);
        unaSocia.pack();
        unaSocia.setVisible(true);
        this.jDesktopPane.add(unaSocia);
    }//GEN-LAST:event_jMenuItemNuevaSociasActionPerformed

    private void jMenuItemAdmClubesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmClubesActionPerformed
        IGestionClub unaGestionClub = new IGestionClub(jDesktopPane);
        unaGestionClub.pack();
        unaGestionClub.setVisible(true);
        this.jDesktopPane.add(unaGestionClub);

    }//GEN-LAST:event_jMenuItemAdmClubesActionPerformed

    private void jMenuItemNuevoCuerpoTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoCuerpoTecnicoActionPerformed
        ICuerpoTecnico unaCuerpoTecnico = new ICuerpoTecnico(jDesktopPane);
        unaCuerpoTecnico.pack();
        unaCuerpoTecnico.setVisible(true);
        this.jDesktopPane.add(unaCuerpoTecnico);
    }//GEN-LAST:event_jMenuItemNuevoCuerpoTecnicoActionPerformed

    private void jMenuItemGestionarCuerpoTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarCuerpoTecnicoActionPerformed
        IGestionCuerpoTecnico unGestionCuerpoTecnico = new IGestionCuerpoTecnico(jDesktopPane);
        unGestionCuerpoTecnico.pack();
        unGestionCuerpoTecnico.setVisible(true);
        this.jDesktopPane.add(unGestionCuerpoTecnico);
    }//GEN-LAST:event_jMenuItemGestionarCuerpoTecnicoActionPerformed

    private void jMenuItemGestionarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarArbitroActionPerformed
        IGestionArbitros unaGestionArbitro = new IGestionArbitros(unaControladoraGlobal);
        unaGestionArbitro.pack();
        unaGestionArbitro.setVisible(true);
        this.jDesktopPane.add(unaGestionArbitro);
    }//GEN-LAST:event_jMenuItemGestionarArbitroActionPerformed

    private void jMenuItemEstadisticaTorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEstadisticaTorneoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemEstadisticaTorneoActionPerformed

    private void jMenuItemGestionTorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionTorneoActionPerformed
        IGestionTorneo unGestionTorneo = new IGestionTorneo();
        unGestionTorneo.pack();
        unGestionTorneo.setVisible(true);
        this.jDesktopPane.add(unGestionTorneo);
    }//GEN-LAST:event_jMenuItemGestionTorneoActionPerformed

    private void jMenuItemGestionarEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarEgresosActionPerformed
        IGestionEgresos unGestionEgresos = new IGestionEgresos(jDesktopPane);
        unGestionEgresos.pack();
        unGestionEgresos.setVisible(true);
        this.jDesktopPane.add(unGestionEgresos);
    }//GEN-LAST:event_jMenuItemGestionarEgresosActionPerformed

    private void jMenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSalirActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuSalirActionPerformed

    private void jMenuItemConceptoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptoIngresoActionPerformed
        IConceptoIngresos unConceptoIngresos = new IConceptoIngresos(jDesktopPane);
        unConceptoIngresos.pack();
        unConceptoIngresos.setVisible(true);
        this.jDesktopPane.add(unConceptoIngresos);
    }//GEN-LAST:event_jMenuItemConceptoIngresoActionPerformed

    private void jMenuItemConceptosEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptosEgresosActionPerformed
        IConceptoEgresos unConceptoEgresos = new IConceptoEgresos(jDesktopPane);
        unConceptoEgresos.pack();
        unConceptoEgresos.setVisible(true);
        this.jDesktopPane.add(unConceptoEgresos);
    }//GEN-LAST:event_jMenuItemConceptosEgresosActionPerformed

    private void jMenuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSalirMouseClicked
        this.setVisible(false);
        this.dispose();              // TODO add your handling code here:
    }//GEN-LAST:event_jMenuSalirMouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItemNuevoArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoArbitroActionPerformed
        IArbitro unArbitro = new IArbitro(this.unaControladoraGlobal);
        unArbitro.pack();
        unArbitro.setVisible(true);
        this.jDesktopPane.add(unArbitro);

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemNuevoArbitroActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        IEquipo unEquipo = new IEquipo(jDesktopPane);
        unEquipo.pack();
        unEquipo.setVisible(true);
        this.jDesktopPane.add(unEquipo);   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItemAdmEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmEquiposActionPerformed
        IGestionEquipo unaGestionEquipo = new IGestionEquipo(jDesktopPane);
        unaGestionEquipo.pack();
        unaGestionEquipo.setVisible(true);
        this.jDesktopPane.add(unaGestionEquipo);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemAdmEquiposActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        skin unaSkin = new skin(this);
        jDesktopPane.add(unaSkin);
        unaSkin.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ILocalidad unLocalidad = new ILocalidad(unaControladoraGlobal);
        unLocalidad.pack();
        unLocalidad.setVisible(true);
        this.jDesktopPane.add(unLocalidad);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    public static void centrar(JInternalFrame unJInternalFrame) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = unJInternalFrame.getSize();
        unJInternalFrame.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuClub;
    private javax.swing.JMenu jMenuContabilidad;
    private javax.swing.JMenu jMenuEquipo;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemAdmClubes;
    private javax.swing.JMenuItem jMenuItemAdmEquipos;
    private javax.swing.JMenuItem jMenuItemAdministrarSocias;
    private javax.swing.JMenuItem jMenuItemBalanceMensual;
    private javax.swing.JMenuItem jMenuItemConceptoIngreso;
    private javax.swing.JMenuItem jMenuItemConceptosEgresos;
    private javax.swing.JMenuItem jMenuItemEstadisticaTorneo;
    private javax.swing.JMenuItem jMenuItemGestionTorneo;
    private javax.swing.JMenuItem jMenuItemGestionarArbitro;
    private javax.swing.JMenuItem jMenuItemGestionarCuerpoTecnico;
    private javax.swing.JMenuItem jMenuItemGestionarEgresos;
    private javax.swing.JMenuItem jMenuItemGestionarIngresos;
    private javax.swing.JMenuItem jMenuItemNuevaSocias;
    private javax.swing.JMenuItem jMenuItemNuevoArbitro;
    private javax.swing.JMenuItem jMenuItemNuevoCuerpoTecnico;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JMenu jMenuSocias;
    private javax.swing.JMenu jMenuTorneo;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
