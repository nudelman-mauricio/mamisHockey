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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PagoDeuda implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Basic
    private double monto;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPagoDeuda;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public PagoDeuda() {
    }

    public PagoDeuda(EntityManager entityManager, Date fecha, double monto, String observacion) {
        this.fecha = fecha;
        this.monto = monto;
        this.observacion = observacion;
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

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Long getIdPagoDeuda() {
        return this.idPagoDeuda;
    }

    public void setIdPagoDeuda(Long idPagoDeuda) {
        this.idPagoDeuda = idPagoDeuda;
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
        if (aux instanceof PagoDeuda) {
            PagoDeuda pagoDeuda = (PagoDeuda) aux;
            if (this.idPagoDeuda > pagoDeuda.idPagoDeuda) {
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
            System.out.println("Error de Persistir PagoDeuda" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
