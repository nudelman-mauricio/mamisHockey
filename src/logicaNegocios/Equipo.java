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

@Entity
public class Equipo implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEquipo;

    @OneToOne(optional = false, targetEntity = Socia.class)
    private Socia unaDelegadaSuplente;

    @OneToMany(targetEntity = Deuda.class)
    private Collection<Deuda> deudas;

    @OneToOne(optional = false, targetEntity = Socia.class)
    private Socia unaCapitanaSuplente;

    @OneToMany(targetEntity = Indumentaria.class)
    private Collection<Indumentaria> indumentarias;

    @OneToOne(optional = false, targetEntity = Socia.class)
    private Socia unaDelegada;

    @Basic
    private String nombre;

    @OneToOne(optional = false, targetEntity = CuerpoTecnico.class)
    private CuerpoTecnico unAyudanteCampo;

    @OneToOne(optional = false, targetEntity = CuerpoTecnico.class)
    private CuerpoTecnico unPreparadorFisico;

    @OneToOne(optional = false, targetEntity = Socia.class)
    private Socia unaCapitana;

    @OneToMany(targetEntity = SancionTribunal.class)
    private Collection<SancionTribunal> sancionesTribunal;

    @OneToMany(targetEntity = Socia.class)
    private Collection<Socia> plantel;

    @Basic
    private boolean borradoLogico;

    @OneToOne(optional = false, targetEntity = CuerpoTecnico.class)
    private CuerpoTecnico unDT;

    public Equipo() {
    }

    public Equipo(EntityManager entityManager, String nombre, Socia unaCapitana, Socia unaDelegada, CuerpoTecnico unDT) {
        this.nombre = nombre;
        this.unaCapitana = unaCapitana;
        this.unaDelegada = unaDelegada;
        this.unDT = unDT;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Long getIdEquipo() {
        return this.idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Socia getUnaDelegadaSuplente() {
        return this.unaDelegadaSuplente;
    }

    public void setUnaDelegadaSuplente(Socia unaDelegadaSuplente) {
        this.unaDelegadaSuplente = unaDelegadaSuplente;
    }

    public Collection<Deuda> getDeudas() {
        return this.deudas;
    }

    public void setDeudas(Collection<Deuda> deudas) {
        this.deudas = deudas;
    }

    public Socia getUnaCapitanaSuplente() {
        return this.unaCapitanaSuplente;
    }

    public void setUnaCapitanaSuplente(Socia unaCapitanaSuplente) {
        this.unaCapitanaSuplente = unaCapitanaSuplente;
    }

    public Collection<Indumentaria> getIndumentarias() {
        return this.indumentarias;
    }

    public void setIndumentarias(Collection<Indumentaria> indumentarias) {
        this.indumentarias = indumentarias;
    }

    public Socia getUnaDelegada() {
        return this.unaDelegada;
    }

    public void setUnaDelegada(Socia unaDelegada) {
        this.unaDelegada = unaDelegada;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CuerpoTecnico getUnAyudanteCampo() {
        return this.unAyudanteCampo;
    }

    public void setUnAyudanteCampo(CuerpoTecnico unAyudanteCampo) {
        this.unAyudanteCampo = unAyudanteCampo;
    }

    public CuerpoTecnico getUnPreparadorFisico() {
        return this.unPreparadorFisico;
    }

    public void setUnPreparadorFisico(CuerpoTecnico unPreparadorFisico) {
        this.unPreparadorFisico = unPreparadorFisico;
    }

    public Socia getUnaCapitana() {
        return this.unaCapitana;
    }

    public void setUnaCapitana(Socia unaCapitana) {
        this.unaCapitana = unaCapitana;
    }

    public Collection<SancionTribunal> getSancionesTribunal() {
        return this.sancionesTribunal;
    }

    public void setSancionesTribunal(Collection<SancionTribunal> sancionesTribunal) {
        this.sancionesTribunal = sancionesTribunal;
    }

    public Collection<Socia> getPlantel() {
        return this.plantel;
    }

    public void setPlantel(Collection<Socia> plantel) {
        this.plantel = plantel;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public CuerpoTecnico getUnDT() {
        return this.unDT;
    }

    public void setUnDT(CuerpoTecnico unDT) {
        this.unDT = unDT;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

//----------------------------------PERSISTENCIA--------------------------------
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSION FINAL -----------------------------------
            System.out.println("Error de Persistir Equipo" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------

//-----------------------------------DEUDAS-------------------------------------
    public void crearDeuda(EntityManager entityManager, Date fecha, double monto, boolean saldado, ConceptoDeportivo unConceptoDeportivo, String observacion) {
        Deuda unaDeuda = new Deuda(entityManager, fecha, monto, saldado, unConceptoDeportivo, observacion);
        this.deudas.add(unaDeuda);            
    }

    public void modificarDeuda(EntityManager entityManager, Deuda unaDeuda, Date fecha, double monto, boolean saldado, ConceptoDeportivo unConceptoDeportivo, String observacion, boolean borradoLogico) {
        unaDeuda.setFecha(fecha);
        unaDeuda.setMonto(monto);
        unaDeuda.setSaldado(saldado);
        unaDeuda.setUnConceptoDeportivo(unConceptoDeportivo);
        unaDeuda.setObservacion(observacion);
        unaDeuda.setBorradoLogico(borradoLogico);

        unaDeuda.persistir(entityManager);
    }

    public void eliminarDeuda(EntityManager entityManager, Deuda unaDeuda) {
        unaDeuda.setBorradoLogico(true);
        unaDeuda.persistir(entityManager);
    }
//---------------------------------FIN DEUDAS-----------------------------------

//--------------------------------INDUMENTARIAS---------------------------------
    public void crearIndumentaria(EntityManager entityManager, String camiseta, String media, String pollera) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Indumentaria unaIndumentaria = new Indumentaria(entityManager, camiseta, media, pollera);
            entityManager.persist(unaIndumentaria);
            this.indumentarias.add(unaIndumentaria);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear Indumentaria en Equipo" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarIndumentaria(EntityManager entityManager, Indumentaria unaIndumentaria, String camiseta, String media, String pollera, boolean borradoLogico) {
        unaIndumentaria.setCamiseta(camiseta);
        unaIndumentaria.setMedia(media);
        unaIndumentaria.setPollera(pollera);
        unaIndumentaria.setBorradoLogico(borradoLogico);

        unaIndumentaria.persistir(entityManager);
    }

    public void eliminarIndumentaria(EntityManager entityManager, Indumentaria unaIndumentaria) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unaIndumentaria.setBorradoLogico(true);
            entityManager.persist(unaIndumentaria);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error en Eliminar Indumentaria en Equipo" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN INDUMENTARIAS-------------------------------

//----------------------------- SANCIONES TRIBUNAL -----------------------------
    public void agregarSancionTribunal(EntityManager entityManager, SancionTribunal unaSancionTribunal) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            sancionesTribunal.add(unaSancionTribunal);
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Agregar Sancion Tribunal en Equipo" + e.getMessage());
            tx.rollback();
        }
    }
//--------------------------- FIN SANCIONES TRIBUNAL ---------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Equipo) {
            Equipo equipo = (Equipo) aux;
            if (this.idEquipo > equipo.idEquipo) {
                retorno = 1;
            }
        }
        return retorno;
    }
}
