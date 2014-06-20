package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Frecuencia implements Serializable, Comparable {

    @Basic
    private String diaGeneracion;

    @Basic
    private String diaVencimiento;

    @OneToMany(targetEntity = Mes.class)
    private Collection<Mes> meses;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFrecuencia;

    @Basic
    private boolean borradoLogico;

    public Frecuencia() {

    }

    public Frecuencia(EntityManager entityManager, String diaGeneracion, String diaVencimiento) {
        this.diaGeneracion = diaGeneracion;
        this.diaVencimiento = diaVencimiento;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public String getDiaGeneracion() {
        return this.diaGeneracion;
    }

    public void setDiaGeneracion(String diaGeneracion) {
        this.diaGeneracion = diaGeneracion;
    }

    public String getDiaVencimiento() {
        return this.diaVencimiento;
    }

    public void setDiaVencimiento(String diaVencimiento) {
        this.diaVencimiento = diaVencimiento;
    }

    public Collection<Mes> getMeses() {
        return this.meses;
    }

    public void setMeses(Collection<Mes> meses) {
        this.meses = meses;
    }

    public Long getIdFrecuencia() {
        return this.idFrecuencia;
    }

    public void setIdFrecuencia(Long idFrecuencia) {
        this.idFrecuencia = idFrecuencia;
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
        if (aux instanceof Frecuencia) {
            Frecuencia frecuencia = (Frecuencia) aux;
            if (this.idFrecuencia > frecuencia.idFrecuencia) {
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
            System.out.println("Error de Persistir Frecuencia" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------

//-----------------------------------MESES--------------------------------------
    public void agregarMes(EntityManager entityManager, Mes unMes) {
        this.meses.add(unMes);
        this.persistir(entityManager);
    }

    public void quitarMes(EntityManager entityManager, Mes unMes) {
        this.meses.remove(unMes);
        this.persistir(entityManager);
    }
//---------------------------------FIN MESES------------------------------------
}
