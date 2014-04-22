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
public  class SancionTribunal implements Serializable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date vencimiento;

    @Basic
    private int cantFechas;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idSancionTribunal;

    @Basic
    private int cantFechasCumplidas;

    @Basic
    private String observacion;

    @Basic
    private String numeroResolucion;

    @Basic
    private boolean borradoLogico;

    public SancionTribunal(){

    }


   public Date getVencimiento() {
        return this.vencimiento;
    }


  public void setVencimiento (Date vencimiento) {
        this.vencimiento = vencimiento;
    }



   public int getCantFechas() {
        return this.cantFechas;
    }


  public void setCantFechas (int cantFechas) {
        this.cantFechas = cantFechas;
    }



   public Long getIdSancionTribunal() {
        return this.idSancionTribunal;
    }


  public void setIdSancionTribunal (Long idSancionTribunal) {
        this.idSancionTribunal = idSancionTribunal;
    }



   public int getCantFechasCumplidas() {
        return this.cantFechasCumplidas;
    }


  public void setCantFechasCumplidas (int cantFechasCumplidas) {
        this.cantFechasCumplidas = cantFechasCumplidas;
    }



   public String getObservacion() {
        return this.observacion;
    }


  public void setObservacion (String observacion) {
        this.observacion = observacion;
    }



   public String getNumeroResolucion() {
        return this.numeroResolucion;
    }


  public void setNumeroResolucion (String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}

