/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Deuda;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
class HistorialEquipoDS_Deudas implements JRDataSource {

    int indice = -1;
    List<Deuda> deudasImprimir;
    ControladoraGlobal unaControladoraGlobal;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");

    HistorialEquipoDS_Deudas(List<Deuda> deudasImprimir,ControladoraGlobal unaControladoraGlobal) {
        this.deudasImprimir = deudasImprimir;
        this.unaControladoraGlobal = unaControladoraGlobal;
    }

    @Override
    public boolean next() throws JRException {
        return ++indice < deudasImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if (null != jrf.getName()) {
            switch (jrf.getName()) {
                case "Fecha":
                    valor = df.format(deudasImprimir.get(indice).getFechaGeneracion());
                    break;
                case "Concepto":
                    valor = deudasImprimir.get(indice).getUnConceptoDeportivo();
                    break;
                case "Vencimiento":
                    valor = df.format(deudasImprimir.get(indice).getPrimerVencimiento());
                    break;
                case "Monto":
                    valor = deudasImprimir.get(indice).getMontoTotal();
                    break;
                case "Deuda Saldada":
                    
                    if(deudasImprimir.get(indice).isSaldado() == true){
                        valor = "Si";
                    } else {
                        valor = "No";
                    }
                    break;
            }
        }
        return valor;
    }
}
