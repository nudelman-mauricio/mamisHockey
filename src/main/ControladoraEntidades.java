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
        this.construirTiposEstados();
        this.construirLocalidades();
    }

    // <editor-fold defaultstate="collapsed" desc="Persona Auxiliar">
    public void crearPersonaAuxiliar(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico, boolean plantaPermanente) {
        new PersonaAuxiliar(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular, arbitro, cuerpoTecnico, plantaPermanente);
    }

    public void modificarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean arbitro, boolean cuerpoTecnico, boolean plantaPermanente, boolean borradoLogico) {
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
        unaPersonaAuxiliar.setArbitro(arbitro);
        unaPersonaAuxiliar.setCuerpoTecnico(cuerpoTecnico);
        unaPersonaAuxiliar.setPlantaPermanente(plantaPermanente);
        unaPersonaAuxiliar.setBorradoLogico(borradoLogico);
        unaPersonaAuxiliar.persistir(this.entityManager);
    }

    public void agregarActaCompromiso(PersonaAuxiliar unaPersonaAuxiliar, Date unaFecha) {
        unaPersonaAuxiliar.agregarActaCompromiso(entityManager, unaFecha);
    }

    public void quitarActaCompromiso(PersonaAuxiliar unaPersonaAuxiliar, Date unaFecha) {
        unaPersonaAuxiliar.quitarActaCompromiso(entityManager, unaFecha);
    }

    public void eliminarPersonaAuxiliar(PersonaAuxiliar unaPersonaAuxiliar) {
        unaPersonaAuxiliar.setBorradoLogico(true);
        unaPersonaAuxiliar.persistir(this.entityManager);
    }

    public void marcarCuerpoTecnicoActivo(PersonaAuxiliar unaPersonaAuxiliar, boolean activo) {
        unaPersonaAuxiliar.setCuerpoTecnicoActivo(activo);
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
    public Socia crearSocia(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, boolean exJugadora, String email, String telFijo, String telCelular, byte[] fotoCarnet) {
        return new Socia(this.entityManager, dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, exJugadora, email, telFijo, telCelular,fotoCarnet);
    }

    public void modificarSocia(Socia unaSocia, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, byte[] fotoCarnet, boolean exJugadora) {
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
        unaSocia.setExJugadora(exJugadora);
        unaSocia.setFotoCarnet(fotoCarnet);
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
        String otraConsulta = "SELECT E FROM Equipo E WHERE (E.nombre LIKE " + "'%" + dato + "%')and(E.borradoLogico = FALSE)";
        List<Equipo> unaListaEquipos = this.entityManager.createQuery(otraConsulta).getResultList();
        if (unaListaEquipos != null) {
            for (Equipo unEquipo : unaListaEquipos) {
                for (Socia unaSocia : unEquipo.getPlantel()) {
                    unaListaResultado.add(unaSocia);
                }
            }
        }
        return unaListaResultado;
    }
    
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Pases">
    public Pase crearPase(Socia unaSocia, Date fechaGeneracion, Equipo unEquipoActual, Equipo unEquipoNuevo, Deuda unaDeuda, boolean libreDeudaClub, boolean solicitudPase, String observacion) {
        Pase unPase = new Pase(this.entityManager, fechaGeneracion, unEquipoNuevo, unaDeuda, libreDeudaClub, solicitudPase, observacion);
        unaSocia.agregarPase(this.entityManager, unPase);
        if (unEquipoActual != null) {//Se controla que el primer pase cero a un equipo no elimine el equipo null
            unEquipoActual.quitarPlantel(this.entityManager, unaSocia);
        }
        if (unEquipoNuevo != null) { //Pase libre
            unEquipoNuevo.agregarPlantel(this.entityManager, unaSocia);
        }
        return unPase;
    }

    public void modificarPase(Pase unPase, Socia unaSocia, Equipo unEquipoNuevo) {
        unPase.modificarEquipoDestino(entityManager, unEquipoNuevo);
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
    private void construirLocalidades() {
        if (this.getConceptoDeportivoBD("2 de Mayo") == null) {
            this.crearLocalidad("2 de Mayo", "3364");
        }
        if (this.getConceptoDeportivoBD("25 de Mayo") == null) {
            this.crearLocalidad("25 de Mayo", "3363");
        }
        if (this.getConceptoDeportivoBD("9 de Julio") == null) {
            this.crearLocalidad("9 de Julio", "3363");
        }
        if (this.getConceptoDeportivoBD("Alba Posse") == null) {
            this.crearLocalidad("Alba Posse", "3363");
        }
        if (this.getConceptoDeportivoBD("Apóstoles") == null) {
            this.crearLocalidad("Apóstoles", "3350");
        }
        if (this.getConceptoDeportivoBD("Aristóbulo del Valle") == null) {
            this.crearLocalidad("Aristóbulo del Valle", "3364");
        }
        if (this.getConceptoDeportivoBD("Bernardo de Irigoyen") == null) {
            this.crearLocalidad("Bernardo de Irigoyen", "3366");
        }
        if (this.getConceptoDeportivoBD("Bonpland") == null) {
            this.crearLocalidad("Bonpland", "3317");
        }
        if (this.getConceptoDeportivoBD("Campo Grande") == null) {
            this.crearLocalidad("Campo Grande", "3362");
        }
        if (this.getConceptoDeportivoBD("Campo Viera") == null) {
            this.crearLocalidad("Campo Viera", "3362");
        }
        if (this.getConceptoDeportivoBD("Candelaria") == null) {
            this.crearLocalidad("Candelaria", "3308");
        }
        if (this.getConceptoDeportivoBD("Capioví") == null) {
            this.crearLocalidad("Capioví", "3332");
        }
        if (this.getConceptoDeportivoBD("Cerro Azul") == null) {
            this.crearLocalidad("Cerro Azul", "3313");
        }
        if (this.getConceptoDeportivoBD("Cerro Corá") == null) {
            this.crearLocalidad("Cerro Corá", "3309");
        }
        if (this.getConceptoDeportivoBD("Colonia Alberdi") == null) {
            this.crearLocalidad("Colonia Alberdi", "3311");
        }
        if (this.getConceptoDeportivoBD("Colonia Aurora") == null) {
            this.crearLocalidad("Colonia Aurora", "3363");
        }
        if (this.getConceptoDeportivoBD("Caraguatay") == null) {
            this.crearLocalidad("Caraguatay", "3386");
        }
        if (this.getConceptoDeportivoBD("Colonia Delicia") == null) {
            this.crearLocalidad("Colonia Delicia", "3382");
        }
        if (this.getConceptoDeportivoBD("Colonia Polana") == null) {
            this.crearLocalidad("Colonia Polana", "3326");
        }
        if (this.getConceptoDeportivoBD("Concepción de la Sierra") == null) {
            this.crearLocalidad("Concepción de la Sierra", "3355");
        }
        if (this.getConceptoDeportivoBD("Corpus") == null) {
            this.crearLocalidad("Corpus", "3327");
        }
        if (this.getConceptoDeportivoBD("Comandante Andresito") == null) {
            this.crearLocalidad("Comandante Andresito", "3364");
        }
        if (this.getConceptoDeportivoBD("Dos Arroyos") == null) {
            this.crearLocalidad("Dos Arroyos", "3315");
        }
        if (this.getConceptoDeportivoBD("El Alcázar") == null) {
            this.crearLocalidad("El Alcázar", "3384");
        }
        if (this.getConceptoDeportivoBD("El Soberbio") == null) {
            this.crearLocalidad("El Soberbio", "3364");
        }
        if (this.getConceptoDeportivoBD("El Dorado") == null) {
            this.crearLocalidad("El Dorado", "3382");
        }
        if (this.getConceptoDeportivoBD("Garuhapé") == null) {
            this.crearLocalidad("Garuhapé", "3334");
        }
        if (this.getConceptoDeportivoBD("Garupá") == null) {
            this.crearLocalidad("Garupá", "3304");
        }
        if (this.getConceptoDeportivoBD("Gobernador Roca") == null) {
            this.crearLocalidad("Gobernador Roca", "3324");
        }
        if (this.getConceptoDeportivoBD("Guaraní") == null) {
            this.crearLocalidad("Guaraní", "3361");
        }
        if (this.getConceptoDeportivoBD("Itacaruaré") == null) {
            this.crearLocalidad("Itacaruaré", "3353");
        }
        if (this.getConceptoDeportivoBD("Jardín América") == null) {
            this.crearLocalidad("Jardín América", "3328");
        }
        if (this.getConceptoDeportivoBD("Leandro N Alem") == null) {
            this.crearLocalidad("Leandro N Alem", "3315");
        }
        if (this.getConceptoDeportivoBD("Loreto") == null) {
            this.crearLocalidad("Loreto", "3316");
        }
        if (this.getConceptoDeportivoBD("Miguel Lanús") == null) {
            this.crearLocalidad("Miguel Lanús", "3304");
        }
        if (this.getConceptoDeportivoBD("Mojón Grande") == null) {
            this.crearLocalidad("Mojón Grande", "3315");
        }
        if (this.getConceptoDeportivoBD("Montecarlo") == null) {
            this.crearLocalidad("Montecarlo", "3384");
        }
        if (this.getConceptoDeportivoBD("Oberá") == null) {
            this.crearLocalidad("Oberá", "3360");
        }
        if (this.getConceptoDeportivoBD("Panambí") == null) {
            this.crearLocalidad("Panambí", "3361");
        }
        if (this.getConceptoDeportivoBD("Posadas") == null) {
            this.crearLocalidad("Posadas", "3300");
        }
        if (this.getConceptoDeportivoBD("Puerto Esperanza") == null) {
            this.crearLocalidad("Puerto Esperanza", "3378");
        }
        if (this.getConceptoDeportivoBD("Puerto Iguazú") == null) {
            this.crearLocalidad("Puerto Iguazú", "3370");
        }
        if (this.getConceptoDeportivoBD("Puerto Libertad") == null) {
            this.crearLocalidad("Puerto Libertad", "3370");
        }
        if (this.getConceptoDeportivoBD("Puerto Piray") == null) {
            this.crearLocalidad("Puerto Piray", "3381");
        }
        if (this.getConceptoDeportivoBD("Puerto Rico") == null) {
            this.crearLocalidad("Puerto Rico", "3334");
        }
        if (this.getConceptoDeportivoBD("Ruiz de Montoya") == null) {
            this.crearLocalidad("Ruiz de Montoya", "3334");
        }
        if (this.getConceptoDeportivoBD("San Antonio") == null) {
            this.crearLocalidad("San Antonio", "3366");
        }
        if (this.getConceptoDeportivoBD("San Ignacio") == null) {
            this.crearLocalidad("San Ignacio", "3322");
        }
        if (this.getConceptoDeportivoBD("San Javier") == null) {
            this.crearLocalidad("San Javier", "3357");
        }
        if (this.getConceptoDeportivoBD("San José") == null) {
            this.crearLocalidad("San José", "3306");
        }
        if (this.getConceptoDeportivoBD("San Pedro") == null) {
            this.crearLocalidad("San Pedro", "3364");
        }
        if (this.getConceptoDeportivoBD("San Vicente") == null) {
            this.crearLocalidad("San Vicente", "3364");
        }
        if (this.getConceptoDeportivoBD("Santa Ana") == null) {
            this.crearLocalidad("Santa Ana", "3316");
        }
        if (this.getConceptoDeportivoBD("Santa Rita") == null) {
            this.crearLocalidad("Santa Rita", "3363");
        }
        if (this.getConceptoDeportivoBD("Santo Pipó") == null) {
            this.crearLocalidad("Santo Pipó", "3326");
        }
        if (this.getConceptoDeportivoBD("Salto Encantado") == null) {
            this.crearLocalidad("Salto Encantado", "3364");
        }
        if (this.getConceptoDeportivoBD("Wanda") == null) {
            this.crearLocalidad("Wanda", "3376");
        }
    }

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
     * Devuelve unConceptoDeportivo por CONCEPTO
     */
    public Localidad getConceptoDeportivoBD(String nombre) {
        List<Localidad> unaListaResultado = this.entityManager.createQuery("SELECT l FROM Localidad l WHERE l.nombre LIKE '" + nombre + "'").getResultList();
        if (unaListaResultado.isEmpty()) {
            return null;
        } else {
            return unaListaResultado.get(0);
        }
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
    private void construirTiposEstados() {
        if (getTipoEstadoBD("Socia") == null) {
            this.crearTipoEstado("Socia");
        }
        if (getTipoEstadoBD("Jugadora") == null) {
            this.crearTipoEstado("Jugadora");
        }
        if (getTipoEstadoBD("Licencia") == null) {
            this.crearTipoEstado("Licencia");
        }
        if (getTipoEstadoBD("Baja") == null) {
            this.crearTipoEstado("Baja");
        }
        if (getTipoEstadoBD("Baja por Mora") == null) {
            this.crearTipoEstado("Baja por Mora");
        }
    }

    public TipoEstado crearTipoEstado(String nombre) {
        return new TipoEstado(this.entityManager, nombre);
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
     * Devuelve unTipoEstado filtrado por tipo
     */
    public TipoEstado getTipoEstadoBD(String tipo) {
        Query traerTipoEstado = this.entityManager.createQuery("SELECT te FROM TipoEstado te WHERE te.borradoLogico = FALSE AND te.nombre LIKE '" + tipo + "'");
        List<TipoEstado> unaListaResultado = traerTipoEstado.getResultList();
        if (unaListaResultado.isEmpty()) {
            return null;
        } else {
            return unaListaResultado.get(0);
        }
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
