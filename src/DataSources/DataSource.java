/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.util.ArrayList;
import java.util.List;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSource implements JRDataSource {

    private List<Equipo> listaEquipo = new ArrayList<Equipo>();
    private Club unClub = new Club();
    private ControladoraGlobal unaControladoraGlobal;
    private int indiceEquipoActual = -1;
    
    public DataSource (ControladoraGlobal unaControladoraGlobal, List<Equipo> unaListaEquipo){
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.listaEquipo = unaListaEquipo;
    }
    
    @Override
    public boolean next() throws JRException {
         return ++indiceEquipoActual < listaEquipo.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;

        if ("nombreEquipo".equals(jrf.getName())) {
            valor = listaEquipo.get(indiceEquipoActual).getNombre();
        } else if ("nombreDT".equals(jrf.getName())) {
            valor = listaEquipo.get(indiceEquipoActual).getUnDT();
        } else if ("nombreClub".equals(jrf.getName())) {
            valor = unaControladoraGlobal.getClubBD(listaEquipo.get(indiceEquipoActual));
        }
        return valor;
    }

}
