package modelo.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import entidad.Videojuego;


public class DaoVideojuegoFichero {
    

	private static final String NOMBRE_COMPAÑIA_NOTA = "videojuegos.txt";

    public List<Videojuego> listarVideojuegos() throws Exception {
        List<Videojuego> videojuegos = new ArrayList<>();
        try (FileReader fr = new FileReader(NOMBRE_COMPAÑIA_NOTA);
             BufferedReader br = new BufferedReader(fr)) {
            String cadena;
            while ((cadena = br.readLine()) != null) {
                String[] cadenaPartida = cadena.split("_");
                String nombreVideojuego = cadenaPartida[0];
                String companiaVideojuego = cadenaPartida[1];
                int notaVideojuego = Integer.parseInt(cadenaPartida[2]);
                
            }
        }
        return videojuegos;
    }

    public void registrar(Videojuego v) throws Exception {
        File f = new File(NOMBRE_COMPAÑIA_NOTA);
        if (!f.exists()) {
            f.createNewFile();
        }
        try (FileWriter fw = new FileWriter(NOMBRE_COMPAÑIA_NOTA, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(v.toString());
            bw.newLine();
        }
    }

    public void borrarVideojuego(String nombre) throws Exception {
        List<Videojuego> videojuegos = listarVideojuegos();
        videojuegos.removeIf(v -> v.getNota().equals(nota));
        try (FileWriter fw = new FileWriter(NOMBRE_COMPAÑIA_NOTA, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Videojuego v : videojuegos) {
                bw.write(v.toString());
                bw.newLine();
            }
        }
    }
   
   
}