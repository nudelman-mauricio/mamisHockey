package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

@Embeddable
public class Integrante implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @OneToOne(optional = false, targetEntity = Socia.class)
    private Socia unaSocia;

    @Basic
    private String camiseta;

    @Basic
    private boolean local;
    // </editor-fold>

    public Integrante() {

    }

    public Integrante(EntityManager entityManager, Socia unaSocia, String camiseta, boolean local) {
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
