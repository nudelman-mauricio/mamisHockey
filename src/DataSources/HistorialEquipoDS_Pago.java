/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.PlanillaPago;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
class HistorialEquipoDS_Pago implements JRDataSource {

    int indice = -1;
    List<PlanillaPago> pagosImprimir;
    ControladoraGlobal unaControladoraGlobal;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");

    HistorialEquipoDS_Pago(List<PlanillaPago> pagosImprimir, ControladoraGlobal unaControladoraGlobal) {
        this.pagosImprimir = pagosImprimir;
        this.unaControladoraGlobal = unaControladoraGlobal;
    }

    @Override
    public boolean next() throws JRException {
        return ++indice < pagosImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if (null != jrf.getName()) {
            switch (jrf.getName()) {
                case "fechaPago":
                    valor = df.format(pagosImprimir.get(indice).getFechaPago());
                    break;
                case "pagadoPor":
                    valor = pagosImprimir.get(indice).getResponsablePago();
                    break;
                case "nroRecibo":
                    valor = pagosImprimir.get(indice).getNroRecibo();
                    break;
                case "monto":
                    valor = pagosImprimir.get(indice).getMonto();
                    break;
            }
        }
        return valor;
    }

}
