package DataSources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Deuda;
import logicaNegocios.Pase;
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

public class FormularioPaseDS implements JRDataSource {

    private int indice = -1;

    private final ControladoraGlobal unaControladoraGlobal;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private final Socia unaSocia;
    private final Pase unPase;

    public FormularioPaseDS(ControladoraGlobal unaControladoraGlobal, Socia unaSocia, Pase unPase) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaSocia = unaSocia;
        this.unPase = unPase;
    }

    public void verReporte() {

        File archivo = new File("formularios/formularioPase.jasper");
        JasperReport reporte;

        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("Planillas de Pago/" + nombrePDF + ".pdf"));
//            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(PlanilladePagoDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HistorialSociaDS_Contabilidad subReporteContabilidad() {
        List<Deuda> deudaImprimir = new ArrayList();
        deudaImprimir.add(unPase.getUnaDeuda());
        HistorialSociaDS_Contabilidad unaPlantelDS = new HistorialSociaDS_Contabilidad(deudaImprimir, this.unaControladoraGlobal);
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
        if (null != jrf.getName()) {
            switch (jrf.getName()) {
                case "ruta":
                    valor = unaControladoraGlobal.rutaSistema();
                    break;
                case "fecha":
                    valor = dateFormat.format(this.unPase.getFecha());
                    break;
                case "socia":
                    valor = this.unaSocia;
                    break;
                case "equipoOrigen":
                    valor = "falta";
                    break;
                case "equipoDestino":
                    if (this.unPase.getUnEquipo() == null) {
                        valor = "Pase Abierto";
                    } else {
                        valor = this.unPase.getUnEquipo().getNombre();
                    }
                    break;
                case "observacion":
                    valor = this.unPase.getObservacion();
                    break;
                case "monto":
                    valor = this.unPase.getUnaDeuda().getMontoTotal();
                    break;
                case "cuotas":
                    valor = this.unPase.getUnaDeuda().getCuotas().size();
                    break;
                case "libreDeuda":
                    if (this.unPase.isLibreDeudaClub()) {
                        valor = "Si";
                    } else {
                        valor = "No";
                    }
                    break;
                case "solicitudPase":
                    if (this.unPase.isSolicitudPase()) {
                        valor = "Si";
                    } else {
                        valor = "No";
                    }
                    break;
                case "idPase":
                    valor = this.unPase.getIdPase();
                    break;
                case "nPase":
                    int cont = 0;
                    for (Pase unPase : unaSocia.getPases()) {
                        if (!unPase.isBorradoLogico()) {
                            cont++;
                        }
                    }
                    valor = cont;
                    break;
                case "Contabilidad":
                    valor = this.subReporteContabilidad();
                    break;
            }
        }
        if ((valor == null) || ("".equals(valor))) {
            valor = "Falta";
        }
        return valor;
    }
}
