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
import logicaNegocios.PersonaAuxiliar;
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
public class ListaPersonaAuxiliarDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    List<PersonaAuxiliar> personasAuxiliares;
    int indicePersonaAuxiliar = -1;
    String opcion;
     private DateFormat df = DateFormat.getDateInstance();

    public ListaPersonaAuxiliarDS(ControladoraGlobal unaControladoraGlobal, List<PersonaAuxiliar> personasAuxiliares, String opcion) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.personasAuxiliares = personasAuxiliares;
        this.opcion = opcion;
    }

    @Override
    public boolean next() throws JRException {
        return ++indicePersonaAuxiliar < personasAuxiliares.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;

        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("filtro".equals(jrf.getName())) {
            valor = opcion;
        } else if ("dni".equals(jrf.getName())) {
            valor = personasAuxiliares.get(indicePersonaAuxiliar).getDni();
        } else if ("apellido y nombre".equals(jrf.getName())) {
            valor = personasAuxiliares.get(indicePersonaAuxiliar);
        } else if ("localidad".equals(jrf.getName())) {
            valor = personasAuxiliares.get(indicePersonaAuxiliar).getUnaLocalidad();
        } else if ("domicilio".equals(jrf.getName())) {
            valor = personasAuxiliares.get(indicePersonaAuxiliar).getDomicilio();
        } else if ("fechaNacimiento".equals(jrf.getName())) {
            valor = df.format(personasAuxiliares.get(indicePersonaAuxiliar).getFechaNacimiento());
        } else if ("fechaIngreso".equals(jrf.getName())) {
            valor = df.format(personasAuxiliares.get(indicePersonaAuxiliar).getFechaIngreso());
        }
        return valor;
    }

    public String verReporte(boolean crearPDF) {
        File archivo = new File("reportes/reporteListaPersonasAuxiliares.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            if (crearPDF) {
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("listaPersonasAuxiliares.pdf"));
                exporter.exportReport();
            } else {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);
            }

        } catch (JRException ex) {
            Logger.getLogger(IGestionEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "listaPersonasAuxiliares.pdf";
    }
}
