package main;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.Arbitro;
import logicaNegocios.Cancha;
import logicaNegocios.Categoria;
import logicaNegocios.Club;
import logicaNegocios.CuerpoTecnico;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Frecuencia;
import logicaNegocios.Gol;
import logicaNegocios.Indumentaria;
import logicaNegocios.Partido;
import logicaNegocios.Persona;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import logicaNegocios.TipoCancha;
import logicaNegocios.Torneo;

public class ControladoraDeportiva {

    private final EntityManager entityManager;

    public ControladoraDeportiva(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//--------------------------------SANCIONES-------------------------------------
    public void crearSancionTribunal(Equipo unEquipo, Persona unaPersona, Date fecha, String motivo, String detalles) {
        SancionTribunal unaSancionTribunal = new SancionTribunal(this.entityManager, fecha, motivo, detalles);
    }

    public void modificarSancionTribunal(SancionTribunal unaSancionTribunal, Date vencimiento, int cantFechas, Date fecha, String motivo, String detalles, Tarjeta unaTarjeta, Partido unPartido, int cantFechasCumplidas, String numeroResolucion, boolean borradoLogico) {
        unaSancionTribunal.setVencimiento(vencimiento);
        unaSancionTribunal.setCantFechas(cantFechas);
        unaSancionTribunal.setFecha(fecha);
        unaSancionTribunal.setMotivo(motivo);
        unaSancionTribunal.setDetalles(detalles);
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
    public void crearTarjetaRoja(SancionTribunal unaSancionTribunal, Socia unaSocia, Partido unPartido, String motivo, String detalles) {
        Tarjeta unaTarjeta = new Tarjeta(this.entityManager, "Roja", motivo, detalles);
        unaSocia.agregarTarjeta(this.entityManager, unaTarjeta);
        unPartido.agregarTarjeta(this.entityManager, unaTarjeta);
        unaSancionTribunal.setUnaTarjeta(unaTarjeta);
        unaSancionTribunal.persistir(this.entityManager);
    }

    public void crearTarjeta(Socia unaSocia, Partido unPartido, String tipo, String motivo, String detalles) {
        Tarjeta unaTarjeta = new Tarjeta(this.entityManager, tipo, motivo, detalles);
        unaSocia.agregarTarjeta(this.entityManager, unaTarjeta);
        unPartido.agregarTarjeta(this.entityManager, unaTarjeta);
    }

    public void modificarTarjeta(Tarjeta unaTarjeta, String tipo, String motivo, String detalles, boolean borradoLogico) {
        unaTarjeta.setTipo(tipo);
        unaTarjeta.setMotivo(motivo);
        unaTarjeta.setDetalles(detalles);
        unaTarjeta.setBorradoLogico(borradoLogico);
        unaTarjeta.persistir(this.entityManager);
    }

    public void eliminarTarjeta(Tarjeta unaTarjeta) {
        unaTarjeta.setBorradoLogico(true);
        unaTarjeta.persistir(this.entityManager);
    }
//------------------------------FIN TARJETAS------------------------------------

//------------------------------EQUIPOS-----------------------------------------   
    public Equipo buscarEquipoBD(Long id) {
        Equipo resultado;
        Query traerEquipo = this.entityManager.createQuery("SELECT A FROM Equipo A WHERE A.idequipo = " + id);
        resultado = (Equipo) traerEquipo.getSingleResult();
        return resultado;
    }
    
    public List<Equipo> getEquipos(){   
        Query traerEquipos = this.entityManager.createQuery("SELECT E FROM Equipo E");
        List<Equipo> unaListaResultado = traerEquipos.getResultList();        
        return unaListaResultado;
    }

    public void crearEquipo(Club unClub, String nombre, Socia unaCapitana, Socia unaDelegada, CuerpoTecnico unDT) {
        Equipo unEquipo = new Equipo(this.entityManager, nombre, unaCapitana, unaDelegada, unDT);
        unClub.agregarEquipo(this.entityManager, unEquipo);
    }

    public void modificarEquipo(Equipo unEquipo, String nombre, Socia unaCapitana, Socia unaCapitanaSuplente, Socia unaDelegada, Socia unaDelegadaSuplente, CuerpoTecnico unDT, CuerpoTecnico unPreparadorFisico, CuerpoTecnico unAyudanteCampo, boolean borradoLogico) {
        unEquipo.setNombre(nombre);
        unEquipo.setUnaCapitana(unaCapitana);
        unEquipo.setUnaCapitanaSuplente(unaCapitanaSuplente);
        unEquipo.setUnaDelegada(unaDelegada);
        unEquipo.setUnaDelegadaSuplente(unaDelegadaSuplente);
        unEquipo.setUnDT(unDT);
        unEquipo.setUnPreparadorFisico(unPreparadorFisico);
        unEquipo.setUnAyudanteCampo(unAyudanteCampo);
        unEquipo.setBorradoLogico(borradoLogico);
        unEquipo.persistir(this.entityManager);
    }

    public void cambiarEquipoDeClub(Equipo unEquipo, Club unClubActual, Club unClubNuevo) {
        unClubActual.quitarEquipo(this.entityManager, unEquipo);
        unClubNuevo.agregarEquipo(this.entityManager, unEquipo);
    }

    public void eliminarEquipo(EntityManager entityManager, Equipo unEquipo) {
        unEquipo.setBorradoLogico(true);
        unEquipo.persistir(entityManager);
    }
//------------------------------FIN EQUIPOS-------------------------------------

//--------------------------------INDUMENTARIAS---------------------------------
    public void crearIndumentaria(Equipo unEquipo, String camiseta, String media, String pollera) {
        Indumentaria unaIndumentaria = new Indumentaria(this.entityManager, camiseta, media, pollera);
        unEquipo.agregarIndumentaria(this.entityManager, unaIndumentaria);
    }

    public void modificarIndumentaria(Indumentaria unaIndumentaria, String camiseta, String media, String pollera, boolean borradoLogico) {
        unaIndumentaria.setCamiseta(camiseta);
        unaIndumentaria.setMedia(media);
        unaIndumentaria.setPollera(pollera);
        unaIndumentaria.setBorradoLogico(borradoLogico);
        unaIndumentaria.persistir(this.entityManager);
    }

    public void cambiarIndumentariaDeEquipo(Indumentaria unaIndumentaria, Equipo unEquipoActual, Equipo unEquipoNuevo) {
        unEquipoActual.quitarIndumentaria(this.entityManager, unaIndumentaria);
        unEquipoNuevo.agregarIndumentaria(this.entityManager, unaIndumentaria);
    }

    public void eliminarIndumentaria(Indumentaria unaIndumentaria) {
        unaIndumentaria.setBorradoLogico(true);
        unaIndumentaria.persistir(this.entityManager);
    }
//------------------------------FIN INDUMENTARIAS-------------------------------

//-------------------------------- CANCHAS -------------------------------------
    public void crearCancha(Club unClub, String nombre, boolean seOcupa, TipoCancha unTipoCancha) {
        Cancha unaCancha = new Cancha(this.entityManager, nombre, seOcupa, unTipoCancha);
        unClub.agregarCancha(this.entityManager, unaCancha);
    }

    public void modificarCancha(Cancha unaCancha, String nombre, boolean seOcupa, TipoCancha unTipoCancha, boolean borradoLogico) {
        unaCancha.setNombre(nombre);
        unaCancha.setSeOcupa(seOcupa);
        unaCancha.setUnTipoCancha(unTipoCancha);
        unaCancha.setBorradoLogico(borradoLogico);
        unaCancha.persistir(this.entityManager);
    }

    public void cambiarCanchaDeClub(Cancha unaCancha, Club unClubActual, Club unClubNuevo) {
        unClubActual.quitarCancha(this.entityManager, unaCancha);
        unClubNuevo.agregarCancha(this.entityManager, unaCancha);
    }

    public void eliminarCancha(Cancha unaCancha) {
        unaCancha.setBorradoLogico(true);
        unaCancha.persistir(this.entityManager);
    }
//--------------------------------FIN CANCHAS-----------------------------------

//--------------------------------TIPO CANCHA-----------------------------------
    public void crearTipoCancha(double monto, Frecuencia unaFrecuencia, String nombre) {
        TipoCancha unTipoCancha = new TipoCancha(this.entityManager, monto, unaFrecuencia, nombre);
    }

    public void modificarTipoCancha(TipoCancha unTipoCancha, double monto, Frecuencia unaFrecuencia, String nombre, boolean borradoLogico) {
        unTipoCancha.setMonto(monto);
        unTipoCancha.setUnaFrecuencia(unaFrecuencia);
        unTipoCancha.setNombre(nombre);
        unTipoCancha.setBorradoLogico(borradoLogico);
        unTipoCancha.persistir(this.entityManager);
    }

    public void eliminarTipoCancha(TipoCancha unTipoCancha) {
        unTipoCancha.setBorradoLogico(true);
        unTipoCancha.persistir(this.entityManager);
    }
//-----------------------------FIN TIPO CANCHA----------------------------------

//------------------------------CATEGORIAS--------------------------------------    
    public Categoria buscarCategoriaBD(Long id) {
        Categoria resultado = null;
        Query traerCategoria = this.entityManager.createQuery("SELECT a FROM Categoria a WHERE a.idCategoria = " + id);
        resultado = (Categoria) traerCategoria.getSingleResult();
        return resultado;
    }

    public void crearCategoria(int cantMenores, String nombre) {
        Categoria unaCategoria = new Categoria(this.entityManager, cantMenores, nombre);
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
    public void crearTorneo(Date diaInicio, Categoria unaCategoria, String nombre) {
        Torneo unTorneo = new Torneo(this.entityManager, diaInicio, unaCategoria, nombre);
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
    
    public Torneo getTorneoBD(Long idTorneo) {
        Torneo resultado;
        Query traerTorneo = this.entityManager.createQuery("SELECT T FROM Torneo T WHERE T.idTorneo = " + idTorneo);
        resultado = (Torneo) traerTorneo.getResultList();
        return resultado;
    }
    
    public List<Torneo> getTorneosBD() {
        String unaConsulta = ("SELECT T FROM Torneo T");
        List<Torneo> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN TORNEOS-------------------------------------

//---------------------------------FECHAS TORNEO--------------------------------
    public FechaTorneo buscarFechaTorneoBd(EntityManager entityManager, Long id) {
        FechaTorneo resultado;
        Query traerFechaTorneo = entityManager.createQuery("SELECT auxFT FROM FechaTorneo auxFT WHERE auxFT.id = " + id);
        resultado = (FechaTorneo) traerFechaTorneo.getResultList();
        return resultado;
    }

    public void crearFechaTorneo(Torneo unTorneo, int numeroFecha) {
        FechaTorneo unaFechaTorneo = new FechaTorneo(this.entityManager, numeroFecha);
        unTorneo.agregarFechaTorneo(this.entityManager, unaFechaTorneo);
    }

    public void modificarFechaTorneo(FechaTorneo unaFechaTorneo, int numeroFecha, boolean borradoLogico) {
        unaFechaTorneo.setNumeroFecha(numeroFecha);
        unaFechaTorneo.setBorradoLogico(borradoLogico);
        unaFechaTorneo.persistir(this.entityManager);
    }

    public void cambiarFechaTorneoDeTorneo(FechaTorneo unaFechaTorneo, Torneo unTorneoActual, Torneo unTorneoNuevo) {
        unTorneoActual.quitarFechaTorneo(this.entityManager, unaFechaTorneo);
        unTorneoNuevo.agregarFechaTorneo(this.entityManager, unaFechaTorneo);
    }

    public void eliminarFechaTorneo(FechaTorneo unaFechaTorneo) {
        unaFechaTorneo.setBorradoLogico(true);
        unaFechaTorneo.persistir(this.entityManager);
    }
//------------------------------FIN FECHAS TORNEO-------------------------------

//-----------------------------------PARTIDOS-----------------------------------
    public Partido buscarPartidoBD(EntityManager entityManager, Long id) {
        Partido resultado;
        Query traerPartido = entityManager.createQuery("SELECT auxP FROM Partido auxP WHERE auxP.id = " + id);
        resultado = (Partido) traerPartido.getResultList();
        return resultado;
    }

    public void crearPartido(FechaTorneo unaFechaTorneo, Equipo unEquipoVisitante, Date fecha, Arbitro unArbitro1, Arbitro unArbitro2, Cancha unaCancha, String observaciones, Equipo unEquipoLocal) {
        Partido unPartido = new Partido(this.entityManager, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unaCancha, observaciones, unEquipoLocal);
        unaFechaTorneo.agregarPartido(this.entityManager, unPartido);
    }

    public void modificarPartido(Partido unPartido, Equipo unEquipoVisitante, Date fecha, Arbitro unArbitro1, Arbitro unArbitro2, Cancha unaCancha, String observaciones, Equipo unEquipoLocal, boolean borradoLogico) {
        unPartido.setBorradoLogico(borradoLogico);
        unPartido.setFecha(fecha);
        unPartido.setObservaciones(observaciones);
        unPartido.setUnArbitro1(unArbitro1);
        unPartido.setUnArbitro2(unArbitro2);
        unPartido.setUnEquipoLocal(unEquipoLocal);
        unPartido.setUnEquipoVisitante(unEquipoVisitante);
        unPartido.setUnaCancha(unaCancha);
        unPartido.persistir(this.entityManager);
    }

    public void cambiarPartidoDeFechaTorneo(Partido unPartido, FechaTorneo unaFechaTorneoActual, FechaTorneo unaFechaTorneoNueva) {
        unaFechaTorneoActual.quitarPartido(this.entityManager, unPartido);
        unaFechaTorneoNueva.agregarPartido(this.entityManager, unPartido);
    }

    public void eliminarPartido(Partido unPartido) {
        unPartido.setBorradoLogico(true);
        unPartido.persistir(this.entityManager);
    }
//---------------------------------FIN PARTIDOS---------------------------------

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
