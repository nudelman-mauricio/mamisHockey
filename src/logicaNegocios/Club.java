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

@Entity
public class Club implements Serializable, Comparable {

    @Basic
    private String nombrePresidente;

    @Basic
    private String logo;

    @OneToMany(targetEntity = Cancha.class)
    private Collection<Cancha> canchas;

    @Basic
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClub;

    @Basic
    private boolean borradoLogico;

    @OneToMany(targetEntity = Equipo.class)
    private Collection<Equipo> equipos;

    @OneToOne(optional = false, targetEntity = Localidad.class)
    private Localidad unaLocalidad;

    public Club() {

    }

    public Club(Long idClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad) {
        this.idClub = idClub;
        this.nombre = nombre;
        this.logo = logo;
        this.nombrePresidente = nombrePresidente;
        this.unaLocalidad = unaLocalidad;
        this.borradoLogico = false;
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public String getNombrePresidente() {
        return this.nombrePresidente;
    }

    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Collection<Cancha> getCanchas() {
        return this.canchas;
    }

    public void setCanchas(Collection<Cancha> canchas) {
        this.canchas = canchas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdClub() {
        return this.idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public Collection<Equipo> getEquipos() {
        return this.equipos;
    }

    public void setEquipos(Collection<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Localidad getUnaLocalidad() {
        return this.unaLocalidad;
    }

    public void setUnaLocalidad(Localidad unaLocalidad) {
        this.unaLocalidad = unaLocalidad;
    }

//----------------------------- FIN GETERS Y SETERS ----------------------------
    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Club) {
            Club club = (Club) aux;
            if (this.idClub > club.idClub) {
                retorno = 1;
            }
        }
        return retorno;
    }

//------------------------------EQUIPOS-----------------------------------------   
//    public Equipo buscarEquipoBD(EntityManager entityManager, Long id) {
//        Equipo resultado;
//        Query traerEquipo = entityManager.createQuery("SELECT A FROM Equipo A WHERE A.idequipo = " + id);
//        resultado = (Equipo) traerEquipo.getSingleResult();
//        return resultado;
//    }
//
//    public Equipo buscarEquipoCollection(Long id) {
//        Equipo resultado = null;
//        for (Equipo aux : equipos) {
//            if (Objects.equals(aux.getIdEquipo(), id)) {
//                resultado = aux;
//            }
//        }
//        return resultado;
//    }
    public void crearEquipo(EntityManager entityManager, String nombre, Collection<Socia> plantel, Collection<Indumentaria> indumentarias, Socia unaCapitana, Socia unaDelegada, CuerpoTecnico unDT) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Equipo unEquipo = new Equipo(nombre, plantel, indumentarias, unaCapitana, unaDelegada, unDT);
            entityManager.persist(unEquipo);
            this.equipos.add(unEquipo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSION FINAL -----------------------------------
            System.out.println("Exception Crear Equipos" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarEquipo(EntityManager entityManager, Equipo unEquipo, String nombre, Socia unaCapitana, Socia unaCapitanaSuplente, Socia unaDelegada, Socia unaDelegadaSuplente, CuerpoTecnico unDT, CuerpoTecnico unPreparadorFisico, CuerpoTecnico unAyudanteCampo, boolean borradoLogico) {

        unEquipo.setNombre(nombre);
        unEquipo.setUnaCapitana(unaCapitana);
        unEquipo.setUnaCapitanaSuplente(unaCapitanaSuplente);
        unEquipo.setUnaDelegada(unaDelegada);
        unEquipo.setUnaDelegadaSuplente(unaDelegadaSuplente);
        unEquipo.setUnDT(unDT);
        unEquipo.setUnPreparadorFisico(unPreparadorFisico);
        unEquipo.setUnAyudanteCampo(unAyudanteCampo);
        unEquipo.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unEquipo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSION FINAL -----------------------------------
            System.out.println("Error de Modificar Equipo" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarEquipo(EntityManager entityManager, Equipo unEquipo) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unEquipo.setBorradoLogico(true);
            entityManager.persist(unEquipo);
            equipos.remove(unEquipo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSION FINAL -----------------------------------
            System.out.println("Error en Eliminar Equipo" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN EQUIPOS-------------------------------------

//-------------------------------- CANCHAS -------------------------------------
    public void crearCancha(EntityManager entityManager, String nombre, boolean seOcupa) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Cancha unaCancha = new Cancha(nombre, seOcupa);
            entityManager.persist(unaCancha);
            this.canchas.add(unaCancha);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSION FINAL -----------------------------------
            System.out.println("Exception Crear Cancha" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarCancha(EntityManager entityManager, Cancha unaCancha, String nombre, boolean seOcupa, boolean borradoLogico) {
        unaCancha.setNombre(nombre);
        unaCancha.setSeOcupa(seOcupa);
        unaCancha.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unaCancha);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSION FINAL -----------------------------------
            System.out.println("Error de Modificar Cancha" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarCancha(EntityManager entityManager, Cancha unaCancha) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unaCancha.setBorradoLogico(true);
            entityManager.persist(unaCancha);
            canchas.remove(unaCancha); //ME PARECE QUE ESTA LINEA NO VA (BORRADO LOGICO)
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSION FINAL -----------------------------------
            System.out.println("Error en Eliminar Cancha" + e.getMessage());
            tx.rollback();
        }
    }
//-------------------------------- FIN CANCHAS ---------------------------------
}
