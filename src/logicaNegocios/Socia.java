package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public  class Socia extends Persona implements Serializable {

    @Basic
    private String numeroCamiseta;

    @Basic
    private String fotoCarnet;

    @Basic
    private boolean exJugadora;

    @OneToMany(targetEntity=Tarjeta.class)
    private Collection<Tarjeta> tarjetas;

    @OneToMany(targetEntity=Pase.class)
    private Collection<Pase> pases;

    @OneToMany(targetEntity=Deuda.class)
    private Collection<Deuda> deudas;

    @OneToMany(targetEntity=Gol.class)
    private Collection<Gol> goles;

    @Basic
    private boolean borradoLogico;

    @OneToMany(targetEntity=Estado.class)
    private Collection<Estado> estados;

    public Socia(){

    }


   public String getNumeroCamiseta() {
        return this.numeroCamiseta;
    }


  public void setNumeroCamiseta (String numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }



   public String getFotoCarnet() {
        return this.fotoCarnet;
    }


  public void setFotoCarnet (String fotoCarnet) {
        this.fotoCarnet = fotoCarnet;
    }



    public boolean isExJugadora() {
        return this.exJugadora;
    }


  public void setExJugadora (boolean exJugadora) {
        this.exJugadora = exJugadora;
    }



   public Collection<Tarjeta> getTarjetas() {
        return this.tarjetas;
    }


  public void setTarjetas (Collection<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }



   public Collection<Pase> getPases() {
        return this.pases;
    }


  public void setPases (Collection<Pase> pases) {
        this.pases = pases;
    }



   public Collection<Deuda> getDeudas() {
        return this.deudas;
    }


  public void setDeudas (Collection<Deuda> deudas) {
        this.deudas = deudas;
    }



   public Collection<Gol> getGoles() {
        return this.goles;
    }


  public void setGoles (Collection<Gol> goles) {
        this.goles = goles;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }



   public Collection<Estado> getEstados() {
        return this.estados;
    }


  public void setEstados (Collection<Estado> estados) {
        this.estados = estados;
    }

}

