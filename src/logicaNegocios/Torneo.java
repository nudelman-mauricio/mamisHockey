package logicaNegocios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Torneo implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;

    @OneToMany(targetEntity = FechaTorneo.class)
    private Collection<FechaTorneo> fechasTorneo;

    @OneToOne(optional = false, targetEntity = Categoria.class)
    private Categoria unaCategoria;

    @Basic
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTorneo;

    @Basic
    private boolean borradoLogico;

    public Torneo() {

    }

    public Torneo(EntityManager entityManager, Date diaInicio, Categoria unaCategoria, String nombre) {
        this.fechaInicio = diaInicio;
        this.unaCategoria = unaCategoria;
        this.nombre = nombre;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Collection<FechaTorneo> getFechasTorneo() {
        return this.fechasTorneo;
    }

    public void setFechasTorneo(Collection<FechaTorneo> fechasTorneo) {
        this.fechasTorneo = fechasTorneo;
    }

    public Categoria getUnaCategoria() {
        return this.unaCategoria;
    }

    public void setUnaCategoria(Categoria unaCategoria) {
        this.unaCategoria = unaCategoria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdTorneo() {
        return this.idTorneo;
    }

    public void setIdTorneo(Long idTorneo) {
        this.idTorneo = idTorneo;
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
        if (aux instanceof Torneo) {
            Torneo torneo = (Torneo) aux;
            if (this.idTorneo > torneo.idTorneo) {
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
            System.out.println("Error de Persistir Torneo" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------

//---------------------------------FECHAS TORNEO--------------------------------    
    public void agregarFechaTorneo(EntityManager entityManager, FechaTorneo unaFechaTorneo) {
        this.fechasTorneo.add(unaFechaTorneo);
        this.persistir(entityManager);
    }

    public void quitarFechaTorneo(EntityManager entityManager, FechaTorneo unaFechaTorneo) {
        this.fechasTorneo.remove(unaFechaTorneo);
        this.persistir(entityManager);
    }
//------------------------------FIN FECHAS TORNEO-------------------------------
}
