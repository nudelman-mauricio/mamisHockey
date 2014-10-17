package logicaNegocios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

@MappedSuperclass
public abstract class Persona implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
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
    // </editor-fold>

    public Persona() {

    }

    public Persona(Long dni, String apellido, String nombre, Localidad unaLocalidad, String domicilio, Date fechaNacimiento, Date fechaIngreso, String email, String telFijo, String telCelular) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.unaLocalidad = unaLocalidad;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.borradoLogico = false;
        this.email = email;
        this.telFijo = telFijo;
        this.telCelular = telCelular;
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
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
    // </editor-fold>

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

    @Override
    public String toString() {
        String nombreM = nombre.toLowerCase();
        char[] caracteres = nombreM.toCharArray();
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < nombreM.length() - 2; i++) {
            if (caracteres[i] == ' ') {
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            }
        }

        return apellido.toUpperCase() + ", " + new String(caracteres);
    }

    // <editor-fold defaultstate="collapsed" desc="Persistencia">
    public void persistir(EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(this);
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos. Avisar al Servicio Tecnico." + System.getProperty("line.separator") + "LMLSOLUCIONESINFORMATICAS@GMAIL.COM");
            tx.rollback();
        }
    }
    // </editor-fold>
            
    // <editor-fold defaultstate="collapsed" desc="Sancion Tribunal">
    public void agregarSancionTribunal(EntityManager entityManager, SancionTribunal unaSancionTribunal) {
        this.sancionesTribunal.add(unaSancionTribunal);
        this.persistir(entityManager);
    }

    public void quitarSancionTribunal(EntityManager entityManager, SancionTribunal unaSancionTribunal) {
        this.sancionesTribunal.remove(unaSancionTribunal);
        this.persistir(entityManager);
    }

    public ArrayList<SancionTribunal> getSancionesVigentes(Date unaFecha) {
        ArrayList<SancionTribunal> resultado = new ArrayList();
        for (SancionTribunal unaSancionTribunal : this.getSancionesTribunal()) {
            if (unaSancionTribunal.isVigente(unaFecha)) {
                resultado.add(unaSancionTribunal);
            }
        }
        return resultado;
    }

    /**
     * Devuelve True si la persona tiene al menos una SancionTribunal que no
     * este borrada y que no haya terminado hasta el dia de la fecha pasada por
     * parametro
     *
     * @param unaFecha
     * @return
     */
    public boolean isSancionada(Date unaFecha) {
        boolean resultado = true;
        if (this.getSancionesVigentes(unaFecha).isEmpty()) {
            resultado = false;
        }
        return resultado;
    }
    // </editor-fold>

    public int getEdadCalendario() {
        Calendar fechaSO = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        int anioNacimiento = Integer.parseInt(dateFormat.format(this.fechaNacimiento));
        int anoActual = fechaSO.get(fechaSO.YEAR);
        return (anoActual - anioNacimiento);
    }
}
