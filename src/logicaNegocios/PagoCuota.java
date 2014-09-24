package logicaNegocios;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

@Entity
public class PagoCuota implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPagoCuota;

    @Basic
    private double monto;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaPago;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public PagoCuota() {

    }

    public PagoCuota(EntityManager entityManager, double monto, Date fechaPago, String observacion) {
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Long getIdPagoCuota() {
        return this.idPagoCuota;
    }

    public void setIdPagoCuota(Long idPagoCuota) {
        this.idPagoCuota = idPagoCuota;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return this.fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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

    public double setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
        return this.monto;
    }
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof PagoCuota) {
            PagoCuota unPagoCuota = (PagoCuota) aux;
            retorno = this.fechaPago.compareTo(unPagoCuota.getFechaPago());
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
