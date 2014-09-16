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
import logicaNegocios.Deuda;
import logicaNegocios.Egreso;
import logicaNegocios.IngresoOtro;
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

    public BalanceMensualDS(List<Egreso> egresos, List<IngresoOtro> ingresos, List<Deuda> deudas) {
        double auxiliarPase = 0;
        Deuda deudaAnterior = deudas.get(0);
        for (Deuda unaDeuda : deudas) {
            if (unaDeuda.getMontoTotal() > 0) {
                if ("Por Pase".equals(unaDeuda.getUnConceptoDeportivo().getConcepto())) {
                    if (deudaAnterior.getFechaGeneracion().getMonth() != unaDeuda.getFechaGeneracion().getMonth()) {
                        auxiliarPase += unaDeuda.getMontoTotal();
                        System.out.println("Entro: " + unaDeuda.getMontoTotal() + "-" + auxiliarPase);
                        balance.add(new Object[]{"Autogenerado mensualmente", "Por Pase", auxiliarPase, 0});
                        auxiliarPase = unaDeuda.getMontoTotal();
                    } else {
                        auxiliarPase += unaDeuda.getMontoTotal();
                    }
                    deudaAnterior = unaDeuda;
                }
            }
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
