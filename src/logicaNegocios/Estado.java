package logicaNegocios;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public  class Estado implements Serializable {

    @Basic
    private boolean jugadora;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idEstado;

    @Basic
    private boolean licencia;

    @Basic
    private boolean baja;

    @Basic
    private boolean activa;

    @Basic
    private boolean borradoLogico;

    public Estado(){

    }


    public boolean isJugadora() {
        return this.jugadora;
    }


  public void setJugadora (boolean jugadora) {
        this.jugadora = jugadora;
    }



   public Date getFecha() {
        return this.fecha;
    }


  public void setFecha (Date fecha) {
        this.fecha = fecha;
    }



   public Long getIdEstado() {
        return this.idEstado;
    }


  public void setIdEstado (Long idEstado) {
        this.idEstado = idEstado;
    }



    public boolean isLicencia() {
        return this.licencia;
    }


  public void setLicencia (boolean licencia) {
        this.licencia = licencia;
    }



    public boolean isBaja() {
        return this.baja;
    }


  public void setBaja (boolean baja) {
        this.baja = baja;
    }



    public boolean isActiva() {
        return this.activa;
    }


  public void setActiva (boolean activa) {
        this.activa = activa;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}

