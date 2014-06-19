package main;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import logicaNegocios.ConceptoDeportivo;
import logicaNegocios.ConceptoEgreso;
import logicaNegocios.ConceptoIngreso;
import logicaNegocios.Cuota;
import logicaNegocios.Deuda;
import logicaNegocios.Egreso;
import logicaNegocios.Equipo;
import logicaNegocios.Frecuencia;
import logicaNegocios.IngresoOtro;
import logicaNegocios.Mes;
import logicaNegocios.PagoCuota;
import logicaNegocios.Socia;

public class ControladoraContabilidad {

    private final EntityManager entityManager;

    public ControladoraContabilidad(EntityManager em) {
        this.entityManager = em;
    }

//------------------------------CONCEPTO DEPORTIVOS-----------------------------
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

    /**
     * Devuelve unConceptoDeportivo por ID incluido los borrados
     */
    public ConceptoDeportivo getConceptoDeportivoBD(Long id) {
        ConceptoDeportivo resultado;
        Query traerConceptoDeportivo = this.entityManager.createQuery("SELECT auxCD FROM ConceptoDeportivo auxCD WHERE auxCD.idConceptoDeportivo = " + id);
        resultado = (ConceptoDeportivo) traerConceptoDeportivo.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los ConceptosDeportivos menos los borrados
     */
    public List<ConceptoDeportivo> getConceptosDeportivosBD() {
        String unaConsulta = "SELECT A FROM ConceptoDeportivo A WHERE A.borradoLogico = FALSE";
        List<ConceptoDeportivo> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//----------------------------- FIN CONCEPTODEPORTIVO --------------------------

//-----------------------------------DEUDAS-------------------------------------
    public void crearDeudaEquipo(Equipo unEquipo, Date fechaGeneracion, String concepto, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        Deuda unaDeuda = new Deuda(this.entityManager, fechaGeneracion, concepto, observacion, montoTotal, cantCuotas, primerVencimiento);
        unEquipo.agregarDeuda(this.entityManager, unaDeuda);
    }

    public Deuda crearDeudaSocia(Socia unaSocia, Date fechaGeneracion, String concepto, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        Deuda unaDeuda;
        if (montoTotal == 0) {
            unaDeuda = new Deuda(this.entityManager, fechaGeneracion, concepto, observacion, montoTotal, 1, fechaGeneracion);
            for (Cuota unCuota : unaDeuda.getCuotas()) {
                crearPagoCuota(unCuota, montoTotal, fechaGeneracion, "Pago deuda de monto 0");    
            }
            unaSocia.agregarDeuda(this.entityManager, unaDeuda);
        }else{
            unaDeuda = new Deuda(this.entityManager, fechaGeneracion, concepto, observacion, montoTotal, cantCuotas, primerVencimiento);
            unaSocia.agregarDeuda(this.entityManager, unaDeuda);
        }
        return unaDeuda;
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
        double montoNotaCredito = unaDeuda.eliminarTodasLasCuotas(entityManager);
        unaDeuda.persistir(this.entityManager);

        if (montoNotaCredito != 0) {//Cartel Informando del Monto de la Nota de Credito si Corresponde
            JOptionPane.showMessageDialog(null, "La operación que realizó eliminó una Deuda que contenía cuotas pagadas. El monto total de las Cuotas Pagadas es: $" + montoNotaCredito, "Realizar Nota de Crédito", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Devuelve unaDeuda por ID incluido los borrados
     */
    public Deuda getDeudaBD(Long id) {
        Deuda resultado;
        Query traerDeuda = this.entityManager.createQuery("SELECT auxCD FROM Deuda auxCD WHERE auxCD.idDeuda = " + id);
        resultado = (Deuda) traerDeuda.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos las Deudas menos las borradas
     */
    public List<Deuda> getDeudaBD() {
        String unaConsulta = "SELECT A FROM Deuda A WHERE A.borradoLogico = FALSE";
        List<Deuda> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//---------------------------------FIN DEUDAS-----------------------------------

//--------------------------------PAGO CUOTA------------------------------------
    public void crearPagoCuota(Cuota unaCuota, double monto, Date fechaPago, String observacion) {
        PagoCuota unPagoCuota = new PagoCuota(this.entityManager, monto, fechaPago, observacion);
        unaCuota.setUnPagoCuota(unPagoCuota);
        unaCuota.persistir(this.entityManager);
    }

    public void modificarPagoCuota(PagoCuota unPagoCuota, double monto, Date fechaPago, String observacion, boolean borradoLogico) {
        unPagoCuota.setMonto(monto);
        unPagoCuota.setFechaPago(fechaPago);
        unPagoCuota.setObservacion(observacion);
        unPagoCuota.setBorradoLogico(borradoLogico);
        unPagoCuota.persistir(this.entityManager);
    }

    public void eliminarPagoCuota(PagoCuota unPagoCuota) {
        unPagoCuota.setBorradoLogico(true);
        unPagoCuota.persistir(this.entityManager);
    }

    /**
     * Devuelve unaPagoCuota por ID incluido los borrados
     */
    public PagoCuota getPagoCuotaBD(Long id) {
        PagoCuota resultado;
        Query traerPagoCuota = this.entityManager.createQuery("SELECT auxCD FROM PagoCuota auxCD WHERE auxCD.idPagoCuota = " + id);
        resultado = (PagoCuota) traerPagoCuota.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los PagoCuota menos las borradas
     */
    public List<PagoCuota> getPagosCuotasBD() {
        String unaConsulta = "SELECT A FROM PagoCuota A WHERE A.borradoLogico = FALSE";
        List<PagoCuota> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//------------------------------FIN PAGO CUOTA----------------------------------

//----------------------------- CONCEPTOINGRESO --------------------------------
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

    /**
     * Devuelve un ConceptoIngreso por ID incluido los borrados
     */
    public ConceptoIngreso getConceptoIngresoBD(Long id) {
        ConceptoIngreso resultado;
        Query traerConceptoIngreso = this.entityManager.createQuery("SELECT auxCI FROM ConceptoIngreso auxCI WHERE auxCI.idConceptoIngreso = " + id);
        resultado = (ConceptoIngreso) traerConceptoIngreso.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los ConceptoIngreso menos los borrados
     */
    public List<ConceptoIngreso> getConceptosIngresosBD() {
        String unaConsulta = "SELECT A FROM ConceptoIngreso A WHERE A.borradoLogico = FALSE";
        List<ConceptoIngreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//----------------------------- FIN CONCEPTOINGRESO ----------------------------

//----------------------------- CONCEPTO EGRESO --------------------------------
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

    /**
     * Devuelve un ConceptoEgreso por ID incluido los Borrados
     */
    public ConceptoEgreso getConceptoEgresoBD(Long id) {
        ConceptoEgreso resultado;
        Query traerConceptoEgreso = this.entityManager.createQuery("SELECT auxCE FROM ConceptoEgreso auxCE WHERE auxCE.idConceptoEgreso = " + id);
        resultado = (ConceptoEgreso) traerConceptoEgreso.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los ConceptoEgreso menos los borrados
     */
    public List<ConceptoEgreso> getConceptosEgresosBD() {
        String unaConsulta = "SELECT A FROM ConceptoEgreso A WHERE A.borradoLogico = FALSE";
        List<ConceptoEgreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//----------------------------- FIN CONCEPTO EGRESO ----------------------------

//----------------------------- INGRESOSOTRO -----------------------------------
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

    /**
     * Devuelve un IngresoOtro por ID incluido los borrados
     */
    public IngresoOtro getIngresoOtroBD(Long id) {
        IngresoOtro resultado;
        Query traerIngresoOtro = this.entityManager.createQuery("SELECT auxIO FROM IngresoOtro auxIO WHERE auxIO.idIngresoOtro = " + id);
        resultado = (IngresoOtro) traerIngresoOtro.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los IngresoOtro menos los borrados
     */
    public List<IngresoOtro> getIngresosOtrosBD() {
        String unaConsulta = "SELECT A FROM IngresoOtro A WHERE A.borradoLogico = FALSE";
        List<IngresoOtro> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//----------------------------- FIN INGRESOSOTRO -------------------------------

//----------------------------- EGRESOS ----------------------------------------
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

    /**
     * Devuelve unEgreso por ID incluido los borrados
     */
    public Egreso getEgresoBD(Long id) {
        Egreso resultado;
        Query traerEgreso = this.entityManager.createQuery("SELECT auxE FROM Egreso auxE WHERE auxE.idEgreso = " + id);
        resultado = (Egreso) traerEgreso.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos los Egresos menos los borrados
     */
    public List<Egreso> getEgresosBD() {
        String unaConsulta = "SELECT A FROM Egreso A WHERE A.borradoLogico = FALSE";
        List<Egreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//----------------------------- FIN EGRESOS ------------------------------------

//------------------------------FRECUENCIA--------------------------------------
    public Frecuencia crearFrecuencia(String diaGeneracion, String diaVencimiento, Collection<Mes> meses) {
        return new Frecuencia(this.entityManager, diaGeneracion, diaVencimiento, meses);
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

    /**
     * Devuelve unaFrecuencia por ID incluido los borrados
     */
    public Frecuencia getFrecuenciaBD(Long id) {
        Frecuencia resultado;
        Query traerFrecuencia = this.entityManager.createQuery("SELECT auxE FROM Frecuencia auxE WHERE auxE.idFrecuencia = " + id);
        resultado = (Frecuencia) traerFrecuencia.getSingleResult();
        return resultado;
    }

    /**
     * Devuelve todos las Frecuencias menos los borrados
     */
    public List<Frecuencia> getFrecuenciasBD() {
        String unaConsulta = "SELECT A FROM Frecuencia A WHERE A.borradoLogico = FALSE";
        List<Frecuencia> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
//----------------------------FIN FRECUENCIA------------------------------------
}
