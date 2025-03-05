package vacancy_manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vacancy_manager.models.Manager;
import vacancy_manager.models.Vacancy;
import vacancy_manager.repos.VacancyRepo;

import java.io.IOException;

public class AddVacancyController implements ManageSelector{

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField salaryField;


    @FXML
    private TextField managerField;

    private int managerId;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button selectManagerButton;
    private Stage dialogStage;
    private boolean okClicked = false;

    // Reference to the main controller to add the vacancy
    private VacancyController vacancyController;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setVacanciesController(VacancyController vacancyControllerController) {
        this.vacancyController = vacancyControllerController;
    }


    // Method to handle the "Добавить вакансию" button
    @FXML
    private void handleAddVacancy() {
        // Get user inputs
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String salaryText = salaryField.getText();
        String managerName = managerField.getText();

        if (title.isEmpty() || description.isEmpty() || salaryText.isEmpty()  || managerName.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);

            // Create a new Vacancy
            Vacancy newVacancy = new Vacancy(
                    title,
                    description,
                    salary,
                    managerId,
                    managerName
            );

            // Add the new vacancy to the main table
            vacancyController.addVacancyToTable(newVacancy);
            VacancyRepo.addVacancy(newVacancy);
            dialogStage.close();
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Пожалуйста, введите корректную зарплату.");
        }
    }

    // Method to handle the "Отмена" button
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    // Helper method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleSelectManagerButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("select_manager.fxml"));
            VBox page  = loader.load();

            // Create a new stage for the manager list window
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Выбор менеджера");

            // Set up the controller for the manager list
            ManagerSelectionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAddVacancyController(this);

            // Show the manager list window
            dialogStage.setScene(new Scene(page));
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectManager(Manager manager) {
        this.managerId = manager.getId();
        this.managerField.setText(manager.toString());
    }
}
