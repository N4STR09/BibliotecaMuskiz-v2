package src.Classes.utils;

import src.Classes.model.Autor;
import src.Classes.repository.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilidadesAutores {

    //autor con mas libros
    public static List<Autor> autoresConMasLibros() {

        String sql = """
            SELECT a.*
            FROM autores a
            LEFT JOIN libros l ON a.id_autor = l.id_autor
            GROUP BY a.id_autor
            HAVING COUNT(l.id_libro) = (
                SELECT MAX(total)
                FROM (
                    SELECT COUNT(*) AS total
                    FROM libros
                    GROUP BY id_autor
                ) t
            )
        """;

        return ejecutarAutores(sql);
    }

    //autor con menos libros
    public static List<Autor> autoresConMenosLibros() {

        String sql = """
            SELECT a.*
            FROM autores a
            LEFT JOIN libros l ON a.id_autor = l.id_autor
            GROUP BY a.id_autor
            HAVING COUNT(l.id_libro) = (
                SELECT MIN(total)
                FROM (
                    SELECT COUNT(*) AS total
                    FROM libros
                    GROUP BY id_autor
                ) t
            )
        """;

        return ejecutarAutores(sql);
    }

    //autor libro mas largo
    public static List<Autor> autoresConLibroMasLargo() {

        String sql = """
            SELECT DISTINCT a.*
            FROM autores a
            JOIN libros l ON l.id_autor = a.id_autor
            WHERE l.numero_paginas = (
                SELECT MAX(numero_paginas)
                FROM libros
            )
        """;

        return ejecutarAutores(sql);
    }

    //autor mas viejo
    public static List<Autor> autoresMasViejos() {

        String sql = """
            SELECT *
            FROM autores
            WHERE TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) =
            (
                SELECT MAX(TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()))
                FROM autores
            )
        """;

        return ejecutarAutores(sql);
    }

    //autor mas joven
    public static List<Autor> autoresMasJovenes() {

        String sql = """
            SELECT *
            FROM autores
            WHERE TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) =
            (
                SELECT MIN(TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()))
                FROM autores
            )
        """;

        return ejecutarAutores(sql);
    }

    //edad media
    public static double edadMediaAutores() {

        String sql = """
            SELECT AVG(TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()))
            FROM autores
        """;

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    //total autores
    public static int totalAutores() {

        String sql = "SELECT COUNT(*) FROM autores";

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    //ejecutor generico
    private static List<Autor> ejecutarAutores(String sql) {

        List<Autor> autores = new ArrayList<>();

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                autores.add(mapAutor(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

    //mapper bd -> objeto
    private static Autor mapAutor(ResultSet rs) throws SQLException {

        return new Autor(
            rs.getInt("id_autor"),
            rs.getString("nombre"),
            rs.getString("nacionalidad"),
            rs.getDate("fecha_nacimiento").toLocalDate(),
            rs.getBoolean("defuncion"),
            rs.getDate("fecha_fallecimiento") != null
                ? rs.getDate("fecha_fallecimiento").toLocalDate()
                : null,
            rs.getString("biografia"),
            rs.getString("foto"),
            rs.getString("genero_literario"),
            rs.getString("premios"),
            rs.getString("obras_destacadas")
        );
    }
}