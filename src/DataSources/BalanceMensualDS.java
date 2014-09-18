/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import logicaNegocios.Deuda;
import logicaNegocios.Egreso;
import logicaNegocios.IngresoOtro;
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
    List<Object[]> balance = new ArrayList();
    Object[] arreglo;
    DateFormat df = DateFormat.getDateInstance();

    public BalanceMensualDS(List<Egreso> egresos, List<IngresoOtro> ingresos, List<Deuda> deudas, ControladoraGlobal unaControladoraGlobal) {
        String fecha, fechaAux;
        String[] fechaDividida, fechaDivididaAux;
        Deuda deudaAux = deudas.get(0);
        deudas.remove(0);
        fechaAux = df.format(deudaAux.getFechaGeneracion());
        fechaDivididaAux = fechaAux.split("/");
        double auxiliarPase = 0;
        for (Deuda unaDeuda : deudas) {
            if (deudaAux.getMontoTotal() > 0) {
                if ("Por Pase".equals(deudaAux.getUnConceptoDeportivo().getConcepto())) {
                    fecha = df.format(unaDeuda.getFechaGeneracion());
                    fechaDividida = fecha.split("/");
                    auxiliarPase = deudaAux.getMontoTotal() + auxiliarPase;
                    if (!(fechaDividida[1].equals(fechaDivididaAux[1]))) {
                        balance.add(new Object[]{fechaDivididaAux[1] + "/" + fechaDivididaAux[2], "Por Pase", auxiliarPase, 0});
                        System.out.println(auxiliarPase);
                        auxiliarPase = 0;
                    }

                }

            }
            deudaAux = unaDeuda;
            fechaAux = df.format(deudaAux.getFechaGeneracion());
            fechaDivididaAux = fechaAux.split("/");
        }
        if ("Por Pase".equals(deudaAux.getUnConceptoDeportivo().getConcepto())) {
            balance.add(new Object[]{fechaDivididaAux[1] + "/" + fechaDivididaAux[2], "Por Pase", auxiliarPase, 0});
            System.out.println(auxiliarPase);
            auxiliarPase = 0;
        }
        for (Egreso unEgreso : egresos) {
            balance.add(new Object[]{df.format(unEgreso.getFecha()), unEgreso.getUnConceptoEgreso().getNombre(), 0, unEgreso.getMonto()});
        }
        for (IngresoOtro unIngreso : ingresos) {
            balance.add(new Object[]{df.format(unIngreso.getFecha()), unIngreso.getUnConceptoIngreso().getNombre(), unIngreso.getMonto(), 0});
        }

    }

    @Override
    public boolean next() throws JRException {
        return ++indiceBalance < balance.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("Fecha".equals(jrf.getName())) {
            if (!balance.isEmpty()) {
                arreglo = (Object[]) balance.get(indiceBalance);
                valor = arreglo[0];
            }
        } else if ("Concepto".equals(jrf.getName())) {
            arreglo = (Object[]) balance.get(indiceBalance);
            valor = arreglo[1];
        } else if ("MontoI".equals(jrf.getName())) {
            arreglo = (Object[]) balance.get(indiceBalance);
            valor = arreglo[2];
        } else if ("MontoE".equals(jrf.getName())) {
            arreglo = (Object[]) balance.get(indiceBalance);
            valor = arreglo[3];
        }
        return valor;
    }

}
