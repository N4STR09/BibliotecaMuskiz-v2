package src.Classes.service;

import java.sql.*;
import java.time.LocalDate;

import java.util.Scanner;

import src.Classes.model.Usuario;
import src.Classes.utils.ColoresUtils;
import src.Classes.utils.InputUtils;
import src.Classes.repository.ConexionBD;

public class ServicePrestamos {

    //solicitar prestamo
    public static void solicitarPrestamo(Scanner sc, Usuario usuario) {

        try (Connection con = ConexionBD.conectar()) {

            String sqlCount = """
                SELECT COUNT(*)
                FROM prestamos
                WHERE id_usuario = ?
                AND fecha_devolucion IS NULL
            """;

            try (PreparedStatement ps = con.prepareStatement(sqlCount)) {

                ps.setInt(1, usuario.getIdUsuario());

                ResultSet rs = ps.executeQuery();

                if (rs.next() && rs.getInt(1) >= 3) {
                    System.out.println(ColoresUtils.ROJO +
                            "Límite de 3 préstamos alcanzado." +
                            ColoresUtils.RESET);
                    return;
                }
            }

            //libros con ejemplares disponibles
            String sqlLibros = """
                SELECT l.id_libro, l.titulo, COUNT(e.cod_ejemplar) AS disponibles
                FROM libros l
                JOIN ejemplares e ON l.id_libro = e.id_libro
                WHERE e.estado = 'DISPONIBLE'
                GROUP BY l.id_libro, l.titulo
            """;

            System.out.println("\nLIBROS DISPONIBLES:\n");

            try (PreparedStatement ps = con.prepareStatement(sqlLibros);
                ResultSet rs = ps.executeQuery()) {

                boolean hay = false;

                while (rs.next()) {
                    hay = true;
                    System.out.println(
                        rs.getInt("id_libro") + " - " +
                        rs.getString("titulo") + " (disponibles: " +
                        rs.getInt("disponibles") + ")"
                    );
                }

                if (!hay) {
                    System.out.println(ColoresUtils.ROJO + "No hay libros disponibles." + ColoresUtils.RESET);
                    return;
                }
            }

            //seleccionar libro
            int idLibro = InputUtils.leerInt(sc, "\nID del libro (0 para salir): ");

            if (idLibro == 0) {
                System.out.println(ColoresUtils.ROJO_LADRILLO + "Operación cancelada." + ColoresUtils.RESET);
                InputUtils.pausa(sc);
                InputUtils.limpiarPantalla();
                return;
            }

            //obtener ejemplar disponible real
            String sqlEjemplar = """
                SELECT cod_ejemplar
                FROM ejemplares
                WHERE id_libro = ?
                AND estado = 'DISPONIBLE'
                LIMIT 1
            """;

            int codEjemplar;

            try (PreparedStatement ps = con.prepareStatement(sqlEjemplar)) {

                ps.setInt(1, idLibro);

                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    System.out.println(ColoresUtils.ROJO +
                            "No hay ejemplares disponibles." +
                            ColoresUtils.RESET);
                    return;
                }

                codEjemplar = rs.getInt("cod_ejemplar");
            }

            //insertar prestamo
            String sqlInsert = """
                INSERT INTO prestamos (
                    fecha_prestamo,
                    fecha_devolucion,
                    cod_ejemplar,
                    id_usuario
                ) VALUES (?, NULL, ?, ?)
            """;

            try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {

                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setInt(2, codEjemplar);
                ps.setInt(3, usuario.getIdUsuario());

                ps.executeUpdate();
            }

            //marcar ejemplar como prestado
            String sqlUpdateEjemplar = """
                UPDATE ejemplares
                SET estado = 'PRESTADO'
                WHERE cod_ejemplar = ?
            """;

            try (PreparedStatement ps = con.prepareStatement(sqlUpdateEjemplar)) {
                ps.setInt(1, codEjemplar);
                ps.executeUpdate();
            }

            System.out.println(ColoresUtils.VERDE +
                    "Préstamo realizado correctamente." +
                    ColoresUtils.RESET);

            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ver prestamos activos
    public static void verPrestamosActivos(Scanner sc, Usuario usuario) {

        String sql = """
            SELECT p.cod_prestamo, l.titulo, p.fecha_prestamo
            FROM prestamos p
            JOIN ejemplares e ON p.cod_ejemplar = e.cod_ejemplar
            JOIN libros l ON e.id_libro = l.id_libro
            WHERE p.id_usuario = ?
            AND p.fecha_devolucion IS NULL
        """;

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());

            ResultSet rs = ps.executeQuery();

            System.out.println("\nPRÉSTAMOS ACTIVOS:\n");

            boolean hay = false;

            while (rs.next()) {
                hay = true;
                System.out.println(
                    rs.getInt("cod_prestamo") + " - " +
                    rs.getString("titulo") + " - " +
                    rs.getDate("fecha_prestamo")
                );
            }

            if (!hay) {
                System.out.println("No tienes préstamos activos.");
            }

            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //devolver prestamo
    public static void gestionarPrestamos(Scanner sc, Usuario usuario) {

        boolean hayPrestamos = listarPrestamosActivos(sc, usuario);

        if (!hayPrestamos) {
            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();
            return;
        }

        int codPrestamo = InputUtils.leerInt(sc, "\nCódigo del préstamo a devolver: ");

        try (Connection con = ConexionBD.conectar()) {

            //obtener ejemplar
            String sqlGet = """
                SELECT cod_ejemplar
                FROM prestamos
                WHERE cod_prestamo = ?
                AND id_usuario = ?
            """;

            int codEjemplar;

            try (PreparedStatement ps = con.prepareStatement(sqlGet)) {

                ps.setInt(1, codPrestamo);
                ps.setInt(2, usuario.getIdUsuario());

                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    System.out.println(ColoresUtils.ROJO +
                            "Préstamo no encontrado." +
                            ColoresUtils.RESET);
                    return;
                }

                codEjemplar = rs.getInt("cod_ejemplar");
            }

            //marcar devolucion
            String sqlDev = """
                UPDATE prestamos
                SET fecha_devolucion = ?
                WHERE cod_prestamo = ?
                AND id_usuario = ?
            """;

            try (PreparedStatement ps = con.prepareStatement(sqlDev)) {

                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setInt(2, codPrestamo);
                ps.setInt(3, usuario.getIdUsuario());

                ps.executeUpdate();
            }

            //devolver ejemplar
            String sqlEjemplar = """
                UPDATE ejemplares
                SET estado = 'DISPONIBLE'
                WHERE cod_ejemplar = ?
            """;

            try (PreparedStatement ps = con.prepareStatement(sqlEjemplar)) {

                ps.setInt(1, codEjemplar);
                ps.executeUpdate();
            }

            System.out.println(ColoresUtils.VERDE +
                    "Préstamo devuelto correctamente." +
                    ColoresUtils.RESET);

            InputUtils.pausa(sc);
            InputUtils.limpiarPantalla();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean listarPrestamosActivos(Scanner sc, Usuario usuario) {

        String sql = """
            SELECT p.cod_prestamo, l.titulo, p.fecha_prestamo
            FROM prestamos p
            JOIN ejemplares e ON p.cod_ejemplar = e.cod_ejemplar
            JOIN libros l ON e.id_libro = l.id_libro
            WHERE p.id_usuario = ?
            AND p.fecha_devolucion IS NULL
        """;

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());

            ResultSet rs = ps.executeQuery();

            System.out.println("\nPRÉSTAMOS ACTIVOS:\n");

            boolean hay = false;

            while (rs.next()) {
                hay = true;
                System.out.println(
                    rs.getInt("cod_prestamo") + " - " +
                    rs.getString("titulo") + " - " +
                    rs.getDate("fecha_prestamo")
                );
            }

            if (!hay) {
                System.out.println("No tienes préstamos activos.");
            }

            return hay;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}