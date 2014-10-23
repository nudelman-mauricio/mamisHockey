package logicaNegocios;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

@Entity
public class Club implements Serializable, Comparable {
    
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @OneToOne(targetEntity = Socia.class)
    private Socia unaResponsableSede;

    @Basic
    private String nombrePresidente;

    @Lob
    @Basic
    private byte[] logo;

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

    @OneToOne(targetEntity = Localidad.class)
    private Localidad unaLocalidad;
    // </editor-fold>

    public Club() {

    }

    public Club(EntityManager entityManager, String nombre, String nombrePresidente, Localidad unaLocalidad) {
        this.nombre = nombre;
        this.nombrePresidente = nombrePresidente;
        this.unaLocalidad = unaLocalidad;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Socia getUnaResponsableSede() {
        return this.unaResponsableSede;
    }

    public void setUnaResponsableSede(Socia unaResponsableSede) {
        this.unaResponsableSede = unaResponsableSede;
    }

    public String getNombrePresidente() {
        return this.nombrePresidente;
    }

    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }

    public byte[] getLogo() {
        return this.logo;
    }

    public void setLogo(byte[] logo) {
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
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Club) {
            Club club = (Club) aux;
            if (this.idClub > club.idClub) {
                retorno = 1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return nombre + ", de " + unaLocalidad;
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

    // <editor-fold defaultstate="collapsed" desc="Equipos">
    public void agregarEquipo(EntityManager entityManager, Equipo unEquipo) {
        this.equipos.add(unEquipo);
        this.persistir(entityManager);
    }

    public void quitarEquipo(EntityManager entityManager, Equipo unEquipo) {
        this.equipos.remove(unEquipo);
        this.persistir(entityManager);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Canchas">
    public void agregarCancha(EntityManager entityManager, Cancha unaCancha) {
        this.canchas.add(unaCancha);
        this.persistir(entityManager);
    }
    // </editor-fold>
}
