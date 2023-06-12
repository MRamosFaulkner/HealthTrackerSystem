package com.example.healthtracker;

import java.util.ArrayList;
import java.util.List;


public class DailyCalories {

    private List<String> mealTypes;
    private List<String> foodItems;
    private List<Integer> calories;
    private List<String> dates;

    public DailyCalories() {
        mealTypes = new ArrayList<>();
        foodItems = new ArrayList<>();
        calories = new ArrayList<>();
        dates = new ArrayList<>();
    }

    //Method for users to add food info
    public void addFood(String mealType, String foodItem, int calories, String date) {
        mealTypes.add(mealType);
        foodItems.add(foodItem);
        this.calories.add(calories);
        dates.add(date);
    }

    //Method to calculate calories for the day
    public int getTotalCalories() {
        int totalCalories = 0;
        for (int calorie : calories) { //Iterates over calories list and adds to 'totalCalories'
            totalCalories += calorie;
        }
        return totalCalories;
    }

    //Method to display calories for specific date
    public void displayCalorieIntake (String date) {
        System.out.println("Caloric intake for " + date + ":");

        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).equals(date)) {
                System.out.println(mealTypes.get(i) + ":");
                System.out.println("Ate: " + foodItems.get(i));
                System.out.println("Calories: " + calories.get(i));
                System.out.println();
            }
        }
    }

    //Getters - returns each list to be able to access data
    public List<String> getMealTypes() {
        return mealTypes;
    }

    public List<String> getFoodItems() {
        return foodItems;
    }

    public List<Integer> getCalories() {
        return calories;
    }

    public List<String> getDates() {
        return dates;
    }

    //Setters
//    public void setMealType(String mealType) {
//        this.mealType = mealType;
//    }
//
//    public void setFoodItem(String foodItem) {
//        this.foodItem = foodItem;
//    }
//
//    public void setCalories(int calories) {
//        this.calories = calories;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }

}
