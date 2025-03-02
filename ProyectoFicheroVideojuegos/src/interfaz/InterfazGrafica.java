package interfaz;

import entidad.Videojuego;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.persistencia.DaoVideojuegoFichero;
import persistencia.BorrarJuegos;

public class InterfazGrafica extends JFrame {
    private DaoVideojuegoFichero dao;
    private BorrarJuegos borrador;
    private JTable tablaJuegos;
    private DefaultTableModel modeloTabla;

    public InterfazGrafica() {
        dao = new DaoVideojuegoFichero();
        borrador = new BorrarJuegos();
        
        setTitle("Gestión de Videojuegos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        
        // Tabla de juegos
        String[] columnas = {"Nombre", "Compañía", "Nota"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaJuegos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaJuegos);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel botonesPanel = new JPanel();
        JButton btnAgregar = new JButton("Agregar Juego");
        JButton btnBorrar = new JButton("Borrar Juego");
        JButton btnActualizar = new JButton("Actualizar Lista");
        
        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnBorrar);
        botonesPanel.add(btnActualizar);
        panel.add(botonesPanel, BorderLayout.SOUTH);
        
        // Eventos de botones
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnBorrar.addActionListener(e -> borrarJuegoSeleccionado());
        btnActualizar.addActionListener(e -> actualizarTabla());
        
        add(panel);
        actualizarTabla();
    }
    
    private void mostrarDialogoAgregar() {
        JTextField nombreField = new JTextField();
        JTextField companiaField = new JTextField();
        JTextField notaField = new JTextField();
        
        Object[] campos = {
            "Nombre:", nombreField,
            "Compañía:", companiaField,
            "Nota:", notaField
        };
        
        int result = JOptionPane.showConfirmDialog(this, campos, 
                "Agregar Nuevo Juego", JOptionPane.OK_CANCEL_OPTION);
                
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText();
                String compania = companiaField.getText();
                int nota = Integer.parseInt(notaField.getText());
                
                Videojuego juego = new Videojuego(nombre, compania, nota);
                dao.registrar(juego);
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "Juego agregado con éxito");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "La nota debe ser un número válido", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al agregar el juego: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void borrarJuegoSeleccionado() {
        int filaSeleccionada = tablaJuegos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombre = (String) tablaJuegos.getValueAt(filaSeleccionada, 0);
            int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que quieres borrar " + nombre + "?",
                "Confirmar Borrado",
                JOptionPane.YES_NO_OPTION);
                
            if (confirmar == JOptionPane.YES_OPTION) {
                borrador.borrarVideojuego(nombre);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecciona un juego para borrar",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        try {
            dao = new DaoVideojuegoFichero();
            String contenido = leerContenidoArchivo();
            String[] lineas = contenido.split("\n");
            
            for (String linea : lineas) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("_");
                    if (datos.length >= 3) {
                        modeloTabla.addRow(datos);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar los juegos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String leerContenidoArchivo() {
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("src/resources/videojuegos.txt");
            return new String(java.nio.file.Files.readAllBytes(path));
        } catch (IOException e) {
            return "";
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica frame = new InterfazGrafica();
            frame.setVisible(true);
        });
    }
}