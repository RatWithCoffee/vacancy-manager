package vacancy_manager.controllers.candidates;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import vacancy_manager.models.Candidate;
import java.util.List;

public class CandidateListController {

    @FXML
    private TableView<Candidate> candidateTable;

    @FXML
    private TableColumn<Candidate, String> colFirstName;

    @FXML
    private TableColumn<Candidate, String> colLastName;

    @FXML
    private TableColumn<Candidate, String> colEmail;

    @FXML
    private TableColumn<Candidate, String> colPhone;

    @FXML
    private Label vacancyTitleLabel;

    // Метод для установки списка кандидатов в таблицу
    public void setCandidates(List<Candidate> candidates) {
        candidateTable.getItems().setAll(candidates);
    }

    // Метод для установки заголовка вакансии
    public void setVacancyTitle(String title) {
        vacancyTitleLabel.setText("Кандидаты для вакансии: " + title);
    }
}
