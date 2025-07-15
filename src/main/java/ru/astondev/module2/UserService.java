package ru.astondev.module2;

import org.hibernate.SessionFactory;

public class UserService {
    private final UserController userController;

    public UserService() {
        this.userController = new UserController();
    }
    public UserService(UserController userController) {
        this.userController = userController;
    }
    public void createUser(User user) {
        userController.createUser(user);
    }

    public User getUserById(Long id) {
        return userController.getUserById(id);
    }

    public void updateUser(User user) {
        userController.updateUser(user);
    }

    public void deleteUser(Long id) {
        userController.deleteUser(id);
    }
}