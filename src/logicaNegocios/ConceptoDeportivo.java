package logicaNegocios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

@Entity
public class ConceptoDeportivo implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Basic
    private double monto;

    @OneToOne(targetEntity = TipoEstado.class)
    private TipoEstado unTipoEstado;

    @Basic
    private String concepto;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConceptoDeportivo;

    @OneToOne(targetEntity = Frecuencia.class)
    private Frecuencia unaFrecuencia;

    @Basic
    private boolean borradoLogico;

    @OneToOne(targetEntity = TipoCancha.class)
    private TipoCancha unTipoCancha;
    // </editor-fold>

    public ConceptoDeportivo() {
    }

    public ConceptoDeportivo(EntityManager entityManager, double monto, String concepto, Frecuencia unaFrecuencia, TipoCancha unTipoCancha, TipoEstado unTipoEstado) {
        this.monto = monto;
        this.concepto = concepto;
        this.unaFrecuencia = unaFrecuencia;
        this.unTipoCancha = unTipoCancha;
        this.unTipoEstado = unTipoEstado;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoEstado getUnTipoEstado() {
        return this.unTipoEstado;
    }

    public void setUnTipoEstado(TipoEstado unTipoEstado) {
        this.unTipoEstado = unTipoEstado;
    }

    public String getConcepto() {
        return this.concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Long getIdConceptoDeportivo() {
        return this.idConceptoDeportivo;
    }

    public void setIdConceptoDeportivo(Long idConceptoDeportivo) {
        this.idConceptoDeportivo = idConceptoDeportivo;
    }

    public Frecuencia getUnaFrecuencia() {
        return this.unaFrecuencia;
    }

    public void setUnaFrecuencia(Frecuencia unaFrecuencia) {
        this.unaFrecuencia = unaFrecuencia;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public TipoCancha getUnTipoCancha() {
        return this.unTipoCancha;
    }

    public void setUnTipoCancha(TipoCancha unTipoCancha) {
        this.unTipoCancha = unTipoCancha;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Persistencia">
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Avisar al Servicio Técnico." + System.getProperty("line.separator") + "LMLSOLUCIONESINFORMATICAS@GMAIL.COM");
            tx.rollback();
        }
    }

    // </editor-fold>
    @Override
    public String toString() {
        return concepto;
    }
    
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
