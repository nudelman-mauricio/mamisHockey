package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConceptoDeportivo implements Serializable, Comparable {

    @Basic
    private double monto;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConceptoDeportivo;

    @Basic
    private String nombre;

    @Basic
    private String detalle;

    @Basic
    private boolean borradoLogico;

    public ConceptoDeportivo() {
    }
    
    public ConceptoDeportivo(double monto, String nombre, String detalle) {
        this.monto = monto;
        this.nombre = nombre;
        this.detalle = detalle;
        this.borradoLogico = false;               
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Long getIdConceptoDeportivo() {
        return this.idConceptoDeportivo;
    }

    public void setIdConceptoDeportivo(Long idConceptoDeportivo) {
        this.idConceptoDeportivo = idConceptoDeportivo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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
        if (aux instanceof ConceptoDeportivo) {
            ConceptoDeportivo conceptoDeportivo = (ConceptoDeportivo) aux;
            if (this.idConceptoDeportivo > conceptoDeportivo.idConceptoDeportivo) {
                retorno = 1;
            }
        }
        return retorno;
    }
}
