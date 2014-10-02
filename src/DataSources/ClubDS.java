/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataSources;

import java.text.DateFormat;
import logicaNegocios.Club;
import main.ControladoraGlobal;
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
    private ControladoraGlobal unaControladoraGlobal;
    private DateFormat df = DateFormat.getDateInstance();
    
    
    public ClubDS(Club unClub, ControladoraGlobal unaControladoraGlobal){
        this.unClub = unClub;
        this.unaControladoraGlobal = unaControladoraGlobal;
    }
    
    @Override
    public boolean next() throws JRException {
          return ++indiceEquipoActual < 1;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;        
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("club".equals(jrf.getName())) {
            valor = unClub.getNombre();        
        } else if ("presidente".equals(jrf.getName())) {
            valor = unClub.getNombrePresidente();
        } else if ("localidad".equals(jrf.getName())) {
            valor = unClub.getUnaLocalidad();
        } else if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } 
        return valor;    
    }
    
}
