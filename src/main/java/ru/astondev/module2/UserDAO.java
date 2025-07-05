package ru.astondev.module2;

public interface UserDAO {
    void createUser(User user);
    User getUserById(Long id);
    void updateUser(User user);
    void deleteUser(Long id);
}
