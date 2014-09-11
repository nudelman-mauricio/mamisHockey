package Interfaces;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import logicaNegocios.Partido;
import logicaNegocios.Socia;
import logicaNegocios.TipoEstado;
import main.ControladoraGlobal;
import main.ImagenFondo;

public class IMenuPrincipalInterface extends javax.swing.JFrame {

    private ControladoraGlobal unaControladoraGlobal;

    public IMenuPrincipalInterface(ControladoraGlobal ControladoraGlobal) {
        initComponents();

        this.unaControladoraGlobal = ControladoraGlobal;

        //Icono de la ventana
        setIconImage(new ImageIcon(getClass().getResource("../Iconos Nuevos/Hockey.png")).getImage());

        //Setea fullscreen teniendo en cuenta el tamaño de la pantalla de windows sin el inicio
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //FONDO jDesktopPane
        jDesktopPane.setBorder(new ImagenFondo());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSocias = new javax.swing.JMenu();
        jMenuAuxiliares = new javax.swing.JMenu();
        jMenuTorneo = new javax.swing.JMenu();
        jMenuClub = new javax.swing.JMenu();
        jMenuEquipo = new javax.swing.JMenu();
        jMenuContabilidad = new javax.swing.JMenu();
        jMenuItemGestionarIngresos = new javax.swing.JMenuItem();
        jMenuItemGestionarEgresos = new javax.swing.JMenuItem();
        jMenuItemBalanceMensual = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemConceptosEgresos = new javax.swing.JMenuItem();
        jMenuItemConceptoIngreso = new javax.swing.JMenuItem();
        jMenuItemConceptosDeportivos = new javax.swing.JMenuItem();
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
        jMenuItemEstadosSocia = new javax.swing.JMenuItem();
        jMenuItemLocalidades = new javax.swing.JMenuItem();
        jMenuItemCategoria = new javax.swing.JMenuItem();
        jMenuItemTipoCancha = new javax.swing.JMenuItem();
        jMenuItemSkin = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asociación de Mami's Hockey");
        setBackground(new java.awt.Color(255, 0, 0));
        setExtendedState(1);

        jDesktopPane.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane.setForeground(new java.awt.Color(0, 0, 0));

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuSocias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Socia2.png"))); // NOI18N
        jMenuSocias.setText("Socias        ");
        jMenuSocias.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenuSocias.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuSocias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSociasMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuSocias);

