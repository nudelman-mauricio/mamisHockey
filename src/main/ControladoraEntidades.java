package main;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import logicaNegocios.Arbitro;
import logicaNegocios.Club;
import logicaNegocios.Localidad;
import logicaNegocios.Socia;
import logicaNegocios.CuerpoTecnico;

public class ControladoraEntidades {

    private Collection<CuerpoTecnico> cuerpoTecnicos;
    private Collection<Arbitro> arbitros;
    private Collection<Club> clubes;
    private Collection<Socia> socias;
    private Collection<Localidad> localidades;
    private final EntityManager entityManager;

    public ControladoraEntidades(EntityManager em) {
        this.entityManager = em;

        //CONSULTA PARA CARGAR TODAS LOS cuerpoTecnico DE LA BD
        Query traerCuerpoTecnico = em.createQuery("SELECT auxH FROM CuerpoTecnico auxH");
        this.cuerpoTecnicos = new TreeSet(traerCuerpoTecnico.getResultList());

        //CONSULTA PARA CARGAR TODAS LOS ARBITROS DE LA BD
        Query traerArbitros = em.createQuery("SELECT auxH FROM Arbitro auxH");
        this.arbitros = new TreeSet(traerArbitros.getResultList());

        //CONSULTA PARA CARGAR TODAS LOS CLUBES DE LA BD
        Query traerClubes = em.createQuery("SELECT auxH FROM Club auxH");
        this.clubes = new TreeSet(traerClubes.getResultList());

        //CONSULTA PARA CARGAR TODAS LOS SOCIAS DE LA BD
        Query traerSocias = em.createQuery("SELECT auxH FROM Socia auxH");
        this.socias = new TreeSet(traerSocias.getResultList());

        //CONSULTA PARA CARGAR TODAS LOS LOCALIDADES DE LA BD
        Query traerLocalidades = em.createQuery("SELECT auxH FROM Localidad auxH");
        this.localidades = new TreeSet(traerLocalidades.getResultList());
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public Collection<CuerpoTecnico> getCuerpoTecnicos() {
        return this.cuerpoTecnicos;
    }

    public void setCuerpoTecnicos(Collection<CuerpoTecnico> cuerpoTecnicos) {
        this.cuerpoTecnicos = cuerpoTecnicos;
    }

    public Collection<Arbitro> getArbitros() {
        return this.arbitros;
    }

    public void setArbitros(Collection<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }

    public Collection<Club> getClubes() {
        return this.clubes;
    }

    public void setClubes(Collection<Club> clubes) {
        this.clubes = clubes;
    }

    public Collection<Socia> getSocias() {
        return this.socias;
    }

    public void setSocias(Collection<Socia> socias) {
        this.socias = socias;
    }

    public Collection<Localidad> getLocalidades() {
        return this.localidades;
    }

    public void setLocalidades(Collection<Localidad> localidades) {
        this.localidades = localidades;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

//------------------------------CUERPO TECNICO----------------------------------   
    public CuerpoTecnico buscarCuerpoTecnicoBD(Long dni) {
        CuerpoTecnico resultado;
        Query traerCuerpoTecnico = this.entityManager.createQuery("SELECT A FROM CuerpoTecnico A WHERE A.dni = " + dni);
        resultado = (CuerpoTecnico) traerCuerpoTecnico.getResultList();
        return resultado;
    }

    public CuerpoTecnico buscarCuerpoTecnico(Long dni) {
        CuerpoTecnico resultado = null;
        for (CuerpoTecnico aux : cuerpoTecnicos) {
            if (Objects.equals(aux.getDni(), dni)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearCuerpoTecnico(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, boolean activo) {
        CuerpoTecnico unCuerpoTecnico = new CuerpoTecnico(entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, activo);
        this.cuerpoTecnicos.add(unCuerpoTecnico);
            
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

        unCuerpoTecnico.persistir(entityManager);
    }

    public void eliminarCuerpoTecnico(CuerpoTecnico unCuerpoTecnico) {
        unCuerpoTecnico.setBorradoLogico(true);
        unCuerpoTecnico.persistir(entityManager);            
    }
//------------------------------FIN CUERPO TECNICO------------------------------

//------------------------------ARBITROS----------------------------------------   
    public Arbitro buscarArbitroBD(Long dni) {
        Arbitro resultado;
        Query traerArbitro = this.entityManager.createQuery("SELECT A FROM Arbitro A WHERE A.dni = " + dni);
        resultado = (Arbitro) traerArbitro.getResultList();
        return resultado;
    }

    public Arbitro buscarArbitro(Long dni) {
        Arbitro resultado = null;
        for (Arbitro aux : arbitros) {
            if (Objects.equals(aux.getDni(), dni)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearArbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso) {
        Arbitro unArbitro = new Arbitro(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso);
        this.arbitros.add(unArbitro);
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

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unArbitro);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar Arbitro" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarArbitro(Arbitro unArbitro) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unArbitro.setBorradoLogico(true);
            entityManager.persist(unArbitro);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error en Eliminar Arbitro" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN ARBITROS------------------------------------

//------------------------------CLUBES------------------------------------------   
    public Club buscarClubBD(Long id) {
        Club resultado;
        Query traerClub = this.entityManager.createQuery("SELECT A FROM Club A WHERE A.idClub = " + id);
        resultado = (Club) traerClub.getResultList();
        return resultado;
    }

    public Club buscarClub(Long idClub) {
        Club resultado = null;
        for (Club aux : clubes) {
            if (Objects.equals(aux.getIdClub(), idClub)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearClub(Long idClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad) {
        Club unClub = new Club(this.entityManager, nombre, logo, nombrePresidente, unaLocalidad);
        this.clubes.add(unClub);
    }

    public void modificarClub(Club unClub, Long idClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad, boolean borradoLogico) {
        unClub.setIdClub(idClub);
        unClub.setNombre(nombre);
        unClub.setLogo(logo);
        unClub.setNombrePresidente(nombrePresidente);
        unClub.setUnaLocalidad(unaLocalidad);
        unClub.setBorradoLogico(borradoLogico);
        unClub.persistir(entityManager);
    }

    public void eliminarClub(Club unClub) {
        unClub.setBorradoLogico(true);
        unClub.persistir(entityManager);
    }
//------------------------------FIN CLUBES--------------------------------------

//------------------------------SOCIAS------------------------------------------   
    public Socia buscarSociaBD(Long dni) {
        Socia resultado;
        Query traerSocia = this.entityManager.createQuery("SELECT A FROM Club A WHERE A.dni = " + dni);
        resultado = (Socia) traerSocia.getResultList();
        return resultado;
    }

    public Socia buscarSocia(Long dni) {
        Socia resultado = null;
        for (Socia aux : socias) {
            if (Objects.equals(aux.getDni(), dni)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora) {
            Socia unaSocia = new Socia(entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, fotoCarnet, exJugadora);
            this.socias.add(unaSocia);            
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

        unaSocia.persistir(entityManager);
    }

    public void eliminarSocia(Socia unaSocia) {
            unaSocia.setBorradoLogico(true);
            unaSocia.persistir(entityManager);            
    }
//------------------------------FIN SOCIAS--------------------------------------

//------------------------------LOCALIDADES-------------------------------------   
    public Localidad buscarLocalidBD(Long id) {
        Localidad resultado;
        Query traerSocia = this.entityManager.createQuery("SELECT A FROM Localidad A WHERE A.idLocalidad = " + id);
        resultado = (Localidad) traerSocia.getResultList();
        return resultado;
    }

    public Localidad buscarLocalidad(Long id) {
        Localidad resultado = null;
        for (Localidad aux : localidades) {
            if (Objects.equals(aux.getIdLocalidad(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearLocalidad(String nombre, String codPostal) {
            Localidad unaLocalidad = new Localidad(entityManager,nombre, codPostal);            
            this.localidades.add(unaLocalidad);            
    }

    public void modificarLocalidad(Localidad unaLocalidad, String nombre, String codPostal, boolean borradoLogico) {
        unaLocalidad.setNombre(nombre);
        unaLocalidad.setCodPostal(codPostal);
        unaLocalidad.setBorradoLogico(borradoLogico);
        
        unaLocalidad.persistir(entityManager);        
    }

    public void eliminarLocalidad(Localidad unaLocalidad) {
            unaLocalidad.setBorradoLogico(true);
            unaLocalidad.persistir(entityManager);            
    }
//------------------------------FIN LOCALIDADES---------------------------------
}
