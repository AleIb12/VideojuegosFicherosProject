package interfaz;

import entidad.Videojuego;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import modelo.persistencia.DaoVideojuegoFichero;
import persistencia.BorrarJuegos;

public class InterfazGrafica extends JFrame {
    private DaoVideojuegoFichero dao;
    private BorrarJuegos borrador;
    private JTable tablaJuegos;
    private DefaultTableModel modeloTabla;
    private Color colorPrincipal = new Color(70, 130, 180); // Steel Blue
    private Color colorSecundario = new Color(135, 206, 235); // Sky Blue
    private Color colorFondo = new Color(240, 248, 255); // Alice Blue
    private Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);
    private Font fuenteNormal = new Font("Arial", Font.PLAIN, 14);

    public InterfazGrafica() {
        dao = new DaoVideojuegoFichero();
        borrador = new BorrarJuegos();
        
        configurarVentana();
        inicializarComponentes();
    }
    
    private void configurarVentana() {
        setTitle("🎮 Gestión de Videojuegos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(colorFondo);
        setLayout(new BorderLayout(10, 10));
    }
    
    private void inicializarComponentes() {
        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(colorPrincipal);
        JLabel titulo = new JLabel("Biblioteca de Videojuegos 🎮");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(colorFondo);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Tabla de juegos con estilo mejorado
        inicializarTabla();
        JScrollPane scrollPane = new JScrollPane(tablaJuegos);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(5, 5, 5, 5),
            new LineBorder(colorPrincipal, 2, true)
        ));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones con estilo mejorado
        JPanel botonesPanel = crearPanelBotones();
        panelPrincipal.add(botonesPanel, BorderLayout.SOUTH);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        // Panel de estadísticas
        JPanel panelEstadisticas = crearPanelEstadisticas();
        add(panelEstadisticas, BorderLayout.EAST);
        
        actualizarTabla();
    }
    
    private void inicializarTabla() {
        String[] columnas = {"Nombre", "Compañía", "Nota"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaJuegos = new JTable(modeloTabla);
        tablaJuegos.setFont(fuenteNormal);
        tablaJuegos.setRowHeight(25);
        tablaJuegos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaJuegos.setGridColor(colorSecundario);
        
        // Personalizar el encabezado
        JTableHeader header = tablaJuegos.getTableHeader();
        header.setBackground(colorPrincipal);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Renderer personalizado para las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaJuegos.setDefaultRenderer(Object.class, centerRenderer);
    }
    
    private JPanel crearPanelBotones() {
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botonesPanel.setBackground(colorFondo);
        
        JButton btnAgregar = crearBotonEstilizado("➕ Agregar Juego", "Agregar un nuevo juego");
        JButton btnBorrar = crearBotonEstilizado("❌ Borrar Juego", "Borrar el juego seleccionado");
        JButton btnActualizar = crearBotonEstilizado("🔄 Actualizar", "Actualizar la lista de juegos");
        
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnBorrar.addActionListener(e -> borrarJuegoSeleccionado());
        btnActualizar.addActionListener(e -> actualizarTabla());
        
        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnBorrar);
        botonesPanel.add(btnActualizar);
        
        return botonesPanel;
    }
    
    private JButton crearBotonEstilizado(String texto, String toolTip) {
        JButton boton = new JButton(texto);
        boton.setFont(fuenteNormal);
        boton.setBackground(colorPrincipal);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(new RoundedBorder(10));
        boton.setToolTipText(toolTip);
        
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorSecundario);
            }
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorPrincipal);
            }
        });
        
        return boton;
    }
    
    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(colorFondo);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(5, 5, 5, 5),
            new TitledBorder(new LineBorder(colorPrincipal, 2, true), "Estadísticas 📊")
        ));
        panel.setPreferredSize(new Dimension(200, 0));
        
        // Aquí añadiremos las estadísticas
        JLabel lblTotal = new JLabel("Total de juegos: 0");
        JLabel lblPromedio = new JLabel("Nota promedio: 0.0");
        JLabel lblMejor = new JLabel("Mejor juego: -");
        
        lblTotal.setFont(fuenteNormal);
        lblPromedio.setFont(fuenteNormal);
        lblMejor.setFont(fuenteNormal);
        
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblTotal);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblPromedio);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblMejor);
        
        return panel;
    }
    
    // Clase para crear bordes redondeados
    private class RoundedBorder extends AbstractBorder {
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(colorPrincipal);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    private void mostrarDialogoAgregar() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(colorFondo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nombreField = new JTextField(20);
        JTextField companiaField = new JTextField(20);
        JTextField notaField = new JTextField(20);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("🎮 Nombre:"), gbc);
        gbc.gridx = 1;
        panel.add(nombreField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("🏢 Compañía:"), gbc);
        gbc.gridx = 1;
        panel.add(companiaField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("⭐ Nota:"), gbc);
        gbc.gridx = 1;
        panel.add(notaField, gbc);
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
                "Agregar Nuevo Juego", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
                
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText();
                String compania = companiaField.getText();
                int nota = Integer.parseInt(notaField.getText());
                
                Videojuego juego = new Videojuego(nombre, compania, nota);
                dao.registrar(juego);
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "✅ ¡Juego agregado con éxito!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "❌ La nota debe ser un número válido", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, 
                    "❌ " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "❌ Error al agregar el juego: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void borrarJuegoSeleccionado() {
        int filaSeleccionada = tablaJuegos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            try {
                String nombre = (String) tablaJuegos.getValueAt(filaSeleccionada, 0);
                int confirmar = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que quieres borrar " + nombre + "?",
                    "Confirmar Borrado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                    
                if (confirmar == JOptionPane.YES_OPTION) {
                    borrador.borrarVideojuego(nombre);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(this,
                        "✅ El juego se ha borrado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "❌ Error al borrar el juego: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "❗ Por favor, selecciona un juego para borrar",
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
            String rutaBase = System.getProperty("user.dir");
            java.nio.file.Path path = java.nio.file.Paths.get(rutaBase, "src", "resources", "videojuegos.txt");
            if (!java.nio.file.Files.exists(path)) {
                // Si no encuentra el archivo en la ruta src/resources, intentar directamente en resources
                path = java.nio.file.Paths.get(rutaBase, "resources", "videojuegos.txt");
                if (!java.nio.file.Files.exists(path)) {
                    // Crear el archivo si no existe
                    java.nio.file.Files.createDirectories(path.getParent());
                    java.nio.file.Files.createFile(path);
                }
            }
            return new String(java.nio.file.Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
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