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
public class EquiposTorneoDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    int indiceEquipos = -1;
    List<Equipo> equipos;
    private DateFormat df = DateFormat.getDateInstance();

    public EquiposTorneoDS(ControladoraGlobal unaControladoraGlobal, Torneo unTorneo) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unTorneo = unTorneo;
        this.equipos = (List<Equipo>) unTorneo.getEquiposInscriptos();
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceEquipos < equipos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("torneo".equals(jrf.getName())) {
            valor = unTorneo.getNombre();
        } else if ("categoria".equals(jrf.getName())) {
            valor = unTorneo.getUnaCategoria();
        } else if ("equipo".equals(jrf.getName())) {
            valor = equipos.get(indiceEquipos).getNombre();
        } else if ("club".equals(jrf.getName())) {
            valor = unaControladoraGlobal.getClubBD(equipos.get(indiceEquipos)).getNombre();
        } else if ("dt".equals(jrf.getName())) {
            valor = equipos.get(indiceEquipos).getUnDT();
        } else if ("delegada".equals(jrf.getName())) {
            valor = equipos.get(indiceEquipos).getUnaDelegada();
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        }
        return valor;
    }

    public void verReporte() {
        File archivo = new File("reportes/reporteEquiposTorneo.jasper");
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
