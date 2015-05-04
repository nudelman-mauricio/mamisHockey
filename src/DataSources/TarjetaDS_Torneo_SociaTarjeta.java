package DataSources;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
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
    Socia unaSocia;
    List<Tarjeta> listaTarjetas = new ArrayList();
    int indiceTarjetas = -1;
    private DateFormat df = DateFormat.getDateInstance();

    public TarjetaDS_Torneo_SociaTarjeta(List<Tarjeta> listaTarjetas, ControladoraGlobal unaControladoraGlobal, Socia unaSocia) {

        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unaSocia = unaSocia;
        this.listaTarjetas = listaTarjetas;
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
        } else if ("fechaTorneo".equals(jrf.getName())) {
            if (unaControladoraGlobal.getFechaTorneoDePartido(unaControladoraGlobal.getPartidoTarjeta(listaTarjetas.get(indiceTarjetas))) != null) {
                valor = unaControladoraGlobal.getFechaTorneoDePartido(unaControladoraGlobal.getPartidoTarjeta(listaTarjetas.get(indiceTarjetas))).getNumeroFecha();
            } else {
                valor = "-";
            }
        } else if ("partido".equals(jrf.getName())) {
            if (unaControladoraGlobal.getPartidoTarjeta(listaTarjetas.get(indiceTarjetas)) != null) {
                valor = unaControladoraGlobal.getPartidoTarjeta(listaTarjetas.get(indiceTarjetas)).getUnEquipoLocal() + " vs " + unaControladoraGlobal.getPartidoTarjeta(listaTarjetas.get(indiceTarjetas)).getUnEquipoVisitante();
            } else {
                valor = "Acumulada";
            }
        } else if ("minuto".equals(jrf.getName())) {
            if (listaTarjetas.get(indiceTarjetas).getMinuto() != null) {
                valor = listaTarjetas.get(indiceTarjetas).getMinuto() + "' " + listaTarjetas.get(indiceTarjetas).getTiempo() + "T";
            } else {
                valor = "-";
            }
        } else if ("contabilizo".equals(jrf.getName())) {
            if (listaTarjetas.get(indiceTarjetas).isComputado()) {
                valor = "Si";
            } else {
                valor = "No";
            }
        }
        return valor;
    }

}
