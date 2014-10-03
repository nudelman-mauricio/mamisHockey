/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.SimpleDateFormat;
import java.util.List;
import logicaNegocios.Cancha;
import logicaNegocios.Cuota;
import logicaNegocios.Partido;
import logicaNegocios.Equipo;
import logicaNegocios.Jugadora;
import logicaNegocios.Socia;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Lucas
 */
public class PlanilladePagoDS_unPlantel_unaDeuda implements JRDataSource {

    private int indiceCuota = -1;

    private ControladoraGlobal unaControladoraGlobal;
    private List<Cuota> unaCuota;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public PlanilladePagoDS_unPlantel_unaDeuda(ControladoraGlobal unaControladoraGlobal, List<Cuota> unaCuota) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaCuota = unaCuota;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indiceCuota < unaCuota.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if ("idCuota".equals(jrf.getName())) {
            valor = unaCuota.get(indiceCuota).getIdCuota();
        } else if ("fechaCreacion".equals(jrf.getName())) {
            valor = dateFormat.format(unaControladoraGlobal.getDeudaDeCuota(unaCuota.get(indiceCuota)).getFechaGeneracion());
        } else if ("concepto".equals(jrf.getName())) {
            valor = unaControladoraGlobal.getDeudaDeCuota(unaCuota.get(indiceCuota)).getUnConceptoDeportivo().getConcepto();
        } else if ("fechaVencimiento".equals(jrf.getName())) {
            valor = dateFormat.format(unaCuota.get(indiceCuota).getFechaVencimiento());
        } else if ("numeroCuota".equals(jrf.getName())) {
            valor = unaCuota.get(indiceCuota).getNumero();
        } else if ("monto".equals(jrf.getName())) {
            valor = unaCuota.get(indiceCuota).getMonto();
        }
        return valor;
    }
}
