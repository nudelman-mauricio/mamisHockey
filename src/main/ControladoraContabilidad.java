package main;

import java.util.Collection;
import javax.persistence.EntityManager;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.ConceptoEgreso;
import logicaNegocios.ConceptoIngreso;
import logicaNegocios.Egreso;
import logicaNegocios.IngresoOtro;

public class ControladoraContabilidad {

    private Collection<ConceptoDeportivo> conceptosDeportivo;
    private Collection<ConceptoIngreso> conceptosIngreso;
    private Collection<IngresoOtro> ingresosOtro;
    private Collection<Egreso> egresos;
    private Collection<ConceptoEgreso> conceptosEgreso;
    private EntityManager entityManager;

    public ControladoraContabilidad(EntityManager em) {
        this.entityManager = em;
    }

    public Collection<ConceptoDeportivo> getConceptosDeportivo() {
        return this.conceptosDeportivo;
    }

    public void setConceptosDeportivo(Collection<ConceptoDeportivo> conceptosDeportivo) {
        this.conceptosDeportivo = conceptosDeportivo;
    }

    public Collection<ConceptoIngreso> getConceptosIngreso() {
        return this.conceptosIngreso;
    }

    public void setConceptosIngreso(Collection<ConceptoIngreso> conceptosIngreso) {
        this.conceptosIngreso = conceptosIngreso;
    }

    public Collection<IngresoOtro> getIngresosOtro() {
        return this.ingresosOtro;
    }

    public void setIngresosOtro(Collection<IngresoOtro> ingresosOtro) {
        this.ingresosOtro = ingresosOtro;
    }

    public Collection<Egreso> getEgresos() {
        return this.egresos;
    }

    public void setEgresos(Collection<Egreso> egresos) {
        this.egresos = egresos;
    }

    public Collection<ConceptoEgreso> getConceptosEgreso() {
        return this.conceptosEgreso;
    }

    public void setConceptosEgreso(Collection<ConceptoEgreso> conceptosEgreso) {
        this.conceptosEgreso = conceptosEgreso;
    }

}
