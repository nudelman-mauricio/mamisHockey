package logicaNegocios;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pase implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Basic
    private double monto;

    @OneToOne(optional = false, targetEntity = Equipo.class)
    private Equipo unEquipo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPase;

    @Basic
    private boolean borradoLogico;

    public Pase() {

    }

    public Pase(Date fecha, double monto, Equipo unEquipo) {
        this.fecha = fecha;
        this.monto = monto;
        this.unEquipo = unEquipo;
        this.borradoLogico = false;
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Equipo getUnEquipo() {
        return this.unEquipo;
    }

    public void setUnEquipo(Equipo unEquipo) {
        this.unEquipo = unEquipo;
    }

    public Long getIdPase() {
        return this.idPase;
    }

    public void setIdPase(Long idPase) {
        this.idPase = idPase;
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
        if (aux instanceof Pase) {
            Pase pase = (Pase) aux;
            if (this.idPase > pase.idPase) {
                retorno = 1;
            }
        }
        return retorno;
    }
}
