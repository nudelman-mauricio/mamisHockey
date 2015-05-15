/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.Deuda;
import logicaNegocios.Equipo;
import logicaNegocios.PlanillaPago;
import logicaNegocios.SancionTribunal;
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
public class HistorialEquipoDS implements JRDataSource {

    private int indice = -1;

    private final ControladoraGlobal unaControladoraGlobal;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private final Date fechaDesde, fechaHasta;
    private final Equipo unEquipo;
    private final boolean plantel, deuda, pagos, sancion;

    public HistorialEquipoDS(ControladoraGlobal unaControladoraGlobal, Date fechaDesde, Date fechaHasta, Equipo unEquipo, boolean plantel, boolean deuda, boolean pagos, boolean sancion) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.unEquipo = unEquipo;
        this.plantel = plantel;
        this.deuda = deuda;
        this.pagos = pagos;
        this.sancion = sancion;
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
                    valor = dateFormat.format(unaControladoraGlobal.fechaSistema());
                    break;
                case "club":
                    valor = unaControladoraGlobal.getClubBD(this.unEquipo);
                    break;
                case "dt":
                    valor = this.unEquipo.getUnDT();
                    break;
                case "preparadorFisico":
                    valor = this.unEquipo.getUnPreparadorFisico();
                    break;
                case "ayudanteCampo":
                    valor = this.unEquipo.getUnAyudanteCampo();
                    break;
                case "delegada":
                    valor = this.unEquipo.getUnaDelegada();
                    break;
                case "delegadaSuplente":
                    valor = this.unEquipo.getUnaDelegadaSuplente();
                    break;
                case "capitana":
                    valor = this.unEquipo.getUnaCapitana();
                    break;
                case "capitanaSuplente":
                    valor = this.unEquipo.getUnaCapitanaSuplente();
                    break;
                case "Pagos":
                    valor = this.subReportePagos();
                    break;
                case "Plantel":
                    valor = this.subReportePlantel();
                    break;
                case "Sanciones":
                    valor = this.subReporteSanciones();
                    break;
                case "Deudas":
                    valor = this.subReporteDeudas();
                    break;
                case "equipo":
                    valor = this.unEquipo.getNombre();
                    break;
            }
        }
        return valor;
    }

    public void verReporte() {
        File archivo = new File("reportes/reporteHistorialEquipo.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, this);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(PlanilladePagoDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HistorialEquipoDS_Pago subReportePagos() {
        List<PlanillaPago> planillasImprimir = new ArrayList();
        if (this.pagos) {
            for (PlanillaPago unaPlanilla : unEquipo.getPlanillasPagos()) {
                if (unaPlanilla.getFechaPago().after(fechaDesde) && unaPlanilla.getFechaPago().before(fechaHasta)) {
                    planillasImprimir.add(unaPlanilla);
                }
            }
        }
        HistorialEquipoDS_Pago unPagosDS = new HistorialEquipoDS_Pago(planillasImprimir, unaControladoraGlobal);
        return unPagosDS;

    }

    private HistorialEquipoDS_Plantel subReportePlantel() {
        List<Socia> plantelImprimir = new ArrayList();
        if (this.plantel) {
            for (Socia unaSocia : unEquipo.getPlantel()) {
                plantelImprimir.add(unaSocia);
            }
        }
        HistorialEquipoDS_Plantel unPlantelDS = new HistorialEquipoDS_Plantel(plantelImprimir, unaControladoraGlobal);
        return unPlantelDS;
    }

    private HistorialEquipoDS_Deudas subReporteDeudas() {
        List<Deuda> deudasImprimir = new ArrayList();
        if (this.deuda) {
            for (Deuda unaDeuda : unEquipo.getDeudas()) {
                if (!unaDeuda.isBorradoLogico()) {
                    if (unaDeuda.getFechaGeneracion().after(fechaDesde) && unaDeuda.getFechaGeneracion().before(fechaHasta)) {
                        deudasImprimir.add(unaDeuda);
                    }
                }
            }
        }
        HistorialEquipoDS_Deudas unaDeudaDs = new HistorialEquipoDS_Deudas(deudasImprimir, unaControladoraGlobal);
        return unaDeudaDs;
    }

    private HistorialEquipoDS_Sanciones subReporteSanciones() {
        List sancionesImprimir = new ArrayList();
        if (this.sancion) {
            for (SancionTribunal unaSancion : unEquipo.getSancionesTribunal()) {
                if (!unaSancion.isBorradoLogico()) {
                    if (unaSancion.getFecha().after(fechaDesde) && unaSancion.getFecha().before(fechaHasta)) {
                        sancionesImprimir.add(unaSancion);
                    }
                }
            }
        }
        HistorialEquipoDS_Sanciones unaSancionDs = new HistorialEquipoDS_Sanciones(sancionesImprimir);
        return unaSancionDs;
    }
}
