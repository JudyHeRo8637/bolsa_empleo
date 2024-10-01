package org.bolsa.java.js.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3306/bolsa_empleo";
    private static String username = "root";
    private static String password = "Herox";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
