module com.example.assignment2s4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment2s4 to javafx.fxml;
    exports com.example.assignment2s4;
}