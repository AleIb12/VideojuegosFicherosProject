package VideojuegoApp;

import java.util.List;
import java.util.List;

public class VideojuegoService {
    private VideojuegoRepository repository = new VideojuegoRepository();

    /**
     * Valida los datos del videojuego y lo envía al repositorio para su almacenamiento.
     * 
     * @param videojuego Objeto Videojuego con los datos a agregar.
     * 
     * @throws IllegalArgumentException Si el nombre del videojuego tiene menos de 3 caracteres,
     * la compañía menos de 5 caracteres, o la nota no está entre 0 y 100.
     */
    public void agregarVideojuego(Videojuego videojuego) {
        validarVideojuego(videojuego);
        repository.guardarVideojuego(videojuego);
    }
    
    /**
     * Llama al repositorio para obtener una lista de todos los videojuegos y la devuelve.
     * 
     * @return List<String> Lista de videojuegos en formato "Nombre: NOMBRE - Compañía: COMPAÑIA - Nota: NOTA".
     */
    public List<String> listarVideojuegos() {
        return repository.listarVideojuegos();
    }

    /**
     * Llama al repositorio para borrar un videojuego por su nombre.
     * 
     * @param nombre Nombre del videojuego a borrar.
     * @return boolean `true` si el videojuego fue borrado con éxito, `false` si no se encontró.
     */
    public boolean borrarVideojuego(String nombre) {
        return repository.borrarVideojuego(nombre);
    }

    private void validarVideojuego(Videojuego videojuego) {
        if (videojuego.getNombre().length() < 3) {
            throw new IllegalArgumentException("El nombre del videojuego debe tener al menos 3 caracteres.");
        }
        if (videojuego.getCompania().length() < 5) {
            throw new IllegalArgumentException("La compañía debe tener al menos 5 caracteres.");
        }
        if (videojuego.getNota() < 0 || videojuego.getNota() > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100.");
        }
    }
}
