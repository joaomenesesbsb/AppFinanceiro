package com.appfinanceiro.view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import lombok.Getter;


public class TransactionForm {
    private TextField descriptionField;
    private TextField amountField;
    private ComboBox<String> typeCombo;
    private DatePicker datePicker;
    private Button addButton;

    public HBox getForm() {
        descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        amountField = new TextField();
        amountField.setPromptText("Amount");

        typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Income", "Expense");
        typeCombo.setPromptText("Type");

        datePicker = new DatePicker();
        datePicker.setPromptText("Date");

        addButton = new Button("Add");

        return new HBox(10, descriptionField, amountField, typeCombo, datePicker, addButton);
    }

    // Getters para os campos se você precisar acessá-los depois
    public TextField getDescriptionField() { return descriptionField; }
    public TextField getAmountField() { return amountField; }
    public ComboBox<String> getTypeCombo() { return typeCombo; }
    public DatePicker getDatePicker() { return datePicker; }
    public Button getAddButton() { return addButton; }

}
