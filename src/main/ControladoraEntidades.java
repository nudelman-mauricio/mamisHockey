package main;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.Deuda;
import logicaNegocios.Equipo;
import logicaNegocios.Ergometria;
import logicaNegocios.Estado;
import logicaNegocios.FechaTorneo;
import logicaNegocios.Localidad;
import logicaNegocios.Partido;
import logicaNegocios.Pase;
import logicaNegocios.PersonaAuxiliar;
import logicaNegocios.Socia;
import logicaNegocios.TipoEstado;

public class ControladoraEntidades {

    private final EntityManager entityManager;

    public ControladoraEntidades(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // <editor-fold defaultstate="collapsed" desc="Persona Auxiliar">
    public void crearPersonaAuxiliar(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico) {
        new PersonaAuxiliar(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular, arbitro, cuerpoTecnico);
    }

    public void modificarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, String fotocopiaDni, boolean arbitro, boolean cuerpoTecnico, boolean borradoLogico) {
        unaPersonaAuxiliar.setDni(dni);
        unaPersonaAuxiliar.setApellido(apellido);
        unaPersonaAuxiliar.setNombre(nombre);
        unaPersonaAuxiliar.setUnaLocalidad(unaLocalidad);
        unaPersonaAuxiliar.setDomicilio(domicilio);
        unaPersonaAuxiliar.setFechaNacimiento(fechaNacimiento);
        unaPersonaAuxiliar.setTelFijo(telFijo);
        unaPersonaAuxiliar.setTelCelular(telCelular);
        unaPersonaAuxiliar.setEmail(email);
        unaPersonaAuxiliar.setFechaIngreso(fechaIngreso);
        unaPersonaAuxiliar.setFotocopiaDni(fotocopiaDni);
        unaPersonaAuxiliar.setArbitro(arbitro);
        unaPersonaAuxiliar.setCuerpoTecnico(cuerpoTecnico);
        unaPersonaAuxiliar.setBorradoLogico(borradoLogico);
        unaPersonaAuxiliar.persistir(this.entityManager);
    }

