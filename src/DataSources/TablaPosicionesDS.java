/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import Interfaces.IGestionEquipo;
import java.io.File;
import java.text.DateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Partido;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Leanwit
 */
public class TablaPosicionesDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    List<FechaTorneo> fechasTorneo;
    List<Equipo> equipos;
    int indiceEquipos = -1;
    private DateFormat df = DateFormat.getDateInstance();

    int cantidadPartidosJugados = 0;
    int cantidadPartidosGanados = 0;
    int cantidadGoles = 0;
    int cantidadGolesContrario = 0;
    int cantidadPartidosEmpatados = 0;
    int cantidadPartidosPerdidos = 0;
    int puntos = 0;

    public TablaPosicionesDS(ControladoraGlobal unaControladoraGlobal, Torneo unTorneoSeleccionado) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneoSeleccionado;
        this.fechasTorneo = (List<FechaTorneo>) unTorneo.getFechasTorneo();
        this.equipos = (List<Equipo>) unTorneo.getEquiposInscriptos();
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceEquipos < equipos.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("equipo".equals(jrf.getName())) {
            cantidadPartidosJugados = 0;
            cantidadPartidosGanados = 0;
            cantidadGoles = 0;
            cantidadGolesContrario = 0;
            cantidadPartidosEmpatados = 0;
            cantidadPartidosPerdidos = 0;
            puntos = 0;
            calcularTablaPosiciones(equipos.get(indiceEquipos));
            valor = equipos.get(indiceEquipos).getNombre();
        } else if ("pj".equals(jrf.getName())) {
            valor = cantidadPartidosJugados;

        } else if ("pg".equals(jrf.getName())) {
            valor = cantidadPartidosGanados;
        } else if ("pe".equals(jrf.getName())) {
            valor = cantidadPartidosEmpatados;
        } else if ("pp".equals(jrf.getName())) {
            valor = cantidadPartidosPerdidos;
        } else if ("ga".equals(jrf.getName())) {
            valor = cantidadGoles;
        } else if ("gc".equals(jrf.getName())) {
            valor = cantidadGolesContrario;
        } else if ("dg".equals(jrf.getName())) {
            valor = cantidadGoles - cantidadGolesContrario;
        } else if ("pts".equals(jrf.getName())) {
            valor = (cantidadPartidosGanados * 3 + cantidadPartidosEmpatados);
        }
        return valor;//To change body of generated methods, choose Tools | Templates.
    }

    private void calcularTablaPosiciones(Equipo unEquipo) {
        for (FechaTorneo unaFechaTorneo : fechasTorneo) {
            for (Partido unPartido : unaFechaTorneo.getPartidos()) {
                if (unPartido.getNombreVeedor() != null) {
                    if (unPartido.getUnEquipoLocal().equals(unEquipo)) {
                        cantidadPartidosJugados++;
                        if (unPartido.getGoles() != null) {
                            cantidadGoles += unaControladoraGlobal.getGolesLocal(unPartido);
                            cantidadGolesContrario += unaControladoraGlobal.getGolesVisitante(unPartido);
                            if (unaControladoraGlobal.getGolesLocal(unPartido) > unaControladoraGlobal.getGolesVisitante(unPartido)) {
                                cantidadPartidosGanados++;
                            } else {
                                if (unaControladoraGlobal.getGolesLocal(unPartido) == unaControladoraGlobal.getGolesVisitante(unPartido)) {
                                    cantidadPartidosEmpatados++;

                                } else {
                                    cantidadPartidosPerdidos++;
                                }
                            }
                        }
                    } else {
                        if (unPartido.getUnEquipoVisitante().equals(unEquipo)) {
                            cantidadPartidosJugados++;
                            if (unPartido.getGoles() != null) {
                                cantidadGoles += unaControladoraGlobal.getGolesVisitante(unPartido);
                                cantidadGolesContrario += unaControladoraGlobal.getGolesLocal(unPartido);
                                if (unaControladoraGlobal.getGolesLocal(unPartido) < unaControladoraGlobal.getGolesVisitante(unPartido)) {
                                    cantidadPartidosGanados++;
                                } else {
                                    if (unaControladoraGlobal.getGolesLocal(unPartido) == unaControladoraGlobal.getGolesVisitante(unPartido)) {
                                        cantidadPartidosEmpatados++;
                                    } else {
                                        cantidadPartidosPerdidos++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
       
    }

    public void verReporte() {
        File archivo = new File("reportes/reporteTablaPosiciones.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
