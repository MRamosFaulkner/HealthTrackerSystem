package com.example.healthtracker;

import java.util.Scanner;



public class HealthTrackerSystem {
    public static void main(String[] args) {
        System.out.printf("\nHello and Welcome!\n\n");


        UserManagement system = new UserManagement();
        Scanner scanner = new Scanner(System.in);

        DataStored dataStored = new DataStored("C:\\Users\\admin\\Documents\\CTAC\\Assignments\\FullStack103\\JavaFinalProject\\userData.txt");
        User currentUser = new User(dataStored);

        boolean running = true;
        while (running) {
            System.out.println("1. Create a new user");
            System.out.println("2. Log in");
            System.out.println("3. Quit");
            System.out.print("\nEnter your choice: ");
            int logInChoice = scanner.nextInt();
            scanner.nextLine();

            switch (logInChoice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    system.createUser(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    system.login(loginUsername, loginPassword);

                    running = false;
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        if (system.isLoggedIn()) {

            while (system.isLoggedIn()) {
                System.out.println("1. Add food entry");
                System.out.println("2. Add exercise activity");
                System.out.println("3. Log hours of sleep");
                System.out.println("4. Display caloric intake for a specific date");
                System.out.println("5. Display exercise activities for a specific date");
                System.out.println("6. Display sleep records for a specific date");
                System.out.println("7. Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter meal type (e.g., Breakfast, Lunch, Dinner, Snack): ");
                        String mealType = scanner.nextLine();
                        System.out.print("Enter food item: ");
                        String foodItem = scanner.nextLine();
                        System.out.print("Enter calories: ");
                        int calories = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter date (yyyy-mm-dd): ");
                        String date = scanner.nextLine();
                        currentUser.addFood(mealType, foodItem, calories, date);
//                        // Read existing food entries
//                        List<String> foodEntries = currentUser.getFoodEntries();
//                        // Append the new food entry
//                        foodEntries.add(foodEntry);
//                        // Write all food entries back to the data file
//                        currentUser.writeFoodEntries(food
                        currentUser.writeData();
                        break;
                    case 2:
                        System.out.print("Enter exercise type: ");
                        String exerciseType = scanner.nextLine();
                        System.out.print("Enter duration in minutes: ");
                        int duration = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter estimated calories burned: ");
                        int caloriesBurned = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter date (yyyy-mm-dd): ");
                        String exerciseDate = scanner.nextLine();
                        currentUser.addExercise(exerciseType, duration, caloriesBurned, exerciseDate);
                        currentUser.writeData();
                        break;
                    case 3:
                        System.out.print("Enter sleep start time (hh:mm): ");
                        String sleepStart = scanner.nextLine();
                        System.out.print("Enter sleep end time (hh:mm): ");
                        String sleepEnd = scanner.nextLine();
                        currentUser.addSleepHours(sleepStart, sleepEnd);
                        currentUser.writeData();
                        break;
                    case 4:
                        System.out.print("Enter date to display caloric intake (yyyy-mm-dd): ");
                        String displayDate = scanner.nextLine();
                        currentUser.displayCalorieIntake(displayDate);
                        break;
                    case 5:
                        System.out.print("Enter date to display exercise activities (yyyy-mm-dd): ");
                        String exerciseDisplayDate = scanner.nextLine();
                        currentUser.displayExerciseActivities(exerciseDisplayDate);
                        break;
                    case 6:
                        System.out.print("Enter date to display sleep records (yyyy-mm-dd): ");
                        String sleepDisplayDate = scanner.nextLine();
                        currentUser.displaySleepHours(sleepDisplayDate);
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                if (!running) {
                    break;
                }
            }
        }
    }
}

