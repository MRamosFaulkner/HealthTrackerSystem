package com.example.healthtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DataRecorder {
    private static final String CALORIE_INTAKE_FILE = "calorie_intake.txt";
    private static final String EXERCISE_FILE = "exercise.txt";
    private static final String SLEEP_FILE = "sleep.txt";

    private static void writeDataToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(data);
            writer.newLine();
            System.out.println("Data recorded successfully.");
        } catch (IOException e) {
            System.out.println("Error recording data: " + e.getMessage());
        }
    }

    public static void recordSleep(String username) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter the time you went to sleep (HH:mm): ");
        String sleepTimeStr = scanner.nextLine();

        System.out.print("Enter the time you woke up (HH:mm): ");
        String wakeupTimeStr = scanner.nextLine();

        writeDataToFile(SLEEP_FILE, username + "," + date + "," + sleepTimeStr + "," + wakeupTimeStr);
    }

    public static void recordCalorieIntake(String username) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter the food item: ");
        String foodItem = scanner.nextLine();

        System.out.print("Enter the caloric value: ");
        int calories = scanner.nextInt();
        scanner.nextLine();

        writeDataToFile(CALORIE_INTAKE_FILE, username + "," + date + "," + foodItem + "," + calories);
    }

    public static void recordExercise(String username) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter the type of exercise: ");
        String exerciseType = scanner.nextLine();

        System.out.print("Enter the duration in minutes: ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        System.out.print("Enter the estimated calories burned: ");
        int caloriesBurned = scanner.nextInt();
        scanner.nextLine();

        writeDataToFile(EXERCISE_FILE, username + "," + date + "," + exerciseType + "," + duration + "," + caloriesBurned);
    }

}

