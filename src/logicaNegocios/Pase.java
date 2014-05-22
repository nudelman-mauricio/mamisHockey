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

    @OneToOne(optional = false, targetEntity = Deuda.class)
    private Deuda unaDeuda;

    @Basic
    private boolean libreDeudaClub;

    @Basic
    private boolean solicitudPase;

    @OneToOne(optional = false, targetEntity = Equipo.class)
    private Equipo unEquipo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPase;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public Pase() {

    }

    public Pase(EntityManager entityManager, Date fecha, Equipo unEquipo, Deuda unaDeuda, boolean libreDeudaClub, boolean solicitudPase, String observacion) {
        this.fecha = fecha;
        this.unEquipo = unEquipo;
        this.unaDeuda = unaDeuda;
        this.libreDeudaClub = libreDeudaClub;
        this.solicitudPase = solicitudPase;
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

    public Deuda getUnaDeuda() {
        return this.unaDeuda;
    }

    public void setUnaDeuda(Deuda unaDeuda) {
        this.unaDeuda = unaDeuda;
    }

    public boolean isLibreDeudaClub() {
        return this.libreDeudaClub;
    }

    public void setLibreDeudaClub(boolean libreDeudaClub) {
        this.libreDeudaClub = libreDeudaClub;
    }

    public boolean isSolicitudPase() {
        return this.solicitudPase;
    }

    public void setSolicitudPase(boolean solicitudPase) {
        this.solicitudPase = solicitudPase;
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
