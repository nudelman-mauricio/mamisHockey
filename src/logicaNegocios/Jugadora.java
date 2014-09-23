package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;

@Entity
@Table
public class Jugadora implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @OneToOne(targetEntity = Socia.class)
    private Socia unaSocia;

    @Basic
    private String camiseta;

    @Basic
    private boolean local;
    // </editor-fold>

    public Jugadora() {

    }

    public Jugadora(EntityManager entityManager, Socia unaSocia, String camiseta, boolean local) {
        this.unaSocia = unaSocia;
        this.camiseta = camiseta;
        this.local = local;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Socia getUnaSocia() {
        return this.unaSocia;
    }

    public void setUnaSocia(Socia unaSocia) {
        this.unaSocia = unaSocia;
    }

    public String getCamiseta() {
        return this.camiseta;
    }

    public void setCamiseta(String camiseta) {
        this.camiseta = camiseta;
    }

    public boolean isLocal() {
        return this.local;
    }

    public void setLocal(boolean local) {
        this.local = local;
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
}
