package src.Classes.setup;

import src.Classes.repository.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() {

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement()) {

            // =========================
            // AUTOR
            // =========================
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS autores (
                    id_autor INT(11) NOT NULL AUTO_INCREMENT,
                    nombre VARCHAR(150) NOT NULL,
                    nacionalidad VARCHAR(100),
                    fecha_nacimiento DATE,
                    defuncion TINYINT(1) DEFAULT 0,
                    fecha_fallecimiento DATE,
                    biografia TEXT,
                    foto VARCHAR(255),
                    genero_literario VARCHAR(100),
                    premios TEXT,
                    obras_destacadas TEXT,
                    PRIMARY KEY (id_autor)
                ) ENGINE=InnoDB;
            """);

            if (isEmpty(st, "autores")) {
                st.executeUpdate("""
                    INSERT INTO autores VALUES
                    (1,'Miguel de Cervantes','Española','1547-09-29',1,'1616-04-22','Escritor del Siglo de Oro','cervantes.jpg','Novela','Ninguno','Don Quijote'),
                    (2,'Gabriel García Márquez','Colombiana','1927-03-06',1,'2014-04-17','Realismo mágico','gabo.jpg','Novela','Nobel','Cien años de soledad'),
                    (3,'J.K. Rowling','Británica','1965-07-31',0,NULL,'Harry Potter','rowling.jpg','Fantasía','Ninguno','Harry Potter'),
                    (4,'Isabel Allende','Chilena','1942-08-02',0,NULL,'Escritora','allende.jpg','Novela','Ninguno','La casa de los espíritus'),
                    (5,'Mario Vargas Llosa','Peruana','1936-03-28',0,NULL,'Escritor','vargas.jpg','Novela','Nobel','La ciudad y los perros')
                """);
            }

            // =========================
            // LIBROS
            // =========================
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS libros (
                    id_libro INT(11) NOT NULL AUTO_INCREMENT,
                    numero_paginas INT(11),
                    titulo VARCHAR(255) NOT NULL,
                    id_autor INT(11) NOT NULL,
                    genero VARCHAR(100),
                    anio_publicacion INT(11),
                    editorial VARCHAR(150),
                    isbn VARCHAR(50),
                    idioma VARCHAR(50),
                    formato VARCHAR(50),
                    portada VARCHAR(255),
                    descripcion TEXT,
                    categoria VARCHAR(100),
                    disponibilidad TINYINT(1) DEFAULT 1,
                    existencias INT(11) NOT NULL DEFAULT 0,
                    PRIMARY KEY (id_libro),
                    FOREIGN KEY (id_autor) REFERENCES autores(id_autor)
                        ON DELETE CASCADE ON UPDATE CASCADE
                ) ENGINE=InnoDB;
            """);

            if (isEmpty(st, "libros")) {
                st.executeUpdate("""
                INSERT INTO libros (
                    id_libro, numero_paginas, titulo, id_autor, genero,
                    anio_publicacion, editorial, isbn, idioma, formato,
                    portada, descripcion, categoria, disponibilidad, existencias
                ) VALUES
                (1,863,'Don Quijote de la Mancha',1,'Novela',1605,'Francisco de Robles',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (2,350,'Novelas ejemplares',1,'Novela',1613,'Juan de la Cuesta',NULL,'Español','Físico',NULL,NULL,'Literatura',1,1),
                (3,471,'La Galatea',1,'Novela',1585,'Blas de Robles',NULL,'Español','Físico',NULL,NULL,'Literatura',1,4),
                (4,417,'Cien años de soledad',2,'Novela',1967,'Sudamericana',NULL,'Español','Físico',NULL,NULL,'Literatura',1,3),
                (5,120,'Crónica de una muerte anunciada',2,'Novela',1981,'Oveja Negra',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (6,368,'El amor en los tiempos del cólera',2,'Novela',1985,'Oveja Negra',NULL,'Español','Físico',NULL,NULL,'Literatura',1,1),
                (7,192,'El coronel no tiene quien le escriba',2,'Novela',1961,'Sudamericana',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (8,450,'Harry Potter y el Cáliz de Fuego',3,'Fantasía',2002,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,3),
                (9,223,'Harry Potter y la piedra filosofal',3,'Fantasía',1997,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,5),
                (10,251,'Harry Potter y la cámara secreta',3,'Fantasía',1998,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,4),
                (11,317,'Harry Potter y el prisionero de Azkaban',3,'Fantasía',1999,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,3),
                (12,636,'Harry Potter y la Orden del Fénix',3,'Fantasía',2003,'Bloomsbury',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (13,432,'La casa de los espíritus',4,'Novela',1982,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (14,368,'Eva Luna',4,'Novela',1987,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,1),
                (15,416,'Paula',4,'Memorias',1994,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (16,384,'Hija de la fortuna',4,'Novela',1999,'Plaza & Janés',NULL,'Español','Físico',NULL,NULL,'Literatura',1,3),
                (17,432,'La ciudad y los perros',5,'Novela',1963,'Seix Barral',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (18,376,'Conversación en La Catedral',5,'Novela',1969,'Seix Barral',NULL,'Español','Físico',NULL,NULL,'Literatura',1,1),
                (19,304,'Pantaleón y las visitadoras',5,'Novela',1973,'Seix Barral',NULL,'Español','Físico',NULL,NULL,'Literatura',1,2),
                (20,288,'La fiesta del chivo',5,'Novela',2000,'Alfaguara',NULL,'Español','Físico',NULL,NULL,'Literatura',1,1)
            """);
            }

            // =========================
            // EJEMPLARES
            // =========================
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS ejemplares (
                    cod_ejemplar INT(11) NOT NULL AUTO_INCREMENT,
                    estado VARCHAR(50) NOT NULL,
                    id_libro INT(11) NOT NULL,
                    PRIMARY KEY (cod_ejemplar),
                    FOREIGN KEY (id_libro) REFERENCES libros(id_libro)
                        ON DELETE CASCADE ON UPDATE CASCADE
                ) ENGINE=InnoDB;
            """);

            if (isEmpty(st, "ejemplares")) {
                st.executeUpdate("""
                    INSERT INTO ejemplares (estado, id_libro) VALUES
                    ('DISPONIBLE',1),
                    ('DISPONIBLE',1),
                    ('DISPONIBLE',2),
                    ('DISPONIBLE',3),
                    ('DISPONIBLE',3),
                    ('DISPONIBLE',3),
                    ('DISPONIBLE',3),
                    ('DISPONIBLE',4),
                    ('DISPONIBLE',4),
                    ('DISPONIBLE',4),
                    ('DISPONIBLE',5),
                    ('DISPONIBLE',5),
                    ('DISPONIBLE',6),
                    ('DISPONIBLE',7),
                    ('DISPONIBLE',7),
                    ('DISPONIBLE',8),
                    ('DISPONIBLE',8),
                    ('DISPONIBLE',8),
                    ('DISPONIBLE',9),
                    ('DISPONIBLE',9),
                    ('DISPONIBLE',9),
                    ('DISPONIBLE',9),
                    ('DISPONIBLE',9),
                    ('DISPONIBLE',10),
                    ('DISPONIBLE',10),
                    ('DISPONIBLE',10),
                    ('DISPONIBLE',10),
                    ('DISPONIBLE',11),
                    ('DISPONIBLE',11),
                    ('DISPONIBLE',11),
                    ('DISPONIBLE',12),
                    ('DISPONIBLE',12),
                    ('DISPONIBLE',13),
                    ('DISPONIBLE',13),
                    ('DISPONIBLE',14),
                    ('DISPONIBLE',15),
                    ('DISPONIBLE',15),
                    ('DISPONIBLE',16),
                    ('DISPONIBLE',16),
                    ('DISPONIBLE',16),
                    ('DISPONIBLE',17),
                    ('DISPONIBLE',17),
                    ('DISPONIBLE',18),
                    ('DISPONIBLE',19),
                    ('DISPONIBLE',19),
                    ('DISPONIBLE',20)
                """);
            }

            // =========================
            // PENALIZACIONES
            // =========================
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS penalizaciones (
                    cod_penalizacion INT(11) NOT NULL AUTO_INCREMENT,
                    motivo VARCHAR(255) NOT NULL,
                    fecha_inicio DATE NOT NULL,
                    fecha_fin DATE,
                    activa TINYINT(1) DEFAULT 1,
                    PRIMARY KEY (cod_penalizacion)
                ) ENGINE=InnoDB;
            """);

            // =========================
            // USUARIOS
            // =========================
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id_usuario INT(11) NOT NULL AUTO_INCREMENT,
                    nombre VARCHAR(100) NOT NULL,
                    fecha_nacimiento DATE NOT NULL,
                    defuncion TINYINT(1) DEFAULT 0,
                    fecha_fallecimiento DATE,
                    activo TINYINT(1) DEFAULT 1,
                    dni VARCHAR(20) NOT NULL UNIQUE,
                    numero_seguridad_social INT(11) NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    cod_penalizacion INT(11),
                    admin TINYINT(1) DEFAULT 0,
                    PRIMARY KEY (id_usuario),
                    FOREIGN KEY (cod_penalizacion)
                        REFERENCES penalizaciones(cod_penalizacion)
                        ON DELETE SET NULL
                ) ENGINE=InnoDB;
            """);

            if (isEmpty(st, "usuarios")) {
                st.executeUpdate("""
                    INSERT INTO usuarios (
                        nombre, fecha_nacimiento, defuncion,
                        fecha_fallecimiento, activo, dni,
                        numero_seguridad_social, password,
                        cod_penalizacion, admin
                    )
                    VALUES
                    ('admin','2000-01-01',0,NULL,1,'admin',0,'admin',NULL,1)
                """);
            }

            // =========================
            // PRESTAMOS
            // =========================
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS prestamos (
                    cod_prestamo INT(11) NOT NULL AUTO_INCREMENT,
                    fecha_prestamo DATE NOT NULL,
                    fecha_devolucion DATE,
                    cod_ejemplar INT(11) NOT NULL,
                    id_usuario INT(11) NOT NULL,
                    PRIMARY KEY (cod_prestamo),
                    FOREIGN KEY (cod_ejemplar)
                        REFERENCES ejemplares(cod_ejemplar)
                        ON DELETE CASCADE ON UPDATE CASCADE,
                    FOREIGN KEY (id_usuario)
                        REFERENCES usuarios(id_usuario)
                        ON DELETE CASCADE ON UPDATE CASCADE
                ) ENGINE=InnoDB;
            """);

        } catch (SQLException e) {
            System.out.println("Error inicializando BD:");
            e.printStackTrace();
        }
    }

    // =========================
    // UTILIDAD
    // =========================
    private static boolean isEmpty(Statement st, String table) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + table);
        rs.next();
        return rs.getInt(1) == 0;
    }
}