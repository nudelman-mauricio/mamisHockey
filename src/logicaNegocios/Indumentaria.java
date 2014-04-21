package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public  class Indumentaria implements Serializable {

    @Basic
    private String camiseta;

    @Basic
    private String media;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idIndumentaria;

    @Basic
    private String pollera;

    public Indumentaria(){

    }


   public String getCamiseta() {
        return this.camiseta;
    }


  public void setCamiseta (String camiseta) {
        this.camiseta = camiseta;
    }



   public String getMedia() {
        return this.media;
    }


  public void setMedia (String media) {
        this.media = media;
    }



   public Long getIdIndumentaria() {
        return this.idIndumentaria;
    }


  public void setIdIndumentaria (Long idIndumentaria) {
        this.idIndumentaria = idIndumentaria;
    }



   public String getPollera() {
        return this.pollera;
    }


  public void setPollera (String pollera) {
        this.pollera = pollera;
    }

}

