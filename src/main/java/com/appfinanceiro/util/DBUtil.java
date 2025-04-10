package com.appfinanceiro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
        private static final String URL = "jdbc:h2:./data/appfinanceiro;AUTO_SERVER=TRUE";
    private static final String USER = "adm";
    private static final String PASSWORD = "";

    static {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS transactions (" +
                         "id IDENTITY PRIMARY KEY, " +
                         "description VARCHAR(255), " +
                         "amount DOUBLE, " +
                         "type VARCHAR(10), " +
                         "date DATE)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
