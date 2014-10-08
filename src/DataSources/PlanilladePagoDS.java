package DataSources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Cuota;
import logicaNegocios.Socia;
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
 * @author Lucas
 */
public class PlanilladePagoDS implements JRDataSource {

    private int indice = -1;

    private ControladoraGlobal unaControladoraGlobal;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private String titulo, idPlanilla, nombrePago, costoCancha, subTotal, total, idRecibo;
    private List<Socia> sociasPagaron;
    private List<Cuota> cuotasPagaron;

    public PlanilladePagoDS(ControladoraGlobal unaControladoraGlobal, String titulo, String idPlanilla, String nombrePago, String costoCancha, String subTotal, String total, String idRecibo, List<Socia> sociasPagaron, List<Cuota> cuotasPagaron) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.titulo = titulo;
        this.idPlanilla = idPlanilla;
        this.nombrePago = nombrePago;
        this.costoCancha = costoCancha;
        this.subTotal = subTotal;
        this.total = total;
        this.idRecibo = idRecibo;
        this.sociasPagaron = sociasPagaron;
        this.cuotasPagaron = cuotasPagaron;

    }

    public void verReporte(String nombrePDF) {

        File archivo = new File("reportes/reportePlanillaPagos.jasper");
        JasperReport reporte;

        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("Planillas de Pago/" + nombrePDF + ".pdf"));
            exporter.exportReport();

        } catch (JRException ex) {
            Logger.getLogger(PlanilladePagoDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PlanilladePagoDS_unPlantel subReporte() {
        PlanilladePagoDS_unPlantel unaPlantelDS = new PlanilladePagoDS_unPlantel(unaControladoraGlobal, this.sociasPagaron, this.cuotasPagaron);
        return unaPlantelDS;
    }

    //Sector de la impresion del datasource
    @Override
    public boolean next() throws JRException {
        return ++indice < 1;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General

        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fecha".equals(jrf.getName())) {
            valor = dateFormat.format(unaControladoraGlobal.fechaSistema());
        } else if ("idPlanilla".equals(jrf.getName())) {
            valor = this.idPlanilla;
        } else if ("titulo".equals(jrf.getName())) {
            valor = this.titulo;
        } else if ("nombrePago".equals(jrf.getName())) {
            valor = this.nombrePago;
        } else if ("costoCancha".equals(jrf.getName())) {
            valor = this.costoCancha;
        } else if ("subTotal".equals(jrf.getName())) {
            valor = this.subTotal;
        } else if ("total".equals(jrf.getName())) {
            valor = this.total;
        }  else if ("idRecibo".equals(jrf.getName())) {
            valor = this.idRecibo;
        } else if ("subReporte".equals(jrf.getName())) {
            valor = this.subReporte();
        }
        if ((valor == null) || ("".equals(valor))) {
            valor = "Falta";
        }
        return valor;
    }
}
