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
import javax.swing.JOptionPane;

@Entity
public class Frecuencia implements Serializable, Comparable {
    
    // <editor-fold defaultstate="collapsed" desc="Atributos">
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
    // </editor-fold>
    
    public Frecuencia() {
    }

    public Frecuencia(EntityManager entityManager, String diaGeneracion, String diaVencimiento) {
        this.diaGeneracion = diaGeneracion;
        this.diaVencimiento = diaVencimiento;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
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
    // </editor-fold>

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

    // <editor-fold defaultstate="collapsed" desc="Mes">
    public void agregarMes(EntityManager entityManager, Mes unMes) {
        this.meses.add(unMes);
        this.persistir(entityManager);
    }

    public void quitarTodosMeses(EntityManager entityManager) {
        this.meses.clear();
        this.persistir(entityManager);
    }
    // </editor-fold>
}
