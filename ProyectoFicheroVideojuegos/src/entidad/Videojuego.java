package entidad;

import java.util.Objects;

/**
 * Representa un videojuego con un nombre, una compañía y una nota.
 * Proporciona métodos para obtener y establecer estas propiedades,
 * asegurando que se cumplan ciertas validaciones al asignarlas.
 */
public class Videojuego {
    private String nombre;
    private String compania;
    private int nota;

    /**
     * Crea una nueva instancia de Videojuego.
     *
     * @param nombreVideojuego el nombre del videojuego
     * @param compañia la compañía que desarrolló el videojuego
     * @param nota la nota del videojuego, que debe estar entre 0 y 100
     * @throws IllegalArgumentException si el nombre del videojuego es vacío o tiene menos de 3 letras,
     *         si la compañía es vacía o tiene menos de 5 letras, o si la nota no está en el rango permitido.
     */
    public Videojuego(String nombreVideojuego, String compañia, int nota) {
        if (nombreVideojuego == null || nombreVideojuego.trim().isEmpty() || nombreVideojuego.length() < 3) {
            throw new IllegalArgumentException("El nombre del videojuego no puede estar vacío y debe tener al menos 3 letras.");
        }
        if (compañia == null || compañia.trim().isEmpty() || compañia.length() < 5) {
            throw new IllegalArgumentException("La compañía no puede estar vacía y debe tener al menos 5 letras.");
        }
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("La nota no puede ser inferior a 0 ni superior a 100. No admite decimales.");
        }

        this.nombre = nombreVideojuego;
        this.compania = compañia;
        this.nota = nota;
    }

    /**
     * Obtiene el nombre del videojuego.
     *
     * @return el nombre del videojuego
     */
    public String getNombreVideojuego() {
        return nombre;
    }

    /**
     * Establece el nombre del videojuego.
     *
     * @param nombreVideojuego el nuevo nombre del videojuego
     * @throws IllegalArgumentException si el nombre es vacío o tiene menos de 3 letras
     */
    public void setNombreVideojuego(String nombreVideojuego) {
        if (nombreVideojuego == null || nombreVideojuego.trim().isEmpty() || nombreVideojuego.length() < 3) {
            throw new IllegalArgumentException("El nombre del videojuego no puede estar vacío y debe tener al menos 3 letras.");
        }
        this.nombre = nombreVideojuego;
    }

    /**
     * Obtiene la compañía del videojuego.
     *
     * @return la compañía del videojuego
     */
    public String getCompañia() {
        return compania;
    }

    /**
     * Establece la compañía del videojuego.
     *
     * @param compañia la nueva compañía del videojuego
     * @throws IllegalArgumentException si la compañía es vacía o tiene menos de 5 letras
     */
    public void setCompañia(String compañia) {
        if (compañia == null || compañia.trim().isEmpty() || compañia.length() < 5) {
            throw new IllegalArgumentException("La compañía no puede estar vacía y debe tener al menos 5 letras.");
        }
        this.compania = compañia;
    }

    /**
     * Obtiene la nota del videojuego.
     *
     * @return la nota del videojuego
     */
    public int getNota() {
        return nota;
    }

    /**
     * Establece la nota del videojuego.
     *
     * @param nota la nueva nota del videojuego
     * @throws IllegalArgumentException si la nota es inferior a 0 o superior a 100
     */
    public void setNota(int nota) {
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("La nota no puede ser inferior a 0 ni superior a 100. No admite decimales.");
        }
        this.nota = nota;
    }

    /**
     * Devuelve una representación en forma de cadena del videojuego.
     *
     * @return una cadena que representa el videojuego
     */
    @Override
    public String toString() {
        return "Videojuego [NombreVideojuego=" + nombre + ", Compañia=" + compania + ", Nota=" + nota + "]";
    }

    /**
     * Calcula el código hash para el videojuego.
     *
     * @return el código hash del videojuego
     */
    @Override
    public int hashCode() {
        return Objects.hash(compania, nombre, nota);
    }

    /**
     * Compara este videojuego con otro objeto para determinar si son iguales.
     *
     * @param obj el objeto a comparar
     * @return true si el objeto es igual a este videojuego, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Videojuego other = (Videojuego) obj;
        return Objects.equals(compania, other.compania) && 
               Objects.equals(nombre, other.nombre) && 
               nota == other.nota; 
    }
}



