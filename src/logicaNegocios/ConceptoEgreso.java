package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConceptoEgreso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConceptoEgreso;

    @Basic
    private String nombre;

    @Basic
    private String detalle;

    @Basic
    private boolean borradoLogico;

    public ConceptoEgreso() {

    }

    public Long getIdConceptoEgreso() {
        return this.idConceptoEgreso;
    }

    public void setIdConceptoEgreso(Long idConceptoEgreso) {
        this.idConceptoEgreso = idConceptoEgreso;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}
