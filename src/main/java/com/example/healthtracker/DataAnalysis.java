package com.example.healthtracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class DataAnalysis {
    private static final String CALORIE_INTAKE_FILE = "calorie_intake.txt";
    private static final String EXERCISE_FILE = "exercise.txt";
    private static final String SLEEP_FILE = "sleep.txt";

    public static void calculateDailyCaloricBalance(String username, LocalDate date) {
        int totalCaloriesConsumed = calculateTotalCaloriesConsumed(username, date, date);
        int totalCaloriesBurned = calculateTotalCaloriesBurned(username, date, date);
        int dailyCaloricBalance = totalCaloriesConsumed - totalCaloriesBurned;
        System.out.println("Daily Caloric Balance for " + date + ": " + dailyCaloricBalance);
    }

    public static void calculateAverageSleepDuration(String username, LocalDate startDate, LocalDate endDate) {
        int totalSleepDuration = 0;
        int numOfDays = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(SLEEP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    LocalDate entryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    if (entryDate.isAfter(startDate.minusDays(1)) && entryDate.isBefore(endDate.plusDays(1))) {
                        int sleepDuration = calculateSleepDuration(parts[2], parts[3]);
                        totalSleepDuration += sleepDuration;
                        numOfDays++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading sleep data: " + e.getMessage());
        }

        if (numOfDays > 0) {
            int averageSleepDuration = totalSleepDuration / numOfDays;
            System.out.println("Average Sleep Duration from " + startDate + " to " + endDate + ": " + averageSleepDuration + " hours");
            displayDaysWithSleepDurationBelowAverage(username, startDate, endDate, averageSleepDuration);
        } else {
            System.out.println("No sleep data available for the specified period.");
        }
    }

    private static int calculateTotalCaloriesConsumed(String username, LocalDate startDate, LocalDate endDate) {
        int totalCaloriesConsumed = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(CALORIE_INTAKE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    LocalDate entryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    if (entryDate.isAfter(startDate.minusDays(1)) && entryDate.isBefore(endDate.plusDays(1))) {
                        totalCaloriesConsumed += Integer.parseInt(parts[3]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading calorie intake data: " + e.getMessage());
        }

        return totalCaloriesConsumed;
    }
    private static int calculateTotalCaloriesBurned(String username, LocalDate startDate, LocalDate endDate) {
        int totalCaloriesBurned = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(EXERCISE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    LocalDate entryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    if (entryDate.isAfter(startDate.minusDays(1)) && entryDate.isBefore(endDate.plusDays(1))) {
                        totalCaloriesBurned += Integer.parseInt(parts[4]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading exercise data: " + e.getMessage());
        }

        return totalCaloriesBurned;
    }
    private static void displayDaysWithSleepDurationBelowAverage(String username, LocalDate startDate, LocalDate endDate, int averageSleepDuration) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SLEEP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    LocalDate entryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    if (entryDate.isAfter(startDate.minusDays(1)) && entryDate.isBefore(endDate.plusDays(1))) {
                        int sleepDuration = calculateSleepDuration(parts[2], parts[3]);
                        if (sleepDuration < averageSleepDuration) {
                            System.out.println("Day with Sleep Duration below average: " + entryDate);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading sleep data: " + e.getMessage());
        }
    }

    private static int calculateSleepDuration(String sleepTimeStr, String wakeupTimeStr) {
        LocalTime sleepTime = LocalTime.parse(sleepTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime wakeupTime = LocalTime.parse(wakeupTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

        if (wakeupTime.isAfter(sleepTime)) {
            return (int) Duration.between(sleepTime, wakeupTime).toHours();
        } else if (wakeupTime.isBefore(sleepTime)) {
            LocalTime adjustedWakeupTime = wakeupTime.plus(Duration.ofDays(1));
            Duration duration = Duration.between(sleepTime, adjustedWakeupTime);
            if (duration.isNegative()) {
                duration = duration.plus(Duration.ofDays(1));
            }
            return (int) duration.toHours();
        } else {
            return 0;
        }
    }

    public static void displayExerciseLog(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(EXERCISE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    String exerciseType = parts[2];
                    int duration = Integer.parseInt(parts[3]);
                    int caloriesBurned = Integer.parseInt(parts[4]);
                    System.out.println("Exercise: " + exerciseType + ", Duration: " + duration + " minutes, Calories Burned: " + caloriesBurned);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading exercise data: " + e.getMessage());
        }
    }

    private static Map<String, Integer> calculateExerciseSummary(String username, LocalDate startDate, LocalDate endDate) {
        Map<String, Integer> exerciseSummary = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(EXERCISE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    LocalDate entryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    if (entryDate.isAfter(startDate.minusDays(1)) && entryDate.isBefore(endDate.plusDays(1))) {
                        String exerciseType = parts[2];
                        int caloriesBurned = Integer.parseInt(parts[4]);
                        exerciseSummary.put(exerciseType, exerciseSummary.getOrDefault(exerciseType, 0) + caloriesBurned);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading exercise data: " + e.getMessage());
        }

        return exerciseSummary;
    }

    private static int calculateNumOfDaysWithSleepData(String username, LocalDate startDate, LocalDate endDate) {
        int numOfDays = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(SLEEP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    LocalDate entryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    if (entryDate.isAfter(startDate.minusDays(1)) && entryDate.isBefore(endDate.plusDays(1))) {
                        numOfDays++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading sleep data: " + e.getMessage());
        }

        return numOfDays;
    }

    private static int calculateTotalSleepDuration(String username, LocalDate startDate, LocalDate endDate) {
        int totalSleepDuration = 0;
        int numOfDays = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(SLEEP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    LocalDate entryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    if (entryDate.isAfter(startDate.minusDays(1)) && entryDate.isBefore(endDate.plusDays(1))) {
                        int sleepDuration = calculateSleepDuration(parts[2], parts[3]);
                        totalSleepDuration += sleepDuration;
                        numOfDays++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading sleep data: " + e.getMessage());
        }

        return totalSleepDuration;
    }


    public static void generateHealthSummary(String username, LocalDate startDate, LocalDate endDate) {
        int totalCaloriesConsumed = calculateTotalCaloriesConsumed(username, startDate, endDate);
        int totalCaloriesBurned = calculateTotalCaloriesBurned(username, startDate, endDate);
        int totalSleepDuration = calculateTotalSleepDuration(username, startDate, endDate);
        int numOfDays = calculateNumOfDaysWithSleepData(username, startDate, endDate);
        Map<String, Integer> exerciseSummary = calculateExerciseSummary(username, startDate, endDate);

        if (numOfDays > 0) {
            int averageSleepDuration = totalSleepDuration / numOfDays;
            System.out.println("Health Summary from " + startDate + " to " + endDate + " for " + username);
            System.out.println("Total Calories Consumed: " + totalCaloriesConsumed);
            System.out.println("Total Calories Burned: " + totalCaloriesBurned);
            System.out.println("Average Sleep Duration: " + averageSleepDuration + " hours");
            System.out.println("Exercise Summary:");
            for (Map.Entry<String, Integer> entry : exerciseSummary.entrySet()) {
                String exerciseType = entry.getKey();
                int caloriesBurned = entry.getValue();
                System.out.println("Exercise: " + exerciseType + ", Calories Burned: " + caloriesBurned);
            }
        } else {
            System.out.println("No health data available for the specified period.");
        }
    }
}

