package com.example.healthtracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;



public class UserManagement {

        private List<User> users;//Stores created users
        private User currentUser;

        //Constructor
        public UserManagement() {
            users = new ArrayList<>();
            currentUser = null;
            loadData();
        }

        //Method that creates new users
        public void createUser(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("Username already exists. Try another name.\n");
                    return;
                }
            }
            User user = new User(username, password);
            users.add(user);
            System.out.println("User created successfully!\n");
        }

        //Method to check if username exists
        public void login(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUser = user;
                    System.out.println("Logged in successfully!\n");
                    System.out.println("Welcome, " + user.getUsername() +"!");
                    return;
                }
            }
            System.out.println("User name or password not valid. Please try again.\n");
        }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void saveData() {
        try {
            // Write the users and their health data to a file(s)
            FileWriter writer = new FileWriter("userData.txt");
            for (User user : users) {
                for (DailyCalories dailyCalories : user.getFoodItems()) {
                    for (int i = 0; i < dailyCalories.getMealTypes().size(); i++) {
                        writer.write(user.getUsername() + ","
                                + dailyCalories.getMealTypes().get(i) + ","
                                + dailyCalories.getFoodItems().get(i) + ","
                                + dailyCalories.getCalories().get(i) + ","
                                + dailyCalories.getDates().get(i) + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        // Read the users and their health data from a file(s)
        try {
            BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String mealType = parts[1];
                String foodItem = parts[2];
                int calories = Integer.parseInt(parts[3]);
                String date = parts[4];
                User user = findUser(username);
                if (user == null) {
                    user = new User(username, "");
                    addUser(user);
                }
                user.addFood(mealType, foodItem, calories, date);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User findUser(String username) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
            return null;
    }
    private void addUser(User user) {
            users.add(user);
    }
}