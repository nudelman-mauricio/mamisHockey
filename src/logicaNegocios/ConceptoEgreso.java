package logicaNegocios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConceptoEgreso implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConceptoEgreso;

    @Basic
    private String nombre;

    @Basic
    private String detalle;

    @Basic
    private boolean borradoLogico;

    public ConceptoEgreso() {
    }

    public ConceptoEgreso(EntityManager entityManager, String nombre, String detalle) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
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
//----------------------------- FIN GETERS Y SETERS ----------------------------

//----------------------------------PERSISTENCIA--------------------------------
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Persistir Concepto Egreso" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------

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
}
