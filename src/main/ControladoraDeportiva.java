package main;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.Cancha;
import logicaNegocios.Categoria;
import logicaNegocios.Club;
import logicaNegocios.Equipo;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Gol;
import logicaNegocios.Indumentaria;
import logicaNegocios.Jugadora;
import logicaNegocios.Localidad;
import logicaNegocios.Partido;
import logicaNegocios.Persona;
import logicaNegocios.PersonaAuxiliar;
import logicaNegocios.SancionTribunal;
import logicaNegocios.Socia;
import logicaNegocios.Tarjeta;
import logicaNegocios.TipoCancha;
import logicaNegocios.Torneo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.util.JRLoader;

public class ControladoraDeportiva {

    private final EntityManager entityManager;

    public ControladoraDeportiva(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // <editor-fold defaultstate="collapsed" desc="Sanciones">
    public SancionTribunal crearSancionTribunal(Partido unPartido, Equipo unEquipo, Persona unaPersona, Date fecha, String motivo, String detalles) {
        SancionTribunal unaSancion = new SancionTribunal(this.entityManager, unPartido, fecha, motivo, detalles);
        if (unEquipo == null) {
            unaPersona.agregarSancionTribunal(entityManager, unaSancion);
        } else {
            unEquipo.agregarSancionTribunal(entityManager, unaSancion);
        }
        return unaSancion;
    }

    public SancionTribunal crearSancionTribunalParaTarjetaRoja(Persona unaPersona, Date fecha, String motivo, String detalles, int cantidadFechasCastigo, Tarjeta unaTarjetaRoja) {
        return new SancionTribunal(this.entityManager, fecha, motivo, detalles, cantidadFechasCastigo, unaTarjetaRoja);
    }

    public void modificarSancionTribunal(SancionTribunal unaSancionTribunal, Date fecha, String motivo, String detalles, String numeroResolucion, Date vencimiento, int cantFechas, boolean borradoLogico) {
        unaSancionTribunal.setFecha(fecha);
        unaSancionTribunal.setMotivo(motivo);
        unaSancionTribunal.setDetalles(detalles);
        unaSancionTribunal.setNumeroResolucion(numeroResolucion);
        unaSancionTribunal.setVencimiento(vencimiento);
        unaSancionTribunal.setCantFechas(cantFechas);
        unaSancionTribunal.setBorradoLogico(borradoLogico);
        unaSancionTribunal.persistir(this.entityManager);
    }

    public void eliminarSancionTribunal(SancionTribunal unaSancionTribunal) {
        unaSancionTribunal.setBorradoLogico(true);
        unaSancionTribunal.persistir(this.entityManager);
    }

    public void descontarSancion(Collection<Jugadora> jugadoras, Date unaFechaParametro) {
        for (Jugadora unaJugadora : jugadoras) {
            for (SancionTribunal unaSancionTribunal : unaJugadora.getUnaSocia().getSancionesVigentes(unaFechaParametro)) {
                unaSancionTribunal.sumarFechaCumplida();
                unaSancionTribunal.persistir(this.entityManager);
            }
        }
    }

    /**
     * Devuelve una SancionTribunal buscando por ID borrados inclusive
     */
    public SancionTribunal getSancionTribunalBD(Long id) {
        String unaConsulta = "SELECT A FROM SancionTribunal A WHERE A.idSancionTribunal = " + id;
        Query traerSancionTribunal = this.entityManager.createQuery(unaConsulta);
        return ((SancionTribunal) traerSancionTribunal.getSingleResult());
    }

    /**
     * Devuelve Todos las SancionTribunal
     */
    public List<SancionTribunal> getSancionesTribunalesBD() {
        String unaConsulta = "SELECT aux FROM SancionTribunal aux WHERE aux.borradoLogico = FALSE";
        List<SancionTribunal> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tarjetas">
    public Tarjeta crearTarjeta(Socia unaSocia, Partido unPartido, Date fecha, String tipo, String motivo, String tiempo, String minuto) {
        Tarjeta unaTarjeta = new Tarjeta(this.entityManager, fecha, tipo, motivo, tiempo, minuto);
        unaSocia.agregarTarjeta(this.entityManager, unaTarjeta);
        unPartido.agregarTarjeta(this.entityManager, unaTarjeta);
        return unaTarjeta;
    }

    public void modificarTarjeta(Tarjeta unaTarjeta, Date fecha, String tipo, String motivo, String tiempo, String minuto, boolean borradoLogico) {
        unaTarjeta.setFecha(fecha);
        unaTarjeta.setTipo(tipo);
        unaTarjeta.setMotivo(motivo);
        unaTarjeta.setTiempo(tiempo);
        unaTarjeta.setMinuto(minuto);
        unaTarjeta.setBorradoLogico(borradoLogico);
        unaTarjeta.persistir(this.entityManager);
    }

    public void eliminarTarjeta(Tarjeta unaTarjeta) {
        unaTarjeta.setBorradoLogico(true);
        unaTarjeta.persistir(this.entityManager);
    }

    public void computarTarjeta(Tarjeta unaTarjeta) {
        unaTarjeta.setComputado(true);
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

    /**
     * Devuelve el Torneo de una tarjeta
     */
    public Torneo getTorneoTarjeta(Tarjeta unaTarjeta) {
        String unaConsulta = "SELECT R FROM Torneo R, Tarjeta T WHERE T.idTarjeta = " + unaTarjeta.getIdTarjeta();
        Query traerTorneo = this.entityManager.createQuery(unaConsulta);
        Torneo unTorneo = (Torneo) traerTorneo.getSingleResult();
        return unTorneo;
        
        //return this.getTorneoDePartido(this.getPartidoTarjeta(unaTarjeta));
    }

    /**
     * Devuelve el Partido de una tarjeta
     */
    public Partido getPartidoTarjeta(Tarjeta unaTarjeta) {
        String unaConsulta = "SELECT P FROM Partido P, Tarjeta T WHERE T.idTarjeta = " + unaTarjeta.getIdTarjeta();
        Query traerPartido = this.entityManager.createQuery(unaConsulta);
        Partido unPartido = (Partido) traerPartido.getSingleResult();
        return unPartido;
    }

    /**
     * Devuelve la Sancion si hay de una tarjeta
     */
    public SancionTribunal getSancionTarjeta(Tarjeta unaTarjeta) {
        String unaConsulta = "SELECT P FROM SancionTribunal P WHERE P.unaTarjeta.idTarjeta = " + unaTarjeta.getIdTarjeta();
        Query traerSancionTribunal = this.entityManager.createQuery(unaConsulta);
        SancionTribunal unaSancionTribunal = (SancionTribunal) traerSancionTribunal.getSingleResult();
        return unaSancionTribunal;
    }

    /**
     * Devuelve las tarjetas de una Socia y un Partido
     */
    public List<Tarjeta> getTarjetaSociaPartido(Partido unPartido, Socia unaSocia) {
        String unaConsulta = "Select T FROM Tarjeta T, Partido P, Socia S WHERE P.tarjetas.idTarjeta = S.tarjetas.idTarjeta AND S.dni = " + unaSocia.getDni() + " AND P.idPartido = " + unPartido.getIdPartido();
        Query traerTarjeta = this.entityManager.createQuery(unaConsulta);
        List<Tarjeta> tarjetas = traerTarjeta.getResultList();
        return tarjetas;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Equipos">
    public Equipo crearEquipo(Club unClub, String nombre, PersonaAuxiliar unDT, Socia unaCapitana, Socia unaCapitanaSup, Socia unaDelegada, Socia unaDelegadaSup, PersonaAuxiliar unPF, PersonaAuxiliar unAC) {
        Equipo unEquipo = new Equipo(this.entityManager, nombre, unDT, unaCapitana, unaCapitanaSup, unaDelegada, unaDelegadaSup, unPF, unAC);
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

    public void eliminarEquipo(Equipo unEquipo) {
        unEquipo.setBorradoLogico(true);
        unEquipo.persistir(this.entityManager);
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
     * Devuelve los equipos que sean de una categoria. menos los borrados
     */
    public List<Equipo> getEquiposDBPorCategoria(Categoria unaCategoria) {
        List<Equipo> unaListaResultado = getEquiposBD();
        for (Equipo aux : getEquiposBD()) {
            if (!aux.isCategoria(unaCategoria)) {
                unaListaResultado.remove(aux);
            }
        }
        return unaListaResultado;
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

    public List<Equipo> getEquipoPorFecha(FechaTorneo unaFecha, Torneo unTorneo) {
        List<Equipo> unaListaResultado = (List<Equipo>) unTorneo.getEquiposInscriptos();
        if (unaFecha != null) {
            for (Partido unPartido : unaFecha.getPartidos()) {
                if (!unPartido.isBorradoLogico()) {
                    unaListaResultado.remove(unPartido.getUnEquipoLocal());
                    unaListaResultado.remove(unPartido.getUnEquipoVisitante());
                }
            }
        }
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Clubes">
    public void crearClub(String nombre, String nombrePresidente, Localidad unaLocalidad) {
        new Club(this.entityManager, nombre, nombrePresidente, unaLocalidad);
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
     * Devuelve unClub dueño de unEquipo pasado por parametro
     */
    public Club getClubBD(Equipo unEquipo) {
        Club resultado = null;
        for (Club aux : getClubesBD()) {
            if (aux.getEquipos().contains(unEquipo)) {
                resultado = aux;
                break;
            }
        }
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

    public JasperPrint generarReporteClub() {
        entityManager.getTransaction().begin();
        java.sql.Connection conexion = entityManager.unwrap(java.sql.Connection.class
        );
        File fichero = new File("reportes/reporteClubesMisiones.jasper");
        JasperPrint jasperPrint = null;

        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(fichero);
            jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
        } catch (JRException ex) {
            Logger.getLogger(ControladoraDeportiva.class.getName()).log(Level.SEVERE, null, ex);
        }

        entityManager.getTransaction()
                .commit();
        return jasperPrint;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Indumentarias">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Canchas">
    public void crearCancha(Club unClub, String nombre, TipoCancha unTipoCancha) {
        Cancha unaCancha = new Cancha(this.entityManager, nombre, unTipoCancha);
        unClub.agregarCancha(this.entityManager, unaCancha);
    }

    public void modificarCancha(Cancha unaCancha, String nombre, TipoCancha unTipoCancha, boolean borradoLogico) {
        unaCancha.setNombre(nombre);
        unaCancha.setUnTipoCancha(unTipoCancha);
        unaCancha.setBorradoLogico(borradoLogico);
        unaCancha.persistir(this.entityManager);
    }

    public void eliminarCancha(Cancha unaCancha) {
        unaCancha.setBorradoLogico(true);
        unaCancha.persistir(this.entityManager);
    }

    public int getCantCanchaOcupadaEnMes(Cancha unaCancha, int mes, int anio) {
        List<Partido> unaListaResultado = this.entityManager.createQuery("SELECT P FROM Partido P WHERE P.borradoLogico = FALSE AND P.unaCancha.idCancha = " + unaCancha.getIdCancha()).getResultList();
        DateFormat df = DateFormat.getDateInstance();
        String[] fechaDividida;
        int resultado = 0;
        for (Partido unPartido : unaListaResultado) {
            if (!unPartido.getNombreVeedor().isEmpty()) {
                fechaDividida = df.format(unPartido.getFecha()).split("/");
                if ((Integer.parseInt(fechaDividida[2]) == anio) && (Integer.parseInt(fechaDividida[1]) == mes)) {
                    resultado++;
                }
            }
        }
        return resultado;
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

    public List<Cancha> getCanchasPorFecha(FechaTorneo unaFecha) {
        String unaConsulta = "SELECT C FROM Cancha C WHERE C.borradoLogico = FALSE AND C.seOcupa = TRUE";
        List<Cancha> listaTodasLasCanchas = this.entityManager.createQuery(unaConsulta).getResultList();
        for (Partido unPartido : unaFecha.getPartidos()) {
            if (!unPartido.isBorradoLogico()) {
                listaTodasLasCanchas.remove(unPartido.getUnaCancha());
            }
        }
        return listaTodasLasCanchas;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Canchas Tipo">
    public void crearTipoCancha(String nombre) {
        new TipoCancha(this.entityManager, nombre);
    }

    public void modificarTipoCancha(TipoCancha unTipoCancha, String nombre, boolean borradoLogico) {
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Categorias">
    public void crearCategoria(String nombre, int edadParametro, int cantidadMinima, int cantidadMaxima) {
        new Categoria(this.entityManager, nombre, edadParametro, cantidadMinima, cantidadMaxima);
    }

    public void modificarCategoria(Categoria unaCategoria, String nombre, int edadParametro, int cantidadMinima, int cantidadMaxima, boolean borradoLogico) {
        unaCategoria.setNombre(nombre);
        unaCategoria.setEdadParametro(edadParametro);
        unaCategoria.setCantidadMinima(cantidadMinima);
        unaCategoria.setCantidadMaxima(cantidadMaxima);
        unaCategoria.setBorradoLogico(borradoLogico);
        unaCategoria.persistir(this.entityManager);
    }

    public void eliminarCategoria(Categoria unaCategoria) {
        unaCategoria.setBorradoLogico(true);
        unaCategoria.persistir(this.entityManager);
    }

    /**
     * Devuelve una Categoria por ID, incluido las borradas
     *
     * @param id
     * @return Categoria
     */
    public Categoria getCategoriaBD(Long id) {
        Query traerCategoria = this.entityManager.createQuery("SELECT a FROM Categoria a WHERE a.idCategoria = " + id);
        return ((Categoria) traerCategoria.getSingleResult());
    }

    /**
     * Devuelve todas las Categorias menos los Borradas
     *
     * @return List(Categoria)
     */
    public List<Categoria> getCategoriasBD() {
        String unaConsulta = "SELECT C FROM Categoria C WHERE C.borradoLogico = FALSE";
        List<Categoria> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Torneos">
    public void crearTorneo(Date diaInicio, Categoria unaCategoria, String nombre) {
        new Torneo(this.entityManager, diaInicio, unaCategoria, nombre);
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

    public int agregarEquipoInscripto(Torneo unTorneo, Equipo unEquipo) {
        return unTorneo.agregarEquipoInscripto(this.entityManager, unEquipo);
    }

    public int quitarEquipoInscripto(Torneo unTorneo, Equipo unEquipo) {
        return unTorneo.quitarEquipoInscripto(this.entityManager, unEquipo);
    }

    /**
     * Devuelve unTorneo por ID incluido los borrados
     */
    public Torneo getTorneoBD(Long idTorneo) {
        Torneo resultado;
        Query traerTorneo = this.entityManager.createQuery("SELECT T FROM Torneo T WHERE T.idTorneo = " + idTorneo);
        resultado = (Torneo) traerTorneo.getSingleResult();
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

    /**
     * Devuelve los Torneos filtrado por Nombre
     */
    public List<Torneo> getTorneosBDFiltro(String dato) {
        String unaConsulta = "SELECT T FROM Torneo T WHERE (T.nombre LIKE '%" + dato + "%' OR T.unaCategoria.nombre LIKE '%" + dato + "%') and(T.borradoLogico = FALSE)";
        List<Torneo> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve los Torneos en los que participo una socia
     */
    public List<Torneo> getTorneoParticipoSocia(Socia unaSocia) {
        List<Torneo> resultado = new ArrayList(this.getTorneosBD());
        for (Torneo unTorneo : this.getTorneosBD()) {
            if (!unTorneo.isSociaParticipo(unaSocia)) {
                resultado.remove(unTorneo);
            }
        }
        return resultado;
    }

    /**
     * Devuelve el Torneo de un Partido
     */
    public Torneo getTorneoDePartido(Partido unPartido) {
        for (Torneo unTorneo : this.getTorneosBD()) {
            for (FechaTorneo unaFechaTorneo : unTorneo.getFechasTorneo()) {
                if (unaFechaTorneo.getPartidos().contains(unPartido)) {
                    return unTorneo;
                }
            }
        }
        return null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fechas Torneo">
    public void crearFechaTorneo(Torneo unTorneo, int numeroFecha) {
        FechaTorneo unaFechaTorneo = new FechaTorneo(this.entityManager, numeroFecha);
        unTorneo.agregarFechaTorneo(this.entityManager, unaFechaTorneo);
    }

    public void modificarFechaTorneo(FechaTorneo unaFechaTorneo, int numeroFecha, boolean borradoLogico) {
        unaFechaTorneo.setNumeroFecha(numeroFecha);
        unaFechaTorneo.setBorradoLogico(borradoLogico);
        unaFechaTorneo.persistir(this.entityManager);
    }

    public void eliminarFechaTorneo(FechaTorneo unaFechaTorneo) {
        unaFechaTorneo.setBorradoLogico(true);
        unaFechaTorneo.persistir(this.entityManager);
    }

    public FechaTorneo getSiguienteFecha(FechaTorneo fechaActual, Torneo unTorneo) {
        return unTorneo.getSiguienteFecha(fechaActual);
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

    public FechaTorneo getUnaFecha(int numeroFecha, Torneo unTorneo) {
        return unTorneo.getUnaFecha(numeroFecha);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Partidos">
    public void crearPartido(FechaTorneo unaFechaTorneo, Date fecha, Cancha unaCancha, Equipo unEquipoLocal, Equipo unEquipoVisitante, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3) {
        Partido unPartido = new Partido(this.entityManager, fecha, unaCancha, unEquipoLocal, unEquipoVisitante, unArbitro1, unArbitro2, unArbitro3);
        unaFechaTorneo.agregarPartido(this.entityManager, unPartido);
    }

    public void modificarPartido(Partido unPartido, Date fecha, Cancha unaCancha, Equipo unEquipoLocal, Equipo unEquipoVisitante, PersonaAuxiliar unArbitro1, PersonaAuxiliar unArbitro2, PersonaAuxiliar unArbitro3, boolean borradoLogico) {
        unPartido.setFecha(fecha);
        unPartido.setUnaCancha(unaCancha);
        unPartido.setUnEquipoLocal(unEquipoLocal);
        unPartido.setUnEquipoVisitante(unEquipoVisitante);
        unPartido.setUnArbitro1(unArbitro1);
        unPartido.setUnArbitro2(unArbitro2);
        unPartido.setUnArbitro3(unArbitro3);
        unPartido.setBorradoLogico(borradoLogico);
        unPartido.persistir(this.entityManager);
    }

    public void modificarPartido(Partido unPartido, String nombreVeedor, String nombreAyudanteMesaLocal, String nombreAyudanteMesaVisitante, String observaciones, boolean borradoLogico) {
        unPartido.setNombreVeedor(nombreVeedor);
        unPartido.setNombreAyudanteMesaLocal(nombreAyudanteMesaLocal);
        unPartido.setNombreAyudanteMesaVisitante(nombreAyudanteMesaVisitante);
        unPartido.setObservaciones(observaciones);
        unPartido.setBorradoLogico(borradoLogico);
        unPartido.persistir(this.entityManager);
    }

    public void agregarJugadora(Partido unPartido, Socia unaSocia, String camiseta, boolean local) {
        unPartido.agregarJugadora(unaSocia, camiseta, local);
        unPartido.persistir(this.entityManager);
    }

    public void vaciarJugadoras(Partido unPartido) {
        unPartido.vaciarJugadoras();
        unPartido.persistir(this.entityManager);
    }

    public void eliminarPartido(Partido unPartido) {
        unPartido.setBorradoLogico(true);
        unPartido.persistir(this.entityManager);
    }

    public FechaTorneo getFechaTorneoDePartido(Partido unPartido) {
        FechaTorneo resultado = null;
        for (Torneo unTorneo : this.getTorneosBD()) {
            for (FechaTorneo unaFechaTorneo : unTorneo.getFechasTorneo()) {
                if (unaFechaTorneo.getPartidos().contains(unPartido)) {
                    resultado = unaFechaTorneo;
                    return resultado;
                }
            }
        }
        return resultado;
    }

    public Partido getPartidoAnterior(Partido unPartidoActual) {
        Partido resultado = null;
        FechaTorneo unaFechaTorneo = this.getFechaTorneoDePartido(unPartidoActual);
        for (Partido unPartidoAnterior : unaFechaTorneo.getPartidos()) {
            if (!unPartidoAnterior.isBorradoLogico()) {
                if (resultado == null) {
                    resultado = unPartidoAnterior;
                }
                if ((unPartidoAnterior.getFecha().before(unPartidoActual.getFecha())) && unPartidoAnterior.getFecha().after(resultado.getFecha())) {
                    resultado = unPartidoAnterior;
                }
            }
        }
        return resultado;
    }

    public boolean isPartidoAnteriorJugado(Partido unPartido) {
        boolean resultado = false;
        Partido unPartidoAnterior = this.getPartidoAnterior(unPartido);
        if (unPartido != unPartidoAnterior) {
            if (unPartidoAnterior.getNombreVeedor() != null) {
                if (!unPartidoAnterior.getNombreVeedor().isEmpty()) {
                    resultado = true;
                }
            }
        } else {
            resultado = true;
        }
        return resultado;
    }

    /**
     * Devuelve los partidos que ya poseen plantel guardado, es decir que estan
     * a punto de jugarse pero todavia no se jugaron hasta el dia de la fecha
     * pasada como parametro
     *
     * @param Date fechaParametro
     * @return
     */
    public List<Partido> getPartidosConPlantelNoJugadosBD(Date fechaParametro) {
        return this.entityManager.createQuery("SELECT T FROM Partido T WHERE T.borradoLogico = FALSE AND T.plantelLocal <> NULL AND T.fecha >=" + fechaParametro).getResultList();
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
    // </editor-fold>

    /**
     * Devuelve el numero de camiseta de la jugadora en un partido
     */
    public String getCamisetaPartido(Partido unPartido, Socia unaSocia) {
        String resultado = "-";
        for (Jugadora unaJugadora : unPartido.getJugadoras()) {
            if (unaJugadora.getUnaSocia().getDni() == unaSocia.getDni()) {
                resultado = unaJugadora.getCamiseta();
            }
        }
        return resultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gol">
    public void crearGol(Socia unaSocia, Partido unPartido, String tiempo, String minuto) {
        Gol unGol = new Gol(this.entityManager, tiempo, minuto);
        unaSocia.agregarGol(this.entityManager, unGol);
        unPartido.agregarGol(this.entityManager, unGol);
    }

    public void modificarGol(Gol unGol, String tiempo, String minuto, boolean borradoLogico) {
        unGol.setTiempo(tiempo);
        unGol.setMinuto(minuto);
        unGol.setBorradoLogico(borradoLogico);
        unGol.persistir(this.entityManager);
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

    public Jugadora getAutoraGol(Partido unPartido, Gol unGol) {
        for (Jugadora unaJugadora : unPartido.getJugadoras()) {
            if (unaJugadora.getUnaSocia().isAutoraGol(unGol)) {
                return unaJugadora;
            }
        }
        return null;
    }

    public int getGolesLocal(Partido unPartido) {
        int cantidadGoles = 0;
        for (Gol unGol : unPartido.getGoles()) {
            if (this.getAutoraGol(unPartido, unGol) != null) {
                if ((!unGol.isBorradoLogico()) && (this.getAutoraGol(unPartido, unGol).isLocal())) {
                    cantidadGoles++;
                }
            }
        }
        return cantidadGoles;
    }

    public int getGolesVisitante(Partido unPartido) {
        int cantidadGoles = 0;
        for (Gol unGol : unPartido.getGoles()) {
            if (this.getAutoraGol(unPartido, unGol) != null) {
                if ((!unGol.isBorradoLogico()) && (!this.getAutoraGol(unPartido, unGol).isLocal())) {
                    cantidadGoles++;
                }
            }
        }
        return cantidadGoles;
    }

    public int getGolesSocia(Partido unPartido, Socia unaSocia) {
        int cantidadGoles = 0;
        for (Gol unGol : unPartido.getGoles()) {
            if ((!unGol.isBorradoLogico()) && (unaSocia.isAutoraGol(unGol))) {
                cantidadGoles++;
            }
        }
        return cantidadGoles;
    }
    // </editor-fold>
}
