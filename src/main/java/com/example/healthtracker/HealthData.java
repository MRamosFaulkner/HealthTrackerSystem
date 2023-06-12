import java.util.*;

public class HealthData {
    private final DataStored dataStored;

    public HealthData(DataStored dataStored) {
        this.dataStored = dataStored;
    }

    public void calculateDailyCaloricBalance(String date) {
        int consumedCalories = 0;
        int burnedCalories = 0;
        List<String> entries = dataStored.readData();

        for (String entry : entries) {
            if (entry.startsWith(date)) {
                String[] parts = entry.split(",");
                if (entry.contains("Food")) {
                    consumedCalories += Integer.parseInt(parts[3]);
                } else if (entry.contains("Exercise")) {
                    burnedCalories += Integer.parseInt(parts[4]);
                }
            }
        }

        int caloricBalance = consumedCalories - burnedCalories;
        System.out.println("Daily Caloric Balance for " + date + ": " + caloricBalance);
    }

    public void calculateAverageSleepDuration(String startDate, String endDate) {
        int totalSleepHours = 0;
        int numDays = 0;
        List<String> entries = dataStored.readData();

        for (String entry : entries) {
            if (entry.startsWith("Sleep")) {
                String[] parts = entry.split(",");
                String sleepDate = parts[0];
                if (sleepDate.compareTo(startDate) >= 0 && sleepDate.compareTo(endDate) <= 0) {
                    int sleepHours = calculateSleepHours(parts[1], parts[2]);
                    totalSleepHours += sleepHours;
                    numDays++;
                }
            }
        }

        double averageSleepHours = (double) totalSleepHours / numDays;
        System.out.println("Average Sleep Duration: " + averageSleepHours + " hours per day");

        for (String entry : entries) {
            if (entry.startsWith("Sleep")) {
                String[] parts = entry.split(",");
                String sleepDate = parts[0];
                if (sleepDate.compareTo(startDate) >= 0 && sleepDate.compareTo(endDate) <= 0) {
                    int sleepHours = calculateSleepHours(parts[1], parts[2]);
                    if (sleepHours < averageSleepHours) {
                        System.out.println("Day with significantly less sleep: " + sleepDate);
                    }
                }
            }
        }
    }

    private int calculateSleepHours(String sleepStart, String sleepEnd) {
        // Calculate the duration of sleep in hours
        // You can implement your own logic here
    }

    public void displayExerciseLog() {
        List<String> entries = dataStored.readData();

        for (String entry : entries) {
            if (entry.contains("Exercise")) {
                String[] parts = entry.split(",");
                String exerciseType = parts[1];
                int duration = Integer.parseInt(parts[2]);
                int caloriesBurned = Integer.parseInt(parts[3]);
                System.out.println("Exercise: " + exerciseType + ", Duration: " + duration + " mins, Calories Burned: " + caloriesBurned);
            }
        }
    }

    public void generateHealthSummary(String startDate, String endDate) {
        int totalConsumedCalories = 0;
        int totalBurnedCalories = 0;
        int totalSleepHours = 0;
        int numDays = 0;
        Map<String, Integer> exerciseCategories = new HashMap<>();
        List<String> entries = dataStored.readData();

        for (String entry : entries) {
            String[] parts = entry.split(",");
            String date = parts[0];

            if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
                if (entry.contains("Food")) {
                    int consumedCalories = Integer.parseInt(parts[3]);
                    totalConsumedCalories += consumedCalories;
                } else if (entry.contains("Exercise")) {
                    int burnedCalories = Integer.parseInt(parts[4]);
                    totalBurnedCalories += burnedCalories;
                    String exerciseType = parts[1];
                    exerciseCategories.put(exerciseType, exerciseCategories.getOrDefault(exerciseType, 0) + 1);
                } else if (entry.contains("Sleep")) {
                    int sleepHours = calculateSleepHours(parts[1], parts[2]);
                    totalSleepHours += sleepHours;
                }

                numDays++;
            }
        }

        double averageCaloricBalance = (double) (totalConsumedCalories - totalBurnedCalories) / numDays;
        System.out.println("Average Daily Caloric Balance: " + averageCaloricBalance);
        System.out.println("Total Calories Consumed: " + totalConsumedCalories);
        System.out.println("Total Calories Burned: " + totalBurnedCalories);
        System.out.println("Total Sleep Hours: " + totalSleepHours);

        // Find the most common exercise category
        String mostCommonExerciseCategory = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : exerciseCategories.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonExerciseCategory = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        if (mostCommonExerciseCategory != null) {
            System.out.println("Most Common Exercise Category: " + mostCommonExerciseCategory);
        }
    }
}
