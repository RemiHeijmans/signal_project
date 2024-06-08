package com.data_management;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileDataReader implements DataReader {

    private String directoryPath;



    
    public FileDataReader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IOException("Specified directory does not exist or is not a directory.");
        }

        // Get list of files in the directory
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            throw new IOException("Specified directory is empty.");
        }

        // Loop through each file and read its content
        for (File file : files) {
            if (file.isFile()) {
                List<String> lines = Files.readAllLines(file.toPath());
                // Assuming each line represents a data entry
                for (String line : lines) {
                    // Parse the data entry and extract patient ID, measurement value, record type, and timestamp
                    // For example:
                    int patientId = 1; // Sample patient ID, replace with actual parsing logic
                    double measurementValue = 75.0; // Sample measurement value, replace with actual parsing logic
                    String recordType = "HeartRate"; // Sample record type, replace with actual parsing logic
                    long timestamp = System.currentTimeMillis(); // Sample timestamp, replace with actual parsing logic
                    
                    // Add the parsed data entry to the DataStorage
                    dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
                }
            }
        }
    }

    @Override
    public void startRealTimeData(DataStorage dataStorage, String uri) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startRealTimeData'");
    }

    @Override
    public void stopRealTimeData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopRealTimeData'");
    }
}
