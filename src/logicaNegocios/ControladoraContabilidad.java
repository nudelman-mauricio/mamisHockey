package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public  class ControladoraContabilidad implements Serializable {

    @OneToMany(targetEntity=ConceptoDeportivo.class)
    private Collection<ConceptoDeportivo> conceptosDeportivo;

    @Id
    private Long idControladoraEntidad;

    @OneToMany(targetEntity=ConceptoIngreso.class)
    private Collection<ConceptoIngreso> conceptosIngreso;

    @OneToMany(targetEntity=IngresoOtro.class)
    private Collection<IngresoOtro> ingresosOtro;

    @OneToMany(targetEntity=Egreso.class)
    private Collection<Egreso> egresos;

    @OneToMany(targetEntity=ConceptoEgreso.class)
    private Collection<ConceptoEgreso> conceptosEgreso;

    public ControladoraContabilidad(){

    }


   public Collection<ConceptoDeportivo> getConceptosDeportivo() {
        return this.conceptosDeportivo;
    }


  public void setConceptosDeportivo (Collection<ConceptoDeportivo> conceptosDeportivo) {
        this.conceptosDeportivo = conceptosDeportivo;
    }



   public Long getIdControladoraEntidad() {
        return this.idControladoraEntidad;
    }


  public void setIdControladoraEntidad (Long idControladoraEntidad) {
        this.idControladoraEntidad = idControladoraEntidad;
    }



   public Collection<ConceptoIngreso> getConceptosIngreso() {
        return this.conceptosIngreso;
    }


  public void setConceptosIngreso (Collection<ConceptoIngreso> conceptosIngreso) {
        this.conceptosIngreso = conceptosIngreso;
    }



   public Collection<IngresoOtro> getIngresosOtro() {
        return this.ingresosOtro;
    }


  public void setIngresosOtro (Collection<IngresoOtro> ingresosOtro) {
        this.ingresosOtro = ingresosOtro;
    }



   public Collection<Egreso> getEgresos() {
        return this.egresos;
    }


  public void setEgresos (Collection<Egreso> egresos) {
        this.egresos = egresos;
    }



   public Collection<ConceptoEgreso> getConceptosEgreso() {
        return this.conceptosEgreso;
    }


  public void setConceptosEgreso (Collection<ConceptoEgreso> conceptosEgreso) {
        this.conceptosEgreso = conceptosEgreso;
    }

}

