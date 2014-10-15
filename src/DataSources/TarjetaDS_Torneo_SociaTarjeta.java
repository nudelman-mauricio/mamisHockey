/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import logicaNegocios.Torneo;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Leanwit
 */
public class TarjetaDS_Torneo_SociaTarjeta implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    Socia unaSocia;
    List<Tarjeta> listaTarjetas = new ArrayList();
    int indiceTarjetas = -1;
    private DateFormat df = DateFormat.getDateInstance();

    public TarjetaDS_Torneo_SociaTarjeta(Torneo unTorneo, ControladoraGlobal unaControladoraGlobal, Socia unaSocia) {
        this.unTorneo = unTorneo;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaSocia = unaSocia;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceTarjetas < listaTarjetas.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("fecha".equals(jrf.getName())) {
            valor = df.format(listaTarjetas.get(indiceTarjetas).getFecha());
        } else if ("nombre".equals(jrf.getName())) {
            valor = unaSocia;
        } else if ("tipoTarjeta".equals(jrf.getName())) {
            valor = listaTarjetas.get(indiceTarjetas).getTipo();
        } else if ("partido".equals(jrf.getName())) {
            valor = unaControladoraGlobal.getPartidoTarjeta(listaTarjetas.get(indiceTarjetas));
        }
        return valor;
    }

}
