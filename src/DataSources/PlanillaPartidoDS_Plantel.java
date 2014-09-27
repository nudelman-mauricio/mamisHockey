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
import logicaNegocios.Jugadora;
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
    private List<Jugadora> unaJugadora;
    private Partido unPartido;

    public PlanillaPartidoDS_Plantel(ControladoraGlobal unaControladoraGlobal, Partido unPartido, List<Jugadora> unaJugadora) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaJugadora = unaJugadora;
        this.unPartido = unPartido;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceSocia < unaJugadora.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if ("cam".equals(jrf.getName())) {
            valor = unaJugadora.get(indiceSocia).getCamiseta();
        } else if ("apellidoyNombre".equals(jrf.getName())) {
            valor = unaJugadora.get(indiceSocia).getUnaSocia();
        }else if ("exJugadora".equals(jrf.getName())) {
            if (unaJugadora.get(indiceSocia).getUnaSocia().isExJugadora()){
                valor = "true";
            }else{
                valor ="false";
            }
        }
        return valor;
    }    
}
