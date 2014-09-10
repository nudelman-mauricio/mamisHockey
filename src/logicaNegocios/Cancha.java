package logicaNegocios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cancha implements Serializable, Comparable {

    @Basic
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCancha;

    @Basic
    private boolean seOcupa;

    @Basic
    private boolean borradoLogico;

    @OneToOne(optional = false, targetEntity = CanchaTipo.class)
    private CanchaTipo unTipoCancha;

    public Cancha() {

    }

    public Cancha(EntityManager entityManager, String nombre, boolean seOcupa, CanchaTipo unTipoCancha) {
        this.nombre = nombre;
        this.seOcupa = seOcupa;
        this.unTipoCancha = unTipoCancha;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdCancha() {
        return this.idCancha;
    }

    public void setIdCancha(Long idCancha) {
        this.idCancha = idCancha;
    }

    public boolean isSeOcupa() {
        return this.seOcupa;
    }

    public void setSeOcupa(boolean seOcupa) {
        this.seOcupa = seOcupa;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public CanchaTipo getUnTipoCancha() {
        return this.unTipoCancha;
    }

    public void setUnTipoCancha(CanchaTipo unTipoCancha) {
        this.unTipoCancha = unTipoCancha;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------
    @Override
     public String toString() {
        return nombre+", "+this.getUnTipoCancha();
    }  
    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Cancha) {
            Cancha cancha = (Cancha) aux;
            if (this.idCancha > cancha.idCancha) {
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
            System.out.println("Error de Persistir Cancha" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
