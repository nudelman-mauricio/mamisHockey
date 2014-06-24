package logicaNegocios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;
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
    // </editor-fold>

    public Deuda() {

    }

    public Deuda(EntityManager entityManager, Date fechaGeneracion, String concepto, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        this.fechaGeneracion = fechaGeneracion;
        this.concepto = concepto;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.cuotas = new TreeSet();
        this.crearCuotas(entityManager, montoTotal, cantCuotas, primerVencimiento);
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
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
    // </editor-fold>

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

    // <editor-fold defaultstate="collapsed" desc="Persistencia">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Cuotas">
    private void crearCuotas(EntityManager entityManager, double montoTotal, int cantCuotas, Date vencimiento) {
        if (montoTotal == 0) {
            Cuota unaCuota = new Cuota(entityManager, montoTotal, vencimiento, ("1/1"));            
            unaCuota.setUnPagoCuota(new PagoCuota(entityManager, montoTotal, vencimiento, "Pago deuda de monto 0"));
            this.cuotas.add(unaCuota);
        } else {
            double montoCuotas = (int) (montoTotal / cantCuotas);
            double montoPrimeraCuota = (montoCuotas + (montoTotal - (montoCuotas * cantCuotas)));
            //Se crea la primer cuota conteniendo el resto de la division
            this.cuotas.add(new Cuota(entityManager, montoPrimeraCuota, vencimiento, ("1/" + Integer.toString(cantCuotas))));
            //Se crean el resto de las cuotas si corresponde
            for (int i = 1; i < cantCuotas; i++) {
                vencimiento.setMonth(vencimiento.getMonth() + 1);
                Cuota unaCuota = new Cuota(entityManager, montoCuotas, vencimiento, (Integer.toString(i + 1) + "/" + Integer.toString(cantCuotas)));
                this.cuotas.add(unaCuota);
            }
        }
    }

    /**
     * Elimina todas las cuotas de una Deuda
     *
     * @param entityManager
     * @return Devuelve el Monto que la asociacion tendria que devolver como
     * nota de credito
     */
    public double eliminarTodasLasCuotas(EntityManager entityManager) {
        double montoNotaCredito = 0;
        for (Cuota aux : this.cuotas) {
            montoNotaCredito += aux.setBorradoLogico(true, entityManager);
            aux.persistir(entityManager);
        }
        return montoNotaCredito;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Montos">
    /**
     * Devueve el Monto de una Deuda sumando todas las cuotas NO las BORRADAS
     */
    public double obtenerMontoTotal() {
        double monto = 0;
        for (Cuota aux : this.cuotas) {
            if (!aux.isBorradoLogico()) {
                monto += aux.getMonto();
            }
        }
        return monto;
    }

    /**
     * Devueve el Monto de una Deuda sumando todas las cuotas no pagas NO
     * Borradas
     */
    public double obtenerMontoTotalAdeudado() {
        double montoAdeudado = 0;
        for (Cuota aux : this.cuotas) {
            if ((!aux.isBorradoLogico()) && (aux.getUnPagoCuota() != null)) {
                if (!aux.getUnPagoCuota().isBorradoLogico()) {
                    montoAdeudado += aux.getMonto();
                }
            }
        }
        return montoAdeudado;
    }
    // </editor-fold>
}
