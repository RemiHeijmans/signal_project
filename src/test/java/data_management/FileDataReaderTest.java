package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.FileDataReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FileDataReaderTest {

    private DataStorage dataStorage;
    private FileDataReader fileDataReader;
    private static final String TEST_DIRECTORY = "test_data";

    @BeforeEach
    public void setUp() {
        dataStorage = new DataStorage();
        fileDataReader = new FileDataReader(TEST_DIRECTORY);
    }

    @Test
    public void testDataReading() {
        // Create test data files
        createTestDataFiles();

        try {
            // Read data from test directory
            fileDataReader.readData(dataStorage);

            // Assert that the data was correctly read and stored in DataStorage
            assertNotNull(dataStorage.getAllPatients());
            assertEquals(1, dataStorage.getAllPatients().size());
        } catch (IOException e) {
            fail("Exception occurred while reading data: " + e.getMessage());
        } finally {
            // Clean up test data files
            deleteTestDataFiles();
        }
    }

    private void createTestDataFiles() {
        try {
            // Create test data directory if it doesn't exist
            File directory = new File(TEST_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Create sample test data files
            String[] testData = {
                "1,75.0,HeartRate",
                "2,120.5,BloodPressure",
                "3,98.6,Temperature",
                "4,60.0,HeartRate",
                "5,110.0,BloodPressure",
                "6,97.2,Temperature"
            };

            for (int i = 0; i < testData.length; i++) {
                File file = new File(TEST_DIRECTORY + "/test" + (i + 1) + ".txt");
                FileWriter writer = new FileWriter(file);
                writer.write(testData[i]);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestDataFiles() {
        // Delete test data files
        File directory = new File(TEST_DIRECTORY);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            directory.delete();
        }
    }
}
