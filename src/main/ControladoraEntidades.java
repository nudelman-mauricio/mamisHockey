package main;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.Arbitro;
import logicaNegocios.Club;
import logicaNegocios.CuerpoTecnico;
import logicaNegocios.Deuda;
import logicaNegocios.Equipo;
import logicaNegocios.Ergometria;
import logicaNegocios.Estado;
import logicaNegocios.Frecuencia;
import logicaNegocios.Localidad;
import logicaNegocios.Pase;
import logicaNegocios.Socia;
import logicaNegocios.TipoEstado;

public class ControladoraEntidades {

    private final EntityManager entityManager;

    public ControladoraEntidades(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//------------------------------CUERPO TECNICO----------------------------------    
    public void crearCuerpoTecnico(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, boolean activo, String email, String telFijo, String telCelular) {
        CuerpoTecnico unCuerpoTecnico = new CuerpoTecnico(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, activo, email, telFijo, telCelular);
    }

    public void modificarCuerpoTecnico(CuerpoTecnico unCuerpoTecnico, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotocopiaDni, boolean activo) {
        unCuerpoTecnico.setDni(dni);
        unCuerpoTecnico.setApellido(apellido);
        unCuerpoTecnico.setNombre(nombre);
        unCuerpoTecnico.setUnaLocalidad(unaLocalidad);
        unCuerpoTecnico.setDomicilio(domicilio);
        unCuerpoTecnico.setFechaNacimiento(fechaNacimiento);
        unCuerpoTecnico.setTelFijo(telFijo);
        unCuerpoTecnico.setTelCelular(telCelular);
        unCuerpoTecnico.setEmail(email);
        unCuerpoTecnico.setFechaIngreso(fechaIngreso);
        unCuerpoTecnico.setBorradoLogico(borradoLogico);
        unCuerpoTecnico.setFotocopiaDni(fotocopiaDni);
        unCuerpoTecnico.setActivo(activo);
        unCuerpoTecnico.persistir(this.entityManager);
    }

    public void eliminarCuerpoTecnico(CuerpoTecnico unCuerpoTecnico) {
        unCuerpoTecnico.setBorradoLogico(true);
        unCuerpoTecnico.persistir(this.entityManager);
    }

    /**
     * Devuelve un CuerpTecnico buscando por DNI borrados inclusive
     */
    public CuerpoTecnico getCuerpoTecnicoBD(Long dni) {
        CuerpoTecnico resultado;
        String unaConsulta = "SELECT A FROM CuerpoTecnico A WHERE A.dni = " + dni;
        Query traerCuerpoTecnico = this.entityManager.createQuery(unaConsulta);
        resultado = (CuerpoTecnico) traerCuerpoTecnico.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve Todos los CuerpoTecnico
     */
    public List<CuerpoTecnico> getCuerposTecnicosBD() {
        String unaConsulta = "SELECT CT FROM CuerpoTecnico CT WHERE CT.borradoLogico = FALSE";
        List<CuerpoTecnico> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una lista de CuerpoTecnico usando como filtro un DNI, Nombre o
     * Apellido menos los borrados
     */
    public List<CuerpoTecnico> getCuerposTecnicosBDFiltro(String dato) {
        String unaConsulta = "SELECT CT FROM CuerpoTecnico CT WHERE (CT.nombre LIKE " + "'%" + dato + "%' OR CT.apellido LIKE " + "'%" + dato + "%' OR CT.dni LIKE " + "'%" + dato + "%')and(CT.borradoLogico = FALSE)";
        List<CuerpoTecnico> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN CUERPO TECNICO------------------------------

//------------------------------ARBITROS----------------------------------------
    public void crearArbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular) {
        Arbitro unArbitro = new Arbitro(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular);
    }

    public void modificarArbitro(Arbitro unArbitro, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotocopiaDni) {
        unArbitro.setDni(dni);
        unArbitro.setApellido(apellido);
        unArbitro.setNombre(nombre);
        unArbitro.setUnaLocalidad(unaLocalidad);
        unArbitro.setDomicilio(domicilio);
        unArbitro.setFechaNacimiento(fechaNacimiento);
        unArbitro.setTelFijo(telFijo);
        unArbitro.setTelCelular(telCelular);
        unArbitro.setEmail(email);
        unArbitro.setFechaIngreso(fechaIngreso);
        unArbitro.setBorradoLogico(borradoLogico);
        unArbitro.setFotocopiaDni(fotocopiaDni);
        unArbitro.persistir(this.entityManager);
    }

    public void eliminarArbitro(Arbitro unArbitro) {
        unArbitro.setBorradoLogico(true);
        unArbitro.persistir(this.entityManager);
    }

    /**
     * Devuelve unArbitro filtrado por DNI incluido los borrados
     */
    public Arbitro getArbitroBD(Long dni) {
        Arbitro resultado;
        String unaConsulta = "SELECT A FROM Arbitro A WHERE A.dni = " + dni + " AND A.borradoLogico = FALSE";
        Query traerArbitro = this.entityManager.createQuery(unaConsulta);
        resultado = (Arbitro) traerArbitro.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los arbitros
     *
     * @return
     */
    public List<Arbitro> getArbitrosBD() {
        String unaConsulta = "SELECT A FROM Arbitro A WHERE A.borradoLogico = FALSE";
        List<Arbitro> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una lista de Arbitros fitlro DNI o Nombre o Apellido
     *
     * @param dato
     * @return
     */
    public List<Arbitro> getArbitrosBDFiltro(String dato) {
        String unaConsulta = "SELECT A FROM Arbitro A WHERE (A.nombre LIKE " + "'%" + dato + "%' OR A.apellido LIKE " + "'%" + dato + "%' OR A.dni LIKE " + "'%" + dato + "%')and(A.borradoLogico = FALSE)";
        List<Arbitro> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN ARBITROS------------------------------------

//------------------------------SOCIAS------------------------------------------
    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora, String email, String telFijo, String telCelular) {
        Socia unaSocia = new Socia(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora, email, telFijo, telCelular);
    }

    public void modificarSocia(Socia unaSocia, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotoCarnet, boolean exJugadora) {
        unaSocia.setDni(dni);
        unaSocia.setApellido(apellido);
        unaSocia.setNombre(nombre);
        unaSocia.setUnaLocalidad(unaLocalidad);
        unaSocia.setDomicilio(domicilio);
        unaSocia.setFechaNacimiento(fechaNacimiento);
        unaSocia.setTelFijo(telFijo);
        unaSocia.setTelCelular(telCelular);
        unaSocia.setEmail(email);
        unaSocia.setFechaIngreso(fechaIngreso);
        unaSocia.setBorradoLogico(borradoLogico);
        unaSocia.setFotoCarnet(fotoCarnet);
        unaSocia.setExJugadora(exJugadora);
        unaSocia.setBorradoLogico(borradoLogico);
        unaSocia.persistir(this.entityManager);
    }

    public void eliminarSocia(Socia unaSocia) {
        unaSocia.setBorradoLogico(true);
        unaSocia.persistir(this.entityManager);
    }

    /**
     * Devuelve una Socia filtrado por DNI incluidas las borradas
     *
     * @param dni
     * @return
     */
    public Socia getSociaBD(Long dni) {
        Socia resultado;
        String unaConsulta = "SELECT S FROM Socia S WHERE S.dni = " + dni;
        Query traerSocia = this.entityManager.createQuery(unaConsulta);
        resultado = (Socia) traerSocia.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas las Socias
     *
     * @return
     */
    public List<Socia> getSociasBD() {
        String unaConsulta = "SELECT S FROM Socia S WHERE S.borradoLogico = FALSE";
        List<Socia> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve todas las Socias filtradas por Dato
     *
     * @param dato
     * @return
     */
    public List<Socia> getSociasBDFiltro(String dato) {
        String unaConsulta = "SELECT S FROM Socia S WHERE (S.nombre LIKE " + "'%" + dato + "%' OR S.apellido LIKE " + "'%" + dato + "%' OR S.dni LIKE " + "'%" + dato + "%')and(S.borradoLogico = FALSE)";
        List<Socia> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN SOCIAS--------------------------------------

//-----------------------------------PASES--------------------------------------
    public void crearPase(Socia unaSocia, Date fecha, double monto, Equipo unEquipo, Deuda unaDeuda) {
        Pase unPase = new Pase(this.entityManager, fecha, monto, unEquipo, unaDeuda);
        unaSocia.agregarPase(this.entityManager, unPase);
    }

    public void modificarPase(Pase unPase, Date fecha, double monto, Equipo unEquipo, boolean borradoLogico) {
        unPase.setFecha(fecha);
        unPase.setMonto(monto);
        unPase.setUnEquipo(unEquipo);
        unPase.setBorradoLogico(borradoLogico);
        unPase.persistir(this.entityManager);
    }

    public void modificarPaseDeSocia(Pase unPase, Socia unaSociaActual, Socia unaSociaNueva) {
        unaSociaActual.quitarPase(this.entityManager, unPase);
        unaSociaNueva.agregarPase(this.entityManager, unPase);
    }

    public void eliminarPase(Pase unPase) {
        unPase.setBorradoLogico(true);
        unPase.persistir(this.entityManager);
    }

    /**
     * Devuelve un Pase filtrado por ID incluido los Eliminados
     *
     * @param id
     * @return
     */
    public Pase getPaseBD(Long id) {
        Pase resultado;
        String unaConsulta = "SELECT auxP FROM Pase auxP WHERE auxP.idPase = " + id;
        Query traerPase = this.entityManager.createQuery(unaConsulta);
        resultado = (Pase) traerPase.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los Pases
     *
     * @return
     */
    public List<Pase> getPaseBD() {
        String unaConsulta = "SELECT P FROM Pase P WHERE P.borradoLogico = FALSE";
        List<Pase> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//---------------------------------FIN PASES------------------------------------

//------------------------------LOCALIDADES-------------------------------------   
    public void crearLocalidad(String nombre, String codPostal) {
        Localidad unaLocalidad = new Localidad(this.entityManager, nombre, codPostal);
    }

    public void modificarLocalidad(Localidad unaLocalidad, String nombre, String codPostal, boolean borradoLogico) {
        unaLocalidad.setNombre(nombre);
        unaLocalidad.setCodPostal(codPostal);
        unaLocalidad.setBorradoLogico(borradoLogico);
        unaLocalidad.persistir(this.entityManager);
    }

    public void eliminarLocalidad(Localidad unaLocalidad) {
        unaLocalidad.setBorradoLogico(true);
        unaLocalidad.persistir(this.entityManager);
    }

    /**
     * Devuelve una Localidad filtrada por ID incluido si esta borrada
     *
     * @param id
     * @return
     */
    public Localidad getLocalidadBD(Long id) {
        Localidad resultado;   
        String unaConsulta = "SELECT L FROM Localidad L WHERE L.idLocalidad = " + id;
        Query traerLocalidad = this.entityManager.createQuery(unaConsulta);
        resultado = (Localidad) traerLocalidad.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas las Localidades
     *
     * @return
     */
    public List<Localidad> getLocalidadesBD() {
        String unaConsulta = "SELECT L FROM Localidad L WHERE L.borradoLogico= FALSE";
        Query traerLocalidades = this.entityManager.createQuery(unaConsulta);
        List<Localidad> unaListaResultado = traerLocalidades.getResultList();
        return unaListaResultado;
    }
//------------------------------FIN LOCALIDADES---------------------------------

//-----------------------------------ERGOMETRIA---------------------------------
    public void crearErgometria(Socia unaSocia, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        Ergometria unaErgometria = new Ergometria(this.entityManager, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
        unaSocia.agregarErgometria(this.entityManager, unaErgometria);
    }

    public void modificarErgometria(Ergometria unaErgometria, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        unaErgometria.setAprobado(aprobado);
        unaErgometria.setComentarios(comentarios);
        unaErgometria.setFechaCaducidad(fechaCaducidad);
        unaErgometria.setFechaRealizacion(fechaRealizacion);
        unaErgometria.persistir(this.entityManager);
    }

    public void cambiarErgometriaDeSocia(Ergometria unaErgometria, Socia unaSociaActual, Socia unaSociaNueva) {
        unaSociaActual.quitarErgometria(this.entityManager, unaErgometria);
        unaSociaNueva.agregarErgometria(this.entityManager, unaErgometria);
    }

    public void eliminarErgometria(Ergometria unaErgometria) {
        unaErgometria.setBorradoLogico(true);
        unaErgometria.persistir(this.entityManager);
    }

    /**
     * Devuelve unaErgometria filtrado por ID inlcuido si está borrada
     *
     * @param id
     * @return
     */
    public Ergometria getErgometriaBD(Long id) {
        Ergometria resultado;
        String unaConsulta = "SELECT auxE FROM Ergometria auxE WHERE auxE.idErgometria = " + id;
        Query traerErgometria = entityManager.createQuery(unaConsulta);
        resultado = (Ergometria) traerErgometria.getSingleResult();
        return resultado;
    }
//---------------------------------FIN ERGOMETRIAS------------------------------

//-----------------------------------ESTADOS------------------------------------
    public void crearEstado(Socia unaSocia, Date fecha, TipoEstado unTipoEstado) {
        Estado unEstado = new Estado(this.entityManager, fecha, unTipoEstado);
        unaSocia.agregarEstado(this.entityManager, unEstado);
    }

    public void modificarEstado(Estado unEstado, Date fecha, TipoEstado unTipoEstado, boolean borradoLogico) {
        unEstado.setFecha(fecha);
        unEstado.setUnTipoEstado(unTipoEstado);
        unEstado.setBorradoLogico(borradoLogico);
        unEstado.persistir(this.entityManager);
    }

    public void cambiarEstadoDeSocia(Estado unEstado, Socia unaSociaActual, Socia unaSociaNueva) {
        unaSociaActual.quitarEstado(this.entityManager, unEstado);
        unaSociaNueva.agregarEstado(this.entityManager, unEstado);
    }

    public void eliminarEstado(Estado unEstado) {
        unEstado.setBorradoLogico(true);
        unEstado.persistir(this.entityManager);
    }

    /**
     * Devuelve unEstado filtrado por ID incluido los borrados
     *
     * @param id
     * @return
     */
    public Estado getEstadoBD(Long id) {
        Estado resultado;
        String unaConsulta = "SELECT auxE FROM Estado auxE WHERE auxE.idEstado = " + id;
        Query traerEstado = this.entityManager.createQuery(unaConsulta);
        resultado = (Estado) traerEstado.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los estados
     */
    public List<Estado> getEstadosDB() {
        String unaConsulta = "SELECT E FROM Estados E WHERE E.borradoLogico= FALSE";
        Query consulta = this.entityManager.createQuery(unaConsulta);
        List<Estado> unaListaResultado = consulta.getResultList();
        return unaListaResultado;
    }
//---------------------------------FIN ESTADOS----------------------------------

//---------------------------------TIPO ESTADO----------------------------------
    public void crearTipoEstado(double monto, Frecuencia unaFrecuencia, String nombre) {
        TipoEstado unTipoEstado = new TipoEstado(this.entityManager, monto, unaFrecuencia, nombre);
    }

    public void modificarTipoEstado(TipoEstado unTipoEstado, double monto, Frecuencia unaFrecuencia, String nombre, boolean borradoLogico) {
        unTipoEstado.setMonto(monto);
        unTipoEstado.setUnaFrecuencia(unaFrecuencia);
        unTipoEstado.setNombre(nombre);
        unTipoEstado.setBorradoLogico(borradoLogico);
        unTipoEstado.persistir(this.entityManager);
    }

    public void eliminarTipoEstado(TipoEstado unTipoEstado) {
        unTipoEstado.setBorradoLogico(true);
        unTipoEstado.persistir(this.entityManager);
    }

    /**
     * Devuelve unTipoEstado filtrado por ID incluido los borrados
     */
    public TipoEstado getTipoEstadoBD(Long id) {
        TipoEstado resultado;
        String unaConsulta = "SELECT auxE FROM TipoEstado auxE WHERE auxE.idTipoEstado = " + id;
        Query traerTipoEstado = this.entityManager.createQuery(unaConsulta);
        resultado = (TipoEstado) traerTipoEstado.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los TiposEstados
     */
    public List<TipoEstado> getTiposEstadosBD() {
        String unaConsulta = "SELECT TE FROM TipoEstado TE WHERE TE.borradoLogico= FALSE";
        Query consulta = this.entityManager.createQuery(unaConsulta);
        List<TipoEstado> unaListaResultado = consulta.getResultList();
        return unaListaResultado;
    }
//-------------------------------FIN TIPO ESTADO--------------------------------
}
