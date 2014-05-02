package main;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.ConceptoEgreso;
import logicaNegocios.ConceptoIngreso;
import logicaNegocios.Egreso;
import logicaNegocios.IngresoOtro;

public class ControladoraContabilidad {

    private Collection<ConceptoDeportivo> conceptosDeportivo;
    private Collection<ConceptoIngreso> conceptosIngreso;
    private Collection<IngresoOtro> ingresosOtro;
    private Collection<Egreso> egresos;
    private Collection<ConceptoEgreso> conceptosEgreso;
    private EntityManager entityManager;

    public ControladoraContabilidad(EntityManager em) {
        this.entityManager = em;

        //CONSULTA PARA CARGAR TODAS LOS CONCEPTOS DEPORTIVOS DE LA BD
        Query traerConceptosDeportivos = em.createQuery("SELECT auxCP FROM ConceptoDeportivo auxCP");
        this.conceptosDeportivo = new TreeSet(traerConceptosDeportivos.getResultList());

        //CONSULTA PARA CARGAR TODAS LOS CONCEPTO INGRESOS DE LA BD
        Query traerConceptosIngreso = em.createQuery("SELECT auxCI FROM ConceptoIngreso auxCI");
        this.conceptosIngreso = new TreeSet(traerConceptosIngreso.getResultList());

        //CONSULTA PARA CARGAR TODAS LOS CONCEPTO EGRESOS DE LA BD
        Query traerConceptosEgreso = em.createQuery("SELECT auxCE FROM ConceptoEgreso auxCE");
        this.conceptosEgreso = new TreeSet(traerConceptosEgreso.getResultList());

    }

//---------------------------- GETERS Y SETERS ---------------------------------
    public Collection<ConceptoDeportivo> getConceptosDeportivo() {
        return this.conceptosDeportivo;
    }

    public void setConceptosDeportivo(Collection<ConceptoDeportivo> conceptosDeportivo) {
        this.conceptosDeportivo = conceptosDeportivo;
    }

    public Collection<ConceptoIngreso> getConceptosIngreso() {
        return this.conceptosIngreso;
    }

    public void setConceptosIngreso(Collection<ConceptoIngreso> conceptosIngreso) {
        this.conceptosIngreso = conceptosIngreso;
    }

    public Collection<IngresoOtro> getIngresosOtro() {
        return this.ingresosOtro;
    }

    public void setIngresosOtro(Collection<IngresoOtro> ingresosOtro) {
        this.ingresosOtro = ingresosOtro;
    }

    public Collection<Egreso> getEgresos() {
        return this.egresos;
    }

    public void setEgresos(Collection<Egreso> egresos) {
        this.egresos = egresos;
    }

    public Collection<ConceptoEgreso> getConceptosEgreso() {
        return this.conceptosEgreso;
    }

    public void setConceptosEgreso(Collection<ConceptoEgreso> conceptosEgreso) {
        this.conceptosEgreso = conceptosEgreso;
    }
//----------------------------- FIN GETERS Y SETERS ----------------------------

//------------------------------CONCEPTO DEPORTIVOS-----------------------------  
    public ConceptoDeportivo buscarConceptoDeportivoBD(Long id) {
        ConceptoDeportivo resultado;
        Query traerConceptoDeportivo = this.entityManager.createQuery("SELECT auxCD FROM ConceptoDeportivo auxCD WHERE auxCD.id = " + id);
        resultado = (ConceptoDeportivo) traerConceptoDeportivo.getResultList();
        return resultado;
    }

