/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Partido;
import logicaNegocios.Socia;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lucas
 */
public class PlanilladePagoDS implements JRDataSource{
    private int indice = -1;

    private ControladoraGlobal unaControladoraGlobal;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private String titulo, idPlanilla, nombrePago, costoCancha, subTotal, total;
    
    PlanilladePagoDS_unPlantel unPlantelDS;


    public PlanilladePagoDS(ControladoraGlobal unaControladoraGlobal, String titulo, String idPlanilla, String nombrePago, String costoCancha, String subTotal, String total, PlanilladePagoDS_unPlantel unPlantelDS) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.titulo = titulo;
        this.idPlanilla = idPlanilla;
        this.nombrePago = nombrePago;
        this.costoCancha = costoCancha;
        this.subTotal = subTotal;
        this.total = total;
        
        this.unPlantelDS = unPlantelDS;
    }
    
    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indice < 1;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fecha".equals(jrf.getName())) {
            valor = dateFormat.format(unaControladoraGlobal.fechaSistema());
        } else if ("idPlanilla".equals(jrf.getName())) {
            valor = this.idPlanilla;
        } else if ("titulo".equals(jrf.getName())) {
            valor = this.titulo;
        }else if ("nombrePago".equals(jrf.getName())) {
            valor = this.nombrePago;
        } else if ("costoCancha".equals(jrf.getName())) {
            valor = this.costoCancha;
        } else if ("subTotal".equals(jrf.getName())) {
            valor = this.subTotal;
        } else if ("total".equals(jrf.getName())) {
            valor = this.total;
        }   else if ("subReporte".equals(jrf.getName())) {
            valor = unPlantelDS;
        }
        if ((valor == null) || ("".equals(valor))) {
            valor = "Falta";
        }
        return valor;
    }
}
