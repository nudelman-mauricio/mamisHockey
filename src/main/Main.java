/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mauricio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
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
        unaControladoraGlobal.crearCategoria(4, "Menores");
//        Date ;
//        unaControladoraGlobal.crearTorneo(null, null, null);

        System.out.println(unaControladoraGlobal.getUnaControladoraDeportiva().buscarCategoriaBD(new Long("1")));

//        Long aux = new Long("251");
//        unaControladoraGlobal.eliminarCategoria(unaControladoraGlobal.buscarCategoria(aux));
//        
//        System.out.println(unaControladoraGlobal.buscarCategoria(aux));
        System.out.println("");

        //----------------------------------------- FIN CODIGO DE PRUEBAS -----------------------------------------
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

}
