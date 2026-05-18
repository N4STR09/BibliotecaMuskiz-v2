package src.Classes.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://datos.somorrostro.com/2526DAMEquipo05";

    private static final String USER = "2526DAMEquipo05";
    private static final String PASSWORD = "2526DAMEquipo05";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean cerrarConexion(Connection con) {

        try {

            if (con != null && !con.isClosed()) {
                con.close();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}