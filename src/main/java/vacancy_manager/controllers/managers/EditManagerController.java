package vacancy_manager.controllers.managers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vacancy_manager.managers.ReposManager;
import vacancy_manager.models.Manager;
import vacancy_manager.utils.AlertUtils;

public class EditManagerController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField patronymicField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button saveButton;


    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private Manager manager;

    private ManagerListController managerListController;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        firstNameField.setText(manager.getFirstName());
        lastNameField.setText(manager.getLastName());
        patronymicField.setText(manager.getPatronymic());
        emailField.setText(manager.getEmail());
        phoneField.setText(manager.getPhone());

    }

    public void setManagerListController(ManagerListController managerListController) {
        this.managerListController = managerListController;
    }

    @FXML
    private void handleSaveChanges() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String patronymic = patronymicField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || patronymic.isEmpty() || email.isEmpty()) {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, заполните все обязательные поля.");
            return;
        }

        try {
            manager.setFirstName(firstName);
            manager.setLastName(lastName);
            manager.setPatronymic(patronymic);
            manager.setEmail(email);
            manager.setPhone(phone);

            managerListController.updateManagerInTable(manager);
            ReposManager.getManagerRepo().updateManager(manager);
            dialogStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
