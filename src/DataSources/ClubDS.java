/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataSources;

import logicaNegocios.Club;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class ClubDS implements JRDataSource {
    private Club unClub;  
    private int indiceEquipoActual = -1;
    
    
    public ClubDS(Club unClub){
        this.unClub = unClub;
    }
    
    @Override
    public boolean next() throws JRException {
          return ++indiceEquipoActual < 1;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;        
        if ("club".equals(jrf.getName())) {
            valor = unClub.getNombre();        
        } else if ("presidente".equals(jrf.getName())) {
            valor = unClub.getNombrePresidente();
        } else if ("localidad".equals(jrf.getName())) {
            valor = unClub.getUnaLocalidad();
        } 
        return valor;    
    }
    
}
