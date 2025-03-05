package vacancy_manager.controllers.vacancies;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vacancy_manager.models.Vacancy;
import vacancy_manager.repos.VacancyRepo;
import vacancy_manager.utils.AlertUtils;

public class VacancyListController {

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

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colManager.setCellValueFactory(new PropertyValueFactory<>("managerName"));


        vacancyList = FXCollections.observableList(VacancyRepo.getAllVacancies());

        vacancyTable.setItems(vacancyList);
        btnAddVacancy.setOnAction(event -> handleAddVacancy());
        btnEditVacancy.setOnAction(event -> handleEditVacancy());
        btnDeleteVacancy.setOnAction(event -> handleDeleteVacancy());
    }


    @FXML
    private void handleAddVacancy() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add_vacancy.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить вакансию");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(vacancyTable.getScene().getWindow());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddVacancyController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVacanciesController(this);

            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.showAlert("Ошибка", "Не удалось открыть окно добавления вакансии.");
        }
    }

    public void addVacancyToTable(Vacancy vacancy) {
        vacancyList.add(vacancy);
    }

    private void handleEditVacancy() {
        Vacancy selectedVacancy = vacancyTable.getSelectionModel().getSelectedItem();
        if (selectedVacancy != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("edit_vacancy.fxml"));
                Scene scene = new Scene(loader.load());

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Редактировать вакансию");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(vacancyTable.getScene().getWindow());
                dialogStage.setScene(scene);

                EditVacancyController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setVacancy(selectedVacancy);
                controller.setVacanciesController(this);

                dialogStage.showAndWait();
            } catch (Exception e) {
                AlertUtils.showAlert("Ошибка", "Не удалось открыть окно редактирования вакансии.");
            }
        } else {
            AlertUtils.showAlert("Выберите вакансию", "Пожалуйста, выберите вакансию для редактирования.");
        }
    }

    public void updateVacancyInTable(Vacancy updatedVacancy) {
        int selectedIndex = vacancyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            vacancyList.set(selectedIndex, updatedVacancy);
        }
    }


    private void handleDeleteVacancy() {
        Vacancy selectedVacancy = vacancyTable.getSelectionModel().getSelectedItem();
        if (selectedVacancy != null) {
            VacancyRepo.deleteVacancy(selectedVacancy.getId());
            vacancyList.remove(selectedVacancy);
        } else {
            AlertUtils.showAlert("Выберите вакансию", "Пожалуйста, выберите вакансию для удаления.");
        }
    }


}
