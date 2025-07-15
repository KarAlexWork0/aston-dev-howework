package ru.astondev.module2;

import ru.astondev.Helper.ConnectionProvider;

import java.util.Scanner;

import static java.lang.System.exit;

public class Module2Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {

        ConnectionProvider.checkProperties();
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            String in = scanner.nextLine();
            switch (in) {
                case "1" -> createUser();
                case "2" -> getUserById();
                case "3" -> updateUser();
                case "4" -> deleteUser();
                case "5" -> exit = true;
                case "6" -> exit(0);
                default -> System.out.println("Invalid value.Try again");
            }
        }
    }

    private static void createUser() {
        boolean flag = false;
        String[] userData = null;
        while (!flag) {
            System.out.println("Enter name/email/age with \" \" (space) between them (Karl a@gmail.com 20)\n or type 'back' to return to main menu: ");
            String input = scanner.nextLine();
            if("back".equals(input)) break;

            userData = input.split("\\s+");
            if (userData.length != 3) {
                System.out.println("Invalid input, try again!: ");
                continue;
            }
            flag = true;
        }
        if(!flag) return;//if 'back' (flag not reached after break)

        String name = userData[0], email = userData[1];
        int  age = Integer.parseInt(userData[2]);
        User user = new User(name, email, age);

        user.setUsername(name);
        user.setEmail(email);
        user.setAge(age);

        userService.createUser(user);
    }

    private static void getUserById() {
        boolean flag = false;

        while (!flag) {
            System.out.println("Enter Id to retrieve user or 'back' to go back: ");
            String input = scanner.nextLine();
            if("back".equals(input)) break;

            try {
                long userId = Long.parseLong(input);
                User user = userService.getUserById(userId);
                System.out.println("Requested user " + user.toString());
                flag = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input, try again!: ");
            }
            catch (Exception e) {
                System.out.println("User was not found, try again!: ");
            }
        }
    }

    private static void updateUser() {
        boolean flag = false;
        String[] userData = null;
        while (!flag) {
            System.out.println("Enter user ID/name/email/age with \" \" (space) between them (1 Karl a@gmail.com 20) to update \n or or type 'back' to return to main menu: ");
            String input = scanner.nextLine();
            if("back".equals(input)) break;

            userData = input.split("\\s+");
            if (userData.length != 4) {
                System.out.println("invalid input, try again!: ");
                continue;
            }
            flag = true;
        }
        if(!flag) return;//if 'back' (flag not reached after break)

        long userId = Long.parseLong(userData[0]);
        String name = userData[1], email = userData[2];
        int  age = Integer.parseInt(userData[3]);

        User user = userService.getUserById(userId);

        user.setUsername(name);
        user.setEmail(email);
        user.setAge(age);

        userService.updateUser(user);
    }

    private static void deleteUser() {
        boolean flag = false;
        while (!flag) {
            System.out.println("Enter Id to delete user \n or or type 'back' to return to main menu: ");
            String input = scanner.nextLine();
            if("back".equals(input)) break;

            try {
                long userId = Long.parseLong(input);
                userService.deleteUser(userId);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input, try again!: ");
                continue;
            }
            catch (Exception e) {
                System.out.println("User not found, try again!: ");
            }
            flag = true;
        }
    }

    private static void printMainMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Create User");
        System.out.println("2. Get user by Id");
        System.out.println("3. Update user");
        System.out.println("4. Delete user");
        System.out.println("5. back\n");
        System.out.println("6. exit\n");
        System.out.println("Enter value: ");
    }
}