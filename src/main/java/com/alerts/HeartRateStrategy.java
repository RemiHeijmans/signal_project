package com.alerts;

import com.data_management.Patient;

public class HeartRateStrategy implements AlertStrategy {
    @Override
    public void checkAlert(Patient patient, AlertGenerator alertGenerator) {
        int patientId = patient.getId();
        long currentTime = System.currentTimeMillis();
        int heartRate = patient.getHeartRate();
        
        // Check for abnormal heart rate
        if (heartRate < 50 || heartRate > 100) {
            alertGenerator.triggerAlert(new Alert(patientId, "Abnormal Heart Rate", currentTime));
        }

        // Check for irregular beat pattern
        if (alertGenerator.isIrregularBeatPattern()) {
            alertGenerator.triggerAlert(new Alert(patientId, "Irregular Beat Pattern", currentTime));
        }
    }
}
