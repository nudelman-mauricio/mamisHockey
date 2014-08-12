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
public class ConceptoIngreso implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long idConceptoIngreso;

    @Basic
    private String nombre;

    @Basic
    private String detalle;

    @Basic
    private boolean borradoLogico;

    public ConceptoIngreso() {

    }

    public ConceptoIngreso(EntityManager entityManager, String nombre, String detalle) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public Long getIdConceptoIngreso() {
        return this.idConceptoIngreso;
    }

    public void setIdConceptoIngreso(Long idConceptoIngreso) {
        this.idConceptoIngreso = idConceptoIngreso;
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
            System.out.println("Error de Persistir Concepto Ingreso" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof ConceptoIngreso) {
            ConceptoIngreso conceptoIngreso = (ConceptoIngreso) aux;
            if (this.idConceptoIngreso > conceptoIngreso.idConceptoIngreso) {
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
