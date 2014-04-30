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
    //------------------------------CONCEPTO DEPORTIVOS----------------------------------   
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
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            ConceptoDeportivo unConceptoDeportivo = new ConceptoDeportivo(monto, nombre, detalle);
            entityManager.persist(unConceptoDeportivo);
            this.conceptosDeportivo.add(unConceptoDeportivo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear ConceptoDeportivo" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, Long id, double monto, String nombre, String detalle, boolean borradoLogico) {

        unConceptoDeportivo.setMonto(monto);
        unConceptoDeportivo.setNombre(nombre);
        unConceptoDeportivo.setDetalle(detalle);
        unConceptoDeportivo.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unConceptoDeportivo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar ConceptoDeportivo" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unConceptoDeportivo.setBorradoLogico(false);
            entityManager.persist(unConceptoDeportivo);
            conceptosDeportivo.remove(unConceptoDeportivo);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error al Eliminar ConceptoDeportivo" + e.getMessage());
            tx.rollback();
        }
    }

    //----------------------------- FIN CONCEPTODEPORTIVO ----------------------------
    
    //----------------------------- CONCEPTOINGRESO ----------------------------------
    
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
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            ConceptoIngreso unConceptoIngreso = new ConceptoIngreso(nombre, detalle);
            entityManager.persist(unConceptoIngreso);
            this.conceptosIngreso.add(unConceptoIngreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear ConceptoIngreso" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarConceptoIngreso(ConceptoIngreso unConceptoIngreso, String nombre, String detalle, boolean borradoLogico) {

        unConceptoIngreso.setNombre(nombre);        
        unConceptoIngreso.setDetalle(detalle);
        unConceptoIngreso.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unConceptoIngreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar ConceptoIngreso" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarConceptoIngreso (ConceptoIngreso unConceptoIngreso) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unConceptoIngreso.setBorradoLogico(false);
            entityManager.persist(unConceptoIngreso);
            conceptosIngreso.remove(unConceptoIngreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error al Eliminar ConceptoIngreso" + e.getMessage());
            tx.rollback();
        }
    }
    
    //----------------------------- FIN CONCEPTOINGRESO ----------------------------
    
    //----------------------------- CONCEPTO EGRESO ----------------------------
         
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
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            ConceptoEgreso unConceptoEgreso = new ConceptoEgreso(nombre, detalle);
            entityManager.persist(unConceptoEgreso);
            this.conceptosEgreso.add(unConceptoEgreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear ConceptoEgreso" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarConceptoEgreso(ConceptoEgreso unConceptoEgreso, String nombre, String detalle, boolean borradoLogico) {

        unConceptoEgreso.setNombre(nombre);        
        unConceptoEgreso.setDetalle(detalle);
        unConceptoEgreso.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unConceptoEgreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar ConceptoEgreso" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarConceptoEgreso (ConceptoEgreso unConceptoEgreso) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unConceptoEgreso.setBorradoLogico(false);
            entityManager.persist(unConceptoEgreso);
            conceptosEgreso.remove(unConceptoEgreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error al Eliminar ConceptoEgreso" + e.getMessage());
            tx.rollback();
        }
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
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            IngresoOtro unIngresoOtro = new IngresoOtro(fecha,monto,unConceptoIngreso,detalle);
            entityManager.persist(unIngresoOtro);
            this.ingresosOtro.add(unIngresoOtro);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception Crear IngresoOtro" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarIngresoOtro(IngresoOtro unIngresoOtro,Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle, boolean borradoLogico) {

        unIngresoOtro.setFecha(fecha);
        unIngresoOtro.setMonto(monto);
        unIngresoOtro.setUnConceptoIngreso(unConceptoIngreso);
        unIngresoOtro.setDetalle(detalle);
        unIngresoOtro.setBorradoLogico(borradoLogico);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unIngresoOtro);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar IngresoOtro" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarIngresoOtro (IngresoOtro unIngresoOtro) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unIngresoOtro.setBorradoLogico(false);
            entityManager.persist(unIngresoOtro);
            ingresosOtro.remove(unIngresoOtro);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error al Eliminar IngresoOtro" + e.getMessage());
            tx.rollback();
        }
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
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Egreso unEgreso = new Egreso(fecha,monto,unConceptoEgreso,observacion);
            entityManager.persist(unEgreso);
            this.egresos.add(unEgreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Exception al Crear Egreso" + e.getMessage());
            tx.rollback();
        }
    }

    public void modificarEgreso(Egreso unEgreso, Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion, boolean borradoLogico) {

        unEgreso.setFecha(fecha);
        unEgreso.setMonto(monto);
        unEgreso.setUnConceptoEgreso(unConceptoEgreso);
        unEgreso.setObservacion(observacion);
        unEgreso.setBorradoLogico(borradoLogico);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(unEgreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error de Modificar Egreso" + e.getMessage());
            tx.rollback();
        }
    }

    public void eliminarEgreso (Egreso unEgreso) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            unEgreso.setBorradoLogico(false);
            entityManager.persist(unEgreso);
            egresos.remove(unEgreso);
            tx.commit();
        } catch (Exception e) {
            //-------------------------- TEMPORAL BORRAR VERSIONA FINAL -----------------------------------
            System.out.println("Error al Eliminar Egreso" + e.getMessage());
            tx.rollback();
        }
    }
    //----------------------------- FIN EGRESOS ------------------------------------
}
