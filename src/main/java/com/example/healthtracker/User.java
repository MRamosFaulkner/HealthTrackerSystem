package com.example.healthtracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<DailyCalories> foodItems;
    private List<ExerciseActivity> exerciseActivities;
    private List<SleepHours> sleepHours;

    public User(DataStored dataStored) {
//        this.username = username;
//        this.password = password;
//        this.foodItems = new ArrayList<>();
//        this.exerciseActivities = new ArrayList<>();
//        this.sleepHours = new ArrayList<>();
        this.dataStored = dataStored;
        this.username = dataStored.getUser();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<DailyCalories> getFoodItems() {
        return foodItems;
    }

    // Method to add a food entry
    public void addFood(String mealType, String foodItem, int calories, String date) {
        DailyCalories dailyCalories = new DailyCalories();
        dailyCalories.addFood(mealType, foodItem, calories, date);
        foodItems.add(dailyCalories);
        System.out.println("Food entry added successfully!\n");

        String foodEntry = mealType + "," + foodItem + "," + calories + "," + date;
        dataStored.writeData(Collections.singletonList(foodEntry));
    }


//    public int calculateTotalCalories() {
//        int totalCalories = 0;
//        for (DailyCalories food : foodItems) {
//            totalCalories += food.getCalories();
//        }
//        return totalCalories;
//    }

    public void displayCalorieIntake(String date) {
        System.out.println("Caloric intake for " + date + ": ");
        for (DailyCalories dailyCalories : foodItems) {
            dailyCalories.displayCalorieIntake(date);

        }
    }
//
//    // Method to add a food entry
//    public void addFood(String mealType, String foodItem, int calories, String date) {
//        Food food = new Food(mealType, foodItem, calories, date);
//        foodEntries.add(food);
//        System.out.println("Food entry added successfully!");
//    }

    // Method to add an exercise activity
    public void addExercise(String exerciseType, int duration, int caloriesBurned, String date) {
        ExerciseActivity exercise = new ExerciseActivity(exerciseType, duration, caloriesBurned, date);
        exerciseActivities.add(exercise);
        System.out.println("Exercise activity added successfully!");

        String exerciseEntry = exerciseType + "," + duration + "," + caloriesBurned + "," + date;
        dataStored.writeData(Collections.singletonList(exerciseEntry));
    }

    // Method to add a sleep record
    public void addSleepHours(String sleepStart, String sleepEnd) {
        SleepHours sleepHours = new SleepHours(sleepStart, sleepEnd);
        sleepHours.add(sleepHours);
        System.out.println("Sleep record added successfully!");

        String sleepRecord = sleepStart + "," + sleepEnd;
        dataStored.writeData(Collections.singletonList(sleepRecord));
    }

    // Method to display exercise activities for a specific date
    public void displayExerciseActivities(String date) {
        for (ExerciseActivity exercise : exerciseActivities) {
            if (exercise.getDate().equals(date)) {
                System.out.println(exercise);
            }
        }
    }

    // Method to display sleep records for a specific date
    public void displaySleepHours(String date) {
        for (SleepHours sleepHours : sleepHours) {
            if (sleepHours.getDate().equals(date)) {
                System.out.println(sleepHours);
            }
        }
    }

    public List<String> getFoodEntries() {
        // Read food entries from the data file
        return dataStored.readData();
    }

    public List<String> getExerciseEntries() {
        // Read exercise entries from the data file
        return dataStored.readData();
    }

    public List<String> getSleepRecords() {
        // Read sleep records from the data file
        return dataStored.readData();
    }

}
