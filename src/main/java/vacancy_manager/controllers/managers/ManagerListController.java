package vacancy_manager.controllers.managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vacancy_manager.controllers.vacancies.AddVacancyController;
import vacancy_manager.models.Manager;
import vacancy_manager.repos.ManagerRepo;
import vacancy_manager.utils.AlertUtils;

public class ManagerListController {

    @FXML
    private TableView<Manager> managerTable;

    @FXML
    private TableColumn<Manager, String> firstNameColumn;

    @FXML
    private TableColumn<Manager, String> lastNameColumn;

    @FXML
    private TableColumn<Manager, String> patronymicColumn;

    @FXML
    private TableColumn<Manager, String> emailColumn;

    @FXML
    private TableColumn<Manager, String> phoneColumn;

    private ObservableList<Manager> managerList;

    public void initialize() {

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        managerList = FXCollections.observableArrayList(ManagerRepo.getAll());
        System.out.println(managerList.size());
        managerTable.setItems(managerList);

    }

    @FXML
    private void handleAddManager() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add_manager.fxml"));
            VBox page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить вакансию");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(managerTable.getScene().getWindow());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddManagerController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setVacanciesController(this);

            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.showAlert("Ошибка", "Не удалось открыть окно добавления менеджера.");
        }
    }

    public void addManagerToTable(Manager manager) {
        managerList.add(manager);
    }

    // Метод для редактирования менеджера
    @FXML
    private void handleEditManager() {
        Manager selectedManager = managerTable.getSelectionModel().getSelectedItem();
        if (selectedManager != null) {
            try {
                // Загружаем FXML для окна редактирования менеджера
                FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_manager.fxml"));
                Scene scene = new Scene(loader.load());

                // Получаем контроллер и передаем выбранного менеджера
                EditManagerController editController = loader.getController();
                editController.setManager(selectedManager);
                editController.setManagerListController(this);

                // Создаем и отображаем окно редактирования
                Stage stage = new Stage();
                editController.setDialogStage(stage);
                stage.setTitle("Редактирование менеджера");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL); // Окно блокирует взаимодействие с основным окном
                stage.showAndWait(); // Ждем закрытия окна

            } catch (Exception e) {
                e.printStackTrace();
                AlertUtils.showAlert("Ошибка", "Не удалось открыть окно редактирования менеджера.");
            }
        } else {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, выберите менеджера для редактирования.");
        }
    }

    public void updateManagerInTable(Manager manager) {
        int selectedIndex = managerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            managerList.set(selectedIndex, manager);
        }
    }

    // Метод для удаления менеджера
    @FXML
    private void handleDeleteManager() {
        Manager selectedManager = managerTable.getSelectionModel().getSelectedItem();
        if (selectedManager != null) {
            ManagerRepo.deleteManager(selectedManager.getId());
            managerList.remove(selectedManager);
        } else {
            AlertUtils.showAlert("Ошибка", "Пожалуйста, выберите менеджера для удаления.");
        }
    }

}
