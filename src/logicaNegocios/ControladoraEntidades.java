package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public  class ControladoraEntidades implements Serializable {

    @OneToMany(targetEntity=cuerpoTecnico.class)
    private Collection<cuerpoTecnico> cuerpoTecnicos;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idControladoraEntidades;

    @OneToMany(targetEntity=Arbitro.class)
    private Collection<Arbitro> arbitros;

    @OneToMany(targetEntity=Club.class)
    private Collection<Club> clubes;

    @OneToMany(targetEntity=Socia.class)
    private Collection<Socia> socias;

    @OneToMany(targetEntity=Localidad.class)
    private Collection<Localidad> localidades;

    public ControladoraEntidades(){

    }


   public Collection<cuerpoTecnico> getCuerpoTecnicos() {
        return this.cuerpoTecnicos;
    }


  public void setCuerpoTecnicos (Collection<cuerpoTecnico> cuerpoTecnicos) {
        this.cuerpoTecnicos = cuerpoTecnicos;
    }



   public Long getIdControladoraEntidades() {
        return this.idControladoraEntidades;
    }


  public void setIdControladoraEntidades (Long idControladoraEntidades) {
        this.idControladoraEntidades = idControladoraEntidades;
    }



   public Collection<Arbitro> getArbitros() {
        return this.arbitros;
    }


  public void setArbitros (Collection<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }



   public Collection<Club> getClubes() {
        return this.clubes;
    }


  public void setClubes (Collection<Club> clubes) {
        this.clubes = clubes;
    }



   public Collection<Socia> getSocias() {
        return this.socias;
    }


  public void setSocias (Collection<Socia> socias) {
        this.socias = socias;
    }



   public Collection<Localidad> getLocalidades() {
        return this.localidades;
    }


  public void setLocalidades (Collection<Localidad> localidades) {
        this.localidades = localidades;
    }

}

