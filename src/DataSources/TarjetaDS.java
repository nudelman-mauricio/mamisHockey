package DataSources;

import Interfaces.IGestionEquipo;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.IngresoOtro;
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
public class TarjetaDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    List<IngresoOtro> listaIngresos = new ArrayList();
    int indiceTorneo = -1;
    List<Torneo> torneos = new ArrayList();
    private DateFormat df = DateFormat.getDateInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/YYYY"); 

    public TarjetaDS(ControladoraGlobal unaControladoraGlobal, List<Torneo> torneos) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.torneos = torneos;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceTorneo < torneos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());        
        } else if ("Monto".equals(jrf.getName())) {
            valor = listaIngresos.get(indiceTorneo).getMonto();
        }
        return valor;
    }

    public void verReporte() {
        File archivo = new File("reportes/reportTarjeta.jasper");
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
