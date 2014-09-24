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
public class Categoria implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Basic
    private int cantidadMinima;

    @Basic
    private int edadParametro;

    @Basic
    private int cantidadMaxima;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategoria;

    @Basic
    private String nombre;

    @Basic
    private boolean borradoLogico;
    // </editor-fold>

    public Categoria() {

    }

    public Categoria(EntityManager entityManager, String nombre, int edadParametro, int cantidadMinima, int cantidadMaxima) {
        this.nombre = nombre;
        this.edadParametro = edadParametro;
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public int getCantidadMinima() {
        return this.cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getEdadParametro() {
        return this.edadParametro;
    }

    public void setEdadParametro(int edadParametro) {
        this.edadParametro = edadParametro;
    }

    public int getCantidadMaxima() {
        return this.cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public Long getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
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
        if (aux instanceof Categoria) {
            Categoria categoria = (Categoria) aux;
            if (this.idCategoria > categoria.idCategoria) {
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
