package vacancy_manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vacancy_manager.managers.ReposManager;
import vacancy_manager.models.User;
import vacancy_manager.storage.UserStorage;

import java.io.IOException;
import java.rmi.RemoteException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private Label errorLabel;

    private final Stage stage;

    public LoginController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        // Инициализация контроллера
        loginButton.setOnAction(event -> handleLogin());
        errorLabel.setText("");
    }

    public void showLoginPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login_page.fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Логин и пароль обязательны для заполнения");
            return;
        }

        try {
            User user = new User(username, password);
            User loginUser = ReposManager.getLoginRepo().login(user);
            UserStorage.setUser(loginUser);
            if (loginUser != null) {
                errorLabel.setText(""); // Очищаем сообщение об ошибке при успешном входе
                MainController.showMainMenu(stage);
            } else {
                errorLabel.setText("Неверный логин или пароль");
                // Можно добавить очистку поля пароля для безопасности
                passwordField.clear();
            }
        } catch (RemoteException e) {
            errorLabel.setText("Ошибка соединения с сервером");
            throw new RuntimeException(e);
        }
    }
}