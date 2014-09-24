package logicaNegocios;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;

@Entity
public class Deuda implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDeuda;

    @OneToOne(targetEntity = ConceptoDeportivo.class)
    private ConceptoDeportivo unConceptoDeportivo;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaGeneracion;

    @OneToMany(targetEntity = Cuota.class)
    private Collection<Cuota> cuotas;

    @Basic
    private String observacion;

    @Basic
    private boolean borradoLogico;
// </editor-fold>

    public Deuda() {

    }

    public Deuda(EntityManager entityManager, Date fechaGeneracion, ConceptoDeportivo unConceptoDeportivo, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        this.fechaGeneracion = fechaGeneracion;
        this.unConceptoDeportivo = unConceptoDeportivo;
        this.observacion = observacion;
        this.borradoLogico = false;
        this.cuotas = new ArrayList();
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

    public ConceptoDeportivo getUnConceptoDeportivo() {
        return this.unConceptoDeportivo;
    }

    public void setUnConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        this.unConceptoDeportivo = unConceptoDeportivo;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
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
    public String toString() {
        return unConceptoDeportivo.getConcepto();
    }

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Deuda) {
            Deuda unaDeuda = (Deuda) aux;
            retorno = this.fechaGeneracion.compareTo(unaDeuda.getFechaGeneracion());
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
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Avisar al Servicio Técnico." + System.getProperty("line.separator") + "LMLSOLUCIONESINFORMATICAS@GMAIL.COM");
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
            Date vencimientoCuotas;
            for (int i = 0; i < cantCuotas; i++) {
                vencimientoCuotas = new Date();
                vencimientoCuotas.setDate(vencimiento.getDate());
                vencimientoCuotas.setMonth(vencimiento.getMonth());
                vencimientoCuotas.setYear(vencimiento.getYear());
                if (i == 0) {
                    //Se crea la primer cuota conteniendo el resto de la division
                    this.cuotas.add(new Cuota(entityManager, montoPrimeraCuota, vencimientoCuotas, ("1/" + Integer.toString(cantCuotas))));
                } else {
                    //Se crea el resto de las cuotas
                    this.cuotas.add(new Cuota(entityManager, montoCuotas, vencimientoCuotas, (Integer.toString(i + 1) + "/" + Integer.toString(cantCuotas))));
                }
                vencimiento.setMonth(vencimiento.getMonth() + 1);
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

    /**
     * Devuelve la cantidad de cuotas no borradas de la deuda
     *
     * @return cantidad de cuotas
     */
    public int getCantidadCuotas() {
        int cantidadCuotas = 0;
        for (Cuota aux : this.cuotas) {
            if (!aux.isBorradoLogico()) {
                cantidadCuotas++;
            }
        }
        return cantidadCuotas;
    }

    /**
     * Devuelve el vencimiento de la primera cuota
     *
     * @return Date
     */
    public Date getPrimerVencimiento() {
        //inicializar con dia de hoy        
        Date primerVencimiento = null;
        for (Cuota unaCuota : this.cuotas) {
            if ((!unaCuota.isBorradoLogico()) && (primerVencimiento == null)) {
                primerVencimiento = unaCuota.getFechaVencimiento();
            }
            if ((!unaCuota.isBorradoLogico()) && (primerVencimiento.after(unaCuota.getFechaVencimiento()))) {
                primerVencimiento = unaCuota.getFechaVencimiento();
            }
        }
        return primerVencimiento;
    }

    /**
     * Devuelve el monto de la primera cuota no borrada
     *
     * @return Monto
     */
    public Double getPrimerMonto() {
        Double primerMonto = 0.0;
        for (Cuota aux : this.cuotas) {
            if ((!aux.isBorradoLogico()) && (primerMonto < aux.getMonto())) {
                primerMonto = aux.getMonto();
            }
        }
        return primerMonto;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Montos">
    /**
     * Devueve el Monto de una Deuda sumando todas las cuotas NO las BORRADAS
     */
    public double getMontoTotal() {
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
    public double getMontoTotalAdeudado() {
        double montoAdeudado = 0;
        for (Cuota unaCuota : this.cuotas) {
            if (!unaCuota.isBorradoLogico()) {
                if (!unaCuota.isSaldado()) {
                    if (unaCuota.getUnPagoCuota() != null) {
                        if (!unaCuota.getUnPagoCuota().isBorradoLogico()) {
                            montoAdeudado += (unaCuota.getMonto() - unaCuota.getUnPagoCuota().getMonto());
                        }
                    } else {
                        montoAdeudado += unaCuota.getMonto();
                    }
                }
            }
        }
        return montoAdeudado;
    }

    /**
     * Devuelve True solo si la Deuda esta totalmente pagada
     *
     * @return True si estÃƒÂ¡ pagada
     */
    public boolean isSaldado() {
        boolean saldado = false;
        if (getMontoTotalAdeudado() == 0) {
            saldado = true;
        }
        return saldado;
    }

    /**
     * Devuelve True si hay al menos una cuota que no esté totalmente pagada y
     * ya paso el vencimiento
     *
     * @return
     */
    public boolean isVencido(Date unaFecha) {
        boolean resultado = false;
        if (!isSaldado()) {
            for (Cuota unaCuota : this.getCuotas()) {
                if ((!unaCuota.isBorradoLogico()) && (unaCuota.isVencido(unaFecha))) {
                    resultado = true;
                }
            }
        }
        return resultado;
    }
    // </editor-fold>
}
