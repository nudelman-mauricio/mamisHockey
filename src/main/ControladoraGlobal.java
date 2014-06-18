package main;

import java.util.Collection;
import java.util.Date;
import java.util.List;
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

//------------------------------------------------------------------------------
//--------------------------CONTROLADORA ENTIDADES------------------------------
//------------------------------------------------------------------------------
//
//----------------------------------MESES---------------------------------------
    public Mes getMesDB(int numeroMes) {
        return this.unaControladoraEntidades.getMesDB(numeroMes);
    }
//--------------------------------FIN MESES-------------------------------------

//----------------------------PERSONA AUXILIAR----------------------------------
    public void crearPersonaAuxiliar(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico, boolean cuerpoTecnicoActivo) {
        this.unaControladoraEntidades.crearPersonaAuxiliar(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular, arbitro, cuerpoTecnico, cuerpoTecnicoActivo);
    }

    public void modificarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, String fotocopiaDni, boolean arbitro, boolean cuerpoTecnico, boolean cuerpoTecnicoActivo, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarPersonaAuxiliar(unaPersonaAuxiliar, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, fotocopiaDni, arbitro, cuerpoTecnico, cuerpoTecnicoActivo, borradoLogico);
    }

    public void eliminarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar) {
        this.unaControladoraEntidades.eliminarPersonaAuxiliar(unaPersonaAuxiliar);
    }

    public PersonaAuxiliar getCuerpoTecnicoBD(Long dni) {
        return this.unaControladoraEntidades.getCuerpoTecnicoBD(dni);
    }

    public PersonaAuxiliar getArbitroBD(Long dni) {
        return this.unaControladoraEntidades.getArbitroBD(dni);
    }

    public List<PersonaAuxiliar> getCuerposTecnicosBD() {
        return this.unaControladoraEntidades.getCuerposTecnicosBD();
    }

    public List<PersonaAuxiliar> getCuerposTecnicosDesocupadosBD() {
        return this.unaControladoraEntidades.getCuerposTecnicosDesocupadosBD();
    }

    public List<PersonaAuxiliar> getArbitrosBD() {
        return this.unaControladoraEntidades.getArbitrosBD();
    }

    public List<PersonaAuxiliar> getCuerposTecnicosBDFiltro(String dato) {
        return this.unaControladoraEntidades.getCuerposTecnicosBDFiltro(dato);
    }

    public List<PersonaAuxiliar> getCuerposTecnicosDesocupadosBDFiltro(String dato) {
        return this.unaControladoraEntidades.getCuerposTecnicosDesocupadosBDFiltro(dato);
    }

    public List<PersonaAuxiliar> getArbitrosBDFiltro(String dato) {
        return this.unaControladoraEntidades.getArbitrosBDFiltro(dato);
    }

    public List<PersonaAuxiliar> getPersonaAuxiliarBDFiltro(String dato) {
        return this.unaControladoraEntidades.getPersonasAuxiliarBDFiltro(dato);
    }
//--------------------------FIN PERSONAS AUXILIARES-----------------------------

//------------------------------SOCIAS------------------------------------------   
    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora, String email, String telFijo, String telCelular) {
        this.unaControladoraEntidades.crearSocia(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora, email, telFijo, telCelular);
    }

    public void modificarSocia(Socia unaSocia, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotoCarnet, boolean exJugadora) {
        this.unaControladoraEntidades.modificarSocia(unaSocia, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, borradoLogico, fotoCarnet, exJugadora);
    }

    public void eliminarSocia(Socia unaSocia) {
        this.unaControladoraEntidades.eliminarSocia(unaSocia);
    }

    public Socia getSociaBD(Long dni) {
        return this.unaControladoraEntidades.getSociaBD(dni);
    }

    public List<Socia> getSociasBD() {
        return this.unaControladoraEntidades.getSociasBD();
    }

    public List<Socia> getSociasBDFiltro(String dato) {
        return this.unaControladoraEntidades.getSociasBDFiltro(dato);
    }
//------------------------------FIN SOCIAS--------------------------------------

