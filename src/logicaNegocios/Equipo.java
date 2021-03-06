package logicaNegocios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

@Entity
public class Equipo implements Serializable, Comparable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEquipo;

    @OneToOne(targetEntity = Socia.class)
    private Socia unaDelegadaSuplente;

    @OneToMany(targetEntity = PlanillaPago.class)
    private Collection<PlanillaPago> planillasPagos;

    @OneToMany(targetEntity = Deuda.class)
    private Collection<Deuda> deudas;

    @OneToOne(targetEntity = Socia.class)
    private Socia unaCapitanaSuplente;

    @OneToMany(targetEntity = Indumentaria.class)
    private Collection<Indumentaria> indumentarias;

    @OneToOne(targetEntity = Socia.class)
    private Socia unaDelegada;

    @Basic
    private String nombre;

    @OneToOne(targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unAyudanteCampo;

    @OneToOne(targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unPreparadorFisico;

    @OneToOne(targetEntity = Socia.class)
    private Socia unaCapitana;

    @OneToMany(targetEntity = SancionTribunal.class)
    private Collection<SancionTribunal> sancionesTribunal;

    @OneToMany(targetEntity = Socia.class)
    private Collection<Socia> plantel;

    @Basic
    private boolean borradoLogico;

    @OneToOne(targetEntity = PersonaAuxiliar.class)
    private PersonaAuxiliar unDT;
    // </editor-fold>

    public Equipo() {

    }

    public Equipo(EntityManager entityManager, String nombre, PersonaAuxiliar unDT, Socia unaCapitana, Socia unaCapitanaSup, Socia unaDelegada, Socia unaDelegadaSup, PersonaAuxiliar unPF, PersonaAuxiliar unAC) {
        this.nombre = nombre;
        this.unDT = unDT;
        this.unaCapitana = unaCapitana;
        this.unaCapitanaSuplente = unaCapitanaSup;
        this.unaDelegada = unaDelegada;
        this.unaDelegadaSuplente = unaDelegadaSup;
        this.unPreparadorFisico = unPF;
        this.unAyudanteCampo = unAC;
        this.borradoLogico = false;
        this.persistir(entityManager);
    }

    // <editor-fold defaultstate="collapsed" desc="Geters y Seters">
    public Long getIdEquipo() {
        return this.idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Socia getUnaDelegadaSuplente() {
        return this.unaDelegadaSuplente;
    }

    public void setUnaDelegadaSuplente(Socia unaDelegadaSuplente) {
        this.unaDelegadaSuplente = unaDelegadaSuplente;
    }

    public Collection<PlanillaPago> getPlanillasPagos() {
        return this.planillasPagos;
    }

    public void setPlanillasPagos(Collection<PlanillaPago> planillasPagos) {
        this.planillasPagos = planillasPagos;
    }

    public Collection<Deuda> getDeudas() {
        return this.deudas;
    }

    public void setDeudas(Collection<Deuda> deudas) {
        this.deudas = deudas;
    }

    public Socia getUnaCapitanaSuplente() {
        return this.unaCapitanaSuplente;
    }

    public void setUnaCapitanaSuplente(Socia unaCapitanaSuplente) {
        this.unaCapitanaSuplente = unaCapitanaSuplente;
    }

    public Collection<Indumentaria> getIndumentarias() {
        return this.indumentarias;
    }

    public void setIndumentarias(Collection<Indumentaria> indumentarias) {
        this.indumentarias = indumentarias;
    }

    public Socia getUnaDelegada() {
        return this.unaDelegada;
    }

    public void setUnaDelegada(Socia unaDelegada) {
        this.unaDelegada = unaDelegada;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PersonaAuxiliar getUnAyudanteCampo() {
        return this.unAyudanteCampo;
    }

    public void setUnAyudanteCampo(PersonaAuxiliar unAyudanteCampo) {
        this.unAyudanteCampo = unAyudanteCampo;
    }

    public PersonaAuxiliar getUnPreparadorFisico() {
        return this.unPreparadorFisico;
    }

    public void setUnPreparadorFisico(PersonaAuxiliar unPreparadorFisico) {
        this.unPreparadorFisico = unPreparadorFisico;
    }

    public Socia getUnaCapitana() {
        return this.unaCapitana;
    }

    public void setUnaCapitana(Socia unaCapitana) {
        this.unaCapitana = unaCapitana;
    }

    public Collection<SancionTribunal> getSancionesTribunal() {
        return this.sancionesTribunal;
    }

    public void setSancionesTribunal(Collection<SancionTribunal> sancionesTribunal) {
        this.sancionesTribunal = sancionesTribunal;
    }

    public Collection<Socia> getPlantel() {
        return this.plantel;
    }

    public void setPlantel(Collection<Socia> plantel) {
        this.plantel = plantel;
    }

    public boolean isBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public PersonaAuxiliar getUnDT() {
        return this.unDT;
    }

    public void setUnDT(PersonaAuxiliar unDT) {
        this.unDT = unDT;
    }
    // </editor-fold>

    @Override
    public int compareTo(Object aux) {
        int retorno = -1;
        if (aux instanceof Equipo) {
            Equipo equipo = (Equipo) aux;
            if (this.idEquipo > equipo.idEquipo) {
                retorno = 1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return nombre;
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

    // <editor-fold defaultstate="collapsed" desc="Deudas">
    public void agregarDeuda(EntityManager entityManager, Deuda unaDeuda) {
        this.deudas.add(unaDeuda);
        this.persistir(entityManager);
    }

    /**
     * Devuelve True solo si hasta el dia de la fecha pasada por parametro no
     * hay ninguna cuota vencida no pagada
     *
     * @param unaFecha
     * @return
     */
    public boolean isAlDia(Date unaFecha) {
        boolean resultado = true;
        for (Deuda unaDeuda : this.getDeudas()) {
            if ((!unaDeuda.isBorradoLogico()) && (unaDeuda.isVencido(unaFecha))) {
                resultado = false;
            }
        }
        return resultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PlanillasPagos">
    public void agregarPlanillaPago(EntityManager entityManager, PlanillaPago unaPlanillaPago) {
        this.planillasPagos.add(unaPlanillaPago);
        this.persistir(entityManager);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Indumentaria">
    public void agregarIndumentaria(EntityManager entityManager, Indumentaria unaIndumentaria) {
        this.indumentarias.add(unaIndumentaria);
        this.persistir(entityManager);
    }

    public void quitarIndumentaria(EntityManager entityManager, Indumentaria unaIndumentaria) {
        this.indumentarias.remove(unaIndumentaria);
        this.persistir(entityManager);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Sanciones Tribunal">
    public void agregarSancionTribunal(EntityManager entityManager, SancionTribunal unaSancionTribunal) {
        this.sancionesTribunal.add(unaSancionTribunal);
        this.persistir(entityManager);
    }

    public void quitarSancionTribunal(EntityManager entityManager, SancionTribunal unaSancionTribunal) {
        this.sancionesTribunal.remove(unaSancionTribunal);
        this.persistir(entityManager);
    }

    /**
     * Devuelve Lista de sanciones vigentes, es decir que no se borraron y que
     * no se terminaron de cumplir al dia de la fecha pasada por parametro
     *
     * @param unaFecha
     * @return
     */
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
     * Devuelve True si el equipo tiene al menos una SancionTribunal que no este
     * borrada y que no haya terminado hasta el dia de la fecha pasada por
     * parametro
     *
     * @param unaFecha
     * @return
     */
    public boolean isSancionado(Date unaFecha) {
        boolean resultado = true;
        if (this.getSancionesVigentes(unaFecha).isEmpty()) {
            resultado = false;
        }
        return resultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Plantel">
    public void agregarPlantel(EntityManager entityManager, Socia unaSocia) {
        this.plantel.add(unaSocia);
        this.persistir(entityManager);
    }

    public void quitarPlantel(EntityManager entityManager, Socia unaSocia) {
        if (unaSocia == this.unaCapitana) {
            this.setUnaCapitana(null); //NO SE SI ESTO VA A GENERAR ERROR POR NO SER CAMPO OPCIONAL EN LA BD
            this.cartelAdvertencia("Capitana");
        }
        if (unaSocia == this.unaCapitanaSuplente) {
            this.setUnaCapitanaSuplente(null);
            this.cartelAdvertencia("Capitana Suplente");
        }
        if (unaSocia == this.unaDelegada) {
            this.setUnaDelegada(null);
            this.cartelAdvertencia("Delegada");
        }
        if (unaSocia == this.unaDelegadaSuplente) {
            this.setUnaDelegadaSuplente(null);
            this.cartelAdvertencia("Delegada Suplente");
        }
        this.plantel.remove(unaSocia);
        this.persistir(entityManager);
    }

    private void cartelAdvertencia(String auxiliar) {
        //CARTEL ADVERTENCIA DE ELIMINAR A UNA SOCIA QUE ES CAPITANA O DELEGADA
        JOptionPane.showMessageDialog(null, "La operaciÃ³n que realizÃ³ dejÃ³ sin " + auxiliar + " al Equipo " + this.getNombre(), "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Categoria">
    public boolean isCategoria(Categoria unaCategoria) {
        boolean resultado = false;
        int cantidadMenores = contarMenores(unaCategoria.getEdadParametro());
        if ((unaCategoria.getCantidadMinima() <= cantidadMenores) && (cantidadMenores <= unaCategoria.getCantidadMaxima())) {
            resultado = true;
        }
        return resultado;
    }

    private int contarMenores(int edadParametro) {
        int cantidadMenores = 0;
        for (Socia aux : this.plantel) {
            if (aux.getEdadCalendario() < edadParametro) {
                cantidadMenores++;
            }
        }
        return cantidadMenores;
    }
    // </editor-fold>
}
