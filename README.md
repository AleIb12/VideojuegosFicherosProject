# 🎮 Video Games Management System

Una aplicación Java moderna para gestionar tu colección de videojuegos con una interfaz gráfica mejorada y almacenamiento en archivos de texto.

## ✨ Características

- 📝 Añadir nuevos videojuegos con nombre, compañía y nota
- 📋 Lista completa de videojuegos en una tabla moderna
- 🗑️ Borrar videojuegos de forma segura
- 💾 Persistencia de datos usando archivos de texto
- ✅ Validación de entrada para todos los campos
- 📊 Panel de estadísticas con información relevante
- 🎨 Interfaz gráfica moderna con:
  - Colores armoniosos y profesionales
  - Botones con efectos hover
  - Bordes redondeados
  - Iconos emoji para mejor usabilidad
  - Diseño responsive y amigable

## 🏗️ Estructura del Proyecto

```
ProyectoFicheroVideojuegos/
├── src/
│   ├── entidad/
│   │   └── Videojuego.java         # Clase entidad de videojuego
│   ├── interfaz/
│   │   ├── Interfaz.java           # Interfaz de consola
│   │   ├── InterfazGrafica.java    # Interfaz gráfica moderna
│   │   └── InterfazTest.java       # Pruebas de interfaz
│   ├── modelo/
│   │   └── persistencia/
│   │       ├── DaoVideojuegoFichero.java    # Acceso a datos
│   │       └── DaoVideojuegoFicheroTest.java # Pruebas de DAO
│   └── persistencia/
│       ├── BorrarJuegos.java       # Funcionalidad de borrado
│       └── BorrarJuegosTest.java   # Pruebas de borrado
└── resources/
    └── videojuegos.txt             # Archivo de almacenamiento
```

## 📋 Requisitos

- ☕ Java JDK 21 o superior
- 🧪 JUnit 5 para pruebas
- 🔍 Mockito para pruebas
- 🎨 Swing (incluido en JDK) para la interfaz gráfica

## 🚀 Uso

La aplicación se puede ejecutar en dos modos:

### 💻 Interfaz de Consola
Ejecuta la clase `Interfaz` y elige entre estas opciones:
- 1️⃣ Agregar un nuevo videojuego
- 2️⃣ Listar todos los videojuegos
- 3️⃣ Borrar un videojuego
- 0️⃣ Salir de la aplicación

### 🖥️ Interfaz Gráfica
Ejecuta la clase `InterfazGrafica` para abrir la aplicación GUI que ofrece:
- 📊 Vista en tabla de todos los juegos
- ➕ Botón para agregar nuevos juegos
- ❌ Botón para borrar juegos
- 🔄 Botón para actualizar la lista
- 📈 Panel de estadísticas
- 🎨 Diseño moderno y profesional

## ✅ Reglas de Validación

- 🎮 Nombres de videojuegos: mínimo 3 caracteres
- 🏢 Nombres de compañías: mínimo 5 caracteres
- ⭐ Notas: entre 0 y 100 (sin decimales)

## ▶️ Ejecutar la Aplicación

Para la interfaz de consola:
```bash
java -cp bin interfaz.Interfaz
```

Para la interfaz gráfica moderna:
```bash
java -cp bin interfaz.InterfazGrafica
```

## 🎨 Características de la Nueva Interfaz

- 🎯 Diseño intuitivo y fácil de usar
- 🌈 Esquema de colores profesional
- 💫 Efectos visuales en botones
- 📱 Diseño responsive
- 🛡️ Validación de datos en tiempo real
- 🔔 Mensajes de feedback claros
- 📊 Panel de estadísticas integrado

