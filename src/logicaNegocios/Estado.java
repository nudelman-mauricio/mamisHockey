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
public class Estado implements Serializable, Comparable {

    @Basic
    private boolean jugadora;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEstado;

    @Basic
    private boolean licencia;

    @Basic
    private boolean baja;

    @Basic
    private boolean activa;

    @Basic
    private boolean borradoLogico;

    public Estado() {
    }

    public Estado(EntityManager entityManager, boolean jugadora, Date fecha, boolean licencia, boolean baja, boolean activa) {
        this.jugadora = jugadora;
        this.fecha = fecha;
        this.licencia = licencia;
        this.baja = baja;
        this.activa = activa;
        this.borradoLogico = false;        
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public boolean isJugadora() {
        return this.jugadora;
    }

    public void setJugadora(boolean jugadora) {
        this.jugadora = jugadora;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public boolean isLicencia() {
        return this.licencia;
    }

    public void setLicencia(boolean licencia) {
        this.licencia = licencia;
    }

    public boolean isBaja() {
        return this.baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isActiva() {
        return this.activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
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
        if (aux instanceof Estado) {
            Estado estado = (Estado) aux;
            if (this.idEstado > estado.idEstado) {// --------- VER POSIBILIDAD DE COMPARAR POR FECHA
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
            System.out.println("Error de Persistir Estado" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
