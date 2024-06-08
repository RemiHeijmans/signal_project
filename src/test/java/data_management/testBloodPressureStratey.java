package data_management;

import com.alerts.AlertGenerator;
import com.alerts.BloodPressureStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testBloodPressureStratey {

    @Test
    public void testBloodPressureStrategy() {
        // Create a mock DataStorage
        DataStorage dataStorage = new DataStorage();

        // Create an instance of AlertGenerator
        AlertGenerator alertGenerator = new AlertGenerator();

        // Create a mock Patient with varying blood pressure readings
        Patient patient = new Patient(1);
        patient.addRecord(120, "SystolicBloodPressure", System.currentTimeMillis()); // Normal systolic pressure
        patient.addRecord(80, "DiastolicBloodPressure", System.currentTimeMillis()); // Normal diastolic pressure

        // Apply BloodPressureStrategy and check for alerts
        BloodPressureStrategy strategy = new BloodPressureStrategy();
        strategy.checkAlert(patient, alertGenerator);

        // Verify no alerts initially
        assertTrue(alertGenerator.getAlerts().isEmpty());

        // Add records to simulate a trend alert
        patient.addRecord(125, "SystolicBloodPressure", System.currentTimeMillis() + 1000); // Increase in systolic pressure
        patient.addRecord(85, "DiastolicBloodPressure", System.currentTimeMillis() + 1000); // Increase in diastolic pressure
        strategy.checkAlert(patient, alertGenerator);

        // Verify trend alert triggered
        assertFalse(alertGenerator.getAlerts().isEmpty());
        assertEquals("Blood Pressure Trend", alertGenerator.getAlerts().get(0).getType());

        // Reset alerts
        alertGenerator.reset();

        // Add records to simulate a critical threshold alert
        patient.addRecord(180, "SystolicBloodPressure", System.currentTimeMillis() + 2000); // Critical systolic pressure
        patient.addRecord(110, "DiastolicBloodPressure", System.currentTimeMillis() + 2000); // Critical diastolic pressure
        strategy.checkAlert(patient, alertGenerator);

        // Verify critical threshold alert triggered
        assertFalse(alertGenerator.getAlerts().isEmpty());
        assertEquals("Critical Blood Pressure", alertGenerator.getAlerts().get(0).getType());
    }

    // Similar tests for HeartRateStrategy and OxygenSaturationStrategy
}
