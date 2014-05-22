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
public class Partido implements Serializable, Comparable {

    @Basic
    private String nombreVeedor;

    @OneToMany(targetEntity = Gol.class)
    private Collection<Gol> goles;

    @OneToOne(optional = false, targetEntity = Equipo.class)
    private Equipo unEquipoVisitante;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @OneToMany(targetEntity = Tarjeta.class)
    private Collection<Tarjeta> tarjetas;

    @Basic
    private String nombreAyudanteMesaLocal;

    @OneToOne(optional = false, targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unArbitro1;

    @OneToOne(optional = false, targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unArbitro2;

    @OneToOne(optional = false, targetEntity = Cancha.class)
    private Cancha unaCancha;

    @OneToOne(optional = false, targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unArbitro3;

    @Basic
    private String observaciones;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPartido;

    @OneToOne(optional = false, targetEntity = Equipo.class)
    private Equipo unEquipoLocal;

    @Basic
    private String nombreAyudanteMesaVisitante;

    @Basic
    private boolean borradoLogico;

    public Partido() {

    }

    public Partido(EntityManager entityManager, Equipo unEquipoVisitante, Date fecha, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, Cancha unaCancha, String observaciones, Equipo unEquipoLocal) {
        this.unEquipoVisitante = unEquipoVisitante;
        this.fecha = fecha;
        this.unArbitro1 = unArbitro1;
        this.unArbitro2 = unArbitro2;
        this.unArbitro3 = unArbitro3;
        this.unaCancha = unaCancha;
        this.observaciones = observaciones;
        this.unEquipoLocal = unEquipoLocal;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public String getNombreVeedor() {
        return this.nombreVeedor;
    }

    public void setNombreVeedor(String nombreVeedor) {
        this.nombreVeedor = nombreVeedor;
    }

    public Collection<Gol> getGoles() {
        return this.goles;
    }

    public void setGoles(Collection<Gol> goles) {
        this.goles = goles;
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
//----------------------------- FIN GETERS Y SETERS ----------------------------

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

//----------------------------------PERSISTENCIA--------------------------------
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
//------------------------------FIN PERSISTENCIA--------------------------------

//-----------------------------------GOLES--------------------------------------
    public void agregarGol(EntityManager entityManager, Gol unGol) {
        this.goles.add(unGol);
        this.persistir(entityManager);
    }

    public void quitarGol(EntityManager entityManager, Gol unGol) {
        this.goles.remove(unGol);
        this.persistir(entityManager);
    }
//---------------------------------FIN GOLES------------------------------------

//---------------------------------TARJETAS-------------------------------------
    public void agregarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.tarjetas.add(unaTarjeta);
        this.persistir(entityManager);
    }

    public void quitarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.tarjetas.remove(unaTarjeta);
        this.persistir(entityManager);
    }
//-------------------------------FIN TARJETAS-----------------------------------
}
