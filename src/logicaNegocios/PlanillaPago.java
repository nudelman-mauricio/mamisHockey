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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

@Entity
public class PlanillaPago implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @OneToOne(targetEntity = Socia.class)
    private Socia responsablePago;

    @Basic
    private double monto;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private long nroRecibo;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaPago;

    @Basic
    private String rutaPDF;
    // </editor-fold>

    public PlanillaPago() {

    }

    public PlanillaPago(EntityManager entityManager, Date fechaPago, double monto, long nroRecibo, Socia responsablePago) {
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.nroRecibo = nroRecibo;
        this.responsablePago = responsablePago;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Socia getResponsablePago() {
        return this.responsablePago;
    }

    public void setResponsablePago(Socia responsablePago) {
        this.responsablePago = responsablePago;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNroRecibo() {
        return this.nroRecibo;
    }

    public void setNroRecibo(long nroRecibo) {
        this.nroRecibo = nroRecibo;
    }

    public Date getFechaPago() {
        return this.fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getRutaPDF() {
        return this.rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
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
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Avisar al Servicio Tecnico." + System.getProperty("line.separator") + "LMLSOLUCIONESINFORMATICAS@GMAIL.COM");
            tx.rollback();
        }
    }
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof PlanillaPago) {
            PlanillaPago unaPlanillaPago = (PlanillaPago) aux;
            retorno = this.fechaPago.compareTo(unaPlanillaPago.getFechaPago());
        }
        return retorno;
    }
}
