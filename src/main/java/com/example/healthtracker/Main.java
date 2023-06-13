package com.example.healthtracker;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManagement userManagement = new UserManagement();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int choice = getUserInputAsInt();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("1. Create User");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserInputAsInt() {
        return scanner.nextInt();
    }

    private static String getUserInputAsString() {
        scanner.nextLine(); // Consume the newline character
        return scanner.nextLine();
    }

    private static void createUser() {
        System.out.print("Enter username: ");
        String username = getUserInputAsString();
        userManagement.createUser(username);
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = getUserInputAsString();
        User user = userManagement.loginUser(username);

        if (user != null) {
            boolean loggedIn = true;

            while (loggedIn) {
                printLoggedInMenu();
                int choice = getUserInputAsInt();

                switch (choice) {
                    case 1:
                        DataRecorder.recordCalorieIntake(user.getUser());
                        break;
                    case 2:
                        DataRecorder.recordExercise(user.getUser());
                        break;
                    case 3:
                        DataRecorder.recordSleep(user.getUser());
                        break;
                    case 4:
                        performHealthDataAnalysis(user.getUser());
                        break;
                    case 5:
                        loggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

    private static void printLoggedInMenu() {
        System.out.println("1. Enter Daily Calorie Intake");
        System.out.println("2. Enter Exercise");
        System.out.println("3. Enter Sleep");
        System.out.println("4. Perform Health Data Analysis");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }

    private static void performHealthDataAnalysis(String username) {
        printHealthDataAnalysisMenu();
        int choice = getUserInputAsInt();

        switch (choice) {
            case 1:
                System.out.print("Enter date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(getUserInputAsString());
                DataAnalysis.calculateDailyCaloricBalance(username, date);
                break;
            case 2:
                System.out.print("Enter start date (YYYY-MM-DD): ");
                LocalDate startDate = LocalDate.parse(getUserInputAsString());
                System.out.print("Enter end date (YYYY-MM-DD): ");
                LocalDate endDate = LocalDate.parse(getUserInputAsString());
                DataAnalysis.calculateAverageSleepDuration(username, startDate, endDate);
                break;
            case 3:
                DataAnalysis.displayExerciseLog(username);
                break;
            case 4:
                System.out.print("Enter start date (YYYY-MM-DD): ");
                startDate = LocalDate.parse(getUserInputAsString());
                System.out.print("Enter end date (YYYY-MM-DD): ");
                endDate = LocalDate.parse(getUserInputAsString());
                DataAnalysis.generateHealthSummary(username, startDate, endDate);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void printHealthDataAnalysisMenu() {
        System.out.println("1. Calculate Daily Caloric Balance");
        System.out.println("2. Perform Sleep Analysis");
        System.out.println("3. Display Exercise Log");
        System.out.println("4. Generate Health Summary");
        System.out.print("Enter your choice: ");
    }
}
