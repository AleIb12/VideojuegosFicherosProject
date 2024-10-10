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
