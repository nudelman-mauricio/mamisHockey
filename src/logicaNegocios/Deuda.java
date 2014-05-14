package logicaNegocios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Deuda implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDeuda;
    
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaGeneracion;
    
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaVencimiento;
    
    @Basic
    private String concepto;

    @Basic
    private String observacion;
    
    @Basic
    private double monto;
    
    @Basic
    private boolean saldado;
    
    @OneToMany(targetEntity = PagoDeuda.class)
    private Collection<PagoDeuda> pagosDeuda;
    
    @Basic
    private boolean borradoLogico;

    public Deuda() {

    }

    public Deuda(EntityManager entityManager, Date fechaGeneracion, Date fechaVencimiento, double monto, String observacion) {
        this.fechaGeneracion = fechaGeneracion;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.saldado = false;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public Collection<PagoDeuda> getPagosDeuda() {
        return this.pagosDeuda;
    }

    public void setPagosDeuda(Collection<PagoDeuda> pagosDeuda) {
        this.pagosDeuda = pagosDeuda;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isSaldado() {
        return this.saldado;
    }

    public void setSaldado(boolean saldado) {
        this.saldado = saldado;
    }

    public Long getIdDeuda() {
        return this.idDeuda;
    }

    public void setIdDeuda(Long idDeuda) {
        this.idDeuda = idDeuda;
    }

    public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getConcepto() {
        return this.concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
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
//----------------------------- FIN GETERS Y SETERS ----------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Deuda) {
            Deuda deuda = (Deuda) aux;
            if (this.idDeuda > deuda.idDeuda) {
                retorno = 1;
            }
        }
        return retorno;
    }

//----------------------------------PERSISTENCIA--------------------------------
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Persistir Deuda" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------

//--------------------------------PAGO DEUDA------------------------------------
    public void agregarPagoDeuda(EntityManager entityManager, PagoDeuda unPagoDeuda) {
        this.pagosDeuda.add(unPagoDeuda);
        this.persistir(entityManager);
    }

    public void quitarPagoDeuda(EntityManager entityManager, PagoDeuda unPagoDeuda) {
        this.pagosDeuda.remove(unPagoDeuda);
        this.persistir(entityManager);
    }
//------------------------------FIN PAGO DEUDA----------------------------------
}
