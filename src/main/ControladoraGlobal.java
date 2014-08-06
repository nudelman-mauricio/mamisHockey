package main;

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

// <editor-fold defaultstate="collapsed" desc="Controladora Entidades">
    // <editor-fold defaultstate="collapsed" desc="Persona Auxiliar">
    public void crearPersonaAuxiliar(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico, boolean cuerpoTecnicoActivo) {
        this.unaControladoraEntidades.crearPersonaAuxiliar(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular, arbitro, cuerpoTecnico, cuerpoTecnicoActivo);
    }

    public void modificarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, String fotocopiaDni, boolean arbitro, boolean cuerpoTecnico, boolean cuerpoTecnicoActivo, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarPersonaAuxiliar(unaPersonaAuxiliar, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, fotocopiaDni, arbitro, cuerpoTecnico, cuerpoTecnicoActivo, borradoLogico);
    }

    public void modificarNumeroCamiseta(Socia unaSocia, String numeroCamiseta) {
        this.unaControladoraEntidades.modificarNumeroCamiseta(unaSocia, numeroCamiseta);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Pases">
    public void crearPase(Socia unaSocia, Date fechaGeneracion, double montoTotal, int cantCuotas, Date primerVencimiento, Equipo unEquipoNuevo, boolean libreDeudaClub, boolean solicitudPase, String observacionPase) {
        String observacionDeuda;
        //no necesariamente es el primer pase. tambien pude una socia vieja pasar al vacio y luego a otro equipo
        if (unaSocia.getEquipoActual() == null) {
            observacionDeuda = "Primer pase de la socia al Equipo: " + unEquipoNuevo.getNombre();
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
            this.crearConceptoDeportivo(0.0, "Por Pase");
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tipo Estados">
    public void crearTipoEstado(String nombre) {
        this.unaControladoraEntidades.crearTipoEstado(nombre);
    }

    public void modificarTipoEstado(TipoEstado unTipoEstado, String nombre, boolean borradoLogico) {
        this.unaControladoraEntidades.modificarTipoEstado(unTipoEstado, nombre, borradoLogico);
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
    // </editor-fold>
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Controladora Deportiva">
    // <editor-fold defaultstate="collapsed" desc="Sanciones">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Equipos">
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

    public List<Equipo> getEquipoPorFecha(FechaTorneo unaFecha) {
        return this.unaControladoraDeportiva.getEquipoPorFecha(unaFecha);
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
    public void crearCancha(Club unClub, String nombre, boolean seOcupa, TipoCancha unTipoCancha) {
        this.unaControladoraDeportiva.crearCancha(unClub, nombre, seOcupa, unTipoCancha);
    }

    public void modificarCancha(Cancha unaCancha, String nombre, boolean seOcupa, TipoCancha unTipoCancha, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarCancha(unaCancha, nombre, seOcupa, unTipoCancha, borradoLogico);
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

    public Categoria buscarCategoriaBD(Long id) {
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

    public Torneo getTorneoPartido(Partido unaPartido) {
        return this.unaControladoraDeportiva.getTorneoPartido(unaPartido);
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
    public void crearPartido(FechaTorneo unaFechaTorneo, Equipo unEquipoVisitante, Date fecha, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, Cancha unaCancha, String observaciones, Equipo unEquipoLocal) {
        this.unaControladoraDeportiva.crearPartido(unaFechaTorneo, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unArbitro3, unaCancha, observaciones, unEquipoLocal);
    }

    public void modificarPartido(Partido unPartido, Equipo unEquipoVisitante, Date fecha, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, String nombreVeedor, String nombreAyudanteMesaLocal, String nombreAyudanteMesaVisitante, Cancha unaCancha, String observaciones, Equipo unEquipoLocal, boolean borradoLogico) {
        this.unaControladoraDeportiva.modificarPartido(unPartido, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unArbitro3, nombreVeedor, nombreAyudanteMesaLocal, nombreAyudanteMesaVisitante, unaCancha, observaciones, unEquipoLocal, borradoLogico);
    }

    public void eliminarPartido(Partido unPartido) {
        this.unaControladoraDeportiva.eliminarPartido(unPartido);
    }

    public Partido getPartidoBD(Long id) {
        return this.unaControladoraDeportiva.getPartidoBD(id);
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
    // </editor-fold>
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Controladora Contabilidad">
    // <editor-fold defaultstate="collapsed" desc="Conceptos Deportivos">
    public ConceptoDeportivo crearConceptoDeportivo(double monto, String concepto) {
        return this.unaControladoraContabilidad.crearConceptoDeportivo(monto, concepto);
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, double monto, String concepto, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarConceptoDeportivo(unConceptoDeportivo, monto, concepto, borradoLogico);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Meses">
    public Mes getMesDB(int numeroMes) {
        return this.unaControladoraContabilidad.getMesDB(numeroMes);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Frecuencia"> 
    public Frecuencia crearFrecuencia(String diaGeneracion, String diaVencimiento, boolean enero, boolean febrero, boolean marzo, boolean abril, boolean mayo, boolean junio, boolean julio, boolean agosto, boolean septiembre, boolean octubre, boolean noviembre, boolean diciembre) {
        Frecuencia nuevaFrecuencia = this.unaControladoraContabilidad.crearFrecuencia(diaGeneracion, diaVencimiento);
        if (enero) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(1));
        }
        if (febrero) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(2));
        }
        if (marzo) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(3));
        }
        if (abril) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(4));
        }
        if (mayo) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(5));
        }
        if (junio) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(6));
        }
        if (julio) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(7));
        }
        if (agosto) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(8));
        }
        if (septiembre) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(9));
        }
        if (octubre) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(10));
        }
        if (noviembre) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(11));
        }
        if (diciembre) {
            this.agregarMesFrecuencia(nuevaFrecuencia, this.getMesDB(12));
        }
        return nuevaFrecuencia;
    }

    public void modificarFrecuencia(Frecuencia unaFrecuencia, String diaGeneracion, String diaVencimiento, boolean enero, boolean febrero, boolean marzo, boolean abril, boolean mayo, boolean junio, boolean julio, boolean agosto, boolean septiembre, boolean octubre, boolean noviembre, boolean diciembre, boolean borradoLogico) {
        this.unaControladoraContabilidad.modificarFrecuencia(unaFrecuencia, diaGeneracion, diaVencimiento, borradoLogico);
        if (enero) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(1));
        }
        if (febrero) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(2));
        }
        if (marzo) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(3));
        }
        if (abril) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(4));
        }
        if (mayo) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(5));
        }
        if (junio) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(6));
        }
        if (julio) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(7));
        }
        if (agosto) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(8));
        }
        if (septiembre) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(9));
        }
        if (octubre) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(10));
        }
        if (noviembre) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(11));
        }
        if (diciembre) {
            this.agregarMesFrecuencia(unaFrecuencia, this.getMesDB(12));
        }
    }

    public void eliminarFrecuencia(Frecuencia unaFrecuencia) {
        this.unaControladoraContabilidad.eliminarFrecuencia(unaFrecuencia);
    }

    public void agregarMesFrecuencia(Frecuencia unaFrecuencia, Mes unMes) {
        this.unaControladoraContabilidad.agregarMesFrecuencia(unaFrecuencia, unMes);
    }

    public Frecuencia getFrecuenciaBD(Long id) {
        return this.unaControladoraContabilidad.getFrecuenciaBD(id);
    }

    public List<Frecuencia> getFrecuenciasBD() {
        return this.unaControladoraContabilidad.getFrecuenciasBD();
    }
    // </editor-fold>
// </editor-fold>
}
