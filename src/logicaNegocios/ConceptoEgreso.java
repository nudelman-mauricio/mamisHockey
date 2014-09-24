package logicaNegocios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.JOptionPane;

@Entity
public class ConceptoEgreso implements Serializable, Comparable {
    
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConceptoEgreso;

    @Basic
    private String nombre;

    @Basic
    private String detalle;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public ConceptoEgreso() {

    }

    public ConceptoEgreso(EntityManager entityManager, String nombre, String detalle) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Long getIdConceptoEgreso() {
        return this.idConceptoEgreso;
    }

    public void setIdConceptoEgreso(Long idConceptoEgreso) {
        this.idConceptoEgreso = idConceptoEgreso;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
    // </editor-fold>

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

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof ConceptoEgreso) {
            ConceptoEgreso conceptoEgreso = (ConceptoEgreso) aux;
            if (this.idConceptoEgreso > conceptoEgreso.idConceptoEgreso) {
                retorno = 1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
