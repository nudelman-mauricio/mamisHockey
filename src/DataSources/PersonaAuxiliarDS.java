/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import Interfaces.IGestionEquipo;
import java.io.File;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Jugadora_;
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
public class PersonaAuxiliarDS implements JRDataSource {

    int indicePersonaAuxiliar = -1;
    ControladoraGlobal unaControladoraGlobal;
    PersonaAuxiliar unaPersonaAuxiliar;
    private DateFormat df = DateFormat.getDateInstance();

    public PersonaAuxiliarDS(ControladoraGlobal unaControladoraGlobal, PersonaAuxiliar unaPersonaAuxiliar) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaPersonaAuxiliar = unaPersonaAuxiliar;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("dni".equals(jrf.getName())) {
            valor = unaPersonaAuxiliar.getDni();
            } else if ("auxiliar".equals(jrf.getName())) {
            valor = unaPersonaAuxiliar.getApellido() + ", "+unaPersonaAuxiliar.getNombre();
        } else if ("localidad".equals(jrf.getName())) {
            valor = unaPersonaAuxiliar.getUnaLocalidad();
        } else if ("domicilio".equals(jrf.getName())) {
            valor = unaPersonaAuxiliar.getDomicilio();
        } else if ("email".equals(jrf.getName())) {
            valor = unaPersonaAuxiliar.getEmail();
        } else if ("fechaNacimiento".equals(jrf.getName())) {
            valor = df.format(unaPersonaAuxiliar.getFechaNacimiento());
        } else if ("fechaIngreso".equals(jrf.getName())) {
            valor = df.format(unaPersonaAuxiliar.getFechaIngreso());
        } else if ("telFijo".equals(jrf.getName())) {
            valor = unaPersonaAuxiliar.getTelFijo();
        } else if ("telCel".equals(jrf.getName())) {
            valor = unaPersonaAuxiliar.getTelCelular();
        } else if ("cargo".equals(jrf.getName())) {
            String cargo = "";
            if (unaPersonaAuxiliar.isArbitro()) {
                cargo = "Arbitro";
            }
            if (unaPersonaAuxiliar.isCuerpoTecnico()) {
                if (cargo.equals("")) {
                    cargo = cargo.concat("Cuerpo Tecnico");
                } else {
                    cargo = cargo.concat(" - Cuerpo Tecnico");
                }
            }
            if (unaPersonaAuxiliar.isPlantaPermanente()) {
                if (cargo.equals("")) {
                    cargo = cargo.concat("Planta Permanente");
                } else {
                    cargo = cargo.concat(" - Planta Permanente");
                }
            }
            valor = cargo;
        } else if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        }
        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indicePersonaAuxiliar < 1;
    }

    public void verReporte() {

        File archivo = new File("reportes/reporteAuxiliar.jasper");
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
