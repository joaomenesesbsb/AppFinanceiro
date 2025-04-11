package com.appfinanceiro.view;

import java.time.LocalDate;

import com.appfinanceiro.controller.TransactionController;
import com.appfinanceiro.model.Transaction;
import com.appfinanceiro.repository.TransactionRepository;
import com.appfinanceiro.service.TransactionService;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView extends Application {

    private TableView<Transaction> table;
    private ObservableList<Transaction> transactionList;
    
    TransactionRepository repository = new TransactionRepository();
    TransactionService service = new TransactionService(repository);

    @Override
    public void start(Stage primaryStage) {primaryStage.setTitle("Genrenciador de Finascas");

   

    table = new TableView<>();
    configureTable();

    transactionList = FXCollections.observableArrayList();
    table.setItems(transactionList);

    transactionList.addAll(service.getAllTransactions());

    TransactionForm form = new TransactionForm();
    TransactionService service = new TransactionService(new TransactionRepository());
    TransactionController controller = new TransactionController(service);

    HBox formBox = form.getForm();

    Button editButton = new Button("Edit");
    Button deleteButton = new Button("Delete");
    HBox buttonBox = new HBox(10, editButton, deleteButton);

    form.getAddButton().setOnAction(e -> controller.handleAdd(form, transactionList));

    VBox root = new VBox(10, formBox, table, buttonBox);
    root.setPadding(new Insets(10));
    Scene scene = new Scene(root, 800, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
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

