package main;

import Interfaces.IPanelLogueo;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
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
            // <editor-fold defaultstate="collapsed" desc="Lectura archivo conf BD">
            String url = null, user = null, pass = null, driver = null;
            try {
                // Abrimos el archivo
                FileInputStream fstream = new FileInputStream("src/Archivo/Conf BD.txt");
                // Creamos el objeto de entrada
                DataInputStream entrada = new DataInputStream(fstream);
                // Creamos el Buffer de Lectura
                BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));

                // Leer el archivo linea por linea
                url = buffer.readLine();
                user = buffer.readLine();
                pass = buffer.readLine();
                driver = buffer.readLine();
                // Cerramos el archivo
                entrada.close();
            } catch (Exception e) { //Catch de excepciones
                JOptionPane.showMessageDialog(null, "Error en la lectura del archivo de configuración de la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Conexion con la Base de Datos">
            Map<String, String> persistenceMap = new HashMap<String, String>();
            persistenceMap.put("javax.persistence.jdbc.url", url);
            persistenceMap.put("javax.persistence.jdbc.user", user);
            persistenceMap.put("javax.persistence.jdbc.password", pass);
            persistenceMap.put("javax.persistence.jdbc.driver", driver);

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mamisHockeyPU", persistenceMap); //nombre de la unidad de persistencia 
            EntityManager entityManager = entityManagerFactory.createEntityManager();
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

            // <editor-fold defaultstate="collapsed" desc="Abrir Ventana Log">
            IPanelLogueo unaVentana = new IPanelLogueo(unaControladoraGlobal, false);
            SwingUtilities.updateComponentTreeUI(unaVentana);
            unaVentana.setLocationRelativeTo(null); //Mandar al centro
            unaVentana.setVisible(true);
            // </editor-fold>
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error en la conexión con la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
