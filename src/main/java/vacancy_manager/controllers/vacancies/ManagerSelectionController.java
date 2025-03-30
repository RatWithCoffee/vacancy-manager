package vacancy_manager.controllers.vacancies;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import repos.ReposManager;
import vacancy_manager.models.Manager;
import vacancy_manager.utils.AlertUtils;

import java.rmi.RemoteException;
import java.util.List;

public class ManagerSelectionController {

    @FXML
    private ListView<Manager> managerListView;

    private ManageSelector addVacancyController;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAddVacancyController(ManageSelector addVacancyController) {
        this.addVacancyController = addVacancyController;
    }

    @FXML
    private void initialize() {
        List<Manager> managers = null;
        try {
            managers = ReposManager.getManagerRepo().getAll();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        managerListView.getItems().setAll(managers);
    }

    @FXML
    private void handleSelectManager() {
        Manager selectedManager = managerListView.getSelectionModel().getSelectedItem();
        if (selectedManager != null) {
            addVacancyController.selectManager(selectedManager);
            dialogStage.close();
        } else {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, выберите менеджера.");
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
