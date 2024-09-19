/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import jflex.exceptions.SilentExit;

/**
 *
 * @author Alfre
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir"); // Obtener la ruta base del proyecto
        String ruta1, ruta2;
        ruta1 = basePath + "/src/main/java/codigo/lex.flex";
        ruta2 = basePath + "/src/main/java/codigo/LexerCup.flex";
        String[] rutas = {"-parser", "Sintax", basePath + "/src/main/java/codigo/Sintax.cup"};
        
        try {
            generar(ruta1, ruta2, rutas);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generar(String ruta1, String ruta2, String[] rutas) throws IOException, Exception {
        try {
            File archivo = new File(ruta1);
            String[] ff = {archivo.toString()};
            try {
                jflex.Main.generate(ff);
            } catch (SilentExit ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            archivo = new File(ruta2);
            String[] ff2 = {archivo.toString()};
            jflex.Main.generate(ff2);
            java_cup.Main.main(rutas);
            
            String basePath = System.getProperty("user.dir"); // Obtener la ruta base del proyecto
            
            Path rutaSym = Paths.get(basePath + "/src/main/java/codigo/sym.java");
            if (Files.exists(rutaSym)) {
                Files.delete(rutaSym);
            }
            Files.move(
                    Paths.get(basePath + "/sym.java"),
                    Paths.get(basePath + "/src/main/java/codigo/sym.java")
            );
            
            Path rutaSin = Paths.get(basePath + "/src/main/java/codigo/Sintax.java");
            if (Files.exists(rutaSin)) {
                Files.delete(rutaSin);
            }
            Files.move(
                    Paths.get(basePath + "/Sintax.java"),
                    Paths.get(basePath + "/src/main/java/codigo/Sintax.java")
            );
            
        } catch (SilentExit ex) {         
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
