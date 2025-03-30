package vacancy_manager.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import vacancy_manager.repos.VacancyRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class VacancyStatisticsController {
    @FXML private BarChart<String, Number> salaryBarChart;
    @FXML private PieChart managerPieChart;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadData() {
        try {
            loadSalaryData();
            loadManagerDistributionData();
        } catch (SQLException e) {
            e.printStackTrace();
            // You might want to show an error dialog here
        }
    }

    private void loadSalaryData() throws SQLException {
        // Initialize axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Вакансия");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Зарплата (руб)");

        salaryBarChart.setTitle("Зарплаты по вакансиям");
        salaryBarChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        List<Map<String, Object>> vacancies = VacancyRepo.getVacanciesWithSalaries();
        System.out.println(vacancies.size());
        for (Map<String, Object> vacancy : vacancies) {
            String title = (String) vacancy.get("title");
            double salary = (double) vacancy.get("salary");
            series.getData().add(new XYChart.Data<>(title, salary));
        }

        salaryBarChart.getData().add(series);
    }

    private void loadManagerDistributionData() throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        Map<String, Integer> managerVacancies = VacancyRepo.getNumberOfCandidatesToVac();
        for (Map.Entry<String, Integer> entry : managerVacancies.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        managerPieChart.setData(pieChartData);
        managerPieChart.setTitle("Кандидаты по вакансиям");
    }

    @FXML
    private void openMainMenu() {
        try {
            MainController.showMainMenu(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}