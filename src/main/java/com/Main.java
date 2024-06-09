package com;

import java.io.IOException;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("DataStorage")) {
            DataStorage.main(new String[]{});
        } else if (args.length > 0 && args[0].equals("HealthDataSimulator")) {
            try {
                HealthDataSimulator.main(new String[]{});
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid command-line argument. Please specify either 'DataStorage' or 'HealthDataSimulator'.");
        }
    }
}
