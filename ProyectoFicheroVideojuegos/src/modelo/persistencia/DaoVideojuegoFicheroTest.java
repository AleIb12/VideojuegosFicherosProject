package modelo.persistencia;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.File;

import modelo.persistencia.DaoVideojuegoFichero;
import entidad.Videojuego;

public class DaoVideojuegoFicheroTest {

    private DaoVideojuegoFichero dao;
    private final String NOMBRE_ARCHIVO_TEMPORAL = "videojuegos_test.txt";

    @Before
    public void setUp() {
        dao = new DaoVideojuegoFichero();
       
        dao.NOMBRE_COMPANIA_NOTA = NOMBRE_ARCHIVO_TEMPORAL;
    }

    @After
    public void tearDown() {
   
        File file = new File(NOMBRE_ARCHIVO_TEMPORAL);
        file.delete();
    }

    @Test
    public void testListarVideojuegos() throws Exception {
      
        dao.registrar(new Videojuego("Mario Bros", "Nintendo", 9));
        dao.registrar(new Videojuego("Zelda", "Nintendo", 10));

       
        dao.listarVideojuegos(); 
    }

  
}