package com.cex.currencyex;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {

    //Exchange currencies available
    private double EUR = 0.93;
    private double EGP = 47.38;
    private double YEN = 155.78;
    private double CAD = 1.37;
    private double USD = 1;

    // Variables taken from id
    @FXML
    private TextField cvFrom;

    @FXML
    private TextField cvTo;

    @FXML
    private ComboBox<String> select_one;

    @FXML
    private ComboBox<String> select_two;

    @FXML
    private Label error;

    // List of currencies available for selection
    private final ObservableList<String> list = FXCollections.observableArrayList("USD", "EUR", "EGP", "YEN", "CAD");

    // Methods to populate the ComboBOxes
    @FXML
    protected void showMenuOne() {
        select_one.setItems(list);
    }
    @FXML
    protected void showMenuTwo() {
        select_two.setItems(list);
    }

    //initializing controller
    @FXML
    public void initialize() {
        showMenuOne();
        showMenuTwo();
    }
// converting amount method linked to the button "Convert"
    @FXML
    protected void click() {

        try {
            String selectedCurrency = select_one.getSelectionModel().getSelectedItem(); // Getting the selected currency to convert FROM
            String toCurrency = select_two.getSelectionModel().getSelectedItem(); // Getting the selected currency to convert TO

            double amountToConvert = Double.parseDouble(cvFrom.getText());
            double convertedAmount = convertCurrency(amountToConvert, selectedCurrency, toCurrency);

            cvTo.setText(String.format("%.2f", convertedAmount)); // Display the converted amount
            error.setText("");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount to convert");
            error.setText("Please enter a valid amount to convert"); // Display error message
        } catch (NullPointerException e) {
            System.out.println("Please select both currencies");
            error.setText("Please select both currencies"); // Display error message for null selection
        }
    }
// Converting process method
    private double convertCurrency(double amount, String from, String to) {
        double rateFrom = getRate(from);
        double rateTo = getRate(to);
        return (amount / rateFrom) * rateTo;
    }
// Method to get the currency value based on the selected currency
    private double getRate(String currency) {
        switch (currency) {
            case "EUR":
                return EUR; // Return the exchange rate value
            case "EGP":
                return EGP;
            case "YEN":
                return YEN;
            case "CAD":
                return CAD;
            default:
                return USD;
        }
    }
}