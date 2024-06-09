package data_management;

import com.alerts.AlertGenerator;
import com.alerts.AlertInterface;
import com.alerts.BloodPressureStrategy;
import com.alerts.HeartRateStrategy;
import com.alerts.OxygenSaturationStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class AlertStrategyTest {

    private DataStorage dataStorage;
    private AlertGenerator alertGenerator;

    @BeforeEach
    public void setUp() {
        dataStorage = new DataStorage();
        alertGenerator = new AlertGenerator(dataStorage);
    }

    @Test
    public void testBloodPressureStrategy() {
        // Create a mock Patient with varying blood pressure readings
        Patient patient = new Patient(1);
        patient.setSystolicBloodPressure(120); // Normal systolic pressure
        patient.setSystolicBloodPressure(80); // Normal diastolic pressure

        // Apply BloodPressureStrategy and check for alerts
        BloodPressureStrategy strategy = new BloodPressureStrategy();
        strategy.checkAlert(patient, alertGenerator);

        // Verify no alerts initially
        assertTrue(alertGenerator.getAlerts().isEmpty());

        // Add records to simulate a trend alert
        patient.setSystolicBloodPressure(135); // Increase in systolic pressure
        patient.setSystolicBloodPressure(85); // Increase in diastolic pressure
        strategy.checkAlert(patient, alertGenerator);

        // Verify trend alert triggered
        List<AlertInterface> alerts = alertGenerator.getAlerts();
        assertFalse(alerts.isEmpty());
        assertEquals("Blood Pressure Trend", alerts.get(0).getType());

        // Reset alerts
        alertGenerator.reset();

        // Add records to simulate a critical threshold alert
        patient.setSystolicBloodPressure(185); // Critical systolic pressure
        patient.setSystolicBloodPressure(110); // Critical diastolic pressure
        strategy.checkAlert(patient, alertGenerator);

        // Verify critical threshold alert triggered
        alerts = alertGenerator.getAlerts();
        assertFalse(alerts.isEmpty());
        assertEquals("Critical Blood Pressure", alerts.get(0).getType());
    }

    @Test
    public void testHeartRateStrategy() {
        // Create a mock Patient with varying heart rate readings
        Patient patient = new Patient(1);
        patient.setHeartRate(45); // Abnormal heart rate

        // Apply HeartRateStrategy and check for alerts
        HeartRateStrategy strategy = new HeartRateStrategy();
        strategy.checkAlert(patient, alertGenerator);

        // Verify abnormal heart rate alert triggered
        List<AlertInterface> alerts = alertGenerator.getAlerts();
        assertFalse(alerts.isEmpty());
        assertEquals("Abnormal Heart Rate", alerts.get(0).getType());

        // Reset alerts
        alertGenerator.reset();

        // Add records to simulate a normal heart rate
        patient.setHeartRate(75); // Normal heart rate
        strategy.checkAlert(patient, alertGenerator);

        // Verify no alerts
        assertTrue(alertGenerator.getAlerts().isEmpty());

        // Simulate irregular beat pattern (not implemented in provided code)
        // This test is a placeholder and should be adjusted based on the actual implementation
        // assertTrue(alertGenerator.isIrregularBeatPattern());
    }

    @Test
    public void testOxygenSaturationStrategy() {
        // Create a mock Patient with varying oxygen saturation readings
        Patient patient = new Patient(1);
        patient.setBloodOxygenSaturation(91); // Low blood oxygen saturation

        // Apply OxygenSaturationStrategy and check for alerts
        OxygenSaturationStrategy strategy = new OxygenSaturationStrategy();
        strategy.checkAlert(patient, alertGenerator);

        // Verify low blood oxygen saturation alert triggered
        List<AlertInterface> alerts = alertGenerator.getAlerts();
        assertFalse(alerts.isEmpty());
        assertEquals("Low Blood Oxygen Saturation", alerts.get(0).getType());

        // Reset alerts
        alertGenerator.reset();

        // Add records to simulate a normal oxygen saturation
        patient.setBloodOxygenSaturation(95); // Normal oxygen saturation
        strategy.checkAlert(patient, alertGenerator);

        // Verify no alerts
        assertTrue(alertGenerator.getAlerts().isEmpty());

        // Simulate rapid drop in blood oxygen saturation (assuming implementation)
        // This test is a placeholder and should be adjusted based on the actual implementation
        patient.setBloodOxygenSaturation(85); // Simulate rapid drop
        strategy.checkAlert(patient, alertGenerator);
        alerts = alertGenerator.getAlerts();
        assertFalse(alerts.isEmpty());
        assertEquals("Rapid Drop in Saturation", alerts.get(0).getType());
    }
}

