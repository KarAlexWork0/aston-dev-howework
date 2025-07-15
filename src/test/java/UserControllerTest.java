
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.astondev.Helper.HibernateUtil;
import ru.astondev.module2.User;
import ru.astondev.module2.UserController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    UserController userController = new UserController();

    @BeforeAll
    static void beforeAll() {
        postgres.start();

        System.setProperty("DB_URL", postgres.getJdbcUrl());
        System.setProperty("DB_USER", postgres.getUsername());
        System.setProperty("DB_PASSWORD", postgres.getPassword());

        HibernateUtil.getSessionFactory();// Force init Hibernate
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
        HibernateUtil.shutdown();
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateUsers() {
        userController.createUser(new User("George" , "1@gmail.c", 22));
        userController.createUser(new User("John", "12@gmail.c", 33));

        List<User> users = new ArrayList<>();
        users.add(userController.getUserById(0L));
        users.add(userController.getUserById(1L));
        assertEquals(2, users.size());
    }

    @Test
    void shouldUpdateUsers() {
        userController.createUser(new User("G" , "1", 11));
        User user = userController.getUserById(3L);

        user.setAge(33);
        user.setUsername("George1");
        user.setEmail("john1@gmail.c");

        userController.updateUser(user);

        assertEquals("George1", userController.getUserById(3L).getUsername());
    }

    @Test
    void shouldDeleteUsers() {
        userController.createUser(new User("G" , "1", 11));
        userController.createUser(new User("J", "2", 22));

        userController.deleteUser(0L);
        userController.deleteUser(1L);

        List<User> users = new ArrayList<>();

        users.add(userController.getUserById(0L));
        users.add(userController.getUserById(1L));
        for (User user : users) {
            assertNull(user);
        }
    }
}