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
public class FechaTorneo implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Basic
    private int numeroFecha;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFecha;

    @OneToMany(targetEntity = Partido.class)
    private Collection<Partido> partidos;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public FechaTorneo() {

    }

    public FechaTorneo(EntityManager entityManager, int numeroFecha) {
        this.numeroFecha = numeroFecha;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public int getNumeroFecha() {
        return this.numeroFecha;
    }

    public void setNumeroFecha(int numeroFecha) {
        this.numeroFecha = numeroFecha;
    }

    public Long getIdFecha() {
        return this.idFecha;
    }

    public void setIdFecha(Long idFecha) {
        this.idFecha = idFecha;
    }

    public Collection<Partido> getPartidos() {
        return this.partidos;
    }

    public void setPartidos(Collection<Partido> partidos) {
        this.partidos = partidos;
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
        if (aux instanceof FechaTorneo) {
            FechaTorneo fechaTorneo = (FechaTorneo) aux;
            if (this.numeroFecha > fechaTorneo.getNumeroFecha()) {
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

    // <editor-fold defaultstate="collapsed" desc="Partidos">
    public void agregarPartido(EntityManager entityManager, Partido unPartido) {
        this.partidos.add(unPartido);
        this.persistir(entityManager);
    }
    // </editor-fold>
}
