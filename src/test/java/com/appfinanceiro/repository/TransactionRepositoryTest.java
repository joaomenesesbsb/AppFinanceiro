package com.appfinanceiro.repository;

import com.appfinanceiro.model.Transaction;
import com.appfinanceiro.util.DBUtil;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    private static TransactionRepository repository;

    @BeforeAll
    static void setup() {
        repository = new TransactionRepository();
    }

    @BeforeEach
    void cleanDatabase() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM transactions");
        } catch (Exception e) {
            fail("Failed to clean up database before test.");
        }
    }

    @Test
    void testSaveAndFindAll() {
        Transaction transaction = new Transaction("Test Income", 500.00, "Income", LocalDate.now());
        repository.save(transaction);

        List<Transaction> result = repository.findAll();

        assertEquals(1, result.size());
        Transaction saved = result.get(0);
        assertEquals("Test Income", saved.getDescription());
        assertEquals(500.00, saved.getAmount());
        assertEquals("Income", saved.getType());
    }

    @Test
    void testDeleteById() {
        Transaction transaction = new Transaction("Temporary", 300.00, "Expense", LocalDate.now());
        repository.save(transaction);

        List<Transaction> all = repository.findAll();
        assertFalse(all.isEmpty());

        Long idToDelete = all.get(0).getId();
        repository.deleteById(idToDelete);

        List<Transaction> afterDelete = repository.findAll();
        assertTrue(afterDelete.isEmpty());
    }
}
