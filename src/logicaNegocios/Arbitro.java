package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public  class Arbitro extends Persona implements Serializable {

    @Basic
    private String fotocopiaDni;

    @Basic
    private boolean borradoLogico;

    public Arbitro(){

    }


   public String getFotocopiaDni() {
        return this.fotocopiaDni;
    }


  public void setFotocopiaDni (String fotocopiaDni) {
        this.fotocopiaDni = fotocopiaDni;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}

