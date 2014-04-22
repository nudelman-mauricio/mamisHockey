package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public  class Tarjeta implements Serializable {

    @Basic
    private boolean roja;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idTarjeta;

    @Basic
    private boolean amarilla;

    @Basic
    private boolean verde;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public Tarjeta(){

    }


    public boolean isRoja() {
        return this.roja;
    }


  public void setRoja (boolean roja) {
        this.roja = roja;
    }



   public Long getIdTarjeta() {
        return this.idTarjeta;
    }


  public void setIdTarjeta (Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }



    public boolean isAmarilla() {
        return this.amarilla;
    }


  public void setAmarilla (boolean amarilla) {
        this.amarilla = amarilla;
    }



    public boolean isVerde() {
        return this.verde;
    }


  public void setVerde (boolean verde) {
        this.verde = verde;
    }



   public String getObservacion() {
        return this.observacion;
    }


  public void setObservacion (String observacion) {
        this.observacion = observacion;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}

