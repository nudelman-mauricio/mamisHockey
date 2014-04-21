package logicaNegocios;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public  class ControladoraGlobal implements Serializable {

    @OneToOne(optional=false,targetEntity=ControladoraContabilidad.class)
    private ControladoraContabilidad unaControladoraContabilidad;

    @OneToOne(optional=false,targetEntity=ControladoraEntidades.class)
    private ControladoraEntidades unaControladoraEntidades;

    @OneToOne(optional=false,targetEntity=ControladoraDeportiva.class)
    private ControladoraDeportiva unaControladoraDeportiva;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idSistemaAsociacion;

    public ControladoraGlobal(){

    }


   public ControladoraContabilidad getUnaControladoraContabilidad() {
        return this.unaControladoraContabilidad;
    }


  public void setUnaControladoraContabilidad (ControladoraContabilidad unaControladoraContabilidad) {
        this.unaControladoraContabilidad = unaControladoraContabilidad;
    }



   public ControladoraEntidades getUnaControladoraEntidades() {
        return this.unaControladoraEntidades;
    }


  public void setUnaControladoraEntidades (ControladoraEntidades unaControladoraEntidades) {
        this.unaControladoraEntidades = unaControladoraEntidades;
    }



   public ControladoraDeportiva getUnaControladoraDeportiva() {
        return this.unaControladoraDeportiva;
    }


  public void setUnaControladoraDeportiva (ControladoraDeportiva unaControladoraDeportiva) {
        this.unaControladoraDeportiva = unaControladoraDeportiva;
    }



   public Long getIdSistemaAsociacion() {
        return this.idSistemaAsociacion;
    }


  public void setIdSistemaAsociacion (Long idSistemaAsociacion) {
        this.idSistemaAsociacion = idSistemaAsociacion;
    }

}

