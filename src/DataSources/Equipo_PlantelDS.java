/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataSources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import logicaNegocios.Socia;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class Equipo_PlantelDS implements JRDataSource {
    
    private List<Socia> plantel = new ArrayList<Socia>();
    private int indiceSociaActual = -1;
  
    
       public Equipo_PlantelDS (Collection unPlantel){
        this.plantel = (List<Socia>) unPlantel;         
    }  

    public Object getFieldValue(JRField jrf) throws JRException
    {
        Object valor = null;

        if ("dni".equals(jrf.getName()))
        {
            valor = plantel.get(indiceSociaActual).getDni();
        }
        else if ("nombre".equals(jrf.getName()))
        {
            valor = plantel.get(indiceSociaActual).getNombre();            
        }
        else if ("apellido".equals(jrf.getName()))
        {
            valor = plantel.get(indiceSociaActual).getApellido();
        }      
        return valor;
    }

    public boolean next() throws JRException
    {
        return ++indiceSociaActual < plantel.size();
    }   

}
