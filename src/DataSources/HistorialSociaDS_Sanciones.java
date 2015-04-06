package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.SancionTribunal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistorialSociaDS_Sanciones implements JRDataSource {

    private int indiceSancion = -1;

    private final List<SancionTribunal> sancionesImprimir;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public HistorialSociaDS_Sanciones(List<SancionTribunal> sancionesImprimir) {
        this.sancionesImprimir = sancionesImprimir;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceSancion < sancionesImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) //General
        {
            switch (jrf.getName()) {
                case "fecha":
                    valor = dateFormat.format(sancionesImprimir.get(indiceSancion).getFecha());
                    break;
                case "nResolucion":
                    if (sancionesImprimir.get(indiceSancion).getNumeroResolucion() != null) {
                        valor = sancionesImprimir.get(indiceSancion).getNumeroResolucion();
                    } else {
                        valor = "-";
                    }
                    break;
                case "motivo":
                    valor = sancionesImprimir.get(indiceSancion).getMotivo();
                    break;
                case "partido":
                    if (sancionesImprimir.get(indiceSancion).getUnPartido() == null) {
                        valor = "Sanción sin Partido";
                    } else {
                        valor = sancionesImprimir.get(indiceSancion).getUnPartido().getUnEquipoLocal() + " vs " + sancionesImprimir.get(indiceSancion).getUnPartido().getUnEquipoVisitante();
                    }
                    break;
            }
        }
        return valor;
    }
}
