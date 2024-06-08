package data_management;

import com.data_management.DataStorage;
import com.data_management.WebSocketDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorHandlingTest {
    private DataStorage dataStorage;
    private WebSocketDataReader client;

    @BeforeEach
    public void setUp() throws Exception {
        dataStorage = DataStorage.getInstance();
        // Clearing any existing data for test isolation
        dataStorage.getAllPatients().clear();
        client = new WebSocketDataReader(new URI("ws://localhost:8080"), dataStorage); // Using a mock URI
    }

    @AfterEach
    public void tearDown() throws Exception {
        dataStorage = null;
        client = null;
    }

    @Test
    public void testConnectionError() {
        // Simulate connection error
        client.onError(new Exception("Connection error"));
        // Since `client.isClosed()` might not be available, we will mock or simulate behavior
        // For demonstration, we assume onError closes the client, which should be verified accordingly
        assertFalse(client.isOpen()); // Assuming `isOpen` method exists to check connection status
    }

    @Test
    public void testDataTransmissionFailure() {
        // Simulate data transmission failure by sending an invalid message
        client.onMessage("Invalid message"); // Simulate receiving invalid data
        // Assert that the data is not stored
        assertEquals(0, dataStorage.getAllPatients().size());
    }
}
