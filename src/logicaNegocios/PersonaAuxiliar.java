package logicaNegocios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

@Entity
public class PersonaAuxiliar extends Persona implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Basic
    private boolean arbitro;

    @Basic
    private boolean cuerpoTecnico;

    @Basic
    private String fotocopiaDni;

    @Basic
    private boolean cuerpoTecnicoActivo;
    // </editor-fold>

    public PersonaAuxiliar() {

    }

    public PersonaAuxiliar(EntityManager entityManager, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular, boolean arbitro, boolean cuerpoTecnico) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso, email, telFijo, telCelular);
        this.arbitro = arbitro;
        this.cuerpoTecnico = cuerpoTecnico;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
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

    public String getFotocopiaDni() {
        return this.fotocopiaDni;
    }

    public void setFotocopiaDni(String fotocopiaDni) {
        this.fotocopiaDni = fotocopiaDni;
    }

    public boolean isCuerpoTecnicoActivo() {
        return this.cuerpoTecnicoActivo;
    }

    public void setCuerpoTecnicoActivo(boolean cuerpoTecnicoActivo) {
        this.cuerpoTecnicoActivo = cuerpoTecnicoActivo;
    }
    // </editor-fold>
}
