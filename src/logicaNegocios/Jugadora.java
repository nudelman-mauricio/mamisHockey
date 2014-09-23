package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Jugadora implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @OneToOne(optional = false, targetEntity = Socia.class)
    private Socia unaSocia;

    @Basic
    private String camiseta;

    @Basic
    private boolean local;
    // </editor-fold>

    public Jugadora() {

    }

    public Jugadora(Socia unaSocia, String camiseta, boolean local) {
        this.unaSocia = unaSocia;
        this.camiseta = camiseta;
        this.local = local;
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Socia getUnaSocia() {
        return this.unaSocia;
    }

    public void setUnaSocia(Socia unaSocia) {
        this.unaSocia = unaSocia;
    }

    public String getCamiseta() {
        return this.camiseta;
    }

    public void setCamiseta(String camiseta) {
        this.camiseta = camiseta;
    }

    public boolean isLocal() {
        return this.local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }
    // </editor-fold>
}
