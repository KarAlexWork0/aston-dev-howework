package ru.astondev;

import ru.astondev.module2.Module2Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        int input = 0;

        while (!flag) {
            System.out.println("Choose module/homework (module 1 is on astondev.ru for now):");
            System.out.println("2: - Module 2 - Hibernate User Data Access Object " +
                    "3: - Module 3 - " +
                    "\n9: exit");

            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nWrong input, try again:");
            }

            switch (input) {
//                case 1 -> Module1Main.main(args);
                case 2 -> Module2Main.main(args);
                case 9 -> flag = true;
                default -> System.out.println("\nWrong input, try again:");
            }
        }


    }
}
