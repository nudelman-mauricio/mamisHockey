package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public  class Torneo implements Serializable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date diaInicio;

    @OneToMany(targetEntity=FechaTorneo.class)
    private Collection<FechaTorneo> fechasTorneo;

    @OneToOne(optional=false,targetEntity=Categoria.class)
    private Categoria unaCategoria;

    @Basic
    private String nombre;

    @Id
    private Long idTorneo;

    public Torneo(){

    }


   public Date getDiaInicio() {
        return this.diaInicio;
    }


  public void setDiaInicio (Date diaInicio) {
        this.diaInicio = diaInicio;
    }



   public Collection<FechaTorneo> getFechasTorneo() {
        return this.fechasTorneo;
    }


  public void setFechasTorneo (Collection<FechaTorneo> fechasTorneo) {
        this.fechasTorneo = fechasTorneo;
    }



   public Categoria getUnaCategoria() {
        return this.unaCategoria;
    }


  public void setUnaCategoria (Categoria unaCategoria) {
        this.unaCategoria = unaCategoria;
    }



   public String getNombre() {
        return this.nombre;
    }


  public void setNombre (String nombre) {
        this.nombre = nombre;
    }



   public Long getIdTorneo() {
        return this.idTorneo;
    }


  public void setIdTorneo (Long idTorneo) {
        this.idTorneo = idTorneo;
    }

}

