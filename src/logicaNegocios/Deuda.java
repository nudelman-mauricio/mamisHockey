package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Deuda implements Serializable {

    @OneToMany(targetEntity = PagoDeuda.class)
    private Collection<PagoDeuda> pagosDeuda;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Basic
    private double monto;

    @Basic
    private boolean saldado;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDeuda;

    @OneToOne(optional = false, targetEntity = ConceptoDeportivo.class)
    private ConceptoDeportivo unConceptoDeportivo;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public Deuda() {

    }

    public Collection<PagoDeuda> getPagosDeuda() {
        return this.pagosDeuda;
    }

    public void setPagosDeuda(Collection<PagoDeuda> pagosDeuda) {
        this.pagosDeuda = pagosDeuda;
    }

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

    public boolean isSaldado() {
        return this.saldado;
    }

    public void setSaldado(boolean saldado) {
        this.saldado = saldado;
    }

    public Long getIdDeuda() {
        return this.idDeuda;
    }

    public void setIdDeuda(Long idDeuda) {
        this.idDeuda = idDeuda;
    }

    public ConceptoDeportivo getUnConceptoDeportivo() {
        return this.unConceptoDeportivo;
    }

    public void setUnConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        this.unConceptoDeportivo = unConceptoDeportivo;
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

}
