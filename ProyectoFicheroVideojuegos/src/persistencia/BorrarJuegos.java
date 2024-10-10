package persistencia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entidad.*;
public class BorrarJuegos {

public void borrar(Scanner sc, String nomarchivo) {
	ArrayList<Videojuego> vd = new ArrayList<Videojuego>();
	File f = new File(nomarchivo);
	try (FileReader fr = new FileReader(f);BufferedReader br = new BufferedReader(fr)) {
		while(br.readLine()!=null) {
			
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
}
