package src.Classes.repository;

import src.Classes.model.*;
import src.Classes.service.*;
import src.Classes.utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorRepository {

    public static void guardarAutor(Autor autor) {

        String sql = """
            INSERT INTO autores (
                idAutor,
                nombre,
                fechaNacimiento,
                defuncion,
                fechaFallecimiento,
                nacionalidad,
                biografia,
                foto,
                generoLiterario
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (
            Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, autor.getIdAutor());
            ps.setString(2, autor.getNombre());
            ps.setDate(3,
                    java.sql.Date.valueOf(autor.getFechaNacimiento()));

            ps.setBoolean(4, autor.isDefuncion());

            if (autor.getFechaFallecimiento() != null) {
                ps.setDate(5,
                    java.sql.Date.valueOf(
                        autor.getFechaFallecimiento()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.setString(6, autor.getNacionalidad());
            ps.setString(7, autor.getBiografia());
            ps.setString(8, autor.getFoto());
            ps.setString(9, autor.getGeneroLiterario());

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

                Autor autor = new Autor();

                autor.setIdAutor(rs.getInt("idAutor"));
                autor.setNombre(rs.getString("nombre"));

                autores.add(autor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }
}