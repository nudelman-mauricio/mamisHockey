package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Pase;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistorialSociaDS_Pases implements JRDataSource {

    private int indiceEstado = -1;

    private final List<Pase> pasesImprimir;
    private final ControladoraGlobal unaControladoraGlobal;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public HistorialSociaDS_Pases(List<Pase> pasesImprimir, ControladoraGlobal unaControladoraGlobal) {
        this.pasesImprimir = pasesImprimir;
        this.unaControladoraGlobal = unaControladoraGlobal;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceEstado < pasesImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) //General
        {
            switch (jrf.getName()) {
                case "nPase":
                    valor = pasesImprimir.get(indiceEstado);
                    break;
                case "fecha":
                    valor = dateFormat.format(pasesImprimir.get(indiceEstado).getFecha());
                    break;
                case "equipoOrigen":
                    //valor = unaControladoraGlobal.gete   HABIA ESTA FUNCION. No la encuentro
                    valor = "Falta";
                    break;
                case "equipoDestino":
                    valor = pasesImprimir.get(indiceEstado).getUnEquipo();
                    break;
                case "monto":
                    valor = pasesImprimir.get(indiceEstado).getUnaDeuda().getMontoTotal();
                    break;
            }
        }
        return valor;
    }
}
