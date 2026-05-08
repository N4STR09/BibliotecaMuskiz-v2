package src.Classes.repository;

import src.Classes.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    // =========================
    // INSERTAR USUARIO
    // =========================
    public static void guardarUsuario(Usuario u) {

        String sql = """
            INSERT INTO usuarios (
                id_usuario,
                nombre,
                fecha_nacimiento,
                defuncion,
                fecha_fallecimiento,
                activo,
                dni,
                numero_seguridad_social,
                password,
                cod_penalizacion
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, u.getIdUsuario());
            ps.setString(2, u.getNombre());
            ps.setDate(3, Date.valueOf(u.getFechaNacimiento()));
            ps.setBoolean(4, u.isDefuncion());

            if (u.getFechaFallecimiento() != null) {
                ps.setDate(5, Date.valueOf(u.getFechaFallecimiento()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.setBoolean(6, u.isActivo());
            ps.setString(7, u.getDni());
            ps.setInt(8, u.getNumeroSeguridadSocial());
            ps.setString(9, u.getPassword());

            if (u.getCodPenalizacion() != null) {
                ps.setInt(10, u.getCodPenalizacion());
            } else {
                ps.setNull(10, Types.INTEGER);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // CARGAR USUARIOS
    // =========================
    public static List<Usuario> cargarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Integer codPenalizacion = null;

                int tmp = rs.getInt("cod_penalizacion");
                if (!rs.wasNull()) {
                    codPenalizacion = tmp;
                }

                Usuario u = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getBoolean("defuncion"),
                    rs.getDate("fecha_fallecimiento") != null
                        ? rs.getDate("fecha_fallecimiento").toLocalDate()
                        : null,
                    rs.getBoolean("activo"),
                    rs.getString("dni"),
                    rs.getInt("numero_seguridad_social"),
                    rs.getString("password"),
                    codPenalizacion
                );

                usuarios.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // =========================
    // ELIMINAR USUARIO
    // =========================
    public static void eliminarUsuario(int idUsuario) {

        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public static Usuario buscarPorId(int idUsuario) {

        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Integer codPenalizacion = null;

                int tmp = rs.getInt("cod_penalizacion");
                if (!rs.wasNull()) {
                    codPenalizacion = tmp;
                }

                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getBoolean("defuncion"),
                    rs.getDate("fecha_fallecimiento") != null
                        ? rs.getDate("fecha_fallecimiento").toLocalDate()
                        : null,
                    rs.getBoolean("activo"),
                    rs.getString("dni"),
                    rs.getInt("numero_seguridad_social"),
                    rs.getString("password"),
                    codPenalizacion
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // LOGIN
    // =========================
    public static Usuario login(String dni, String password) {

        String sql = """
            SELECT *
            FROM usuarios
            WHERE dni = ? AND password = ?
        """;

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Integer codPenalizacion = null;

                int tmp = rs.getInt("cod_penalizacion");
                if (!rs.wasNull()) {
                    codPenalizacion = tmp;
                }

                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getBoolean("defuncion"),
                    rs.getDate("fecha_fallecimiento") != null
                        ? rs.getDate("fecha_fallecimiento").toLocalDate()
                        : null,
                    rs.getBoolean("activo"),
                    rs.getString("dni"),
                    rs.getInt("numero_seguridad_social"),
                    rs.getString("password"),
                    codPenalizacion
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}