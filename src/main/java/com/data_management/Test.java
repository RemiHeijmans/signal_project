package com.data_management;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

public class Test {
    
    @Test
    public void testDataReading() {
        // Create a mock DataStorage
        DataStorage dataStorage = new DataStorage();

        // Specify a directory with test data files
        String directoryPath = "test_data_directory";

        // Create an instance of FileDataReader
        FileDataReader fileDataReader = new FileDataReader(directoryPath);

        try {
            // Call the readData method
            fileDataReader.readData(dataStorage);

            // Assert that the data was correctly read and stored in DataStorage
            // Implement assertions based on the expected data in DataStorage
            // For exampe
            assertNotNull(dataStorage.getPatientData());
            assertEquals(10, dataStorage.getPatientData().size()); // Assuming 10 patient records were read
        } catch (IOException e) {
            fail("Exception occurred while reading data: " + e.getMessage());
        }
    }
}
