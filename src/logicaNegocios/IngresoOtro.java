package logicaNegocios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    public IngresoOtro(EntityManager entityManager, Date fecha, ConceptoIngreso unConceptoIngreso, double monto, String detalle) {
        this.fecha = fecha;
        this.unConceptoIngreso = unConceptoIngreso;
        this.monto = monto;
        this.detalle = detalle;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdIngresoOtro() {
        return this.idIngresoOtro;
    }

    public void setIdIngresoOtro(Long idIngresoOtro) {
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

//----------------------------------PERSISTENCIA--------------------------------
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Persistir IngresoOtro" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
