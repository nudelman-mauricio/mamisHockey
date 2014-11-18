package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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
import logicaNegocios.IngresoOtro;
import logicaNegocios.Mes;
import logicaNegocios.PagoCuota;
import logicaNegocios.PlanillaPago;
import logicaNegocios.Socia;
import logicaNegocios.TipoCancha;
import logicaNegocios.TipoEstado;

public class ControladoraContabilidad {

    private final EntityManager entityManager;

    public ControladoraContabilidad(EntityManager em) {
        this.entityManager = em;
        this.construirMeses();
    }

    // <editor-fold defaultstate="collapsed" desc="Conceptos Deportivos">
    public ConceptoDeportivo crearConceptoDeportivo(double monto, String concepto, ArrayList<Mes> meses, TipoCancha unTipoCancha, TipoEstado unTipoEstado) {
        return new ConceptoDeportivo(this.entityManager, monto, concepto, meses, unTipoCancha, unTipoEstado);
    }

    public void modificarConceptoDeportivo(ConceptoDeportivo unConceptoDeportivo, double monto, String concepto, ArrayList<Mes> meses, TipoCancha unTipoCancha, TipoEstado unTipoEstado, boolean borradoLogico) {
        unConceptoDeportivo.setMonto(monto);
        unConceptoDeportivo.setConcepto(concepto);
        unConceptoDeportivo.setMeses(meses);
        unConceptoDeportivo.setUnTipoCancha(unTipoCancha);
        unConceptoDeportivo.setUnTipoEstado(unTipoEstado);
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
        Query traerConceptoDeportivo = this.entityManager.createQuery("SELECT auxCD FROM ConceptoDeportivo auxCD WHERE auxCD.idConceptoDeportivo = " + id);
        return ((ConceptoDeportivo) traerConceptoDeportivo.getSingleResult());
    }

    /**
     * Devuelve unConceptoDeportivo por CONCEPTO
     */
    public ConceptoDeportivo getConceptoDeportivoBD(String concepto) {
        List<ConceptoDeportivo> unaListaResultado = this.entityManager.createQuery("SELECT cd FROM ConceptoDeportivo cd WHERE cd.borradoLogico = FALSE AND cd.concepto LIKE '" + concepto + "'").getResultList();
        if (unaListaResultado.isEmpty()) {
            return null;
        } else {
            return unaListaResultado.get(0);
        }
    }

    public Vector<ConceptoDeportivo> getConceptosDeportivosAutomaticosBD() {
        Vector<ConceptoDeportivo> unaListaResultado = new Vector<ConceptoDeportivo>();
        unaListaResultado.add(this.getConceptoDeportivoBD("Cuota Socia"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Cuota Jugadora"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Cuota Licencia"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Cuota Baja por Mora"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Pase"));
        return unaListaResultado;
    }

