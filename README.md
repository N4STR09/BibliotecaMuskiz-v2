# 📚 Biblioteca Muskiz

Sistema de gestión de biblioteca desarrollado en **Java (consola)** con persistencia en **MySQL**.

El proyecto simula una biblioteca real con usuarios, préstamos, control de ejemplares, roles de administrador y gestión de libros y autores.

---

# 🚀 Funcionalidades principales

## 👤 Usuarios
- Registro de usuarios
- Login con DNI y contraseña
- Sistema de roles (usuario / admin)
- Edición de datos personales
- Cambio de contraseña

## 📚 Libros
- Listado de libros disponibles
- Consulta de detalles de cada libro
- Relación con autores
- Control de existencias

## 📦 Préstamos
- Solicitud de préstamos
- Límite de 3 préstamos activos por usuario
- Listado de préstamos activos
- Devolución de ejemplares
- Gestión automática de disponibilidad

## 🧑‍💼 Administrador
- Gestión de usuarios
- Gestión de autores
- Gestión de libros
- Acceso a menú exclusivo de administración

---

# 🧠 Arquitectura del proyecto

El proyecto está organizado en capas:

## 📁 model
Clases principales del dominio:
- Usuario
- Libro
- Autor
- Ejemplar
- Prestamo
- Persona

## 📁 repository
Acceso a base de datos (JDBC):
- Conexión MySQL
- CRUD de entidades
- Consultas SQL

## 📁 service
Lógica de negocio:
- Gestión de préstamos
- Gestión de usuarios
- Login
- Lógica de biblioteca

## 📁 menu
Interfaz por consola:
- Menú principal
- Menú de admin
- Menú de préstamos
- Menú de cuenta

## 📁 utils
Herramientas auxiliares:
- InputUtils (validación de entradas)
- ColoresUtils (formato consola)
- TitlesUtils (títulos ASCII)

---

# 🗄️ Base de datos

## Tablas principales

- usuarios
- libros
- autores
- ejemplares
- prestamos
- penalizaciones

## Relaciones clave

- Un libro pertenece a un autor
- Un libro tiene múltiples ejemplares
- Un usuario puede tener varios préstamos
- Un préstamo está asociado a un ejemplar

---

# 🔐 Sistema de login

- Autenticación por DNI + contraseña
- Carga de usuario desde base de datos
- Control de acceso por rol (`admin` / usuario normal)

---

# ⚙️ Tecnologías utilizadas

- Java 17+
- JDBC
- MySQL
- Programación orientada a objetos (POO)
- Arquitectura por capas

---

# 📌 Reglas del sistema

- Máximo 3 préstamos activos por usuario
- No se pueden prestar ejemplares no disponibles
- Los préstamos se cierran al devolver el libro
- El campo `admin` se controla desde la base de datos (no desde la app)

---

# 🧩 Mejoras futuras

- Interfaz gráfica (JavaFX / Swing)
- Historial de préstamos
- Multa automática por retrasos
- Reserva de libros
- API REST

---

# 👨‍💻 Autor

Proyecto desarrollado como práctica de **Java + JDBC + MySQL**, simulando un sistema real de biblioteca, por el grupo 5 de DAM3 1ero curso 2025-2026.
