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
public class Gol implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Basic
    private String minuto;

    @Basic
    private String tiempo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGol;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public Gol() {

    }

    public Gol(EntityManager entityManager, String tiempo, String minuto) {
        this.tiempo = tiempo;
        this.minuto = minuto;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public String getMinuto() {
        return this.minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Long getIdGol() {
        return this.idGol;
    }

    public void setIdGol(Long idGol) {
        this.idGol = idGol;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
    // </editor-fold>

    public String toString() {
        return tiempo + "T " + minuto + "'";
    }

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Gol) {
            Gol gol = (Gol) aux;
            if (Integer.parseInt(this.tiempo) > Integer.parseInt(gol.getTiempo())) {
                retorno = 1;
            } else {
                if (Integer.parseInt(this.tiempo) == Integer.parseInt(gol.getTiempo())) {
                    if (Integer.parseInt(this.minuto) > Integer.parseInt(gol.getMinuto())) {
                        retorno = 1;
                    }
                }
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
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Avisar al Servicio Tecnico." + System.getProperty("line.separator") + "LMLSOLUCIONESINFORMATICAS@GMAIL.COM");
            tx.rollback();
        }
    }
    // </editor-fold>
}
