package vacancy_manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vacancy_manager.models.Manager;
import vacancy_manager.models.Vacancy;
import vacancy_manager.repos.VacancyRepo;

import java.io.IOException;

public class EditVacancyController implements ManageSelector{

    public Button selectManagerButton;
    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField salaryField;


    @FXML
    private TextField managerField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private Vacancy vacancy;

    // Reference to the main controller
    private VacancyController vacanciesController;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
        titleField.setText(vacancy.getTitle());
        descriptionArea.setText(vacancy.getDescription());
        salaryField.setText(String.valueOf(vacancy.getSalary()));
        managerField.setText(vacancy.getManagerName());
    }

    public void setVacanciesController(VacancyController vacanciesController) {
        this.vacanciesController = vacanciesController;
    }

    @FXML
    private void handleSaveChanges() {
        // Get the values from the form fields
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String salaryText = salaryField.getText();
        String manager = managerField.getText();

        if (title.isEmpty() || description.isEmpty() || salaryText.isEmpty() || manager.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);

            // Update the Vacancy object with the new values
            vacancy.setTitle(title);
            vacancy.setDescription(description);
            vacancy.setSalary(salary);
            vacancy.setManagerName(manager);
            vacanciesController.updateVacancyInTable(vacancy);
            VacancyRepo.updateVacancy(vacancy);
            dialogStage.close();
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Пожалуйста, введите корректную зарплату.");
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

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
        this.managerField.setText(manager.toString());
    }
}
