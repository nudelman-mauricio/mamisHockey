package logicaNegocios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoEstado implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTipoEstado;

    @Basic
    private String nombre;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public TipoEstado() {

    }

    public TipoEstado(EntityManager entityManager, String nombre) {
        this.nombre = nombre;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Long getIdTipoEstado() {
        return this.idTipoEstado;
    }

    public void setIdTipoEstado(Long idTipoEstado) {
        this.idTipoEstado = idTipoEstado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Persistencia">
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Persistir TipoEstado" + e.getMessage());
            tx.rollback();
        }
    }
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof TipoEstado) {
            TipoEstado unTipoEstado = (TipoEstado) aux;
            if (this.idTipoEstado > unTipoEstado.idTipoEstado) {
                retorno = 1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return "Socia Estado: " + nombre;
    }
}
