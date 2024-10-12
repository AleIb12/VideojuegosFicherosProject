package interfaz;

import java.util.Scanner;
import entidad.Videojuego;
import modelo.persistencia.DaoVideojuegoFichero;
import persistencia.BorrarJuegos;

/**
 * Clase que representa la interfaz de usuario de la aplicación de gestión de videojuegos.
 * 
 * <p>Esta clase proporciona un menú para que los usuarios puedan agregar, listar y borrar
 * videojuegos. Utiliza la clase {@link DaoVideojuegoFichero} para manejar la persistencia
 * de datos y la clase {@link BorrarJuegos} para eliminar videojuegos.</p>
 */
public class Interfaz {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opcion;
		do {
			mostrarMenu();
			opcion = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcion) {
				case 1:
					agregarVideojuego();
					break;
				case 2:
					listarVideojuegos();
					break;
				case 3:
					borrarJuegos();
					break;
				case 0:
					System.out.println("Saliendo de la aplicación....");
					break;
				default:
					System.out.println("Opción no válida");
			}
		} while(opcion != 0);
	}
	
	/**
	 * Muestra el menú principal de la aplicación, permitiendo al usuario seleccionar
	 * una opción para interactuar con el sistema.
	 */
	private static void mostrarMenu() {
		System.out.println("Elige una opción: ");
		System.out.println("1. Agregar Videojuego");
		System.out.println("2. Listar Videojuegos");
		System.out.println("3. Borrar Videojuego");
		System.out.println("0. Salir de la aplicación");
	}
	
	/**
	 * Agrega un nuevo videojuego solicitando al usuario el nombre, la compañía y la nota.
	 * 
	 * <p>Se crea un objeto {@link Videojuego} con los datos ingresados y se registra
	 * en el sistema utilizando {@link DaoVideojuegoFichero}.</p>
	 */
	private static void agregarVideojuego() {
		DaoVideojuegoFichero daoVideojuegoFichero = new DaoVideojuegoFichero();
		System.out.println("Ingrese el nombre del videojuego a crear: ");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese la compañía: ");
		String compania = scanner.nextLine();
		System.out.println("Ingrese la nota: ");
		String nota = scanner.nextLine();

		Videojuego videojuego = new Videojuego(nombre, compania, nota);
		try {
			daoVideojuegoFichero.registrar(videojuego);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista todos los videojuegos registrados en el sistema.
	 * 
	 * <p>Este método llama al método {@link DaoVideojuegoFichero#listarVideojuegos()}
	 * para mostrar los videojuegos en la consola.</p>
	 */
	private static void listarVideojuegos() {
		DaoVideojuegoFichero daoVideojuegoFichero = new DaoVideojuegoFichero();
		try {
			daoVideojuegoFichero.listarVideojuegos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Borra un videojuego del sistema solicitando al usuario el nombre del videojuego a eliminar.
	 * 
	 * <p>Este método utiliza la clase {@link BorrarJuegos} para realizar la operación de borrado.</p>
	 */
	private static void borrarJuegos() {
		System.out.println("Ingrese el nombre del videojuego a borrar: ");
		String nombre = scanner.nextLine();
		BorrarJuegos borrarJuegos = new BorrarJuegos();
		borrarJuegos.borrarVideojuego(nombre); 
	}
}


