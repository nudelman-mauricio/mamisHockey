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

    public HistorialSociaDS(ControladoraGlobal unaControladoraGlobal, Date fechaDesde, Date fechaHasta, Socia unaSocia) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.unaSocia = unaSocia;
    }

    public void verReporte() {

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
        for (Estado unEstado : this.unaSocia.getEstados()) {
            if ((unEstado.getFecha().after(fechaDesde)) && (unEstado.getFecha().before(fechaHasta))) {
                estadosImprimir.add(unEstado);
            }
        }

        HistorialSociaDS_Estados unaPlantelDS = new HistorialSociaDS_Estados(estadosImprimir);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Ergometrias subReporteErgometrias() {
        List<Ergometria> ergometriasImprimir = new ArrayList();
        for (Ergometria unaErgometria : this.unaSocia.getErgometrias()) {
            if ((unaErgometria.getFechaRealizacion().after(fechaDesde)) && (unaErgometria.getFechaRealizacion().before(fechaHasta))) {
                ergometriasImprimir.add(unaErgometria);
            }
        }

        HistorialSociaDS_Ergometrias unaPlantelDS = new HistorialSociaDS_Ergometrias(ergometriasImprimir);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Pases subReportePases() {
        List<Pase> pasesImprimir = new ArrayList();
        for (Pase unPase : this.unaSocia.getPases()) {
            if ((unPase.getFecha().after(fechaDesde)) && (unPase.getFecha().before(fechaHasta))) {
                pasesImprimir.add(unPase);
            }
        }

        HistorialSociaDS_Pases unaPlantelDS = new HistorialSociaDS_Pases(pasesImprimir, this.unaControladoraGlobal);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Contabilidad subReporteContabilidad() {
        List<Deuda> deudaImprimir = new ArrayList();
        for (Deuda unaDeuda : this.unaSocia.getDeudas()) {
            if ((unaDeuda.getFechaGeneracion().after(fechaDesde)) && (unaDeuda.getFechaGeneracion().before(fechaHasta))) {
                deudaImprimir.add(unaDeuda);
            }
        }

        HistorialSociaDS_Contabilidad unaPlantelDS = new HistorialSociaDS_Contabilidad(deudaImprimir);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Sanciones subReporteSanciones() {
        List<SancionTribunal> sancionTribunalImprimir = new ArrayList();
        for (SancionTribunal unaSancionTribunal : this.unaSocia.getSancionesTribunal()) {
            if ((unaSancionTribunal.getFecha().after(fechaDesde)) && (unaSancionTribunal.getFecha().before(fechaHasta))) {
                sancionTribunalImprimir.add(unaSancionTribunal);
            }
        }

        HistorialSociaDS_Sanciones unaPlantelDS = new HistorialSociaDS_Sanciones(sancionTribunalImprimir);
        return unaPlantelDS;
    }

    private HistorialSociaDS_Tarjetas subReporteTarjetas() {
        List<Tarjeta> tarjetasImprimir = new ArrayList();
        for (Tarjeta unaTarjeta : this.unaSocia.getTarjetas()) {
            //No puedo definir cuales son las tarjtas en determinado rango de fechas. Por las tarjetas que son acumuladas
            //if ((unaTarjeta.get().after(fechaDesde)) && (unaTarjeta.getFecha().before(fechaHasta))) {
                tarjetasImprimir.add(unaTarjeta);
            //}
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

        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fecha".equals(jrf.getName())) {
            valor = dateFormat.format(unaControladoraGlobal.fechaSistema());
        } else if ("socia".equals(jrf.getName())) {
            valor = this.unaSocia;
        } else if ("dni".equals(jrf.getName())) {
            valor = this.unaSocia.getDni();
        } else if ("localidad".equals(jrf.getName())) {
            valor = this.unaSocia.getUnaLocalidad().getNombre();
        } else if ("domicilio".equals(jrf.getName())) {
            valor = this.unaSocia.getDomicilio();
        } else if ("email".equals(jrf.getName())) {
            valor = this.unaSocia.getEmail();
        } else if ("fechaNacimiento".equals(jrf.getName())) {
            valor = dateFormat.format(this.unaSocia.getFechaNacimiento());
        } else if ("fechaIngreso".equals(jrf.getName())) {
            valor = dateFormat.format(this.unaSocia.getFechaIngreso());
        } else if ("telFijo".equals(jrf.getName())) {
            valor = this.unaSocia.getTelFijo();
        } else if ("telCel".equals(jrf.getName())) {
            valor = this.unaSocia.getTelCelular();
        } else if ("exJugadora".equals(jrf.getName())) {
            if (this.unaSocia.isExJugadora()) {
                valor = "Si";
            } else {
                valor = "No";
            }
        } else if ("Estados".equals(jrf.getName())) {
            valor = this.subReporteEstados();
        } else if ("Ergometrias".equals(jrf.getName())) {
            valor = this.subReporteErgometrias();
        } else if ("Pases".equals(jrf.getName())) {
            valor = this.subReportePases();
        } else if ("Contabilidad".equals(jrf.getName())) {
            valor = this.subReporteContabilidad();
        } else if ("Sanciones".equals(jrf.getName())) {
            valor = this.subReporteSanciones();
        } else if ("Tarjetas".equals(jrf.getName())) {
            valor = this.subReporteTarjetas();
        }
        if ((valor == null) || ("".equals(valor))) {
            valor = "Falta";
        }
        return valor;
    }
}
