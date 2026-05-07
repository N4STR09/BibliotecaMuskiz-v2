# 📚 Biblioteca Muskiz

Sistema de gestión de biblioteca desarrollado en Java orientado a consola.

El proyecto implementa una arquitectura sencilla pero bastante bien separada por responsabilidades, utilizando programación orientada a objetos, persistencia en JSON mediante Gson y una estructura modular basada en servicios, menús, modelos y utilidades.

---

# 🚀 Características principales

* Gestión completa de autores.
* Gestión completa de libros.
* Persistencia automática en archivos JSON.
* Menús interactivos en consola.
* Sistema de estadísticas.
* Arquitectura modular.
* Uso de herencia y composición.
* Adaptador personalizado para `LocalDate`.
* Colores ANSI en consola.
* Inicialización automática de datos.
* Validaciones de entrada.
* Proyecto preparado para Visual Studio Code.

---

# 🧠 Tecnologías utilizadas

| Tecnología                       | Uso                                            |
| -------------------------------- | ---------------------------------------------- |
| Java                             | Lenguaje principal                             |
| Gson                             | Serialización y deserialización JSON           |
| MySQL Connector/J                | Dependencia incluida para futuras ampliaciones |
| VS Code                          | Entorno de desarrollo                          |
| Programación Orientada a Objetos | Base del proyecto                              |

---

# 📁 Estructura del proyecto

```text
BibliotecaMuskiz/
│
├── bin/                         # Clases compiladas
├── lib/                         # Dependencias externas
│   ├── gson-2.10.1.jar
│   └── mysql-connector-j-8.2.0.jar
│
├── src/
│   └── Classes/
│       ├── Main.java
│       │
│       ├── menu/
│       │   ├── MenuPrincipal.java
│       │   ├── MenuAutores.java
│       │   └── MenuLibros.java
│       │
│       ├── model/
│       │   ├── Persona.java
│       │   ├── Autor.java
│       │   ├── Libro.java
│       │   ├── Usuario.java
│       │   ├── Prestamo.java
│       │   ├── Ejemplar.java
│       │   └── Penalizacion.java
│       │
│       ├── repository/
│       │   └── BibliotecaRepository.java
│       │
│       ├── service/
│       │   ├── ServiceAutores.java
│       │   └── ServiceLibros.java
│       │
│       ├── setup/
│       │   ├── Inicializaciones.java
│       │   └── LocalDateAdapter.java
│       │
│       └── utils/
│           ├── InputUtils.java
│           ├── ColoresUtils.java
│           ├── TitlesUtils.java
│           ├── UtilidadesAutores.java
│           └── UtilidadesLibros.java
│
├── .vscode/
├── .gitignore
└── README.md
```

---

# 🏗️ Arquitectura del proyecto

El proyecto está dividido por capas lógicas.

## 1. `model/`

Contiene todas las entidades del sistema.

Aquí se define la lógica estructural de los objetos:

* Autores
* Libros
* Usuarios
* Préstamos
* Penalizaciones
* Ejemplares

La relación entre clases está bastante bien separada mediante composición y herencia.

Ejemplo:

* `Autor` hereda de `Persona`
* `Usuario` hereda de `Persona`
* `Libro` contiene un objeto `Autor`
* `Prestamo` contiene un `Usuario` y un `Ejemplar`

---

## 2. `service/`

Contiene la lógica funcional.

Aquí es donde realmente se ejecutan las operaciones:

* Añadir autores
* Eliminar autores
* Mostrar detalles
* Generar estadísticas
* Añadir libros
* Gestionar datos

Los servicios son los responsables de manipular las listas de datos.

---

## 3. `menu/`

Gestiona toda la navegación del programa.

Los menús son completamente interactivos y funcionan mediante consola.

El flujo principal es:

```text
Main
 └── MenuPrincipal
      ├── MenuAutores
      └── MenuLibros
```

---

## 4. `repository/`

Gestiona la persistencia de datos.

La clase `BibliotecaRepository`:

* Guarda datos en JSON.
* Carga datos desde JSON.
* Usa Gson.
* Implementa serialización de `LocalDate`.

Archivos utilizados:

```text
/data/autores.json
/data/libros.json
```

---

## 5. `utils/`

Contiene utilidades reutilizables.

### `InputUtils`

Centraliza:

* Lectura de enteros.
* Lectura de fechas.
* Lectura de booleanos.
* Validaciones.
* Limpieza de pantalla.
* Pausas.

### `ColoresUtils`

Implementa códigos ANSI para mejorar la visualización en consola.

### `TitlesUtils`

Centraliza los títulos y cabeceras visuales.

### `UtilidadesAutores`

Implementa cálculos estadísticos relacionados con autores.

### `UtilidadesLibros`

Implementa cálculos estadísticos relacionados con libros.

---

# 🧬 Modelado de clases

## 👤 Persona

Clase abstracta base.

Campos principales:

```java
nombre
fechaNacimiento
defuncion
fechaFallecimiento
edad
```

Incluye cálculo automático de edad usando:

```java
Period.between()
```

La edad se calcula automáticamente:

* Con fecha actual si la persona vive.
* Con fecha de fallecimiento si está muerta.

---

## ✍️ Autor

Hereda de `Persona`.

Campos destacados:

