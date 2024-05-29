package com.alerts;

// Represents an alert
public class Alert implements AlertInterface {
    private int patientId;
    private String condition;
    private long timestamp;

    public Alert(int patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    @Override
    public int getPatientId() {
        return patientId;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public void trigger() {
        // Trigger the alert (logging, notification, etc.)
        System.out.println("Alert triggered: " + condition + " for patient " + patientId);
    }
}
