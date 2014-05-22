package main;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.PersonaAuxiliar;
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
    public void crearCuerpoTecnico(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, boolean arbitro, boolean cuerpoTecnico, String email, String telFijo, String telCelular) {
        PersonaAuxiliar unCuerpoTecnico = new PersonaAuxiliar(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, arbitro, cuerpoTecnico, email, telFijo, telCelular);
    }

    public void modificarCuerpoTecnico(PersonaAuxiliar unCuerpoTecnico, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotocopiaDni, boolean cuerpoTecnico, boolean dtActivo) {
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
        unCuerpoTecnico.setCuerpoTecnico(cuerpoTecnico);
        unCuerpoTecnico.setDtActivo(dtActivo);
        unCuerpoTecnico.persistir(this.entityManager);
    }

    public void eliminarCuerpoTecnico(PersonaAuxiliar unCuerpoTecnico) {
        unCuerpoTecnico.setBorradoLogico(true);
        unCuerpoTecnico.persistir(this.entityManager);
    }

    /**
     * Devuelve un CuerpTecnico buscando por DNI borrados inclusive
     */
    public PersonaAuxiliar getCuerpoTecnicoBD(Long dni) {
        PersonaAuxiliar resultado;
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.dni = " + dni;
        Query traerCuerpoTecnico = this.entityManager.createQuery(unaConsulta);
        resultado = (PersonaAuxiliar) traerCuerpoTecnico.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve Todos los CuerpoTecnico
     */
    public List<PersonaAuxiliar> getCuerposTecnicosBD() {
        String unaConsulta = "SELECT CT FROM PersonaAuxiliar CT WHERE CT.borradoLogico = FALSE";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una lista de CuerpoTecnico usando como filtro un DNI, Nombre o
     * Apellido menos los borrados
     */
    public List<PersonaAuxiliar> getCuerposTecnicosBDFiltro(String dato) {
        String unaConsulta = "SELECT CT FROM PersonaAuxiliar CT WHERE (CT.nombre LIKE " + "'%" + dato + "%' OR CT.apellido LIKE " + "'%" + dato + "%' OR CT.dni LIKE " + "'%" + dato + "%')and(CT.borradoLogico = FALSE)";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN CUERPO TECNICO------------------------------

//------------------------------ARBITROS----------------------------------------
    public void crearArbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico) {
        PersonaAuxiliar unArbitro = new PersonaAuxiliar(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, arbitro, cuerpoTecnico, email, telFijo, telCelular);
    }

    public void modificarArbitro(PersonaAuxiliar unArbitro, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico, String email, Date fechaIngreso, boolean borradoLogico, String fotocopiaDni) {
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
        unArbitro.setArbitro(arbitro);
        unArbitro.setCuerpoTecnico(cuerpoTecnico);
        unArbitro.setBorradoLogico(borradoLogico);
        unArbitro.setFotocopiaDni(fotocopiaDni);
        unArbitro.persistir(this.entityManager);
    }

    public void eliminarArbitro(PersonaAuxiliar unArbitro) {
        unArbitro.setBorradoLogico(true);
        unArbitro.persistir(this.entityManager);
    }

    /**
     * Devuelve unArbitro filtrado por DNI incluido los borrados
     */
    public PersonaAuxiliar getArbitroBD(Long dni) {
        PersonaAuxiliar resultado;
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.dni = " + dni + " AND A.borradoLogico = FALSE";
        Query traerArbitro = this.entityManager.createQuery(unaConsulta);
        resultado = (PersonaAuxiliar) traerArbitro.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los arbitros menos los borrados
     */
    public List<PersonaAuxiliar> getArbitrosBD() {
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.borradoLogico = FALSE";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una lista de Arbitros fitlro DNI o Nombre o Apellido menos los
     * borrados
     */
    public List<PersonaAuxiliar> getArbitrosBDFiltro(String dato) {
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE (A.nombre LIKE " + "'%" + dato + "%' OR A.apellido LIKE " + "'%" + dato + "%' OR A.dni LIKE " + "'%" + dato + "%')and(A.borradoLogico = FALSE)";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
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
     */
    public List<Socia> getSociasBD() {
        String unaConsulta = "SELECT S FROM Socia S WHERE S.borradoLogico = FALSE";
        List<Socia> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve todas las Socias filtradas por Dato
     */
    public List<Socia> getSociasBDFiltro(String dato) {
        String unaConsulta = "SELECT S FROM Socia S WHERE (S.nombre LIKE " + "'%" + dato + "%' OR S.apellido LIKE " + "'%" + dato + "%' OR S.dni LIKE " + "'%" + dato + "%')and(S.borradoLogico = FALSE)";
        List<Socia> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN SOCIAS--------------------------------------

//-----------------------------------PASES--------------------------------------
    public void crearPase(Socia unaSocia, Date fecha, double monto, Equipo unEquipo, Deuda unaDeuda, boolean libreDeudaClub, boolean solicitudPase, String observacion) {
        Pase unPase = new Pase(this.entityManager, fecha, monto, unEquipo, unaDeuda, libreDeudaClub, solicitudPase, observacion);
        unaSocia.agregarPase(this.entityManager, unPase);
    }

    public void modificarPase(Pase unPase, Date fecha, double monto, Equipo unEquipo, boolean libreDeudaClub, boolean solicitudPase, String observacion, boolean borradoLogico) {
        unPase.setFecha(fecha);
        unPase.setMonto(monto);
        unPase.setUnEquipo(unEquipo);
        unPase.setLibreDeudaClub(libreDeudaClub);
        unPase.setSolicitudPase(solicitudPase);
        unPase.setObservacion(observacion);
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
     */
    public Pase getPaseBD(Long id) {
        Pase resultado;
        String unaConsulta = "SELECT auxP FROM Pase auxP WHERE auxP.idPase = " + id;
        Query traerPase = this.entityManager.createQuery(unaConsulta);
        resultado = (Pase) traerPase.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los Pases Menos los borrados
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
     */
    public Localidad getLocalidadBD(Long id) {
        Localidad resultado;
        String unaConsulta = "SELECT A FROM Localidad A WHERE A.idLocalidad = " + id;
        Query traerLocalidad = this.entityManager.createQuery(unaConsulta);
        resultado = (Localidad) traerLocalidad.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todas las Localidades menos las borradas
     */
    public List<Localidad> getLocalidadesBD() {
        String unaConsulta = "SELECT A FROM Localidad A WHERE A.borradoLogico= FALSE";
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
