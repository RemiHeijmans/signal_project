package com.alerts;

import com.data_management.Patient;

public class BloodPressureStrategy implements AlertStrategy {
    @Override
    public void checkAlert(Patient patient, AlertGenerator alertGenerator) {
        int patientId = patient.getId();
        long currentTime = System.currentTimeMillis();
        int systolicPressure = patient.getSystolicBloodPressure();
        int diastolicPressure = patient.getDiastolicBloodPressure();
        
        // Check for trend alert
        int lastSystolicReading = alertGenerator.getSystolicReadings().getOrDefault(patientId, systolicPressure);
        int lastDiastolicReading = alertGenerator.getDiastolicReadings().getOrDefault(patientId, diastolicPressure);
        if (alertGenerator.isTrendAlert(lastSystolicReading, systolicPressure) || alertGenerator.isTrendAlert(lastDiastolicReading, diastolicPressure)) {
            alertGenerator.triggerAlert(new Alert(patientId, "Blood Pressure Trend", currentTime));
        }

        // Check for critical threshold alert
        if (alertGenerator.isCriticalThreshold(systolicPressure, diastolicPressure)) {
            alertGenerator.triggerAlert(new Alert(patientId, "Critical Blood Pressure", currentTime));
        }

        // Update readings and timestamp
        alertGenerator.getSystolicReadings().put(patientId, systolicPressure);
        alertGenerator.getDiastolicReadings().put(patientId, diastolicPressure);
        alertGenerator.getLastReadingTimestamps().put(patientId, currentTime);
    }
}
