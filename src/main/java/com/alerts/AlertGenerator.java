package com.alerts;

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

    private AlertStrategy bloodPressureStrategy;
    private AlertStrategy heartRateStrategy;
    private AlertStrategy oxygenSaturationStrategy;

    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        systolicReadings = new HashMap<>();
        diastolicReadings = new HashMap<>();
        lastSaturationReadings = new HashMap<>();
        lastReadingTimestamps = new HashMap<>();
        lastSaturationTimestamps = new HashMap<>();

        this.bloodPressureStrategy = new BloodPressureStrategy();
        this.heartRateStrategy = new HeartRateStrategy();
        this.oxygenSaturationStrategy = new OxygenSaturationStrategy();
    }

    public void evaluateData(Patient patient) {
        bloodPressureStrategy.checkAlert(patient, this);
        heartRateStrategy.checkAlert(patient, this);
        oxygenSaturationStrategy.checkAlert(patient, this);
    }

    public void triggerAlert(AlertInterface alert) {
        // Wrap alert with decorators if needed
        AlertInterface priorityAlert = new PriorityAlertDecorator(alert, "HIGH");
        AlertInterface repeatedAlert = new RepeatedAlertDecorator(priorityAlert, 1000, 3);
        repeatedAlert.trigger();
    }

    public boolean isTrendAlert(int lastReading, int currentReading) {
        return Math.abs(lastReading - currentReading) > 10;
    }

    public boolean isCriticalThreshold(int systolicPressure, int diastolicPressure) {
        return systolicPressure > 180 || systolicPressure < 90 || diastolicPressure > 120 || diastolicPressure < 60;
    }

    public boolean isRapidSaturationDrop(int patientId, int currentSaturation, long currentTime) {
        Integer lastSaturation = lastSaturationReadings.get(patientId);
        Long lastSaturationTime = lastSaturationTimestamps.get(patientId);

        if (lastSaturation != null && lastSaturationTime != null) {
            return currentSaturation - lastSaturation >= 5 && currentTime - lastSaturationTime <= 10 * 60 * 1000;
        }

        return false;
    }

    public boolean isIrregularBeatPattern() {
        return false;
    }

    public Map<Integer, Integer> getSystolicReadings() {
        return systolicReadings;
    }

    public Map<Integer, Integer> getDiastolicReadings() {
        return diastolicReadings;
    }

    public Map<Integer, Long> getLastReadingTimestamps() {
        return lastReadingTimestamps;
    }

    public Map<Integer, Integer> getLastSaturationReadings() {
        return lastSaturationReadings;
    }

    public Map<Integer, Long> getLastSaturationTimestamps() {
        return lastSaturationTimestamps;
    }
}
