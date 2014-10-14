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
import logicaNegocios.IngresoOtro;
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
public class GestionIngresosDS implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    List<IngresoOtro> listaIngresos = new ArrayList();
    int indiceIngresos = -1;
    Date desde, hasta;
    private DateFormat df = DateFormat.getDateInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/YYYY"); 

    public GestionIngresosDS(ControladoraGlobal unaControladoraGlobal, List<IngresoOtro> listaIngresos, Date desde, Date hasta) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.listaIngresos = listaIngresos;
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceIngresos < listaIngresos.size();
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
            valor = df.format(listaIngresos.get(indiceIngresos).getFecha());
        } else if ("Concepto".equals(jrf.getName())) {
            valor = listaIngresos.get(indiceIngresos).getUnConceptoIngreso().getNombre();
        } else if ("Observacion".equals(jrf.getName())) {
            valor = listaIngresos.get(indiceIngresos).getDetalle();
        } else if ("Monto".equals(jrf.getName())) {
            valor = listaIngresos.get(indiceIngresos).getMonto();
        }
        return valor;
    }

    public void verReporte() {
        File archivo = new File("reportes/reporteIngresos.jasper");
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
