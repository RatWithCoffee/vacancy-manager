package vacancy_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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

            VacancyListController controller = loader.getController();
            controller.setStage(stage);

            Scene vacancyScene = new Scene(page, 1500, 600);
            stage.setTitle("Список вакансий");
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

            ManagerListController controller = loader.getController();
            controller.setStage(stage);

            Scene managerScene = new Scene(page, 1500, 600);
            stage.setTitle("Список менеджеров");
            stage.setScene(managerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStatisticsButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vacancy_statistic.fxml"));
            BorderPane page = loader.load();

            VacancyStatisticsController controller = loader.getController();
            controller.setStage(stage);
            controller.loadData(); // Load and display the data

            Scene statisticsScene = new Scene(page, 1500, 600);
            stage.setTitle("Статистика по вакансиям");
            stage.setScene(statisticsScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showMainMenu(Stage stage) {
        System.out.println("here we are");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("main_page.fxml"));
            MainController controller = new MainController(stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            stage.setTitle("Менеджер вакансий");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
