/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
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
    List<Object[]> balanceFinal = new ArrayList();
    DateFormat df = DateFormat.getDateInstance();
    TreeSet<Balance> unBalance = new TreeSet();    

    // <editor-fold defaultstate="collapsed" desc="ClaseBalance">
    private class Balance implements Comparable{

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
            if (unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto().equals("Por Pase")) {
                if (unPagoCuota.getMonto() != 0) {
                    if (mesEvaluado == 0) {
                        mesEvaluado = unPagoCuota.getFechaPago().getMonth();
                    }
                    if (unPagoCuota.getFechaPago().getMonth() != mesEvaluado) {
                        Balance unaBalanza = new Balance (unPagoCuota.getFechaPago(),"Por pase",montoPase,0);
                        unBalance.add(unaBalanza);
                        mesEvaluado = unPagoCuota.getFechaPago().getMonth();
                        montoPase = 0;
                    }
                    montoPase += unPagoCuota.getMonto();
                    if (pagoCuotas.indexOf(unPagoCuota) == (pagoCuotas.size() - 1)) {
                       Balance unaBalanza = new Balance (unPagoCuota.getFechaPago(),"Por pase",montoPase,0);
                       unBalance.add(unaBalanza);
                    }
                }
            }
        }        
        for (Egreso unEgreso : egresos) {           
            Balance unaBalanza = new Balance (unEgreso.getFecha(),unEgreso.getUnConceptoEgreso().getNombre(),0,unEgreso.getMonto());
            unBalance.add(unaBalanza);            
        }
        for (IngresoOtro unIngreso : ingresos) {
            Balance unaBalanza = new Balance (unIngreso.getFecha(),unIngreso.getUnConceptoIngreso().getNombre(), unIngreso.getMonto(), 0);
            unBalance.add(unaBalanza);           
        }
        for(Balance unBalanceAux :unBalance){
            balanceFinal.add(new Object[]{df.format(unBalanceAux.getFecha()), unBalanceAux.getUnConcepto(), unBalanceAux.getMontoIngreso(),unBalanceAux.getMontoEgreso()});
        }      
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceBalance < balanceFinal.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("Fecha".equals(jrf.getName())) {
            if (!balanceFinal.isEmpty()) {
                arreglo = (Object[]) balanceFinal.get(indiceBalance);
                valor = arreglo[0];
            }
        } else if ("Concepto".equals(jrf.getName())) {
            arreglo = (Object[]) balanceFinal.get(indiceBalance);
            valor = arreglo[1];
        } else if ("MontoI".equals(jrf.getName())) {
            arreglo = (Object[]) balanceFinal.get(indiceBalance);
            valor = arreglo[2];
        } else if ("MontoE".equals(jrf.getName())) {
            arreglo = (Object[]) balanceFinal.get(indiceBalance);
            valor = arreglo[3];
        }
        return valor;
    }

}
