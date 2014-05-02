package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Query;
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
    
    private EntityManager entityManager;
    

    public Torneo() {
    }

    public Torneo(EntityManager em, Date diaInicio, Categoria unaCategoria, String nombre) {
        this.fechaInicio = diaInicio;
        this.unaCategoria = unaCategoria;
        this.nombre = nombre;
        this.borradoLogico = false;
        this.entityManager = em;
        this.persistir(em);
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
    
    //-----------------------------------FechasTorneo-----------------------------------
    public FechaTorneo buscarFechaTorneoBd(Long id) {
        FechaTorneo resultado;
        Query traerFechaTorneo = this.entityManager.createQuery("SELECT auxFT FROM FechaTorneo auxFT WHERE auxFT.id = " + id);
        resultado = (FechaTorneo) traerFechaTorneo.getResultList();
        return resultado;
    }

    public FechaTorneo buscarFechaTorneo(Long id) {
        FechaTorneo resultado = null;
        for (FechaTorneo aux : fechasTorneo) {
            if (Objects.equals(aux.getIdFecha(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearFechaTorneo(EntityManager entityManager, int numeroFecha) {
        FechaTorneo unaFechaTorneo = new FechaTorneo(entityManager, numeroFecha );
        this.fechasTorneo.add(unaFechaTorneo);            
    }

    public void modificarFechaTorneo(FechaTorneo unaFechaTorneo, EntityManager entityManager, int numeroFecha, boolean borradoLogico) {
        unaFechaTorneo.setNumeroFecha(numeroFecha);
        unaFechaTorneo.setBorradoLogico(borradoLogico);
        
        unaFechaTorneo.persistir(entityManager);
    }

    public void eliminarFechaTorneo(FechaTorneo unaFechaTorneo) {
       unaFechaTorneo.setBorradoLogico(true);
       unaFechaTorneo.persistir(entityManager);
    }
//---------------------------------FIN PARTIDOS---------------------------------
    
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

    //----------------------------------- TEMPORAL BORRAR PARA LA VERSION FINAL ---------------
    @Override
    public String toString() {
        return "Torneo{" + "diaInicio=" + fechaInicio + ", fechasTorneo=" + fechasTorneo + ", unaCategoria=" + unaCategoria + ", nombre=" + nombre + ", idTorneo=" + idTorneo + ", borradoLogico=" + borradoLogico + '}';
    }

}
