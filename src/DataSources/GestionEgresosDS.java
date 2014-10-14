/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import Interfaces.IGestionEquipo;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Egreso;
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
 * @author Leanwit
 */
public class GestionEgresosDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    List<Egreso> listaEgresos = new ArrayList();
    int indiceEgresos = -1;
    Date desde, hasta;
    private DateFormat df = DateFormat.getDateInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/YYYY"); 

    public GestionEgresosDS(ControladoraGlobal unaControladoraGlobal, List<Egreso> listaEgresos, Date desde, Date hasta) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.listaEgresos = listaEgresos;
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceEgresos < listaEgresos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("desde".equals(jrf.getName())) {
            valor = dateFormat.format(this.desde);
        } else if ("hasta".equals(jrf.getName())) {
            valor = dateFormat.format(this.hasta);
        } else if ("FechaConcepto".equals(jrf.getName())) {
            valor = df.format(listaEgresos.get(indiceEgresos).getFecha());
        } else if ("Concepto".equals(jrf.getName())) {
            valor = listaEgresos.get(indiceEgresos).getUnConceptoEgreso().getNombre();
        }  else if ("Observacion".equals(jrf.getName())) {
            valor = listaEgresos.get(indiceEgresos).getObservacion();
        }  else if ("Monto".equals(jrf.getName())) {
            valor = listaEgresos.get(indiceEgresos).getMonto();
        } 
        return valor;
    }

     public void verReporte() {
        File archivo = new File("reportes/reporteEgresos.jasper");
        JasperReport reporte;
        try {            
            reporte = (JasperReport) JRLoader.loadObject(archivo);            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
