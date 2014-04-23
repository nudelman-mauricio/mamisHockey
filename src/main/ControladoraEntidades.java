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
        
        //CONSULTA PARA CARGAR TODAS LOS ARBITROS DE LA BD
        Query traerArbitros = em.createQuery("SELECT auxH FROM Arbitro auxH");
        this.arbitros = new TreeSet(traerArbitros.getResultList());
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
    
    //------------------------------ARBITROS------------------------------------    
    public Arbitro buscarArbitro(Long dni) {
        Arbitro resultado = null;
        for (Arbitro aux : arbitros) {
            if (Objects.equals(aux.getDni(), dni)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearArbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso, boolean borradoLogico, String fotocopiaDni) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Arbitro unArbitro = new Arbitro(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, borradoLogico, fotocopiaDni);
            entityManager.persist(unArbitro);
            this.arbitros.add(unArbitro);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear Arbitro" + e.getMessage());
            tx.rollback();
        }
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
            unArbitro.setBorradoLogico(false);
            entityManager.persist(unArbitro);
            arbitros.remove(unArbitro); //ME PARECE QUE ESTA LINEA NO VA (BORRADO LOGICO)
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error en Eliminar Arbitro" + e.getMessage());
            tx.rollback();
        }
    }
    //-------------------------------FIN TORNEOS--------------------------------
}
