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
 * 
 * @param nombreborrar
 * @param nomarchivo
 * <p>Este metodo inicialmente recoge dos strings los cuales uno sirve ya que sera el nombre del videojuego que queremos borrar
 * el otro string es el nombre del archivo con el cual trabjaremos 
 * creamos un arraylist para almacenar los videojuegos que pillemos del fichero 
 * con un buffered reader leemos todas las lineas y las separamos usando un split el cual posteriormente guardamos en el array videojuego
 * con este array creamos un objeto videojuego el cual finalmente añadimos al arraylist
 * despues de esto revisaremos el araylist cotejando los nombres y si el nombre coincide se eliminara del arraylist y despues de esto recribimos el fichero entero</p>
 * @exception FileNotFoundException para el fichero
 * @exception IOException para controlar errores
 */
public void borrar(String nombreborrar, String nomarchivo) {

	ArrayList<Videojuego> vd = new ArrayList<Videojuego>();
	File f = new File(nomarchivo);
	try (FileReader fr = new FileReader(f);BufferedReader br = new BufferedReader(fr)) {
		while(br.readLine()!=null) {
			Videojuego v = null;
			String[] videojuego = null;
			
				String linea = br.readLine();
				 videojuego = linea.split("_");
				 v.setNombreVideojuego(videojuego[0]);
				 v.setCompañia(videojuego[1]);
				 v.setNota(videojuego[2]);
				 vd.add(v);
				 
			
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	vd.removeIf(videojuego -> videojuego.getNombreVideojuego().equals(nombreborrar));
	try (FileWriter fw = new FileWriter(f);BufferedWriter bw = new BufferedWriter(fw)){
		for (Videojuego videojuego2 : vd) {
			bw.write(videojuego2.getNombreVideojuego() + "_" + videojuego2.getCompañia() + "_" + videojuego2.getNota());
		}
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	
}
}
