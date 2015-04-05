package logicaNegocios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

@Entity
public class Egreso implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;
    @Basic
    private double monto;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEgreso;
    @OneToOne(targetEntity = ConceptoEgreso.class)
    private ConceptoEgreso unConceptoEgreso;
    @Column(length = 1000)
    @Basic
    private String observacion;
    @Basic
    private boolean borradoLogico;
// </editor-fold>

    public Egreso() {

    }

    public Egreso(EntityManager entityManager, Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion) {
        this.fecha = fecha;
        this.monto = monto;
        this.unConceptoEgreso = unConceptoEgreso;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
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

    public Long getIdEgreso() {
        return this.idEgreso;
    }

    public void setIdEgreso(Long idEgreso) {
        this.idEgreso = idEgreso;
    }

    public ConceptoEgreso getUnConceptoEgreso() {
        return this.unConceptoEgreso;
    }

    public void setUnConceptoEgreso(ConceptoEgreso unConceptoEgreso) {
        this.unConceptoEgreso = unConceptoEgreso;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Egreso) {
            Egreso egreso = (Egreso) aux;
            retorno = this.fecha.compareTo(egreso.getFecha());
        }
        return retorno;
    }

    // <editor-fold defaultstate="collapsed" desc="Persistencia">
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Avisar al Servicio Tecnico." + System.getProperty("line.separator") + "LMLSOLUCIONESINFORMATICAS@GMAIL.COM");
            tx.rollback();
        }
    }
    // </editor-fold>
}