//-----------------------------------PASES--------------------------------------
    public void crearPase(Socia unaSocia, Date fechaGeneracion, double montoTotal, int cantCuotas, Date primerVencimiento, Equipo unEquipoNuevo, boolean libreDeudaClub, boolean solicitudPase, String observacionPase) {
        //Si es el primer pase a su primer equipo, no se genera la deuda ya que tiene monto CERO
        Deuda unaDeuda = null;
        if (unaSocia.getEquipoActual() != null) {
            String observacionDeuda = "De Equipo: " + unaSocia.getEquipoActual().getNombre() + " a Equipo: " + unEquipoNuevo.getNombre();
            String conceptoDeuda = "Por Pase";
            unaDeuda = this.unaControladoraContabilidad.crearDeudaSocia(unaSocia, fechaGeneracion, conceptoDeuda, observacionDeuda, montoTotal, cantCuotas, primerVencimiento);
        }
        this.unaControladoraEntidades.crearPase(unaSocia, fechaGeneracion, unaSocia.getEquipoActual(), unEquipoNuevo, unaDeuda, libreDeudaClub, solicitudPase, observacionPase);
    }

    public void eliminarUltimoPase(Pase ultimoPase, Socia unaSocia) {
        if (ultimoPase.getUnaDeuda() != null) {//Se debe comprobar porque el pase CERO no posee deuda
            this.unaControladoraContabilidad.eliminarDeuda(ultimoPase.getUnaDeuda());
        }
        this.unaControladoraEntidades.eliminarUltimoPase(ultimoPase, unaSocia);
    }

    public Pase getPaseBD(Long id) {
        return this.unaControladoraEntidades.getPaseBD(id);
    }

    //Se va ultilizar para la parte Contable
    public List<Pase> getPasesBD() {
        return this.unaControladoraEntidades.getPaseBD();
    }
//---------------------------------FIN PASES------------------------------------

//------------------------------LOCALIDADES-------------------------------------   
    public void crearLocalidad(String nombre, String codPostal) {
        this.unaControladoraEntidades.crearLocalidad(nombre, codPostal);
    }

    public void modificarLocalidad(Localidad unaLocalidad, String nombre, String codPostal, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarLocalidad(unaLocalidad, nombre, codPostal, borradoLogico);
    }

    public void eliminarLocalidad(Localidad unaLocalidad) {
        this.unaControladoraEntidades.eliminarLocalidad(unaLocalidad);
    }

    public Localidad getLocalidadBD(Long id) {
        return this.unaControladoraEntidades.getLocalidadBD(id);
    }

    public List<Localidad> getLocalidadesBD() {
        return unaControladoraEntidades.getLocalidadesBD();
    }
//------------------------------FIN LOCALIDADES---------------------------------

