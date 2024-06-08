package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.data_management.DataReader;
import com.data_management.DataStorage;
import com.data_management.WebSocketDataReader;

public class DataReaderTest {
    private DataStorage dataStorage;
    private DataReader dataReader;

    @BeforeEach
    public void setUp() throws Exception {
        dataStorage = DataStorage.getInstance();
        dataReader = new WebSocketDataReader(new URI("ws://localhost:8080"), dataStorage); // Use a mock or local server URI
    }

    @AfterEach
    public void tearDown() throws Exception {
        dataStorage = null;
        dataReader = null;
    }

    @Test
    public void testReadData() {
        // The method readData is not implemented for WebSocketDataReade, hence, i won't call it.
        // Instead, we focus on simulating realtime data reception.
        // This is where you can manually invoke onMessage to simulate receiving a message.
        ((WebSocketDataReader) dataReader).onMessage("1,1700000000000,SystolicPressure,190");
        ((WebSocketDataReader) dataReader).onMessage("1,1700000000000,DiastolicPressure,120");

        // Verify that data is read and stored correctly (integration with DataStorage is indirectly tested)
        assertNotNull(dataStorage.getAllPatients());
        assertFalse(dataStorage.getAllPatients().isEmpty());
    }
}
