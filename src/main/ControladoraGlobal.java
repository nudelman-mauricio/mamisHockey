package main;

import java.util.Date;
import javax.persistence.EntityManager;
import logicaNegocios.*;

public class ControladoraGlobal {

    private final ControladoraContabilidad unaControladoraContabilidad;
    private final ControladoraEntidades unaControladoraEntidades;
    private final ControladoraDeportiva unaControladoraDeportiva;

    public ControladoraGlobal(EntityManager entityManager) {
        this.unaControladoraContabilidad = new ControladoraContabilidad(entityManager);
        this.unaControladoraEntidades = new ControladoraEntidades(entityManager);
        this.unaControladoraDeportiva = new ControladoraDeportiva(entityManager);
    }

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
}