//-----------------------------------ERGOMETRIA---------------------------------
    public void crearErgometria(Socia unaSocia, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        this.unaControladoraEntidades.crearErgometria(unaSocia, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
    }

    public void modificarErgometria(Ergometria unaErgometria, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        this.unaControladoraEntidades.modificarErgometria(unaErgometria, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
    }

    //Me Parese que esta no tiene NINGUN sentido
    public void cambiarErgometriaDeSocia(Ergometria unaErgometria, Socia unaSociaActual, Socia unaSociaNueva) {
        this.unaControladoraEntidades.cambiarErgometriaDeSocia(unaErgometria, unaSociaActual, unaSociaNueva);
    }

    public void eliminarErgometria(Ergometria unaErgometria) {
        this.unaControladoraEntidades.eliminarErgometria(unaErgometria);
    }

    public Ergometria getErgometriaBD(Long id) {
        return this.unaControladoraEntidades.getErgometriaBD(id);
    }
//---------------------------------FIN ERGOMETRIAS---------------------------------

//-----------------------------------ESTADOS-------------------------------------
    public void crearEstado(Socia unaSocia, Date fecha, TipoEstado unTipoEstado) {
        this.unaControladoraEntidades.crearEstado(unaSocia, fecha, unTipoEstado);
    }

    public void modificarEstado(Estado unEstado, Date fecha, TipoEstado unTipoEstado, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarEstado(unEstado, fecha, unTipoEstado, borradoLogico);
    }

    //Me Parese que esta no tiene NINGUN sentido
    public void cambiarEstadoDeSocia(Estado unEstado, Socia unaSociaActual, Socia unaSociaNueva) {
        this.unaControladoraEntidades.cambiarEstadoDeSocia(unEstado, unaSociaActual, unaSociaNueva);
    }

    public void eliminarEstado(Estado unEstado) {
        this.unaControladoraEntidades.eliminarEstado(unEstado);
    }

    //public Estado getEstadoBD(Long id) {
    public Estado getEstadoBD(Long id) {
        return this.unaControladoraEntidades.getEstadoBD(id);
    }

    //Listado de todos los cambios de Estados - Este me parece que no seria tan generico.
    //Se podria utilizar para alguna Consulta. Onda, todas las socias que cambiaron de estado entre fechas
    public List<Estado> getEstadosDB() {
        return this.unaControladoraEntidades.getEstadosDB();
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

    public TipoEstado getTipoEstadoBD(Long id) {
        return this.unaControladoraEntidades.getTipoEstadoBD(id);
    }

    public List<TipoEstado> getTiposEstadosBD() {
        return unaControladoraEntidades.getTiposEstadosBD();
    }
//-------------------------------FIN TIPO ESTADO--------------------------------

//------------------------------------------------------------------------------
//--------------------------FIN CONTROLADORA ENTIDADES--------------------------
//------------------------------------------------------------------------------
//
//    
//    
//    
//------------------------------------------------------------------------------
//---------------------------CONTROLADORA DEPORTIVA-----------------------------
//------------------------------------------------------------------------------
//
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

    public SancionTribunal getSancionTribunalBD(Long id) {
        return this.unaControladoraDeportiva.getSancionTribunalBD(id);
    }

    public List<SancionTribunal> getSancionesTribunalesBD() {
        return this.unaControladoraDeportiva.getSancionesTribunalesBD();
    }

//------------------------------FIN SANCIONES-----------------------------------
//
//--------------------------------TARJETAS--------------------------------------
    public void crearTarjeta(Socia unaSocia, Partido unPartido, String tipo, String motivo, String minuto, String detalles) {
        this.unaControladoraDeportiva.crearTarjeta(unaSocia, unPartido, tipo, motivo, minuto, detalles);
    }

    public void crearTarjetaRoja(SancionTribunal unaSancionTribunal, Socia unaSocia, Partido unPartido, String motivo, String minuto, String detalles) {
        this.unaControladoraDeportiva.crearTarjetaRoja(unaSancionTribunal, unaSocia, unPartido, motivo, minuto, detalles);
    }

    public void modificarTarjeta(Tarjeta unaTarjeta, String tipo, String motivo, String minuto, String detalles, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarTarjeta(unaTarjeta, tipo, motivo, minuto, detalles, borradoLogico);
    }

    public void eliminarTarjeta(Tarjeta unaTarjeta) {
        this.unaControladoraDeportiva.eliminarTarjeta(unaTarjeta);
    }

    public Tarjeta getTarjetaBD(Long id) {
        return this.unaControladoraDeportiva.getTarjetaBD(id);
    }

    public List<Tarjeta> getTarjetasBD() {
        return this.unaControladoraDeportiva.getTarjetasBD();
    }
//------------------------------FIN TARJETAS------------------------------------

//------------------------------EQUIPOS-----------------------------------------   
    public Equipo crearEquipo(Club unClub, String nombre, PersonaAuxiliar unDT) {
        return this.unaControladoraDeportiva.crearEquipo(unClub, nombre, unDT);
    }

    public void modificarEquipo(Equipo unEquipo, String nombre, Socia unaCapitana, Socia unaCapitanaSuplente, Socia unaDelegada, Socia unaDelegadaSuplente, PersonaAuxiliar unDT, PersonaAuxiliar unPreparadorFisico, PersonaAuxiliar unAyudanteCampo, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarEquipo(unEquipo, nombre, unaCapitana, unaCapitanaSuplente, unaDelegada, unaDelegadaSuplente, unDT, unPreparadorFisico, unAyudanteCampo, borradoLogico);
    }

    public void cambiarEquipoDeClub(Equipo unEquipo, Club unClubActual, Club unClubNuevo) {
        this.unaControladoraDeportiva.cambiarEquipoDeClub(unEquipo, unClubActual, unClubNuevo);
    }

    public void eliminarEquipo(Equipo unEquipo) {
        this.unaControladoraDeportiva.eliminarEquipo(unEquipo);
    }

    public Equipo getEquipoBD(Long id) {
        return this.unaControladoraDeportiva.getEquipoBD(id);
    }

    public List<Equipo> getEquiposBD() {
        return unaControladoraDeportiva.getEquiposBD();
    }

    public List<Equipo> getEquiposBDFiltro(String dato) {
        return this.unaControladoraDeportiva.getEquiposBDFiltro(dato);
    }
//------------------------------FIN EQUIPOS-------------------------------------

//------------------------------CLUBES------------------------------------------   
    public void crearClub(String nombre, String nombrePresidente, Localidad unaLocalidad) {
        this.unaControladoraDeportiva.crearClub(nombre, nombrePresidente, unaLocalidad);
    }

    public void modificarClub(Club unClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarClub(unClub, nombre, logo, nombrePresidente, unaLocalidad, borradoLogico);
    }

    public void eliminarClub(Club unClub) {
        this.unaControladoraDeportiva.eliminarClub(unClub);
    }

    public Club getClubBD(Long id) {
        return this.unaControladoraDeportiva.getClubBD(id);
    }

    public List<Club> getClubesBD() {
        return this.unaControladoraDeportiva.getClubesBD();
    }

    public List<Club> getClubesBDFiltro(String dato) {
        return this.unaControladoraDeportiva.getClubesBDFiltro(dato);
    }
//------------------------------FIN CLUBES--------------------------------------

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

    public Indumentaria getIndumentariaBD(Long id) {
        return this.unaControladoraDeportiva.getIndumentariaBD(id);
    }

    public List<Indumentaria> getIndumentariasBD() {
        return this.unaControladoraDeportiva.getIndumentariasBD();
    }
//------------------------------FIN INDUMENTARIAS-------------------------------

//-------------------------------- CANCHAS -------------------------------------
    public void crearCancha(Club unClub, String nombre, boolean seOcupa, TipoCancha unTipoCancha) {
        this.unaControladoraDeportiva.crearCancha(unClub, nombre, seOcupa, unTipoCancha);
    }

    public void modificarCancha(Cancha unaCancha, String nombre, boolean seOcupa, TipoCancha unTipoCancha, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarCancha(unaCancha, nombre, seOcupa, unTipoCancha, borradoLogico);
    }

    //Esta Interesante, la pregunta seria si se va utilizar... ¿Va ser factible desde la parte grafica?
    public void cambiarCanchaDeClub(Cancha unaCancha, Club unClubActual, Club unClubNuevo) {
        this.unaControladoraDeportiva.cambiarCanchaDeClub(unaCancha, unClubActual, unClubNuevo);
    }

    public void eliminarCancha(Cancha unaCancha) {
        this.unaControladoraDeportiva.eliminarCancha(unaCancha);
    }

    public Cancha getCanchaBD(Long id) {
        return this.unaControladoraDeportiva.getCanchaBD(id);
    }

    public List<Cancha> getCanchasBD() {
        return this.unaControladoraDeportiva.getCanchasBD();
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

    public TipoCancha getTipoCanchaBD(Long id) {
        return this.unaControladoraDeportiva.getTipoCanchaBD(id);
    }

    public List<TipoCancha> getTiposCanchasBD() {
        return this.unaControladoraDeportiva.getTiposCanchasBD();
    }
//-----------------------------FIN TIPO CANCHA----------------------------------

//------------------------------CATEGORIAS--------------------------------------
    public void crearCategoria(int cantMenores, String nombre) {
        this.unaControladoraDeportiva.crearCategoria(cantMenores, nombre);
    }

    public void modificarCategoria(Categoria unaCategoria, int cantMenores, String nombre, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarCategoria(unaCategoria, cantMenores, nombre);
    }

    public void eliminarCategoria(Categoria unaCategoria) {
        this.unaControladoraDeportiva.eliminarCategoria(unaCategoria);
    }

    public Categoria buscarCategoriaBD(Long id) {
        return this.unaControladoraDeportiva.getCategoriaBD(id);
    }

    public List<Categoria> getCategoriasBD() {
        return this.unaControladoraDeportiva.getCategoriasBD();
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

    public Torneo getTorneoBD(Long idTorneo) {
        return this.unaControladoraDeportiva.getTorneoBD(idTorneo);
    }

    public List<Torneo> getTorneosBD() {
        return this.unaControladoraDeportiva.getTorneosBD();
    }

    public List<Torneo> getTorneosBDFiltro(String dato) {
        return this.unaControladoraDeportiva.getTorneosBDFiltro(dato);
    }
//------------------------------FIN TORNEOS-------------------------------------

//------------------------------FECHAS TORNEO-----------------------------------
    public void crearFechaTorneo(Torneo unTorneo, int numeroFecha) {
        this.unaControladoraDeportiva.crearFechaTorneo(unTorneo, numeroFecha);
    }

    public void modificarFechaTorneo(FechaTorneo unaFechaTorneo, int numeroFecha, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarFechaTorneo(unaFechaTorneo, numeroFecha, borradoLogico);
    }

    //ME PARECE QUE NO TIENE NINGUN SENTIDO - NI SIQUIERA VIABLE DE LA PARTE GRAFICA
    public void cambiarFechaTorneoDeTorneo(FechaTorneo unaFechaTorneo, Torneo unTorneoActual, Torneo unTorneoNuevo) {
        this.unaControladoraDeportiva.cambiarFechaTorneoDeTorneo(unaFechaTorneo, unTorneoActual, unTorneoNuevo);
    }

    public void eliminarFechaTorneo(FechaTorneo unaFechaTorneo) {
        this.unaControladoraDeportiva.eliminarFechaTorneo(unaFechaTorneo);
    }

    //public FechaTorneo getFechaTorneoBD(Long id) {
    public FechaTorneo getFechaTorneoBD(EntityManager entityManager, Long id) {
        return this.unaControladoraDeportiva.getFechaTorneoBD(id);
    }

    public List<FechaTorneo> getFechasTorneosBD() {
        return this.unaControladoraDeportiva.getFechasTorneosBD();
    }
//----------------------------FIN FECHAS TORNEO---------------------------------

//---------------------------------PARTIDOS-------------------------------------
    public void crearPartido(FechaTorneo unaFechaTorneo, Equipo unEquipoVisitante, Date fecha, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, Cancha unaCancha, String observaciones, Equipo unEquipoLocal) {
        this.unaControladoraDeportiva.crearPartido(unaFechaTorneo, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unArbitro3, unaCancha, observaciones, unEquipoLocal);
    }

    public void modificarPartido(Partido unPartido, Equipo unEquipoVisitante, Date fecha, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, String nombreVeedor, String nombreAyudanteMesaLocal, String nombreAyudanteMesaVisitante, Cancha unaCancha, String observaciones, Equipo unEquipoLocal, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarPartido(unPartido, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unArbitro3, nombreVeedor, nombreAyudanteMesaLocal, nombreAyudanteMesaVisitante, unaCancha, observaciones, unEquipoLocal, borradoLogico);
    }

    //ME PARECE QUE NO TIENE NINGUN SENTIDO - NI SIQUIERA VIABLE DE LA PARTE GRAFICA
    public void cambiarPartidoDeFechaTorneo(Partido unPartido, FechaTorneo unaFechaTorneoActual, FechaTorneo unaFechaTorneoNueva) {
        this.unaControladoraDeportiva.cambiarPartidoDeFechaTorneo(unPartido, unaFechaTorneoActual, unaFechaTorneoNueva);
    }

    public void eliminarPartido(Partido unPartido) {
        this.unaControladoraDeportiva.eliminarPartido(unPartido);
    }

    public Partido getPartidoBD(EntityManager entityManager, Long id) {
        return this.unaControladoraDeportiva.getPartidoBD(id);
    }

    public List<Partido> getPartidosBD() {
        return this.unaControladoraDeportiva.getPartidosBD();
    }
//---------------------------------FIN PARTIDOS---------------------------------

//---------------------------------GOLES----------------------------------------
    public void crearGol(Socia unaSocia, Partido unPartido, String minuto) {
        this.unaControladoraDeportiva.crearGol(unaSocia, unPartido, minuto);
    }

    public void modificarGol(Gol unGol, String minuto, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarGol(unGol, minuto, borradoLogico);
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

    public Gol getGolBD(Long id) {
        return this.unaControladoraDeportiva.getGolBD(id);
    }

    public List<Gol> getGolesBD() {
        return this.unaControladoraDeportiva.getGolesBD();
    }
//-------------------------------FIN GOLES--------------------------------------

//------------------------------------------------------------------------------
//------------------------FIN CONTROLADORA DEPORTIVA----------------------------
//------------------------------------------------------------------------------
//
//
//
//------------------------------------------------------------------------------
//------------------------CONTROLADORA CONTABILIDAD-----------------------------
//------------------------------------------------------------------------------
//
//------------------------------CONCEPTO DEPORTIVOS-----------------------------
    public void crearConceptoDeportivo(double monto, String concepto) {
        this.unaControladoraContabilidad.crearConceptoDeportivo(monto, concepto);
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, Long id, double monto, String concepto, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoDeportivo(unConceptoDeportivo, id, monto, concepto, borradoLogico);
    }

    public void eliminarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        this.unaControladoraContabilidad.eliminarConceptoDeportivo(unConceptoDeportivo);
    }

    public ConceptoDeportivo getConceptoDeportivoBD(Long id) {
        return this.unaControladoraContabilidad.getConceptoDeportivoBD(id);
    }

    public List<ConceptoDeportivo> getConceptosDeportivosBD() {
        return this.unaControladoraContabilidad.getConceptosDeportivosBD();
    }
//----------------------------- FIN CONCEPTODEPORTIVO --------------------------

//-----------------------------------DEUDAS-------------------------------------
    public void crearDeudaSocia(Socia unaSocia, Date fechaGeneracion, String concepto, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        this.unaControladoraContabilidad.crearDeudaSocia(unaSocia, fechaGeneracion, concepto, observacion, montoTotal, cantCuotas, primerVencimiento);
    }

    public void crearDeudaEquipo(Equipo unEquipo, Date fechaGeneracion, String concepto, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        this.unaControladoraContabilidad.crearDeudaEquipo(unEquipo, fechaGeneracion, concepto, observacion, montoTotal, cantCuotas, primerVencimiento);
    }

    //TIENE SENTIDO?
    //public void modificarDeudaDeEquipo(Deuda unaDeuda, Equipo unEquipoActual, Equipo unEquipoNuevo) {
    public void cambiarDeudaDeEquipo(Deuda unaDeuda, Equipo unEquipoActual, Equipo unEquipoNuevo) {
        this.unaControladoraContabilidad.cambiarDeudaDeEquipo(unaDeuda, unEquipoActual, unEquipoNuevo);
    }

    //TIENE SENTIDO?
    //public void modificarDeudaDeSocia(Deuda unaDeuda, Socia unaSociaActual, Socia unaSociaNueva) {
    public void cambiarDeudaDeSocia(Deuda unaDeuda, Socia unaSociaActual, Socia unaSociaNueva) {
        this.unaControladoraContabilidad.cambiarDeudaDeSocia(unaDeuda, unaSociaActual, unaSociaNueva);
    }

    public void eliminarDeuda(Deuda unaDeuda) {
        this.unaControladoraContabilidad.eliminarDeuda(unaDeuda);
    }

    public Deuda getDeudaBD(Long id) {
        return this.unaControladoraContabilidad.getDeudaBD(id);
    }

    public List<Deuda> getDeudasBD() {
        return this.unaControladoraContabilidad.getDeudaBD();
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

    public PagoCuota getPagoCuotaBD(Long id) {
        return this.unaControladoraContabilidad.getPagoCuotaBD(id);
    }

    public List<PagoCuota> getPagosCuotasBD() {
        return this.unaControladoraContabilidad.getPagosCuotasBD();
    }
//------------------------------FIN PAGO CUOTA----------------------------------

//----------------------------- CONCEPTOINGRESO --------------------------------
    public void crearConceptoIngreso(String nombre, String detalle) {
        this.unaControladoraContabilidad.crearConceptoIngreso(nombre, detalle);
    }

    public void modificarConceptoIngreso(ConceptoIngreso unConceptoIngreso, String nombre, String detalle, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoIngreso(unConceptoIngreso, nombre, detalle, borradoLogico);
    }

    public void eliminarConceptoIngreso(ConceptoIngreso unConceptoIngreso) {
        this.unaControladoraContabilidad.eliminarConceptoIngreso(unConceptoIngreso);
    }

    public ConceptoIngreso getConceptoIngresoBD(Long id) {
        return this.unaControladoraContabilidad.getConceptoIngresoBD(id);
    }

    public List<ConceptoIngreso> getConceptosIngresosBD() {
        return this.unaControladoraContabilidad.getConceptosIngresosBD();
    }
//----------------------------- FIN CONCEPTOINGRESO ----------------------------

//----------------------------- CONCEPTO EGRESO --------------------------------
    public void crearConceptoEgreso(String nombre, String detalle) {
        this.unaControladoraContabilidad.crearConceptoEgreso(nombre, detalle);
    }

    public void modificarConceptoEgreso(ConceptoEgreso unConceptoEgreso, String nombre, String detalle, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoEgreso(unConceptoEgreso, nombre, detalle, borradoLogico);
    }

    public void eliminarConceptoEgreso(ConceptoEgreso unConceptoEgreso) {
        this.unaControladoraContabilidad.eliminarConceptoEgreso(unConceptoEgreso);
    }

    public ConceptoEgreso getConceptoEgresoBD(Long id) {
        return this.unaControladoraContabilidad.getConceptoEgresoBD(id);
    }

    public List<ConceptoEgreso> getConceptosEgresosBD() {
        return this.unaControladoraContabilidad.getConceptosEgresosBD();
    }
//----------------------------- FIN CONCEPTO EGRESO ----------------------------

//----------------------------- INGRESOSOTRO -----------------------------------
    public void crearIngresoOtro(Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle) {
        this.unaControladoraContabilidad.crearIngresoOtro(fecha, monto, unConceptoIngreso, detalle);
    }

    public void modificarIngresoOtro(IngresoOtro unIngresoOtro, Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarIngresoOtro(unIngresoOtro, fecha, monto, unConceptoIngreso, detalle, borradoLogico);
    }

    public void eliminarIngresoOtro(IngresoOtro unIngresoOtro) {
        this.unaControladoraContabilidad.eliminarIngresoOtro(unIngresoOtro);
    }

    public IngresoOtro getIngresoOtroBD(Long id) {
        return this.unaControladoraContabilidad.getIngresoOtroBD(id);
    }

    public List<IngresoOtro> getIngresosOtrosBD() {
        return this.unaControladoraContabilidad.getIngresosOtrosBD();
    }
//----------------------------- FIN INGRESOSOTRO -------------------------------

//----------------------------- EGRESOS ----------------------------------------
    public void crearEgreso(Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion) {
        this.unaControladoraContabilidad.crearEgreso(fecha, monto, unConceptoEgreso, observacion);
    }

    public void modificarEgreso(Egreso unEgreso, Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarEgreso(unEgreso, fecha, monto, unConceptoEgreso, observacion, borradoLogico);
    }

    public void eliminarEgreso(Egreso unEgreso) {
        this.unaControladoraContabilidad.eliminarEgreso(unEgreso);
    }

    public Egreso getEgresoBD(Long id) {
        return this.unaControladoraContabilidad.getEgresoBD(id);
    }

    public List<Egreso> getEgresosBD() {
        return this.unaControladoraContabilidad.getEgresosBD();
    }
//----------------------------- FIN EGRESOS ------------------------------------

//------------------------------FRECUENCIA--------------------------------------
    public Frecuencia crearFrecuencia(String diaGeneracion, String diaVencimiento, Collection<Mes> meses) {
        return this.unaControladoraContabilidad.crearFrecuencia(diaGeneracion, diaVencimiento, null);
    }

    public void modificarFrecuencia(Frecuencia unaFrecuencia, String diaGeneracion, String diaVencimiento, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarFrecuencia(unaFrecuencia, diaGeneracion, diaVencimiento, borradoLogico);
    }

    public void eliminarFrecuencia(Frecuencia unaFrecuencia) {
        this.unaControladoraContabilidad.eliminarFrecuencia(unaFrecuencia);
    }

    public Frecuencia getFrecuenciaBD(Long id) {
        return this.unaControladoraContabilidad.getFrecuenciaBD(id);
    }

    public List<Frecuencia> getFrecuenciasBD() {
        return this.unaControladoraContabilidad.getFrecuenciasBD();
    }
//----------------------------FIN FRECUENCIA------------------------------------

//------------------------------------------------------------------------------
//----------------------FIN CONTROLADORA CONTABILIDAD---------------------------
//------------------------------------------------------------------------------
}
