package com.alerts;

import com.data_management.Patient;

public class OxygenSaturationStrategy implements AlertStrategy {
    @Override
    public void checkAlert(Patient patient, AlertGenerator alertGenerator) {
        int patientId = patient.getId();
        long currentTime = System.currentTimeMillis();
        int bloodOxygenSaturation = patient.getBloodOxygenSaturation();
        
        // Check for low blood oxygen saturation
        if (bloodOxygenSaturation < 92) {
            alertGenerator.triggerAlert(new Alert(patientId, "Low Blood Oxygen Saturation", currentTime));
        }

        // Check for rapid drop in blood oxygen saturation
        if (alertGenerator.isRapidSaturationDrop(patientId, bloodOxygenSaturation, currentTime)) {
            alertGenerator.triggerAlert(new Alert(patientId, "Rapid Drop in Saturation", currentTime));
        }
    }
}
