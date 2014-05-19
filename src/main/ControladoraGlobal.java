package main;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.*;

public class ControladoraGlobal {

    private final ControladoraContabilidad unaControladoraContabilidad;
    private final ControladoraEntidades unaControladoraEntidades;
    private final ControladoraDeportiva unaControladoraDeportiva;
    //private final EntityManager entityManager;

    public ControladoraGlobal(EntityManager entityManager) {
        //this.entityManager = entityManager;
        this.unaControladoraContabilidad = new ControladoraContabilidad(entityManager);
        this.unaControladoraEntidades = new ControladoraEntidades(entityManager);
        this.unaControladoraDeportiva = new ControladoraDeportiva(entityManager);
        this.construirMeses(entityManager);
    }

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
//
//------------------------------------------------------------------------------
//-----------------------CONTROLADORA ENTIDADES---------------------------------
//------------------------------------------------------------------------------

//------------------------------CUERPO TECNICO----------------------------------
    public CuerpoTecnico buscarCuerpoTecnicoBD(Long dni) {
        return this.unaControladoraEntidades.buscarCuerpoTecnicoBD(dni);
    }

    public void crearCuerpoTecnico(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, boolean activo, String email, String telFijo, String telCelular) {
        this.unaControladoraEntidades.crearCuerpoTecnico(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, activo, email, telFijo, telCelular);
    }

    public void modificarCuerpoTecnico(CuerpoTecnico unCuerpoTecnico, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotocopiaDni, boolean activo) {
        this.unaControladoraEntidades.modificarCuerpoTecnico(unCuerpoTecnico, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, borradoLogico, fotocopiaDni, activo);
    }

    public void eliminarCuerpoTecnico(CuerpoTecnico unCuerpoTecnico) {
        this.unaControladoraEntidades.eliminarCuerpoTecnico(unCuerpoTecnico);
    }
//------------------------------FIN CUERPO TECNICO------------------------------

//----------------------------------ARBITROS------------------------------------
    public Arbitro buscarUnArbitroBD(Long dni) {
        return this.unaControladoraEntidades.buscarArbitroBD(dni);
    }
    
     public List<Object[]> buscarArbitrosBD(String dato) {
        return this.unaControladoraEntidades.buscarArbitrosBD(dato);
    }

    public void crearArbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, String email, String telFijo, String telCelular) {
        this.unaControladoraEntidades.crearArbitro(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular);
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

//------------------------------CLUBES------------------------------------------   
    public Club buscarClubBD(Long id) {
        return this.unaControladoraEntidades.buscarClubBD(id);
    }

    public void crearClub(Long idClub, String nombre, String nombrePresidente, Localidad unaLocalidad) {
        this.unaControladoraEntidades.crearClub(idClub, nombre, nombrePresidente, unaLocalidad);
    }

    public void modificarClub(Club unClub, Long idClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarClub(unClub, idClub, nombre, logo, nombrePresidente, unaLocalidad, borradoLogico);
    }

    public void eliminarClub(Club unClub) {
        this.unaControladoraEntidades.eliminarClub(unClub);
    }
//------------------------------FIN CLUBES--------------------------------------

//------------------------------SOCIAS------------------------------------------   
    public List<Object[]> buscarSociasBDFiltro(String dato) {
        return this.unaControladoraEntidades.buscarSociasBDFiltro(dato);
    }

    public Socia buscarSociaBD(Long dni) {
        return this.unaControladoraEntidades.buscarSociaBD(dni);
    }

    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora, String email, String telFijo, String telCelular) {
        this.unaControladoraEntidades.crearSocia(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora, email, telFijo, telCelular);
    }

    public void modificarSocia(Socia unaSocia, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotoCarnet, boolean exJugadora) {
        this.unaControladoraEntidades.modificarSocia(unaSocia, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, borradoLogico, fotoCarnet, exJugadora);
    }

    public void eliminarSocia(Socia unaSocia) {
        this.unaControladoraEntidades.eliminarSocia(unaSocia);
    }
//------------------------------FIN SOCIAS--------------------------------------

