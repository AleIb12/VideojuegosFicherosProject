package VideojuegoApp;

import java.util.Scanner;

public class VideojuegoApp {
	/**
	 * Método  que gestiona el ciclo de vida de la aplicación.
	 * Muestra un menú y permite al usuario agregar, listar o borrar videojuegos.
	 * 
	 * @param args Argumentos pasados desde la línea de comandos (no se utilizan en este caso).
	 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideojuegoService service = new VideojuegoService();

        while (true) {
            System.out.println("---- Menú ----");
            System.out.println("1. Agregar videojuego");
            System.out.println("2. Listar videojuegos");
            System.out.println("3. Borrar videojuego por nombre");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();  
            
            switch (opcion) {
                case 1:
                    agregarVideojuego(scanner, service);
                    break;
                case 2:
                    listarVideojuegos(service);
                    break;
                case 3:
                    borrarVideojuego(scanner, service);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Solicita los datos del videojuego al usuario (nombre, compañía y nota) 
     * y los envía al servicice para su almacenamiento.
     * 
     * @param scanner Objeto Scanner para la lectura de datos desde la consola.
     * @param service Servicio encargado de la lógica de negocio de videojuegos.
     * 
     * @throws IllegalArgumentException Si los datos del videojuego no son válidos.
     */
    private static void agregarVideojuego(Scanner scanner, VideojuegoService service) {
        System.out.print("Nombre del videojuego: ");
        String nombre = scanner.nextLine();
        System.out.print("Compañía: ");
        String compania = scanner.nextLine();
        System.out.print("Nota (0-100): ");
        int nota = scanner.nextInt();
        scanner.nextLine();  

        try {
            service.agregarVideojuego(new Videojuego(nombre, compania, nota));
            System.out.println("Videojuego agregado con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Llama al servicio para obtener una lista de videojuegos y los muestra en la consola.
     * 
     * @param service Servicio encargado de la lógica de negocio de videojuegos.
     */
    private static void listarVideojuegos(VideojuegoService service) {
        for (String info : service.listarVideojuegos()) {
            System.out.println(info);
        }
    }
    
    /**
     * Solicita al usuario el nombre del videojuego que desea borrar y llama al servicio 
     * para eliminarlo del archivo. Informa al usuario si la operación fue exitosa.
     * 
     * @param scanner Objeto Scanner para la lectura de datos desde la consola.
     * @param service Servicio encargado de la lógica de negocio de videojuegos.
     */
    private static void borrarVideojuego(Scanner scanner, VideojuegoService service) {
        System.out.print("Nombre del videojuego a borrar: ");
        String nombre = scanner.nextLine();

        if (service.borrarVideojuego(nombre)) {
            System.out.println("Videojuego borrado con éxito.");
        } else {
            System.out.println("No se encontró el videojuego.");
        }
    }
}
