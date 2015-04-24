/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import Interfaces.IGestionEquipo;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Torneo;
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
public class FixtureDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    FechaTorneo unaFechaTorneo;
    int indiceFecha = -1;   
    private DateFormat df = DateFormat.getDateInstance();

    public FixtureDS(ControladoraGlobal unaControladoraGlobal, Torneo unTorneo, FechaTorneo unaFecha) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneo;      
        this.unaFechaTorneo = unaFecha;
    }
 

    @Override
    public boolean next() throws JRException {
       return ++indiceFecha < 1; 
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("nombreTorneo".equals(jrf.getName())) {
            valor = this.unTorneo.getNombre();
        } 
        return valor;
    }
    
    public void verReporte() {
        File archivo = new File("reportes/reporteFixture.jasper");
        JasperReport reporte;
        FixtureDS_Fecha unaFechaDS = null;
        if(this.unaFechaTorneo != null){
            List<FechaTorneo> unaFecha = new ArrayList();
            unaFecha.add(unaFechaTorneo);
            unaFechaDS = new FixtureDS_Fecha(unaControladoraGlobal,unaFecha,unTorneo);    
        } else {
            unaFechaDS = new FixtureDS_Fecha(unaControladoraGlobal, (List<FechaTorneo>) unTorneo.getFechasTorneo(), unTorneo);  
        } 
        try {            
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            Map parametros = new HashMap();
            parametros.put("subreport_datasource_fecha", unaFechaDS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, this);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
