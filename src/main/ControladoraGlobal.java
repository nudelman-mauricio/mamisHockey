package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import logicaNegocios.*;
import net.sf.jasperreports.engine.JasperPrint;

public class ControladoraGlobal {

    private final ControladoraContabilidad unaControladoraContabilidad;
    private final ControladoraEntidades unaControladoraEntidades;
    private final ControladoraDeportiva unaControladoraDeportiva;

    public ControladoraGlobal(EntityManager entityManager) {
        this.unaControladoraContabilidad = new ControladoraContabilidad(entityManager);
        this.unaControladoraEntidades = new ControladoraEntidades(entityManager);
        this.unaControladoraDeportiva = new ControladoraDeportiva(entityManager);
    }

// <editor-fold defaultstate="collapsed" desc="Controladora Entidades">
    // <editor-fold defaultstate="collapsed" desc="Persona Auxiliar">
    public void crearPersonaAuxiliar(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico) {
        this.unaControladoraEntidades.crearPersonaAuxiliar(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular, arbitro, cuerpoTecnico);
    }

    public void modificarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, String fotocopiaDni, boolean arbitro, boolean cuerpoTecnico, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarPersonaAuxiliar(unaPersonaAuxiliar, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, fotocopiaDni, arbitro, cuerpoTecnico, borradoLogico);
    }

    public void marcarCuerpoTecnicoActivo(PersonaAuxiliar unaPersonaAuxiliar, boolean activo) {
        this.unaControladoraEntidades.marcarCuerpoTecnicoActivo(unaPersonaAuxiliar, activo);
    }

    public void eliminarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar) {
        this.unaControladoraEntidades.eliminarPersonaAuxiliar(unaPersonaAuxiliar);
    }

    public PersonaAuxiliar getPersonaAuxiliarBD(Long dni) {
        return this.unaControladoraEntidades.getPersonaAuxiliarBD(dni);
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

    public List<PersonaAuxiliar> getArbitrosPorFecha(FechaTorneo unaFecha) {
        return this.unaControladoraEntidades.getArbitrosPorFecha(unaFecha);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Socias">
    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora, String email, String telFijo, String telCelular) {
        Socia unSocia = this.unaControladoraEntidades.crearSocia(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora, email, telFijo, telCelular);
        TipoEstado unTipoEstadoSocia = this.getTipoEstadoBD("Socia");
        if (unTipoEstadoSocia == null) {
            unTipoEstadoSocia = this.crearTipoEstado("Socia");
        }
        this.crearEstado(unSocia, fechaIngreso, unTipoEstadoSocia);
    }

    public void modificarSocia(Socia unaSocia, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotoCarnet, boolean exJugadora) {
        this.unaControladoraEntidades.modificarSocia(unaSocia, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, borradoLogico, fotoCarnet, exJugadora);
    }

    public void modificarNumeroCamiseta(Socia unaSocia, String numeroCamiseta) {
        this.unaControladoraEntidades.modificarNumeroCamiseta(unaSocia, numeroCamiseta);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Pases">
    public void crearPase(Socia unaSocia, Date fechaGeneracion, double montoTotal, int cantCuotas, Date primerVencimiento, Equipo unEquipoNuevo, boolean libreDeudaClub, boolean solicitudPase, String observacionPase) {
        String observacionDeuda;
        if (unaSocia.getEquipoActual() == null) {
            observacionDeuda = "Pase de Equipo: Sin Equipo a Equipo : " + unEquipoNuevo.getNombre();
        } else {
            observacionDeuda = "Pase de Equipo: " + unaSocia.getEquipoActual().getNombre() + " a Equipo: " + unEquipoNuevo.getNombre();
        }

        ConceptoDeportivo porPase = null;
        for (ConceptoDeportivo aux : this.getConceptosDeportivosBD()) {
            if ((!aux.isBorradoLogico()) && (aux.getConcepto().compareToIgnoreCase("Por Pase") == 0)) {
                porPase = aux;
            }
        }
        if (porPase == null) {
            porPase = this.crearConceptoDeportivo(0.0, "Por Pase", null, null, null);
        }

        Deuda unaDeuda = this.unaControladoraContabilidad.crearDeudaSocia(unaSocia, fechaGeneracion, porPase, observacionDeuda, montoTotal, cantCuotas, primerVencimiento);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Localidad">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ergometria">
    public void crearErgometria(Socia unaSocia, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        this.unaControladoraEntidades.crearErgometria(unaSocia, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
    }

    public void modificarErgometria(Ergometria unaErgometria, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarErgometria(unaErgometria, fechaCaducidad, fechaRealizacion, aprobado, comentarios, borradoLogico);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Estados">
    public void crearEstado(Socia unaSocia, Date fecha, TipoEstado unTipoEstado) {
        this.unaControladoraEntidades.crearEstado(unaSocia, fecha, unTipoEstado);
    }

    public void modificarEstado(Estado unEstado, Date fecha, TipoEstado unTipoEstado, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarEstado(unEstado, fecha, unTipoEstado, borradoLogico);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tipo Estados">
    public TipoEstado crearTipoEstado(String nombre) {
        return this.unaControladoraEntidades.crearTipoEstado(nombre);
    }

    public void modificarTipoEstado(TipoEstado unTipoEstado, String nombre, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarTipoEstado(unTipoEstado, nombre, borradoLogico);
    }

    public void eliminarTipoEstado(TipoEstado unTipoEstado) {
        this.unaControladoraEntidades.eliminarTipoEstado(unTipoEstado);
    }

    public TipoEstado getTipoEstadoBD(String tipo) {
        return this.unaControladoraEntidades.getTipoEstadoBD(tipo);
    }

    public TipoEstado getTipoEstadoBD(Long id) {
        return this.unaControladoraEntidades.getTipoEstadoBD(id);
    }

    public List<TipoEstado> getTiposEstadosBD() {
        return unaControladoraEntidades.getTiposEstadosBD();
    }
    // </editor-fold>
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Controladora Deportiva">
    // <editor-fold defaultstate="collapsed" desc="Sanciones">
    public SancionTribunal crearSancionTribunal(Equipo unEquipo, Persona unaPersona, Date fecha, String motivo, String detalles) {
        return this.unaControladoraDeportiva.crearSancionTribunal(unEquipo, unaPersona, fecha, motivo, detalles);
    }

    public void modificarSancionTribunal(SancionTribunal unaSancionTribunal, Date fecha, String motivo, String detalles, String numeroResolucion, Date vencimiento, int cantFechas, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarSancionTribunal(unaSancionTribunal, fecha, motivo, detalles, numeroResolucion, vencimiento, cantFechas, borradoLogico);
    }

    public void eliminarSancionTribunal(SancionTribunal unaSancionTribunal) {
        this.unaControladoraDeportiva.eliminarSancionTribunal(unaSancionTribunal);
    }

    public void descontarSancion(Collection<Socia> unPlantel, Date unaFechaParametro) {
        this.unaControladoraDeportiva.descontarSancion(unPlantel, unaFechaParametro);
    }

    public SancionTribunal getSancionTribunalBD(Long id) {
        return this.unaControladoraDeportiva.getSancionTribunalBD(id);
    }

    public List<SancionTribunal> getSancionesTribunalesBD() {
        return this.unaControladoraDeportiva.getSancionesTribunalesBD();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tarjetas">
    public void crearTarjeta(Socia unaSocia, Partido unPartido, String tipo, String motivo, String tiempo, String minuto) {
        this.unaControladoraDeportiva.crearTarjeta(unaSocia, unPartido, tipo, motivo, tiempo, minuto);
    }

    public void crearTarjetaRoja(SancionTribunal unaSancionTribunal, Socia unaSocia, Partido unPartido, String motivo, String tiempo, String minuto) {
        this.unaControladoraDeportiva.crearTarjetaRoja(unaSancionTribunal, unaSocia, unPartido, motivo, tiempo, minuto);
    }

    public void modificarTarjeta(Tarjeta unaTarjeta, String tipo, String motivo, String tiempo, String minuto, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarTarjeta(unaTarjeta, tipo, motivo, tiempo, minuto, borradoLogico);
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

    public Torneo getTorneoTarjeta(Tarjeta unaTarjeta) {
        return unaControladoraDeportiva.getTorneoTarjeta(unaTarjeta);
    }

    public Partido getPartidoTarjeta(Tarjeta unaTarjeta) {
        return unaControladoraDeportiva.getPartidoTarjeta(unaTarjeta);
    }

    public SancionTribunal getSancionTarjeta(Tarjeta unaTarjeta) {
        return unaControladoraDeportiva.getSancionTarjeta(unaTarjeta);
    }

    public List<Tarjeta> getTarjetaSociaPartido(Partido unPartido, Socia unaSocia) {
        return unaControladoraDeportiva.getTarjetaSociaPartido(unPartido, unaSocia);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Equipos">
    public Equipo crearEquipo(Club unClub, String nombre, PersonaAuxiliar unDT, Socia unaCapitana, Socia unaCapitanaSup, Socia unaDelegada, Socia unaDelegadaSup, PersonaAuxiliar unPF, PersonaAuxiliar unAC) {
        return this.unaControladoraDeportiva.crearEquipo(unClub, nombre, unDT, unaCapitana, unaCapitanaSup, unaDelegada, unaDelegadaSup, unPF, unAC);
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

    public List<Socia> getJugadorasHabilitadas(Equipo unEquipo, Date unaFecha) {
        return this.unaControladoraDeportiva.getJugadorasHabilitadas(unEquipo, unaFecha);
    }

    public Equipo getEquipoBD(Long id) {
        return this.unaControladoraDeportiva.getEquipoBD(id);
    }

    public Club getClubBD(Equipo unEquipo) {
        return this.unaControladoraDeportiva.getClubBD(unEquipo);
    }

    public List<Equipo> getEquiposDBPorCategoria(Categoria unaCategoria) {
        return this.unaControladoraDeportiva.getEquiposDBPorCategoria(unaCategoria);
    }

    public List<Equipo> getEquiposBD() {
        return unaControladoraDeportiva.getEquiposBD();
    }

    public List<Equipo> getEquiposBDFiltro(String dato) {
        return this.unaControladoraDeportiva.getEquiposBDFiltro(dato);
    }

    public List<Equipo> getEquipoPorFecha(FechaTorneo unaFecha, Torneo unTorneo) {
        return this.unaControladoraDeportiva.getEquipoPorFecha(unaFecha, unTorneo);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Clubes">
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

    public JasperPrint generarReporteClub() {
        return this.unaControladoraDeportiva.generarReporteClub();
    }

    public List<Club> getClubesBDFiltro(String dato) {
        return this.unaControladoraDeportiva.getClubesBDFiltro(dato);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Indumentarias">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Canchas">
    public void crearCancha(Club unClub, String nombre, TipoCancha unTipoCancha) {
        this.unaControladoraDeportiva.crearCancha(unClub, nombre, unTipoCancha);
    }

    public void modificarCancha(Cancha unaCancha, String nombre, TipoCancha unTipoCancha, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarCancha(unaCancha, nombre, unTipoCancha, borradoLogico);
    }

    public void eliminarCancha(Cancha unaCancha) {
        this.unaControladoraDeportiva.eliminarCancha(unaCancha);
    }

    /**
     * Devuelve la cantidad de veces que se ocupó una cancha en un mes
     * determinado. Toma en cuenta solo partidos ya jugados dentro del mes/anio
     * pasado como parámetro.
     *
     * @param unaCancha
     * @param mes
     * @return
     */
    public int getCantCanchaOcupadaEnMes(Cancha unaCancha, int mes, int anio) {
        return this.unaControladoraDeportiva.getCantCanchaOcupadaEnMes(unaCancha, mes, anio);
    }

    public Cancha getCanchaBD(Long id) {
        return this.unaControladoraDeportiva.getCanchaBD(id);
    }

    public List<Cancha> getCanchasBD() {
        return this.unaControladoraDeportiva.getCanchasBD();
    }

    public List<Cancha> getCanchasPorFecha(FechaTorneo unaFecha) {
        return this.unaControladoraDeportiva.getCanchasPorFecha(unaFecha);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tipo Canchas">
    public void crearTipoCancha(String nombre) {
        this.unaControladoraDeportiva.crearTipoCancha(nombre);
    }

    public void modificarTipoCancha(TipoCancha unTipoCancha, String nombre, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarTipoCancha(unTipoCancha, nombre, borradoLogico);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Categorias">
    public void crearCategoria(String nombre, int edadParametro, int cantidadMinima, int cantidadMaxima) {
        this.unaControladoraDeportiva.crearCategoria(nombre, edadParametro, cantidadMinima, cantidadMaxima);
    }

    public void modificarCategoria(Categoria unaCategoria, String nombre, int edadParametro, int cantidadMinima, int cantidadMaxima, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarCategoria(unaCategoria, nombre, edadParametro, cantidadMinima, cantidadMaxima, borradoLogico);
    }

    public void eliminarCategoria(Categoria unaCategoria) {
        this.unaControladoraDeportiva.eliminarCategoria(unaCategoria);
    }

    public Categoria getCategoriaBD(Long id) {
        return this.unaControladoraDeportiva.getCategoriaBD(id);
    }

    public List<Categoria> getCategoriasBD() {
        return this.unaControladoraDeportiva.getCategoriasBD();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Torneos">
    public void crearTorneo(Date diaInicio, Categoria unaCategoria, String nombre) {
        this.unaControladoraDeportiva.crearTorneo(diaInicio, unaCategoria, nombre);
    }

    public void modificarTorneo(Torneo unTorneo, Date fechaInicio, Categoria unaCategoria, String nombre) {
        this.unaControladoraDeportiva.modificarTorneo(unTorneo, fechaInicio, unaCategoria, nombre);
    }

    public void eliminarTorneo(Torneo unTorneo) {
        this.unaControladoraDeportiva.eliminarTorneo(unTorneo);
    }

    public int agregarEquipoInscripto(Torneo unTorneo, Equipo unEquipo) {
        return this.unaControladoraDeportiva.agregarEquipoInscripto(unTorneo, unEquipo);
    }

    public int quitarEquipoInscripto(Torneo unTorneo, Equipo unEquipo) {
        return this.unaControladoraDeportiva.quitarEquipoInscripto(unTorneo, unEquipo);
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

    public List<Torneo> getTorneoParticipoSocia(Socia unaSocia) {
        return this.unaControladoraDeportiva.getTorneoParticipoSocia(unaSocia);
    }

    public Torneo getTorneoPartido(Partido unPartido) {
        return this.unaControladoraDeportiva.getTorneoPartido(unPartido);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fechas Torneo">
    public void crearFechaTorneo(Torneo unTorneo, int numeroFecha) {
        this.unaControladoraDeportiva.crearFechaTorneo(unTorneo, numeroFecha);
    }

    public void modificarFechaTorneo(FechaTorneo unaFechaTorneo, int numeroFecha, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarFechaTorneo(unaFechaTorneo, numeroFecha, borradoLogico);
    }

    public void eliminarFechaTorneo(FechaTorneo unaFechaTorneo) {
        this.unaControladoraDeportiva.eliminarFechaTorneo(unaFechaTorneo);
    }

    public FechaTorneo getFechaTorneoBD(Long id) {
        return this.unaControladoraDeportiva.getFechaTorneoBD(id);
    }

    public List<FechaTorneo> getFechasTorneosBD() {
        return this.unaControladoraDeportiva.getFechasTorneosBD();
    }

    public FechaTorneo getSiguienteFecha(FechaTorneo fechaActual, Torneo unTorneo) {
        return this.unaControladoraDeportiva.getSiguienteFecha(fechaActual, unTorneo);
    }

    public FechaTorneo getUnaFecha(int numeroFecha, Torneo unTorneo) {
        return this.unaControladoraDeportiva.getUnaFecha(numeroFecha, unTorneo);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Partidos">
    public void crearPartido(FechaTorneo unaFechaTorneo, Date fecha, Cancha unaCancha, Equipo unEquipoLocal, Equipo unEquipoVisitante, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3) {
        this.unaControladoraDeportiva.crearPartido(unaFechaTorneo, fecha, unaCancha, unEquipoLocal, unEquipoVisitante, unArbitro1, unArbitro2, unArbitro3);
    }

    public void modificarPartido(Partido unPartido, Date fecha, Cancha unaCancha, Equipo unEquipoLocal, Equipo unEquipoVisitante, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarPartido(unPartido, fecha, unaCancha, unEquipoLocal, unEquipoVisitante, unArbitro1, unArbitro2, unArbitro3, borradoLogico);
    }

    public void modificarPartido(Partido unPartido, String nombreVeedor, String nombreAyudanteMesaLocal, String nombreAyudanteMesaVisitante, String observaciones, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarPartido(unPartido, nombreVeedor, nombreAyudanteMesaLocal, nombreAyudanteMesaVisitante, observaciones, borradoLogico);
    }

    public void modificarPartidoPlantelLocal(Partido unPartido, Socia unaSocia) {
        this.unaControladoraDeportiva.modificarPartidoPlantelLocal(unPartido, unaSocia);
    }

    public void modificarPartidoPlantelVisitante(Partido unPartido, Collection<Socia> unPlantel) {
        this.unaControladoraDeportiva.modificarPartidoPlantelVisitante(unPartido, unPlantel);
    }

    public void eliminarPartido(Partido unPartido) {
        this.unaControladoraDeportiva.eliminarPartido(unPartido);
    }

    public boolean isPartidoAnteriorJugado(Partido unPartido) {
        return this.unaControladoraDeportiva.isPartidoAnteriorJugado(unPartido);
    }

    public Partido getPartidoBD(Long id) {
        return this.unaControladoraDeportiva.getPartidoBD(id);
    }

    public List<Partido> getPartidosConPlantelNoJugadosBD(Date fechaParametro) {
        return this.unaControladoraDeportiva.getPartidosConPlantelNoJugadosBD(fechaParametro);
    }

    public List<Partido> getPartidosBD() {
        return this.unaControladoraDeportiva.getPartidosBD();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gol">
    public void crearGol(Socia unaSocia, Partido unPartido, String tiempo, String minuto) {
        this.unaControladoraDeportiva.crearGol(unaSocia, unPartido, tiempo, minuto);
    }

    public void modificarGol(Gol unGol, String tiempo, String minuto, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarGol(unGol, tiempo, minuto, borradoLogico);
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

    public int getGolesLocal(Partido unPartido) {
        return this.unaControladoraDeportiva.getGolesLocal(unPartido);
    }

    public int getGolesVisitante(Partido unPartido) {
        return this.unaControladoraDeportiva.getGolesVisitante(unPartido);
    }

    /**
     * Devuelve Cantidad de goles que hizo una Socia en un Partido pasados por
     * parametro.
     *
     * @param unPartido
     * @param unaSocia
     * @return
     */
    public int getGolesSocia(Partido unPartido, Socia unaSocia) {
        return this.unaControladoraDeportiva.getGolesSocia(unPartido, unaSocia);
    }
    // </editor-fold>    
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Controladora Contabilidad">
    // <editor-fold defaultstate="collapsed" desc="Conceptos Deportivos">
    public ConceptoDeportivo crearConceptoDeportivo(double monto, String concepto, ArrayList<Mes> meses, TipoCancha unTipoCancha, TipoEstado unTipoEstado) {
        return this.unaControladoraContabilidad.crearConceptoDeportivo(monto, concepto, meses, unTipoCancha, unTipoEstado);
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, double monto, String concepto, ArrayList<Mes> meses, TipoCancha unTipoCancha, TipoEstado unTipoEstado, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoDeportivo(unConceptoDeportivo, monto, concepto, meses, unTipoCancha, unTipoEstado, borradoLogico);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Deudas">
    public void crearDeudaSocia(Socia unaSocia, Date fechaGeneracion, ConceptoDeportivo unConceptoDeportivo, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        this.unaControladoraContabilidad.crearDeudaSocia(unaSocia, fechaGeneracion, unConceptoDeportivo, observacion, montoTotal, cantCuotas, primerVencimiento);
    }

    public void crearDeudaEquipo(Equipo unEquipo, Date fechaGeneracion, ConceptoDeportivo unConceptoDeportivo, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        this.unaControladoraContabilidad.crearDeudaEquipo(unEquipo, fechaGeneracion, unConceptoDeportivo, observacion, montoTotal, cantCuotas, primerVencimiento);
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

    public List<Deuda> getDeudasEntreFechas(Date desde, Date hasta) {
        return this.unaControladoraContabilidad.getDeudaoEntreFechas(desde, hasta);
    }

    public List<Deuda> getDeudasMesSocias(Date fecha, Socia unaSocia) {
        return null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Cuotas">
    public Cuota getCuotaBD(Long id) {
        return this.unaControladoraContabilidad.getCuotaBD(id);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Pago Cuotas">
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
    
    public List<PagoCuota> getPagosCuotasEntreFechasBD(Date desde, Date hasta) {
        return this.unaControladoraContabilidad.getPagosCuotasEntreFechasBD(desde, hasta);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Concepto Ingreso">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Concepto Egreso">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ingreso Otro">
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

    public IngresoOtro getUltimoIngresoOtro() {
        return this.unaControladoraContabilidad.getUltimoIngresoOtro();
    }

    public IngresoOtro getPrimerIngresoOtro() {
        return this.unaControladoraContabilidad.getPrimerIngresoOtro();
    }

    public List<IngresoOtro> getIngresoOtroEntreFechas(Date desde, Date hasta) {
        return this.unaControladoraContabilidad.getIngresoOtroEntreFechas(desde, hasta);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Egreso">
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

    public Egreso getUltimoEgreso() {
        return this.unaControladoraContabilidad.getUltimoEgreso();
    }

    public Egreso getPrimerEgreso() {
        return this.unaControladoraContabilidad.getPrimerEgreso();
    }

    public List<Egreso> getEgresosEntreFechas(Date desde, Date hasta) {
        return this.unaControladoraContabilidad.getEgresosEntreFechas(desde, hasta);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Meses">
    public Mes getMesDB(int numeroMes) {
        return this.unaControladoraContabilidad.getMesDB(numeroMes);
    }
    // </editor-fold>    
// </editor-fold>

    public Date fechaSistema() {
        Date fechaSO = null;
        try {
            DateFormat df = DateFormat.getDateInstance();
            Calendar FechaSO = Calendar.getInstance();
            fechaSO = new java.sql.Date(df.parse(df.format(FechaSO.getTime())).getTime());
        } catch (ParseException ex) {
        }
        return fechaSO;
    }
}