        jMenuAuxiliares.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/referee.png"))); // NOI18N
        jMenuAuxiliares.setText("Auxiliares");
        jMenuAuxiliares.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenuAuxiliares.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuAuxiliares.setPreferredSize(new java.awt.Dimension(91, 32));
        jMenuAuxiliares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAuxiliaresMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuAuxiliares);
        jMenuAuxiliares.getAccessibleContext().setAccessibleName("       Arbitros     ");

        jMenuTorneo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Torneo.png"))); // NOI18N
        jMenuTorneo.setText("Torneos");
        jMenuTorneo.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuTorneo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuTorneoMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuTorneo);

        jMenuClub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Club.png"))); // NOI18N
        jMenuClub.setText("Clubes");
        jMenuClub.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuClub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuClubMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuClub);

        jMenuEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Equipoo.png"))); // NOI18N
        jMenuEquipo.setText("Equipos");
        jMenuEquipo.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuEquipoMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuEquipo);

        jMenuContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Contabilidad.png"))); // NOI18N
        jMenuContabilidad.setText("Contabilidad");
        jMenuContabilidad.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemGestionarIngresos.setText("Gestión Ingresos");
        jMenuItemGestionarIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionarIngresosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemGestionarIngresos);

        jMenuItemGestionarEgresos.setText("Gestión Egresos");
        jMenuItemGestionarEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionarEgresosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemGestionarEgresos);

        jMenuItemBalanceMensual.setText("Balance Mensual");
        jMenuItemBalanceMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBalanceMensualActionPerformed(evt);
            }
        });
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

        jMenuItemConceptosDeportivos.setText("Conceptos Deportivos");
        jMenuItemConceptosDeportivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConceptosDeportivosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemConceptosDeportivos);

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

        jMenuItemEstadosSocia.setText("Estados de una Socia");
        jMenuItemEstadosSocia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEstadosSociaActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemEstadosSocia);

        jMenuItemLocalidades.setText("Localidades");
        jMenuItemLocalidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLocalidadesActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemLocalidades);

        jMenuItemCategoria.setText("Categorias");
        jMenuItemCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCategoriaActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemCategoria);

        jMenuItemTipoCancha.setText("Tipos de Canchas");
        jMenuItemTipoCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTipoCanchaActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemTipoCancha);

        jMenuItemSkin.setText("Skin");
        jMenuItemSkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSkinActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemSkin);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Asistentes de Carga DB");

        jMenuItem2.setText("Estado\"Socia\"ASocias");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Asingar Equipo Aleatorio");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem6.setText("Asignar Ergometrias");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Salir.png"))); // NOI18N
        jMenuSalir.setText("Salir");
        jMenuSalir.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemGestionarEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarEgresosActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            IGestionEgresos unGestionEgresos = new IGestionEgresos(unaControladoraGlobal);
            unGestionEgresos.pack();
            unGestionEgresos.setVisible(true);
            this.jDesktopPane.add(unGestionEgresos);
        }
    }//GEN-LAST:event_jMenuItemGestionarEgresosActionPerformed

    private void jMenuItemConceptoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptoIngresoActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            IConceptoIngresos unConceptoIngresos = new IConceptoIngresos(unaControladoraGlobal);
            unConceptoIngresos.pack();
            unConceptoIngresos.setVisible(true);
            this.jDesktopPane.add(unConceptoIngresos);
        }
    }//GEN-LAST:event_jMenuItemConceptoIngresoActionPerformed

    private void jMenuItemConceptosEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptosEgresosActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            IConceptoEgresos unConceptoEgresos = new IConceptoEgresos(unaControladoraGlobal);
            unConceptoEgresos.pack();
            unConceptoEgresos.setVisible(true);
            this.jDesktopPane.add(unConceptoEgresos);
        }
    }//GEN-LAST:event_jMenuItemConceptosEgresosActionPerformed

    private void jMenuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSalirMouseClicked
        this.setVisible(false);
        this.dispose();              // TODO add your handling code here:
    }//GEN-LAST:event_jMenuSalirMouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed

    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItemSkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSkinActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            skin unaSkin = new skin(this);
            jDesktopPane.add(unaSkin);
            unaSkin.setVisible(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemSkinActionPerformed

    private void jMenuItemLocalidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLocalidadesActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            ILocalidad unLocalidad = new ILocalidad(unaControladoraGlobal);
            unLocalidad.pack();
            unLocalidad.setVisible(true);
            this.jDesktopPane.add(unLocalidad);
        }
    }//GEN-LAST:event_jMenuItemLocalidadesActionPerformed

    private void jMenuItemCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoriaActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            ICategoria unaCategoria = new ICategoria(unaControladoraGlobal);
            unaCategoria.pack();
            unaCategoria.setVisible(true);
            this.jDesktopPane.add(unaCategoria);
        }
    }//GEN-LAST:event_jMenuItemCategoriaActionPerformed

    private void jMenuItemEstadosSociaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEstadosSociaActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            IEstadoTipo unTipoEstado = new IEstadoTipo(unaControladoraGlobal, jDesktopPane);
            unTipoEstado.pack();
            unTipoEstado.setVisible(true);
            this.jDesktopPane.add(unTipoEstado);
        }
    }//GEN-LAST:event_jMenuItemEstadosSociaActionPerformed

    private void jMenuItemTipoCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTipoCanchaActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            ICanchaTipo unaVentanaTipoCancha = new ICanchaTipo(unaControladoraGlobal);
            unaVentanaTipoCancha.pack();
            unaVentanaTipoCancha.setVisible(true);
            this.jDesktopPane.add(unaVentanaTipoCancha);
        }
    }//GEN-LAST:event_jMenuItemTipoCanchaActionPerformed

    private void jMenuItemConceptosDeportivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptosDeportivosActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            IConceptosDeportivos unaVentanaConceptosDeportivos = new IConceptosDeportivos(unaControladoraGlobal);
            unaVentanaConceptosDeportivos.pack();
            unaVentanaConceptosDeportivos.setVisible(true);
            this.jDesktopPane.add(unaVentanaConceptosDeportivos);
        }
    }//GEN-LAST:event_jMenuItemConceptosDeportivosActionPerformed

    private void jMenuItemGestionarIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarIngresosActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            IGestionIngresos unaGestionIngresos = new IGestionIngresos(unaControladoraGlobal);
            unaGestionIngresos.pack();
            unaGestionIngresos.setVisible(true);
            this.jDesktopPane.add(unaGestionIngresos);
        }
    }//GEN-LAST:event_jMenuItemGestionarIngresosActionPerformed

    private void jMenuItemBalanceMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBalanceMensualActionPerformed
        if (jDesktopPane.getComponentCount() == 0) {
            IBalanceMensual unaIBalanceMensual = new IBalanceMensual(this.unaControladoraGlobal);
            unaIBalanceMensual.pack();
            unaIBalanceMensual.setVisible(true);
            this.jDesktopPane.add(unaIBalanceMensual);
        }
    }//GEN-LAST:event_jMenuItemBalanceMensualActionPerformed

    private void jMenuSociasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSociasMouseClicked
        if (jDesktopPane.getComponentCount() == 0) {
            IGestionSocias unaGestionSocias = new IGestionSocias(unaControladoraGlobal);
            unaGestionSocias.pack();
            unaGestionSocias.setVisible(true);
            this.jDesktopPane.add(unaGestionSocias);
        }
    }//GEN-LAST:event_jMenuSociasMouseClicked

    private void jMenuAuxiliaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAuxiliaresMouseClicked
        if (jDesktopPane.getComponentCount() == 0) {
            IGestionPersonaAuxiliar unaIGestionAuxiliares = new IGestionPersonaAuxiliar(unaControladoraGlobal);
            unaIGestionAuxiliares.pack();
            unaIGestionAuxiliares.setVisible(true);
            this.jDesktopPane.add(unaIGestionAuxiliares);
        }
    }//GEN-LAST:event_jMenuAuxiliaresMouseClicked

    private void jMenuClubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuClubMouseClicked
        if (jDesktopPane.getComponentCount() == 0) {
            IGestionClub unaGestionClub = new IGestionClub(this.unaControladoraGlobal);
            unaGestionClub.pack();
            unaGestionClub.setVisible(true);
            this.jDesktopPane.add(unaGestionClub);
        }
    }//GEN-LAST:event_jMenuClubMouseClicked

    private void jMenuEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEquipoMouseClicked
        if (jDesktopPane.getComponentCount() == 0) {
            IGestionEquipo unaGestionEquipo = new IGestionEquipo(this.unaControladoraGlobal);
            unaGestionEquipo.pack();
            unaGestionEquipo.setVisible(true);
            this.jDesktopPane.add(unaGestionEquipo);
        }
    }//GEN-LAST:event_jMenuEquipoMouseClicked

    private void jMenuTorneoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuTorneoMouseClicked
        if (jDesktopPane.getComponentCount() == 0) {
            IGestionTorneo unGestionTorneo = new IGestionTorneo(unaControladoraGlobal);
            unGestionTorneo.pack();
            unGestionTorneo.setVisible(true);
            this.jDesktopPane.add(unGestionTorneo);
        }
    }//GEN-LAST:event_jMenuTorneoMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

