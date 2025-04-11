package com.appfinanceiro.service;

import java.util.List;

import com.appfinanceiro.model.Transaction;
import com.appfinanceiro.repository.TransactionRepository;

public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public TransactionService() {
        this.repository = new TransactionRepository();
    }

    public void addTransaction(Transaction transaction) {
        // You can add validation here if needed
        repository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public double calculateTotalIncome() {
        return repository.findAll().stream()
                .filter(t -> "Income".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double calculateTotalExpense() {
        return repository.findAll().stream()
                .filter(t -> "Expense".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double calculateBalance() {
        return calculateTotalIncome() - calculateTotalExpense();
    }
}
