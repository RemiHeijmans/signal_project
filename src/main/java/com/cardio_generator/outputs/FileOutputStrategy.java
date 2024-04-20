package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * The FileOutputStrategy class implements the OutputStrategy interface   like said in the the interface
 * to output patient data to files on the file system.
 */
public class FileOutputStrategy implements OutputStrategy {

    // The base directory where output files will be stored
    private String baseDirectory;

    /**
     * Constructs a new FileOutputStrategy with the specified base directory.
     *
     * @param baseDirectory The directory where output files will be stored.
     */
    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * Outputs patient data to a file in the specified base directory
     *  
     * @param patientId The ID of the patient associated with the data.
     * @param timestamp The timestamp indicating when the data was generated.
     * @param label A label describing the type or category of the data.
     * @param data The actual data to be output.
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory if it doesn't exist
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }

        // Construct the file path based on the label
        String filePath = Paths.get(baseDirectory, label + ".txt").toString();

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
