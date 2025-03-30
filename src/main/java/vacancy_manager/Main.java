package vacancy_manager;

import javafx.application.Application;
import javafx.stage.Stage;
import vacancy_manager.controllers.MainController;

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