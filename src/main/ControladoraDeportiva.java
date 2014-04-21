package main;

import java.util.Collection;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import logicaNegocios.Categoria;
import logicaNegocios.Sancion;
import logicaNegocios.Torneo;

public class ControladoraDeportiva {

    private Collection<Torneo> torneos;
    private Collection<Categoria> categorias;
    private Collection<Sancion> sanciones;
    private EntityManager entityManager;

    public ControladoraDeportiva(EntityManager em) {
        this.entityManager = em;        
        
        Query q = em.createQuery("SELECT unaCategoria FROM Categoria unaCategoria");
        this.categorias = new TreeSet(q.getResultList());

    }

    public void crearCategoria(int cantMenores, String nombre) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Categoria unaCategoria = new Categoria(cantMenores, nombre);
            entityManager.persist(unaCategoria);
            //this.categorias.add(unaCategoria);
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception de Guardar");
            tx.rollback();
        }
    }

    public Collection<Torneo> getTorneos() {
        return this.torneos;
    }

    public void setTorneos(Collection<Torneo> torneos) {
        this.torneos = torneos;
    }

    public Collection<Categoria> getCategorias() {
        return this.categorias;
    }

    public void setCategorias(Collection<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Collection<Sancion> getSanciones() {
        return this.sanciones;
    }

    public void setSanciones(Collection<Sancion> sanciones) {
        this.sanciones = sanciones;
    }

}