```java
idAutor
nacionalidad
biografia
foto
generoLiterario
premios
obrasDestacadas
librosEscritos
```

Características importantes:

* Relación autor-libro.
* Gestión de libros escritos.
* Sobrescritura de `equals()` y `hashCode()`.

---

## 📖 Libro

Entidad principal de la biblioteca.

Campos destacados:

```java
idLibro
existencias
numeroPaginas
titulo
autor
genero
isbn
idioma
formato
categoria
disponibilidad
```

El libro contiene un objeto `Autor`, no solo un ID.

Eso simplifica muchísimo el acceso a datos relacionados.

---

## 👥 Usuario

Hereda de `Persona`.

Incluye:

```java
activo
dni
numeroSeguridadSocial
penalizacion
```

Tiene lógica para desactivar usuarios fallecidos.

---

## 📕 Ejemplar

Representa una copia física concreta de un libro.

---

## 🔁 Prestamo

Relaciona:

* Usuario
* Ejemplar
* Fecha de préstamo
* Fecha de devolución

---

## ⚠️ Penalizacion

Gestiona:

* Multas
* Tiempo de penalización
* Fechas
* Penalizaciones activas

---

# 💾 Persistencia de datos

La persistencia se realiza usando Gson.

## Configuración Gson

```java
private static final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();
```

Características:

* JSON legible.
* Compatibilidad con `LocalDate`.
* Serialización automática.

---

# 📊 Sistema de estadísticas

El proyecto incluye bastantes estadísticas interesantes.

## Estadísticas de autores

* Autor con más libros.
* Autor con menos libros.
* Autor con libro más largo.
* Autor con libro más corto.
* Autor más viejo.
* Autor más joven.
* Edad media.
* Total de autores.

---

## Estadísticas de libros

* Media de páginas.
* Libro más largo.
* Libro más corto.
* Media de existencias.
* Libro con más existencias.
* Libro con menos existencias.
* Porcentaje disponibles.
* Libros por género.
* Total de libros.

---

# 🔍 Validaciones implementadas

El proyecto tiene bastantes validaciones útiles.

Ejemplos:

* Evitar fechas de fallecimiento anteriores al nacimiento.
* Validación de rangos en menús.
* Validación de enteros.
* Validación de booleanos.
* Prevención de listas nulas.
* Prevención de listas vacías.

---

# 🎨 Interfaz de consola

El proyecto utiliza códigos ANSI para mejorar la experiencia visual.

Incluye:

* Colores.
* Negritas.
* Subrayados.
* Títulos personalizados.

Esto hace que la navegación sea bastante más clara comparado con una consola plana normal.

---

# ▶️ Ejecución del proyecto

## Requisitos

* Java 17 o superior recomendado.
* Visual Studio Code.
* Extension Pack for Java.

---

## Abrir el proyecto

Abrir la carpeta:

```text
BibliotecaMuskiz
```

---

## Ejecutar desde VS Code

Ejecutar:

```text
src/Classes/Main.java
```

VS Code detectará automáticamente:

* Source path.
* Dependencias.
* Librerías.

---

# ⚙️ Dependencias

## Gson

Archivo:

```text
lib/gson-2.10.1.jar
```

Uso:

* Persistencia JSON.
* Serialización.
* Deserialización.

---

## MySQL Connector/J

Archivo:

```text
lib/mysql-connector-j-8.2.0.jar
```

Actualmente no se utiliza directamente en el proyecto.

Pero está preparado para futuras migraciones desde JSON hacia MySQL.

---

# 🧪 Flujo de ejecución

## Inicio del programa

```java
List<Autor> autores = BibliotecaRepository.cargarAutores();
List<Libro> libros = BibliotecaRepository.cargarLibros();
```

Si no existen datos:

```java
Inicializaciones.inicializar(autores, libros);
```

Después:

```java
MenuPrincipal.mostrar(sc, autores, libros);
```

---

# 📌 Diseño destacable

## ✔️ Separación de responsabilidades

Cada paquete tiene una responsabilidad muy concreta.

Eso hace que:

* El mantenimiento sea más fácil.
* El código sea más escalable.
* La lógica esté más limpia.

---

## ✔️ Uso correcto de herencia

`Persona` abstrae lógica común.

Evita duplicación en:

* Autor
* Usuario

---

## ✔️ Uso de composición

El proyecto utiliza composición en lugar de abusar de IDs sueltos.

Ejemplo:

```java
private Autor autor;
```

En vez de:

```java
private int idAutor;
```

Eso mejora muchísimo la legibilidad.

---

## ✔️ Persistencia desacoplada

Toda la lógica JSON está aislada en `BibliotecaRepository`.

El resto del sistema no necesita saber cómo se guardan los datos.

---

# 🧱 Conceptos de Java utilizados

Este proyecto toca bastantes conceptos importantes:

* Clases abstractas.
* Herencia.
* Composición.
* Encapsulación.
* Polimorfismo.
* Listas.
* Mapas.
* Streams.
* Serialización JSON.
* Manejo de fechas.
* Programación modular.
* Validaciones.
* Arquitectura por capas.
* Sobrescritura de métodos.
* Gestión de excepciones.

---

# 👨‍💻 Autor

Grupo 5 - DAM3 25-26

---

# 📄 Licencia

Proyecto educativo.