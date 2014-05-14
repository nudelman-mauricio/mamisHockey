package logicaNegocios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;

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

    public Socia() {

    }

    public Socia(EntityManager entityManager, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso);
        this.fotoCarnet = fotoCarnet;
        this.exJugadora = exJugadora;
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

//-----------------------------------ERGOMETRIA---------------------------------
    public void agregarErgometria(EntityManager entityManager, Ergometria unaErgometria) {
        this.ergometrias.add(unaErgometria);
        this.persistir(entityManager);
    }

    public void quitarErgometria(EntityManager entityManager, Ergometria unaErgometria) {
        this.ergometrias.remove(unaErgometria);
        this.persistir(entityManager);
    }
//---------------------------------FIN ERGOMETRIAS---------------------------------

//-----------------------------------TARJETAS-----------------------------------
    public void agregarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.tarjetas.add(unaTarjeta);
        this.persistir(entityManager);
    }

    public void quitarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.tarjetas.remove(unaTarjeta);
        this.persistir(entityManager);
    }
//---------------------------------FIN TARJETAS---------------------------------

//-----------------------------------PASES-----------------------------------
    public void agregarPase(EntityManager entityManager, Pase unPase) {
        this.pases.add(unPase);
        this.persistir(entityManager);
    }

    public void quitarPase(EntityManager entityManager, Pase unPase) {
        this.pases.remove(unPase);
        this.persistir(entityManager);
    }
//---------------------------------FIN PASES---------------------------------

//-----------------------------------DEUDAS-------------------------------------
    public void agregarDeuda(EntityManager entityManager, Deuda unaDeuda) {
        this.deudas.add(unaDeuda);
        this.persistir(entityManager);
    }

    public void quitarDeuda(EntityManager entityManager, Deuda unaDeuda) {
        this.deudas.remove(unaDeuda);
        this.persistir(entityManager);
    }
//---------------------------------FIN DEUDAS-----------------------------------

//-----------------------------------GOLES--------------------------------------
    public void agregarGol(EntityManager entityManager, Gol unGol) {
        this.goles.add(unGol);
        this.persistir(entityManager);
    }

    public void quitarGol(EntityManager entityManager, Gol unGol) {
        this.goles.remove(unGol);
        this.persistir(entityManager);
    }
//---------------------------------FIN GOLES------------------------------------

//-----------------------------------ESTADOS-------------------------------------
    public void agregarEstado(EntityManager entityManager, Estado unEstado) {
        this.estados.add(unEstado);
        this.persistir(entityManager);
    }

    public void quitarEstado(EntityManager entityManager, Estado unEstado) {
        this.estados.remove(unEstado);
        this.persistir(entityManager);
    }
//---------------------------------FIN ESTADOS----------------------------------
}