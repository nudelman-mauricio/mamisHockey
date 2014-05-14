package logicaNegocios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

@Entity
public class CuerpoTecnico extends Persona implements Serializable {

    @Basic
    private String fotocopiaDni;

    @Basic
    private boolean activo;

    public CuerpoTecnico() {

    }

    public CuerpoTecnico(EntityManager entityManager, Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, boolean activo) {
        super(dni, apellido, nombre, unaLocalidad, domicilio, fechaNacimiento, fechaIngreso);
        this.activo = activo;
        this.persistir(entityManager);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public String getFotocopiaDni() {
        return this.fotocopiaDni;
    }

    public void setFotocopiaDni(String fotocopiaDni) {
        this.fotocopiaDni = fotocopiaDni;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------
}