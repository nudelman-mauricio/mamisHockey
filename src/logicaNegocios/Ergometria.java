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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

@Entity
public class Ergometria implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaCaducidad;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaRealizacion;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idErgometria;

    @Basic
    private boolean aprobado;

    @Basic
    private String comentarios;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public Ergometria() {

    }

    public Ergometria(EntityManager entityManager, Date fechaCaducidad, Date fechaRealizacion, boolean aprobado, String comentarios) {
        this.fechaCaducidad = fechaCaducidad;
        this.fechaRealizacion = fechaRealizacion;
        this.aprobado = aprobado;
        this.comentarios = comentarios;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Date getFechaCaducidad() {
        return this.fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getFechaRealizacion() {
        return this.fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Long getIdErgometria() {
        return this.idErgometria;
    }

    public void setIdErgometria(Long idErgometria) {
        this.idErgometria = idErgometria;
    }

    public boolean isAprobado() {
        return this.aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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
        if (aux instanceof Ergometria) {
            Ergometria ergometria = (Ergometria) aux;
            retorno = this.fechaRealizacion.compareTo(ergometria.getFechaRealizacion());
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
}