    public ConceptoDeportivo buscarConceptoDeportivo(Long id) {
        ConceptoDeportivo resultado = null;
        for (ConceptoDeportivo aux : conceptosDeportivo) {
            if (Objects.equals(aux.getIdConceptoDeportivo(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearConceptoDeportivo(double monto, String nombre, String detalle) {
        ConceptoDeportivo unConceptoDeportivo = new ConceptoDeportivo(entityManager, monto, nombre, detalle);
        this.conceptosDeportivo.add(unConceptoDeportivo);
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, Long id, double monto, String nombre, String detalle, boolean borradoLogico) {
        unConceptoDeportivo.setMonto(monto);
        unConceptoDeportivo.setNombre(nombre);
        unConceptoDeportivo.setDetalle(detalle);
        unConceptoDeportivo.setBorradoLogico(borradoLogico);
        unConceptoDeportivo.persistir(entityManager);
    }

    public void eliminarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        unConceptoDeportivo.setBorradoLogico(true);
        unConceptoDeportivo.persistir(entityManager);
    }
//----------------------------- FIN CONCEPTODEPORTIVO --------------------------

//----------------------------- CONCEPTOINGRESO --------------------------------
    public ConceptoIngreso buscarConceptoIngresoBD(Long id) {
        ConceptoIngreso resultado;
        Query traerConceptoIngreso = this.entityManager.createQuery("SELECT auxCI FROM ConceptoIngreso auxCI WHERE auxCI.id = " + id);
        resultado = (ConceptoIngreso) traerConceptoIngreso.getResultList();
        return resultado;
    }

    public ConceptoIngreso buscarConceptoIngreso(Long id) {
        ConceptoIngreso resultado = null;
        for (ConceptoIngreso aux : conceptosIngreso) {
            if (Objects.equals(aux.getIdConceptoIngreso(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearConceptoIngreso(String nombre, String detalle) {
        ConceptoIngreso unConceptoIngreso = new ConceptoIngreso(entityManager, nombre, detalle);
        this.conceptosIngreso.add(unConceptoIngreso);
    }

    public void modificarConceptoIngreso(ConceptoIngreso unConceptoIngreso, String nombre, String detalle, boolean borradoLogico) {
        unConceptoIngreso.setNombre(nombre);
        unConceptoIngreso.setDetalle(detalle);
        unConceptoIngreso.setBorradoLogico(borradoLogico);
        unConceptoIngreso.persistir(entityManager);
    }

    public void eliminarConceptoIngreso(ConceptoIngreso unConceptoIngreso) {
        unConceptoIngreso.setBorradoLogico(true);
        unConceptoIngreso.persistir(entityManager);
    }
//----------------------------- FIN CONCEPTOINGRESO ----------------------------

//----------------------------- CONCEPTO EGRESO --------------------------------
    public ConceptoEgreso buscarConceptoEgresoBD(Long id) {
        ConceptoEgreso resultado;
        Query traerConceptoEgreso = this.entityManager.createQuery("SELECT auxCE FROM ConceptoEgreso auxCE WHERE auxCE.id = " + id);
        resultado = (ConceptoEgreso) traerConceptoEgreso.getResultList();
        return resultado;
    }

    public ConceptoEgreso buscarConceptoEgreso(Long id) {
        ConceptoEgreso resultado = null;
        for (ConceptoEgreso aux : conceptosEgreso) {
            if (Objects.equals(aux.getIdConceptoEgreso(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearConceptoEgreso(String nombre, String detalle) {
        ConceptoEgreso unConceptoEgreso = new ConceptoEgreso(entityManager, nombre, detalle);
        this.conceptosEgreso.add(unConceptoEgreso);
    }

    public void modificarConceptoEgreso(ConceptoEgreso unConceptoEgreso, String nombre, String detalle, boolean borradoLogico) {
        unConceptoEgreso.setNombre(nombre);
        unConceptoEgreso.setDetalle(detalle);
        unConceptoEgreso.setBorradoLogico(borradoLogico);
        unConceptoEgreso.persistir(entityManager);
    }

    public void eliminarConceptoEgreso(ConceptoEgreso unConceptoEgreso) {
        unConceptoEgreso.setBorradoLogico(true);
        unConceptoEgreso.persistir(entityManager);
    }
//----------------------------- FIN CONCEPTO EGRESO ----------------------------

//----------------------------- INGRESOSOTRO -----------------------------------
    public IngresoOtro buscarIngresosOtroBD(Long id) {
        IngresoOtro resultado;
        Query traerIngresoOtro = this.entityManager.createQuery("SELECT auxIO FROM IngresoOtro auxIO WHERE auxIO.id = " + id);
        resultado = (IngresoOtro) traerIngresoOtro.getResultList();
        return resultado;
    }

    public IngresoOtro buscarIngresosOtro(Long id) {
        IngresoOtro resultado = null;
        for (IngresoOtro aux : ingresosOtro) {
            if (Objects.equals(aux.getIdIngresoOtro(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearIngresoOtro(Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle) {
        IngresoOtro unIngresoOtro = new IngresoOtro(entityManager,fecha, unConceptoIngreso, monto, detalle);
        this.ingresosOtro.add(unIngresoOtro);           
    }

    public void modificarIngresoOtro(IngresoOtro unIngresoOtro, Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle, boolean borradoLogico) {
        unIngresoOtro.setFecha(fecha);
        unIngresoOtro.setMonto(monto);
        unIngresoOtro.setUnConceptoIngreso(unConceptoIngreso);
        unIngresoOtro.setDetalle(detalle);
        unIngresoOtro.setBorradoLogico(borradoLogico);
       
        unIngresoOtro.persistir(entityManager);
    }

    public void eliminarIngresoOtro(IngresoOtro unIngresoOtro) {
        unIngresoOtro.setBorradoLogico(true);
        unIngresoOtro.persistir(entityManager);        
    }
//----------------------------- FIN INGRESOSOTRO -------------------------------

//----------------------------- EGRESOS ----------------------------------------
    public Egreso buscarEgresoBD(Long id) {
        Egreso resultado;
        Query traerEgreso = this.entityManager.createQuery("SELECT auxE FROM Egreso auxE WHERE auxE.id = " + id);
        resultado = (Egreso) traerEgreso.getResultList();
        return resultado;
    }

    public Egreso buscarEgreso(Long id) {
        Egreso resultado = null;
        for (Egreso aux : egresos) {
            if (Objects.equals(aux.getIdEgreso(), id)) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public void crearEgreso(Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion) {
        Egreso unEgreso = new Egreso(entityManager,fecha, monto, unConceptoEgreso, observacion);
        this.egresos.add(unEgreso);            
    }

    public void modificarEgreso(Egreso unEgreso, Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion, boolean borradoLogico) {
        unEgreso.setFecha(fecha);
        unEgreso.setMonto(monto);
        unEgreso.setUnConceptoEgreso(unConceptoEgreso);
        unEgreso.setObservacion(observacion);
        unEgreso.setBorradoLogico(borradoLogico);
        
        unEgreso.persistir(entityManager);
        
    }

    public void eliminarEgreso(Egreso unEgreso) {
       unEgreso.setBorradoLogico(true);
       unEgreso.persistir(entityManager);
    }
//----------------------------- FIN EGRESOS ------------------------------------
}
