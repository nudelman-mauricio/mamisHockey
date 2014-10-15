/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.util.ArrayList;
import java.util.List;
import logicaNegocios.Tarjeta;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class TarjetaDS_Torneo implements JRDataSource{
    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    List<Tarjeta> listaTarjetas = new ArrayList();
    int indiceTarjetas = -1;
    
    public TarjetaDS_Torneo (Torneo unTorneo,ControladoraGlobal unaControladora){
        this.unTorneo = unTorneo;
        this.unaControladoraGlobal = unaControladoraGlobal;
        
    }

    @Override
    public boolean next() throws JRException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
