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
public class Sancion implements Serializable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @OneToOne(optional = false, targetEntity = Tarjeta.class)
    private Tarjeta unaTarjeta;

    @OneToOne(optional = false, targetEntity = SancionTribunal.class)
    private SancionTribunal unaSancionTribunal;

    @Basic
    private boolean resolucion;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSancion;

    @OneToOne(optional = false, targetEntity = Partido.class)
    private Partido unPartido;

    @Basic
    private boolean borradoLogico;

    public Sancion() {

    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tarjeta getUnaTarjeta() {
        return this.unaTarjeta;
    }

    public void setUnaTarjeta(Tarjeta unaTarjeta) {
        this.unaTarjeta = unaTarjeta;
    }

    public SancionTribunal getUnaSancionTribunal() {
        return this.unaSancionTribunal;
    }

    public void setUnaSancionTribunal(SancionTribunal unaSancionTribunal) {
        this.unaSancionTribunal = unaSancionTribunal;
    }

    public boolean isResolucion() {
        return this.resolucion;
    }

    public void setResolucion(boolean resolucion) {
        this.resolucion = resolucion;
    }

    public Long getIdSancion() {
        return this.idSancion;
    }

    public void setIdSancion(Long idSancion) {
        this.idSancion = idSancion;
    }

    public Partido getUnPartido() {
        return this.unPartido;
    }

    public void setUnPartido(Partido unPartido) {
        this.unPartido = unPartido;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

}
