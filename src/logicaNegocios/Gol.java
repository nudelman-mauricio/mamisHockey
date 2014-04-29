package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gol implements Serializable {

    @Basic
    private String tiempo;

    @Basic
    private boolean autoGol;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGol;

    @Basic
    private boolean borradoLogico;

    public Gol() {

    }

    public String getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isAutoGol() {
        return this.autoGol;
    }

    public void setAutoGol(boolean autoGol) {
        this.autoGol = autoGol;
    }

    public Long getIdGol() {
        return this.idGol;
    }

    public void setIdGol(Long idGol) {
        this.idGol = idGol;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}
