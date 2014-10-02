/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import logicaNegocios.Egreso;
import logicaNegocios.IngresoOtro;
import logicaNegocios.PagoCuota;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class BalanceMensualDS implements JRDataSource {

    private int indiceBalance = -1;
    Object[] arreglo;
    DateFormat df = DateFormat.getDateInstance();
    ArrayList<Balance> unBalance = new ArrayList();
    Balance unaBalanza;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/YYYY");
    Date fechaEvaluadaPase = null;
    double montoPase = 0;
    String desde, hasta;
    ControladoraGlobal unaControladoraGlobal;

    // <editor-fold defaultstate="collapsed" desc="ClaseBalance">
    private class Balance implements Comparable {

        Date fecha;
        String unConcepto;
        double montoIngreso;
        double montoEgreso;
        String fechaBalance;

        public String getFechaBalance() {
            return fechaBalance;
        }

        public void setFechaBalance(String fechaBalance) {
            this.fechaBalance = fechaBalance;
        }

        public Balance(Date fecha, String unConcepto, double montoIngreso, double montoEgreso, String fechaBalance) {
            this.fecha = fecha;
            this.unConcepto = unConcepto;
            this.montoIngreso = montoIngreso;
            this.montoEgreso = montoEgreso;
            this.fechaBalance = fechaBalance;
        }

        public Date getFecha() {
            return fecha;
        }

        public String getUnConcepto() {
            return unConcepto;
        }

        public double getMontoIngreso() {
            return montoIngreso;
        }

        public double getMontoEgreso() {
            return montoEgreso;
        }

        @Override
        public int compareTo(Object aux) {
            int retorno = -1;
            if (aux instanceof Balance) {
                Balance unBalance = (Balance) aux;
                retorno = this.fecha.compareTo(unBalance.getFecha());
            }
            return retorno;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public void setUnConcepto(String unConcepto) {
            this.unConcepto = unConcepto;
        }

        public void setMontoIngreso(double montoIngreso) {
            this.montoIngreso = montoIngreso;
        }

        public void setMontoEgreso(double montoEgreso) {
            this.montoEgreso = montoEgreso;
        }
    }
    // </editor-fold>

    public BalanceMensualDS(List<Egreso> egresos, List<IngresoOtro> ingresos, List<PagoCuota> pagoCuotas, ControladoraGlobal unaControladoraGlobal, String desde, String hasta) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.desde = desde; this.hasta = hasta;
        for (PagoCuota unPagoCuota : pagoCuotas) {
            if (unPagoCuota.getMonto() != 0) {
                if (unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto().equals("Pase")) {
                    insertarPase(unPagoCuota, pagoCuotas);
                }
            }
        }
        for (Egreso unEgreso : egresos) {
            unaBalanza = new Balance(unEgreso.getFecha(), unEgreso.getUnConceptoEgreso().getNombre(), 0, unEgreso.getMonto(), df.format(unEgreso.getFecha()));
            unBalance.add(unaBalanza);
        }
        for (IngresoOtro unIngreso : ingresos) {
            unaBalanza = new Balance(unIngreso.getFecha(), unIngreso.getUnConceptoIngreso().getNombre(), unIngreso.getMonto(), 0, df.format(unIngreso.getFecha()));
            unBalance.add(unaBalanza);
        }
        Collections.sort(unBalance, (Balance o1, Balance o2) -> ((Integer) o1.getFecha().getMonth()).compareTo((Integer) o2.getFecha().getMonth()));
    }

    private void insertarPase(PagoCuota unPagoCuota, List pagoCuotas) {
        if (fechaEvaluadaPase == null) {
            fechaEvaluadaPase = unPagoCuota.getFechaPago();
        }
        if (unPagoCuota.getFechaPago().getMonth() != fechaEvaluadaPase.getMonth()) {
            unaBalanza = new Balance(fechaEvaluadaPase, "Pase", montoPase, 0, dateFormat.format(fechaEvaluadaPase));
            unBalance.add(unaBalanza);
            fechaEvaluadaPase = unPagoCuota.getFechaPago();
            montoPase = 0;
        }
        montoPase += unPagoCuota.getMonto();
        if (pagoCuotas.indexOf(unPagoCuota) == (pagoCuotas.size() - 1)) {
            unaBalanza = new Balance(unPagoCuota.getFechaPago(), "Pase", montoPase, 0, dateFormat.format(unPagoCuota.getFechaPago()));
            if (!unBalance.contains(unaBalanza)) {
                unBalance.add(unaBalanza);
            }
        }
    }

    //Sector de la impresion del datasource
    @Override

    public boolean next() throws JRException {
        return ++indiceBalance < unBalance.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("Fecha".equals(jrf.getName())) {
            if (!unBalance.isEmpty()) {
                valor = unBalance.get(indiceBalance).getFechaBalance();
            }
        } else if ("Concepto".equals(jrf.getName())) {
            valor = unBalance.get(indiceBalance).getUnConcepto();
        } else if ("MontoI".equals(jrf.getName())) {
            valor = unBalance.get(indiceBalance).getMontoIngreso();
        } else if ("MontoE".equals(jrf.getName())) {
            valor = unBalance.get(indiceBalance).getMontoEgreso();
        } else if ("desde".equals(jrf.getName())) {
            valor = desde;
        } else if ("hasta".equals(jrf.getName())) {
            valor = hasta;
        } else if ("fecha".equals(jrf.getName())) {
            valor = df.format(unaControladoraGlobal.fechaSistema());
        }
        return valor;
    }

}
