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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Deuda implements Serializable, Comparable {

    @OneToMany(targetEntity = PagoDeuda.class)
    private Collection<PagoDeuda> pagosDeuda;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Basic
    private double monto;

    @Basic
    private boolean saldado;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDeuda;

    @OneToOne(optional = false, targetEntity = ConceptoDeportivo.class)
    private ConceptoDeportivo unConceptoDeportivo;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public Deuda() {
    }

    public Deuda(Date fecha, double monto, boolean saldado, ConceptoDeportivo unConceptoDeportivo, String observacion) {
        this.fecha = fecha;
        this.monto = monto;
        this.saldado = saldado;
        this.unConceptoDeportivo = unConceptoDeportivo;
        this.observacion = observacion;
        this.borradoLogico = false;
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public Collection<PagoDeuda> getPagosDeuda() {
        return this.pagosDeuda;
    }

    public void setPagosDeuda(Collection<PagoDeuda> pagosDeuda) {
        this.pagosDeuda = pagosDeuda;
    }

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

    public ConceptoDeportivo getUnConceptoDeportivo() {
        return this.unConceptoDeportivo;
    }

    public void setUnConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        this.unConceptoDeportivo = unConceptoDeportivo;
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

//--------------------------------PAGO DEUDA------------------------------------
    public void agregarPagoDeuda(EntityManager entityManager, Date fecha, double monto, String observacion) {
        this.pagosDeuda.add(new PagoDeuda(fecha, monto, observacion));

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Agregar PagoDeuda en Deuda" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarPagoDeuda(EntityManager entityManager, PagoDeuda unPagoDeuda, Date fecha, double monto, String observacion, boolean borradoLogico) {
        unPagoDeuda.setFecha(fecha);
        unPagoDeuda.setMonto(monto);
        unPagoDeuda.setObservacion(observacion);
        unPagoDeuda.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar PagoDeuda en Deuda" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarPagoDeuda(EntityManager entityManager, PagoDeuda unPagoDeuda) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unPagoDeuda.setBorradoLogico(true);
            entityManager.persist(unPagoDeuda);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error en Eliminar CuerpoTecnico" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PAGO DEUDA----------------------------------

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
}
