# 📚 Biblioteca Muskiz

Sistema de gestión de biblioteca desarrollado en Java con arquitectura modular orientada a consola.

El proyecto implementa gestión completa de biblioteca con usuarios, libros, autores, préstamos y sistema de cuentas, usando programación orientada a objetos y persistencia en base de datos o almacenamiento interno según configuración del sistema.

---

# 🚀 Características principales

- Gestión de autores.
- Gestión de libros.
- Gestión de usuarios y cuentas.
- Sistema de login.
- Gestión de préstamos y devoluciones.
- Control de penalizaciones.
- Menús interactivos por consola.
- Sistema de estadísticas.
- Validaciones de entrada robustas.
- Arquitectura modular por servicios.
- Uso de POO (herencia, composición y encapsulación).
- Preparado para ampliación a GUI o API.

---

# 🧠 Tecnologías utilizadas

| Tecnología | Uso |
|------------|-----|
| Java | Lenguaje principal |
| MySQL Connector/J | Conexión a base de datos |
| VS Code | Entorno de desarrollo |
| POO | Base del diseño |

---

# 📁 Estructura del proyecto
BibliotecaMuskiz/
│
├── lib/
│ └── mysql-connector-j-8.2.0.jar
│
├── src/
│ └── Classes/
│ ├── Main.java
│
│ ├── menu/
│ │ ├── MenuPrincipal.java
│ │ ├── MenuAutores.java
│ │ ├── MenuLibros.java
│ │ ├── MenuUsuarios.java
│ │ └── MenuPrestamos.java
│
│ ├── model/
│ │ ├── Persona.java
│ │ ├── Autor.java
│ │ ├── Libro.java
│ │ ├── Usuario.java
│ │ ├── Prestamo.java
│ │ ├── Ejemplar.java
│ │ └── Penalizacion.java
│
│ ├── service/
│ │ ├── LoginService.java
│ │ ├── ServiceAutores.java
│ │ ├── ServiceLibros.java
│ │ ├── ServicePrestamos.java
│ │ ├── ServiceCuenta.java
│ │ └── ServiceUsuarios.java
│
│ ├── utils/
│ │ ├── InputUtils.java
│ │ ├── ColoresUtils.java
│ │ ├── TitlesUtils.java
│ │ ├── UtilidadesAutores.java
│ │ └── UtilidadesLibros.java
│
│ └── setup/
│ └── Inicializaciones.java
│
└── README.md

---

# 🏗️ Arquitectura del proyecto

## 1. model/
Contiene las entidades del sistema:
- Autor
- Libro
- Usuario
- Cuenta
- Préstamo
- Ejemplar
- Penalización

Relaciones principales:
- Autor → Libro
- Usuario → Cuenta → Login
- Usuario → Préstamos

---

## 2. service/
Contiene toda la lógica de negocio.

Servicios principales:
- LoginService → autenticación de usuarios
- ServiceCuenta → gestión de cuentas
- ServiceUsuarios → gestión de usuarios
- ServicePrestamos → gestión de préstamos
- ServiceLibros → gestión de libros
- ServiceAutores → gestión de autores

Aquí se ejecutan las reglas del sistema.

---

## 3. menu/
Capa de interfaz por consola.

Responsabilidad:
- Mostrar opciones
- Recoger entradas
- Redirigir a servicios

Flujo:
Main → MenuPrincipal → Menús específicos

---

## 4. utils/
Utilidades globales:
- Entrada de datos segura
- Formato de consola
- Títulos
- Funciones auxiliares

---

# 🔐 Sistema de login

El sistema incluye autenticación:

- LoginService valida credenciales
- Control de acceso por usuario
- Posible extensión a roles

---

# 📊 Funcionalidades del sistema

## Autores
- CRUD completo
- Estadísticas

## Libros
- Gestión completa
- Control de existencias

## Usuarios
- Alta / baja
- Gestión de cuenta

## Préstamos
- Crear préstamo
- Devolución
- Penalizaciones automáticas

---

# 📈 Estadísticas

- Libros por autor
- Usuarios activos
- Préstamos activos
- Datos de biblioteca general

---

# 🔍 Validaciones

- Datos obligatorios
- Fechas coherentes
- Control de préstamos duplicados
- Validación de usuario logueado
- Entradas seguras

---

# 🧱 Diseño del proyecto

✔ Arquitectura modular  
✔ Separación por servicios  
✔ POO aplicada correctamente  
✔ Sistema escalable  
✔ Código mantenible  

---

# ⚙️ Requisitos

- Java 17+
- MySQL (opcional según configuración)
- VS Code
- MySQL Connector/J

---

# ▶️ Ejecución

1. Abrir proyecto en VS Code
2. Ejecutar:
```text
Main.java