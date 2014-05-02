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
public class Gol implements Serializable, Comparable {

    @Basic
    private String tiempo;

    @Basic
    private boolean autoGol;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGol;

    @Basic
    private boolean borradoLogico;

    public Gol() {

    }

    public Gol(EntityManager entityManager, String tiempo, boolean autoGol) {
        this.tiempo = tiempo;
        this.autoGol = autoGol;
        this.borradoLogico = false;
        
        this.persistir(entityManager);
    }
    
//------------------------------ GETERS Y SETERS -------------------------------
    public String getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isAutoGol() {
        return this.autoGol;
    }

    public void setAutoGol(boolean autoGol) {
        this.autoGol = autoGol;
    }

    public Long getIdGol() {
        return this.idGol;
    }

    public void setIdGol(Long idGol) {
        this.idGol = idGol;
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
        if (aux instanceof Gol) {
            Gol gol = (Gol) aux;
            if (this.idGol > gol.idGol) {
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
            System.out.println("Error de Persistir Gol" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
