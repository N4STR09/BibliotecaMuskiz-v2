package src.Classes.repository;

import src.Classes.model.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepository {

    public static void guardarLibro(Libro libro) {

        String sql = """
            INSERT INTO libros (
                existencias,
                numero_paginas,
                titulo,
                id_autor,
                genero,
                anio_publicacion,
                editorial,
                isbn,
                idioma,
                formato,
                portada,
                descripcion,
                categoria,
                disponibilidad
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (
            Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, libro.getExistencias());
            ps.setInt(2, libro.getNumeroPaginas());
            ps.setString(3, libro.getTitulo());
            ps.setInt(4, libro.getIdAutor());
            ps.setString(5, libro.getGenero());
            ps.setInt(6, libro.getAnioPublicacion());
            ps.setString(7, libro.getEditorial());
            ps.setString(8, libro.getIsbn());
            ps.setString(9, libro.getIdioma());
            ps.setString(10, libro.getFormato());
            ps.setString(11, libro.getPortada());
            ps.setString(12, libro.getDescripcion());
            ps.setString(13, libro.getCategoria());
            ps.setBoolean(14, libro.isDisponibilidad());

            ps.executeUpdate();

            System.out.println("Libro guardado.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Libro> cargarLibros() {

        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT * FROM libros";

        try (
            Connection con = ConexionBD.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                Libro libro = new Libro(
                    rs.getInt("id_libro"),
                    rs.getInt("existencias"),
                    rs.getInt("numero_paginas"),
                    rs.getString("titulo"),
                    rs.getInt("id_autor"),
                    rs.getString("genero"),
                    rs.getInt("anio_publicacion"),
                    rs.getString("editorial"),
                    rs.getString("isbn"),
                    rs.getString("idioma"),
                    rs.getString("formato"),
                    rs.getString("portada"),
                    rs.getString("descripcion"),
                    rs.getString("categoria"),
                    rs.getBoolean("disponibilidad")
                );

                libros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

    public static void eliminarLibro(int idLibro) {

        String sql = "DELETE FROM libros WHERE id_libro = ?";

        try (
            Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idLibro);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No existe un libro con ese ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}