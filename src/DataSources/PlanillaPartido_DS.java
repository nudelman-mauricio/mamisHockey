package DataSources;

import logicaNegocios.Equipo;
import logicaNegocios.Partido;
import main.ControladoraGlobal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Lucas
 */
public class PlanillaPartido_DS implements JRDataSource {

    private int indicePantel = -1;

    private Partido unPartido;
    private ControladoraGlobal unaControladoraGlobal;

    public PlanillaPartido_DS(ControladoraGlobal unaControladoraGlobal, Partido unPartido) {
        this.unaControladoraGlobal = unaControladoraGlobal;
        this.unPartido = unPartido;
    }

    //Sector de la impresion del datasource
    @Override

    public boolean next() throws JRException {
        //return ++indicePantel < unBalance.size();
        return false;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        //General
        if ("Fecha".equals(jrf.getName())) {
            valor = unPartido.getFecha();
        } else if ("idPartido".equals(jrf.getName())) {
            valor = unPartido.getIdPartido();
        } else if ("Arbitro1".equals(jrf.getName())) {
            valor = unPartido.getUnArbitro1();
        } else if ("Arbitro2".equals(jrf.getName())) {
            valor = unPartido.getUnArbitro2();
        } else if ("Arbitro3".equals(jrf.getName())) {
            valor = unPartido.getUnArbitro3();
        } else if ("Cancha".equals(jrf.getName())) {
            valor = unPartido.getUnaCancha();
        } else 

        //Equipo Local    
        if ("equipoLocal".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoLocal().getNombre();
        } else if ("dtLocal".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoLocal().getUnDT();
        } else if ("acLocal".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoLocal().getUnAyudanteCampo();
        } else if ("pfLocal".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoLocal().getUnPreparadorFisico();
        } else if ("capitanaLocal".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoLocal().getUnaCapitana();
        } else if ("capitanaSuplenteLocal".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoLocal().getUnaCapitanaSuplente();
        } else 
        //PlantelLocal    
        

        //Equipo Visitante   
        if ("equipoVisitante".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoVisitante().getNombre();
        } else if ("dtVisitante".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoVisitante().getUnDT();
        } else if ("acVisitante".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoVisitante().getUnAyudanteCampo();
        } else if ("pfVisitante".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoVisitante().getUnPreparadorFisico();
        } else if ("capitanaVisitante".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoVisitante().getUnaCapitana();
        } else if ("capitanaSuplenteVisitante".equals(jrf.getName())) {
            valor = unPartido.getUnEquipoVisitante().getUnaCapitanaSuplente();
        } //else 
        //PlantelVisitante

        return valor;
    }
}
