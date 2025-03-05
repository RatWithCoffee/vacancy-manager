package vacancy_manager.controllers.vacancies;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vacancy_manager.models.Manager;
import vacancy_manager.models.Vacancy;
import vacancy_manager.repos.VacancyRepo;
import vacancy_manager.utils.AlertUtils;

import java.io.IOException;

public class AddVacancyController implements ManageSelector {

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

    private VacancyListController vacancyListController;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setVacanciesController(VacancyListController vacancyControllerListController) {
        this.vacancyListController = vacancyControllerListController;
    }


    @FXML
    private void handleAddVacancy() {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String salaryText = salaryField.getText();
        String managerName = managerField.getText();

        if (title.isEmpty() || description.isEmpty() || salaryText.isEmpty() || managerName.isEmpty()) {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);
            Vacancy newVacancy = new Vacancy(
                    title,
                    description,
                    salary,
                    managerId,
                    managerName
            );

            vacancyListController.addVacancyToTable(newVacancy);
            VacancyRepo.addVacancy(newVacancy);
            dialogStage.close();
        } catch (NumberFormatException e) {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, введите корректную зарплату.");
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    @FXML
    private void handleSelectManagerButton() {
        ManagerSelectionCreator.createManagerSelectionWindow(this);
    }

    @Override
    public void selectManager(Manager manager) {
        this.managerId = manager.getId();
        this.managerField.setText(manager.toString());
    }
}
