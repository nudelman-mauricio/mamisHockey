package main;

import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.ConceptoEgreso;
import logicaNegocios.ConceptoIngreso;
import logicaNegocios.Deuda;
import logicaNegocios.Egreso;
import logicaNegocios.Equipo;
import logicaNegocios.Frecuencia;
import logicaNegocios.IngresoOtro;
import logicaNegocios.Mes;
import logicaNegocios.Socia;
import logicaNegocios.TipoCancha;
import logicaNegocios.TipoEstado;

public class ControladoraContabilidad {

    private final EntityManager entityManager;

    public ControladoraContabilidad(EntityManager em) {
        this.entityManager = em;
    }

//------------------------------CONCEPTO DEPORTIVOS-----------------------------  
    public ConceptoDeportivo buscarConceptoDeportivoBD(Long id) {
        ConceptoDeportivo resultado;
        Query traerConceptoDeportivo = this.entityManager.createQuery("SELECT auxCD FROM ConceptoDeportivo auxCD WHERE auxCD.id = " + id);
        resultado = (ConceptoDeportivo) traerConceptoDeportivo.getResultList();
        return resultado;
    }

    public void crearConceptoDeportivo(double monto, String concepto) {
        ConceptoDeportivo unConceptoDeportivo = new ConceptoDeportivo(this.entityManager, monto, concepto);
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, Long id, double monto, String concepto, boolean borradoLogico) {
        unConceptoDeportivo.setMonto(monto);
        unConceptoDeportivo.setConcepto(concepto);
        unConceptoDeportivo.setBorradoLogico(borradoLogico);
        unConceptoDeportivo.persistir(this.entityManager);
    }

    public void eliminarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo) {
        unConceptoDeportivo.setBorradoLogico(true);
        unConceptoDeportivo.persistir(this.entityManager);
    }
//----------------------------- FIN CONCEPTODEPORTIVO --------------------------

//-----------------------------------DEUDAS-------------------------------------
    public void crearDeudaEquipo(Equipo unEquipo, Date fechaGeneracion, Date fechaVencimiento, double monto, String observacion) {
        Deuda unaDeuda = new Deuda(this.entityManager, fechaGeneracion, fechaVencimiento, monto, observacion);
        unEquipo.agregarDeuda(this.entityManager, unaDeuda);
    }

    public void crearDeudaSocia(Socia unaSocia, Date fechaGeneracion, Date fechaVencimiento, double monto, String observacion) {
        Deuda unaDeuda = new Deuda(this.entityManager, fechaGeneracion, fechaVencimiento, monto, observacion);
        unaSocia.agregarDeuda(this.entityManager, unaDeuda);
    }

    public void modificarDeuda(Deuda unaDeuda, Date fechaGeneracion, Date fechaVencimiento, double monto, boolean saldado, String observacion, boolean borradoLogico) {
        unaDeuda.setFechaGeneracion(fechaGeneracion);
        unaDeuda.setFechaVencimiento(fechaVencimiento);
        unaDeuda.setMonto(monto);
        unaDeuda.setSaldado(saldado);
        unaDeuda.setObservacion(observacion);
        unaDeuda.setBorradoLogico(borradoLogico);
        unaDeuda.persistir(this.entityManager);
    }

    public void cambiarDeudaDeEquipo(Deuda unaDeuda, Equipo unEquipoActual, Equipo unEquipoNuevo) {
        unEquipoActual.quitarDeuda(this.entityManager, unaDeuda);
        unEquipoNuevo.agregarDeuda(this.entityManager, unaDeuda);
    }

    public void cambiarDeudaDeSocia(Deuda unaDeuda, Socia unaSociaActual, Socia unaSociaNueva) {
        unaSociaActual.quitarDeuda(this.entityManager, unaDeuda);
        unaSociaNueva.agregarDeuda(this.entityManager, unaDeuda);
    }

    public void eliminarDeuda(Deuda unaDeuda) {
        unaDeuda.setBorradoLogico(true);
        unaDeuda.persistir(this.entityManager);
    }
//---------------------------------FIN DEUDAS-----------------------------------

//--------------------------------PAGO DEUDA------------------------------------
    public void crearPagoDeuda(Deuda unaDeuda, Date fecha, double monto, String observacion) {
        PagoDeuda unPagoDeuda = new PagoDeuda(this.entityManager, fecha, monto, observacion);
        unaDeuda.agregarPagoDeuda(this.entityManager, unPagoDeuda);
    }

    public void modificarPagoDeuda(PagoDeuda unPagoDeuda, Date fecha, double monto, String observacion, boolean borradoLogico) {
        unPagoDeuda.setFecha(fecha);
        unPagoDeuda.setMonto(monto);
        unPagoDeuda.setObservacion(observacion);
        unPagoDeuda.setBorradoLogico(borradoLogico);
        unPagoDeuda.persistir(this.entityManager);
    }

    public void cambiarPagoDeudaDeDeuda(PagoDeuda unPagoDeuda, Deuda unaDeudaActual, Deuda unaDeudaNueva) {
        unaDeudaActual.quitarPagoDeuda(this.entityManager, unPagoDeuda);
        unaDeudaNueva.agregarPagoDeuda(this.entityManager, unPagoDeuda);
    }

    public void eliminarPagoDeuda(PagoDeuda unPagoDeuda) {
        unPagoDeuda.setBorradoLogico(true);
        unPagoDeuda.persistir(this.entityManager);
    }
//------------------------------FIN PAGO DEUDA----------------------------------

