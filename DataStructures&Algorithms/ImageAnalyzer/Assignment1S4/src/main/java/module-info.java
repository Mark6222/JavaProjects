module com.example.assignment1s4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.assignment1s4 to javafx.fxml;
    exports com.example.assignment1s4;
}