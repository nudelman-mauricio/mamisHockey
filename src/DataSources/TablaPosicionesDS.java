/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import Interfaces.IGestionEquipo;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

// <editor-fold defaultstate="collapsed" desc="equipoTablaPosiciones">
class equipoTablaPosiciones {

    private int cantidadPartidosJugados;
    private int cantidadPartidosGanados;
    private int cantidadGoles;
    private int cantidadGolesContrario;
    private int cantidadPartidosEmpatados;
    private int cantidadPartidosPerdidos;
    private int puntos;
    private int diferenciaGol;
    private String nombreEquipo;

    public equipoTablaPosiciones(int cantPartJug, int cantPartGan, int cantGoles, int cantGolesContrario, int cantPartEmpat, int cantPartPer, String nombreEquipo) {
        this.cantidadPartidosJugados = cantPartJug;
        this.cantidadPartidosGanados = cantPartGan;
        this.cantidadGoles = cantGoles;
        this.cantidadGolesContrario = cantGolesContrario;
        this.cantidadPartidosEmpatados = cantPartEmpat;
        this.cantidadPartidosPerdidos = cantPartPer;
        this.puntos = (cantidadPartidosGanados * 3) + cantidadPartidosEmpatados;
        this.diferenciaGol = cantidadGoles - cantidadGolesContrario;
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * @return the cantidadPartidosJugados
     */
    public int getCantidadPartidosJugados() {
        return cantidadPartidosJugados;
    }

    /**
     * @param cantidadPartidosJugados the cantidadPartidosJugados to set
     */
    public void setCantidadPartidosJugados(int cantidadPartidosJugados) {
        this.cantidadPartidosJugados = cantidadPartidosJugados;
    }

    /**
     * @return the cantidadPartidosGanados
     */
    public int getCantidadPartidosGanados() {
        return cantidadPartidosGanados;
    }

    /**
     * @param cantidadPartidosGanados the cantidadPartidosGanados to set
     */
    public void setCantidadPartidosGanados(int cantidadPartidosGanados) {
        this.cantidadPartidosGanados = cantidadPartidosGanados;
    }

    /**
     * @return the cantidadGoles
     */
    public int getCantidadGoles() {
        return cantidadGoles;
    }

    /**
     * @param cantidadGoles the cantidadGoles to set
     */
    public void setCantidadGoles(int cantidadGoles) {
        this.cantidadGoles = cantidadGoles;
    }

    /**
     * @return the cantidadGolesContrario
     */
    public int getCantidadGolesContrario() {
        return cantidadGolesContrario;
    }

    /**
     * @param cantidadGolesContrario the cantidadGolesContrario to set
     */
    public void setCantidadGolesContrario(int cantidadGolesContrario) {
        this.cantidadGolesContrario = cantidadGolesContrario;
    }

    /**
     * @return the cantidadPartidosEmpatados
     */
    public int getCantidadPartidosEmpatados() {
        return cantidadPartidosEmpatados;
    }

    /**
     * @param cantidadPartidosEmpatados the cantidadPartidosEmpatados to set
     */
    public void setCantidadPartidosEmpatados(int cantidadPartidosEmpatados) {
        this.cantidadPartidosEmpatados = cantidadPartidosEmpatados;
    }

    /**
     * @return the cantidadPartidosPerdidos
     */
    public int getCantidadPartidosPerdidos() {
        return cantidadPartidosPerdidos;
    }

    /**
     * @param cantidadPartidosPerdidos the cantidadPartidosPerdidos to set
     */
    public void setCantidadPartidosPerdidos(int cantidadPartidosPerdidos) {
        this.cantidadPartidosPerdidos = cantidadPartidosPerdidos;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the diferenciaGol
     */
    public int getDiferenciaGol() {
        return diferenciaGol;
    }

    /**
     * @param diferenciaGol the diferenciaGol to set
     */
    public void setDiferenciaGol(int diferenciaGol) {
        this.diferenciaGol = diferenciaGol;
    }

    /**
     * @return the nombreEquipo
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * @param nombreEquipo the nombreEquipo to set
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
}
//</editor-fold> 

public class TablaPosicionesDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    List<FechaTorneo> fechasTorneo;
    List<Equipo> equipos;
    int indiceEquipos = -1;
    private DateFormat df = DateFormat.getDateInstance();
    private equipoTablaPosiciones unEquipoTabla;
    private List<equipoTablaPosiciones> listaTablaPosiciones = new ArrayList();

    public TablaPosicionesDS(ControladoraGlobal unaControladoraGlobal, Torneo unTorneoSeleccionado) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneoSeleccionado;
        this.fechasTorneo = (List<FechaTorneo>) unTorneo.getFechasTorneo();
        this.equipos = (List<Equipo>) unTorneo.getEquiposInscriptos();
        for (Equipo unEquipo : equipos) {
            calcularTablaPosiciones(unEquipo);
        }
        Collections.sort(listaTablaPosiciones, new Comparator<equipoTablaPosiciones>() {
        @Override
        public int compare(equipoTablaPosiciones  equipo1, equipoTablaPosiciones  equipo2)
        {
            
            int valor = ((Integer)equipo2.getPuntos()).compareTo((Integer)equipo1.getPuntos());
            if(valor == 0){
                return  ((Integer)equipo2.getDiferenciaGol()).compareTo((Integer)equipo1.getDiferenciaGol());
            }
            return valor;
        }
    });
        //Collections.sort(listaTablaPosiciones, (equipoTablaPosiciones equipo1, equipoTablaPosiciones equipo2) -> ((Integer) equipo2.getPuntos()).compareTo((Integer) equipo1.getPuntos()));
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceEquipos < listaTablaPosiciones.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("equipo".equals(jrf.getName())) {
            valor = listaTablaPosiciones.get(indiceEquipos).getNombreEquipo();
        } else if ("pj".equals(jrf.getName())) {
            valor = listaTablaPosiciones.get(indiceEquipos).getCantidadPartidosJugados();

        } else if ("pg".equals(jrf.getName())) {
            valor = listaTablaPosiciones.get(indiceEquipos).getCantidadPartidosGanados();
        } else if ("pe".equals(jrf.getName())) {
          valor = listaTablaPosiciones.get(indiceEquipos).getCantidadPartidosEmpatados();
        } else if ("pp".equals(jrf.getName())) {
           valor = listaTablaPosiciones.get(indiceEquipos).getCantidadPartidosPerdidos();
        } else if ("ga".equals(jrf.getName())) {
            valor = listaTablaPosiciones.get(indiceEquipos).getCantidadGoles();
        } else if ("gc".equals(jrf.getName())) {
           valor = listaTablaPosiciones.get(indiceEquipos).getCantidadGolesContrario();
        } else if ("dg".equals(jrf.getName())) {
           valor = listaTablaPosiciones.get(indiceEquipos).getDiferenciaGol();
        } else if ("pts".equals(jrf.getName())) {
           valor = listaTablaPosiciones.get(indiceEquipos).getPuntos();
        }
        return valor;//To change body of generated methods, choose Tools | Templates.
    }

    private void calcularTablaPosiciones(Equipo unEquipo) {
        int cantidadPartidosJugados = 0;
        int cantidadPartidosGanados = 0;
        int cantidadGoles = 0;
        int cantidadGolesContrario = 0;
        int cantidadPartidosEmpatados = 0;
        int cantidadPartidosPerdidos = 0;        
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
        equipoTablaPosiciones unEquipoTabla = new equipoTablaPosiciones(cantidadPartidosJugados, cantidadPartidosGanados, cantidadGoles, cantidadGolesContrario, cantidadPartidosEmpatados, cantidadPartidosPerdidos, unEquipo.getNombre());
        listaTablaPosiciones.add(unEquipoTabla);

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
