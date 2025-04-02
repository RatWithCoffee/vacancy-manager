package vacancy_manager.storage;

import vacancy_manager.models.User;

public class UserStorage {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserStorage.user = user;
    }

    public static void deleteUser() {
        user = null;
    }
}
