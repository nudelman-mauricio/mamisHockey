package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TipoEstado implements Serializable, Comparable {

    @Basic
    private double monto;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTipoEstado;

    @OneToOne(optional = false, targetEntity = Frecuencia.class)
    private Frecuencia unaFrecuencia;

    @Basic
    private String nombre;

    @Basic
    private boolean borradoLogico;

    public TipoEstado() {

    }

    public TipoEstado(double monto, Frecuencia unaFrecuencia, String nombre) {
        this.monto = monto;
        this.unaFrecuencia = unaFrecuencia;
        this.nombre = nombre;
        this.borradoLogico = false;
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Long getIdTipoEstado() {
        return this.idTipoEstado;
    }

    public void setIdTipoEstado(Long idTipoEstado) {
        this.idTipoEstado = idTipoEstado;
    }

    public Frecuencia getUnaFrecuencia() {
        return this.unaFrecuencia;
    }

    public void setUnaFrecuencia(Frecuencia unaFrecuencia) {
        this.unaFrecuencia = unaFrecuencia;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (aux instanceof TipoEstado) {
            TipoEstado unTipoEstado = (TipoEstado) aux;
            if (this.idTipoEstado > unTipoEstado.idTipoEstado) {
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
            System.out.println("Error de Persistir TipoEstado" + e.getMessage());
            tx.rollback();
        }
    }
//------------------------------FIN PERSISTENCIA--------------------------------
}
