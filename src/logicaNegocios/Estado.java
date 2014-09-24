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
public class Estado implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEstado;

    @OneToOne(optional = false, targetEntity = TipoEstado.class)
    private TipoEstado unTipoEstado;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public Estado() {

    }

    public Estado(EntityManager entityManager, Date fecha, TipoEstado unTipoEstado) {
        this.fecha = fecha;
        this.unTipoEstado = unTipoEstado;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public TipoEstado getUnTipoEstado() {
        return this.unTipoEstado;
    }

    public void setUnTipoEstado(TipoEstado unTipoEstado) {
        this.unTipoEstado = unTipoEstado;
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
        if (aux instanceof Estado) {
            Estado estado = (Estado) aux;
            retorno = this.fecha.compareTo(estado.getFecha());
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
}
