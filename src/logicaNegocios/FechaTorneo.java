package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public  class FechaTorneo implements Serializable {

    @Basic
    private int numeroFecha;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idFecha;

    @OneToMany(targetEntity=Partido.class)
    private Collection<Partido> partidos;

    public FechaTorneo(){

    }


   public int getNumeroFecha() {
        return this.numeroFecha;
    }


  public void setNumeroFecha (int numeroFecha) {
        this.numeroFecha = numeroFecha;
    }



   public Long getIdFecha() {
        return this.idFecha;
    }


  public void setIdFecha (Long idFecha) {
        this.idFecha = idFecha;
    }



   public Collection<Partido> getPartidos() {
        return this.partidos;
    }


  public void setPartidos (Collection<Partido> partidos) {
        this.partidos = partidos;
    }

}

