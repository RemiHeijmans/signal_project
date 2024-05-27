package data_management;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.data_management.DataReader;
import com.data_management.DataStorage;
import com.data_management.WebSocketDataReader;

public class DataReaderTest {
    private DataStorage dataStorage;
    private DataReader dataReader;

    @BeforeEach
    public void setUp() throws Exception {
        dataStorage = new DataStorage();
        dataReader = new WebSocketDataReader(null, dataStorage); // Null URI, as we won't actually connect in tests
    }

    @AfterEach
    public void tearDown() throws Exception {
        dataStorage = null;
        dataReader = null;
    }

    @Test
    public void testReadData() {
        try {
            dataReader.readData(dataStorage);
            // Verify that data is read and stored correctly (integration with DataStorage is indirectly tested)
            assertNotNull(dataStorage.getAllPatients());
        } catch (IOException e) {
            fail("IOException should not be thrown during testing");
        }
    }
}
