package main;

import java.util.Collection;
import javax.persistence.EntityManager;
import logicaNegocios.Arbitro;
import logicaNegocios.Club;
import logicaNegocios.Localidad;
import logicaNegocios.Socia;
import logicaNegocios.cuerpoTecnico;

public class ControladoraEntidades {

    private Collection<cuerpoTecnico> cuerpoTecnicos;
    private Collection<Arbitro> arbitros;
    private Collection<Club> clubes;
    private Collection<Socia> socias;
    private Collection<Localidad> localidades;
    private EntityManager entityManager;


    public ControladoraEntidades(EntityManager em) {
        this.entityManager = em;
    }

    public Collection<cuerpoTecnico> getCuerpoTecnicos() {
        return this.cuerpoTecnicos;
    }

    public void setCuerpoTecnicos(Collection<cuerpoTecnico> cuerpoTecnicos) {
        this.cuerpoTecnicos = cuerpoTecnicos;
    }

    public Collection<Arbitro> getArbitros() {
        return this.arbitros;
    }

    public void setArbitros(Collection<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }

    public Collection<Club> getClubes() {
        return this.clubes;
    }

    public void setClubes(Collection<Club> clubes) {
        this.clubes = clubes;
    }

    public Collection<Socia> getSocias() {
        return this.socias;
    }

    public void setSocias(Collection<Socia> socias) {
        this.socias = socias;
    }

    public Collection<Localidad> getLocalidades() {
        return this.localidades;
    }

    public void setLocalidades(Collection<Localidad> localidades) {
        this.localidades = localidades;
    }

}
