package data_management;

import com.alerts.*;
import com.data_management.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertStrategyTest {
    private TestAlertGenerator alertGenerator;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        alertGenerator = new TestAlertGenerator();
        patient = new Patient(1);

        // Add mock data to the patient for testing
        patient.addRecord(190, "SystolicPressure", System.currentTimeMillis());
        patient.addRecord(130, "DiastolicPressure", System.currentTimeMillis());
        patient.addRecord(120, "HeartRate", System.currentTimeMillis());
        patient.addRecord(85, "OxygenSaturation", System.currentTimeMillis());
    }

    @Test
    public void testBloodPressureStrategy() {
        BloodPressureStrategy strategy = new BloodPressureStrategy();
        strategy.checkAlert(patient, alertGenerator);

        // Verify that an alert was triggered
        assertEquals(1, alertGenerator.getAlerts().size());
    }

    @Test
    public void testHeartRateStrategy() {
        HeartRateStrategy strategy = new HeartRateStrategy();
        strategy.checkAlert(patient, alertGenerator);

        // Verify that an alert was triggered
        assertEquals(1, alertGenerator.getAlerts().size());
    }

    @Test
    public void testOxygenSaturationStrategy() {
        OxygenSaturationStrategy strategy = new OxygenSaturationStrategy();
        strategy.checkAlert(patient, alertGenerator);

        // Verify that an alert was triggered
        assertEquals(1, alertGenerator.getAlerts().size());
    }

    // Helper class to test the AlertGenerator
    private static class TestAlertGenerator implements AlertGenerator {
        private final List<Alert> alerts = new ArrayList<>();

        @Override
        public void triggerAlert(Alert alert) {
            alerts.add(alert);
        }

        public List<Alert> getAlerts() {
            return alerts;
        }
    }
}
