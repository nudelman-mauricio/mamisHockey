package DataSources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Deuda;
import logicaNegocios.Ergometria;
import logicaNegocios.Estado;
import logicaNegocios.Pase;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class HistorialSociaDS implements JRDataSource {

    private int indice = -1;

    private final ControladoraGlobal unaControladoraGlobal;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private final Date fechaDesde, fechaHasta;
    private final Socia unaSocia;
    private final boolean tarjetas, pases, sanciones, ergometrias, contable, estados;

    public HistorialSociaDS(ControladoraGlobal unaControladoraGlobal, Date fechaDesde, Date fechaHasta, Socia unaSocia, boolean tarjetas, boolean pases, boolean sanciones, boolean ergometrias, boolean contable, boolean estados) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.unaSocia = unaSocia;
        this.tarjetas = tarjetas;
        this.pases = pases;
        this.sanciones = sanciones;
        this.ergometrias = ergometrias;
        this.contable = contable;
        this.estados = estados;
    }

    public void verReporte() {
        System.out.println(this.unaSocia);
        System.out.println(this.unaSocia.getDeudas().size());
        System.out.println(this.unaSocia.getTarjetas().size());
        System.out.println(this.unaSocia.getPases().size());
        System.out.println(this.unaSocia.getEstados().size());
        System.out.println(this.unaSocia.getErgometrias().size());
        
        
        File archivo = new File("reportes/reporteHistorialSocia.jasper");
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

    private HistorialSociaDS_Estados subReporteEstados() {
        List<Estado> estadosImprimir = new ArrayList();
        if (this.estados) {
            for (Estado unEstado : this.unaSocia.getEstados()) {
                if ((unEstado.getFecha().after(fechaDesde)) && (unEstado.getFecha().before(fechaHasta))) {
                    estadosImprimir.add(unEstado);
                }
            }
        }
        HistorialSociaDS_Estados unaPlantelDS = new HistorialSociaDS_Estados(estadosImprimir);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Ergometrias subReporteErgometrias() {
        List<Ergometria> ergometriasImprimir = new ArrayList();
        if (this.ergometrias) {
            for (Ergometria unaErgometria : this.unaSocia.getErgometrias()) {
                if ((unaErgometria.getFechaRealizacion().after(fechaDesde)) && (unaErgometria.getFechaRealizacion().before(fechaHasta))) {
                    ergometriasImprimir.add(unaErgometria);
                }
            }
        }
        HistorialSociaDS_Ergometrias unaPlantelDS = new HistorialSociaDS_Ergometrias(ergometriasImprimir);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Pases subReportePases() {
        List<Pase> pasesImprimir = new ArrayList();
        if (this.pases) {
            for (Pase unPase : this.unaSocia.getPases()) {
                if ((unPase.getFecha().after(fechaDesde)) && (unPase.getFecha().before(fechaHasta))) {
                    pasesImprimir.add(unPase);
                }
            }
        }
        HistorialSociaDS_Pases unaPlantelDS = new HistorialSociaDS_Pases(pasesImprimir, this.unaControladoraGlobal);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Contabilidad subReporteContabilidad() {
        List<Deuda> deudaImprimir = new ArrayList();
        if (this.contable) {
            for (Deuda unaDeuda : this.unaSocia.getDeudas()) {
                if ((unaDeuda.getFechaGeneracion().after(fechaDesde)) && (unaDeuda.getFechaGeneracion().before(fechaHasta))) {
                    deudaImprimir.add(unaDeuda);
                }
            }
        }
        HistorialSociaDS_Contabilidad unaPlantelDS = new HistorialSociaDS_Contabilidad(deudaImprimir, this.unaControladoraGlobal);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Sanciones subReporteSanciones() {
        List<SancionTribunal> sancionTribunalImprimir = new ArrayList();
        if (this.sanciones) {
            for (SancionTribunal unaSancionTribunal : this.unaSocia.getSancionesTribunal()) {
                if ((unaSancionTribunal.getFecha().after(fechaDesde)) && (unaSancionTribunal.getFecha().before(fechaHasta))) {
                    sancionTribunalImprimir.add(unaSancionTribunal);
                }
            }
        }
        HistorialSociaDS_Sanciones unaPlantelDS = new HistorialSociaDS_Sanciones(sancionTribunalImprimir);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Tarjetas subReporteTarjetas() {
        List<Tarjeta> tarjetasImprimir = new ArrayList();
        if (this.tarjetas) {
            for (Tarjeta unaTarjeta : this.unaSocia.getTarjetas()) {
                if ((unaTarjeta.getFecha().after(fechaDesde)) && (unaTarjeta.getFecha().before(fechaHasta))) {
                tarjetasImprimir.add(unaTarjeta);
                }
            }
        }
        HistorialSociaDS_Tarjetas unaPlantelDS = new HistorialSociaDS_Tarjetas(tarjetasImprimir, this.unaControladoraGlobal);
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
        if (null != jrf.getName()) switch (jrf.getName()) {
            case "ruta":
                valor = unaControladoraGlobal.rutaSistema();
                break;
            case "fecha":
                valor = dateFormat.format(unaControladoraGlobal.fechaSistema());
                break;
            case "socia":
                valor = this.unaSocia;
                break;
            case "dni":
                valor = this.unaSocia.getDni();
                break;
            case "localidad":
                valor = this.unaSocia.getUnaLocalidad().getNombre();
                break;
            case "domicilio":
                valor = this.unaSocia.getDomicilio();
                break;
            case "email":
                valor = this.unaSocia.getEmail();
                break;
            case "fechaNacimiento":
                valor = dateFormat.format(this.unaSocia.getFechaNacimiento());
                break;
            case "fechaIngreso":
                valor = dateFormat.format(this.unaSocia.getFechaIngreso());
                break;
            case "telFijo":
                valor = this.unaSocia.getTelFijo();
                break;
            case "telCel":
                valor = this.unaSocia.getTelCelular();
                break;
            case "exJugadora":
                if (this.unaSocia.isExJugadora()) {
                    valor = "Si";
                } else {
                    valor = "No";
                }   break;
            case "Estados":
                valor = this.subReporteEstados();
                break;
            case "Ergometrias":
                valor = this.subReporteErgometrias();
                break;
            case "Pases":
                valor = this.subReportePases();
                break;
            case "Contabilidad":
                valor = this.subReporteContabilidad();
                break;
            case "Sanciones":
                valor = this.subReporteSanciones();
                break;
            case "Tarjetas":
                valor = this.subReporteTarjetas();
                break;
        }
        if ((valor == null) || ("".equals(valor))) {
            valor = "Falta";
        }
        return valor;
    }
}
