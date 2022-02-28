package org.pastorga.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC";
    private static String username = "root";
    private static String pass = "root";
    private static Connection conn;

    public static Connection getInstance() throws SQLException {//Autoclose
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, pass);
        }
        return conn;
    }

}
