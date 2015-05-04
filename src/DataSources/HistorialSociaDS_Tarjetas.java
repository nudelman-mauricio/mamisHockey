package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Partido;
import logicaNegocios.Tarjeta;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistorialSociaDS_Tarjetas implements JRDataSource {

    private int indiceTarjeta = -1;

    private final List<Tarjeta> tarjetasImprimir;
    private final ControladoraGlobal unaControladoraGlobal;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public HistorialSociaDS_Tarjetas(List<Tarjeta> tarjetasImprimir, ControladoraGlobal unaControladoraGlobal) {
        this.tarjetasImprimir = tarjetasImprimir;
        this.unaControladoraGlobal = unaControladoraGlobal;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceTarjeta < tarjetasImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) //General
        {
            switch (jrf.getName()) {
                case "fecha":
                    valor = dateFormat.format(tarjetasImprimir.get(indiceTarjeta).getFecha());
                    break;
                case "tipoTarjeta":
                    valor = tarjetasImprimir.get(indiceTarjeta).getTipo();
                    break;
                case "torneo":
                    if (unaControladoraGlobal.getTorneoTarjeta(tarjetasImprimir.get(indiceTarjeta)) != null){
                        valor = unaControladoraGlobal.getTorneoTarjeta(tarjetasImprimir.get(indiceTarjeta));   
                    }else{
                        valor = "-";
                    }
                    break;
                case "partido":
                    if (unaControladoraGlobal.getPartidoTarjeta(tarjetasImprimir.get(indiceTarjeta)) != null){
                        Partido unPartido = unaControladoraGlobal.getPartidoTarjeta(tarjetasImprimir.get(indiceTarjeta));   
                        valor = unPartido.getUnEquipoLocal()+ " vs " + unPartido.getUnEquipoVisitante();
                    }else{
                        valor = "Por Acumulación";
                    }
                    break;
            }
        }
        return valor;
    }
}