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
public class Cuota implements Serializable, Comparable {

    @Basic
    private double monto;

    @OneToOne(optional = false, targetEntity = PagoCuota.class)
    private PagoCuota unPagoCuota;

    @Basic
    private String numero;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCuota;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaVencimiento;

    @Basic
    private boolean borradoLogico;

    public Cuota() {

    }

    public Cuota(EntityManager entityManager, double monto, Date fechaVencimiento, String numero) {
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.numero = numero;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public PagoCuota getUnPagoCuota() {
        return this.unPagoCuota;
    }

    public void setUnPagoCuota(PagoCuota unPagoCuota) {
        this.unPagoCuota = unPagoCuota;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getIdCuota() {
        return this.idCuota;
    }

    public void setIdCuota(Long idCuota) {
        this.idCuota = idCuota;
    }

    public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
        if (aux instanceof Cuota) {
            Cuota unaCuota = (Cuota) aux;
            if (this.idCuota > unaCuota.idCuota) {
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
            System.out.println("Error de Persistir Cuota" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
