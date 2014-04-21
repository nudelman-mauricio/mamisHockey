package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public  class ControladoraDeportiva implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idControladoraDeportiva;

    @OneToMany(targetEntity=Torneo.class)
    private Collection<Torneo> torneos;

    @OneToMany(targetEntity=Categoria.class)
    private Collection<Categoria> categorias;

    @OneToMany(targetEntity=Sancion.class)
    private Collection<Sancion> sanciones;

    public ControladoraDeportiva(){

    }


   public Long getIdControladoraDeportiva() {
        return this.idControladoraDeportiva;
    }


  public void setIdControladoraDeportiva (Long idControladoraDeportiva) {
        this.idControladoraDeportiva = idControladoraDeportiva;
    }



   public Collection<Torneo> getTorneos() {
        return this.torneos;
    }


  public void setTorneos (Collection<Torneo> torneos) {
        this.torneos = torneos;
    }



   public Collection<Categoria> getCategorias() {
        return this.categorias;
    }


  public void setCategorias (Collection<Categoria> categorias) {
        this.categorias = categorias;
    }



   public Collection<Sancion> getSanciones() {
        return this.sanciones;
    }


  public void setSanciones (Collection<Sancion> sanciones) {
        this.sanciones = sanciones;
    }

}

