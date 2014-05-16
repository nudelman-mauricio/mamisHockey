package main;

import Interfaces.IMenuPrincipalInterface;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
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

        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            //Skin theSkinToUse = SkinLookAndFeel.loadThemePack("skins/royalInspiratthemepack.zip");
            Skin theSkinToUse = SkinLookAndFeel.loadThemePack("skins/toxicthemepack.zip");
            SkinLookAndFeel.setSkin(theSkinToUse);
            UIManager.setLookAndFeel(new SkinLookAndFeel());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        IMenuPrincipalInterface unaVentana = new IMenuPrincipalInterface(unaControladoraGlobal);
        SwingUtilities.updateComponentTreeUI(unaVentana);
        unaVentana.setLocationRelativeTo(null); //Mandar al centro
        unaVentana.setVisible(true);

    }
}
