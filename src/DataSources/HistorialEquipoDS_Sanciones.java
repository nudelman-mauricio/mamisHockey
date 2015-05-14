/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.SancionTribunal;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
class HistorialEquipoDS_Sanciones implements JRDataSource {

    int indice = -1;
    List<SancionTribunal> sancionesImprimir;
    SimpleDateFormat df;

    HistorialEquipoDS_Sanciones(List<SancionTribunal> sancionesImprimir) {
        this.sancionesImprimir = sancionesImprimir;
    }

    @Override
    public boolean next() throws JRException {
        return ++indice < sancionesImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if (null != jrf.getName()) {
            switch (jrf.getName()) {
                case "fecha":
                    valor = sancionesImprimir.get(indice).getFecha();
                    break;
                case "motivo":
                    valor = sancionesImprimir.get(indice).getMotivo();
                    break;
                case "numeroResolucion":
                    valor = sancionesImprimir.get(indice).getNumeroResolucion();
                    break;
                case "penalizacion":
                    valor = "-";
                    if (sancionesImprimir.get(indice).getCantFechas() != 0) {
                        valor = Integer.toString(sancionesImprimir.get(indice).getCantFechas()) + " Fechas";
                    }
                    if (sancionesImprimir.get(indice).getVencimiento() != null) {
                        valor = "Hasta " + df.format(sancionesImprimir.get(indice).getVencimiento());
                    }
                    break;
                case "fechasCumplidas":
                    valor = sancionesImprimir.get(indice).getCantFechasCumplidas();
                    break;
                case "partido":
                    valor = sancionesImprimir.get(indice).getUnPartido();
                    break;
            }
        }
        return valor;
    }

}
