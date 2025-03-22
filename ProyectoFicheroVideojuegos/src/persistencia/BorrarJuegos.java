package persistencia;
import entidad.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class BorrarJuegos {
    private static String NOMBRE_COMPANIA_NOTA;
    
    static {
        try {
            String rutaBase = System.getProperty("user.dir");
            Path path = Paths.get(rutaBase, "src", "resources", "videojuegos.txt");
            if (!Files.exists(path)) {
                // Si no encuentra el archivo en la ruta src/resources, intentar directamente en resources
                path = Paths.get(rutaBase, "resources", "videojuegos.txt");
                if (!Files.exists(path)) {
                    // Crear el archivo si no existe
                    Files.createDirectories(path.getParent());
                    Files.createFile(path);
                }
            }
            NOMBRE_COMPANIA_NOTA = path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            // En caso de error, usar una ruta por defecto
            NOMBRE_COMPANIA_NOTA = "resources/videojuegos.txt";
        }
    }

    public void borrarVideojuego(String nombreborrar) {
        ArrayList<Videojuego> videojuegos = new ArrayList<>();
        File f = new File(NOMBRE_COMPANIA_NOTA);

        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr)) {
            
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] videojuegoData = linea.split("_");
                if (videojuegoData.length >= 3) {
                    String nombre = videojuegoData[0];
                    String compania = videojuegoData[1];
                    int nota = Integer.parseInt(videojuegoData[2]);
                    Videojuego v = new Videojuego(nombre, compania, nota);
                    videojuegos.add(v);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + NOMBRE_COMPANIA_NOTA);
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
            return;
        } catch (NumberFormatException e) {
            System.err.println("Error en el formato de la nota: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        boolean encontrado = videojuegos.removeIf(videojuego -> 
            videojuego.getNombreVideojuego().equals(nombreborrar));
        
        if (!encontrado) {
            System.err.println("No se encontró el videojuego: " + nombreborrar);
            return;
        }

        try (FileWriter fw = new FileWriter(f);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            for (Videojuego videojuego : videojuegos) {
                bw.write(String.format("%s_%s_%d", 
                    videojuego.getNombreVideojuego(),
                    videojuego.getCompañia(),
                    videojuego.getNota()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
