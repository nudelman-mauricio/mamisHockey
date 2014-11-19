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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Gol;
import logicaNegocios.Jugadora;
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

//<editor-fold defaultstate="collapsed" desc="JugadoraTabla">
class JugadoraTabla {

    private String nombreJugadora;
    private String nombreEquipo;
    private int goles;

    public JugadoraTabla(String nombreJugadora, String nombreEquipo) {
        this.nombreJugadora = nombreJugadora;
        this.nombreEquipo = nombreEquipo;

    }

    /**
     * @return the nombreJugadora
     */
    public String getNombreJugadora() {
        return nombreJugadora;
    }

    /**
     * @param nombreJugadora the nombreJugadora to set
     */
    public void setNombreJugadora(String nombreJugadora) {
        this.nombreJugadora = nombreJugadora;
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

    /**
     * @return the goles
     */
    public int getGoles() {
        return goles;
    }

    /**
     * @param goles the goles to set
     */
    public void setGoles(int goles) {
        this.goles = goles;
    }
}
//</editor-fold>

public class TablaGoleadorasDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    int indiceGoleadora = -1;
    private DateFormat df = DateFormat.getDateInstance();
    List<JugadoraTabla> jugadorasTablas = new ArrayList();
    JugadoraTabla unaJugadoraTablaAuxiliar;
    Jugadora unaJugadora;
    boolean bandera = false;

    public TablaGoleadorasDS(ControladoraGlobal unaControladoraGlobal, Torneo unTorneoSeleccionado) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneoSeleccionado;
        calcularGoles();
        //Ordena el arraylist. Lambda Expresion
        Collections.sort(jugadorasTablas, (JugadoraTabla jugadora1, JugadoraTabla jugadora2) -> ((Integer) jugadora2.getGoles()).compareTo((Integer) jugadora1.getGoles()));
    }

    private void calcularGoles() {

        for (FechaTorneo unaFecha : unTorneo.getFechasTorneo()) {
            for (Partido unPartido : unaFecha.getPartidos()) {
                for (Gol unGol : unPartido.getGoles()) {
                    unaJugadora = unaControladoraGlobal.getAutoraGol(unPartido, unGol);

                    bandera = false;
                    for (JugadoraTabla aux : jugadorasTablas) {
                        if (aux.getNombreJugadora().equals(unaJugadora.getUnaSocia().toString())) {
                            aux.setGoles(aux.getGoles() + 1);
                            bandera = true;
                        }
                    }
                    if (!bandera) {

                        unaJugadoraTablaAuxiliar = new JugadoraTabla(unaJugadora.getUnaSocia().toString(), unaJugadora.getUnaSocia().getEquipoActual().getNombre());
                        unaJugadoraTablaAuxiliar.setGoles(1);
                        jugadorasTablas.add(unaJugadoraTablaAuxiliar);
                    }
                }
            }
        }
        if (unTorneo.getUnTorneoPadre().getFechasTorneo() != null) {
            for (FechaTorneo unaFecha : unTorneo.getUnTorneoPadre().getFechasTorneo()) {
                for (Partido unPartido : unaFecha.getPartidos()) {
                    for (Gol unGol : unPartido.getGoles()) {
                        unaJugadora = unaControladoraGlobal.getAutoraGol(unPartido, unGol);

                        bandera = false;
                        for (JugadoraTabla aux : jugadorasTablas) {
                            if (aux.getNombreJugadora().equals(unaJugadora.getUnaSocia().toString())) {
                                aux.setGoles(aux.getGoles() + 1);
                                bandera = true;
                            }
                        }
                        if (!bandera) {
                            unaJugadoraTablaAuxiliar = new JugadoraTabla(unaJugadora.getUnaSocia().toString(), unaJugadora.getUnaSocia().getEquipoActual().getNombre());
                            unaJugadoraTablaAuxiliar.setGoles(1);
                            jugadorasTablas.add(unaJugadoraTablaAuxiliar);
                        }
                    }
                }
            }
        }

    }

    @Override
    public boolean next() throws JRException {
        return ++indiceGoleadora < jugadorasTablas.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("nombreTorneo".equals(jrf.getName())) {
            valor = this.unTorneo.getNombre();
        } else if ("nombreJugadora".equals(jrf.getName())) {
            valor = jugadorasTablas.get(indiceGoleadora).getNombreJugadora();
        } else if ("Equipo".equals(jrf.getName())) {
            valor = jugadorasTablas.get(indiceGoleadora).getNombreEquipo();
        } else if ("Gol".equals(jrf.getName())) {
            valor = jugadorasTablas.get(indiceGoleadora).getGoles();
        }
        return valor;
    }

    public void verReporte() {
        File archivo = new File("reportes/reporteGoleadoras.jasper");
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
