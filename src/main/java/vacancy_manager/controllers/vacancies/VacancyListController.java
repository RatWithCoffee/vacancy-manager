package vacancy_manager.controllers.vacancies;

import javafx.beans.property.SimpleStringProperty;
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
import vacancy_manager.repos.ReposManager;
import vacancy_manager.controllers.MainController;
import vacancy_manager.controllers.candidates.CandidateListController;
import vacancy_manager.models.Candidate;
import vacancy_manager.models.Vacancy;
import vacancy_manager.utils.AlertUtils;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class VacancyListController {

    @FXML
    public Button btnViewCandidates;
    public Button btnMainMenu;
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

    private Stage stage;

    @FXML
    public void initialize() throws RemoteException {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colManager.setCellValueFactory(cellData -> {
            String firstName = cellData.getValue().getManagerName();
            System.out.println(firstName != null);
            System.out.println(firstName);
            return new SimpleStringProperty(firstName != null ? firstName : "");
        });



        vacancyList = FXCollections.observableList(ReposManager.getVacancyRepo().getAllVacancies());

        vacancyTable.setItems(vacancyList);
        btnAddVacancy.setOnAction(event -> handleAddVacancy());
        btnEditVacancy.setOnAction(event -> handleEditVacancy());
        btnDeleteVacancy.setOnAction(event -> {
            try {
                handleDeleteVacancy();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        btnViewCandidates.setOnAction(event -> {
            try {
                handleViewCandidates();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        btnMainMenu.setOnAction(event -> openMainMenu());
    }

    @FXML
    private void openMainMenu() {
        try {
            MainController.showMainMenu(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setStage(Stage stage) {
        this.stage = stage;
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


    private void handleDeleteVacancy() throws RemoteException {
        Vacancy selectedVacancy = vacancyTable.getSelectionModel().getSelectedItem();
        if (selectedVacancy != null) {
            ReposManager.getVacancyRepo().deleteVacancy(selectedVacancy.getId());
            vacancyList.remove(selectedVacancy);
        } else {
            AlertUtils.showAlert("Выберите вакансию", "Пожалуйста, выберите вакансию для удаления.");
        }
    }

    @FXML
    private void handleViewCandidates() throws RemoteException {
        Vacancy selectedVacancy = vacancyTable.getSelectionModel().getSelectedItem();

        if (selectedVacancy == null) {
            AlertUtils.showAlert("", "Пожалуйста, выберите вакансию.");
            return;
        }

        // Fetch the list of candidates for the selected vacancy
        List<Candidate> candidates = ReposManager.getCandidateRepo().getCandidatesByVacancyId(selectedVacancy.getId());

        if (candidates.isEmpty()) {
            AlertUtils.showAlert("", "Для этой вакансии нет кандидатов.");
            return;
        }

        // Open a new window showing the list of candidates
        showCandidatesWindow(candidates, selectedVacancy);
    }

    private void showCandidatesWindow(List<Candidate> candidates, Vacancy selectedVacancy) {
        try {
            // Создаем новое окно
            Stage candidateStage = new Stage();
            FXMLLoader loader = new FXMLLoader(CandidateListController.class.getResource("candidate_list.fxml"));

            // Загружаем FXML файл
            Scene scene = new Scene(loader.load());

            // Получаем контроллер для окна кандидатов
            CandidateListController candidateListController = loader.getController();

            // Передаем список кандидатов в контроллер
            candidateListController.setCandidates(candidates);
            candidateListController.setVacancyInfo(selectedVacancy);
//            candidateListController.setVacancyTitle(selectedVacancy.getTitle());

            // Отображаем окно
            candidateStage.setTitle("Кандидаты для вакансии: " + selectedVacancy.getTitle());
            candidateStage.setScene(scene);
            candidateStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            AlertUtils.showAlert("Ошибка", "Не удалось открыть окно кандидатов.");
        }
    }

}
