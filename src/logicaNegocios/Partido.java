package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public  class Partido implements Serializable {

    @OneToOne(optional=false,targetEntity=Equipo.class)
    private Equipo unEquipoVisitante;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @OneToMany(targetEntity=Tarjeta.class)
    private Collection<Tarjeta> tarjetas;

    @Basic
    private String nombreAyudanteMesaLocal;

    @OneToOne(optional=false,targetEntity=Arbitro.class)
    private Arbitro unArbitro1;

    @OneToOne(optional=false,targetEntity=Arbitro.class)
    private Arbitro unArbitro2;

    @OneToOne(optional=false,targetEntity=Cancha.class)
    private Cancha unaCancha;

    @Basic
    private String observaciones;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idPartido;

    @OneToOne(optional=false,targetEntity=Equipo.class)
    private Equipo unEquipoLocal;

    @OneToMany(targetEntity=Gol.class)
    private Collection<Gol> goles;

    @Basic
    private String nombreAyudanteMesaVisitante;

    public Partido(){

    }


   public Equipo getUnEquipoVisitante() {
        return this.unEquipoVisitante;
    }


  public void setUnEquipoVisitante (Equipo unEquipoVisitante) {
        this.unEquipoVisitante = unEquipoVisitante;
    }



   public Date getFecha() {
        return this.fecha;
    }


  public void setFecha (Date fecha) {
        this.fecha = fecha;
    }



   public Collection<Tarjeta> getTarjetas() {
        return this.tarjetas;
    }


  public void setTarjetas (Collection<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }



   public String getNombreAyudanteMesaLocal() {
        return this.nombreAyudanteMesaLocal;
    }


  public void setNombreAyudanteMesaLocal (String nombreAyudanteMesaLocal) {
        this.nombreAyudanteMesaLocal = nombreAyudanteMesaLocal;
    }



   public Arbitro getUnArbitro1() {
        return this.unArbitro1;
    }


  public void setUnArbitro1 (Arbitro unArbitro1) {
        this.unArbitro1 = unArbitro1;
    }



   public Arbitro getUnArbitro2() {
        return this.unArbitro2;
    }


  public void setUnArbitro2 (Arbitro unArbitro2) {
        this.unArbitro2 = unArbitro2;
    }



   public Cancha getUnaCancha() {
        return this.unaCancha;
    }


  public void setUnaCancha (Cancha unaCancha) {
        this.unaCancha = unaCancha;
    }



   public String getObservaciones() {
        return this.observaciones;
    }


  public void setObservaciones (String observaciones) {
        this.observaciones = observaciones;
    }



   public Long getIdPartido() {
        return this.idPartido;
    }


  public void setIdPartido (Long idPartido) {
        this.idPartido = idPartido;
    }



   public Equipo getUnEquipoLocal() {
        return this.unEquipoLocal;
    }


  public void setUnEquipoLocal (Equipo unEquipoLocal) {
        this.unEquipoLocal = unEquipoLocal;
    }



   public Collection<Gol> getGoles() {
        return this.goles;
    }


  public void setGoles (Collection<Gol> goles) {
        this.goles = goles;
    }



   public String getNombreAyudanteMesaVisitante() {
        return this.nombreAyudanteMesaVisitante;
    }


  public void setNombreAyudanteMesaVisitante (String nombreAyudanteMesaVisitante) {
        this.nombreAyudanteMesaVisitante = nombreAyudanteMesaVisitante;
    }

}

