package VideojuegoApp;

public class Videojuego {
    private String nombre;
    private String compania;
    private int nota;

    /**
     * Constructor para inicializar un objeto Videojuego con su nombre, compañía y nota.
     * 
     * @param nombre Nombre del videojuego.
     * @param compania Nombre de la compañía que desarrolló el videojuego.
     * @param nota Calificación del videojuego (0-100).
     */
    public Videojuego(String nombre, String compania, int nota) {
        this.nombre = nombre;
        this.compania = compania;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCompania() {
        return compania;
    }

    public int getNota() {
        return nota;
    }
}
