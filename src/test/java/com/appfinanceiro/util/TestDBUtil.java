package com.appfinanceiro.util;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class TestDBUtil {

    @Test
    void testConnectionAndQuery() {
        try (Connection conn = DBUtil.getConnection()) {
            assertNotNull(conn, "Connection should not be null");
            System.out.println("Connection to database successful.");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions");

            System.out.println("Table 'transactions' found. Rows:");

            while (rs.next()) {
                System.out.println("- ID: " + rs.getLong("id") +
                                   ", Description: " + rs.getString("description") +
                                   ", Amount: " + rs.getDouble("amount") +
                                   ", Type: " + rs.getString("type") +
                                   ", Date: " + rs.getDate("date"));
            }

        } catch (Exception e) {
            fail("Failed to connect or query the database: " + e.getMessage());
        }
    }
}
