package com.appfinanceiro.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDBUtil {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println(" Conexão com o banco realizada com sucesso!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions");

            System.out.println(" Tabela 'transactions' encontrada. Linhas:");

            while (rs.next()) {
                System.out.println("- ID: " + rs.getLong("id") +
                                   ", Description: " + rs.getString("description") +
                                   ", Amount: " + rs.getDouble("amount") +
                                   ", Type: " + rs.getString("type") +
                                   ", Date: " + rs.getDate("date"));
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar ou consultar o banco:");
            e.printStackTrace();
        }
    }

}
