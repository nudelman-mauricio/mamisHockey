package logicaNegocios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;

@Entity
public class Socia extends Persona implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
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
    // </editor-fold>

    public Socia() {

    }

    public Socia(EntityManager entityManager, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String fotoCarnet, boolean exJugadora, String email, String telFijo, String telCelular) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular);
        this.fotoCarnet = fotoCarnet;
        this.exJugadora = exJugadora;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ergometria">
    public void agregarErgometria(EntityManager entityManager, Ergometria unaErgometria) {
        this.getErgometrias().add(unaErgometria);
        this.persistir(entityManager);
    }

    public void quitarErgometria(EntityManager entityManager, Ergometria unaErgometria) {
        this.getErgometrias().remove(unaErgometria);
        this.persistir(entityManager);
    }

    /**
     * Devuelve True si la Socia tiene Ergometria en vigencia y aprobada al dia
     * de la fecha pasada por parametro
     */
    public boolean isErgometriaAprobada_y_Vigente(Date unaFecha) {
        boolean resultado = false;
        Ergometria ultimaErgometria = this.getUltimaErgometria();
        if ((ultimaErgometria.getFechaCaducidad().after(unaFecha)) && (ultimaErgometria.isAprobado())) {
            resultado = true;
        }
        return resultado;
    }

    public Ergometria getUltimaErgometria() {
        Ergometria ultimaErgometria = null;
        for (Ergometria unaErgometria : getErgometrias()) {
            if (!unaErgometria.isBorradoLogico()) {
                if (ultimaErgometria != null) {
                    if (unaErgometria.getFechaRealizacion().after(ultimaErgometria.getFechaRealizacion())) {
                        ultimaErgometria = unaErgometria;
                    }
                } else {
                    ultimaErgometria = unaErgometria;
                }
            }
        }
        return ultimaErgometria;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tarjetas">
    public void agregarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.getTarjetas().add(unaTarjeta);
        this.persistir(entityManager);
    }

    public void quitarTarjeta(EntityManager entityManager, Tarjeta unaTarjeta) {
        this.getTarjetas().remove(unaTarjeta);
        this.persistir(entityManager);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Pases">
    public void agregarPase(EntityManager entityManager, Pase unPase) {
        this.getPases().add(unPase);
        this.persistir(entityManager);
    }

    public void quitarPase(EntityManager entityManager, Pase unPase) {
        this.getPases().remove(unPase);
        this.persistir(entityManager);
    }

    /**
     * Devuelve SOLAMENTE los pases NO borrados
     */
    public Collection<Pase> getPasesValidos() {
        Collection<Pase> pasesValidos = new ArrayList();
        for (Pase aux : this.pases) {
            if (!aux.isBorradoLogico()) {
                pasesValidos.add(aux);
            }
        }
        return pasesValidos;
    }

    /**
     * Devuelve el Equipo Actual de la Socia
     */
    public Equipo getEquipoActual() {
        Equipo resultado = null;
        for (Pase aux : getPases()) {
            if (!aux.isBorradoLogico()) {
                resultado = aux.getUnEquipo();
            }
        }
        return resultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Deudas">
    public void agregarDeuda(EntityManager entityManager, Deuda unaDeuda) {
        this.getDeudas().add(unaDeuda);
        this.persistir(entityManager);
    }

    /**
     * Devuelve True solo si hasta el dia de la fecha pasada por parametro no
     * hay ninguna cuota vencida no pagada
     *
     * @param unaFecha
     * @return
     */
    public boolean isAlDia(Date unaFecha) {
        boolean resultado = true;
        for (Deuda unaDeuda : this.getDeudas()) {
            if ((!unaDeuda.isBorradoLogico()) && (unaDeuda.isVencido(unaFecha))) {
                resultado = false;
            }
        }
        return resultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Goles">
    public void agregarGol(EntityManager entityManager, Gol unGol) {
        this.getGoles().add(unGol);
        this.persistir(entityManager);
    }

    public void quitarGol(EntityManager entityManager, Gol unGol) {
        this.getGoles().remove(unGol);
        this.persistir(entityManager);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Estados">
    public void agregarEstado(EntityManager entityManager, Estado unEstado) {
        this.getEstados().add(unEstado);
        this.persistir(entityManager);
    }

    public void quitarEstado(EntityManager entityManager, Estado unEstado) {
        this.getEstados().remove(unEstado);
        this.persistir(entityManager);
    }

    /**
     * Devuelve solamente los estados no borrados
     */
    public Collection<Estado> getEstadosValidos() {
        Collection<Estado> estadosValidos = new ArrayList();
        for (Estado aux : this.estados) {
            if (!aux.isBorradoLogico()) {
                estadosValidos.add(aux);
            }
        }
        return estadosValidos;
    }

    /**
     * Devuelve el Estado Actual de la Socia
     */
    public Estado getUltimoEstado() {
        Estado ultimoEstado = null;
        for (Estado unEstado : getEstados()) {
            if (!unEstado.isBorradoLogico()) {
                if (ultimoEstado != null) {
                    if (unEstado.getFecha().after(ultimoEstado.getFecha())) {
                        ultimoEstado = unEstado;
                    }
                } else {
                    ultimoEstado = unEstado;
                }
            }
        }
        return ultimoEstado;
    }
    // </editor-fold>
}
