/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.util.List;
import logicaNegocios.Cancha;
import logicaNegocios.Partido;
import logicaNegocios.Equipo;
import logicaNegocios.Socia;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Lucas
 */
public class PlanillaPartidoDS_Plantel  implements JRDataSource {

    private int indiceSocia = -1;

    private ControladoraGlobal unaControladoraGlobal;
    private List<Socia> unaSocia;

    public PlanillaPartidoDS_Plantel(ControladoraGlobal unaControladoraGlobal, List<Socia> unaSocia) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaSocia = unaSocia;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceSocia < unaSocia.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if ("cam".equals(jrf.getName())) {
            valor = unaSocia.get(indiceSocia).getNumeroCamiseta();
        } else if ("apellidoyNombre".equals(jrf.getName())) {
            valor = unaSocia.get(indiceSocia);
        }
        return valor;
    }    
}
