module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports com.example;
    exports models;
    opens com.example to javafx.fxml;
}