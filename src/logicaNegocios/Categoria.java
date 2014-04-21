package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public  class Categoria implements Serializable {

    @Basic
    private String cantMenores;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idCategoria;

    @Basic
    private String nombre;

    public Categoria(){

    }


   public String getCantMenores() {
        return this.cantMenores;
    }


  public void setCantMenores (String cantMenores) {
        this.cantMenores = cantMenores;
    }



   public Long getIdCategoria() {
        return this.idCategoria;
    }


  public void setIdCategoria (Long idCategoria) {
        this.idCategoria = idCategoria;
    }



   public String getNombre() {
        return this.nombre;
    }


  public void setNombre (String nombre) {
        this.nombre = nombre;
    }

}

