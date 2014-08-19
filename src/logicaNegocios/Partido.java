package logicaNegocios;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
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
public class Partido implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @ElementCollection
    private Collection<Socia> plantelLocal;

    @Basic
    private PersonaAuxiliar unAyudanteCampoLocal;

    @Basic
    private String nombreVeedor;

    @Basic
    private PersonaAuxiliar unDTLocal;

    @Basic
    private PersonaAuxiliar unPreparadorFisicoLocal;

    @OneToMany(targetEntity = Gol.class)
    private Collection<Gol> goles;

    @Basic
    private PersonaAuxiliar unAyudanteCampoVisitante;

    @OneToOne(targetEntity = Equipo.class)
    private Equipo unEquipoVisitante;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Basic
    private PersonaAuxiliar unDTVisitante;

    @Basic
    private PersonaAuxiliar unPreparadorFisicoVisitante;

    @OneToMany(targetEntity = Tarjeta.class)
    private Collection<Tarjeta> tarjetas;

    @Basic
    private String nombreAyudanteMesaLocal;

    @OneToOne(targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unArbitro1;

    @OneToOne(targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unArbitro2;

    @OneToOne(targetEntity = Cancha.class)
    private Cancha unaCancha;

    @OneToOne(targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unArbitro3;

    @Basic
    private String observaciones;

    @ElementCollection
    private Collection<Socia> plantelVisitante;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPartido;

    @OneToOne(targetEntity = Equipo.class)
    private Equipo unEquipoLocal;

    @Basic
    private String nombreAyudanteMesaVisitante;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public Partido() {

    }

    public Partido(EntityManager entityManager, Date fecha, Cancha unaCancha, Equipo unEquipoLocal, Equipo unEquipoVisitante, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3) {
        this.fecha = fecha;
        this.unaCancha = unaCancha;
        this.unEquipoLocal = unEquipoLocal;
        this.unEquipoVisitante = unEquipoVisitante;
        this.unArbitro1 = unArbitro1;
        this.unArbitro2 = unArbitro2;
        this.unArbitro3 = unArbitro3;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Collection<Socia> getPlantelLocal() {
        return this.plantelLocal;
    }

    public void setPlantelLocal(Collection<Socia> plantelLocal) {
        this.plantelLocal = plantelLocal;
    }

    public PersonaAuxiliar getUnAyudanteCampoLocal() {
        return this.unAyudanteCampoLocal;
    }

    public void setUnAyudanteCampoLocal(PersonaAuxiliar unAyudanteCampoLocal) {
        this.unAyudanteCampoLocal = unAyudanteCampoLocal;
    }

    public String getNombreVeedor() {
        return this.nombreVeedor;
    }

    public void setNombreVeedor(String nombreVeedor) {
        this.nombreVeedor = nombreVeedor;
    }

    public PersonaAuxiliar getUnDTLocal() {
        return this.unDTLocal;
    }

    public void setUnDTLocal(PersonaAuxiliar unDTLocal) {
        this.unDTLocal = unDTLocal;
    }

    public PersonaAuxiliar getUnPreparadorFisicoLocal() {
        return this.unPreparadorFisicoLocal;
    }

    public void setUnPreparadorFisicoLocal(PersonaAuxiliar unPreparadorFisicoLocal) {
        this.unPreparadorFisicoLocal = unPreparadorFisicoLocal;
    }

    public Collection<Gol> getGoles() {
        return this.goles;
    }

    public void setGoles(Collection<Gol> goles) {
        this.goles = goles;
    }

    public PersonaAuxiliar getUnAyudanteCampoVisitante() {
        return this.unAyudanteCampoVisitante;
    }

    public void setUnAyudanteCampoVisitante(PersonaAuxiliar unAyudanteCampoVisitante) {
        this.unAyudanteCampoVisitante = unAyudanteCampoVisitante;
    }

    public Equipo getUnEquipoVisitante() {
        return this.unEquipoVisitante;
    }

    public void setUnEquipoVisitante(Equipo unEquipoVisitante) {
        this.unEquipoVisitante = unEquipoVisitante;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PersonaAuxiliar getUnDTVisitante() {
        return this.unDTVisitante;
    }

    public void setUnDTVisitante(PersonaAuxiliar unDTVisitante) {
        this.unDTVisitante = unDTVisitante;
    }

    public PersonaAuxiliar getUnPreparadorFisicoVisitante() {
        return this.unPreparadorFisicoVisitante;
    }

    public void setUnPreparadorFisicoVisitante(PersonaAuxiliar unPreparadorFisicoVisitante) {
        this.unPreparadorFisicoVisitante = unPreparadorFisicoVisitante;
    }

    public Collection<Tarjeta> getTarjetas() {
        return this.tarjetas;
    }

    public void setTarjetas(Collection<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public String getNombreAyudanteMesaLocal() {
        return this.nombreAyudanteMesaLocal;
    }

    public void setNombreAyudanteMesaLocal(String nombreAyudanteMesaLocal) {
        this.nombreAyudanteMesaLocal = nombreAyudanteMesaLocal;
    }

    public PersonaAuxiliar getUnArbitro1() {
        return this.unArbitro1;
    }

    public void setUnArbitro1(PersonaAuxiliar unArbitro1) {
        this.unArbitro1 = unArbitro1;
    }

    public PersonaAuxiliar getUnArbitro2() {
        return this.unArbitro2;
    }

    public void setUnArbitro2(PersonaAuxiliar unArbitro2) {
        this.unArbitro2 = unArbitro2;
    }

    public Cancha getUnaCancha() {
        return this.unaCancha;
    }

    public void setUnaCancha(Cancha unaCancha) {
        this.unaCancha = unaCancha;
    }

    public PersonaAuxiliar getUnArbitro3() {
        return this.unArbitro3;
    }

    public void setUnArbitro3(PersonaAuxiliar unArbitro3) {
        this.unArbitro3 = unArbitro3;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Collection<Socia> getPlantelVisitante() {
        return this.plantelVisitante;
    }

    public void setPlantelVisitante(Collection<Socia> plantelVisitante) {
        this.plantelVisitante = plantelVisitante;
    }

    public Long getIdPartido() {
        return this.idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Equipo getUnEquipoLocal() {
        return this.unEquipoLocal;
    }

    public void setUnEquipoLocal(Equipo unEquipoLocal) {
        this.unEquipoLocal = unEquipoLocal;
    }

    public String getNombreAyudanteMesaVisitante() {
        return this.nombreAyudanteMesaVisitante;
    }

    public void setNombreAyudanteMesaVisitante(String nombreAyudanteMesaVisitante) {
        this.nombreAyudanteMesaVisitante = nombreAyudanteMesaVisitante;
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
        if (aux instanceof Partido) {
            Partido partido = (Partido) aux;
            if (this.idPartido > partido.idPartido) {
                retorno = 1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return unEquipoVisitante + " vs " + unEquipoLocal + "Fecha: " + DateFormat.getDateInstance().format(this.fecha);
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
            System.out.println("Error de Persistir Partido" + e.getMessage());
            tx.rollback();
        }
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="Goles">
    public void agregarGol(EntityManager entityManager, Gol unGol) {
        this.goles.add(unGol);
        this.persistir(entityManager);
    }

    public void quitarGol(EntityManager entityManager, Gol unGol) {
        this.goles.remove(unGol);
        this.persistir(entityManager);
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Tarjetas">
    public void agregarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.tarjetas.add(unaTarjeta);
        this.persistir(entityManager);
    }

    public void quitarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.tarjetas.remove(unaTarjeta);
        this.persistir(entityManager);
    }
    // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="Planteles">
    /**
     * Devuelve TRUE si la jugadora que se pasa esta en el plantel local o
     * visitante
     *
     * @param unaSocia
     * @return
     */
    public boolean isJugadoraInscripta(Socia unaSocia) {
        boolean resultado = false;
        if (this.plantelLocal.contains(unaSocia)) {
            resultado = true;
        }
        if (this.plantelVisitante.contains(unaSocia)) {
            resultado = true;
        }
        return resultado;
    }
    // </editor-fold>   
}
