package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FechaTorneo implements Serializable, Comparable {

    @Basic
    private int numeroFecha;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFecha;

    @OneToMany(targetEntity = Partido.class)
    private Collection<Partido> partidos;

    @Basic
    private boolean borradoLogico;

    public FechaTorneo() {
    }

    public FechaTorneo(EntityManager entityManager, int numeroFecha) {
        this.numeroFecha = numeroFecha;
        this.borradoLogico = false;
        
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public int getNumeroFecha() {
        return this.numeroFecha;
    }

    public void setNumeroFecha(int numeroFecha) {
        this.numeroFecha = numeroFecha;
    }

    public Long getIdFecha() {
        return this.idFecha;
    }

    public void setIdFecha(Long idFecha) {
        this.idFecha = idFecha;
    }

    public Collection<Partido> getPartidos() {
        return this.partidos;
    }

    public void setPartidos(Collection<Partido> partidos) {
        this.partidos = partidos;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

//-----------------------------------PARTIDOS-----------------------------------
    /*
    Cada constructor de todas las clases tiene que ser el encargado de persistir la nueva entidad
    cada seter tiene que ser el encargado de persistir
    los modificar gigantes no deberian existir
    */
//---------------------------------FIN PARTIDOS---------------------------------
    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof FechaTorneo) {
            FechaTorneo fechaTorneo = (FechaTorneo) aux;
            if (this.idFecha > fechaTorneo.idFecha) {
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
            System.out.println("Error de Persistir FechaTorneo" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
