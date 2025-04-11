package com.appfinanceiro.controller;

import java.time.LocalDate;

import com.appfinanceiro.model.Transaction;
import com.appfinanceiro.service.TransactionService;
import com.appfinanceiro.view.TransactionForm;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class TransactionController {
    
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    public void handleAdd(TransactionForm form, ObservableList<Transaction> list) {
        try {
            String description = form.getDescriptionField().getText();
            double amount = Double.parseDouble(form.getAmountField().getText());
            String type = form.getTypeCombo().getValue();
            LocalDate date = form.getDatePicker().getValue();

            Transaction transaction = new Transaction(description, amount, type, date);
            service.addTransaction(transaction);
            list.add(transaction); // Adiciona à tabela

            System.out.println("Objeto adicionado com sucesso");
            System.err.println("## descricao " + transaction.getDescription() + 
                                "/  amount  " + transaction.getAmount() + 
                                "/  Type   " + transaction.getType() + 
                                "/  date   " + 
                                transaction.getDate());

            // Limpar campos depois
            form.getDescriptionField().clear();
            form.getAmountField().clear();
            form.getTypeCombo().setValue(null);
            form.getDatePicker().setValue(null);

        } catch (Exception e) {
            showAlert("Invalid input", "Please fill all fields correctly.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
