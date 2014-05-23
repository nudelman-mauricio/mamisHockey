package main;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.PersonaAuxiliar;
import logicaNegocios.Cancha;
import logicaNegocios.Categoria;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Frecuencia;
import logicaNegocios.Gol;
import logicaNegocios.Indumentaria;
import logicaNegocios.Localidad;
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

    /**
     * Devuelve una SancionTribunal buscando por ID borrados inclusive
     */
    public SancionTribunal getSancionTribunalBD(Long id) {
        SancionTribunal resultado;
        String unaConsulta = "SELECT A FROM SancionTribunal A WHERE A.idSancionTribunal = " + id;
        Query traerSancionTribunal = this.entityManager.createQuery(unaConsulta);
        resultado = (SancionTribunal) traerSancionTribunal.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve Todos las SancionTribunal
     */
    public List<SancionTribunal> getSancionesTribunalesBD() {
        String unaConsulta = "SELECT aux FROM SancionTribunal aux WHERE aux.borradoLogico = FALSE";
        List<SancionTribunal> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN SANCIONES-----------------------------------

//--------------------------------TARJETAS--------------------------------------
    public void crearTarjeta(Socia unaSocia, Partido unPartido, String tipo, String motivo, String minuto, String detalles) {
        Tarjeta unaTarjeta = new Tarjeta(this.entityManager, tipo, motivo, minuto, detalles);
        unaSocia.agregarTarjeta(this.entityManager, unaTarjeta);
        unPartido.agregarTarjeta(this.entityManager, unaTarjeta);
    }

    /**
     * Al mismo tiempo se crea una sancion tribunal por la Roja
     */
    public void crearTarjetaRoja(SancionTribunal unaSancionTribunal, Socia unaSocia, Partido unPartido, String motivo, String minuto, String detalles) {
        Tarjeta unaTarjeta = new Tarjeta(this.entityManager, "Roja", motivo, minuto, detalles);
        unaSocia.agregarTarjeta(this.entityManager, unaTarjeta);
        unPartido.agregarTarjeta(this.entityManager, unaTarjeta);
        unaSancionTribunal.setUnaTarjeta(unaTarjeta);
        unaSancionTribunal.persistir(this.entityManager);
    }

    public void modificarTarjeta(Tarjeta unaTarjeta, String tipo, String motivo, String minuto, String detalles, boolean borradoLogico) {
        unaTarjeta.setTipo(tipo);
        unaTarjeta.setMotivo(motivo);
        unaTarjeta.setMinuto(minuto);
        unaTarjeta.setDetalles(detalles);
        unaTarjeta.setBorradoLogico(borradoLogico);
        unaTarjeta.persistir(this.entityManager);
    }

    public void eliminarTarjeta(Tarjeta unaTarjeta) {
        unaTarjeta.setBorradoLogico(true);
        unaTarjeta.persistir(this.entityManager);
    }

    /**
     * Devuelve una tarjeta buscada por ID incluidas las borradas
     */
    public Tarjeta getTarjetaBD(Long id) {
        String unaConsulta = "SELECT A FROM Tarjeta A WHERE A.idTarjeta = " + id;
        Query traerTarjeta = this.entityManager.createQuery(unaConsulta);
        return ((Tarjeta) traerTarjeta.getSingleResult());
    }

    /**
     * Devuelve todas las Tarjetas
     */
    public List<Tarjeta> getTarjetasBD() {
        String unaConsulta = "SELECT E FROM Tarjeta E WHERE E.borradoLogico = FALSE";
        Query traerTarjeta = this.entityManager.createQuery(unaConsulta);
        List<Tarjeta> unaListaResultado = traerTarjeta.getResultList();
        return unaListaResultado;
    }
//------------------------------FIN TARJETAS------------------------------------

//------------------------------EQUIPOS-----------------------------------------   
    public Equipo crearEquipo(Club unClub, String nombre, PersonaAuxiliar unDT) {
        Equipo unEquipo = new Equipo(this.entityManager, nombre, unDT);
        unClub.agregarEquipo(this.entityManager, unEquipo);
        return unEquipo;
    }

    public void modificarEquipo(Equipo unEquipo, String nombre, Socia unaCapitana, Socia unaCapitanaSuplente, Socia unaDelegada, Socia unaDelegadaSuplente, PersonaAuxiliar unDT, PersonaAuxiliar unPreparadorFisico, PersonaAuxiliar unAyudanteCampo, boolean borradoLogico) {
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

    /**
     * Devuelve un Equipo por ID incluido los borrados
     */
    public Equipo getEquipoBD(Long id) {
        Equipo resultado;
        Query traerEquipo = this.entityManager.createQuery("SELECT A FROM Equipo A WHERE A.idEquipo = " + id);
        resultado = (Equipo) traerEquipo.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los equipos menos los borrados
     */
    public List<Equipo> getEquiposBD() {
        Query traerEquipos = this.entityManager.createQuery("SELECT E FROM Equipo E WHERE E.borradoLogico = FALSE");
        List<Equipo> unaListaResultado = traerEquipos.getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve equipos filtrando por nombre de Club o Equipo excluye a los
     * borrados
     */
    public List<Equipo> getEquiposBDFiltro(String dato) {
        String unaConsulta = "SELECT E FROM Equipo E, Club C JOIN C.equipos R WHERE (R.idEquipo = E.idEquipo) AND ((E.nombre LIKE " + "'%" + dato + "%') OR (C.nombre LIKE " + "'%" + dato + "%')) AND (E.borradoLogico = FALSE) AND (C.borradoLogico = FALSE)";
        List<Equipo> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN EQUIPOS-------------------------------------

//------------------------------CLUBES------------------------------------------
    public void crearClub(String nombre, String nombrePresidente, Localidad unaLocalidad) {
        Club unClub = new Club(this.entityManager, nombre, nombrePresidente, unaLocalidad);
    }

    public void modificarClub(Club unClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad, boolean borradoLogico) {
        unClub.setNombre(nombre);
        unClub.setLogo(logo);
        unClub.setNombrePresidente(nombrePresidente);
        unClub.setUnaLocalidad(unaLocalidad);
        unClub.setBorradoLogico(borradoLogico);
        unClub.persistir(this.entityManager);
    }

    public void eliminarClub(Club unClub) {
        unClub.setBorradoLogico(true);
        unClub.persistir(this.entityManager);
    }

    /**
     * Devuelve unClub filtrado por ID incluido los borrados
     */
    public Club getClubBD(Long id) {
        Club resultado;
        String unaConsulta = "SELECT A FROM Club A WHERE A.idClub = " + id;
        Query traerClub = this.entityManager.createQuery(unaConsulta);
        resultado = (Club) traerClub.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los Clubes menos los borrados
     */
    public List<Club> getClubesBD() {
        String unaConsulta = "SELECT C FROM Club C WHERE C.borradoLogico = FALSE";
        List<Club> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve los Clubes filtrado por Nombre
     */
    public List<Club> getClubesBDFiltro(String dato) {
        String unaConsulta = "SELECT C FROM Club C WHERE (C.nombre LIKE " + "'%" + dato + "%')and(C.borradoLogico = FALSE)";
        List<Club> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN CLUBES--------------------------------------

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

    /**
     * Devuelve unaIndumentaria por ID incluido los borrados
     */
    public Indumentaria getIndumentariaBD(Long id) {
        Indumentaria resultado;
        String unaConsulta = "SELECT A FROM Indumentaria A WHERE A.idIndumentaria = " + id;
        Query traerIndumentaria = this.entityManager.createQuery(unaConsulta);
        resultado = (Indumentaria) traerIndumentaria.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas las Indumentarias menos las Borradas
     */
    public List<Indumentaria> getIndumentariasBD() {
        String unaConsulta = "SELECT C FROM Indumentaria C WHERE C.borradoLogico = FALSE";
        List<Indumentaria> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
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

    /**
     * Devuelve unaCancha por ID incluido las borradas
     */
    public Cancha getCanchaBD(Long id) {
        Cancha resultado;
        String unaConsulta = "SELECT A FROM Cancha A WHERE A.idCancha = " + id;
        Query traerCancha = this.entityManager.createQuery(unaConsulta);
        resultado = (Cancha) traerCancha.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas los TipoCancha menos los Borrados
     */
    public List<Cancha> getCanchasBD() {
        String unaConsulta = "SELECT C FROM Cancha C WHERE C.borradoLogico = FALSE";
        List<Cancha> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
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

    /**
     * Devuelve unTipoCancha por ID incluido los borrados
     */
    public TipoCancha getTipoCanchaBD(Long id) {
        TipoCancha resultado;
        String unaConsulta = "SELECT A FROM TipoCancha A WHERE A.idTipoCancha = " + id;
        Query traerTipoCancha = this.entityManager.createQuery(unaConsulta);
        resultado = (TipoCancha) traerTipoCancha.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas los TipoCancha menos los Borrados
     */
    public List<TipoCancha> getTiposCanchasBD() {
        String unaConsulta = "SELECT C FROM TipoCancha C WHERE C.borradoLogico = FALSE";
        List<TipoCancha> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//-----------------------------FIN TIPO CANCHA----------------------------------

//------------------------------CATEGORIAS--------------------------------------    
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

    /**
     * Devuelve una Categoria por ID, incluido las borradas
     */
    public Categoria getCategoriaBD(Long id) {
        Categoria resultado = null;
        Query traerCategoria = this.entityManager.createQuery("SELECT a FROM Categoria a WHERE a.idCategoria = " + id);
        resultado = (Categoria) traerCategoria.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas las Categorias menos los Borradas
     */
    public List<Categoria> getCategoriasBD() {
        String unaConsulta = "SELECT C FROM Categoria C WHERE C.borradoLogico = FALSE";
        List<Categoria> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
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

    /**
     * Devuelve unTorneo por ID incluido los borrados
     */
    public Torneo getTorneoBD(Long idTorneo) {
        Torneo resultado;
        Query traerTorneo = this.entityManager.createQuery("SELECT T FROM Torneo T WHERE T.idTorneo = " + idTorneo);
        resultado = (Torneo) traerTorneo.getResultList();
        return resultado;
    }

    /**
     * Devuelve todos los torneos menos los borrados
     */
    public List<Torneo> getTorneosBD() {
        String unaConsulta = ("SELECT T FROM Torneo T WHERE T.borradoLogico = FALSE");
        List<Torneo> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN TORNEOS-------------------------------------

//---------------------------------FECHAS TORNEO--------------------------------
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

    /**
     * Devuelve unaFechaTorneo por ID incluidas las borradas
     */
    public FechaTorneo getFechaTorneoBD(Long id) {
        FechaTorneo resultado;
        Query traerFechaTorneo = this.entityManager.createQuery("SELECT auxFT FROM FechaTorneo auxFT WHERE auxFT.idFechaTorneo = " + id);
        resultado = (FechaTorneo) traerFechaTorneo.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas las FechaTorneo menos las borradas
     */
    public List<FechaTorneo> getFechasTorneosBD() {
        String unaConsulta = ("SELECT T FROM FechaTorneo T WHERE T.borradoLogico = FALSE");
        List<FechaTorneo> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN FECHAS TORNEO-------------------------------

//-----------------------------------PARTIDOS-----------------------------------
    public void crearPartido(FechaTorneo unaFechaTorneo, Equipo unEquipoVisitante, Date fecha, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, Cancha unaCancha, String observaciones, Equipo unEquipoLocal) {
        Partido unPartido = new Partido(this.entityManager, unEquipoVisitante, fecha, unArbitro1, unArbitro2, unArbitro3, unaCancha, observaciones, unEquipoLocal);
        unaFechaTorneo.agregarPartido(this.entityManager, unPartido);
    }

    public void modificarPartido(Partido unPartido, Equipo unEquipoVisitante, Date fecha, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, String nombreVeedor, String nombreAyudanteMesaLocal, String nombreAyudanteMesaVisitante, Cancha unaCancha, String observaciones, Equipo unEquipoLocal, boolean borradoLogico) {
        unPartido.setBorradoLogico(borradoLogico);
        unPartido.setFecha(fecha);
        unPartido.setObservaciones(observaciones);
        unPartido.setUnArbitro1(unArbitro1);
        unPartido.setUnArbitro2(unArbitro2);
        unPartido.setUnArbitro3(unArbitro3);
        unPartido.setNombreVeedor(nombreVeedor);
        unPartido.setNombreAyudanteMesaLocal(nombreAyudanteMesaLocal);
        unPartido.setNombreAyudanteMesaVisitante(nombreAyudanteMesaVisitante);
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

    /**
     * Devuelve unPartido por ID incluido los Borrados
     */
    public Partido getPartidoBD(Long id) {
        Partido resultado;
        Query traerPartido = entityManager.createQuery("SELECT auxP FROM Partido auxP WHERE auxP.idPartido = " + id);
        resultado = (Partido) traerPartido.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los Partidos menos los borrados
     */
    public List<Partido> getPartidosBD() {
        String unaConsulta = ("SELECT T FROM Partido T WHERE T.borradoLogico = FALSE");
        List<Partido> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//---------------------------------FIN PARTIDOS---------------------------------

//---------------------------------GOLES----------------------------------------
    public void crearGol(Socia unaSocia, Partido unPartido, String minuto) {
        Gol unGol = new Gol(this.entityManager, minuto);
        unaSocia.agregarGol(this.entityManager, unGol);
        unPartido.agregarGol(this.entityManager, unGol);
    }

    public void modificarGol(Gol unGol, String minuto, boolean borradoLogico) {
        unGol.setMinuto(minuto);
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

    /**
     * Devuelve unGol por ID incluido los Borrados
     */
    public Gol getGolBD(Long id) {
        Gol resultado;
        Query traerGol = entityManager.createQuery("SELECT auxP FROM Gol auxP WHERE auxP.idGol = " + id);
        resultado = (Gol) traerGol.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los Goles menos los borrados
     */
    public List<Gol> getGolesBD() {
        String unaConsulta = ("SELECT T FROM Gol T WHERE T.borradoLogico = FALSE");
        List<Gol> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//-------------------------------FIN GOLES--------------------------------------
}
