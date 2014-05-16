package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.Arbitro;
import logicaNegocios.Club;
import logicaNegocios.CuerpoTecnico;
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
    public CuerpoTecnico buscarCuerpoTecnicoBD(Long dni) {
        CuerpoTecnico resultado;
        Query traerCuerpoTecnico = this.entityManager.createQuery("SELECT A FROM CuerpoTecnico A WHERE A.dni = " + dni);
        resultado = (CuerpoTecnico) traerCuerpoTecnico.getResultList();
        return resultado;
    }

    public void crearCuerpoTecnico(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, boolean activo) {
        CuerpoTecnico unCuerpoTecnico = new CuerpoTecnico(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, activo);
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
//------------------------------FIN CUERPO TECNICO------------------------------

//------------------------------ARBITROS----------------------------------------   
    public Arbitro buscarArbitroBD(Long dni) {
        Arbitro resultado;
        Query traerArbitro = this.entityManager.createQuery("SELECT A FROM Arbitro A WHERE A.dni = " + dni);
        resultado = (Arbitro) traerArbitro.getResultList();
        return resultado;
    }

    public void crearArbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso) {
        Arbitro unArbitro = new Arbitro(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso);
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
//------------------------------FIN ARBITROS------------------------------------

//------------------------------CLUBES------------------------------------------   
    public Club buscarClubBD(Long id) {
        Club resultado;
        Query traerClub = this.entityManager.createQuery("SELECT A FROM Club A WHERE A.idClub = " + id);
        resultado = (Club) traerClub.getResultList();
        return resultado;
    }

    public void crearClub(Long idClub, String nombre, String nombrePresidente, Localidad unaLocalidad) {
        Club unClub = new Club(this.entityManager, nombre, nombrePresidente, unaLocalidad);
    }

    public void modificarClub(Club unClub, Long idClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad, boolean borradoLogico) {
        unClub.setIdClub(idClub);
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
//------------------------------FIN CLUBES--------------------------------------

//------------------------------SOCIAS------------------------------------------   
    public List<Object[]> buscarSociaConEquipoBD(String tipo, String dato) {

        //String unaConsulta = "SELECT S, E.nombre FROM Socia S, Equipo E JOIN E.plantel p WHERE p.dni = S.dni and S." + tipo + " LIKE " + "'%" + dato + "%'" ;
        String unaConsulta = "SELECT S, S.nombre FROM Socia S WHERE (S.nombre LIKE " + "'%" + dato + "%' OR S.apellido LIKE " + "'%" + dato + "%' OR S.dni LIKE " + "'%" + dato + "%')";
        //String unaConsulta = "SELECT S, E.nombre FROM Socia S, Equipo E JOIN E.plantel p WHERE S.dni = p.dni AND (S.nombre LIKE " + "'%" + dato + "%' OR S.apellido LIKE " + "'%" + dato + "%' OR S.dni LIKE " + "'%" + dato + "%')";
        //unaConsulta += " JOIN E.plantel p WHERE p.dni = S.dni";
        List<Object[]> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    //-------NO CREO QUE ANDE TODAVIA jeje--------------------------------------

    public Socia buscarSociaBD(Long dni) {
        Socia resultado;
        Query traerSocia = this.entityManager.createQuery("SELECT A FROM Club A WHERE A.dni = " + dni);
        resultado = (Socia) traerSocia.getResultList();
        return resultado;
    }

    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora) {
        Socia unaSocia = new Socia(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora);
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
//------------------------------FIN SOCIAS--------------------------------------

//-----------------------------------PASES--------------------------------------
    public Pase buscarPaseBD(EntityManager entityManager, Long id) {
        Pase resultado;
        Query traerPase = entityManager.createQuery("SELECT auxP FROM Pase auxP WHERE auxP.id = " + id);
        resultado = (Pase) traerPase.getResultList();
        return resultado;
    }

    public void crearPase(Socia unaSocia, Date fecha, double monto, Equipo unEquipo) {
        Pase unPase = new Pase(this.entityManager, fecha, monto, unEquipo);
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
//---------------------------------FIN PASES------------------------------------

//------------------------------LOCALIDADES-------------------------------------   
    public Localidad buscarLocalidBD(Long id) {
        Localidad resultado;
        Query traerLocalidad = this.entityManager.createQuery("SELECT A FROM Localidad A WHERE A.idLocalidad = " + id);
        resultado = (Localidad) traerLocalidad.getResultList();
        return resultado;
    }

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

    public Vector<Localidad> getLocalidades() {
        Vector<Localidad> vLocalidades = new Vector<Localidad>();
        Query traerLocalidades = this.entityManager.createQuery("SELECT A FROM Localidad A");
        List<Localidad> unaListaResultado = traerLocalidades.getResultList();
        for (Localidad unaLocalidad : unaListaResultado) {
            vLocalidades.add(unaLocalidad);
        }
        return vLocalidades;
    }
//------------------------------FIN LOCALIDADES---------------------------------

//-----------------------------------ERGOMETRIA---------------------------------
    public Ergometria buscarErgometriaBD(Long id) {
        Ergometria resultado;
        Query traerErgometria = entityManager.createQuery("SELECT auxE FROM Ergometria auxE WHERE auxE.id = " + id);
        resultado = (Ergometria) traerErgometria.getResultList();
        return resultado;
    }

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
//---------------------------------FIN ERGOMETRIAS---------------------------------

//-----------------------------------ESTADOS-------------------------------------
    public Estado buscarEstadoBD(EntityManager entityManager, Long id) {
        Estado resultado;
        Query traerEstado = entityManager.createQuery("SELECT auxE FROM Estado auxE WHERE auxE.id = " + id);
        resultado = (Estado) traerEstado.getResultList();
        return resultado;
    }

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
//-------------------------------FIN TIPO ESTADO--------------------------------
}
