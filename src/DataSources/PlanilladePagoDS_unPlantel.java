package DataSources;

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

    private void Reporte() {
        List<Cuota> Cuotas = null;

        //Crea la fecha para traer las cuotas que vencieron o estan por vencer en un mes mas, hasta el dia 8
        Date fechaHasta = unaControladoraGlobal.fechaSistema();
        fechaHasta.setMonth(fechaHasta.getMonth() + 1);
        fechaHasta.setDate(8);

        //Recorrido de las deudas para pagar
        for (Deuda unaDeuda : unaSocia.getDeudas()) {
            if ((!unaDeuda.isBorradoLogico()) && (!unaDeuda.isSaldado())) {
                for (Cuota unaCuota : unaDeuda.getCuotas()) {
                    if ((unaCuota.getFechaVencimiento().before(fechaHasta)) && (!unaCuota.isSaldado())) {
                        Cuotas.add(unaCuota);
                    }
                }
            }
        }
        
        
        PlanilladePagoDS_unPlantel_unaDeuda unaDeudaDS = new PlanilladePagoDS_unPlantel_unaDeuda(unaControladoraGlobal, Cuotas);

        Map parameters = new HashMap();
        parameters.put("subreport_datasource_unPlantel_unaDeuda", unaDeudaDS);
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
        if ("dni".equals(jrf.getName())) {
            valor = unPlantel.get(indiceSocia).getDni();
        } else if ("apellidoNombre".equals(jrf.getName())) {
            valor = unPlantel.get(indiceSocia);
        }
        return valor;
    }
}
