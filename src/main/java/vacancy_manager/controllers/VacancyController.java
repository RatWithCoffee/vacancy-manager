package vacancy_manager.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vacancy_manager.models.Vacancy;
import vacancy_manager.repos.VacancyRepo;

public class VacancyController {

    @FXML private TableView<Vacancy> vacancyTable;
    @FXML
    private TableColumn<Vacancy, String> colTitle;
    @FXML private TableColumn<Vacancy, String> colDescription;
    @FXML private TableColumn<Vacancy, Double> colSalary;
    @FXML private TableColumn<Vacancy, String> colManager;

    @FXML private Button btnAddVacancy;
    @FXML private Button btnEditVacancy;
    @FXML private Button btnDeleteVacancy;

    private ObservableList<Vacancy> vacancyList;

    public ObservableList<Vacancy> getVacancyList() {
        return vacancyList;
    }

    @FXML
    public void initialize() {
        // Initialize the columns with PropertyValueFactory
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colManager.setCellValueFactory(new PropertyValueFactory<>("managerName"));

        // Sample data
//        vacancyList = (ObservableList<Vacancy>) VacancyRepo.getAllVacancies();
        vacancyList = FXCollections.observableList(VacancyRepo.getAllVacancies());

        // Set the data into the TableView
        vacancyTable.setItems(vacancyList);
        btnAddVacancy.setOnAction(event -> handleAddVacancy());
        btnEditVacancy.setOnAction(event -> editVacancy());
        btnDeleteVacancy.setOnAction(event -> deleteVacancy());
    }


    @FXML
    private void handleAddVacancy() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add_vacancy.fxml"));
            GridPane page = loader.load();

            // Create a new stage (window)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить вакансию");
            dialogStage.initModality(Modality.WINDOW_MODAL);  // Makes this window modal
            dialogStage.initOwner(vacancyTable.getScene().getWindow());

            // Set the scene and show the dialog window
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the controller for the dialog window
            AddVacancyController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVacanciesController(this);  // Pass the main controller to the dialog

            // Show the dialog and wait for it to close
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ошибка", "Не удалось открыть окно добавления вакансии.");
        }
    }

    // Method to add a vacancy to the table
    public void addVacancyToTable(Vacancy vacancy) {
        vacancyList.add(vacancy);
    }

    // Method to edit a selected vacancy
    private void editVacancy() {
        Vacancy selectedVacancy = vacancyTable.getSelectionModel().getSelectedItem();
        if (selectedVacancy != null) {
            try {
                // Load the FXML file for editing
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("edit_vacancy.fxml"));
                Scene scene = new Scene(loader.load());

                // Create the dialog stage (window)
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Редактировать вакансию");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(vacancyTable.getScene().getWindow());
                dialogStage.setScene(scene);

                // Get the controller for the edit window and set the selected vacancy
                EditVacancyController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setVacancy(selectedVacancy);
                controller.setVacanciesController(this);

                // Show the dialog window and wait for it to close
                dialogStage.showAndWait();
            } catch (Exception e) {
                showAlert("Ошибка", "Не удалось открыть окно редактирования вакансии.");
            }
        } else {
            showAlert("Выберите вакансию", "Пожалуйста, выберите вакансию для редактирования.");
        }
    }

    public void updateVacancyInTable(Vacancy updatedVacancy) {
        int selectedIndex = vacancyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            vacancyList.set(selectedIndex, updatedVacancy);
        }
    }


    // Method to delete a selected vacancy
    private void deleteVacancy() {
        Vacancy selectedVacancy = vacancyTable.getSelectionModel().getSelectedItem();
        if (selectedVacancy != null) {
            vacancyList.remove(selectedVacancy);
        } else {
            showAlert("Выберите вакансию", "Пожалуйста, выберите вакансию для удаления.");
        }
    }

    // Helper method to show an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
