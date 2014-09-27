/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataSources;

import logicaNegocios.Club;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class EquipoDS implements JRDataSource {
   
    private Club unClub = new Club();
    private ControladoraGlobal unaControladoraGlobal;
    private int indiceEquipoActual = -1;
    private Equipo unEquipo;
    
    public EquipoDS (ControladoraGlobal unaControladoraGlobal, Equipo unEquipo){
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unEquipo = unEquipo;        
    }    
   

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;        
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("equipo".equals(jrf.getName())) {
            valor = unEquipo.getNombre();        
        } else if ("directorTecnico".equals(jrf.getName())) {
            valor = unEquipo.getUnDT();
        } else if ("preparadorFisico".equals(jrf.getName())) {
            valor = unEquipo.getUnPreparadorFisico();
        } else if ("delegada".equals(jrf.getName())) {
            valor = unEquipo.getUnaDelegada();
        } else if ("delegadaSuplente".equals(jrf.getName())) {
            valor = unEquipo.getUnaDelegadaSuplente();
        } else if ("capitana".equals(jrf.getName())) {
            valor = unEquipo.getUnaCapitana();
        } else if ("capitanaSuplente".equals(jrf.getName())) {
            valor = unEquipo.getUnaCapitanaSuplente();
        } else if ("ayudanteCampo".equals(jrf.getName())) {
            valor = unEquipo.getUnAyudanteCampo();
        } 
        return valor;
    }

    @Override
    public boolean next() throws JRException {
         return ++indiceEquipoActual < 1;
    }
    
    
}
