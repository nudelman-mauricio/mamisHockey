package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Estado;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistorialSociaDS_Estados  implements JRDataSource {

    private int indiceEstado = -1;

    private final List<Estado> estadosImprimir;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public HistorialSociaDS_Estados(List<Estado> estadosImprimir) {
        this.estadosImprimir = estadosImprimir;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceEstado < estadosImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) //General
        switch (jrf.getName()) {
            case "fecha":
                valor = dateFormat.format(estadosImprimir.get(indiceEstado).getFecha());
                break;
            case "estado":
                valor = estadosImprimir.get(indiceEstado).getUnTipoEstado().getNombre();
                break;
        }
        return valor;
    }    
}
