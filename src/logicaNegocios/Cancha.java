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
import javax.swing.JOptionPane;

@Entity
public class Cancha implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Basic
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCancha;

    @Basic
    private boolean borradoLogico;

    @OneToOne(optional = false, targetEntity = TipoCancha.class)
    private TipoCancha unTipoCancha;
    // </editor-fold>

    public Cancha() {

    }

    public Cancha(EntityManager entityManager, String nombre, TipoCancha unTipoCancha) {
        this.nombre = nombre;
        this.unTipoCancha = unTipoCancha;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdCancha() {
        return this.idCancha;
    }

    public void setIdCancha(Long idCancha) {
        this.idCancha = idCancha;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public TipoCancha getUnTipoCancha() {
        return this.unTipoCancha;
    }

    public void setUnTipoCancha(TipoCancha unTipoCancha) {
        this.unTipoCancha = unTipoCancha;
    }
// </editor-fold>

    @Override
    public String toString() {
        return nombre + ", " + this.getUnTipoCancha();
    }

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Cancha) {
            Cancha cancha = (Cancha) aux;
            if (this.idCancha > cancha.idCancha) {
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
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Avisar al Servicio Tecnico." + System.getProperty("line.separator") + "LMLSOLUCIONESINFORMATICAS@GMAIL.COM");
            tx.rollback();
        }
    }
    // </editor-fold>
}
