package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public  class cuerpoTecnico extends Persona implements Serializable {

    @Basic
    private String fotocopiaDni;

    @Basic
    private boolean activo;

    public cuerpoTecnico(){

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

}

