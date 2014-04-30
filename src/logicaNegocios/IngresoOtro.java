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
public class IngresoOtro implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idIngresoOtro;

    @Basic
    private double monto;

    @OneToOne(optional = false, targetEntity = ConceptoIngreso.class)
    private ConceptoIngreso unConceptoIngreso;

    @Basic
    private String detalle;

    @Basic
    private boolean borradoLogico;

    public IngresoOtro() {

    }

    public IngresoOtro(Date fecha, double monto, String detalle) {
        this.fecha = fecha;
        this.monto = monto;
        this.detalle = detalle;
        this.borradoLogico = false;
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdIngrsoOtro() {
        return this.idIngresoOtro;
    }

    public void setIdIngrsoOtro(Long idIngresoOtro) {
        this.idIngresoOtro = idIngresoOtro;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public ConceptoIngreso getUnConceptoIngreso() {
        return this.unConceptoIngreso;
    }

    public void setUnConceptoIngreso(ConceptoIngreso unConceptoIngreso) {
        this.unConceptoIngreso = unConceptoIngreso;
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
//----------------------------- FIN GETERS Y SETERS ----------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof IngresoOtro) {
            IngresoOtro ingresoOtro = (IngresoOtro) aux;
            if (this.idIngresoOtro > ingresoOtro.idIngresoOtro) {
                retorno = 1;
            }
        }
        return retorno;
    }

    public Object getIdIngresoOtro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
