/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

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
public class HistorialEquipoDS_Plantel implements JRDataSource {

    List<Socia> plantelImprimir;
    int indice = -1;
    ControladoraGlobal unaControladoraGlobal;

    HistorialEquipoDS_Plantel(List<Socia> plantelImprimir, ControladoraGlobal unaControladoraGlobal) {
        this.plantelImprimir = plantelImprimir;
        this.unaControladoraGlobal = unaControladoraGlobal;
    }

    @Override
    public boolean next() throws JRException {
        return ++indice < plantelImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if (null != jrf.getName()) {
            switch (jrf.getName()) {
                case "nroCamiseta":
                    valor = plantelImprimir.get(indice).getNumeroCamiseta();
                    break;
                case "apellidoynombre":
                    valor = plantelImprimir.get(indice).getApellido() + ", " + plantelImprimir.get(indice).getNombre();
                    break;
                case "dni":
                    valor = plantelImprimir.get(indice).getDni();
                    break;
            }
        }
        return valor;
    }

}
