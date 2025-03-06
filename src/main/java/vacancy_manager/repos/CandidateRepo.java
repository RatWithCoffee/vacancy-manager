package vacancy_manager.repos;

import vacancy_manager.models.Candidate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateRepo {

    // Метод для получения списка кандидатов по ID вакансии
    public static List<Candidate> getCandidatesByVacancyId(int vacancyId) {
        String sql = "SELECT id, first_name, last_name, patronymic, email, phone, cv " +
                "FROM candidate WHERE vacancy_id = ?";

        List<Candidate> candidates = new ArrayList<>();

        try (Connection conn = DbManager.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Устанавливаем ID вакансии в запрос
                stmt.setInt(1, vacancyId);

                // Выполняем запрос
                try (ResultSet rs = stmt.executeQuery()) {
                    // Обрабатываем результаты запроса
                    while (rs.next()) {
                        Candidate candidate = new Candidate();
                        candidate.setId(rs.getInt("id"));
                        candidate.setFirstName(rs.getString("first_name"));
                        candidate.setLastName(rs.getString("last_name"));
                        candidate.setPatronymic(rs.getString("patronymic"));
                        candidate.setEmail(rs.getString("email"));
                        candidate.setPhone(rs.getString("phone"));
                        candidate.setCv(rs.getString("cv"));
                        candidate.setVacancyId(vacancyId);  // Устанавливаем ID вакансии

                        candidates.add(candidate);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidates;  // Возвращаем список кандидатов
    }
}
