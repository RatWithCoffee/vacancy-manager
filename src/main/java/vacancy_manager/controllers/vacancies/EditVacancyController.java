package vacancy_manager.controllers.vacancies;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vacancy_manager.managers.ReposManager;
import vacancy_manager.models.Manager;
import vacancy_manager.models.User;
import vacancy_manager.models.Vacancy;
import vacancy_manager.storage.UserStorage;
import vacancy_manager.utils.AlertUtils;

import java.rmi.RemoteException;
import java.util.List;

public class EditVacancyController implements ManageSelector {

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

    private Integer managerId;

    private Stage dialogStage;
    private Vacancy vacancy;

    private VacancyListController vacanciesController;


    @FXML
    private void initialize() {
        User user = UserStorage.getUser();
        if (!user.isAdmin()) {
            selectManagerButton.setVisible(false);
            managerField.setVisible(false);
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
        titleField.setText(vacancy.getTitle());
        descriptionArea.setText(vacancy.getDescription());
        salaryField.setText(String.valueOf(vacancy.getSalary()));
        managerField.setText(vacancy.getManagerName());
        managerId = vacancy.getManagerId();
    }

    public void setVacanciesController(VacancyListController vacanciesController) {
        this.vacanciesController = vacanciesController;
    }

    @FXML
    private void handleSaveChanges() {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String salaryText = salaryField.getText();
        String manager = managerField.getText();

        if (title.isEmpty() || description.isEmpty() || salaryText.isEmpty() || manager.isEmpty()) {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);
            vacancy.setTitle(title);
            vacancy.setDescription(description);
            vacancy.setSalary(salary);
            vacancy.setManagerName(manager);
            vacancy.setManagerId(managerId);
            vacanciesController.updateVacancyInTable(vacancy);
            ReposManager.getVacancyRepo().updateVacancy(vacancy);
            dialogStage.close();
        } catch (NumberFormatException e) {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, введите корректную зарплату.");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
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
