package interfaz;

import java.util.Scanner;

public class Interfaz {
	private static Scanner scanner = new Scanner(System.in);
	 private static VideojuegoController controller = new VideojuegoController();
	
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
				listarVideojuegos();
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
		private static void mostrarMenu() {
			System.out.println("Elige una opción: ");
			System.out.println("1. Agregar Videojuego");
			System.out.println("2. Listar Videojuegos");
			System.out.println("3. Borrar Videojuego");
			System.out.println("0. Salir de la aplicación");
		}
		
		private static void agregarVideojuego() {
			System.out.println("Ingrese el nombre del videojuego a crear: ");
			String nombre = scanner.nextLine();
			System.out.println("Ingrese la comapañía: ");
			String compania = scanner.nextLine();
			System.out.println("Ingrese la nota: ");
			String nota = scanner.nextLine();
			
			controller.agregarVideojuego(nombre, compania, nota);
		}
		
		private static void listarVideojuegos() {
	        controller.listarVideojuegos();
	    }
		
		private static void BorrarJuegos() {
	        System.out.print("Ingrese el nombre del videojuego a borrar: ");
	        String nombre = scanner.nextLine();
	        controller.BorrarJuegos(nombre);
	    }
}
	


