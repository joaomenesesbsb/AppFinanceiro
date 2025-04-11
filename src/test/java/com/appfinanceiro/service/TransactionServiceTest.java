package com.appfinanceiro.service;

import com.appfinanceiro.model.Transaction;
import com.appfinanceiro.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionRepository mockRepository;
    private TransactionService service;

    @BeforeEach
    public void setup() {
        mockRepository = mock(TransactionRepository.class);
        service = new TransactionService(mockRepository);
    }

    @Test
    public void testCalculateTotalIncome() {
        // Given
        List<Transaction> mockTransactions = Arrays.asList(
            new Transaction("Salary", 3000.00, "Income", LocalDate.now()),
            new Transaction("Freelance", 1500.00, "Income", LocalDate.now()),
            new Transaction("Rent", 1000.00, "Expense", LocalDate.now())
        );

        when(mockRepository.findAll()).thenReturn(mockTransactions);

        // When
        double totalIncome = service.calculateTotalIncome();

        // Then
        assertEquals(4500.00, totalIncome, 0.001);
        verify(mockRepository, times(1)).findAll(); // garante que foi chamado
    }

    @Test
    public void testCalculateTotalExpense() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("Groceries", 200.0, "Expense", LocalDate.now()),
            new Transaction("Rent", 800.0, "Expense", LocalDate.now()),
            new Transaction("Bonus", 1000.0, "Income", LocalDate.now())
        );

        when(mockRepository.findAll()).thenReturn(transactions);

        double result = service.calculateTotalExpense();
        assertEquals(1000.0, result, 0.001);
    }

    
    @Test
    public void testCalculateBalance() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("Salary", 3000.0, "Income", LocalDate.now()),
            new Transaction("Bonus", 500.0, "Income", LocalDate.now()),
            new Transaction("Utilities", 400.0, "Expense", LocalDate.now()),
            new Transaction("Rent", 1000.0, "Expense", LocalDate.now())
        );

        when(mockRepository.findAll()).thenReturn(transactions);

        double result = service.calculateBalance();
        // Total Income: 3500.0, Total Expense: 1400.0
        assertEquals(2100.0, result, 0.001);
    }
}
