module com.example.cattempt1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cattempt1 to javafx.fxml;
    exports com.example.cattempt1;
}