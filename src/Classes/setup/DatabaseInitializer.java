package src.Classes.setup;

import src.Classes.repository.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() {

        try (Connection con = ConexionBD.conectar();
            Statement st = con.createStatement()) {

            //autores
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

            //libros
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

            //ejemplares
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

            //penalizaciones
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

            //usuarios
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

            //prestamos
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
}