package VideojuegoApp;

import java.io.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoRepository {
    private final String ARCHIVO = "videojuegos.txt";

    /**
     * Guarda un nuevo videojuego en el archivo "videojuegos.txt".
     * 
     * @param videojuego Objeto Videojuego que se desea guardar.
     */
    public void guardarVideojuego(Videojuego videojuego) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(videojuego.getNombre() + "_" + videojuego.getCompania() + "_" + videojuego.getNota());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lee el archivo "videojuegos.txt" y devuelve una lista de videojuegos con su información.
     * 
     * @return List<String> Lista de videojuegos en formato "Nombre: NOMBRE - Compañía: COMPAÑIA - Nota: NOTA".
     */

    public List<String> listarVideojuegos() {
        List<String> videojuegos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("_");
                videojuegos.add("Nombre: " + partes[0] + " - Compañía: " + partes[1] + " - Nota: " + partes[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videojuegos;
    }

    public boolean borrarVideojuego(String nombre) {
        File archivo = new File(ARCHIVO);
        File archivoTemporal = new File("videojuegos_temp.txt");
        boolean borrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("_");
                if (!partes[0].equalsIgnoreCase(nombre)) {
                    writer.write(linea);
                    writer.newLine();
                } else {
                    borrado = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (borrado) {
            archivo.delete();
            archivoTemporal.renameTo(archivo);
        }

        return borrado;
    }
}
