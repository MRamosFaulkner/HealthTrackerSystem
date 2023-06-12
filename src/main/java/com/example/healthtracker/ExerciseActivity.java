package com.example.healthtracker;

public class ExerciseActivity {
    private String exerciseType;
    private int duration;
    private int caloriesBurned;
    private String date;

    public ExerciseActivity(String exerciseType, int duration, int caloriesBurned, String date) {
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

    // Getters and setters

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // toString method

    @Override
    public String toString() {
        return "ExerciseActivity [exerciseType=" + exerciseType + ", duration=" + duration + ", caloriesBurned="
                + caloriesBurned + ", date=" + date + "]";
    }
}

