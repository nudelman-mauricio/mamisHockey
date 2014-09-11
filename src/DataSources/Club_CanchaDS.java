/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataSources;

import java.util.List;
import logicaNegocios.Cancha;
import logicaNegocios.Club;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *  * @author Leanwit
 */
public class Club_CanchaDS implements JRDataSource {
    private List<Cancha> canchas;
    private int indiceCanchaActual = -1;
  
    
       public Club_CanchaDS (Club unClubSeleccionado){
        this.canchas = (List<Cancha>) unClubSeleccionado.getCanchas();         
    }  

    public Object getFieldValue(JRField jrf) throws JRException
    {
        Object valor = null;

        if ("Tipo".equals(jrf.getName()))
        {
            valor = canchas.get(indiceCanchaActual).getUnTipoCancha().getNombre();            
        }
        else if ("Nombre".equals(jrf.getName()))
        {
            valor = canchas.get(indiceCanchaActual).getNombre();           
        }
        else if ("SeOcupa".equals(jrf.getName()))
        {
            valor = canchas.get(indiceCanchaActual).isSeOcupa();            
        }      
        return valor;
    }

    public boolean next() throws JRException
    {
        return ++indiceCanchaActual < canchas.size();
    }   
}
