package persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import entidad.*;
public class BorrarJuegos {
	/**
	 * Borra un videojuego del archivo especificado basado en su nombre.
	 * 
	 * <p>Este método recibe el nombre de un videojuego y el nombre de un archivo. Lee todas las líneas del archivo,
	 * separa los datos de cada línea utilizando un carácter de separación ("_") y los guarda en una lista de videojuegos.
	 * Si el nombre de algún videojuego en la lista coincide con el nombre proporcionado, se elimina dicho videojuego
	 * de la lista. Finalmente, el archivo se sobrescribe con la lista actualizada de videojuegos.</p>
	 * 
	 * @param nombreborrar El nombre del videojuego que se desea borrar.
	 * <p>Se utiliza para buscar y eliminar el videojuego correspondiente de la lista.</p>
	 * 
	 * @param nomarchivo El nombre del archivo en el que se almacenan los videojuegos.
	 * <p>Especifica el archivo en el que se leerán y sobrescribirán los datos de los videojuegos.</p>
	 * 
	 * @exception FileNotFoundException Si el archivo especificado no se encuentra.
	 * <p>Se lanza cuando el archivo de videojuegos no existe o no puede ser localizado.</p>
	 * 
	 * @exception IOException Si ocurre un error durante la lectura o escritura del archivo.
	 * <p>Se lanza cuando hay un problema al leer las líneas del archivo o al escribir la nueva lista en el archivo.</p>
	 */
private static final String NOMBRE_COMPANIA_NOTA = "src/resources/videojuegos.txt";

 public void borrarVideojuego(String nombreborrar) {
     ArrayList<Videojuego> videojuegos = new ArrayList<>();
     File f = new File(NOMBRE_COMPANIA_NOTA);

  
     try (FileReader fr = new FileReader(f);
          BufferedReader br = new BufferedReader(fr)) {
          
         String linea;
         while ((linea = br.readLine()) != null) {
             String[] videojuegoData = linea.split("_");

            
             if (videojuegoData.length >= 3) {
                 String nombre = videojuegoData[0];
                 String compania = videojuegoData[1];
                 String nota = videojuegoData[2];

               
                 Videojuego v = new Videojuego(nombre, compania, Integer.parseInt(nota));
                 videojuegos.add(v);
             } else {
                 System.out.println("Formato incorrecto en la línea: " + linea);
             }
         }
     } catch (FileNotFoundException e) {
         System.out.println("Archivo no encontrado: " + NOMBRE_COMPANIA_NOTA);
         e.printStackTrace();
     } catch (IOException e) {
         System.out.println("Error al leer el archivo.");
         e.printStackTrace();
     }

  
     videojuegos.removeIf(videojuego -> videojuego.getNombreVideojuego().equals(nombreborrar));

    
     try (FileWriter fw = new FileWriter(f);
          BufferedWriter bw = new BufferedWriter(fw)) {
          
         for (Videojuego videojuego : videojuegos) {
             bw.write(videojuego.getNombreVideojuego() + "_" + videojuego.getCompañia() + "_" + videojuego.getNota());
             bw.newLine(); 
         }
     } catch (IOException e) {
         System.out.println("Error al escribir en el archivo.");
         e.printStackTrace();
     }
 }
}
