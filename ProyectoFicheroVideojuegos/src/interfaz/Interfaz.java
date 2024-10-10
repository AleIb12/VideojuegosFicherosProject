package interfaz;

import java.util.Scanner;

public class Interfaz {
	
	private static Scanner scanner = new Scanner(System.in);
	private static DaoVideojuegoFichero NOMBRE_COMAPAÑIA_NOTA = new VideojuegoController();
	 
	public static void main(String[]args) {
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
				ListarVideojuegos();
				break;
				
			case 3:
				BorrarJuegos();
				break;
			
			case 0:
				System.out.println("Saliendo de la aplicación....");
				break;
			
			default:
				System.out.println("Opción no valida");
			}
		}while(opcion !=0);
	}
	
	/**
	 * Muestra el menú de inicio en el que tendrás que seleccionar una opción para
	 * acceder a una de las secciones
	 */
		private static void mostrarMenu() {
			System.out.println("Elige una opción: ");
			System.out.println("1. Agregar Videojuego");
			System.out.println("2. Listar Videojuegos");
			System.out.println("3. Borrar Videojuego");
			System.out.println("0. Salir de la aplicación");
			
		}
		/**
		 * Método agregarVideojuego, su función es ser un menú, pides al usuario
		 * el nombre del videojuego, la comapñía del videojuego y la nota del mismo
		 * y el usuario deberá con introducir dicha información, la cual se guardará.
		 */
		private static void agregarVideojuego() {
			System.out.println("Ingrese el nombre del videojuego a crear: ");
			String nombre = scanner.nextLine();
			System.out.println("Ingrese la comapañía: ");
			String compania = scanner.nextLine();
			System.out.println("Ingrese la nota: ");
			String nota = scanner.nextLine();
			
			NOMBRE_COMAPAÑIA_NOTA.agregarVideojuego(nombre, compania, nota);
		}
		
		/**
		 * Método para listar los videojuegos creados por el usuario
		 */
		private static void ListarVideojuegos() {
			NOMBRE_COMAPAÑIA_NOTA.ListarVideojuegos();
	    }
		/**
		 * Método para ingresar el nombre del Videojuego y poder borrarlo,
		 * lo busca por el nombre del videojuego y lo borra
		 */
		private static void BorrarJuegos() {
	        System.out.print("Ingrese el nombre del videojuego a borrar: ");
	        String nombre = scanner.nextLine();
	        NOMBRE_COMAPAÑIA_NOTA.BorrarJuegos(nombre);
	    }
}
	