//Si no hay tipoEstadoSocia, lo crea
        TipoEstado unTipoEstadoSocia = null;
        for (TipoEstado unTipoEstado : unaControladoraGlobal.getTiposEstadosBD()) {
            if ("Socia".equals(unTipoEstado.getNombre())) {
                unTipoEstadoSocia = unTipoEstado;
            }
        }
        if (unTipoEstadoSocia == null) {
            unaControladoraGlobal.crearTipoEstado("Socia");
            for (TipoEstado unTipoEstado : unaControladoraGlobal.getTiposEstadosBD()) {
                if ("Socia".equals(unTipoEstado.getNombre())) {
                    unTipoEstadoSocia = unTipoEstado;
                }
            }
        }
        //A todas las socias que no tienen un estado, le asigna uno
        for (Socia unaSocia : unaControladoraGlobal.getSociasBD()) {
            if (unaSocia.getUltimoEstado() == null) {
                unaControladoraGlobal.crearEstado(unaSocia, unaSocia.getFechaIngreso(), unTipoEstadoSocia);
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        int Max = (unaControladoraGlobal.getEquiposBD().size()) + 1;
        int Min = 1;
        int aux;

        for (Socia unaSocia : unaControladoraGlobal.getSociasBD()) {
            aux = ((int) (Math.random() * (Max - Min)) + Min);
            unaControladoraGlobal.crearPase(unaSocia,
                    unaSocia.getFechaIngreso(), 0, 1,
                    unaSocia.getFechaIngreso(),
                    unaControladoraGlobal.getEquipoBD(Long.valueOf(String.valueOf(aux))), true, false, "");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        int Max = (10) + 1;
        int Min = 1;
        int aux;
        DateFormat df = DateFormat.getDateInstance();
        Date fechaVencimiento;
        try {
            fechaVencimiento = new java.sql.Date(df.parse("20/05/2019").getTime());
            for (Socia unaSocia : unaControladoraGlobal.getSociasBD()) {
                aux = ((int) (Math.random() * (Max - Min)) + Min);
                if (aux == 1) {
                    unaControladoraGlobal.crearErgometria(unaSocia, fechaVencimiento, unaSocia.getFechaIngreso(), false, "");
                } else {
                    unaControladoraGlobal.crearErgometria(unaSocia, fechaVencimiento, unaSocia.getFechaIngreso(), true, "");
                }

            }
        } catch (ParseException ex) {
            Logger.getLogger(IMenuPrincipalInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public static void centrar(JInternalFrame unJInternalFrame) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = unJInternalFrame.getSize();
        unJInternalFrame.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuAuxiliares;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuClub;
    private javax.swing.JMenu jMenuContabilidad;
    private javax.swing.JMenu jMenuEquipo;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemBalanceMensual;
    private javax.swing.JMenuItem jMenuItemCategoria;
    private javax.swing.JMenuItem jMenuItemConceptoIngreso;
    private javax.swing.JMenuItem jMenuItemConceptosDeportivos;
    private javax.swing.JMenuItem jMenuItemConceptosEgresos;
    private javax.swing.JMenuItem jMenuItemEstadosSocia;
    private javax.swing.JMenuItem jMenuItemGestionarEgresos;
    private javax.swing.JMenuItem jMenuItemGestionarIngresos;
    private javax.swing.JMenuItem jMenuItemLocalidades;
    private javax.swing.JMenuItem jMenuItemSkin;
    private javax.swing.JMenuItem jMenuItemTipoCancha;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JMenu jMenuSocias;
    private javax.swing.JMenu jMenuTorneo;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
