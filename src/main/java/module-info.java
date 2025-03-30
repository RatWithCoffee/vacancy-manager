module vacancy_manager{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;
    requires java.rmi;

    exports vacancy_manager.controllers;
    exports vacancy_manager.models;
    opens vacancy_manager.controllers to javafx.fxml;
    exports vacancy_manager.controllers.vacancies;
    opens vacancy_manager.controllers.vacancies to javafx.fxml;
    exports vacancy_manager;
    opens vacancy_manager to javafx.fxml;
}