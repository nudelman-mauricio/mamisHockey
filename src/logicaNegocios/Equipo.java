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
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

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

    @OneToOne(optional = false, targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unAyudanteCampo;

    @OneToOne(optional = false, targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unPreparadorFisico;

    @OneToOne(optional = false, targetEntity = Socia.class)
    private Socia unaCapitana;

    @OneToMany(targetEntity = SancionTribunal.class)
    private Collection<SancionTribunal> sancionesTribunal;

    @OneToMany(targetEntity = Socia.class)
    private Collection<Socia> plantel;

    @Basic
    private boolean borradoLogico;

    @OneToOne(optional = false, targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unDT;

    public Equipo() {

    }

    public Equipo(EntityManager entityManager, String nombre, PersonaAuxiliar unDT) {
        this.nombre = nombre;
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

    public PersonaAuxiliar getUnAyudanteCampo() {
        return this.unAyudanteCampo;
    }

    public void setUnAyudanteCampo(PersonaAuxiliar unAyudanteCampo) {
        this.unAyudanteCampo = unAyudanteCampo;
    }

    public PersonaAuxiliar getUnPreparadorFisico() {
        return this.unPreparadorFisico;
    }

    public void setUnPreparadorFisico(PersonaAuxiliar unPreparadorFisico) {
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

    public PersonaAuxiliar getUnDT() {
        return this.unDT;
    }

    public void setUnDT(PersonaAuxiliar unDT) {
        this.unDT = unDT;
    }

//----------------------------- FIN GETERS Y SETERS ----------------------------
    @Override
    public String toString() {
        return nombre;
    }

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
    public void agregarDeuda(EntityManager entityManager, Deuda unaDeuda) {
        this.deudas.add(unaDeuda);
        this.persistir(entityManager);
    }
//---------------------------------FIN DEUDAS-----------------------------------

//--------------------------------INDUMENTARIAS---------------------------------
    public void agregarIndumentaria(EntityManager entityManager, Indumentaria unaIndumentaria) {
        this.indumentarias.add(unaIndumentaria);
        this.persistir(entityManager);
    }

    public void quitarIndumentaria(EntityManager entityManager, Indumentaria unaIndumentaria) {
        this.indumentarias.remove(unaIndumentaria);
        this.persistir(entityManager);
    }
//------------------------------FIN INDUMENTARIAS-------------------------------

//----------------------------- SANCIONES TRIBUNAL -----------------------------
    public void agregarSancionTribunal(EntityManager entityManager, SancionTribunal unaSancionTribunal) {
        this.sancionesTribunal.add(unaSancionTribunal);
        this.persistir(entityManager);
    }

    public void quitarSancionTribunal(EntityManager entityManager, SancionTribunal unaSancionTribunal) {
        this.sancionesTribunal.remove(unaSancionTribunal);
        this.persistir(entityManager);
    }
//--------------------------- FIN SANCIONES TRIBUNAL ---------------------------

//------------------------------------PLANTEL-----------------------------------
    public void agregarPlantel(EntityManager entityManager, Socia unaSocia) {
        this.plantel.add(unaSocia);
        this.persistir(entityManager);
    }

    public void quitarPlantel(EntityManager entityManager, Socia unaSocia) {
        if (unaSocia == this.unaCapitana) {
            this.setUnaCapitana(null); //NO SE SI ESTO VA A GENERAR ERROR POR NO SER CAMPO OPCIONAL EN LA BD
            this.cartelAdvertencia("Capitana");
        }
        if (unaSocia == this.unaCapitanaSuplente) {
            this.setUnaCapitanaSuplente(null);
            this.cartelAdvertencia("Capitana Suplente");
        }
        if (unaSocia == this.unaDelegada) {
            this.setUnaDelegada(null);
            this.cartelAdvertencia("Delegada");
        }
        if (unaSocia == this.unaDelegadaSuplente) {
            this.setUnaDelegadaSuplente(null);
            this.cartelAdvertencia("Delegada Suplente");
        }
        this.plantel.remove(unaSocia);
        this.persistir(entityManager);
    }

    private void cartelAdvertencia(String auxiliar) {
        //CARTEL ADVERTENCIA DE ELIMINAR A UNA SOCIA QUE ES CAPITANA O DELEGADA
        JOptionPane.showMessageDialog(null, "La operación que realizó dejó sin " + auxiliar + " al Equipo " + this.getNombre(), "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
//----------------------------------FIN PLANTEL---------------------------------

    @Override
    public int compareTo(Object aux
    ) {
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
