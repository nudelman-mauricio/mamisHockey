package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Indumentaria implements Serializable, Comparable {

    @Basic
    private String camiseta;

    @Basic
    private String media;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idIndumentaria;

    @Basic
    private String pollera;

    @Basic
    private boolean borradoLogico;

    public Indumentaria() {

    }

    public Indumentaria(String camiseta, String media, String pollera) {
        this.camiseta = camiseta;
        this.media = media;
        this.pollera = pollera;
        this.borradoLogico = false;
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public String getCamiseta() {
        return this.camiseta;
    }

    public void setCamiseta(String camiseta) {
        this.camiseta = camiseta;
    }

    public String getMedia() {
        return this.media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Long getIdIndumentaria() {
        return this.idIndumentaria;
    }

    public void setIdIndumentaria(Long idIndumentaria) {
        this.idIndumentaria = idIndumentaria;
    }

    public String getPollera() {
        return this.pollera;
    }

    public void setPollera(String pollera) {
        this.pollera = pollera;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Indumentaria) {
            Indumentaria indumentaria = (Indumentaria) aux;
            if (this.idIndumentaria > indumentaria.idIndumentaria) {
                retorno = 1;
            }
        }
        return retorno;
    }
}
