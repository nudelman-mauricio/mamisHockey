package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localidad implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLocalidad;

    @Basic
    private String nombre;

    @Basic
    private String codPostal;

    @Basic
    private boolean borradoLogico;

    public Localidad() {

    }

    public Localidad(String nombre, String codPostal) {
        this.nombre = nombre;
        this.codPostal = codPostal;
        this.borradoLogico = false;
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Long getIdLocalidad() {
        return this.idLocalidad;
    }

    public void setIdLocalidad(Long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodPostal() {
        return this.codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Localidad) {
            Localidad localidad = (Localidad) aux;
            if (this.idLocalidad > localidad.idLocalidad) {
                retorno = 1;
            }
        }
        return retorno;
    }
}
