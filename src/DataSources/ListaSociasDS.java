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
 * @author Leanwit
 */
public class ListaSociasDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    List<Socia> socias;
    int indiceSocias = -1;

    private final DateFormat df = DateFormat.getDateInstance();

    public ListaSociasDS(ControladoraGlobal unaControladoraGlobal, List<Socia> socias) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.socias = socias;

    }

    @Override
    public boolean next() throws JRException {
        return ++indiceSocias < socias.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;

        if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        } else if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("dni".equals(jrf.getName())) {
            valor = socias.get(indiceSocias).getDni();
        } else if ("apellidoynombre".equals(jrf.getName())) {
            valor = socias.get(indiceSocias);
        } else if ("exJugadora".equals(jrf.getName())) {
            if(socias.get(indiceSocias).isExJugadora()){
                valor = "X";
            } else {
                valor = "";
            }            
        } else if ("estado".equals(jrf.getName())) {
            valor = socias.get(indiceSocias).getUltimoEstado().getUnTipoEstado().getNombre();
        } else if ("equipo".equals(jrf.getName())) {
            valor = socias.get(indiceSocias).getEquipoActual();
        }
        return valor;
    }

    public void verReporte() {
        File archivo = new File("reportes/reporteListaSocias.jasper");
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
