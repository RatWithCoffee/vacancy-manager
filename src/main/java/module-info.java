module vacancy_manager{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    exports vacancy_manager.controllers;
    exports vacancy_manager.models;
    opens vacancy_manager.controllers to javafx.fxml;
}