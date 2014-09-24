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
public class TipoCancha implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTipoCancha;

    @Basic
    private String nombre;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public TipoCancha() {

    }

    public TipoCancha(EntityManager entityManager, String nombre) {
        this.nombre = nombre;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Long getIdTipoCancha() {
        return this.idTipoCancha;
    }

    public void setIdTipoCancha(Long idTipoCancha) {
        this.idTipoCancha = idTipoCancha;
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
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof TipoCancha) {
            TipoCancha unTipoCancha = (TipoCancha) aux;
            if (this.idTipoCancha > unTipoCancha.idTipoCancha) {
                retorno = 1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return nombre;
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
