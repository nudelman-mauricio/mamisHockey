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

    @Basic
    private String concepto;

    @OneToMany(targetEntity = Cuota.class)
    private Collection<Cuota> cuotas;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;

    public Deuda() {

    }

    public Deuda(EntityManager entityManager, Date fechaGeneracion, String concepto, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        this.fechaGeneracion = fechaGeneracion;
        this.concepto = concepto;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.persistir(entityManager);
        this.crearCuotas(entityManager, montoTotal, cantCuotas, primerVencimiento);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public Long getIdDeuda() {
        return this.idDeuda;
    }

    public void setIdDeuda(Long idDeuda) {
        this.idDeuda = idDeuda;
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

    public Collection<Cuota> getCuotas() {
        return this.cuotas;
    }

    public void setCuotas(Collection<Cuota> cuotas) {
        this.cuotas = cuotas;
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
            Deuda unaDeuda = (Deuda) aux;
            if (this.idDeuda > unaDeuda.idDeuda) {
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

//-----------------------------------CUOTAS-------------------------------------    
    public void crearCuotas(EntityManager entityManager, double montoTotal, int cantCuotas, Date primerVencimiento) {
        Date vencimiento = primerVencimiento;
        double auxMontoCuotas = (int) (montoTotal / cantCuotas);
        for (int i = 0; i < cantCuotas; i++) {
            if (i==0){
                
            }            
            Cuota unaCuota = new Cuota(entityManager, auxMontoCuotas, vencimiento, (Integer.toString(i + 1) + "/" + Integer.toString(cantCuotas)));
            this.cuotas.add(unaCuota);
            vencimiento.setMonth(vencimiento.getMonth() + 1);
        }
        this.persistir(entityManager);
    }

    public void eliminarCuota(EntityManager entityManager, Cuota unaCuota) {
        unaCuota.setBorradoLogico(true);
        unaCuota.persistir(entityManager);
    }

    public void eliminarTodasLasCuotas(EntityManager entityManager) {
        for (Cuota aux : this.cuotas) {
            aux.setBorradoLogico(true);
        }
    }
//---------------------------------FIN CUOTAS-----------------------------------

    /**
     * Devueve un float con el Monto de una Deuda sumando todas las cuotas
     */
    public double obtenerMontoTotal() {
        double monto = 0;
        for (Cuota aux : this.cuotas) {
            monto += aux.getMonto();
        }
        return monto;
    }

    /**
     * Devueve un float con el Monto de una Deuda sumando todas las cuotas no
     * pagas
     */
    public double obtenerMontoTotalAdeudado() {
        double monto = 0;
        for (Cuota aux : this.cuotas) {
            if (!aux.isBorradoLogico()) {
                monto += aux.getMonto();
            }
        }
        return monto;
    }
}
