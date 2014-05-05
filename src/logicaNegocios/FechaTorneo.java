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
import javax.persistence.Query;

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
    public Partido buscarPartidoBD(EntityManager entityManager, Long id) {
        Partido resultado;
        Query traerPartido = entityManager.createQuery("SELECT auxP FROM Partido auxP WHERE auxP.id = " + id);
        resultado = (Partido) traerPartido.getResultList();
        return resultado;
    }

    public Partido buscarPartido(Long id) {
        Partido resultado = null;
        for (Partido aux : partidos) {
            if (Objects.equals(aux.getIdPartido(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearPartido(EntityManager entityManager, Equipo unEquipoVisitante, Date fecha, Arbitro unArbitro1, Arbitro unArbitro2, Cancha unaCancha, String observaciones, Equipo unEquipoLocal) {
        Partido unPartido = new Partido(entityManager, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unaCancha, observaciones, unEquipoLocal);
        this.partidos.add(unPartido);
        this.persistir(entityManager);
    }

    public void modificarPartido(EntityManager entityManager, Partido unPartido, Equipo unEquipoVisitante, Date fecha, Arbitro unArbitro1, Arbitro unArbitro2, Cancha unaCancha, String observaciones, Equipo unEquipoLocal, boolean borradoLogico) {
        unPartido.setBorradoLogico(borradoLogico);
        unPartido.setFecha(fecha);
        unPartido.setObservaciones(observaciones);
        unPartido.setUnArbitro1(unArbitro1);
        unPartido.setUnArbitro2(unArbitro2);
        unPartido.setUnEquipoLocal(unEquipoLocal);
        unPartido.setUnEquipoVisitante(unEquipoVisitante);
        unPartido.setUnaCancha(unaCancha);
        unPartido.persistir(entityManager);
    }

    public void eliminarPartido(EntityManager entityManager, Partido unPartido) {
        unPartido.setBorradoLogico(true);
        unPartido.persistir(entityManager);
    }
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