    public void eliminarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar) {
        unaPersonaAuxiliar.setBorradoLogico(true);
        unaPersonaAuxiliar.persistir(this.entityManager);
    }

    public List<PersonaAuxiliar> getPersonasAuxiliarBDFiltro(String dato) {
        String unaConsulta = "SELECT PA FROM PersonaAuxiliar PA WHERE (PA.nombre LIKE " + "'%" + dato + "%' OR PA.apellido LIKE " + "'%" + dato + "%' OR PA.dni LIKE " + "'%" + dato + "%')and(PA.borradoLogico = FALSE)";

        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una PersonaAuxiliar buscando por DNI
     */
    public PersonaAuxiliar getPersonaAuxiliarBD(Long dni) {
        PersonaAuxiliar resultado;
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.dni = " + dni;
        Query traerCuerpoTecnico = this.entityManager.createQuery(unaConsulta);
        resultado = (PersonaAuxiliar) traerCuerpoTecnico.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve un CuerpTecnico buscando por DNI borrados inclusive
     */
    public PersonaAuxiliar getCuerpoTecnicoBD(Long dni) {
        PersonaAuxiliar resultado;
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.dni = " + dni + " AND A.cuerpoTecnico = TRUE AND A.borradoLogico = FALSE";
        Query traerPersonaAuxiliar = this.entityManager.createQuery(unaConsulta);
        resultado = (PersonaAuxiliar) traerPersonaAuxiliar.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve unArbitro filtrado por DNI incluido los borrados
     */
    public PersonaAuxiliar getArbitroBD(Long dni) {
        PersonaAuxiliar resultado;
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.dni = " + dni + " AND A.arbitro = TRUE";
        Query traerArbitro = this.entityManager.createQuery(unaConsulta);
        resultado = (PersonaAuxiliar) traerArbitro.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve Todos los CuerpoTecnico menos los borrados
     */
    public List<PersonaAuxiliar> getCuerposTecnicosBD() {
        String unaConsulta = "SELECT CT FROM PersonaAuxiliar CT WHERE CT.borradoLogico = FALSE AND CT.cuerpoTecnico = TRUE";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve Todos los CuerpoTecnico DESOCUPADOS menos los borrados
     */
    public List<PersonaAuxiliar> getCuerposTecnicosDesocupadosBD() {
        String unaConsulta = "SELECT CT FROM PersonaAuxiliar CT WHERE CT.borradoLogico = FALSE AND CT.cuerpoTecnico = TRUE AND CT.cuerpoTecnicoActivo = FALSE";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve todos los arbitros menos los borrados
     */
    public List<PersonaAuxiliar> getArbitrosBD() {
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.borradoLogico = FALSE AND A.arbitro = TRUE";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una lista de CuerpoTecnico usando como filtro un DNI, Nombre o
     * Apellido menos los borrados
     */
    public List<PersonaAuxiliar> getCuerposTecnicosBDFiltro(String dato) {
        String unaConsulta = "SELECT CT FROM PersonaAuxiliar CT WHERE (CT.nombre LIKE " + "'%" + dato + "%' OR CT.apellido LIKE " + "'%" + dato + "%' OR CT.dni LIKE " + "'%" + dato + "%')AND(CT.borradoLogico = FALSE)AND(CT.cuerpoTecnico = TRUE)";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una lista de CuerpoTecnico DESOCUAPDOS usando como filtro un
     * DNI, Nombre o Apellido menos los borrados
     */
    public List<PersonaAuxiliar> getCuerposTecnicosDesocupadosBDFiltro(String dato) {
        String unaConsulta = "SELECT CT FROM PersonaAuxiliar CT WHERE (CT.nombre LIKE " + "'%" + dato + "%' OR CT.apellido LIKE " + "'%" + dato + "%' OR CT.dni LIKE " + "'%" + dato + "%')AND(CT.borradoLogico = FALSE)AND(CT.cuerpoTecnico = TRUE)AND(CT.cuerpoTecnicoActivo = FALSE)";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    /**
     * Devuelve una lista de Arbitros fitlro DNI o Nombre o Apellido menos los
     * borrados
     */
    public List<PersonaAuxiliar> getArbitrosBDFiltro(String dato) {
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE (A.nombre LIKE " + "'%" + dato + "%' OR A.apellido LIKE " + "'%" + dato + "%' OR A.dni LIKE " + "'%" + dato + "%')AND(A.borradoLogico = FALSE)AND(A.arbitro = TRUE)";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    public List<PersonaAuxiliar> getArbitrosPorFecha(FechaTorneo unaFecha) {
        String unaConsulta = "SELECT A FROM PersonaAuxiliar A WHERE A.borradoLogico = FALSE AND A.arbitro = TRUE";
        List<PersonaAuxiliar> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        for (Partido unPartido : unaFecha.getPartidos()) {
            unaListaResultado.remove(unPartido.getUnArbitro1());
            unaListaResultado.remove(unPartido.getUnArbitro2());
            if (unPartido.getUnArbitro3() != null) {
                if (!unPartido.isBorradoLogico()) {
                    unaListaResultado.remove(unPartido.getUnArbitro3());
                }
            }
        }
        return unaListaResultado;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Socias">
    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora, String email, String telFijo, String telCelular) {
        new Socia(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora, email, telFijo, telCelular);
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

    public void modificarNumeroCamiseta(Socia unaSocia, String numeroCamistea) {
        unaSocia.setNumeroCamiseta(numeroCamistea);
        unaSocia.persistir(entityManager);
    }

    /**
     * Devuelve todas las Socias filtradas por Dato
     */
    public List<Socia> getSociasBDFiltro(String dato) {
        String unaConsulta = "SELECT S FROM Socia S WHERE (S.nombre LIKE " + "'%" + dato + "%' OR S.apellido LIKE " + "'%" + dato + "%' OR S.dni LIKE " + "'%" + dato + "%')and(S.borradoLogico = FALSE)";
        List<Socia> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Pases">
    public void crearPase(Socia unaSocia, Date fechaGeneracion, Equipo unEquipoActual, Equipo unEquipoNuevo, Deuda unaDeuda, boolean libreDeudaClub, boolean solicitudPase, String observacion) {
        Pase unPase = new Pase(this.entityManager, fechaGeneracion, unEquipoNuevo, unaDeuda, libreDeudaClub, solicitudPase, observacion);
        unaSocia.agregarPase(this.entityManager, unPase);
        if (unEquipoActual != null) {//Se controla que el primer pase cero a un equipo no elimine el equipo null
            unEquipoActual.quitarPlantel(this.entityManager, unaSocia);
        }
        unEquipoNuevo.agregarPlantel(this.entityManager, unaSocia);
    }

    public void eliminarUltimoPase(Pase ultimoPase, Socia unaSocia) {
        ultimoPase.getUnEquipo().quitarPlantel(this.entityManager, unaSocia);
        obtenerAnteUltimoEquipo(unaSocia).agregarPlantel(this.entityManager, unaSocia);
        ultimoPase.setBorradoLogico(true);
        ultimoPase.persistir(this.entityManager);
    }

    private Equipo obtenerAnteUltimoEquipo(Socia unaSocia) {
        Pase anteUltimoPaseValido = null, ultimoPaseValido = null;
        for (Pase aux : unaSocia.getPasesValidos()) {
            anteUltimoPaseValido = ultimoPaseValido;
            ultimoPaseValido = aux;
        }
        return anteUltimoPaseValido.getUnEquipo();
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Localidad">
    public void crearLocalidad(String nombre, String codPostal) {
        new Localidad(this.entityManager, nombre, codPostal);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ergometria">
    public void crearErgometria(Socia unaSocia, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        Ergometria unaErgometria = new Ergometria(this.entityManager, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
        unaSocia.agregarErgometria(this.entityManager, unaErgometria);
    }

    public void modificarErgometria(Ergometria unaErgometria, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios, boolean borradoLogico) {
        unaErgometria.setAprobado(aprobado);
        unaErgometria.setComentarios(comentarios);
        unaErgometria.setFechaCaducidad(fechaCaducidad);
        unaErgometria.setFechaRealizacion(fechaRealizacion);
        unaErgometria.setBorradoLogico(borradoLogico);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Estados">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tipo Estados">
    public void crearTipoEstado(String nombre) {
        new TipoEstado(this.entityManager, nombre);
    }

    public void modificarTipoEstado(TipoEstado unTipoEstado, String nombre, boolean borradoLogico) {
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
    // </editor-fold>
}