    public Vector<ConceptoDeportivo> getConceptosDeportivosManualesBD() {
        Vector<ConceptoDeportivo> unaListaResultado = new Vector<ConceptoDeportivo>();
        unaListaResultado.add(this.getConceptoDeportivoBD("Fichaje"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Re-Fichaje"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Inscripción"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Re-Inscripción"));
        unaListaResultado.add(this.getConceptoDeportivoBD("Otro"));
        return unaListaResultado;
    }

    /**
     * Devuelve todos los ConceptosDeportivos menos los borrados
     *
     * @return List(ConceptoDeportivo)
     */
    public List<ConceptoDeportivo> getConceptosDeportivosBD() {
        String unaConsulta = "SELECT A FROM ConceptoDeportivo A WHERE A.borradoLogico = FALSE";
        List<ConceptoDeportivo> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Deudas">
    public void crearDeudaEquipo(Equipo unEquipo, Date fechaGeneracion, ConceptoDeportivo unConceptoDeportivo, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        Deuda unaDeuda = new Deuda(this.entityManager, fechaGeneracion, unConceptoDeportivo, observacion, montoTotal, cantCuotas, primerVencimiento);
        unEquipo.agregarDeuda(this.entityManager, unaDeuda);
    }

    public Deuda crearDeudaSocia(Socia unaSocia, Date fechaGeneracion, ConceptoDeportivo unConceptoDeportivo, String observacion, double montoTotal, int cantCuotas, Date primerVencimiento) {
        Deuda unaDeuda = new Deuda(this.entityManager, fechaGeneracion, unConceptoDeportivo, observacion, montoTotal, cantCuotas, primerVencimiento);
        unaSocia.agregarDeuda(this.entityManager, unaDeuda);
        return unaDeuda;
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

    public List<Deuda> getDeudaoEntreFechas(Date desde, Date hasta) {
        String unaConsulta = "SELECT A FROM Deuda A WHERE A.borradoLogico = FALSE AND A.fechaGeneracion >= '" + desde + "' AND A.fechaGeneracion<'" + hasta + "' ORDER BY A.fechaGeneracion ASC";
        List<Deuda> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    public Deuda getDeudaPagoCuota(PagoCuota unPago) {
        List<Deuda> unaListaResultado = this.entityManager.createQuery("SELECT D FROM Deuda D, Cuota C, PagoCuota P JOIN D.cuotas R WHERE (R.idCuota = C.idCuota) AND (C.unPagoCuota.idPagoCuota = P.idPagoCuota) AND P.idPagoCuota =" + unPago.getIdPagoCuota()).getResultList();
        if (!unaListaResultado.isEmpty()) {
            return unaListaResultado.get(0);
        }
        return null;
//        Alternativa que YA SABEMOS FUNCIONA BIEN
//        
//        for (Deuda unaDeuda : this.getDeudaBD()) {
//            for (Cuota unaCuota : unaDeuda.getCuotas()) {
//                if (unaCuota.getUnPagoCuota() == unPago) {
//                    return unaDeuda;
//                }
//            }
//        }
//        return null;
    }

    public Deuda getDeudaDeCuota(Cuota unaCuotaParametro) {
        List<Deuda> unaListaResultado = this.entityManager.createQuery("SELECT D FROM Deuda D, Cuota C JOIN D.cuotas R WHERE (R.idCuota = C.idCuota) AND C.idCuota=" + unaCuotaParametro.getIdCuota()).getResultList();
        if (!unaListaResultado.isEmpty()) {
            return unaListaResultado.get(0);
        }
        return null;

//Alternativa que YA SABEMOS FUNCIONA BIEN
//
//        for (Deuda unaDeuda : this.getDeudaBD()) {
//            for (Cuota unaCuota : unaDeuda.getCuotas()) {
//                if (unaCuota == unaCuotaParametro) {
//                    return unaDeuda;
//                }
//            }
//        }
//        return null;
    }

    public Socia getSociaResponsableDeuda(Deuda unaDeuda) {
        List<Socia> unaListaResultado = this.entityManager.createQuery("SELECT S FROM Socia S, Deuda D JOIN S.deudas R WHERE (R.idDeuda = D.idDeuda) AND D.idDeuda=" + unaDeuda.getIdDeuda()).getResultList();
        if (!unaListaResultado.isEmpty()) {
            return unaListaResultado.get(0);
        }
        return null;
    }

    public Equipo getEquipoResponsableDeuda(Deuda unaDeuda) {
        List<Equipo> unaListaResultado = this.entityManager.createQuery("SELECT E FROM Equipo E, Deuda D JOIN E.deudas R WHERE (R.idDeuda = D.idDeuda) AND D.idDeuda=" + unaDeuda.getIdDeuda()).getResultList();
        if (!unaListaResultado.isEmpty()) {
            return unaListaResultado.get(0);
        }
        return null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Cuotas">
    public Cuota getCuotaBD(Long id) {
        Cuota resultado;
        Query traerCuota = this.entityManager.createQuery("SELECT auxCD FROM Cuota auxCD WHERE auxCD.idCuota = " + id);
        resultado = (Cuota) traerCuota.getSingleResult();
        return resultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Pago Cuotas">
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
        String unaConsulta = "SELECT A FROM PagoCuota A WHERE A.borradoLogico = FALSE ORDER BY A.fechaPago ASC";
        List<PagoCuota> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    public List<PagoCuota> getPagosCuotasEntreFechasBD(Date desde, Date hasta) {
        String unaConsulta = "SELECT A FROM PagoCuota A WHERE A.borradoLogico = FALSE AND A.fechaPago >= '" + desde + "' AND A.fechaPago<'" + hasta + "' AND A.monto > 0 ORDER BY A.fechaPago ASC";
        List<PagoCuota> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Concepto Ingreso">
    public void crearConceptoIngreso(String nombre, String detalle) {
        new ConceptoIngreso(this.entityManager, nombre, detalle);
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
        Query traerConceptoIngreso = this.entityManager.createQuery("SELECT auxCI FROM ConceptoIngreso auxCI WHERE auxCI.idConceptoIngreso = " + id);
        return ((ConceptoIngreso) traerConceptoIngreso.getSingleResult());
    }

    /**
     * Devuelve todos los ConceptoIngreso menos los borrados
     */
    public List<ConceptoIngreso> getConceptosIngresosBD() {
        String unaConsulta = "SELECT A FROM ConceptoIngreso A WHERE A.borradoLogico = FALSE";
        List<ConceptoIngreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Concepto Egreso">
    public void crearConceptoEgreso(String nombre, String detalle) {
        new ConceptoEgreso(this.entityManager, nombre, detalle);
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
        Query traerConceptoEgreso = this.entityManager.createQuery("SELECT auxCE FROM ConceptoEgreso auxCE WHERE auxCE.idConceptoEgreso = " + id);
        return ((ConceptoEgreso) traerConceptoEgreso.getSingleResult());
    }

    /**
     * Devuelve todos los ConceptoEgreso menos los borrados
     */
    public List<ConceptoEgreso> getConceptosEgresosBD() {
        String unaConsulta = "SELECT A FROM ConceptoEgreso A WHERE A.borradoLogico = FALSE";
        List<ConceptoEgreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ingreso Otro">
    public void crearIngresoOtro(Date fecha, double monto, ConceptoIngreso unConceptoIngreso, String detalle) {
        new IngresoOtro(this.entityManager, fecha, unConceptoIngreso, monto, detalle);
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
        Query traerIngresoOtro = this.entityManager.createQuery("SELECT auxIO FROM IngresoOtro auxIO WHERE auxIO.idIngresoOtro = " + id);
        return ((IngresoOtro) traerIngresoOtro.getSingleResult());
    }

    /**
     * Devuelve todos los IngresoOtro menos los borrados
     */
    public List<IngresoOtro> getIngresosOtrosBD() {
        String unaConsulta = "SELECT A FROM IngresoOtro A WHERE A.borradoLogico = FALSE";
        List<IngresoOtro> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    public IngresoOtro getUltimoIngresoOtro() {
        String unaConsulta = "SELECT A FROM IngresoOtro A WHERE A.borradoLogico = FALSE ORDER BY A.fecha DESC";
        List<IngresoOtro> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        for (IngresoOtro unIngreso : unaListaResultado) {
            return unIngreso;
        }
        return null;
    }

    public IngresoOtro getPrimerIngresoOtro() {
        String unaConsulta = "SELECT A FROM IngresoOtro A WHERE A.borradoLogico = FALSE ORDER BY A.fecha ASC";
        List<IngresoOtro> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        for (IngresoOtro unIngreso : unaListaResultado) {
            return unIngreso;
        }
        return null;
    }

    public List<IngresoOtro> getIngresoOtroEntreFechas(Date desde, Date hasta) {
        String unaConsulta = "SELECT A FROM IngresoOtro A WHERE A.borradoLogico = FALSE AND A.fecha >= '" + desde + "' AND A.fecha<'" + hasta + "' ORDER BY A.fecha ASC";
        List<IngresoOtro> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Egreso">
    public void crearEgreso(Date fecha, double monto, ConceptoEgreso unConceptoEgreso, String observacion) {
        new Egreso(this.entityManager, fecha, monto, unConceptoEgreso, observacion);
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
        Query traerEgreso = this.entityManager.createQuery("SELECT auxE FROM Egreso auxE WHERE auxE.idEgreso = " + id);
        return ((Egreso) traerEgreso.getSingleResult());
    }

    /**
     * Devuelve todos los Egresos menos los borrados
     */
    public List<Egreso> getEgresosBD() {
        String unaConsulta = "SELECT A FROM Egreso A WHERE A.borradoLogico = FALSE ORDER BY A.fecha ASC";
        List<Egreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }

    public Egreso getUltimoEgreso() {
        String unaConsulta = "SELECT A FROM Egreso A WHERE A.borradoLogico = FALSE ORDER BY A.fecha DESC";
        List<Egreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        if (unaListaResultado.isEmpty()) {
            return null;
        } else {
            return unaListaResultado.get(0);
        }
    }

    public Egreso getPrimerEgreso() {
        String unaConsulta = "SELECT A FROM Egreso A WHERE A.borradoLogico = FALSE ORDER BY A.fecha ASC";
        List<Egreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        if (unaListaResultado.isEmpty()) {
            return null;
        } else {
            return unaListaResultado.get(0);
        }
    }

    public List<Egreso> getEgresosEntreFechas(Date desde, Date hasta) {
        String unaConsulta = "SELECT A FROM Egreso A WHERE A.borradoLogico = FALSE AND A.fecha >= '" + desde + "' AND A.fecha<'" + hasta + "' ORDER BY A.fecha ASC";
        List<Egreso> unaListaResultado = this.entityManager.createQuery(unaConsulta).getResultList();
        return unaListaResultado;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Meses">
    private void construirMeses() {
        Query tablaMesVacia = entityManager.createQuery("SELECT A FROM Mes A");
        if (tablaMesVacia.getResultList().isEmpty()) {
            new Mes(entityManager, "Enero");
            new Mes(entityManager, "Febrero");
            new Mes(entityManager, "Marzo");
            new Mes(entityManager, "Abril");
            new Mes(entityManager, "Mayo");
            new Mes(entityManager, "Junio");
            new Mes(entityManager, "Julio");
            new Mes(entityManager, "Agosto");
            new Mes(entityManager, "Septiembre");
            new Mes(entityManager, "Octubre");
            new Mes(entityManager, "Nobiembre");
            new Mes(entityManager, "Diciembre");
        }
    }

    /**
     * Devuelve una instancia mes de la BD de acurdo al numero de mes
     *
     * @param numeroMes
     * @return Mes
     */
    public Mes getMesDB(int numeroMes) {
        String nombreMes = "Diciembre";
        if (numeroMes == 1) {
            nombreMes = "Enero";
        } else {
            if (numeroMes == 2) {
                nombreMes = "Febrero";
            } else {
                if (numeroMes == 3) {
                    nombreMes = "Marzo";
                } else {
                    if (numeroMes == 4) {
                        nombreMes = "Abril";
                    } else {
                        if (numeroMes == 5) {
                            nombreMes = "Mayo";
                        } else {
                            if (numeroMes == 6) {
                                nombreMes = "Junio";
                            } else {
                                if (numeroMes == 7) {
                                    nombreMes = "Julio";
                                } else {
                                    if (numeroMes == 8) {
                                        nombreMes = "Agosto";
                                    } else {
                                        if (numeroMes == 9) {
                                            nombreMes = "Septiembre";
                                        } else {
                                            if (numeroMes == 10) {
                                                nombreMes = "Octubre";
                                            } else {
                                                if (numeroMes == 11) {
                                                    nombreMes = "Nobiembre";
                                                } else {
                                                    nombreMes = "Diciembre";
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String unaConsulta = "SELECT M FROM Mes M WHERE M.nombre LIKE '" + nombreMes + "'";
        Query traerMes = this.entityManager.createQuery(unaConsulta);
        return ((Mes) traerMes.getSingleResult());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PlanillaPago">
    public PlanillaPago crearPlanillaPago(Equipo unEquipoResponsasble, Date fechaPago, double monto, long nroRecibo, Socia responsablePago) {
        PlanillaPago unaPlanillaPago = new PlanillaPago(this.entityManager, fechaPago, monto, nroRecibo, responsablePago);
        unEquipoResponsasble.agregarPlanillaPago(this.entityManager, unaPlanillaPago);
        return unaPlanillaPago;

    }

    public void modificarPlanillaPago(PlanillaPago unaPlanillaPago, String rutaPDF) {
        unaPlanillaPago.setRutaPDF(rutaPDF);
        unaPlanillaPago.persistir(this.entityManager);
    }

    public void modificarPlanillaPago(PlanillaPago unaPlanillaPago, Date fechaPago, double monto, long nroRecibo, Socia responsablePago, String rutaPDF) {
        unaPlanillaPago.setFechaPago(fechaPago);
        unaPlanillaPago.setMonto(monto);
        unaPlanillaPago.setNroRecibo(nroRecibo);
        unaPlanillaPago.setResponsablePago(responsablePago);
        unaPlanillaPago.setRutaPDF(rutaPDF);
        unaPlanillaPago.persistir(this.entityManager);
    }

    public PlanillaPago getPlanillaPagoBD(Long id) {
        Query traerPlanillaPago = this.entityManager.createQuery("SELECT aux FROM PlanillaPago aux WHERE aux.id = " + id);
        return ((PlanillaPago) traerPlanillaPago.getSingleResult());
    }

    public List<PlanillaPago> getPlanillasPagosBD() {
        List<PlanillaPago> unaListaResultado = this.entityManager.createQuery("SELECT aux FROM PlanillaPago aux ORDER BY aux.fechaPago ASC").getResultList();
        return unaListaResultado;
    }
    // </editor-fold>
}
