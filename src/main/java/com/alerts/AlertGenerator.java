import com.alerts.Alert;
import com.data_management.DataStorage;
import com.data_management.Patient;


import java.util.HashMap;
import java.util.Map;

public class AlertGenerator {
    private DataStorage dataStorage;
    private Map<Integer, Integer> systolicReadings;
    private Map<Integer, Integer> diastolicReadings;
    private Map<Integer, Integer> lastSaturationReadings;
    private Map<Integer, Long> lastReadingTimestamps;
    private Map<Integer, Long> lastSaturationTimestamps;

    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        systolicReadings = new HashMap<>();
        diastolicReadings = new HashMap<>();
        lastSaturationReadings = new HashMap<>();
        lastReadingTimestamps = new HashMap<>();
        lastSaturationTimestamps = new HashMap<>();
    }

    public void evaluateData(Patient patient) {
        int patientId = patient.getId();
        long currentTime = System.currentTimeMillis();

        // Check for high cholesterol
        if (patient.getCholesterol() > 200) {
            triggerAlert(new Alert(patientId, "High Cholesterol", currentTime));
        }

        // Check for low white blood cell count
        if (patient.getWhiteBloodCellCount() < 3.5) {
            triggerAlert(new Alert(patientId, "Low White Blood Cell Count", currentTime));
        }

        // Check for high systolic blood pressure
        int systolicPressure = patient.getSystolicBloodPressure();
        int diastolicPressure = patient.getDiastolicBloodPressure();
        int lastSystolicReading = systolicReadings.getOrDefault(patientId, systolicPressure);
        int lastDiastolicReading = diastolicReadings.getOrDefault(patientId, diastolicPressure);
        long lastTimestamp = lastReadingTimestamps.getOrDefault(patientId, currentTime);

        // Trend Alert
        if (isTrendAlert(lastSystolicReading, systolicPressure) || isTrendAlert(lastDiastolicReading, diastolicPressure)) {
            triggerAlert(new Alert(patientId, "Blood Pressure Trend", currentTime));
        }

        // Critical Threshold Alert
        if (isCriticalThreshold(systolicPressure, diastolicPressure)) {
            triggerAlert(new Alert(patientId, "Critical Blood Pressure", currentTime));
        }

        // Update readings and timestamp
        systolicReadings.put(patientId, systolicPressure);
        diastolicReadings.put(patientId, diastolicPressure);
        lastReadingTimestamps.put(patientId, currentTime);

        // Check for low blood oxygen saturation.
        if (patient.getBloodOxygenSaturation() < 92) {
            triggerAlert(new Alert(patientId, "Low Blood Oxygen Saturation", currentTime));
        }

        // Check for rapid drop in blood oxygen saturation
        if (isRapidSaturationDrop(patientId, patient.getBloodOxygenSaturation(), currentTime)) {
            triggerAlert(new Alert(patientId, "Rapid Drop in Saturation", currentTime));
        }

        // Combined Alert: Hypotensive Hypoxemia Alert
        if (patient.getSystolicBloodPressure() < 90 && patient.getBloodOxygenSaturation() < 92) {
            triggerAlert(new Alert(patientId, "Hypotensive Hypoxemia", currentTime));
        }

        // Check for abnormal heart rate
        int heartRate = patient.getHeartRate();
        if (heartRate < 50 || heartRate > 100) {
            triggerAlert(new Alert(patientId, "Abnormal Heart Rate", currentTime));
        }

        // Check for irregular beat pattern
        if (isIrregularBeatPattern()) {
            triggerAlert(new Alert(patientId, "Irregular Beat Pattern", currentTime));
        }

        // Handle triggered alert from HealthDataGenerator
        // Assuming HealthDataGenerator triggers this alert
        // This is a placeholder for actual implementation
        if (patient.isTriggeredAlert()) {
            triggerAlert(new Alert(patientId, "Triggered Alert", currentTime));
        }
    }

    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff...
    }

    private boolean isTrendAlert(int lastReading, int currentReading) {
        return Math.abs(lastReading - currentReading) > 10;
    }

    private boolean isCriticalThreshold(int systolicPressure, int diastolicPressure) {
        return systolicPressure > 180 || systolicPressure < 90 || diastolicPressure > 120 || diastolicPressure < 60;
    }

    private boolean isRapidSaturationDrop(int patientId, int currentSaturation, long currentTime) {
        Integer lastSaturation = lastSaturationReadings.get(patientId);
        Long lastSaturationTime = lastSaturationTimestamps.get(patientId);

        if (lastSaturation != null && lastSaturationTime != null) {
            return currentSaturation - lastSaturation >= 5 && currentTime - lastSaturationTime <= 10 * 60 * 1000;
        }

        return false;
    }

    private boolean isIrregularBeatPattern() {
        // Implement logic to check for irregular beat pattern
        // I don't know how to write the logic for this.
        return false; // Placeholder, needs implementation
    }
}
