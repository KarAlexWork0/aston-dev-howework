package ru.astondev.module2;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private SessionFactory mockSessionFactory;
    private Session mockSession;
    private Transaction mockTransaction;
    private UserController mockController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockSessionFactory = mock(SessionFactory.class);
        mockSession = mock(Session.class);
        mockTransaction = mock(Transaction.class);

        when(mockSessionFactory.openSession()).thenReturn(mockSession);
        when(mockSession.beginTransaction()).thenReturn(mockTransaction);

        mockController = new UserController(mockSessionFactory); // Inject mock SF
        userService = new UserService(mockController);
    }

    @Test
    void testCreateUser_shouldCallHibernatePersist() {
        // Arrange
        User user = new User("Alice", "alice@example.com", 30);

        // Act
        userService.createUser(user);

        // Assert
        verify(mockSession).persist(user);
        verify(mockTransaction).commit();
    }

    @Test
    void testGetUserById_shouldReturnUserFromDB() {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User("Bob", "bob@example.com", 25);
        when(mockSession.find(User.class, userId)).thenReturn(expectedUser);

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedUser.getUsername(), result.getUsername());
        verify(mockSession).find(User.class, userId);
    }

    @Test
    void testGetUserById_shouldReturnNullIfNotFound() {
        // Arrange
        Long userId = 999L;
        when(mockSession.find(User.class, userId)).thenReturn(null);

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertNull(result);
    }

    @Test
    void testUpdateUser_shouldCallMergeAndCommit() {
        // Arrange
        User user = new User("Charlie", "charlie@example.com", 40);

        // Act
        userService.updateUser(user);

        // Assert
        verify(mockSession).merge(user);
        verify(mockTransaction).commit();
    }

    @Test
    void testDeleteUser_shouldRemoveUserAndCommit() {
        // Arrange
        Long userId = 1L;
        User mockUser = new User("Diana", "diana@example.com", 35);
        when(mockSession.find(User.class, userId)).thenReturn(mockUser);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(mockSession).remove(mockUser);
        verify(mockTransaction).commit();
    }
}