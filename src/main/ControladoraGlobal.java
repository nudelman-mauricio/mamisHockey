package main;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.*;

public class ControladoraGlobal {

    private final ControladoraContabilidad unaControladoraContabilidad;
    private final ControladoraEntidades unaControladoraEntidades;
    private final ControladoraDeportiva unaControladoraDeportiva;

    public ControladoraGlobal(EntityManager entityManager) {
        this.unaControladoraContabilidad = new ControladoraContabilidad(entityManager);
        this.unaControladoraEntidades = new ControladoraEntidades(entityManager);
        this.unaControladoraDeportiva = new ControladoraDeportiva(entityManager);
        this.construirMeses(entityManager);
    }

//-----------------------METODOS de CONSULTAS-------------------------------
    
    //---------- IGestionSocias-------------------------------------------------
    public List <Object[]> buscarSociaConEquipoBD(String tipo, String dato) {
        return this.unaControladoraEntidades.buscarSociaConEquipoBD(tipo, dato);
    }
    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora) {
        unaControladoraEntidades.crearSocia(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora);
    }
    
    public void crearLocalidad(String nombre, String codPostal){
        
    }
    //----------FIN IGestionSocias----------------------------------------------
//----------------------FIN METODOS-----------------------------------------









//------------------------------ARBITROS----------------------------------------
    public void crearArbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso) {
        this.unaControladoraEntidades.crearArbitro(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso);
    }

    public void modificarArbitro(Arbitro unArbitro, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotocopiaDni) {
        if (unArbitro != null) {
            this.unaControladoraEntidades.modificarArbitro(unArbitro, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, borradoLogico, fotocopiaDni);
        }
    }

    public void eliminarArbitro(Arbitro unArbitro) {
        if (unArbitro != null) {
            this.unaControladoraEntidades.eliminarArbitro(unArbitro);
        }
    }
//-------------------------------FIN ARBITROS----------------------------------- 

//----------------------------CATEGORIAS----------------------------------------
    public void crearCategoria(int cantMenores, String nombre) {
        this.unaControladoraDeportiva.crearCategoria(cantMenores, nombre);
    }

    public void modificarCategoria(Categoria unaCategoria, int cantMenores, String nombre) {
        if (unaCategoria != null) {
            this.unaControladoraDeportiva.modificarCategoria(unaCategoria, cantMenores, nombre);
        }
    }

    public void eliminarCategoria(Categoria unaCategoria) {
        if (unaCategoria != null) {
            this.unaControladoraDeportiva.eliminarCategoria(unaCategoria);
        }
    }
//------------------------------FIN CATEGORIAS----------------------------------

//------------------------------TORNEOS-----------------------------------------
    public void crearTorneo(Date diaInicio, Categoria unaCategoria, String nombre) {
        this.unaControladoraDeportiva.crearTorneo(diaInicio, unaCategoria, nombre);
    }

    public void modificarTorneo(Torneo unTorneo, Date diaInicio, Categoria unaCategoria, String nombre) {
        if (unTorneo != null) {
            this.unaControladoraDeportiva.modificarTorneo(unTorneo, diaInicio, unaCategoria, nombre);
        }
    }

    public void eliminarTorneo(Torneo unTorneo) {
        if (unTorneo != null) {
            this.unaControladoraDeportiva.eliminarTorneo(unTorneo);
        }
    }
//-------------------------------FIN TORNEOS------------------------------------

//----------------------------------MESES---------------------------------------
    private void construirMeses(EntityManager entityManager) {
        Query tablaMesVacia = entityManager.createQuery("SELECT A FROM Mes A");
        if (tablaMesVacia.getResultList().isEmpty()) {
            Mes unMes;
            unMes = new Mes(entityManager, "Enero");
            unMes = new Mes(entityManager, "Febrero");
            unMes = new Mes(entityManager, "Marzo");
            unMes = new Mes(entityManager, "Abril");
            unMes = new Mes(entityManager, "Mayo");
            unMes = new Mes(entityManager, "Junio");
            unMes = new Mes(entityManager, "Julio");
            unMes = new Mes(entityManager, "Agosto");
            unMes = new Mes(entityManager, "Septiembre");
            unMes = new Mes(entityManager, "Octubre");
            unMes = new Mes(entityManager, "Nobiembre");
            unMes = new Mes(entityManager, "Diciembre");
        }
    }
//--------------------------------FIN MESES-------------------------------------
}
