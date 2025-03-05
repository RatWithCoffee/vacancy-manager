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
        FXMLLoader fxmlLoader = new FXMLLoader(VacancyListController.class.getResource("vacancy_list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 600);
        stage.setTitle("Vacancy manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}