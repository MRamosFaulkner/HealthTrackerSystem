package com.example.healthtracker;

public class SleepHours {

        private String sleepStart;
        private String sleepEnd;
        private String date;

        public SleepHours(String sleepStart, String sleepEnd) {
            this.sleepStart = sleepStart;
            this.sleepEnd = sleepEnd;
            // Date can be set based on the system date or any other approach
            // For simplicity, it is not included in the constructor
        }

        // Getters and setters

        public String getSleepStart() {
            return sleepStart;
        }

        public void setSleepStart(String sleepStart) {
            this.sleepStart = sleepStart;
        }

        public String getSleepEnd() {
            return sleepEnd;
        }

        public void setSleepEnd(String sleepEnd) {
            this.sleepEnd = sleepEnd;
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
            return "SleepRecord [sleepStart=" + sleepStart + ", sleepEnd=" + sleepEnd + ", date=" + date + "]";
        }
    }


