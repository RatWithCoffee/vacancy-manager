package vacancy_manager.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vacancy_manager.controllers.vacancies.VacancyListController;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainController.showMainMenu(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}