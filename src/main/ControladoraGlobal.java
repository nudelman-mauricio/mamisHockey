package main;

import javax.persistence.EntityManager;

public class ControladoraGlobal {

    private ControladoraContabilidad unaControladoraContabilidad;
    private ControladoraEntidades unaControladoraEntidades;
    private ControladoraDeportiva unaControladoraDeportiva;

    public ControladoraGlobal(EntityManager em) {
        this.unaControladoraContabilidad = new ControladoraContabilidad(em);
        this.unaControladoraEntidades = new ControladoraEntidades(em);
        this.unaControladoraDeportiva = new ControladoraDeportiva(em);
    }

    public void crearCategoria(int cantMenores, String nombre) {
        this.unaControladoraDeportiva.crearCategoria(cantMenores, nombre);
    }

    public ControladoraContabilidad getUnaControladoraContabilidad() {
        return this.unaControladoraContabilidad;
    }

    public void setUnaControladoraContabilidad(ControladoraContabilidad unaControladoraContabilidad) {
        this.unaControladoraContabilidad = unaControladoraContabilidad;
    }

    public ControladoraEntidades getUnaControladoraEntidades() {
        return this.unaControladoraEntidades;
    }

    public void setUnaControladoraEntidades(ControladoraEntidades unaControladoraEntidades) {
        this.unaControladoraEntidades = unaControladoraEntidades;
    }

    public ControladoraDeportiva getUnaControladoraDeportiva() {
        return this.unaControladoraDeportiva;
    }

    public void setUnaControladoraDeportiva(ControladoraDeportiva unaControladoraDeportiva) {
        this.unaControladoraDeportiva = unaControladoraDeportiva;
    }
}