//-----------------------------------PASES--------------------------------------
    public Pase buscarPaseBD(EntityManager entityManager, Long id) {
        return this.unaControladoraEntidades.buscarPaseBD(entityManager, id);
    }

    public void crearPase(Socia unaSocia, Date fecha, double monto, Equipo unEquipo) {
        this.unaControladoraEntidades.crearPase(unaSocia, fecha, monto, unEquipo);
    }

    public void modificarPase(Pase unPase, Date fecha, double monto, Equipo unEquipo, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarPase(unPase, fecha, monto, unEquipo, borradoLogico);
    }

    public void modificarPaseDeSocia(Pase unPase, Socia unaSociaActual, Socia unaSociaNueva) {
        this.unaControladoraEntidades.modificarPaseDeSocia(unPase, unaSociaActual, unaSociaNueva);
    }

    public void eliminarPase(Pase unPase) {
        this.unaControladoraEntidades.eliminarPase(unPase);
    }
//---------------------------------FIN PASES------------------------------------

//------------------------------LOCALIDADES-------------------------------------   
    public List<Localidad> getLocalidades() {
        return unaControladoraEntidades.getLocalidades();
    }

    public Localidad buscarLocalidBD(Long id) {
        return this.unaControladoraEntidades.buscarLocalidBD(id);
    }

    public void crearLocalidad(String nombre, String codPostal) {
        this.unaControladoraEntidades.crearLocalidad(nombre, codPostal);
    }

    public void modificarLocalidad(Localidad unaLocalidad, String nombre, String codPostal, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarLocalidad(unaLocalidad, nombre, codPostal, borradoLogico);
    }

    public void eliminarLocalidad(Localidad unaLocalidad) {
        this.unaControladoraEntidades.eliminarLocalidad(unaLocalidad);
    }
//------------------------------FIN LOCALIDADES---------------------------------

