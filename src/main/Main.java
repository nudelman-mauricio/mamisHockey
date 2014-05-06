package main;

import Interfaces.MenuPrincipalInterface;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Mauricio
 */
public class Main {

    public static void main(String[] args) {
        /*EntityManagerFactory emf = null;
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
        } */
        
        //Aplicar skin a la aplicacion. 
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            Skin theSkinToUse = SkinLookAndFeel.loadThemePack("skins/underlingthemepack.zip");
            SkinLookAndFeel.setSkin(theSkinToUse);
            UIManager.setLookAndFeel(new SkinLookAndFeel());
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Creacion de la ventana
        MenuPrincipalInterface unaVentana = new MenuPrincipalInterface ();
        SwingUtilities.updateComponentTreeUI ( unaVentana ) ;        
        unaVentana.setVisible(true);
        
    }

}
