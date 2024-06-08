package data_management;
import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.AlertFactory;
import com.alerts.BloodOxygenAlertFactory;
import com.alerts.BloodPressureAlertFactory;
import com.alerts.ECGAlertFactory;

import static org.junit.jupiter.api.Assertions.*;

public class AlertFactoryTest {
    @Test
    public void testBloodPressureAlertFactory() {
        AlertFactory factory = new BloodPressureAlertFactory();
        Alert alert = factory.createAlert(1, "High Cholesterol", System.currentTimeMillis());
        assertEquals(1, alert.getPatientId());
        assertEquals("High Cholesterol", alert.getCondition());
    }

    @Test
    public void testBloodOxygenAlertFactory() {
        AlertFactory factory = new BloodOxygenAlertFactory();
        Alert alert = factory.createAlert(2, "Low Blood Oxygen Saturation", System.currentTimeMillis());
        assertEquals(2, alert.getPatientId());
        assertEquals("Low Blood Oxygen Saturation", alert.getCondition());
    }

    @Test
    public void testECGAlertFactory() {
        AlertFactory factory = new ECGAlertFactory();
        Alert alert = factory.createAlert(3, "Abnormal Heart Rate", System.currentTimeMillis());
        assertEquals(3, alert.getPatientId());
        assertEquals("Abnormal Heart Rate", alert.getCondition());
    }
}
