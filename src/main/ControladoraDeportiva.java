package main;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.Categoria;
import logicaNegocios.Equipo;
import logicaNegocios.Gol;
import logicaNegocios.Partido;
import logicaNegocios.Persona;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
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
        Categoria unaCategoria = new Categoria(this.entityManager, cantMenores, nombre);
        this.categorias.add(unaCategoria);
    }
    
    public void modificarCategoria(Categoria unaCategoria, int cantMenores, String nombre) {
        unaCategoria.setCantMenores(cantMenores);
        unaCategoria.setNombre(nombre);
        unaCategoria.persistir(this.entityManager);
    }
    
    public void eliminarCategoria(Categoria unaCategoria) {
        unaCategoria.setBorradoLogico(true);
        unaCategoria.persistir(this.entityManager);
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
        Torneo unTorneo = new Torneo(this.entityManager, diaInicio, unaCategoria, nombre);
        this.torneos.add(unTorneo);
    }
    
    public void modificarTorneo(Torneo unTorneo, Date fechaInicio, Categoria unaCategoria, String nombre) {
        unTorneo.setFechaInicio(fechaInicio);
        unTorneo.setUnaCategoria(unaCategoria);
        unTorneo.setNombre(nombre);
        unTorneo.persistir(this.entityManager);
        
    }
    
    public void eliminarTorneo(Torneo unTorneo) {
        unTorneo.setBorradoLogico(true);
        unTorneo.persistir(this.entityManager);
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
        SancionTribunal unaSancionTribunal = new SancionTribunal(this.entityManager, vencimiento, cantFechas, fecha, observacion);
        this.sancionesTribunal.add(unaSancionTribunal);
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
        unaSancionTribunal.persistir(this.entityManager);
    }
    
    public void eliminarSancionTribunal(SancionTribunal unaSancionTribunal) {
        unaSancionTribunal.setBorradoLogico(true);
        unaSancionTribunal.persistir(this.entityManager);
    }
//------------------------------FIN SANCIONES-----------------------------------

//--------------------------------TARJETAS--------------------------------------
    public void crearTarjetaRoja(SancionTribunal unaSancionTribunal, Socia unaSocia, Partido unPartido, String tipo, String observacion) {
        Tarjeta unaTarjeta = new Tarjeta(this.entityManager, tipo, observacion);
        unaSocia.agregarTarjeta(this.entityManager, unaTarjeta);
        unPartido.agregarTarjeta(this.entityManager, unaTarjeta);
        unaSancionTribunal.setUnaTarjeta(unaTarjeta);
        unaSancionTribunal.persistir(this.entityManager);
    }
    
    public void crearTarjeta(Socia unaSocia, Partido unPartido, String tipo, String observacion) {
        Tarjeta unaTarjeta = new Tarjeta(this.entityManager, tipo, observacion);
        unaSocia.agregarTarjeta(this.entityManager, unaTarjeta);
        unPartido.agregarTarjeta(this.entityManager, unaTarjeta);
    }
    
    public void modificarTarjeta(Tarjeta unaTarjeta, String tipo, String observacion, boolean borradoLogico) {
        unaTarjeta.setTipo(tipo);
        unaTarjeta.setObservacion(observacion);
        unaTarjeta.setBorradoLogico(borradoLogico);
        unaTarjeta.persistir(this.entityManager);
    }
    
    public void eliminarTarjeta(Tarjeta unaTarjeta) {
        unaTarjeta.setBorradoLogico(true);
        unaTarjeta.persistir(this.entityManager);        
    }
//------------------------------FIN TARJETAS------------------------------------

//---------------------------------GOLES----------------------------------------
    public void crearGol(Socia unaSocia, Partido unPartido, String tiempo, boolean autoGol) {
        Gol unGol = new Gol(this.entityManager, tiempo, autoGol);
        unaSocia.agregarGol(this.entityManager, unGol);
        unPartido.agregarGol(this.entityManager, unGol);
    }
    
    public void modificarGol(Gol unGol, String tiempo, boolean autoGol, boolean borradoLogico) {
        unGol.setTiempo(tiempo);
        unGol.setAutoGol(autoGol);
        unGol.setBorradoLogico(borradoLogico);
        unGol.persistir(this.entityManager);
    }
    
    public void cambiarAutoraGol(Gol unGol, Socia unaAutoraActual, Socia unaAutoraNueva) {
        unaAutoraActual.quitarGol(entityManager, unGol);
        unaAutoraNueva.agregarGol(entityManager, unGol);
    }
    
    public void cambiarPartidoGol(Gol unGol, Partido unPartidoActual, Partido unPartidoNuevo) {
        unPartidoActual.quitarGol(this.entityManager, unGol);
        unPartidoNuevo.agregarGol(this.entityManager, unGol);
    }
    
    public void eliminarGol(Gol unGol) {
        unGol.setBorradoLogico(true);
        unGol.persistir(this.entityManager);
    }
//-------------------------------FIN GOLES--------------------------------------
}
