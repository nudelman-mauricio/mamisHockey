package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PersonaAuxiliar extends Persona implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @ElementCollection
    @Temporal(TemporalType.DATE)
    private Collection<Date> actasCompromiso;

    @Basic
    private boolean arbitro;

    @Basic
    private boolean cuerpoTecnico;

    @Lob
    @Basic
    private byte[] fotocopiaDni;

    @Basic
    private boolean cuerpoTecnicoActivo;

    @Basic
    private boolean plantaPermanente;
    // </editor-fold>

    public PersonaAuxiliar() {

    }

    public PersonaAuxiliar(EntityManager entityManager, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico, boolean plantaPermanente) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular);
        this.arbitro = arbitro;
        this.cuerpoTecnico = cuerpoTecnico;
        this.plantaPermanente = plantaPermanente;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Collection<Date> getActasCompromiso() {
        return this.actasCompromiso;
    }

    public void setActasCompromiso(Collection<Date> actasCompromiso) {
        this.actasCompromiso = actasCompromiso;
    }

    public boolean isArbitro() {
        return this.arbitro;
    }

    public void setArbitro(boolean arbitro) {
        this.arbitro = arbitro;
    }

    public boolean isCuerpoTecnico() {
        return this.cuerpoTecnico;
    }

    public void setCuerpoTecnico(boolean cuerpoTecnico) {
        this.cuerpoTecnico = cuerpoTecnico;
    }

    public byte[] getFotocopiaDni() {
        return this.fotocopiaDni;
    }

    public void setFotocopiaDni(byte[] fotocopiaDni) {
        this.fotocopiaDni = fotocopiaDni;
    }

    public boolean isCuerpoTecnicoActivo() {
        return this.cuerpoTecnicoActivo;
    }

    public void setCuerpoTecnicoActivo(boolean cuerpoTecnicoActivo) {
        this.cuerpoTecnicoActivo = cuerpoTecnicoActivo;
    }

    public boolean isPlantaPermanente() {
        return this.plantaPermanente;
    }

    public void setPlantaPermanente(boolean plantaPermanente) {
        this.plantaPermanente = plantaPermanente;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Actas Compromiso">
    public void agregarActaCompromiso(EntityManager entityManager, Date unaFecha) {
        this.actasCompromiso.add(unaFecha);
        this.persistir(entityManager);
    }

    public void quitarActaCompromiso(EntityManager entityManager, Date unaFecha) {
        this.actasCompromiso.remove(unaFecha);
        this.persistir(entityManager);
    }
    // </editor-fold>
}
