package Interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import logicaNegocios.Socia;
import logicaNegocios.TipoEstado;
import main.ControladoraGlobal;

public class IMenuPrincipalInterface extends javax.swing.JFrame {

    private final ControladoraGlobal unaControladoraGlobal;
    private final DateFormat df = DateFormat.getDateInstance();

    public IMenuPrincipalInterface(ControladoraGlobal ControladoraGlobal) {
        initComponents();

        this.unaControladoraGlobal = ControladoraGlobal;
        try {
            //Icono de la ventana
            setIconImage(new ImageIcon(getClass().getResource("/Iconos Nuevos/Hockey.png")).getImage());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "ICONO", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            //Setea fullscreen teniendo en cuenta el tamaño de la pantalla de windows sin el inicio
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            this.setMaximizedBounds(env.getMaximumWindowBounds());
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        /**
         * Momentaneamente Desactiado para no alargar mas la entrerga. Recordar
         * que los formulasrios esos no pueden ser inventados. Hay que acordar
         * con la comision directiva
         */
        jMenuFormularios.setVisible(false);

        //FONDO jDesktopPane
        jDesktopPane.setBorder(new ImagenFondo());
        
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "MAS", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="CLASE Imagen Fondo">
    public class ImagenFondo implements Border {

        public BufferedImage back;

        public ImagenFondo() {
            try {
                URL imagePath = new URL(getClass().getResource("/Iconos Nuevos/Fondo Mamis.png").toString());
                back = ImageIO.read(imagePath);
            } catch (IOException ex) {
            }
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawImage(back, (x + (width - back.getWidth()) / 2), (y + (height - back.getHeight()) / 2), null);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 0, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSocias = new javax.swing.JMenu();
        jMenuAuxiliares = new javax.swing.JMenu();
        jMenuTorneo = new javax.swing.JMenu();
        jMenuClub = new javax.swing.JMenu();
        jMenuEquipo = new javax.swing.JMenu();
        jMenuContabilidad = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItemGestionarIngresos = new javax.swing.JMenuItem();
        jMenuItemGestionarEgresos = new javax.swing.JMenuItem();
        jMenuItemBalanceMensual = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemConceptoIngreso = new javax.swing.JMenuItem();
        jMenuItemConceptosDeportivos = new javax.swing.JMenuItem();
        jMenuItemConceptosEgresos = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCuotaMensual = new javax.swing.JMenuItem();
        jMenuFormularios = new javax.swing.JMenu();
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
        jMenuConfiguracion = new javax.swing.JMenu();
        jMenuItemEstadosSocia = new javax.swing.JMenuItem();
        jMenuItemLocalidades = new javax.swing.JMenuItem();
        jMenuItemCategoria = new javax.swing.JMenuItem();
        jMenuItemTipoCancha = new javax.swing.JMenuItem();
        jMenuCargaDB = new javax.swing.JMenu();
        jMenuItemLos3 = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asociación de Mami's Hockey");
        setBackground(new java.awt.Color(255, 0, 0));
        setExtendedState(1);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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

        jMenuItem2.setText("Gestión Ingresos a Futuro");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItem2);

        jMenuItemGestionarIngresos.setText("Gestión Ingresos Otro");
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

        jMenuItemConceptoIngreso.setText("Conceptos Ingreso Otro");
        jMenuItemConceptoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConceptoIngresoActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemConceptoIngreso);

        jMenuItemConceptosDeportivos.setText("Conceptos Ingreso Deportivo");
        jMenuItemConceptosDeportivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConceptosDeportivosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemConceptosDeportivos);

        jMenuItemConceptosEgresos.setText("Conceptos Egresos");
        jMenuItemConceptosEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConceptosEgresosActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemConceptosEgresos);
        jMenuContabilidad.add(jSeparator4);

