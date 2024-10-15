package persistencia;

import entidad.Videojuego;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BorrarJuegosTest {
    private static final String TEST_FILE_PATH = "src/resources/videojuegos_test.txt";
    private BorrarJuegos borrarJuegos;

    @BeforeEach
    public void setUp() throws IOException {
        borrarJuegos = new BorrarJuegos();
        File testFile = new File(TEST_FILE_PATH);
        if (!testFile.exists()) {
            testFile.createNewFile(); 
        }

       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("Super Mario_Bros Inc_95\n");
            writer.write("The Legend of Zelda_Nintendo_85\n");
            writer.write("Minecraft_Mojang_90\n");
        }
    }

    @AfterEach
    public void tearDown() {
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete(); 
        }
    }

    @Test
    public void testBorrarVideojuego() {
      
        borrarJuegos.borrarVideojuego("Super Mario");

       
        ArrayList<Videojuego> videojuegos = cargarVideojuegosDesdeArchivo();
        assertEquals(2, videojuegos.size()); 
        assertFalse(videojuegos.stream().anyMatch(v -> v.getNombreVideojuego().equals("Super Mario")), "El videojuego 'Super Mario' no debería estar presente en la lista.");
    }

    @Test
    public void testBorrarVideojuegoInexistente() {
        
        borrarJuegos.borrarVideojuego("Call of Duty");

       
        ArrayList<Videojuego> videojuegos = cargarVideojuegosDesdeArchivo();
        assertEquals(3, videojuegos.size(), "El número de videojuegos debería ser el mismo.");
    }

    private ArrayList<Videojuego> cargarVideojuegosDesdeArchivo() {
        ArrayList<Videojuego> videojuegos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] videojuegoData = linea.split("_");
                if (videojuegoData.length >= 3) {
                    String nombre = videojuegoData[0];
                    String compania = videojuegoData[1];
                    int nota = Integer.parseInt(videojuegoData[2]);
                    videojuegos.add(new Videojuego(nombre, compania, nota));
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videojuegos;
    }
}


