package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public  class CuerpoTecnico extends Persona implements Serializable {

    @Basic
    private String fotocopiaDni;

    @Basic
    private boolean activo;

    @Basic
    private boolean borradoLogico;

    public CuerpoTecnico(){

    }


   public String getFotocopiaDni() {
        return this.fotocopiaDni;
    }


  public void setFotocopiaDni (String fotocopiaDni) {
        this.fotocopiaDni = fotocopiaDni;
    }



    public boolean isActivo() {
        return this.activo;
    }


  public void setActivo (boolean activo) {
        this.activo = activo;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}

