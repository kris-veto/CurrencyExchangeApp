module com.cex.currencyex {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cex.currencyex to javafx.fxml;
    exports com.cex.currencyex;
}