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
public class Tarjeta implements Serializable, Comparable {

    @Basic
    private boolean roja;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTarjeta;

    @Basic
    private boolean amarilla;

    @Basic
    private boolean verde;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public Tarjeta() {
    }

    public Tarjeta(EntityManager entityManager, boolean roja, boolean amarilla, boolean verde, String observacion) {
        this.roja = roja;
        this.amarilla = amarilla;
        this.verde = verde;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public boolean isRoja() {
        return this.roja;
    }

    public void setRoja(boolean roja) {
        this.roja = roja;
    }

    public Long getIdTarjeta() {
        return this.idTarjeta;
    }

    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public boolean isAmarilla() {
        return this.amarilla;
    }

    public void setAmarilla(boolean amarilla) {
        this.amarilla = amarilla;
    }

    public boolean isVerde() {
        return this.verde;
    }

    public void setVerde(boolean verde) {
        this.verde = verde;
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
        if (aux instanceof Tarjeta) {
            Tarjeta unaTarjeta = (Tarjeta) aux;
            if (this.idTarjeta > unaTarjeta.idTarjeta) {
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
            System.out.println("Error de Persistir Tarjeta" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
