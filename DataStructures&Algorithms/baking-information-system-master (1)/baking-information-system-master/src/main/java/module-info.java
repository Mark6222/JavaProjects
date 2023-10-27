module bnmh.ca2 {
    requires javafx.controls;
    requires javafx.fxml;



    exports bnmh.ca2.controllers;
    opens bnmh.ca2.controllers to javafx.fxml;
    exports bnmh.ca2.models;
    opens bnmh.ca2.models to javafx.fxml;
    exports bnmh.ca2.utils;
    opens bnmh.ca2.utils to javafx.fxml;
}