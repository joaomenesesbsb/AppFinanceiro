package com.appfinanceiro.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Transaction {

    private Long id;
    private String description; // descrição do lançamento
    private double amount;      // valor em reais
    private String type;        // Receita ou Despesa
    private LocalDate date;     // data da transação

    public Transaction() {}

    public Transaction(String description, double amount, String type, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

}
