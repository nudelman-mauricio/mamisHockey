package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Persona implements Serializable, Comparable {

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaIngreso;

    @Basic
    private String domicilio;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaNacimiento;

    @Column(length = 100)
    @Basic
    private String apellido;

    @Basic
    private String telFijo;

    @OneToMany(targetEntity = SancionTribunal.class)
    private Collection<SancionTribunal> sancionesTribunal;

    @Column(length = 100)
    @Basic
    private String nombre;

    @Id
    private Long dni;

    @Column(length = 100)
    @Basic
    private String email;

    @Basic
    private String telCelular;

    @Basic
    private boolean borradoLogico;

    @OneToOne(optional = false, targetEntity = Localidad.class)
    private Localidad unaLocalidad;

    public Persona() {

    }

    public Persona(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.unaLocalidad = unaLocalidad;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.borradoLogico = false;
    }

//------------------------------ GETERS Y SETERS -------------------------------
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDomicilio() {
        return this.domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelFijo() {
        return this.telFijo;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public Collection<SancionTribunal> getSancionesTribunal() {
        return this.sancionesTribunal;
    }

    public void setSancionesTribunal(Collection<SancionTribunal> sancionesTribunal) {
        this.sancionesTribunal = sancionesTribunal;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDni() {
        return this.dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelCelular() {
        return this.telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public Localidad getUnaLocalidad() {
        return this.unaLocalidad;
    }

    public void setUnaLocalidad(Localidad unaLocalidad) {
        this.unaLocalidad = unaLocalidad;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Persona) {
            Persona persona = (Persona) aux;
            if (this.dni > persona.dni) {
                retorno = 1;
            }
        }
        return retorno;
    }
}
