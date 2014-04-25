package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Club implements Serializable {

    @Basic
    private String nombrePresidente;

    @Basic
    private String logo;

    @OneToMany(targetEntity = Cancha.class)
    private Collection<Cancha> canchas;

    @Basic
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClub;

    @Basic
    private boolean borradoLogico;

    @OneToMany(targetEntity = Equipo.class)
    private Collection<Equipo> equipos;

    @OneToOne(optional = false, targetEntity = Localidad.class)
    private Localidad unaLocalidad;

    public Club() {

    }

    public Club(Long idClub, String nombre, String logo, String nombrePresidente, Localidad unaLocalidad, boolean borradoLogico) {
        this.idClub = idClub;
        this.nombre = nombre;
        this.logo = logo;
        this.nombrePresidente = nombrePresidente;
        this.unaLocalidad = unaLocalidad;
        this.borradoLogico = borradoLogico;
    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public String getNombrePresidente() {
        return this.nombrePresidente;
    }

    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Collection<Cancha> getCanchas() {
        return this.canchas;
    }

    public void setCanchas(Collection<Cancha> canchas) {
        this.canchas = canchas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdClub() {
        return this.idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public Collection<Equipo> getEquipos() {
        return this.equipos;
    }

    public void setEquipos(Collection<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Localidad getUnaLocalidad() {
        return this.unaLocalidad;
    }

    public void setUnaLocalidad(Localidad unaLocalidad) {
        this.unaLocalidad = unaLocalidad;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------
}
