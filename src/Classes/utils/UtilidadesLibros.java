package src.Classes.utils;

import src.Classes.repository.ConexionBD;

import java.sql.*;
import java.util.*;

public class UtilidadesLibros {

    //paginas
    public static double mediaPaginas() {

        String sql = "SELECT AVG(numero_paginas) FROM libros";

        return ejecutarDouble(sql);
    }

    public static int maxPaginas() {

        String sql = "SELECT MAX(numero_paginas) FROM libros";

        return ejecutarInt(sql);
    }

    public static int minPaginas() {

        String sql = "SELECT MIN(numero_paginas) FROM libros";

        return ejecutarInt(sql);
    }

    //existencias
    public static double mediaExistencias() {

        String sql = "SELECT AVG(existencias) FROM libros";

        return ejecutarDouble(sql);
    }

    public static int maxExistencias() {

        String sql = "SELECT MAX(existencias) FROM libros";

        return ejecutarInt(sql);
    }

    public static int minExistencias() {

        String sql = "SELECT MIN(existencias) FROM libros";

        return ejecutarInt(sql);
    }

    //generales
    public static double porcentajeDisponibles() {

        String sql = """
            SELECT (SUM(CASE WHEN disponibilidad = 1 THEN 1 ELSE 0 END) / COUNT(*)) * 100
            FROM libros
        """;

        return ejecutarDouble(sql);
    }

    public static int totalLibros() {

        String sql = "SELECT COUNT(*) FROM libros";

        return ejecutarInt(sql);
    }

    public static Map<String, Integer> librosPorGenero() {

        Map<String, Integer> map = new HashMap<>();

        String sql = """
            SELECT genero, COUNT(*) as total
            FROM libros
            GROUP BY genero
        """;

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("genero"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    //helpers
    private static double ejecutarDouble(String sql) {

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getDouble(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static int ejecutarInt(String sql) {

        try (Connection con = ConexionBD.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}