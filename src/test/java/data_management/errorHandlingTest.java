package data_management;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.data_management.DataStorage;
import com.data_management.DataWebSocketClient;

public class ErrorHandlingTest {
    private DataStorage dataStorage;
    private DataWebSocketClient client;

    @Before
    public void setUp() throws Exception {
        dataStorage = new DataStorage();
        client = new DataWebSocketClient(null, dataStorage); // Null URI, as we won't actually connect in tests
    }

    @After
    public void tearDown() throws Exception {
        dataStorage = null;
        client = null;
    }

    @Test
    public void testConnectionError() {
        // Simulate connection error
        client.onError(new Exception("Connection error"));
        // Assert that the client attempts to reconnect
        assertTrue(client.isClosed());
    }

    @Test
    public void testDataTransmissionFailure() {
        // Simulate data transmission failure
        client.onMessage("Invalid message"); // Simulate receiving invalid data
        // Assert that the data is not stored
        assertEquals(0, dataStorage.getAllPatients().size());
    }
}
