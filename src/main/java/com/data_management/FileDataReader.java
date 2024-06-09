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

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            throw new IOException("Specified directory is empty.");
        }

        for (File file : files) {
            if (file.isFile()) {
                List<String> lines = Files.readAllLines(file.toPath());
                for (String line : lines) {
                    // Placeholder parsing logic; should be replaced with actual parsing
                    int patientId = 1;
                    double measurementValue = 75.0;
                    String recordType = "HeartRate";
                    long timestamp = System.currentTimeMillis();
                    
                    dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
                }
            }
        }
    }

    @Override
    public void startRealTimeData(DataStorage dataStorage, String uri) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'startRealTimeData'");
    }

    @Override
    public void stopRealTimeData() {
        throw new UnsupportedOperationException("Unimplemented method 'stopRealTimeData'");
    }
}
