package vacancy_manager.controllers.candidates;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import repos.ReposManager;
import vacancy_manager.models.Candidate;
import vacancy_manager.models.Vacancy;
import vacancy_manager.utils.AlertUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandidateListController {

    @FXML private TableView<Candidate> candidateTable;
    @FXML private TableColumn<Candidate, String> colFirstName;
    @FXML private TableColumn<Candidate, String> colLastName;
    @FXML private TableColumn<Candidate, String> colEmail;
    @FXML private TableColumn<Candidate, String> colPhone;
    @FXML private Label vacancyTitleLabel;

    private int vacancyId; // Store vacancy ID for saving candidates


    // Method to set vacancy information
    public void setVacancyInfo(Vacancy vacancy) {
        this.vacancyId = vacancy.getId();
        vacancyTitleLabel.setText(vacancy.getTitle());
    }

    // Method to set candidates in table
    public void setCandidates(List<Candidate> candidates) {
        candidateTable.getItems().setAll(candidates);
    }

    @FXML
    private void handleImportFromCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите CSV файл");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        File file = fileChooser.showOpenDialog(candidateTable.getScene().getWindow());
        if (file != null) {
            try {
                List<Candidate> importedCandidates = parseCsvFile(file);
                int addedCandidatesBatch = ReposManager.getCandidateRepo().addCandidatesBatch(importedCandidates, vacancyId );
                if (addedCandidatesBatch == importedCandidates.size()) {
                    candidateTable.getItems().addAll(importedCandidates);
                    AlertUtils.showAlert("Импорт завершен",
                            "Успешно импортировано " + importedCandidates.size() + " кандидатов");

                } else {
                    AlertUtils.showAlert("Ошибка импорта",
                            "Не удалось добавить в бд");

                }


                 } catch (IOException e) {
                AlertUtils.showAlert("Ошибка импорта",
                        "Не удалось прочитать файл: " + e.getMessage());
            } catch (Exception e) {
                AlertUtils.showAlert("Ошибка формата",
                        "Некорректный формат CSV файла: " + e.getMessage());
            }
        }
    }

    private List<Candidate> parseCsvFile(File file) throws IOException {
        List<Candidate> candidates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }

                String[] values = line.split(",");
                if (values.length >= 4) {
                    Candidate candidate = new Candidate();
                    candidate.setFirstName(values[0].trim());
                    candidate.setLastName(values[1].trim());
                    candidate.setEmail(values[2].trim());
                    candidate.setPhone(values[3].trim());
                    candidate.setVacancyId(vacancyId); // Set the vacancy ID

                    candidates.add(candidate);


                }
            }
        }


        return candidates;
    }

}