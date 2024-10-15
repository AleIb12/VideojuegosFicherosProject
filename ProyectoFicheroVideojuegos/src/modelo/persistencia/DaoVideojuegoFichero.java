package modelo.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entidad.Videojuego;

/**
 * Clase que maneja la persistencia de datos de videojuegos en un archivo.
 * 
 * <p>Esta clase proporciona métodos para listar y registrar videojuegos en un archivo de texto.
 * Los videojuegos se almacenan en un formato específico y se manipulan a través de la clase
 * {@link Videojuego}. La clase gestiona la lectura y escritura de datos a un archivo
 * ubicado en la ruta especificada por la constante {@link #NOMBRE_COMPANIA_NOTA}.</p>
 */
public class DaoVideojuegoFichero {
    
    static String NOMBRE_COMPANIA_NOTA = "src/resources/videojuegos.txt";

    /**
     * Lista todos los videojuegos almacenados en el archivo.
     * 
     * <p>Este método lee el archivo de videojuegos línea por línea, separa cada línea
     * en nombre, compañía y nota utilizando un carácter de separación ("_"). Luego,
     * crea objetos {@link Videojuego} a partir de estos datos y los almacena en una lista.
     * Finalmente, imprime en la consola los videojuegos leídos.</p>
     * 
     * @throws IOException Si ocurre un error durante la lectura del archivo.
     * <p>Se lanza si hay un problema al abrir el archivo o al leer su contenido.</p>
     */
    public void listarVideojuegos() throws IOException {
        List<Videojuego> videojuegos = new ArrayList<>();

        try (FileReader fr = new FileReader(NOMBRE_COMPANIA_NOTA);
             BufferedReader br = new BufferedReader(fr)) {
             
            String cadena;
            while ((cadena = br.readLine()) != null) {
                String[] cadenaPartida = cadena.split("_");

                if (cadenaPartida.length >= 3) {
                    try {
                        String nombreVideojuego = cadenaPartida[0];
                        String companiaVideojuego = cadenaPartida[1];
                        String notaVideojuego = cadenaPartida[2];

                        Videojuego v = new Videojuego(nombreVideojuego, companiaVideojuego, Integer.parseInt(notaVideojuego));
                        videojuegos.add(v);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir la nota a número en la línea: " + cadena);
                    }
                } else {
                    System.out.println("Formato incorrecto en la línea: " + cadena);
                }
            }
        }

        for (Videojuego videojuego : videojuegos) {
            System.out.println(videojuego.toString());
        }
    }

    /**
     * Registra un nuevo videojuego en el archivo.
     * 
     * <p>Este método agrega un nuevo videojuego al archivo especificado. Si el archivo no existe,
     * se crea uno nuevo. Los datos del videojuego se guardan en el formato "nombre_compañía_nota".</p>
     * 
     * @param v El objeto {@link Videojuego} que se desea registrar.
     * <p>Este objeto contiene el nombre, la compañía y la nota del videojuego.</p>
     * 
     * @throws IOException Si ocurre un error durante la escritura en el archivo.
     * <p>Se lanza si hay un problema al abrir el archivo para escribir o al crear uno nuevo.</p>
     */
    public void registrar(Videojuego v) throws Exception {
        File f = new File(NOMBRE_COMPANIA_NOTA);
         
        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new IOException("No se pudo crear el archivo: " + NOMBRE_COMPANIA_NOTA);
            }
        }

        try (FileWriter fw = new FileWriter(f, true); 
             BufferedWriter bw = new BufferedWriter(fw)) {
             
            bw.write(v.getNombreVideojuego() + "_" + v.getCompañia() + "_" + v.getNota());
            bw.newLine();
        }
    }


}
