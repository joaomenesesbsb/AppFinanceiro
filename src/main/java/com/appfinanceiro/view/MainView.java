package com.appfinanceiro.view;

import java.time.LocalDate;

import com.appfinanceiro.model.Transaction;
import com.appfinanceiro.repository.TransactionRepository;
import com.appfinanceiro.service.TransactionService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView extends Application {

    private TableView<Transaction> table;
    
    TransactionRepository repository = new TransactionRepository();
    TransactionService service = new TransactionService(repository);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Financial Manager");

        // Campos de entrada
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Income", "Expense");
        typeCombo.setPromptText("Type");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date");       

        // Tabela de transações
        table = new TableView<>();
        configureTable();

        // Botões
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        // Layout do formulário de entrada
        HBox form = new HBox(10, descriptionField, amountField, typeCombo, datePicker, addButton);

        // Layout dos botoes
        HBox buttonBox = new HBox(10, addButton, editButton, deleteButton);

        // Layout principal
        VBox root = new VBox(10,form, table, buttonBox);
        root.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ação do botão "Add"
addButton.setOnAction(e -> {
    try {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String type = typeCombo.getValue();
        LocalDate date = datePicker.getValue();

        if (description.isEmpty() || type == null || date == null) {
            showAlert(Alert.AlertType.WARNING, "Please fill in all fields.");
            return;
        }

        Transaction transaction = new Transaction(description, amount, type, date);
        service.addTransaction(transaction);

        // Atualiza a tabela
        table.getItems().setAll(service.getAllTransactions());

        // Limpa os campos
        descriptionField.clear();
        amountField.clear();
        typeCombo.setValue(null);
        datePicker.setValue(null);

    } catch (NumberFormatException ex) {
        showAlert(Alert.AlertType.ERROR, "Amount must be a valid number.");
    } catch (Exception ex) {
        ex.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error adding transaction: " + ex.getMessage());
    }
});
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void configureTable() {
        TableColumn<Transaction, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<Transaction, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());

        TableColumn<Transaction, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getType()));

        TableColumn<Transaction, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDate().toString()));

        table.getColumns().addAll(descriptionCol, amountCol, typeCol, dateCol);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

