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
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.ConceptoEgreso;
import logicaNegocios.ConceptoIngreso;
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
    Date fechaEvaluada = null;
    double monto = 0;
    String desde, hasta;
    ControladoraGlobal unaControladoraGlobal;

    // <editor-fold defaultstate="collapsed" desc="ClaseBalance">
    private class Balance implements Comparable {

        Date fecha;
        String unConcepto;
        double montoIngreso;
        double montoEgreso;
        String fechaPago;
        private String fechaVencimiento;

        public String getFechaPago() {
            return fechaPago;
        }

        public void setFechaBalance(String fechaBalance) {
            this.fechaPago = fechaBalance;
        }

        public Balance(Date fecha, String unConcepto, double montoIngreso, double montoEgreso, String fechaPago, String fechaVencimiento) {
            this.fecha = fecha;
            this.unConcepto = unConcepto;
            this.montoIngreso = montoIngreso;
            this.montoEgreso = montoEgreso;
            this.fechaPago = fechaPago;
            this.fechaVencimiento = fechaVencimiento;
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

        /**
         * @return the fechaVencimiento
         */
        public String getFechaVencimiento() {
            return fechaVencimiento;
        }

        /**
         * @param fechaVencimiento the fechaVencimiento to set
         */
        public void setFechaVencimiento(String fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }
    }
    // </editor-fold>

    public BalanceMensualDS(List<Egreso> egresos, List<IngresoOtro> ingresos, List<PagoCuota> pagoCuotas, ControladoraGlobal unaControladoraGlobal, String desde, String hasta, String opcion, boolean isAgrupar) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.desde = desde;
        this.hasta = hasta;
        List<ConceptoDeportivo> conceptosDeportivos = unaControladoraGlobal.getConceptosDeportivosBD();
        if (opcion.equals("Todo")) {
            if (isAgrupar) {
                for (ConceptoDeportivo unConcepto : conceptosDeportivos) {
                    for (PagoCuota unPagoCuota : pagoCuotas) {
                        if (unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().equals(unConcepto)) {
                            if (fechaEvaluada == null) {
                                fechaEvaluada = unPagoCuota.getFechaPago();
                            }
                            if (unPagoCuota.getFechaPago().getMonth() != fechaEvaluada.getMonth()) {
                                unaBalanza = new Balance(fechaEvaluada, unConcepto.getConcepto(), monto, 0, dateFormat.format(fechaEvaluada),"Fechas Varios");
                                unBalance.add(unaBalanza);
                                fechaEvaluada = unPagoCuota.getFechaPago();
                                monto = 0;
                            }
                            monto += unPagoCuota.getMonto();
                            if (pagoCuotas.indexOf(unPagoCuota) == (pagoCuotas.size() - 1)) {
                                unaBalanza = new Balance(unPagoCuota.getFechaPago(), unConcepto.getConcepto(), monto, 0, dateFormat.format(unPagoCuota.getFechaPago()),"Fechas Varios");
                                if (!unBalance.contains(unaBalanza)) {
                                    unBalance.add(unaBalanza);

                                }
                                monto = 0;
                            }
                        }
                    }
                    if (monto != 0) {
                        unaBalanza = new Balance(fechaEvaluada, unConcepto.getConcepto(), monto, 0, dateFormat.format(fechaEvaluada), "Fechas Varios");
                        unBalance.add(unaBalanza);
                    }
                    monto = 0;
                    fechaEvaluada = null;
                }
                this.desde = desde;
                this.hasta = hasta;
                monto = 0;
                List<ConceptoEgreso> conceptosEgresos = unaControladoraGlobal.getConceptosEgresosBD();
                for (ConceptoEgreso unConcepto : conceptosEgresos) {
                    for (Egreso unEgreso : egresos) {
                        if (unEgreso.getUnConceptoEgreso().equals(unConcepto)) {
                            if (fechaEvaluada == null) {
                                fechaEvaluada = unEgreso.getFecha();
                            }
                            if (unEgreso.getFecha().getMonth() != fechaEvaluada.getMonth()) {
                                unaBalanza = new Balance(fechaEvaluada, unEgreso.getUnConceptoEgreso().getNombre(), 0, monto, dateFormat.format(fechaEvaluada),"Fechas Varios");
                                unBalance.add(unaBalanza);
                                fechaEvaluada = unEgreso.getFecha();
                                monto = 0;
                            }
                            monto += unEgreso.getMonto();
                            if (egresos.indexOf(unEgreso) == (pagoCuotas.size() - 1)) {
                                unaBalanza = new Balance(unEgreso.getFecha(), unEgreso.getUnConceptoEgreso().getNombre(), 0, monto, dateFormat.format(unEgreso.getFecha()),"Fechas Varias");
                                if (!unBalance.contains(unaBalanza)) {
                                    unBalance.add(unaBalanza);

                                }
                                monto = 0;
                            }
                        }
                    }
                    if (monto != 0) {
                        unaBalanza = new Balance(fechaEvaluada, unConcepto.getNombre(), 0, monto, dateFormat.format(fechaEvaluada),"Fechas Varias");
                        unBalance.add(unaBalanza);
                    }
                    monto = 0;
                    fechaEvaluada = null;
                }
                this.desde = desde;
                this.hasta = hasta;
                monto = 0;
                List<ConceptoIngreso> conceptoIngresos = unaControladoraGlobal.getConceptosIngresosBD();
                for (ConceptoIngreso unConcepto : conceptoIngresos) {
                    for (IngresoOtro unIngresoOtro : ingresos) {
                        if (unIngresoOtro.getUnConceptoIngreso().equals(unConcepto)) {
                            if (fechaEvaluada == null) {
                                fechaEvaluada = unIngresoOtro.getFecha();
                            }
                            if (unIngresoOtro.getFecha().getMonth() != fechaEvaluada.getMonth()) {
                                unaBalanza = new Balance(fechaEvaluada, unIngresoOtro.getUnConceptoIngreso().getNombre(), monto, 0, dateFormat.format(fechaEvaluada),"Fechas Varias");
                                unBalance.add(unaBalanza);
                                fechaEvaluada = unIngresoOtro.getFecha();
                                monto = 0;
                            }
                            monto += unIngresoOtro.getMonto();
                            if (ingresos.indexOf(unIngresoOtro) == (pagoCuotas.size() - 1)) {
                                unaBalanza = new Balance(unIngresoOtro.getFecha(), unIngresoOtro.getUnConceptoIngreso().getNombre(), monto, 0, dateFormat.format(unIngresoOtro.getFecha()),"Fechas Varias");
                                if (!unBalance.contains(unaBalanza)) {
                                    unBalance.add(unaBalanza);

                                }
                                monto = 0;
                            }
                        }
                    }
                    if (monto != 0) {
                        unaBalanza = new Balance(fechaEvaluada, unConcepto.getNombre(), monto, 0, dateFormat.format(fechaEvaluada),"Fechas Varias");
                        unBalance.add(unaBalanza);
                    }
                    monto = 0;
                    fechaEvaluada = null;
                }
            } else {
                for (Egreso unEgreso : egresos) {
                    unaBalanza = new Balance(unEgreso.getFecha(), unEgreso.getUnConceptoEgreso().getNombre(), 0, unEgreso.getMonto(), df.format(unEgreso.getFecha()),"Fechas Varias");
                    unBalance.add(unaBalanza);
                }
                for (IngresoOtro unIngreso : ingresos) {
                    unaBalanza = new Balance(unIngreso.getFecha(), unIngreso.getUnConceptoIngreso().getNombre(), unIngreso.getMonto(), 0, df.format(unIngreso.getFecha()),"Fechas Varias");
                    unBalance.add(unaBalanza);
                }
                for (PagoCuota unPagoCuota : pagoCuotas) {
                    if (unaControladoraGlobal.getSociaResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)) != null) {
                        unaBalanza = new Balance(unPagoCuota.getFechaPago(), opcion + " " + unaControladoraGlobal.getSociaResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)), unPagoCuota.getMonto(), 0, dateFormat.format(unPagoCuota.getFechaPago()),"Fechas Varias");
                        unBalance.add(unaBalanza);
                    } else {
                        if (unaControladoraGlobal.getEquipoResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)) != null) {
                            unaBalanza = new Balance(unPagoCuota.getFechaPago(), opcion + " " + unaControladoraGlobal.getEquipoResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)), unPagoCuota.getMonto(), 0, dateFormat.format(unPagoCuota.getFechaPago()),"Fechas Varias");
                            unBalance.add(unaBalanza);
                        } else {
                            unaBalanza = new Balance(unPagoCuota.getFechaPago(), opcion, unPagoCuota.getMonto(), 0, dateFormat.format(unPagoCuota.getFechaPago()),"Fechas Varias");
                            unBalance.add(unaBalanza);
                        }
                    }

                }
            }
        } else {
            if (!isAgrupar) {
                for (Egreso unEgreso : egresos) {
                    if (unEgreso.getUnConceptoEgreso().getNombre().equals(opcion)) {
                        unaBalanza = new Balance(unEgreso.getFecha(), unEgreso.getUnConceptoEgreso().getNombre(), 0, unEgreso.getMonto(), df.format(unEgreso.getFecha()),"-");
                        unBalance.add(unaBalanza);
                    }
                }
                for (IngresoOtro unIngreso : ingresos) {
                    if (unIngreso.getUnConceptoIngreso().getNombre().equals(opcion)) {
                        unaBalanza = new Balance(unIngreso.getFecha(), unIngreso.getUnConceptoIngreso().getNombre(), unIngreso.getMonto(), 0, df.format(unIngreso.getFecha()),"-");
                        unBalance.add(unaBalanza);
                    }
                }
                for (PagoCuota unPagoCuota : pagoCuotas) {
                    System.out.println(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto().equals(opcion)); 
                    if (unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto().equals(opcion)) {
                        if (unaControladoraGlobal.getSociaResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)) != null) {
                            unaBalanza = new Balance(unPagoCuota.getFechaPago(), unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto() + " " + unaControladoraGlobal.getSociaResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)), unPagoCuota.getMonto(), 0, dateFormat.format(unPagoCuota.getFechaPago()),unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getPrimerVencimiento().toString());
                            unBalance.add(unaBalanza);
                        } else {
                            if (unaControladoraGlobal.getEquipoResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)) != null) {
                                unaBalanza = new Balance(unPagoCuota.getFechaPago(), unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto() + " " + unaControladoraGlobal.getEquipoResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)), unPagoCuota.getMonto(), 0, dateFormat.format(unPagoCuota.getFechaPago()),unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getPrimerVencimiento().toString());
                                unBalance.add(unaBalanza);
                            } else {
                                unaBalanza = new Balance(unPagoCuota.getFechaPago(), unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().getConcepto() + " " + unaControladoraGlobal.getEquipoResponsableDeuda(unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota)), unPagoCuota.getMonto(), 0, dateFormat.format(unPagoCuota.getFechaPago()),unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getPrimerVencimiento().toString());
                                unBalance.add(unaBalanza);
                            }
                        }
                    }
                }
            } else {
                this.desde = desde;
                this.hasta = hasta;
                monto = 0;
                String fechaVencimiento = "-";
                for (ConceptoDeportivo unConcepto : conceptosDeportivos) {
                    if (unConcepto.getConcepto().equals(opcion)) {
                        for (PagoCuota unPagoCuota : pagoCuotas) {
                            if (unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getUnConceptoDeportivo().equals(unConcepto)) {
                                if (fechaEvaluada == null) {
                                    fechaEvaluada = unPagoCuota.getFechaPago();
                                }
                                if (unPagoCuota.getFechaPago().getMonth() != fechaEvaluada.getMonth()) {
                                    unaBalanza = new Balance(fechaEvaluada, unConcepto.getConcepto(), monto, 0, dateFormat.format(fechaEvaluada),unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getPrimerVencimiento().toString());
                                    unBalance.add(unaBalanza);
                                    fechaEvaluada = unPagoCuota.getFechaPago();
                                    monto = 0;
                                }
                                monto += unPagoCuota.getMonto();
                                if (pagoCuotas.indexOf(unPagoCuota) == (pagoCuotas.size() - 1)) {
                                    unaBalanza = new Balance(unPagoCuota.getFechaPago(), unConcepto.getConcepto(), monto, 0, dateFormat.format(unPagoCuota.getFechaPago()),unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getPrimerVencimiento().toString());
                                    if (!unBalance.contains(unaBalanza)) {
                                        unBalance.add(unaBalanza);

                                    }
                                    monto = 0;
                                }
                            }
                            fechaVencimiento = unaControladoraGlobal.getDeudaPagoCuota(unPagoCuota).getPrimerVencimiento().toString();
                        }
                        if (monto != 0) {
                            unaBalanza = new Balance(fechaEvaluada, unConcepto.getConcepto(), monto, 0, dateFormat.format(fechaEvaluada),fechaVencimiento);
                            unBalance.add(unaBalanza);
                        }
                        monto = 0;
                        fechaEvaluada = null;
                    }
                }
                this.desde = desde;
                this.hasta = hasta;
                monto = 0;
                List<ConceptoEgreso> conceptosEgresos = unaControladoraGlobal.getConceptosEgresosBD();
                for (ConceptoEgreso unConcepto : conceptosEgresos) {
                    if (unConcepto.getNombre().equals(opcion)) {
                        for (Egreso unEgreso : egresos) {
                            if (unEgreso.getUnConceptoEgreso().equals(unConcepto)) {
                                if (fechaEvaluada == null) {
                                    fechaEvaluada = unEgreso.getFecha();
                                }
                                if (unEgreso.getFecha().getMonth() != fechaEvaluada.getMonth()) {
                                    unaBalanza = new Balance(fechaEvaluada, unEgreso.getUnConceptoEgreso().getNombre(), 0, monto, dateFormat.format(fechaEvaluada),"-");
                                    unBalance.add(unaBalanza);
                                    fechaEvaluada = unEgreso.getFecha();
                                    monto = 0;
                                }
                                monto += unEgreso.getMonto();
                                if (egresos.indexOf(unEgreso) == (pagoCuotas.size() - 1)) {
                                    unaBalanza = new Balance(unEgreso.getFecha(), unEgreso.getUnConceptoEgreso().getNombre(), 0, monto, dateFormat.format(unEgreso.getFecha()),"-");
                                    if (!unBalance.contains(unaBalanza)) {
                                        unBalance.add(unaBalanza);

                                    }
                                    monto = 0;
                                }
                            }
                        }
                        if (monto != 0) {
                            unaBalanza = new Balance(fechaEvaluada, unConcepto.getNombre(), 0, monto, dateFormat.format(fechaEvaluada),"-");
                            unBalance.add(unaBalanza);
                        }
                        monto = 0;
                        fechaEvaluada = null;
                    }
                }
                this.desde = desde;
                this.hasta = hasta;
                monto = 0;
                List<ConceptoIngreso> conceptoIngresos = unaControladoraGlobal.getConceptosIngresosBD();
                for (ConceptoIngreso unConcepto : conceptoIngresos) {
                    if (unConcepto.getNombre().equals(opcion)) {
                        for (IngresoOtro unIngresoOtro : ingresos) {
                            if (unIngresoOtro.getUnConceptoIngreso().equals(unConcepto)) {
                                if (fechaEvaluada == null) {
                                    fechaEvaluada = unIngresoOtro.getFecha();
                                }
                                if (unIngresoOtro.getFecha().getMonth() != fechaEvaluada.getMonth()) {
                                    unaBalanza = new Balance(fechaEvaluada, unIngresoOtro.getUnConceptoIngreso().getNombre(), monto, 0, dateFormat.format(fechaEvaluada),"-");
                                    unBalance.add(unaBalanza);
                                    fechaEvaluada = unIngresoOtro.getFecha();
                                    monto = 0;
                                }
                                monto += unIngresoOtro.getMonto();
                                if (ingresos.indexOf(unIngresoOtro) == (pagoCuotas.size() - 1)) {
                                    unaBalanza = new Balance(unIngresoOtro.getFecha(), unIngresoOtro.getUnConceptoIngreso().getNombre(), monto, 0, dateFormat.format(unIngresoOtro.getFecha()),"-");
                                    if (!unBalance.contains(unaBalanza)) {
                                        unBalance.add(unaBalanza);

                                    }
                                    monto = 0;
                                }
                            }
                        }
                        if (monto != 0) {
                            unaBalanza = new Balance(fechaEvaluada, unConcepto.getNombre(), monto, 0, dateFormat.format(fechaEvaluada),"-");
                            unBalance.add(unaBalanza);
                        }
                        monto = 0;
                        fechaEvaluada = null;
                    }
                }
            }
        }
        Collections.sort(unBalance, (Balance o1, Balance o2) -> ((Integer) o1.getFecha().getMonth()).compareTo((Integer) o2.getFecha().getMonth()));
    }

    //Sector de la impresion del datasource
    @Override

    public boolean next() throws JRException {
        return ++indiceBalance < unBalance.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if (null != jrf.getName()) switch (jrf.getName()) {
            case "ruta":
                valor = unaControladoraGlobal.rutaSistema();
                break;
            case "fechaPago":
                if (!unBalance.isEmpty()) {
                    valor = unBalance.get(indiceBalance).getFechaPago();
                }   break;
            case "Concepto":
                valor = unBalance.get(indiceBalance).getUnConcepto();
                break;
            case "MontoI":
                valor = unBalance.get(indiceBalance).getMontoIngreso();
                break;
            case "MontoE":
                valor = unBalance.get(indiceBalance).getMontoEgreso();
                break;
            case "fechaBalance":
                int mes1 = Integer.parseInt(desde.substring(0, 2));
                int mes2 = Integer.parseInt(hasta.substring(0, 2));
                if ((mes2 - mes1) == 1) {
                    valor = "Balance del mes " + desde;
                } else {
                    valor = "Balance del mes " + desde + " hasta el mes " + (mes2 - 1) + hasta.substring(2);
                }   break;
            case "fecha":
                valor = df.format(unaControladoraGlobal.fechaSistema());
                break;
            case "fechaVencimiento":
                if (!unBalance.isEmpty()) {
                    valor = unBalance.get(indiceBalance).getFechaVencimiento();
                } 
        }
        return valor;
    }

}
