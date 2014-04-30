package logicaNegocios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Arbitro extends Persona implements Serializable {

    @Basic
    private String fotocopiaDni;

    public Arbitro() {
    }

    public Arbitro(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public String getFotocopiaDni() {
        return this.fotocopiaDni;
    }

    public void setFotocopiaDni(String fotocopiaDni) {
        this.fotocopiaDni = fotocopiaDni;
    }

//----------------------------- FIN GETERS Y SETERS ----------------------------
}
