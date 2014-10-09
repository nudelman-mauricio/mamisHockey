package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Cuota;
import logicaNegocios.Deuda;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HistorialSociaDS_Contabilidad implements JRDataSource {

    private int indiceDeuda = -1;

    private final List<Deuda> deudasImprimir;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private final ControladoraGlobal unaControladoraGlobal;

    public HistorialSociaDS_Contabilidad(List<Deuda> deudasImprimir, ControladoraGlobal unaControladoraGlobal) {
        this.deudasImprimir = deudasImprimir;
        this.unaControladoraGlobal = unaControladoraGlobal;
    }

    private HistorialSociaDS_Contabilidad_Cuotas subReporte(List<Cuota> cuotas) {
        HistorialSociaDS_Contabilidad_Cuotas cuotasDS = new HistorialSociaDS_Contabilidad_Cuotas(cuotas);
        return cuotasDS;
    }
    
    
    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceDeuda < deudasImprimir.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) //General
        {
            switch (jrf.getName()) {
                case "ruta":
                valor = unaControladoraGlobal.rutaSistema();
                break;
                case "fecha":
                    valor = dateFormat.format(deudasImprimir.get(indiceDeuda).getFechaGeneracion());
                    break;
                case "concepto":
                    valor = deudasImprimir.get(indiceDeuda).getUnConceptoDeportivo().getConcepto();
                    break;
                case "montoTotal":
                    valor = deudasImprimir.get(indiceDeuda).getMontoTotal();
                    break;
                case "saldada":
                    if (deudasImprimir.get(indiceDeuda).isSaldado()){
                        valor = "Si";
                    }else{
                        valor = "No";
                    }
                    break;
                case "subReporte":
                    valor = subReporte((List<Cuota>) deudasImprimir.get(indiceDeuda).getCuotas());
                    break;
            }
        }
        return valor;
    }
}
