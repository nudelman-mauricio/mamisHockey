/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    // <editor-fold defaultstate="collapsed" desc="ClaseBalance">
    private class Balance implements Comparable {
        long id;
        Date fecha;
        String unConcepto;
        double montoIngreso;
        double montoEgreso;

        public Balance(Date fecha, String unConcepto, double montoIngreso, double montoEgreso) {           
            this.fecha = fecha;
            this.unConcepto = unConcepto;
            this.montoIngreso = montoIngreso;
            this.montoEgreso = montoEgreso;
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

    public BalanceMensualDS(List<Egreso> egresos, List<IngresoOtro> ingresos, List<PagoCuota> pagoCuotas, ControladoraGlobal unaControladoraGlobal) {
        int mesEvaluado = 0;
        double montoPase = 0;

        for (PagoCuota unPagoCuota : pagoCuotas) {
            if (unPagoCuota.getMonto() != 0) {
                if (unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto().equals("Por Pase")) {
                    if (mesEvaluado == 0) {
                        mesEvaluado = unPagoCuota.getFechaPago().getMonth();
                        System.out.println("Entro 1: " + unPagoCuota.getMonto());
                    }
                    if (unPagoCuota.getFechaPago().getMonth() != mesEvaluado) {
                        unaBalanza = new Balance(unPagoCuota.getFechaPago(), "Por pase", montoPase, 0);
                        unBalance.add(unaBalanza);
                        System.out.println("Entro 2: " + montoPase);
                        mesEvaluado = unPagoCuota.getFechaPago().getMonth();
                        montoPase = 0;
                        
                    }
                    System.out.println("Sumo:"+montoPase+"-"+unPagoCuota.getMonto());
                    montoPase += unPagoCuota.getMonto();
                   
                    if (pagoCuotas.indexOf(unPagoCuota) == (pagoCuotas.size() - 1)) {
                        unaBalanza = new Balance(unPagoCuota.getFechaPago(), "Por pase", montoPase, 0);
                        if (!unBalance.contains(unaBalanza)) {
                            unBalance.add(unaBalanza);
                            System.out.println("Entro 3: " + montoPase);
                        }

                    }
                }
            }
        }
        for (Egreso unEgreso : egresos) {
            unaBalanza = new Balance(unEgreso.getFecha(), unEgreso.getUnConceptoEgreso().getNombre(), 0, unEgreso.getMonto());
            unBalance.add(unaBalanza);
        }
        for (IngresoOtro unIngreso : ingresos) {
            unaBalanza = new Balance(unIngreso.getFecha(), unIngreso.getUnConceptoIngreso().getNombre(), unIngreso.getMonto(), 0);
            unBalance.add(unaBalanza);
        }        
        Collections.sort(unBalance, (Balance o1, Balance o2) -> ((Integer)o1.getFecha().getMonth()).compareTo((Integer)o2.getFecha().getMonth()));       
    }

    @Override

    public boolean next() throws JRException {
        return ++indiceBalance < unBalance.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("Fecha".equals(jrf.getName())) {
            if (!unBalance.isEmpty()) {
                valor  =  df.format(unBalance.get(indiceBalance).getFecha());               
            }
        } else if ("Concepto".equals(jrf.getName())) {
            valor = unBalance.get(indiceBalance).getUnConcepto();   
        } else if ("MontoI".equals(jrf.getName())) {
            valor = unBalance.get(indiceBalance).getMontoIngreso();           
        } else if ("MontoE".equals(jrf.getName())) {
            valor = unBalance.get(indiceBalance).getMontoEgreso();            
        }
        return valor;
    }

}