//----------------------------- CONCEPTOINGRESO --------------------------------
    public ConceptoIngreso buscarConceptoIngresoBD(Long id) {
        ConceptoIngreso resultado;
        Query traerConceptoIngreso = this.entityManager.createQuery("SELECT auxCI FROM ConceptoIngreso auxCI WHERE auxCI.id = " + id);
        resultado = (ConceptoIngreso) traerConceptoIngreso.getResultList();
        return resultado;
    }

    public void crearConceptoIngreso(String nombre, String detalle) {
        ConceptoIngreso unConceptoIngreso = new ConceptoIngreso(this.entityManager, nombre, detalle);
    }

    public void modificarConceptoIngreso(ConceptoIngreso unConceptoIngreso, String nombre, String detalle, boolean borradoLogico) {
        unConceptoIngreso.setNombre(nombre);
        unConceptoIngreso.setDetalle(detalle);
        unConceptoIngreso.setBorradoLogico(borradoLogico);
        unConceptoIngreso.persistir(this.entityManager);
    }

    public void eliminarConceptoIngreso(ConceptoIngreso unConceptoIngreso) {
        unConceptoIngreso.setBorradoLogico(true);
        unConceptoIngreso.persistir(this.entityManager);
    }
//----------------------------- FIN CONCEPTOINGRESO ----------------------------

//----------------------------- CONCEPTO EGRESO --------------------------------
    public ConceptoEgreso buscarConceptoEgresoBD(Long id) {
        ConceptoEgreso resultado;
        Query traerConceptoEgreso = this.entityManager.createQuery("SELECT auxCE FROM ConceptoEgreso auxCE WHERE auxCE.id = " + id);
        resultado = (ConceptoEgreso) traerConceptoEgreso.getResultList();
        return resultado;
    }

    public void crearConceptoEgreso(String nombre, String detalle) {
        ConceptoEgreso unConceptoEgreso = new ConceptoEgreso(this.entityManager, nombre, detalle);
    }

    public void modificarConceptoEgreso(ConceptoEgreso unConceptoEgreso, String nombre, String detalle, boolean borradoLogico) {
        unConceptoEgreso.setNombre(nombre);
        unConceptoEgreso.setDetalle(detalle);
        unConceptoEgreso.setBorradoLogico(borradoLogico);
        unConceptoEgreso.persistir(this.entityManager);
    }

    public void eliminarConceptoEgreso(ConceptoEgreso unConceptoEgreso) {
        unConceptoEgreso.setBorradoLogico(true);
        unConceptoEgreso.persistir(this.entityManager);
    }
//----------------------------- FIN CONCEPTO EGRESO ----------------------------

//----------------------------- INGRESOSOTRO -----------------------------------
    public IngresoOtro buscarIngresosOtroBD(Long id) {
        IngresoOtro resultado;
        Query traerIngresoOtro = this.entityManager.createQuery("SELECT auxIO FROM IngresoOtro auxIO WHERE auxIO.id = " + id);
        resultado = (IngresoOtro) traerIngresoOtro.getResultList();
        return resultado;
    }

    public void crearIngresoOtro(Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle) {
        IngresoOtro unIngresoOtro = new IngresoOtro(this.entityManager, fecha, unConceptoIngreso, monto, detalle);
    }

    public void modificarIngresoOtro(IngresoOtro unIngresoOtro, Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle, boolean borradoLogico) {
        unIngresoOtro.setFecha(fecha);
        unIngresoOtro.setMonto(monto);
        unIngresoOtro.setUnConceptoIngreso(unConceptoIngreso);
        unIngresoOtro.setDetalle(detalle);
        unIngresoOtro.setBorradoLogico(borradoLogico);
        unIngresoOtro.persistir(this.entityManager);
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

    public void crearEgreso(Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion) {
        Egreso unEgreso = new Egreso(this.entityManager, fecha, monto, unConceptoEgreso, observacion);
    }

    public void modificarEgreso(Egreso unEgreso, Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion, boolean borradoLogico) {
        unEgreso.setFecha(fecha);
        unEgreso.setMonto(monto);
        unEgreso.setUnConceptoEgreso(unConceptoEgreso);
        unEgreso.setObservacion(observacion);
        unEgreso.setBorradoLogico(borradoLogico);
        unEgreso.persistir(this.entityManager);

    }

    public void eliminarEgreso(Egreso unEgreso) {
        unEgreso.setBorradoLogico(true);
        unEgreso.persistir(this.entityManager);
    }
//----------------------------- FIN EGRESOS ------------------------------------

//------------------------------FRECUENCIA--------------------------------------
    public void crearFrecuencia(String diaGeneracion, String diaVencimiento, Collection<Mes> meses) {
        Frecuencia unaFrecuencia = new Frecuencia(this.entityManager, diaGeneracion, diaVencimiento, meses);
    }

    public void modificarFrecuencia(Frecuencia unaFrecuencia, String diaGeneracion, String diaVencimiento, boolean borradoLogico) {
        unaFrecuencia.setDiaGeneracion(diaGeneracion);
        unaFrecuencia.setDiaVencimiento(diaVencimiento);
        unaFrecuencia.setBorradoLogico(borradoLogico);
        unaFrecuencia.persistir(this.entityManager);
    }

    public void eliminarFrecuencia(Frecuencia unaFrecuencia) {
        unaFrecuencia.setBorradoLogico(true);
        unaFrecuencia.persistir(this.entityManager);
    }
//----------------------------FIN FRECUENCIA------------------------------------
}
