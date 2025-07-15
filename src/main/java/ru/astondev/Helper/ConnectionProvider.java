package ru.astondev.Helper;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.System.exit;

public class ConnectionProvider {
//    private final String url;
//    private final String username;
//    private final String password;
//
//    public ConnectionProvider(String url, String username, String password) {
//        this.url = url;
//        this.username = username;
//        this.password = password;
//    }
//
//    Connection getConnection() {
//        try {
//            return DriverManager.getConnection(url, username, password);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void checkProperties() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        String url = config.getProperty("hibernate.connection.url");
        String user = config.getProperty("hibernate.connection.username");
        String password = config.getProperty("hibernate.connection.password");

        if (url == null || url.isBlank() ||
                user == null || user.isBlank() ||
                password == null || password.isBlank()) {
            System.err.println("\n\n !!!!!\nMissing database configuration properties" +
                    " please enter url, username and password values in " +
                    "\n../resources/hibernate.cfg.xml or ../resources/hibernate.properties");
            exit(1);
        }
    }
}
