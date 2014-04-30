package logicaNegocios;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Egreso implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Basic
    private double monto;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEgreso;

    @OneToOne(optional = false, targetEntity = ConceptoEgreso.class)
    private ConceptoEgreso unConceptoEgreso;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public Egreso() {

    }

    public Egreso(Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion) {
        this.fecha = fecha;
        this.monto = monto;
        this.unConceptoEgreso = unConceptoEgreso;
        this.observacion = observacion;
        this.borradoLogico = false;
    }    
    
//---------------------------- GETERS Y SETERS ---------------------------------
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Long getIdEgreso() {
        return this.idEgreso;
    }

    public void setIdEgreso(Long idEgreso) {
        this.idEgreso = idEgreso;
    }

    public ConceptoEgreso getUnConceptoEgreso() {
        return this.unConceptoEgreso;
    }

    public void setUnConceptoEgreso(ConceptoEgreso unConceptoEgreso) {
        this.unConceptoEgreso = unConceptoEgreso;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Egreso) {
            Egreso egreso = (Egreso) aux;
            if (this.idEgreso > egreso.idEgreso) {
                retorno = 1;
            }
        }
        return retorno;
    }
}
