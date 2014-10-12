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
public class SancionTribunal implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Temporal(TemporalType.DATE)
    @Basic
    private Date vencimiento = null;

    @Basic
    private int cantFechas = 0;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @OneToOne(optional = false, targetEntity = Tarjeta.class)
    private Tarjeta unaTarjeta;

    @Basic
    private String motivo;

    @OneToOne(optional = false, targetEntity = Partido.class)
    private Partido unPartido;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSancionTribunal;

    @Basic
    private String detalles;

    @Basic
    private int cantFechasCumplidas = 0;

    @Basic
    private String numeroResolucion;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public SancionTribunal() {

    }

    public SancionTribunal(EntityManager entityManager, Partido unPartido, Date fecha, String motivo, String detalles) {
        this.unPartido = unPartido;
        this.fecha = fecha;
        this.motivo = motivo;
        this.detalles = detalles;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    public SancionTribunal(EntityManager entityManager, Partido unPartido, Date fecha, String motivo, String detalles, int cantidadFechasCastigo, Tarjeta unaTarjetaRoja) {
        this.unPartido = unPartido;
        this.fecha = fecha;
        this.motivo = motivo;
        this.detalles = detalles;
        this.cantFechas = cantidadFechasCastigo;
        this.unaTarjeta = unaTarjetaRoja;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Date getVencimiento() {
        return this.vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getCantFechas() {
        return this.cantFechas;
    }

    public void setCantFechas(int cantFechas) {
        this.cantFechas = cantFechas;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tarjeta getUnaTarjeta() {
        return this.unaTarjeta;
    }

    public void setUnaTarjeta(Tarjeta unaTarjeta) {
        this.unaTarjeta = unaTarjeta;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Partido getUnPartido() {
        return this.unPartido;
    }

    public void setUnPartido(Partido unPartido) {
        this.unPartido = unPartido;
    }

    public Long getIdSancionTribunal() {
        return this.idSancionTribunal;
    }

    public void setIdSancionTribunal(Long idSancionTribunal) {
        this.idSancionTribunal = idSancionTribunal;
    }

    public String getDetalles() {
        return this.detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public int getCantFechasCumplidas() {
        return this.cantFechasCumplidas;
    }

    public void setCantFechasCumplidas(int cantFechasCumplidas) {
        this.cantFechasCumplidas = cantFechasCumplidas;
    }

    public String getNumeroResolucion() {
        return this.numeroResolucion;
    }

    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
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
        if (aux instanceof SancionTribunal) {
            SancionTribunal sancionTribunal = (SancionTribunal) aux;
            retorno = this.fecha.compareTo(sancionTribunal.getFecha());
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

    /**
     * Devuelve True solo si la Sancion no se cumplio totalmente, ya sea porque
     * no vencio todavia o porque no se cumplieron todas las fechas de
     * penalizacion
     *
     * @param unaFecha
     * @return
     */
    public boolean isVigente(Date unaFecha) {
        boolean resultado = false;
        if (this.borradoLogico == false) {
            if ((this.cantFechas != 0) && (this.cantFechas > this.cantFechasCumplidas)) {
                resultado = true;
            } else {
                if ((this.cantFechas == 0) && (this.vencimiento != null)) {
                    if (this.vencimiento.after(unaFecha)) {
                        resultado = true;
                    }
                }
            }
        }
        return resultado;
    }

    public void sumarFechaCumplida() {
        this.cantFechasCumplidas++;
    }
}
