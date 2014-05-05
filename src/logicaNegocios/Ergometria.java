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
public class Ergometria implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaCaducidad;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaRealizacion;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idErgometria;

    @Basic
    private boolean aprobado;

    @Basic
    private String comentarios;

    @Basic
    private boolean borradoLogico;

    public Ergometria() {
    }

    public Ergometria(EntityManager entityManager, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        this.fechaCaducidad = fechaCaducidad;
        this.fechaRealizacion = fechaRealizacion;
        this.aprobado = aprobado;
        this.comentarios = comentarios;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Date getFechaCaducidad() {
        return this.fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getFechaRealizacion() {
        return this.fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Long getIdErgometria() {
        return this.idErgometria;
    }

    public void setIdErgometria(Long idErgometria) {
        this.idErgometria = idErgometria;
    }

    public boolean isAprobado() {
        return this.aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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
        if (aux instanceof Ergometria) {
            Ergometria ergometria = (Ergometria) aux;
            if (this.idErgometria > ergometria.idErgometria) {
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
            System.out.println("Error de Persistir Ergometria" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
