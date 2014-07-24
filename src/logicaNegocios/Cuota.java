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

@Entity
public class Cuota implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Basic
    private double monto;

    @OneToOne(optional = false, targetEntity = PagoCuota.class)
    private PagoCuota unPagoCuota;

    @Basic
    private String numero;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCuota;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaVencimiento;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public Cuota() {

    }

    public Cuota(EntityManager entityManager, double monto, Date fechaVencimiento, String numero) {
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.numero = numero;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Setters">
    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public PagoCuota getUnPagoCuota() {
        return this.unPagoCuota;
    }

    public void setUnPagoCuota(PagoCuota unPagoCuota) {
        this.unPagoCuota = unPagoCuota;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getIdCuota() {
        return this.idCuota;
    }

    public void setIdCuota(Long idCuota) {
        this.idCuota = idCuota;
    }

    public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public double setBorradoLogico(boolean borradoLogico, EntityManager entityManager) {
        this.borradoLogico = borradoLogico;
        double montoNotaCredito = 0;
        if (borradoLogico && (this.unPagoCuota != null) && (!this.unPagoCuota.isBorradoLogico())) {
            montoNotaCredito = this.unPagoCuota.setBorradoLogico(true);
            this.unPagoCuota.persistir(entityManager);
        }
        return montoNotaCredito;
    }
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Cuota) {
            Cuota unaCuota = (Cuota) aux;
            if (this.idCuota > unaCuota.idCuota) {
                retorno = 1;
            }
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
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Persistir Cuota" + e.getMessage());
            tx.rollback();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="PagosCuotas">    
    /**
     * Fecha del Pago o Null
     * @return NULL o FechaPago
     */
    public Date getFechaPago(){
        Date fechaPago = null;
        if (this.unPagoCuota != null){
            fechaPago= this.unPagoCuota.getFechaPago();
        }
        return fechaPago;
    }
    
    /**
     * Monto pagado o 0.0
     * @return MontoPago o 0.0
     */
    public Double getMontoPago(){
        Double montoPago = 0.0;
        if (this.unPagoCuota != null){
            montoPago= this.unPagoCuota.getMonto();
        }
        return montoPago;
    }
    // </editor-fold>    
}
