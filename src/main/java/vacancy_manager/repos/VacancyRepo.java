package vacancy_manager.repos;


import vacancy_manager.models.Manager;
import vacancy_manager.models.Vacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VacancyRepo {

    // Method to get all vacancies
    public static List<Vacancy> getAllVacancies() {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection conn = DbManager.getConnection()) {
            String sql = "SELECT vacancy.id, title, description, salary, manager_id, first_name, last_name  FROM vacancy JOIN manager ON vacancy.manager_id = manager.id";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Vacancy vacancy = mapResultSetToVacancy(rs);
                    vacancies.add(vacancy);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return vacancies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancies;
    }


    // Method to add a new vacancy
    public static boolean addVacancy(Vacancy vacancy) {
        String sql = "INSERT INTO vacancy (title, description, salary, manager_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbManager.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, vacancy.getTitle());
                stmt.setString(2, vacancy.getDescription());
                stmt.setDouble(3, vacancy.getSalary());
                stmt.setInt(4, vacancy.getManagerId());  // This could be null if no manager assigned

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // Return true if insert was successful
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Return false if there was an error
    }

    // Method to update an existing vacancy
    public static boolean updateVacancy(Vacancy vacancy) {
        String sql = "UPDATE vacancy SET title = ?, description = ?, salary = ?, manager_id = ? WHERE id = ?";
        try (Connection conn = DbManager.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, vacancy.getTitle());
                stmt.setString(2, vacancy.getDescription());
                stmt.setDouble(3, vacancy.getSalary());
                stmt.setInt(4, vacancy.getManagerId());
                stmt.setInt(5, vacancy.getId());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // Return true if update was successful
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Return false if there was an error
    }

    // Method to delete a vacancy by ID
    public static boolean deleteVacancy(int id) {
        String sql = "DELETE FROM vacancy WHERE id = ?";
        try (Connection conn = DbManager.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // Return true if delete was successful
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if there was an error
    }

    // Helper method to map a ResultSet row to a Vacancy object
    private static Vacancy mapResultSetToVacancy(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        double salary = rs.getDouble("salary");
        int managerId = rs.getInt("manager_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");


        return new Vacancy(id, title, description, salary, managerId, firstName + " " + lastName);
    }


}
