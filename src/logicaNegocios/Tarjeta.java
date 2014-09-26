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
public class Tarjeta implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTarjeta;

    @Basic
    private String tipo;

    @Basic
    private String motivo;

    @Basic
    private String tiempo;

    @Basic
    private String minuto;

    @Basic
    private boolean computado;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public Tarjeta() {

    }

    public Tarjeta(EntityManager entityManager, String tipo, String motivo, String tiempo, String minuto) {
        this.tipo = tipo;
        this.motivo = motivo;
        this.tiempo = tiempo;
        this.minuto = minuto;
        this.computado = false;
        this.borradoLogico = false;        
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Long getIdTarjeta() {
        return this.idTarjeta;
    }

    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getMinuto() {
        return this.minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public boolean isComputado() {
        return this.computado;
    }

    public void setComputado(boolean computado) {
        this.computado = computado;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return tiempo + "T " + minuto + "'";
}

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Tarjeta) {
            Tarjeta unaTarjeta = (Tarjeta) aux;
            if (Integer.parseInt(this.tiempo) > Integer.parseInt(unaTarjeta.getTiempo())) {
                retorno = 1;
            } else {
                if (Integer.parseInt(this.tiempo) == Integer.parseInt(unaTarjeta.getTiempo())) {
                    if (Integer.parseInt(this.minuto) > Integer.parseInt(unaTarjeta.getMinuto())) {
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
