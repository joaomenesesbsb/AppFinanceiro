package com.appfinanceiro.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.appfinanceiro.model.Transaction;
import com.appfinanceiro.util.DBUtil;

public class TransactionRepository {
    
    
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (description, amount, type, date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaction.getDescription());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getType());
            stmt.setDate(4, Date.valueOf(transaction.getDate()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getLong("id"));
                t.setDescription(rs.getString("description"));
                t.setAmount(rs.getDouble("amount"));
                t.setType(rs.getString("type"));
                t.setDate(rs.getDate("date").toLocalDate());

                transactions.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM transactions WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
