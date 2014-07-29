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

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @OneToMany(targetEntity = Equipo.class)
    private Collection<Equipo> equiposInscriptos;

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
    // </editor-fold>

    public Torneo() {

    }

    public Torneo(EntityManager entityManager, Date diaInicio, Categoria unaCategoria, String nombre) {
        this.fechaInicio = diaInicio;
        this.unaCategoria = unaCategoria;
        this.nombre = nombre;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Collection<Equipo> getEquiposInscriptos() {
        return this.equiposInscriptos;
    }

    public void setEquiposInscriptos(Collection<Equipo> equiposInscriptos) {
        this.equiposInscriptos = equiposInscriptos;
    }

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
    // </editor-fold>

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

    // <editor-fold defaultstate="collapsed" desc="Persistencia">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Equipos Inscriptos">
    public int agregarEquipoInscripto(EntityManager entityManager, Equipo unEquipo) {
        this.equiposInscriptos.add(unEquipo);
        this.persistir(entityManager);
        return this.getCantidadEquiposInscriptos();
    }

    public int quitarEquipoInscripto(EntityManager entityManager, Equipo unEquipo) {
        this.equiposInscriptos.remove(unEquipo);
        this.persistir(entityManager);
        return this.getCantidadEquiposInscriptos();
    }

    /**
     * Devuelve la cantidad de Equipos Inscriptos en un torneo
     *
     * @return cantidadEquiposInscriptos
     */
    public int getCantidadEquiposInscriptos() {
        int cantidadEquiposInscriptos = 0;
        for (Equipo aux : this.equiposInscriptos) {
            if (!aux.isBorradoLogico()) {
                cantidadEquiposInscriptos++;
            }
        }
        return cantidadEquiposInscriptos;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fechas Torneos">
    public void agregarFechaTorneo(EntityManager entityManager, FechaTorneo unaFechaTorneo) {
        this.fechasTorneo.add(unaFechaTorneo);
        this.persistir(entityManager);
    }

    /**
     * Devuelve la cantidad de fechas de un torneo contando solo las NO borradas
     *
     * @return cantidadFechas
     */
    public int getCantidadFechas() {
        int cantidadFechas = 0;
        for (FechaTorneo aux : this.fechasTorneo) {
            if (!aux.isBorradoLogico()) {
                cantidadFechas++;
            }
        }
        return cantidadFechas;
    }
    
    public FechaTorneo getSiguienteFecha(FechaTorneo fechaActual){
        boolean bandera = false;
        for(FechaTorneo aux: this.fechasTorneo){
            if(bandera){
                return aux;
            }
            if(aux.getNumeroFecha() == fechaActual.getNumeroFecha() && !aux.isBorradoLogico()){
                bandera=true;
            }
        }
        return null;
    }
    
    public FechaTorneo getUnaFecha(int numeroFecha){
        for(FechaTorneo aux: this.fechasTorneo){
            if(aux.getNumeroFecha() == numeroFecha && !aux.isBorradoLogico() ){
                return aux;
            }
        }
        return null;
    }
    // </editor-fold>
}
