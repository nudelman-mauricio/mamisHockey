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
public  class IngresoOtro implements Serializable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idIngrsoOtro;

    @Basic
    private double monto;

    @OneToOne(optional=false,targetEntity=ConceptoIngreso.class)
    private ConceptoIngreso unConceptoIngreso;

    @Basic
    private String detalle;

    @Basic
    private boolean borradoLogico;

    public IngresoOtro(){

    }


   public Date getFecha() {
        return this.fecha;
    }


  public void setFecha (Date fecha) {
        this.fecha = fecha;
    }



   public Long getIdIngrsoOtro() {
        return this.idIngrsoOtro;
    }


  public void setIdIngrsoOtro (Long idIngrsoOtro) {
        this.idIngrsoOtro = idIngrsoOtro;
    }



   public double getMonto() {
        return this.monto;
    }


  public void setMonto (double monto) {
        this.monto = monto;
    }



   public ConceptoIngreso getUnConceptoIngreso() {
        return this.unConceptoIngreso;
    }


  public void setUnConceptoIngreso (ConceptoIngreso unConceptoIngreso) {
        this.unConceptoIngreso = unConceptoIngreso;
    }



   public String getDetalle() {
        return this.detalle;
    }


  public void setDetalle (String detalle) {
        this.detalle = detalle;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}

