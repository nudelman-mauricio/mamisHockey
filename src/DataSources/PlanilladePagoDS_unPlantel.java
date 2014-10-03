package DataSources;

import java.util.ArrayList;
import java.util.List;
import logicaNegocios.Cuota;
import logicaNegocios.Socia;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Lucas
 */
public class PlanilladePagoDS_unPlantel implements JRDataSource {

    private int indiceSocia = -1;

    private ControladoraGlobal unaControladoraGlobal;
    private List<Socia> sociasPagaron;
    List<Cuota> cuotasPagaron;

    public PlanilladePagoDS_unPlantel(ControladoraGlobal unaControladoraGlobal, List<Socia> sociasPagaron, List<Cuota> cuotasPagaron) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.sociasPagaron = sociasPagaron;
        this.cuotasPagaron = cuotasPagaron;
    }

    private PlanilladePagoDS_unPlantel_unaDeuda subReporte(Socia unaSocia) {
        List<Cuota> CuotasPagadasSocia = new ArrayList();
        for (Cuota unaCuota : this.cuotasPagaron) {
            if (unaSocia.getDeudas().contains(unaControladoraGlobal.getDeudaDeCuota(unaCuota))) {
                CuotasPagadasSocia.add(unaCuota);
                //this.cuotasPagaron.remove(unaCuota);
            }
        }
        PlanilladePagoDS_unPlantel_unaDeuda unaDeudas = new PlanilladePagoDS_unPlantel_unaDeuda(unaControladoraGlobal, CuotasPagadasSocia);
        return unaDeudas;
    }
    
    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {

        return ++indiceSocia < sociasPagaron.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("dni".equals(jrf.getName())) {
            valor = sociasPagaron.get(indiceSocia).getDni();
        } else if ("apellidoNombre".equals(jrf.getName())) {
            valor = sociasPagaron.get(indiceSocia);
        } else if ("subReporte".equals(jrf.getName())) {
            valor = subReporte(sociasPagaron.get(indiceSocia));
        }
        return valor;
    }
}
