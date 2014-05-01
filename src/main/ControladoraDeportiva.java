package main;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import logicaNegocios.Categoria;
import logicaNegocios.Equipo;
import logicaNegocios.Partido;
import logicaNegocios.Persona;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Tarjeta;
import logicaNegocios.Torneo;

public class ControladoraDeportiva {

    private Collection<Torneo> torneos;
    private Collection<Categoria> categorias;
    private Collection<SancionTribunal> sancionesTribunal;
    private final EntityManager entityManager;

    public ControladoraDeportiva(EntityManager em) {
        this.entityManager = em;

        //CONSULTA PARA CARGAR TODAS LAS CATEGORIAS DE LA BD
        Query traerCategorias = em.createQuery("SELECT auxC FROM Categoria auxC");
        this.categorias = new TreeSet(traerCategorias.getResultList());
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Collection<Torneo> getTorneos() {
        return this.torneos;
    }

    public void setTorneos(Collection<Torneo> torneos) {
        this.torneos = torneos;
    }

    public Collection<Categoria> getCategorias() {
        return this.categorias;
    }

    public void setCategorias(Collection<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Collection<SancionTribunal> getSanciones() {
        return this.sancionesTribunal;
    }

    public void setSanciones(Collection<SancionTribunal> sancionesTribunal) {
        this.sancionesTribunal = sancionesTribunal;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

//------------------------------CATEGORIAS--------------------------------------
    public Categoria buscarCategoria(Long id) {
        Categoria resultado = null;
        for (Categoria aux : categorias) {
            if (Objects.equals(aux.getIdCategoria(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public Categoria buscarCategoriaBD(Long id) {
        Categoria resultado = null;
        Query traerCategoria = this.entityManager.createQuery("SELECT a FROM Categoria a WHERE a.idCategoria = " + id);
        resultado = (Categoria) traerCategoria.getSingleResult();
        return resultado;
    }

    public void crearCategoria(int cantMenores, String nombre) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Categoria unaCategoria = new Categoria(cantMenores, nombre);
            entityManager.persist(unaCategoria);
            this.categorias.add(unaCategoria);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear Categoria" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarCategoria(Categoria unaCategoria, int cantMenores, String nombre) {
        unaCategoria.setCantMenores(cantMenores);
        unaCategoria.setNombre(nombre);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unaCategoria);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar Categoria");
            tx.rollback();
        }
    }

    public void eliminarCategoria(Categoria unaCategoria) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unaCategoria.setBorradoLogico(true);
            entityManager.persist(unaCategoria);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error en eliminar categoria" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN CATEGORIAS----------------------------------

//------------------------------TORNEOS-----------------------------------------
    public Torneo buscarTorneo(Long id) {
        Torneo resultado = null;
        for (Torneo aux : torneos) {
            if (Objects.equals(aux.getIdTorneo(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearTorneo(Date diaInicio, Categoria unaCategoria, String nombre) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Torneo unTorneo = new Torneo(diaInicio, unaCategoria, nombre);
            entityManager.persist(unTorneo);
            this.torneos.add(unTorneo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear Torneo" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarTorneo(Torneo unTorneo, Date fechaInicio, Categoria unaCategoria, String nombre) {
        unTorneo.setFechaInicio(fechaInicio);
        unTorneo.setUnaCategoria(unaCategoria);
        unTorneo.setNombre(nombre);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unTorneo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar Torneo" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarTorneo(Torneo unTorneo) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unTorneo.setBorradoLogico(true);
            entityManager.persist(unTorneo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error en Eliminar Torneo" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN TORNEOS-------------------------------------

//--------------------------------SANCIONES-------------------------------------
    public SancionTribunal buscarSancionTribunal(Long id) {
        SancionTribunal resultado = null;
        for (SancionTribunal aux : sancionesTribunal) {
            if (Objects.equals(aux.getIdSancionTribunal(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearSancionTribunal(Equipo unEquipo, Persona unaPersona, Date vencimiento, int cantFechas, Date fecha, String observacion) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            SancionTribunal unaSancionTribunal = new SancionTribunal(vencimiento, cantFechas, fecha, observacion);
            entityManager.persist(unaSancionTribunal);
            this.sancionesTribunal.add(unaSancionTribunal);
            if (unEquipo != null) {
                unEquipo.agregarSancionTribunal(entityManager, unaSancionTribunal);
            }
            if (unaPersona != null) {
                unaPersona.agregarSancionTribunal(entityManager, unaSancionTribunal);
            }
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear Sanciones Tribunal" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarSancionTribunal(SancionTribunal unaSancionTribunal, Date vencimiento, int cantFechas, Date fecha, String observacion, Tarjeta unaTarjeta, Partido unPartido, int cantFechasCumplidas, String numeroResolucion, boolean borradoLogico) {
        unaSancionTribunal.setVencimiento(vencimiento);
        unaSancionTribunal.setCantFechas(cantFechas);
        unaSancionTribunal.setFecha(fecha);
        unaSancionTribunal.setObservacion(observacion);
        unaSancionTribunal.setUnaTarjeta(unaTarjeta);
        unaSancionTribunal.setUnPartido(unPartido);
        unaSancionTribunal.setCantFechasCumplidas(cantFechasCumplidas);
        unaSancionTribunal.setNumeroResolucion(numeroResolucion);
        unaSancionTribunal.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unaSancionTribunal);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar Sancion Tribunal" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarSancionTribunal(SancionTribunal unaSancionTribunal) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unaSancionTribunal.setBorradoLogico(true);
            entityManager.persist(unaSancionTribunal);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error en Eliminar Sancion Tribunal" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN SANCIONES-----------------------------------
}
