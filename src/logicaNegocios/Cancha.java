package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cancha implements Serializable {

    @Basic
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCancha;

    @Basic
    private boolean seOcupa;

    @Basic
    private boolean borradoLogico;

    public Cancha() {

    }

    public Cancha(String nombre, boolean seOcupa) {
        this.nombre = nombre;
        this.seOcupa = seOcupa;
        this.borradoLogico = false;
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdCancha() {
        return this.idCancha;
    }

    public void setIdCancha(Long idCancha) {
        this.idCancha = idCancha;
    }

    public boolean isSeOcupa() {
        return this.seOcupa;
    }

    public void setSeOcupa(boolean seOcupa) {
        this.seOcupa = seOcupa;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

}
