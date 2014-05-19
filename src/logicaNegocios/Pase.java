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
public class Pase implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Basic
    private double monto;

    @OneToOne(optional = false, targetEntity = Deuda.class)
    private Deuda unaDeuda;

    @OneToOne(optional = false, targetEntity = Equipo.class)
    private Equipo unEquipo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPase;

    @Basic
    private boolean borradoLogico;

    public Pase() {

    }

    public Pase(EntityManager entityManager, Date fecha, double monto, Equipo unEquipo, Deuda unaDeuda) {
        this.fecha = fecha;
        this.monto = monto;
        this.unEquipo = unEquipo;
        this.unaDeuda = unaDeuda;
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

    public Deuda getUnaDeuda() {
        return this.unaDeuda;
    }

    public void setUnaDeuda(Deuda unaDeuda) {
        this.unaDeuda = unaDeuda;
    }

    public Equipo getUnEquipo() {
        return this.unEquipo;
    }

    public void setUnEquipo(Equipo unEquipo) {
        this.unEquipo = unEquipo;
    }

    public Long getIdPase() {
        return this.idPase;
    }

    public void setIdPase(Long idPase) {
        this.idPase = idPase;
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
        if (aux instanceof Pase) {
            Pase pase = (Pase) aux;
            if (this.idPase > pase.idPase) {
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
            System.out.println("Error de Persistir Pase" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