        jMenuItemCuotaMensual.setText("Generar Cuota Mensual");
        jMenuItemCuotaMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCuotaMensualActionPerformed(evt);
            }
        });
        jMenuContabilidad.add(jMenuItemCuotaMensual);

        jMenuBar1.add(jMenuContabilidad);

        jMenuFormularios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/PlantillasPredeterminadas.png"))); // NOI18N
        jMenuFormularios.setText("Formularios");
        jMenuFormularios.setMaximumSize(new java.awt.Dimension(129, 32767));
        jMenuFormularios.setPreferredSize(new java.awt.Dimension(111, 19));

        jMenuItem1.setText("Formulario de Pase");
        jMenuFormularios.add(jMenuItem1);

        jMenuItem4.setText("Formulario Cambio de Estado");
        jMenuFormularios.add(jMenuItem4);
        jMenuFormularios.add(jSeparator2);

        jMenu5.setText("Formulario Nuevo/a");

        jMenuItem5.setText("Socia");
        jMenu5.add(jMenuItem5);

        jMenuItem7.setText("Arbitros");
        jMenu5.add(jMenuItem7);

        jMenuItem8.setText("Cuerpo Tecnico");
        jMenu5.add(jMenuItem8);
        jMenu5.add(jSeparator3);

        jMenuItem10.setText("Club");
        jMenu5.add(jMenuItem10);

        jMenuItem9.setText("Equipo");
        jMenu5.add(jMenuItem9);

        jMenuFormularios.add(jMenu5);

        jMenuBar1.add(jMenuFormularios);

        jMenuConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos Nuevos/Config.png"))); // NOI18N
        jMenuConfiguracion.setText("Configuración");
        jMenuConfiguracion.setMaximumSize(new java.awt.Dimension(129, 32767));

        jMenuItemEstadosSocia.setText("Estados de una Socia");
        jMenuItemEstadosSocia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEstadosSociaActionPerformed(evt);
            }
        });
        jMenuConfiguracion.add(jMenuItemEstadosSocia);

        jMenuItemLocalidades.setText("Localidades");
        jMenuItemLocalidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLocalidadesActionPerformed(evt);
            }
        });
        jMenuConfiguracion.add(jMenuItemLocalidades);

        jMenuItemCategoria.setText("Categorías");
        jMenuItemCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCategoriaActionPerformed(evt);
            }
        });
        jMenuConfiguracion.add(jMenuItemCategoria);

        jMenuItemTipoCancha.setText("Tipos de Canchas");
        jMenuItemTipoCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTipoCanchaActionPerformed(evt);
            }
        });
        jMenuConfiguracion.add(jMenuItemTipoCancha);

        jMenuBar1.add(jMenuConfiguracion);

        jMenuCargaDB.setText("Asistentes de Carga DB");

        jMenuItemLos3.setText("TODOS JUNTOS");
        jMenuItemLos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLos3ActionPerformed(evt);
            }
        });
        jMenuCargaDB.add(jMenuItemLos3);

        jMenuBar1.add(jMenuCargaDB);

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
        IGestionEgresos unGestionEgresos = new IGestionEgresos(unaControladoraGlobal);
        unGestionEgresos.pack();
        unGestionEgresos.setVisible(true);
    }//GEN-LAST:event_jMenuItemGestionarEgresosActionPerformed

    private void jMenuItemConceptoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptoIngresoActionPerformed
        IConceptoIngresoOtro unConceptoIngresos = new IConceptoIngresoOtro(unaControladoraGlobal);
        unConceptoIngresos.pack();
        unConceptoIngresos.setVisible(true);
    }//GEN-LAST:event_jMenuItemConceptoIngresoActionPerformed

    private void jMenuItemConceptosEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptosEgresosActionPerformed
        IConceptoEgresos unConceptoEgresos = new IConceptoEgresos(unaControladoraGlobal);
        unConceptoEgresos.pack();
        unConceptoEgresos.setVisible(true);
    }//GEN-LAST:event_jMenuItemConceptosEgresosActionPerformed

    private void jMenuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSalirMouseClicked
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuSalirMouseClicked

    private void jMenuItemLocalidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLocalidadesActionPerformed
        ILocalidad unLocalidad = new ILocalidad(unaControladoraGlobal);
        unLocalidad.pack();
        unLocalidad.setVisible(true);
    }//GEN-LAST:event_jMenuItemLocalidadesActionPerformed

    private void jMenuItemCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoriaActionPerformed
        ICategoria unaCategoria = new ICategoria(unaControladoraGlobal);
        unaCategoria.pack();
        unaCategoria.setVisible(true);
    }//GEN-LAST:event_jMenuItemCategoriaActionPerformed

    private void jMenuItemEstadosSociaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEstadosSociaActionPerformed
        IEstadoTipo unTipoEstado = new IEstadoTipo(unaControladoraGlobal);
        unTipoEstado.pack();
        unTipoEstado.setVisible(true);
    }//GEN-LAST:event_jMenuItemEstadosSociaActionPerformed

    private void jMenuItemTipoCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTipoCanchaActionPerformed
        ICanchaTipo unaVentanaTipoCancha = new ICanchaTipo(unaControladoraGlobal);
        unaVentanaTipoCancha.pack();
        unaVentanaTipoCancha.setVisible(true);
    }//GEN-LAST:event_jMenuItemTipoCanchaActionPerformed

    private void jMenuItemConceptosDeportivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConceptosDeportivosActionPerformed
        IConceptoIngresoDeportivo unaVentanaConceptosDeportivos = new IConceptoIngresoDeportivo(unaControladoraGlobal);
        unaVentanaConceptosDeportivos.pack();
        unaVentanaConceptosDeportivos.setVisible(true);
    }//GEN-LAST:event_jMenuItemConceptosDeportivosActionPerformed

    private void jMenuItemGestionarIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionarIngresosActionPerformed
        IGestionIngresosOtro unaGestionIngresos = new IGestionIngresosOtro(unaControladoraGlobal);
        unaGestionIngresos.pack();
        unaGestionIngresos.setVisible(true);
    }//GEN-LAST:event_jMenuItemGestionarIngresosActionPerformed

    private void jMenuItemBalanceMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBalanceMensualActionPerformed
        IBalanceMensual unaIBalanceMensual = new IBalanceMensual(this.unaControladoraGlobal);
        unaIBalanceMensual.pack();
        unaIBalanceMensual.setVisible(true);
    }//GEN-LAST:event_jMenuItemBalanceMensualActionPerformed

    private void jMenuSociasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSociasMouseClicked
        IGestionSocias unaGestionSocias = new IGestionSocias(unaControladoraGlobal);
        unaGestionSocias.pack();
        unaGestionSocias.setVisible(true);
    }//GEN-LAST:event_jMenuSociasMouseClicked

    private void jMenuAuxiliaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAuxiliaresMouseClicked
        IGestionPersonaAuxiliar unaIGestionAuxiliares = new IGestionPersonaAuxiliar(unaControladoraGlobal);
        unaIGestionAuxiliares.pack();
        unaIGestionAuxiliares.setVisible(true);
    }//GEN-LAST:event_jMenuAuxiliaresMouseClicked

    private void jMenuClubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuClubMouseClicked
        IGestionClub unaGestionClub = new IGestionClub(this.unaControladoraGlobal);
        unaGestionClub.pack();
        unaGestionClub.setVisible(true);
    }//GEN-LAST:event_jMenuClubMouseClicked

    private void jMenuEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEquipoMouseClicked
        IGestionEquipo unaGestionEquipo = new IGestionEquipo(this.unaControladoraGlobal);
        unaGestionEquipo.pack();
        unaGestionEquipo.setVisible(true);
    }//GEN-LAST:event_jMenuEquipoMouseClicked

    private void jMenuTorneoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuTorneoMouseClicked
        IGestionTorneo unGestionTorneo = new IGestionTorneo(unaControladoraGlobal);
        unGestionTorneo.pack();
        unGestionTorneo.setVisible(true);
    }//GEN-LAST:event_jMenuTorneoMouseClicked

    private void jMenuItemLos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLos3ActionPerformed
        setEstadoSocia();
        setEquipoAleatorio();
        setErgometrias();
        setCamSocia();
    }//GEN-LAST:event_jMenuItemLos3ActionPerformed

    private void jMenuItemCuotaMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCuotaMensualActionPerformed
        IGeneradorMensual unIGeneradorMensual = new IGeneradorMensual(unaControladoraGlobal);
        unIGeneradorMensual.pack();
        unIGeneradorMensual.setVisible(true);
    }//GEN-LAST:event_jMenuItemCuotaMensualActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        IGestionIngresosFuturos unIGestionIngresosFuturos = new IGestionIngresosFuturos(unaControladoraGlobal);
        unIGestionIngresosFuturos.pack();
        unIGestionIngresosFuturos.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void setCamSocia() {
        int Max = 99;
        int Min = 1;
        int aux;
        for (Socia unaSocia : unaControladoraGlobal.getSociasBD()) {
            aux = ((int) (Math.random() * (Max - Min)) + Min);
            unaControladoraGlobal.modificarNumeroCamiseta(unaSocia, String.valueOf(aux));
        }
    }

    private void setEstadoSocia() {
        //Si no hay tipoEstadoSocia, lo crea
        TipoEstado unTipoEstadoSocia = null;
        for (TipoEstado unTipoEstado : unaControladoraGlobal.getTiposEstadosBD()) {
            if ("Jugadora".equals(unTipoEstado.getNombre())) {
                unTipoEstadoSocia = unTipoEstado;
            }
        }
        //A todas las socias que no tienen un estado, le asigna uno
        for (Socia unaSocia : unaControladoraGlobal.getSociasBD()) {
            if (unaSocia.getUltimoEstado() == null) {
                unaControladoraGlobal.crearEstado(unaSocia, unaSocia.getFechaIngreso(), unTipoEstadoSocia);
            }
        }
    }

    private void setEquipoAleatorio() {
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
    }

    private void setErgometrias() {
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
    }

    public static void centrarYalFrente(JInternalFrame unJInternalFrame) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = unJInternalFrame.getSize();
        unJInternalFrame.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        unJInternalFrame.moveToFront();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuAuxiliares;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCargaDB;
    private javax.swing.JMenu jMenuClub;
    private javax.swing.JMenu jMenuConfiguracion;
    private javax.swing.JMenu jMenuContabilidad;
    private javax.swing.JMenu jMenuEquipo;
    private javax.swing.JMenu jMenuFormularios;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemBalanceMensual;
    private javax.swing.JMenuItem jMenuItemCategoria;
    private javax.swing.JMenuItem jMenuItemConceptoIngreso;
    private javax.swing.JMenuItem jMenuItemConceptosDeportivos;
    private javax.swing.JMenuItem jMenuItemConceptosEgresos;
    private javax.swing.JMenuItem jMenuItemCuotaMensual;
    private javax.swing.JMenuItem jMenuItemEstadosSocia;
    private javax.swing.JMenuItem jMenuItemGestionarEgresos;
    private javax.swing.JMenuItem jMenuItemGestionarIngresos;
    private javax.swing.JMenuItem jMenuItemLocalidades;
    private javax.swing.JMenuItem jMenuItemLos3;
    private javax.swing.JMenuItem jMenuItemTipoCancha;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JMenu jMenuSocias;
    private javax.swing.JMenu jMenuTorneo;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
