package vacancy_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vacancy_manager.controllers.managers.ManagerListController;
import vacancy_manager.controllers.vacancies.VacancyListController;

import java.io.IOException;

public class MainController {

    private Stage stage;

    public MainController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleVacanciesButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(VacancyListController.class.getResource("vacancy_list.fxml"));
            BorderPane page = loader.load();

            Scene vacancyScene = new Scene(page, 1500, 600);
            stage.setScene(vacancyScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManagersButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(ManagerListController.class.getResource("manager_list.fxml"));
            BorderPane page = loader.load();

            Scene managerScene = new Scene(page, 1500, 600);
            stage.setScene(managerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
