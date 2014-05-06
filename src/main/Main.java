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
