/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.DateFormat;
import java.util.List;
import logicaNegocios.Partido;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class FixtureDS_Fecha_Partido implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    List<Partido> partidos;
    private int indicePartidos = -1;
    private DateFormat df = DateFormat.getDateInstance();

    public FixtureDS_Fecha_Partido(ControladoraGlobal unaControladoraGlobal, List<Partido> partidos) {    
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.partidos = partidos;
        
    }

    @Override
    public boolean next() throws JRException {
        return ++indicePartidos < partidos.size();
    }

    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fechaPartido".equals(jrf.getName())) {
            valor = df.format(partidos.get(indicePartidos).getFecha());
        } else if ("golLocal".equals(jrf.getName())) {
           valor = unaControladoraGlobal.getGolesLocal(partidos.get(indicePartidos));
           if (!partidos.get(indicePartidos).isJugado()) { 
                valor = "";
            }        
        } else if ("local".equals(jrf.getName())) {
            valor = partidos.get(indicePartidos).getUnEquipoLocal().getNombre();
        } else if ("visitante".equals(jrf.getName())) {            
            valor = partidos.get(indicePartidos).getUnEquipoVisitante().getNombre();
        } else if ("golVisitante".equals(jrf.getName())) {
            valor = unaControladoraGlobal.getGolesVisitante(partidos.get(indicePartidos));
            if (!partidos.get(indicePartidos).isJugado()) { 
                valor = "";
            }              
        } else if ("cancha".equals(jrf.getName())) {
            valor = partidos.get(indicePartidos).getUnaCancha().getNombre();
        }
        return valor;
    }

}
