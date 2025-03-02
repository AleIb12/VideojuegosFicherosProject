# Video Games Management System

This is a Java application that allows users to manage a collection of video games. The system provides functionality to add, list, and delete video games, storing the information in text files.

## Features

- Add new video games with name, company, and rating
- List all stored video games
- Delete video games by name
- Data persistence using text files
- Input validation for all fields
- Both console and graphical user interfaces

## Project Structure

```
ProyectoFicheroVideojuegos/
├── src/
│   ├── entidad/
│   │   └── Videojuego.java         # Video game entity class
│   ├── interfaz/
│   │   ├── Interfaz.java           # Console interface
│   │   ├── InterfazGrafica.java    # Graphical interface
│   │   └── InterfazTest.java       # Interface tests
│   ├── modelo/
│   │   └── persistencia/
│   │       ├── DaoVideojuegoFichero.java    # Data access object
│   │       └── DaoVideojuegoFicheroTest.java # DAO tests
│   └── persistencia/
│       ├── BorrarJuegos.java       # Delete functionality
│       └── BorrarJuegosTest.java   # Delete tests
└── resources/
    └── videojuegos.txt             # Data storage file
```

## Requirements

- Java JDK 21 or higher
- JUnit 5 for running tests
- Mockito for testing
- Swing (included in JDK) for graphical interface

## Usage

You can run the application in two modes:

### Console Interface
Run the `Interfaz` class and choose from these options:
- 1: Add a new video game
- 2: List all video games
- 3: Delete a video game
- 0: Exit the application

### Graphical Interface
Run the `InterfazGrafica` class to open the GUI application which provides:
- A table view of all games
- Buttons to add, delete, and refresh the game list
- User-friendly forms for data input

## Validation Rules

- Video game names must be at least 3 characters long
- Company names must be at least 5 characters long
- Ratings must be between 0 and 100 (no decimals)

## Running the Application

To run the console interface:
```bash
java -cp bin interfaz.Interfaz
```

To run the graphical interface:
```bash
java -cp bin interfaz.InterfazGrafica
```

