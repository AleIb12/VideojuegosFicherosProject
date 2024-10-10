package entidad;

import java.util.Objects;

public class Videojuego {
	private String nombre;
	private String compania;
	private int nota;
	public Videojuego(String nombreVideojuego, String compañia, String nota) {
		super();
		nombre = nombreVideojuego;
		compania = compañia;
		nota = nota;
	}
	public String getNombreVideojuego() {
		return nombre;
	}
	public void setNombreVideojuego(String nombreVideojuego) {
		nombre = nombreVideojuego;
	}
	public String getCompañia() {
		return compania;
	}
	public void setCompañia(String compañia) {
		compania = compañia;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(String nota) {
		nota = nota;
	}
	@Override
	public String toString() {
		return "Videojuego [NombreVideojuego=" + nombre + ", Compañia=" + compania + ", Nota=" + nota + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(compania, nombre, nota);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videojuego other = (Videojuego) obj;
		return Objects.equals(compania, other.compania) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(nota, other.nota);
	}
	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> main