//-----------------------------------ERGOMETRIA---------------------------------
    public Ergometria buscarErgometriaBD(Long id) {
        return this.unaControladoraEntidades.buscarErgometriaBD(id);
    }

    public void crearErgometria(Socia unaSocia, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        this.unaControladoraEntidades.crearErgometria(unaSocia, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
    }

    public void modificarErgometria(Ergometria unaErgometria, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        this.unaControladoraEntidades.modificarErgometria(unaErgometria, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
    }

    public void cambiarErgometriaDeSocia(Ergometria unaErgometria, Socia unaSociaActual, Socia unaSociaNueva) {
        this.unaControladoraEntidades.cambiarErgometriaDeSocia(unaErgometria, unaSociaActual, unaSociaNueva);
    }

    public void eliminarErgometria(Ergometria unaErgometria) {
        this.unaControladoraEntidades.eliminarErgometria(unaErgometria);
    }
//---------------------------------FIN ERGOMETRIAS---------------------------------

//-----------------------------------ESTADOS-------------------------------------
    public Estado buscarEstadoBD(EntityManager entityManager, Long id) {
        return this.unaControladoraEntidades.buscarEstadoBD(entityManager, id);
    }

    public void crearEstado(Socia unaSocia, Date fecha, TipoEstado unTipoEstado) {
        this.unaControladoraEntidades.crearEstado(unaSocia, fecha, unTipoEstado);
    }

    public void modificarEstado(Estado unEstado, Date fecha, TipoEstado unTipoEstado, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarEstado(unEstado, fecha, unTipoEstado, borradoLogico);
    }

    public void cambiarEstadoDeSocia(Estado unEstado, Socia unaSociaActual, Socia unaSociaNueva) {
        this.unaControladoraEntidades.cambiarEstadoDeSocia(unEstado, unaSociaActual, unaSociaNueva);
    }

    public void eliminarEstado(Estado unEstado) {
        this.unaControladoraEntidades.eliminarEstado(unEstado);
    }
//---------------------------------FIN ESTADOS----------------------------------

//---------------------------------TIPO ESTADO----------------------------------
    public void crearTipoEstado(double monto, Frecuencia unaFrecuencia, String nombre) {
        this.unaControladoraEntidades.crearTipoEstado(monto, unaFrecuencia, nombre);
    }

    public void modificarTipoEstado(TipoEstado unTipoEstado, double monto, Frecuencia unaFrecuencia, String nombre, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarTipoEstado(unTipoEstado, monto, unaFrecuencia, nombre, borradoLogico);
    }

    public void eliminarTipoEstado(TipoEstado unTipoEstado) {
        this.unaControladoraEntidades.eliminarTipoEstado(unTipoEstado);
    }
//-------------------------------FIN TIPO ESTADO--------------------------------
//
//------------------------------------------------------------------------------
//--------------------------FIN CONTROLADORA ENTIDADES--------------------------
//------------------------------------------------------------------------------
//
//------------------------------------------------------------------------------
//---------------------------CONTROLADORA DEPORTIVA-----------------------------
//------------------------------------------------------------------------------

//--------------------------------SANCIONES-------------------------------------
    public void crearSancionTribunal(Equipo unEquipo, Persona unaPersona, Date fecha, String motivo, String detalles) {
        this.unaControladoraDeportiva.crearSancionTribunal(unEquipo, unaPersona, fecha, motivo, detalles);
    }

    public void modificarSancionTribunal(SancionTribunal unaSancionTribunal, Date vencimiento, int cantFechas, Date fecha, String motivo, String detalles, Tarjeta unaTarjeta, Partido unPartido, int cantFechasCumplidas, String numeroResolucion, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarSancionTribunal(unaSancionTribunal, vencimiento, cantFechas, fecha, motivo, detalles, unaTarjeta, unPartido, cantFechasCumplidas, numeroResolucion, borradoLogico);
    }

    public void eliminarSancionTribunal(SancionTribunal unaSancionTribunal) {
        this.unaControladoraDeportiva.eliminarSancionTribunal(unaSancionTribunal);
    }
//------------------------------FIN SANCIONES-----------------------------------

//--------------------------------TARJETAS--------------------------------------
    public void crearTarjetaRoja(SancionTribunal unaSancionTribunal, Socia unaSocia, Partido unPartido, String motivo, String detalles) {
        this.unaControladoraDeportiva.crearTarjetaRoja(unaSancionTribunal, unaSocia, unPartido, motivo, detalles);
    }

    public void crearTarjeta(Socia unaSocia, Partido unPartido, String tipo, String motivo, String detalles) {
        this.unaControladoraDeportiva.crearTarjeta(unaSocia, unPartido, tipo, motivo, detalles);
    }

    public void modificarTarjeta(Tarjeta unaTarjeta, String tipo, String motivo, String detalles, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarTarjeta(unaTarjeta, tipo, motivo, detalles, borradoLogico);
    }

    public void eliminarTarjeta(Tarjeta unaTarjeta) {
        this.unaControladoraDeportiva.eliminarTarjeta(unaTarjeta);
    }
//------------------------------FIN TARJETAS------------------------------------

//------------------------------EQUIPOS-----------------------------------------   
    public Equipo buscarEquipoBD(Long id) {
        return this.unaControladoraDeportiva.buscarEquipoBD(id);
    }
    
    public List<Equipo> getEquipos() {
        return unaControladoraDeportiva.getEquipos();
    }

    public void crearEquipo(Club unClub, String nombre, Socia unaCapitana, Socia unaDelegada, CuerpoTecnico unDT) {
        this.unaControladoraDeportiva.crearEquipo(unClub, nombre, unaCapitana, unaDelegada, unDT);
    }

    public void modificarEquipo(Equipo unEquipo, String nombre, Socia unaCapitana, Socia unaCapitanaSuplente, Socia unaDelegada, Socia unaDelegadaSuplente, CuerpoTecnico unDT, CuerpoTecnico unPreparadorFisico, CuerpoTecnico unAyudanteCampo, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarEquipo(unEquipo, nombre, unaCapitana, unaCapitanaSuplente, unaDelegada, unaDelegadaSuplente, unDT, unPreparadorFisico, unAyudanteCampo, borradoLogico);
    }

    public void cambiarEquipoDeClub(Equipo unEquipo, Club unClubActual, Club unClubNuevo) {
        this.unaControladoraDeportiva.cambiarEquipoDeClub(unEquipo, unClubActual, unClubNuevo);
    }

    public void eliminarEquipo(EntityManager entityManager, Equipo unEquipo) {
        this.unaControladoraDeportiva.eliminarEquipo(entityManager, unEquipo);
    }
//------------------------------FIN EQUIPOS-------------------------------------

//--------------------------------INDUMENTARIAS---------------------------------
    public void crearIndumentaria(Equipo unEquipo, String camiseta, String media, String pollera) {
        this.unaControladoraDeportiva.crearIndumentaria(unEquipo, camiseta, media, pollera);
    }

    public void modificarIndumentaria(Indumentaria unaIndumentaria, String camiseta, String media, String pollera, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarIndumentaria(unaIndumentaria, camiseta, media, pollera, borradoLogico);
    }

    public void cambiarIndumentariaDeEquipo(Indumentaria unaIndumentaria, Equipo unEquipoActual, Equipo unEquipoNuevo) {
        this.unaControladoraDeportiva.cambiarIndumentariaDeEquipo(unaIndumentaria, unEquipoActual, unEquipoNuevo);
    }

    public void eliminarIndumentaria(Indumentaria unaIndumentaria) {
        this.unaControladoraDeportiva.eliminarIndumentaria(unaIndumentaria);
    }
//------------------------------FIN INDUMENTARIAS-------------------------------

//-------------------------------- CANCHAS -------------------------------------
    public void crearCancha(Club unClub, String nombre, boolean seOcupa, TipoCancha unTipoCancha) {
        this.unaControladoraDeportiva.crearCancha(unClub, nombre, seOcupa, unTipoCancha);
    }

    public void modificarCancha(Cancha unaCancha, String nombre, boolean seOcupa, TipoCancha unTipoCancha, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarCancha(unaCancha, nombre, seOcupa, unTipoCancha, borradoLogico);
    }

    public void cambiarCanchaDeClub(Cancha unaCancha, Club unClubActual, Club unClubNuevo) {
        this.unaControladoraDeportiva.cambiarCanchaDeClub(unaCancha, unClubActual, unClubNuevo);
    }

    public void eliminarCancha(Cancha unaCancha) {
        this.unaControladoraDeportiva.eliminarCancha(unaCancha);
    }
//--------------------------------FIN CANCHAS-----------------------------------

//--------------------------------TIPO CANCHA-----------------------------------
    public void crearTipoCancha(double monto, Frecuencia unaFrecuencia, String nombre) {
        this.unaControladoraDeportiva.crearTipoCancha(monto, unaFrecuencia, nombre);
    }

    public void modificarTipoCancha(TipoCancha unTipoCancha, double monto, Frecuencia unaFrecuencia, String nombre, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarCancha(null, nombre, borradoLogico, unTipoCancha, borradoLogico);
    }

    public void eliminarTipoCancha(TipoCancha unTipoCancha) {
        this.unaControladoraDeportiva.eliminarTipoCancha(unTipoCancha);
    }
//-----------------------------FIN TIPO CANCHA----------------------------------

//------------------------------CATEGORIAS--------------------------------------    
    public Categoria buscarCategoriaBD(Long id) {
        return this.unaControladoraDeportiva.buscarCategoriaBD(id);
    }

    public void crearCategoria(int cantMenores, String nombre) {
        this.unaControladoraDeportiva.crearCategoria(cantMenores, nombre);
    }

    public void modificarCategoria(Categoria unaCategoria, int cantMenores, String nombre) {
        this.unaControladoraDeportiva.modificarCategoria(unaCategoria, cantMenores, nombre);
    }

    public void eliminarCategoria(Categoria unaCategoria) {
        this.unaControladoraDeportiva.eliminarCategoria(unaCategoria);
    }
//------------------------------FIN CATEGORIAS----------------------------------

//------------------------------TORNEOS-----------------------------------------        
    public void crearTorneo(Date diaInicio, Categoria unaCategoria, String nombre) {
        this.unaControladoraDeportiva.crearTorneo(diaInicio, unaCategoria, nombre);
    }

    public void modificarTorneo(Torneo unTorneo, Date fechaInicio, Categoria unaCategoria, String nombre) {
        this.unaControladoraDeportiva.modificarTorneo(unTorneo, fechaInicio, unaCategoria, nombre);
    }

    public void eliminarTorneo(Torneo unTorneo) {
        this.unaControladoraDeportiva.eliminarTorneo(unTorneo);
    }
//------------------------------FIN TORNEOS-------------------------------------

//---------------------------------FECHAS TORNEO--------------------------------
    public FechaTorneo buscarFechaTorneoBd(EntityManager entityManager, Long id) {
        return this.unaControladoraDeportiva.buscarFechaTorneoBd(entityManager, id);
    }

    public void crearFechaTorneo(Torneo unTorneo, int numeroFecha) {
        this.unaControladoraDeportiva.crearFechaTorneo(unTorneo, numeroFecha);
    }

    public void modificarFechaTorneo(FechaTorneo unaFechaTorneo, int numeroFecha, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarFechaTorneo(unaFechaTorneo, numeroFecha, borradoLogico);
    }

    public void cambiarFechaTorneoDeTorneo(FechaTorneo unaFechaTorneo, Torneo unTorneoActual, Torneo unTorneoNuevo) {
        this.unaControladoraDeportiva.cambiarFechaTorneoDeTorneo(unaFechaTorneo, unTorneoActual, unTorneoNuevo);
    }

    public void eliminarFechaTorneo(FechaTorneo unaFechaTorneo) {
        this.unaControladoraDeportiva.eliminarFechaTorneo(unaFechaTorneo);
    }
//------------------------------FIN FECHAS TORNEO-------------------------------

//-----------------------------------PARTIDOS-----------------------------------
    public Partido buscarPartidoBD(EntityManager entityManager, Long id) {
        return this.unaControladoraDeportiva.buscarPartidoBD(entityManager, id);
    }

    public void crearPartido(FechaTorneo unaFechaTorneo, Equipo unEquipoVisitante, Date fecha, Arbitro unArbitro1, Arbitro unArbitro2, Cancha unaCancha, String observaciones, Equipo unEquipoLocal) {
        this.unaControladoraDeportiva.crearPartido(unaFechaTorneo, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unaCancha, observaciones, unEquipoLocal);
    }

    public void modificarPartido(Partido unPartido, Equipo unEquipoVisitante, Date fecha, Arbitro unArbitro1, Arbitro unArbitro2, Cancha unaCancha, String observaciones, Equipo unEquipoLocal, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarTorneo(null, fecha, null, observaciones);
    }

    public void cambiarPartidoDeFechaTorneo(Partido unPartido, FechaTorneo unaFechaTorneoActual, FechaTorneo unaFechaTorneoNueva) {
        this.unaControladoraDeportiva.cambiarPartidoDeFechaTorneo(unPartido, unaFechaTorneoActual, unaFechaTorneoNueva);
    }

    public void eliminarPartido(Partido unPartido) {
        this.unaControladoraDeportiva.eliminarPartido(unPartido);
    }
//---------------------------------FIN PARTIDOS---------------------------------

//---------------------------------GOLES----------------------------------------
    public void crearGol(Socia unaSocia, Partido unPartido, String tiempo, boolean autoGol) {
        this.unaControladoraDeportiva.crearGol(unaSocia, unPartido, tiempo, autoGol);
    }

    public void modificarGol(Gol unGol, String tiempo, boolean autoGol, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarGol(unGol, tiempo, autoGol, borradoLogico);
    }

    public void cambiarAutoraGol(Gol unGol, Socia unaAutoraActual, Socia unaAutoraNueva) {
        this.unaControladoraDeportiva.cambiarAutoraGol(unGol, unaAutoraActual, unaAutoraNueva);
    }

    public void cambiarPartidoGol(Gol unGol, Partido unPartidoActual, Partido unPartidoNuevo) {
        this.unaControladoraDeportiva.cambiarPartidoGol(unGol, unPartidoActual, unPartidoNuevo);
    }

    public void eliminarGol(Gol unGol) {
        this.unaControladoraDeportiva.eliminarGol(unGol);
    }
//-------------------------------FIN GOLES--------------------------------------
//
//------------------------------------------------------------------------------
//------------------------FIN CONTROLADORA DEPORTIVA----------------------------
//------------------------------------------------------------------------------
//
//------------------------------------------------------------------------------
//------------------------CONTROLADORA CONTABILIDAD-----------------------------
//------------------------------------------------------------------------------

//------------------------------CONCEPTO DEPORTIVOS-----------------------------
    public ConceptoDeportivo buscarConceptoDeportivoBD(Long id) {
        return this.unaControladoraContabilidad.buscarConceptoDeportivoBD(id);
    }

    public void crearConceptoDeportivo(double monto, String concepto) {
        this.unaControladoraContabilidad.crearConceptoDeportivo(monto, concepto);
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, Long id, double monto, String concepto, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoDeportivo(unConceptoDeportivo, id, monto, concepto, borradoLogico);
    }

    public void eliminarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        this.unaControladoraContabilidad.eliminarConceptoDeportivo(unConceptoDeportivo);
    }
//----------------------------- FIN CONCEPTODEPORTIVO --------------------------

//-----------------------------------DEUDAS-------------------------------------
    

    public void crearDeudaSocia(Socia unaSocia, Date fechaGeneracion, String concepto, String observacion,double montoTotal, int cantCuotas, Date Vencimiento) {
        this.unaControladoraContabilidad.crearDeudaSocia(unaSocia, fechaGeneracion, concepto, observacion, montoTotal, cantCuotas, Vencimiento);
    }
    
    //FALTA VERIFICAR ESTO, CUANDO SE CREA UNA DEUDA, ESTA GERERA LAS CUOTAS
    //por lo tanto hay que verificar que los cambios se realicen correctamente.
    //Se miro unicamente la creacion. LA BAJA; ELIMINACION; etc no esta bien!!
    public void crearDeudaEquipo(Equipo unEquipo, Date fechaGeneracion, String concepto, String observacion,double montoTotal, int cantCuotas, Date Vencimiento) {
        this.unaControladoraContabilidad.crearDeudaEquipo(unEquipo, fechaGeneracion, concepto, observacion, montoTotal, cantCuotas, Vencimiento);
    }
    
    public void modificarDeuda(Deuda unaDeuda, Date fechaGeneracion, String concepto, String observacion, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarDeuda(unaDeuda, fechaGeneracion, concepto, observacion, borradoLogico);
    }

    public void cambiarDeudaDeEquipo(Deuda unaDeuda, Equipo unEquipoActual, Equipo unEquipoNuevo) {
        this.unaControladoraContabilidad.cambiarDeudaDeEquipo(unaDeuda, unEquipoActual, unEquipoNuevo);
    }

    public void cambiarDeudaDeSocia(Deuda unaDeuda, Socia unaSociaActual, Socia unaSociaNueva) {
        this.unaControladoraContabilidad.cambiarDeudaDeSocia(unaDeuda, unaSociaActual, unaSociaNueva);
    }

    public void eliminarDeuda(Deuda unaDeuda) {
        this.unaControladoraContabilidad.eliminarDeuda(unaDeuda);
    }
//---------------------------------FIN DEUDAS-----------------------------------

//--------------------------------PAGO CUOTA------------------------------------
    public void crearPagoCuota(Cuota unaCuota, double monto, Date fechaPago, String observacion) {
        this.unaControladoraContabilidad.crearPagoCuota(unaCuota, monto, fechaPago, observacion);
    }

    public void modificarPagoCuota(PagoCuota unPagoCuota, double monto, Date fechaPago, String observacion, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarPagoCuota(unPagoCuota, monto, fechaPago, observacion, borradoLogico);
    }

    public void eliminarPagoCuota(PagoCuota unPagoCuota) {
        this.unaControladoraContabilidad.eliminarPagoCuota(unPagoCuota);
    }
//------------------------------FIN PAGO CUOTA----------------------------------

//----------------------------- CONCEPTOINGRESO --------------------------------
    public ConceptoIngreso buscarConceptoIngresoBD(Long id) {
        return this.unaControladoraContabilidad.buscarConceptoIngresoBD(id);
    }

    public void crearConceptoIngreso(String nombre, String detalle) {
        this.unaControladoraContabilidad.crearConceptoIngreso(nombre, detalle);
    }

    public void modificarConceptoIngreso(ConceptoIngreso unConceptoIngreso, String nombre, String detalle, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoIngreso(unConceptoIngreso, nombre, detalle, borradoLogico);
    }

    public void eliminarConceptoIngreso(ConceptoIngreso unConceptoIngreso) {
        this.unaControladoraContabilidad.eliminarConceptoIngreso(unConceptoIngreso);
    }
//----------------------------- FIN CONCEPTOINGRESO ----------------------------

//----------------------------- CONCEPTO EGRESO --------------------------------
    public ConceptoEgreso buscarConceptoEgresoBD(Long id) {
        return this.unaControladoraContabilidad.buscarConceptoEgresoBD(id);
    }

    public void crearConceptoEgreso(String nombre, String detalle) {
        this.unaControladoraContabilidad.crearConceptoEgreso(nombre, detalle);
    }

    public void modificarConceptoEgreso(ConceptoEgreso unConceptoEgreso, String nombre, String detalle, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoEgreso(unConceptoEgreso, nombre, detalle, borradoLogico);
    }

    public void eliminarConceptoEgreso(ConceptoEgreso unConceptoEgreso) {
        this.unaControladoraContabilidad.eliminarConceptoEgreso(unConceptoEgreso);
    }
//----------------------------- FIN CONCEPTO EGRESO ----------------------------

//----------------------------- INGRESOSOTRO -----------------------------------
    public IngresoOtro buscarIngresosOtroBD(Long id) {
        return this.unaControladoraContabilidad.buscarIngresosOtroBD(id);
    }

    public void crearIngresoOtro(Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle) {
        this.unaControladoraContabilidad.crearIngresoOtro(fecha, monto, unConceptoIngreso, detalle);
    }

    public void modificarIngresoOtro(IngresoOtro unIngresoOtro, Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarIngresoOtro(unIngresoOtro, fecha, monto, unConceptoIngreso, detalle, borradoLogico);
    }

    public void eliminarIngresoOtro(IngresoOtro unIngresoOtro) {
        this.unaControladoraContabilidad.eliminarIngresoOtro(unIngresoOtro);
    }
//----------------------------- FIN INGRESOSOTRO -------------------------------

//----------------------------- EGRESOS ----------------------------------------
    public Egreso buscarEgresoBD(Long id) {
        return this.unaControladoraContabilidad.buscarEgresoBD(id);
    }

    public void crearEgreso(Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion) {
        this.unaControladoraContabilidad.crearEgreso(fecha, monto, unConceptoEgreso, observacion);
    }

    public void modificarEgreso(Egreso unEgreso, Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarEgreso(unEgreso, fecha, monto, unConceptoEgreso, observacion, borradoLogico);
    }

    public void eliminarEgreso(Egreso unEgreso) {
        this.unaControladoraContabilidad.eliminarEgreso(unEgreso);
    }
//----------------------------- FIN EGRESOS ------------------------------------

//------------------------------FRECUENCIA--------------------------------------
    public void crearFrecuencia(String diaGeneracion, String diaVencimiento, Collection<Mes> meses) {
        this.unaControladoraContabilidad.crearFrecuencia(diaGeneracion, diaVencimiento, null);
    }

    public void modificarFrecuencia(Frecuencia unaFrecuencia, String diaGeneracion, String diaVencimiento, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarFrecuencia(unaFrecuencia, diaGeneracion, diaVencimiento, borradoLogico);
    }

    public void eliminarFrecuencia(Frecuencia unaFrecuencia) {
        this.unaControladoraContabilidad.eliminarFrecuencia(unaFrecuencia);
    }
//----------------------------FIN FRECUENCIA------------------------------------
//
//------------------------------------------------------------------------------
//----------------------FIN CONTROLADORA CONTABILIDAD---------------------------
//------------------------------------------------------------------------------
}
