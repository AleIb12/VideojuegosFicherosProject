package objetovideojuego;

import java.util.Objects;

public class Videojuego {
	private String NombreVideojuego;
	private String Compañia;
	private String Nota;
	public Videojuego(String nombreVideojuego, String compañia, String nota) {
		super();
		NombreVideojuego = nombreVideojuego;
		Compañia = compañia;
		Nota = nota;
	}
	public String getNombreVideojuego() {
		return NombreVideojuego;
	}
	public void setNombreVideojuego(String nombreVideojuego) {
		NombreVideojuego = nombreVideojuego;
	}
	public String getCompañia() {
		return Compañia;
	}
	public void setCompañia(String compañia) {
		Compañia = compañia;
	}
	public String getNota() {
		return Nota;
	}
	public void setNota(String nota) {
		Nota = nota;
	}
	@Override
	public String toString() {
		return "Videojuego [NombreVideojuego=" + NombreVideojuego + ", Compañia=" + Compañia + ", Nota=" + Nota + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(Compañia, NombreVideojuego, Nota);
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
		return Objects.equals(Compañia, other.Compañia) && Objects.equals(NombreVideojuego, other.NombreVideojuego)
				&& Objects.equals(Nota, other.Nota);
	}
	
}
