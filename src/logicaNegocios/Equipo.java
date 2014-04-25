package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public  class Equipo implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idEquipo;

    @OneToOne(optional=false,targetEntity=Socia.class)
    private Socia unaDelegadaSuplente;

    @OneToMany(targetEntity=Deuda.class)
    private Collection<Deuda> deudas;

    @OneToOne(optional=false,targetEntity=Socia.class)
    private Socia unaCapitanaSuplente;

    @OneToMany(targetEntity=Indumentaria.class)
    private Collection<Indumentaria> indumentarias;

    @OneToOne(optional=false,targetEntity=Socia.class)
    private Socia unaDelegada;

    @Basic
    private String nombre;

    @OneToOne(optional=false,targetEntity=CuerpoTecnico.class)
    private CuerpoTecnico unAyudanteCampo;

    @OneToOne(optional=false,targetEntity=CuerpoTecnico.class)
    private CuerpoTecnico unPreparadorFisico;

    @OneToOne(optional=false,targetEntity=Socia.class)
    private Socia unaCapitana;

    @OneToMany(targetEntity=Socia.class)
    private Collection<Socia> plantel;

    @Basic
    private boolean borradoLogico;

    @OneToOne(optional=false,targetEntity=CuerpoTecnico.class)
    private CuerpoTecnico unDT;

    public Equipo(){

    }

    public Equipo(String nombre, Collection<Socia> plantel,Collection<Indumentaria> indumentarias, Socia unaCapitana, Socia unaCapitanaSuplente,  Socia unaDelegada, Socia unaDelegadaSuplente,CuerpoTecnico unDT, CuerpoTecnico unPreparadorFisico, CuerpoTecnico unAyudanteCampo, boolean borradoLogico){
        this.nombre = nombre;
        this.plantel = plantel;
        this.indumentarias = indumentarias;
        this.unaCapitana = unaCapitana;
        this.unaCapitanaSuplente = unaCapitanaSuplente;
        this.unaDelegada=  unaDelegada;
        this.unaDelegadaSuplente = unaDelegadaSuplente;
        this.unDT = unDT;
        this.unPreparadorFisico = unPreparadorFisico;
        this.unAyudanteCampo = unAyudanteCampo;
        this.borradoLogico = borradoLogico;
    }

   public Long getIdEquipo() {
        return this.idEquipo;
    }


  public void setIdEquipo (Long idEquipo) {
        this.idEquipo = idEquipo;
    }



   public Socia getUnaDelegadaSuplente() {
        return this.unaDelegadaSuplente;
    }


  public void setUnaDelegadaSuplente (Socia unaDelegadaSuplente) {
        this.unaDelegadaSuplente = unaDelegadaSuplente;
    }



   public Collection<Deuda> getDeudas() {
        return this.deudas;
    }


  public void setDeudas (Collection<Deuda> deudas) {
        this.deudas = deudas;
    }



   public Socia getUnaCapitanaSuplente() {
        return this.unaCapitanaSuplente;
    }


  public void setUnaCapitanaSuplente (Socia unaCapitanaSuplente) {
        this.unaCapitanaSuplente = unaCapitanaSuplente;
    }



   public Collection<Indumentaria> getIndumentarias() {
        return this.indumentarias;
    }


  public void setIndumentarias (Collection<Indumentaria> indumentarias) {
        this.indumentarias = indumentarias;
    }



   public Socia getUnaDelegada() {
        return this.unaDelegada;
    }


  public void setUnaDelegada (Socia unaDelegada) {
        this.unaDelegada = unaDelegada;
    }



   public String getNombre() {
        return this.nombre;
    }


  public void setNombre (String nombre) {
        this.nombre = nombre;
    }



   public CuerpoTecnico getUnAyudanteCampo() {
        return this.unAyudanteCampo;
    }


  public void setUnAyudanteCampo (CuerpoTecnico unAyudanteCampo) {
        this.unAyudanteCampo = unAyudanteCampo;
    }



   public CuerpoTecnico getUnPreparadorFisico() {
        return this.unPreparadorFisico;
    }


  public void setUnPreparadorFisico (CuerpoTecnico unPreparadorFisico) {
        this.unPreparadorFisico = unPreparadorFisico;
    }



   public Socia getUnaCapitana() {
        return this.unaCapitana;
    }


  public void setUnaCapitana (Socia unaCapitana) {
        this.unaCapitana = unaCapitana;
    }



   public Collection<Socia> getPlantel() {
        return this.plantel;
    }


  public void setPlantel (Collection<Socia> plantel) {
        this.plantel = plantel;
    }



    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }


  public void setBorradoLogico (boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }



   public CuerpoTecnico getUnDT() {
        return this.unDT;
    }


  public void setUnDT (CuerpoTecnico unDT) {
        this.unDT = unDT;
    }

}

