package data_management;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.data_management.DataStorage;
import com.data_management.DataWebSocketClient;

public class WebSocketClientTest {
    private DataStorage dataStorage;
    private DataWebSocketClient client;

    @BeforeAll
    public void setUp() throws Exception {
        dataStorage = new DataStorage();
        client = new DataWebSocketClient(null, dataStorage); // Null URI, as we won't actually connect in tests
    }

    @AfterEach
    public void tearDown() throws Exception {
        dataStorage = null;
        client = null;
    }

    @Test
    public void testMessageParsing() {
        // Test a valid message
        client.onMessage("1,1622217600000,HeartRate,80.0");
        assertEquals(1, dataStorage.getRecords(1, 1622217600000L, 1622217600001L).size());

        // Test an invalid message (missing fields)
        client.onMessage("2,1622217600000");
        assertEquals(0, dataStorage.getRecords(2, 1622217600000L, 1622217600001L).size());
    }

    @Test
    public void testConnectionLoss() {
        // Simulate connection loss
        client.onClose(1006, "Connection lost", true);
        // Assert that the client attempts to reconnect
        assertTrue(client.isClosed());
    }
}
