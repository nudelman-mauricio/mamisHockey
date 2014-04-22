package main;

import java.util.Date;
import javax.persistence.EntityManager;
import logicaNegocios.Categoria;
import logicaNegocios.Torneo;

public class ControladoraGlobal {

    private ControladoraContabilidad unaControladoraContabilidad;
    private ControladoraEntidades unaControladoraEntidades;
    private ControladoraDeportiva unaControladoraDeportiva;

    public ControladoraGlobal(EntityManager em) {
        this.unaControladoraContabilidad = new ControladoraContabilidad(em);
        this.unaControladoraEntidades = new ControladoraEntidades(em);
        this.unaControladoraDeportiva = new ControladoraDeportiva(em);
    }
    
    //----------------------------CATEGORIAS-------------------------------------
    public Categoria buscarCategoria(Long id) {
        return this.unaControladoraDeportiva.buscarCategoria(id);
    }
    
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
    //------------------------------FIN CATEGORIAS------------------------------
    
    //------------------------------TORNEOS-------------------------------------    
    public Torneo buscarTorneo(Long id) {
        return this.unaControladoraDeportiva.buscarTorneo(id);
    }

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

    //-------------------------------FIN TORNEOS--------------------------------

    //---------------------------- GETERS Y SETERS -------------------------------------
    public ControladoraContabilidad getUnaControladoraContabilidad() {
        return this.unaControladoraContabilidad;
    }

    public void setUnaControladoraContabilidad(ControladoraContabilidad unaControladoraContabilidad) {
        this.unaControladoraContabilidad = unaControladoraContabilidad;
    }

    public ControladoraEntidades getUnaControladoraEntidades() {
        return this.unaControladoraEntidades;
    }

    public void setUnaControladoraEntidades(ControladoraEntidades unaControladoraEntidades) {
        this.unaControladoraEntidades = unaControladoraEntidades;
    }

    public ControladoraDeportiva getUnaControladoraDeportiva() {
        return this.unaControladoraDeportiva;
    }

    public void setUnaControladoraDeportiva(ControladoraDeportiva unaControladoraDeportiva) {
        this.unaControladoraDeportiva = unaControladoraDeportiva;
    }
}
