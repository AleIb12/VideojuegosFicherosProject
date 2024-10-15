package interfaz;

import entidad.Videojuego;
import modelo.persistencia.DaoVideojuegoFichero;
import persistencia.BorrarJuegos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class InterfazTest {

    @Mock
    private DaoVideojuegoFichero daoVideojuegoFicheroMock;

    @Mock
    private BorrarJuegos borrarJuegosMock;

    @InjectMocks
    private Interfaz interfaz; 

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testAgregarVideojuego() {
        System.setIn(new ByteArrayInputStream("Super Mario\nNintendo\n90\n".getBytes()));
        try {
			doNothing().when(daoVideojuegoFicheroMock).registrar(any(Videojuego.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

        Interfaz.agregarVideojuego();

        try {
			verify(daoVideojuegoFicheroMock).registrar(argThat(videojuego ->
			        "Super Mario".equals(videojuego.getNombreVideojuego()) &&
			        "Nintendo".equals(videojuego.getCompañia()) &&
			        videojuego.getNota() == 90));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testBorrarJuegos() {
        System.setIn(new ByteArrayInputStream("Super Mario\n".getBytes()));
        doNothing().when(borrarJuegosMock).borrarVideojuego(anyString());

        Interfaz.borrarJuegos();

        verify(borrarJuegosMock).borrarVideojuego("Super Mario");
    }

    @Test
    public void testListarVideojuegos() {
        System.setIn(new ByteArrayInputStream("2\n0\n".getBytes()));
        Interfaz.listarVideojuegos();

        try {
			verify(daoVideojuegoFicheroMock).listarVideojuegos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
