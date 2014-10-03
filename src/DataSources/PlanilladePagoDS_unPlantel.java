package DataSources;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Cancha;
import logicaNegocios.Cuota;
import logicaNegocios.Deuda;
import logicaNegocios.Partido;
import logicaNegocios.Equipo;
import logicaNegocios.Jugadora;
import logicaNegocios.Socia;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Lucas
 */
public class PlanilladePagoDS_unPlantel implements JRDataSource {

    private int indiceSocia = -1;

    private ControladoraGlobal unaControladoraGlobal;
    private List<Socia> unPlantel;

    public PlanilladePagoDS_unPlantel(ControladoraGlobal unaControladoraGlobal, List<Socia> unPlantel) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unPlantel = unPlantel;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {

        return ++indiceSocia < unPlantel.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("dni".equals(jrf.getName())) {
            valor = unPlantel.get(indiceSocia).getDni();
        } else if ("apellidoNombre".equals(jrf.getName())) {
            valor = unPlantel.get(indiceSocia);
        } else if ("subReporte".equals(jrf.getName())) {
            valor = subReporte(unPlantel.get(indiceSocia));
        }
        return valor;
    }

    private PlanilladePagoDS_unPlantel_unaDeuda subReporte(Socia unaSocia) {
        List<Cuota> CuotasPagadas = new ArrayList();
        if (unaSocia.getDni() == 13013763) {
            CuotasPagadas.add(unaControladoraGlobal.getCuotaBD((long) 2951));
            CuotasPagadas.add(unaControladoraGlobal.getCuotaBD((long) 2953));
        }
        if (unaSocia.getDni() == 27800068) {
            CuotasPagadas.add(unaControladoraGlobal.getCuotaBD((long) 2955));
        }
        PlanilladePagoDS_unPlantel_unaDeuda unaDeudas = new PlanilladePagoDS_unPlantel_unaDeuda(unaControladoraGlobal, CuotasPagadas);
        return unaDeudas;
    }
}
