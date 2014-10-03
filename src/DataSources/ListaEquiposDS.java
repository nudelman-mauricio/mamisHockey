/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import Interfaces.IGestionEquipo;
import java.io.File;
import java.text.DateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Equipo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Leanwit
 */
public class ListaEquiposDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    List<Equipo> equipos;
    int indiceEquipo = -1;
     private DateFormat df = DateFormat.getDateInstance();

    public ListaEquiposDS(ControladoraGlobal unaControladoraGlobal, List<Equipo> equipos) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.equipos = equipos;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceEquipo < equipos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;

        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("nombre".equals(jrf.getName())) {
            valor = equipos.get(indiceEquipo).getNombre();
        } else if ("club".equals(jrf.getName())) {
            valor = unaControladoraGlobal.getClubBD(equipos.get(indiceEquipo)).getNombre();
        } else if ("delegada".equals(jrf.getName())) {
            valor = equipos.get(indiceEquipo).getUnaDelegada();
        } else if ("directorTecnico".equals(jrf.getName())) {
            valor = equipos.get(indiceEquipo).getUnDT();
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        }
        return valor;
    }

    public String verReporte(boolean crearPDF) {
        File archivo = new File("reportes/reporteListaEquipos.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            if (crearPDF) {
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("listaEquipos.pdf"));
                exporter.exportReport();
            } else {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);
            }

        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "listaEquipos.pdf";
    }
}
