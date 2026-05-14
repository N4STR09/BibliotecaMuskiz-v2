package src.Classes.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bibliotecamuskiz";

    private static final String USER = "ian";
    private static final String PASSWORD = "1234";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}