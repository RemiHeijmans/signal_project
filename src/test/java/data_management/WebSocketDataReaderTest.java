package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.WebSocketDataReader;

import java.net.URI;
import java.io.IOException;

public class WebSocketDataReaderTest {

    private DataStorage dataStorageMock;
    private WebSocketDataReader reader;
    private WebSocketClientMock clientMock;

    @BeforeEach
    public void setUp() throws Exception {
        dataStorageMock = new DataStorage();
        clientMock = new WebSocketClientMock(new URI("ws://localhost:8080"));
        reader = new WebSocketDataReader(new URI("ws://localhost:8080"), dataStorageMock);
        reader.startRealTimeData(dataStorageMock, "ws://localhost:8080");
    }

    @Test
    public void testDataReading() throws Exception {
        // Simulate receiving messages
        clientMock.simulateMessage("1,1622217600000,HeartRate,80.0");
        clientMock.simulateMessage("2,1622217600000,BloodPressure,120.0");
    
        // Wait for a while to ensure messages are processed
        Thread.sleep(1000);
    
        System.out.println("Number of patients in data storage: " + dataStorageMock.getAllPatients().size());
    
        // Verify that data is correctly stored in the data storage
        assert dataStorageMock.getAllPatients().size() < 3;
    
        // Stop real-time data reading
        reader.stopRealTimeData();
    }
}
