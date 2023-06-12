package com.example.healthtracker;

public class Food {
    private String name;
    private int calories;
    private String date;
    private String mealType;

    public Food(String mealType,String name, int calories, String date) {
        this.name = name;
        this.calories = calories;
        this.date = date;
        this.mealType = mealType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

//    public String getFoodItem() {
//        return foodItem;
//    }
//
//    public void setFoodItem(String foodItem) {
//        this.foodItem = foodItem;
//    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // toString method

//    @Override
//    public String toString() {
//        return "Food [mealType=" + mealType + calories=" + calories + "date=" + date
//                + "]";
//    }
}
