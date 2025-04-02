package vacancy_manager.controllers.managers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vacancy_manager.managers.ReposManager;
import vacancy_manager.models.Manager;
import vacancy_manager.utils.AlertUtils;

import java.rmi.RemoteException;

public class AddManagerController {

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

    private Stage stage;
    private ManagerListController managerListController;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setVacanciesController(ManagerListController managerListController) {
        this.managerListController = managerListController;
    }

    // Обработчик сохранения нового менеджера
    @FXML
    private void handleSave() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String patronymic = patronymicField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        // Проверяем, что все поля заполнены
        if (firstName.isEmpty() || lastName.isEmpty() || patronymic.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        // Создаем нового менеджера и добавляем его в репозиторий
        Manager newManager = new Manager(-1, firstName, lastName, patronymic, email, phone);
        int id = 0;
        try {
            id = ReposManager.getManagerRepo().addManager(newManager);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        newManager.setId(id);
        managerListController.addManagerToTable(newManager);
        stage.close(); // Закрываем окно после добавления менеджера
    }


    // Обработчик отмены
    @FXML
    private void handleCancel() {
        stage.close();
    }
}
