/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Leanwit
 */
@Entity
@Table(name = "localidad", catalog = "mamishockeydb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l"),
    @NamedQuery(name = "Localidad.findByIdlocalidad", query = "SELECT l FROM Localidad l WHERE l.idlocalidad = :idlocalidad"),
    @NamedQuery(name = "Localidad.findByBorradologico", query = "SELECT l FROM Localidad l WHERE l.borradologico = :borradologico"),
    @NamedQuery(name = "Localidad.findByCodpostal", query = "SELECT l FROM Localidad l WHERE l.codpostal = :codpostal"),
    @NamedQuery(name = "Localidad.findByNombre", query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre")})
public class Localidad implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDLOCALIDAD")
    private Long idlocalidad;
    @Column(name = "BORRADOLOGICO")
    private Boolean borradologico;
    @Column(name = "CODPOSTAL")
    private String codpostal;
    @Column(name = "NOMBRE")
    private String nombre;

    public Localidad() {
    }

    public Localidad(Long idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public Long getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(Long idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public Boolean getBorradologico() {
        return borradologico;
    }

    public void setBorradologico(Boolean borradologico) {
        this.borradologico = borradologico;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocalidad != null ? idlocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.idlocalidad == null && other.idlocalidad != null) || (this.idlocalidad != null && !this.idlocalidad.equals(other.idlocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Interfaces.Localidad[ idlocalidad=" + idlocalidad + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
