package logicaNegocios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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

//-----------------------------------ERGOMETRIA-----------------------------------
    public Ergometria buscarErgometriaBD(EntityManager entityManager, Long id) {
        Ergometria resultado;
        Query traerErgometria = entityManager.createQuery("SELECT auxE FROM Ergometria auxE WHERE auxE.id = " + id);
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
        this.persistir(entityManager);
    }

    public void modificarPartido(EntityManager entityManager, Ergometria unaErgometria, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        unaErgometria.setAprobado(aprobado);
        unaErgometria.setComentarios(comentarios);
        unaErgometria.setFechaCaducidad(fechaCaducidad);
        unaErgometria.setFechaRealizacion(fechaRealizacion);
        unaErgometria.persistir(entityManager);
    }

    public void eliminarErgometria(EntityManager entityManager, Ergometria unaErgometria) {
        unaErgometria.setBorradoLogico(true);
        unaErgometria.persistir(entityManager);
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
    public Pase buscarPaseBD(EntityManager entityManager, Long id) {
        Pase resultado;
        Query traerPase = entityManager.createQuery("SELECT auxP FROM Pase auxP WHERE auxP.id = " + id);
        resultado = (Pase) traerPase.getResultList();
        return resultado;
    }

    public Pase buscarPase(Long id) {
        Pase resultado = null;
        for (Pase aux : pases) {
            if (Objects.equals(aux.getIdPase(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearPase(EntityManager entityManager, Date fecha, double monto, Equipo unEquipo) {
        Pase unPase = new Pase(entityManager, fecha, monto, unEquipo);
        this.pases.add(unPase);
        this.persistir(entityManager);
    }

    public void modificarPase(EntityManager entityManager, Pase unPase, Date fecha, double monto, Equipo unEquipo, boolean borradoLogico) {
        unPase.setFecha(fecha);
        unPase.setMonto(monto);
        unPase.setUnEquipo(unEquipo);
        unPase.setBorradoLogico(borradoLogico);
        unPase.persistir(entityManager);
    }

    public void eliminarunPase(EntityManager entityManager, Pase unPase) {
        unPase.setBorradoLogico(true);
        unPase.persistir(entityManager);
    }
//---------------------------------FIN PASES---------------------------------

//-----------------------------------DEUDAS-------------------------------------
    public Deuda buscarDeudaBD(EntityManager entityManager, Long id) {
        Deuda resultado;
        Query traerDeuda = entityManager.createQuery("SELECT auxD FROM Deuda auxD WHERE auxD.id = " + id);
        resultado = (Deuda) traerDeuda.getResultList();
        return resultado;
    }

    public Deuda buscarDeuda(Long id) {
        Deuda resultado = null;
        for (Deuda aux : deudas) {
            if (Objects.equals(aux.getIdDeuda(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearDeuda(EntityManager entityManager, Date fecha, double monto, boolean saldado, ConceptoDeportivo unConceptoDeportivo, String observacion) {
        Deuda unaDeuda = new Deuda(entityManager, fecha, monto, saldado, unConceptoDeportivo, observacion);
        this.deudas.add(unaDeuda);
        this.persistir(entityManager);
    }

    public void modificarDeuda(EntityManager entityManager, Deuda unaDeuda, Date fecha, double monto, boolean saldado, ConceptoDeportivo unConceptoDeportivo, String observacion, boolean borradoLogico) {
        unaDeuda.setFecha(fecha);
        unaDeuda.setMonto(monto);
        unaDeuda.setSaldado(saldado);
        unaDeuda.setUnConceptoDeportivo(unConceptoDeportivo);
        unaDeuda.setObservacion(observacion);
        unaDeuda.setBorradoLogico(borradoLogico);
        unaDeuda.persistir(entityManager);
    }

    public void eliminarDeuda(EntityManager entityManager, Deuda unaDeuda) {
        unaDeuda.setBorradoLogico(true);
        unaDeuda.persistir(entityManager);
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
    public Estado buscarEstadoBD(EntityManager entityManager, Long id) {
        Estado resultado;
        Query traerEstado = entityManager.createQuery("SELECT auxE FROM Estado auxE WHERE auxE.id = " + id);
        resultado = (Estado) traerEstado.getResultList();
        return resultado;
    }

    public Estado buscarEstado(Long id) {
        Estado resultado = null;
        for (Estado aux : estados) {
            if (Objects.equals(aux.getIdEstado(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearEstado(EntityManager entityManager, String tipo, Date fecha) {
        Estado unEstado = new Estado(entityManager, fecha, tipo);
        this.estados.add(unEstado);
        this.persistir(entityManager);
    }

    public void modificarEstado(EntityManager entityManager, Estado unEstado, String tipo, Date fecha, boolean borradoLogico) {
        unEstado.setTipo(tipo);
        unEstado.setFecha(fecha);
        unEstado.setBorradoLogico(borradoLogico);
        unEstado.persistir(entityManager);
    }

    public void eliminarunEstado(EntityManager entityManager, Estado unEstado) {
        unEstado.setBorradoLogico(true);
        unEstado.persistir(entityManager);
    }
//---------------------------------FIN ESTADOS----------------------------------
}
