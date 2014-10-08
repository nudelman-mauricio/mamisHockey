package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Cuota;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistorialSociaDS_Contabilidad_Cuotas implements JRDataSource {

    private int indiceCuota = -1;

    private final List<Cuota> cuotasImprimir;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public HistorialSociaDS_Contabilidad_Cuotas(List<Cuota> cuotasImprimir) {
        this.cuotasImprimir = cuotasImprimir;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceCuota < cuotasImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) //General
        {
            switch (jrf.getName()) {
                case "cuota":
                    valor = cuotasImprimir.get(indiceCuota).getNumero();
                    break;
                case "vencimiento":
                    valor = dateFormat.format(cuotasImprimir.get(indiceCuota).getFechaVencimiento());
                    break;
                case "monto":
                    valor = cuotasImprimir.get(indiceCuota).getMonto();
                    break;
                case "fechaPago":
                    valor = dateFormat.format(cuotasImprimir.get(indiceCuota).getFechaPago());
                    break;
            }
        }
        return valor;
    }
}
