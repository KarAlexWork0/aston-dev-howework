package ru.astondev.module2;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.astondev.Helper.HibernateUtil;

import java.util.logging.Logger;

public class UserController implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private final SessionFactory sessionFactory;

    public UserController() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    // inject mock SessionFactory
    public UserController(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            logger.info("Created user: " + user);
            System.out.println("Created user: " + user);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.severe("Error creating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public User getUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.find(User.class, id);
            if (user != null) {
                logger.fine("Retrieved user: " + user);
                System.out.println("Retrieved user: " + user);
            } else {
                logger.warning("User not found with ID: " + id);
                System.out.println("User not found with ID: " + id);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            System.out.println("Updated user: " + user);
            logger.info("Updated user: " + user);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.severe("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
                System.out.println("Deleted user with ID: " + id);
                logger.info("Deleted user with ID: " + id);
            } else {
                logger.warning("User not found with ID: " + id);
                System.out.println("User not found with ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.severe("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }
}