/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataSources;

import java.util.Collection;
import java.util.List;
import logicaNegocios.Equipo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class Club_EquipoDS implements JRDataSource  {
    private List<Equipo> equipos;
    private int indiceEquipoActual = -1;
  
    
       public Club_EquipoDS (Collection equipos){
        this.equipos = (List<Equipo>) equipos;         
    }  

    public Object getFieldValue(JRField jrf) throws JRException
    {
        Object valor = null;

        if ("nombre".equals(jrf.getName()))
        {
            valor = equipos.get(indiceEquipoActual).getNombre();
        }
        else if ("directorTecnico".equals(jrf.getName()))
        {
            valor = equipos.get(indiceEquipoActual).getUnDT();
            if(valor == null){
                valor = "";
            }
        }
        else if ("delegada".equals(jrf.getName()))
        {
            valor = equipos.get(indiceEquipoActual).getUnaDelegada();
            if(valor == null){
                valor = "";
            }
        }  else if ("delegadaSuplente".equals(jrf.getName()))
        {
            valor = equipos.get(indiceEquipoActual).getUnaDelegadaSuplente();
            if(valor == null){
                valor = "";
            }
        }     
        return valor;
    }

    public boolean next() throws JRException
    {
        return ++indiceEquipoActual < equipos.size();
    }   
}
