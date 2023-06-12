package com.example.healthtracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    public class DataStored {
        private String filePath;

        public DataStored(String filePath) {
            this.filePath = filePath;
        }

        public List<String> readData() {
            List<String> data = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading data from file: " + e.getMessage());
            }

            return data;
        }

        public void writeData(List<String> data) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : data) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing data to file: " + e.getMessage());
            }
        }
    }

