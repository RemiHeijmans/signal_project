package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.WebSocketDataReader;
import com.data_management.Patient;
import com.data_management.PatientRecord;

public class IntegrationTest {
    private DataStorage dataStorage;
    private WebSocketDataReader client;
    private AlertGenerator alertGenerator;

    @BeforeEach
    public void setUp() throws Exception {
        dataStorage = DataStorage.getInstance();
        dataStorage.getAllPatients().clear(); // Clear any existing data for test isolation
        client = new WebSocketDataReader(new URI("ws://localhost:8080"), dataStorage); // Use a mock URI
        alertGenerator = new AlertGenerator(dataStorage);
    }

    @AfterEach
    public void tearDown() throws Exception {
        dataStorage = null;
        client = null;
        alertGenerator = null;
    }

    @Test
    public void testRealTimeDataProcessing() {
        client.onOpen(null); // Simulate connection
        client.onMessage("1,1622217600000,HeartRate,80.0");
        client.onMessage("1,1622217601000,BloodPressure,120.0");
        client.onClose(1000, "Connection closed", true); // Simulate connection closure

        List<PatientRecord> records = dataStorage.getRecords(1, 1622217600000L, 1622217601001L);
        assertEquals(2, records.size());

        List<Patient> patients = dataStorage.getAllPatients();
        for (Patient patient : patients) {
            alertGenerator.evaluateData(patient);
            // Add assertions for alerts if needed
        }
    }
}
