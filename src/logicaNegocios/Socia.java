package logicaNegocios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OneToMany;
import javax.persistence.Query;

@Entity
public class Socia extends Persona implements Serializable {

    @Basic
    private String numeroCamiseta;

    @Basic
    private String fotoCarnet;

    @OneToMany(targetEntity = Ergometria.class)
    private Collection<Ergometria> ergometrias;

    @Basic
    private boolean exJugadora;

    @OneToMany(targetEntity = Tarjeta.class)
    private Collection<Tarjeta> tarjetas;

    @OneToMany(targetEntity = Pase.class)
    private Collection<Pase> pases;

    @OneToMany(targetEntity = Deuda.class)
    private Collection<Deuda> deudas;

    @OneToMany(targetEntity = Gol.class)
    private Collection<Gol> goles;

    @OneToMany(targetEntity = Estado.class)
    private Collection<Estado> estados;
    
    private EntityManager entityManager;

    public Socia() {

    }

    public Socia(EntityManager entityManager, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso);
        this.fotoCarnet = fotoCarnet;
        this.exJugadora = exJugadora;
        this.entityManager = entityManager;
        this.persistir(entityManager);
    }
    
//------------------------------ GETERS Y SETERS -------------------------------
    public String getNumeroCamiseta() {
        return this.numeroCamiseta;
    }

    public void setNumeroCamiseta(String numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public String getFotoCarnet() {
        return this.fotoCarnet;
    }

    public void setFotoCarnet(String fotoCarnet) {
        this.fotoCarnet = fotoCarnet;
    }

    public Collection<Ergometria> getErgometrias() {
        return this.ergometrias;
    }

    public void setErgometrias(Collection<Ergometria> ergometrias) {
        this.ergometrias = ergometrias;
    }

    public boolean isExJugadora() {
        return this.exJugadora;
    }

    public void setExJugadora(boolean exJugadora) {
        this.exJugadora = exJugadora;
    }

    public Collection<Tarjeta> getTarjetas() {
        return this.tarjetas;
    }

    public void setTarjetas(Collection<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Collection<Pase> getPases() {
        return this.pases;
    }

    public void setPases(Collection<Pase> pases) {
        this.pases = pases;
    }

    public Collection<Deuda> getDeudas() {
        return this.deudas;
    }

    public void setDeudas(Collection<Deuda> deudas) {
        this.deudas = deudas;
    }

    public Collection<Gol> getGoles() {
        return this.goles;
    }

    public void setGoles(Collection<Gol> goles) {
        this.goles = goles;
    }

    public Collection<Estado> getEstados() {
        return this.estados;
    }

    public void setEstados(Collection<Estado> estados) {
        this.estados = estados;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------
 
//-----------------------------------ERGOMETRIA-----------------------------------
    public Ergometria buscarErgometriaBD(Long id) {
        Ergometria resultado;
        Query traerErgometria = this.entityManager.createQuery("SELECT auxE FROM Ergometria auxE WHERE auxE.id = " + id);
        resultado = (Ergometria) traerErgometria.getResultList();
        return resultado;
    }

    public Ergometria buscarErgometria(Long id) {
        Ergometria resultado = null;
        for (Ergometria aux : ergometrias) {
            if (Objects.equals(aux.getIdErgometria(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearErgometria(EntityManager entityManager, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        Ergometria unaErgometria = new Ergometria(entityManager, fechaCaducidad, fechaRealizacion, aprobado, comentarios);
        this.ergometrias.add(unaErgometria);            
    }

    public void modificarPartido(Ergometria unaErgometria, EntityManager entityManager, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        unaErgometria.setAprobado(aprobado);
        unaErgometria.setComentarios(comentarios);
        unaErgometria.setFechaCaducidad(fechaCaducidad);
        unaErgometria.setFechaRealizacion(fechaRealizacion);
        
        unaErgometria.persistir(entityManager);        
    }

    public void eliminarErgometria(Ergometria unaErgometria) {
       //unaErgometria.setBorradoLogico(true);
      // unPartido.persistir(entityManager);
    }
//---------------------------------FIN PARTIDOS---------------------------------
    
 }
