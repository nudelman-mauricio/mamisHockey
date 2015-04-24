/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Partido;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class FixtureDS_Fecha implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    int indiceFecha = -1;
    List<FechaTorneo> fechasTorneo = new ArrayList();
    private DateFormat df = DateFormat.getDateInstance();
    boolean libre = false,bandera = true;
    String mensajeLibre ="";
    

    public FixtureDS_Fecha(ControladoraGlobal unaControladoraGlobal, List<FechaTorneo> listaFechasTorneo, Torneo unTorneo) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneo;
        for (FechaTorneo unaFecha : listaFechasTorneo) {
            if (!unaFecha.isBorradoLogico()) {
                fechasTorneo.add(unaFecha);
            }
        }
        if ((unTorneo.getCantidadEquiposInscriptos() % 2) != 0) {
            this.libre = true;
        }

    }

    @Override
    public boolean next() throws JRException {
        return ++indiceFecha < fechasTorneo.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fechaNro".equals(jrf.getName())) {
            valor = fechasTorneo.get(indiceFecha).getNumeroFecha();
        } else if ("partido".equals(jrf.getName())) {
            valor = subReporte((List<Partido>) fechasTorneo.get(indiceFecha).getPartidosNoBorrados());
        } else if ("libre".equals(jrf.getName())) {
            if (libre) {
                FechaTorneo unaFechaAux = fechasTorneo.get(indiceFecha);
                for (Equipo unEquipoAux : unTorneo.getEquiposInscriptos()) {
                    bandera = true;
                    for (Partido unPartidoAux : unaFechaAux.getPartidosNoBorrados()) {
                        if(unPartidoAux.getUnEquipoLocal().equals(unEquipoAux) || unPartidoAux.getUnEquipoVisitante().equals(unEquipoAux)){
                            bandera = false;
                        }
                    }
                    if(bandera){
                        valor = "Nota: "+ unEquipoAux.getNombre() + " está libre en esta fecha";
                    }
                }
            }
            
        }
        return valor;
    }

    private FixtureDS_Fecha_Partido subReporte(List<Partido> partidos) {
        FixtureDS_Fecha_Partido partidoDS = new FixtureDS_Fecha_Partido(unaControladoraGlobal, partidos);
        return partidoDS;
    }

}
