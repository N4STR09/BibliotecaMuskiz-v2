package src.Classes.repository;

import src.Classes.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorRepository {

    public static void guardarAutor(Autor autor) {

        String sql = """
            INSERT INTO autores (
                nombre,
                nacionalidad,
                fecha_nacimiento,
                defuncion,
                fecha_fallecimiento,
                biografia,
                foto,
                genero_literario,
                premios,
                obras_destacadas
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (
            Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.setDate(3, java.sql.Date.valueOf(autor.getFechaNacimiento()));
            ps.setBoolean(4, autor.isDefuncion());

            if (autor.getFechaFallecimiento() != null) {
                ps.setDate(5, java.sql.Date.valueOf(autor.getFechaFallecimiento()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.setString(6, autor.getBiografia());
            ps.setString(7, autor.getFoto());
            ps.setString(8, autor.getGeneroLiterario());
            ps.setString(9, autor.getPremios());
            ps.setString(10, autor.getObrasDestacadas());

            ps.executeUpdate();

            System.out.println("Autor guardado.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Autor> cargarAutores() {

        List<Autor> autores = new ArrayList<>();

        String sql = "SELECT * FROM autores";

        try (
            Connection con = ConexionBD.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                Autor autor = new Autor(
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

                autores.add(autor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

    public static void eliminarAutor(int idAutor) {

        String sql = "DELETE FROM autores WHERE id_autor = ?";

        try (
            Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idAutor);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Autor eliminado correctamente.");
            } else {
                System.out.println("No existe un autor con ese ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}