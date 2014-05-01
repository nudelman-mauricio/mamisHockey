package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria implements Serializable, Comparable {

    @Basic
    private int cantMenores;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategoria;

    @Basic
    private String nombre;

    @Basic
    private boolean borradoLogico;

    public Categoria() {
    }

    public Categoria(int cantMenores, String nombre) {
        this.cantMenores = cantMenores;
        this.nombre = nombre;
        this.borradoLogico = false;
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public int getCantMenores() {
        return this.cantMenores;
    }

    public void setCantMenores(int cantMenores) {
        this.cantMenores = cantMenores;
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
//----------------------------- FIN GETERS Y SETERS ----------------------------

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

    //----------------------------------- TEMPORAL BORRAR PARA LA VERSION FINAL ---------------
    @Override
    public String toString() {
        return "Categoria{" + "cantMenores=" + cantMenores + ", idCategoria=" + idCategoria + ", nombre=" + nombre + ", borradoLogico=" + borradoLogico + '}';
    }

}
