package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logicaNegocios.Indumentaria;

/**
 *
 * @author Mauricio
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("mamisHockeyPU"); //nombre de la unidad de persistencia 
            em = emf.createEntityManager();
            System.out.println("EntityManager Created: " + emf);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //----------------------------------------- CODIGO DE PRUEBAS -----------------------------------------
        ControladoraGlobal unaControladoraGlobal = new ControladoraGlobal(em);

        Indumentaria unaIndumentaria = new Indumentaria(em, "rojo", "amarillo", "verde");
        
        
        
//        unaControladoraGlobal.crearCategoria(4, "Menores");
//        Date ;
//        unaControladoraGlobal.crearTorneo(null, null, null);

//        System.out.println(unaControladoraGlobal.getUnaControladoraDeportiva().buscarCategoriaBD(new Long("1")));

//        Long aux = new Long("251");
//        unaControladoraGlobal.eliminarCategoria(unaControladoraGlobal.buscarCategoria(aux));
//        
//        System.out.println(unaControladoraGlobal.buscarCategoria(aux));

        //----------------------------------------- FIN CODIGO DE PRUEBAS -----------------------------------------
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

}
