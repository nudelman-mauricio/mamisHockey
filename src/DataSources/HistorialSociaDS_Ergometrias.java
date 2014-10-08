package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Ergometria;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistorialSociaDS_Ergometrias implements JRDataSource {

    private int indiceErgometrias = -1;

    private final List<Ergometria> ergometriasImprimir;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public HistorialSociaDS_Ergometrias(List<Ergometria> ergometriasImprimir) {
        this.ergometriasImprimir = ergometriasImprimir;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceErgometrias < ergometriasImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) //General
        {
            switch (jrf.getName()) {
                case "realizacion":
                    valor = dateFormat.format(ergometriasImprimir.get(indiceErgometrias).getFechaRealizacion());
                    break;
                case "caducidad":
                    valor = dateFormat.format(ergometriasImprimir.get(indiceErgometrias).getFechaCaducidad());
                    break;
                case "resultado":
                    if (ergometriasImprimir.get(indiceErgometrias).isAprobado()) {
                        valor = "Aprobado";
                    } else {
                        valor = "Desaprobado";
                    }
                    break;
                case "comentario":
                    valor = ergometriasImprimir.get(indiceErgometrias).getComentarios();
                    break;
            }
        }
        return valor;
    }
}
