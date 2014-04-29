package logicaNegocios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Arbitro extends Persona implements Serializable {

    @Basic
    private String fotocopiaDni;

    @Basic
    private boolean borradoLogico;

    public Arbitro() {

    }

    public Arbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio,
            Date fechaNacimiento, String telFijo, String telCelular, String email, Date fechaIngreso,
            boolean borradoLogico, String fotocopiaDni) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, telFijo, telCelular, email, fechaIngreso, borradoLogico);
        this.fotocopiaDni = fotocopiaDni;
        this.borradoLogico = borradoLogico;
    }

    public String getFotocopiaDni() {
        return this.fotocopiaDni;
    }

    public void setFotocopiaDni(String fotocopiaDni) {
        this.fotocopiaDni = fotocopiaDni;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}
