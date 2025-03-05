package vacancy_manager.controllers.vacancies;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerSelectionCreator {
    public static void createManagerSelectionWindow(ManageSelector selector) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(selector.getClass().getResource("select_manager.fxml"));
            VBox page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Выбор менеджера");

            ManagerSelectionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAddVacancyController(selector);

            dialogStage.setScene(new Scene(page));
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
