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
public class IngresoOtro implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idIngresoOtro;
    @Basic
    private double monto;
    @OneToOne(targetEntity = ConceptoIngreso.class)
    private ConceptoIngreso unConceptoIngreso;
    @Column(length = 1000)
    @Basic
    private String observacion;
    @Basic
    private boolean borradoLogico;
// </editor-fold>

    public IngresoOtro() {

    }

    public IngresoOtro(EntityManager entityManager, Date fecha, ConceptoIngreso unConceptoIngreso, double monto, String detalle) {
        this.fecha = fecha;
        this.unConceptoIngreso = unConceptoIngreso;
        this.monto = monto;
        this.observacion = detalle;
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

    public Long getIdIngresoOtro() {
        return this.idIngresoOtro;
    }

    public void setIdIngresoOtro(Long idIngresoOtro) {
        this.idIngresoOtro = idIngresoOtro;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public ConceptoIngreso getUnConceptoIngreso() {
        return this.unConceptoIngreso;
    }

    public void setUnConceptoIngreso(ConceptoIngreso unConceptoIngreso) {
        this.unConceptoIngreso = unConceptoIngreso;
    }

    public String getDetalle() {
        return this.observacion;
    }

    public void setDetalle(String detalle) {
        this.observacion = detalle;
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
        if (aux instanceof IngresoOtro) {
            IngresoOtro ingresoOtro = (IngresoOtro) aux;
            retorno = this.fecha.compareTo(ingresoOtro.getFecha());
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
