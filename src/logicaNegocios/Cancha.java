package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public  class Cancha implements Serializable {

    @Basic
    private String nombre;

    @Basic
    private boolean activa;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idCancha;

    @Basic
    private boolean borradoLogico;

    public Cancha(){

    }


   public String getNombre() {
        return this.nombre;
    }


  public void setNombre (String nombre) {
        this.nombre = nombre;
    }



    public boolean isActiva() {
        return this.activa;
    }


  public void setActiva (boolean activa) {
        this.activa = activa;
    }



   public Long getIdCancha() {
        return this.idCancha;
    }


  public void setIdCancha (Long idCancha) {
        this.idCancha = idCancha;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}

