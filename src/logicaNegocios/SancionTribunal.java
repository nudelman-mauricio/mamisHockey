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
public class SancionTribunal implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date vencimiento;

    @Basic
    private int cantFechas;

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
    private int cantFechasCumplidas;

    @Basic
    private String numeroResolucion;

    @Basic
    private boolean borradoLogico;

    public SancionTribunal(EntityManager entityManager , Date fecha, String motivo, String detalles) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.detalles = detalles;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
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
//----------------------------- FIN GETERS Y SETERS ----------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof SancionTribunal) {
            SancionTribunal sancionTribunal = (SancionTribunal) aux;
            if (this.idSancionTribunal > sancionTribunal.idSancionTribunal) {
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
            System.out.println("Error de Persistir SancionTribunal" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
