package main;

import Interfaces.IMenuPrincipalInterface;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            // <editor-fold defaultstate="collapsed" desc="Conexion con la Base de Datos">
            EntityManagerFactory entityManagerFactory = null;
            EntityManager entityManager = null;
            entityManagerFactory = Persistence.createEntityManagerFactory("mamisHockeyPU"); //nombre de la unidad de persistencia 
            entityManager = entityManagerFactory.createEntityManager();
            // </editor-fold>

            ControladoraGlobal unaControladoraGlobal = new ControladoraGlobal(entityManager);

            // <editor-fold defaultstate="collapsed" desc="Aplicar Skin">
            try {
                JFrame.setDefaultLookAndFeelDecorated(true);
                //Skin theSkinToUse = SkinLookAndFeel.loadThemePack("skins/royalInspiratthemepack.zip");
                Skin theSkinToUse = SkinLookAndFeel.loadThemePack("skins/toxicthemepack.zip");
                SkinLookAndFeel.setSkin(theSkinToUse);
                UIManager.setLookAndFeel(new SkinLookAndFeel());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Eror al aplicar el Skin, se ejecutará el programa con apariencia por defecto.", "Problema al cargar el Skin", JOptionPane.WARNING_MESSAGE);
            }
            // </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Abrir Ventana Principal">
            IMenuPrincipalInterface unaVentana = new IMenuPrincipalInterface(unaControladoraGlobal);
            SwingUtilities.updateComponentTreeUI(unaVentana);
            unaVentana.setLocationRelativeTo(null); //Mandar al centro
            unaVentana.setVisible(true);
            // </editor-fold>
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error en la conexión con la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(exception.toString());
        }
    }
}
