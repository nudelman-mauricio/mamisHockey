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
public class TarjetaDS_Torneo implements JRDataSource {

    ControladoraGlobal unaControladoraGlobal;
    Torneo unTorneo;
    Socia unaSocia;
    List<Torneo> listaTorneos = new ArrayList();
    int indiceTorneo = -1;
    private DateFormat df = DateFormat.getDateInstance();

    public TarjetaDS_Torneo(List<Torneo> listaTorneos, ControladoraGlobal unaControladoraGlobal, Socia unaSocia) {
        this.listaTorneos = listaTorneos;
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaSocia = unaSocia;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceTorneo < listaTorneos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ruta".equals(jrf.getName())) {
            valor = unaControladoraGlobal.rutaSistema();
        } else if ("torneo".equals(jrf.getName())) {
            valor = listaTorneos.get(indiceTorneo).getNombre();
        } else if ("tarjeta_socia".equals(jrf.getName())) {
            valor = subReporteUnTorneo(listaTorneos.get(indiceTorneo));
        }
        return valor;
    }
    
     private TarjetaDS_Torneo_SociaTarjeta subReporteUnTorneo(Torneo unTorneo) {       
        TarjetaDS_Torneo_SociaTarjeta unTorneoDS = new TarjetaDS_Torneo_SociaTarjeta(unTorneo, this.unaControladoraGlobal, unaSocia);
        return unTorneoDS;
    }

}
