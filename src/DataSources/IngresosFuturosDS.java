/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import Interfaces.IGestionIngresosFuturos;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.ConceptoDeportivo;
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
public class IngresosFuturosDS implements JRDataSource {

    int indiceConceptos = -1;
    ArrayList<IGestionIngresosFuturos.ObjectoTabla> unaLista;
    ControladoraGlobal unaControladoraGlobal;
    private DateFormat df = DateFormat.getDateInstance();
    String unTitulo;

   

    public IngresosFuturosDS(ArrayList<IGestionIngresosFuturos.ObjectoTabla> unaLista, ControladoraGlobal unaControladoraGlobal, String titulo) {
        this.unaLista = unaLista;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTitulo = titulo;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceConceptos < unaLista.size();
    }

     @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("concepto".equals(jrf.getName())) {
            valor = unaLista.get(indiceConceptos).getUnConceptoDeportivo().getConcepto();
        } else if ("montoIngresar".equals(jrf.getName())) {
            valor = unaLista.get(indiceConceptos).getMontoAIngresar();
        } else if ("montoVencido".equals(jrf.getName())) {
            valor = unaLista.get(indiceConceptos).getMontoVencido();
        } else if ("tituloReporteIngresosFuturos".equals(jrf.getName())) {
            valor = this.unTitulo;
        }
        return valor;
    }

    public void verReporte() {
        File archivo = new File("reportes/reporteIngresosFuturos.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(PlanilladePagoDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
