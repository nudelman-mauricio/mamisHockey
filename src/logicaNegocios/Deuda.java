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

    public Deuda(EntityManager entityManager, Date fechaGeneracion, String concepto, String observacion,double montoTotal, int cantCuotas, Date Vencimiento) {
        this.fechaGeneracion = fechaGeneracion;
        this.concepto = concepto;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.persistir(entityManager);
        
        for (int i = 0; i < cantCuotas; i++) {
            this.crearCuota(entityManager, montoTotal/cantCuotas, fechaGeneracion);
            fechaGeneracion.setMonth(fechaGeneracion.getMonth()+1);
        }
    }
    
    //-----------------------------------CUOTAS-------------------------------------
    public void crearCuota(EntityManager entityManager, double monto, Date fechaVencimiento) {
        Cuota unaCuota = new Cuota(entityManager, monto, fechaVencimiento);
        this.agregarCuota(entityManager, unaCuota);
    }

    public void modificarCuota(EntityManager entityManager,Cuota unaCuota, double monto, Date fechaVencimiento, PagoCuota unPagoCuota, boolean borradoLogico) {
        unaCuota.setMonto(monto);
        unaCuota.setFechaVencimiento(fechaVencimiento);
        unaCuota.setUnPagoCuota(unPagoCuota);
        unaCuota.setBorradoLogico(borradoLogico);
        unaCuota.persistir(entityManager);
    }

    //REVEER SI ESTO QUEDA-------------------------PELA-------------------------
    public void cambiarCuotaDeDeuda(EntityManager entityManager,Cuota unaCuota, Deuda unaDeudaActual, Deuda unaDeudaNueva) {
        unaDeudaActual.quitarCuota(entityManager, unaCuota);
        unaDeudaNueva.agregarCuota(entityManager, unaCuota);
    }
    //FIN REVEER SI ESTO QUEDA-------------------------PELA---------------------
    
    public void eliminarCuota(EntityManager entityManager,Cuota unaCuota) {
        unaCuota.setBorradoLogico(true);
        unaCuota.persistir(entityManager);
    }
//---------------------------------FIN CUOTAS-----------------------------------

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

//----------------------------------CUOTAS--------------------------------------
    public void agregarCuota(EntityManager entityManager, Cuota unaCuota) {
        this.cuotas.add(unaCuota);
        this.persistir(entityManager);
    }

    public void quitarCuota(EntityManager entityManager, Cuota unaCuota) {
        this.cuotas.remove(unaCuota);
        this.persistir(entityManager);
    }
//--------------------------------FIN CUOTAS------------------------------------
}
