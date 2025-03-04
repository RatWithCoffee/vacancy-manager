package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Vacancy;

public class AddVacancyController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField salaryField;


    @FXML
    private TextField managerField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

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
        String manager = managerField.getText();

        if (title.isEmpty() || description.isEmpty() || salaryText.isEmpty()  || manager.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);

            // Create a new Vacancy
            Vacancy newVacancy = new Vacancy(
                    vacancyController.getVacancyList().size() + 1, // Just a placeholder for the ID
                    title,
                    description,
                    salary,
                    manager
            );

            // Add the new vacancy to the main table
            vacancyController.addVacancyToTable(newVacancy);
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
}
